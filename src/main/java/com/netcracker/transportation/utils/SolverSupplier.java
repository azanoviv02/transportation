package com.netcracker.transportation.utils;

import com.netcracker.transportation.algorithms.TransportationProblemSolver;
import com.netcracker.transportation.algorithms.auction.AuctionAlgorithm;
import com.netcracker.transportation.algorithms.auction.auxillary.logic.epsilonscaling.DefaultEpsilonSequenceProducer;

import java.util.LinkedHashMap;
import java.util.Map;

public class SolverSupplier {

    public static Map<String, TransportationProblemSolver> createSolverMap() {
        DefaultEpsilonSequenceProducer epsilonSequenceProducer = new DefaultEpsilonSequenceProducer(
                1.0,
                0.25
        );

        Map<String, TransportationProblemSolver> solverMap = new LinkedHashMap<>();
//        solverMap.put("Modi", new ModiMethod());
        solverMap.put("Auction", new AuctionAlgorithm(epsilonSequenceProducer));

        return solverMap;
    }

}
