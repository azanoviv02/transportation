package com.netcracker.transportation.utils;

import com.netcracker.transportation.algorithms.TransportationProblem;

import java.util.LinkedHashMap;
import java.util.Map;

public class ProblemSupplier {

    public static Map<String, TransportationProblem> createProblemMap() {
        Map<String, TransportationProblem> problemMap = new LinkedHashMap<>();

        problemMap.put("trivial",
                new TransportationProblem(
                        new int[][]{
                                {19, 30, 50, 12},
                                {70, 30, 40, 60},
                                {40, 10, 60, 20}
                        },
                        new int[]{7, 10, 18},
                        new int[]{5, 8, 7, 15}
                )
        );

        return problemMap;
    }
}
