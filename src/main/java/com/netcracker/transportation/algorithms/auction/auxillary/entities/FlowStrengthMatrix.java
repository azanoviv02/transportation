package com.netcracker.transportation.algorithms.auction.auxillary.entities;

import com.netcracker.transportation.utils.MatrixFormatter;

import static com.netcracker.transportation.utils.GeneralUtils.getFilledIntMatrix;

public class FlowStrengthMatrix {

    public static final int INITIAL_FLOW_VALUE = 0;

    public static FlowStrengthMatrix init(int[] supplyArray,
                                          int[] demandArray) {

        int sourceAmount = supplyArray.length;
        int sinkAmount = demandArray.length;
        int[][] flowValueMatrix = getFilledIntMatrix(
                sourceAmount,
                sinkAmount,
                INITIAL_FLOW_VALUE
        );
        return new FlowStrengthMatrix(
                flowValueMatrix,
                supplyArray,
                demandArray
        );
    }

    private final int[][] flowStrengthMatrix;
    private final int[] supplyArray;
    private final int[] demandArray;

    public FlowStrengthMatrix(int[][] flowStrengthMatrix, int[] supplyArray, int[] demandArray) {
        this.flowStrengthMatrix = flowStrengthMatrix;
        this.supplyArray = supplyArray;
        this.demandArray = demandArray;
    }

    public int[][] getFlowStrengthMatrix() {
        return flowStrengthMatrix;
    }

    public int getStrength(Flow flow) {
        Source source = flow.getSource();
        int sourceIndex = source.getSourceIndex();

        Sink sink = flow.getSink();
        int sinkIndex = sink.getSinkIndex();

        if (sourceIndex < 0 && sinkIndex < 0) {
            throw new IllegalStateException();
        }

        if (sourceIndex < 0) {
            return getRemainingFlowStrengthForSink(sink);
        }

        if (sinkIndex < 0) {
            return getRemainingFlowStrength(source);
        }

        return flowStrengthMatrix[sourceIndex][sinkIndex];
    }

    public void setStrengthForFlow(Flow flow, int strength) {
        int sourceIndex = flow.getSource().getSourceIndex();
        int sinkIndex = flow.getSink().getSinkIndex();
        if (sourceIndex < 0 || sinkIndex < 0) {
            throw new IllegalStateException();
        }
        flowStrengthMatrix[sourceIndex][sinkIndex] = strength;
    }

    public void updateStrengthForFlow(Flow flow, int difference) {
        int sourceIndex = flow.getSource().getSourceIndex();
        int sinkIndex = flow.getSink().getSinkIndex();
        if (sourceIndex < 0 || sinkIndex < 0) {
            throw new IllegalStateException();
        }
        flowStrengthMatrix[sourceIndex][sinkIndex] += difference;
    }

    @Override
    public String toString() {
        return MatrixFormatter.formatIntMatrix(flowStrengthMatrix);
    }

    //=== for Sink ===

    public int getRemainingFlowStrengthForSink(Sink sink) {
        int sinkIndex = sink.getSinkIndex();
        int maxFlow = demandArray[sinkIndex];
        int usedFlow = getUsedFlowStrengthForSink(sink);
        return maxFlow - usedFlow;
    }


    public int getUsedFlowStrengthForSink(Sink sink) {
        int sinkIndex = sink.getSinkIndex();
        int usedFlowStrength = 0;
        for (int sourceIndex = 0; sourceIndex < supplyArray.length; sourceIndex++) {
            usedFlowStrength += flowStrengthMatrix[sourceIndex][sinkIndex];
        }
        return usedFlowStrength;
    }

    //=== for Source ===

    public int getRemainingFlowStrength(Source source) {
        int sourceIndex = source.getSourceIndex();
        int maxFlow = supplyArray[sourceIndex];
        int usedFlow = getUsedFlowStrengthForSource(source);
        return maxFlow - usedFlow;
    }


    public int getUsedFlowStrengthForSource(Source source) {
        int sourceIndex = source.getSourceIndex();
        int usedFlowStrength = 0;
        for (int sinkIndex = 0; sinkIndex < demandArray.length; sinkIndex++) {
            usedFlowStrength += flowStrengthMatrix[sourceIndex][sinkIndex];
        }
        return usedFlowStrength;
    }
}
