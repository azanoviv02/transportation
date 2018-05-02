package com.netcracker.transportation.algorithms.auction;

import com.netcracker.transportation.algorithms.Solution;
import com.netcracker.transportation.algorithms.TransportationProblem;
import com.netcracker.transportation.algorithms.TransportationProblemSolver;
import com.netcracker.transportation.algorithms.auction.auxillary.entities.*;
import com.netcracker.transportation.algorithms.auction.auxillary.logic.epsilonscaling.EpsilonSequenceProducer;

import java.util.*;

import static java.util.Collections.reverseOrder;

public class AuctionAlgorithm implements TransportationProblemSolver {

    private final EpsilonSequenceProducer epsilonProducer;

    public AuctionAlgorithm(EpsilonSequenceProducer epsilonProducer) {
        this.epsilonProducer = epsilonProducer;
    }

    @Override
    public Solution getSolution(TransportationProblem problem) {
        int sourceAmount = problem.getSupplyArray().length;
        int sinkAmount = problem.getDemandArray().length;
        PriceMatrix priceMatrix = PriceMatrix.init(sourceAmount, sinkAmount);

        List<Double> epsilonSequence = epsilonProducer.getEpsilonSequence(sinkAmount);

        Solution solution = null;
        for (Double epsilon : epsilonSequence) {
            solution = epsilonScalingPhase(problem, priceMatrix, epsilon);
        }
        return solution;
    }

    public Solution epsilonScalingPhase(TransportationProblem problem,
                                        PriceMatrix priceMatrix,
                                        double epsilon) {
        int[] supplyArray = problem.getSupplyArray();
        int sourceAmount = supplyArray.length;
        int[] demandArray = problem.getDemandArray();
        int sinkAmount = demandArray.length;

        List<Source> sourceList = new ArrayList<>(sourceAmount);
        for (int i = 0; i < sourceAmount; i++) {
            sourceList.add(new Source(i));
        }

        List<Sink> sinkList = new ArrayList<>(sinkAmount);
        for (int i = 0; i < sinkAmount; i++) {
            sinkList.add(new Sink(i));
        }

        FlowHolder flowHolder = FlowHolder.init(sourceList, sinkList);
        FlowStrengthMatrix flowStrengthMatrix = FlowStrengthMatrix.init(supplyArray, demandArray);

        while (true) {
            auctionRound(
                    problem,
                    priceMatrix,
                    flowStrengthMatrix,
                    sourceList,
                    sinkList,
                    flowHolder,
                    epsilon
            );

            // checking if allocation if complete
            boolean allocationIsComplete = true;
            for (Source source : sourceList) {
                int remainingFlowStrength = flowStrengthMatrix.getRemainingFlowStrength(source);
                if (remainingFlowStrength < 0) {
                    throw new IllegalStateException("Underflow");
                } else if (remainingFlowStrength > 0) {
                    allocationIsComplete = false;
                    break;
                }
            }
            if (allocationIsComplete) {
                return new Solution(problem, flowStrengthMatrix.getFlowStrengthMatrix());
            }
        }
    }

    public void auctionRound(TransportationProblem problem,
                             PriceMatrix priceMatrix,
                             FlowStrengthMatrix flowStrengthMatrix,
                             List<Source> sourceList,
                             List<Sink> sinkList,
                             FlowHolder flowHolder,
                             double epsilon) {
        int[][] benefitMatrix = problem.getBenefitMatrix();
        int[] supplyArray = problem.getSupplyArray();
        int[] demandArray = problem.getDemandArray();

        int sourceAmount = supplyArray.length;
        int sinkAmount = demandArray.length;

        //=== Bidding phase ===

        List<Bid> bidList = new ArrayList<>();
        for (Source source : sourceList) {
            int remainingFlowStrength = flowStrengthMatrix.getRemainingFlowStrength(source);
            if (remainingFlowStrength == 0) {
                continue;
            }

            // Queue, which ranks all existing flows according to their values for current source.
            Queue<Pair<Double, Flow>> flowPriorityQueue = new PriorityQueue<>(reverseOrder());
            int sourceIndex = source.getSourceIndex();
            for (Flow flow : flowHolder.getFlatFlowList()) {
                int sinkIndex = flow.getSink().getSinkIndex();
                int benefit = benefitMatrix[sourceIndex][sinkIndex];
                double price = priceMatrix.getPriceForFlow(flow);
                double value = benefit - price;
                flowPriorityQueue.add(new Pair<>(value, flow));
            }


            // Selecting top flows, which this source can actually support
            double addedFlowStrength = 0.0;
            List<Flow> chosenFlowList = new ArrayList<>();
            while (addedFlowStrength < remainingFlowStrength) {
                Flow flow = flowPriorityQueue.remove().getSecond();
                chosenFlowList.add(flow);

                int strengthValue = flowStrengthMatrix.getStrength(flow);
                addedFlowStrength += strengthValue;
            }


            // Calculating new value of flows for current source
            Map<Sink, Integer> updatedFlowValues = new HashMap<>();
            for (Flow flow : flowHolder.getFlowsForSource(source)) {
                int flowStrength = flowStrengthMatrix.getStrength(flow);
                updatedFlowValues.put(flow.getSink(), flowStrength);
            }
            List<Flow> chosenFlowListWithoutLast = chosenFlowList.subList(0, chosenFlowList.size() - 1);
            for (Flow flow : chosenFlowListWithoutLast) {
                Sink sink = flow.getSink();
                Integer flowStrength = updatedFlowValues.get(sink);
                flowStrength += flowStrengthMatrix.getStrength(flow);
                updatedFlowValues.put(sink, flowStrength);
            }

            // Making bid for selected flows

            System.out.println(chosenFlowList);
        }

        //=== Assignment phase ===


    }
}
