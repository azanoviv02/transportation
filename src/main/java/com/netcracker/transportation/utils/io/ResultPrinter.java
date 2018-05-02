package com.netcracker.transportation.utils.io;

import com.netcracker.transportation.algorithms.Solution;
import com.netcracker.transportation.utils.io.logging.Logger;
import com.netcracker.transportation.utils.io.logging.SystemOutLogger;

import java.util.Map;

public class ResultPrinter {

    private final static Logger logger = new SystemOutLogger(true);

    public static void printResults(Map<String, Map<String, Solution>> allAllocations) {
        allAllocations.forEach((problemName, allAllocationsForProblem) -> {
            logger.info("Assignments for problem: %s", problemName);
            allAllocationsForProblem.forEach((solverName, allocation) -> {
                int totalCost = allocation.getTotalCost();
                logger.info("(%s) Total cost: %d", solverName, totalCost);
                logger.info("%s", allocation);
            });
            logger.info("\n");
        });
    }
}
