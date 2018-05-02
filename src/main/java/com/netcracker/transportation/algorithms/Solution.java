package com.netcracker.transportation.algorithms;

import com.netcracker.transportation.utils.MatrixFormatter;

public class Solution {

    private final TransportationProblem problem;
    private final int[][] allocationMatrix;

    public Solution(TransportationProblem problem, int[][] allocationMatrix) {
        this.problem = problem;
        this.allocationMatrix = allocationMatrix;
    }

    public TransportationProblem getProblem() {
        return problem;
    }

    public int[][] getAllocationMatrix() {
        return allocationMatrix;
    }

    public int getTotalCost() {
        int[][] costMatrix = problem.getBenefitMatrix();
        int totalCost = 0;
        for (int i = 0; i < costMatrix.length; i++) {
            for (int j = 0; j < costMatrix[0].length; j++) {
                totalCost += costMatrix[i][j] * allocationMatrix[i][j];
            }
        }
        return totalCost;
    }

    @Override
    public String toString() {
        return MatrixFormatter.formatSolution(this);
    }

}
