package com.netcracker.transportation.utils;

import com.netcracker.transportation.algorithms.Solution;
import com.netcracker.transportation.algorithms.TransportationProblem;

import static java.lang.String.format;

public class MatrixFormatter {

    public static final String NEW_LINE = "\n";

    public static String formatIntMatrix(int[][] matrix) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                sb.append(format("%3d ", matrix[i][j]));
            }
            sb.append(NEW_LINE);
        }
        return sb.toString();
    }

    public static String formatDoubleMatrix(double[][] matrix) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                sb.append(format("%3.0f ", matrix[i][j]));
            }
            sb.append(NEW_LINE);
        }
        return sb.toString();
    }

    public static String formatSolution(Solution solution) {
        int[][] allocationMatrix = solution.getAllocationMatrix();

        TransportationProblem problem = solution.getProblem();
        int[] supplyArray = problem.getSupplyArray();
        int[] demandArray = problem.getDemandArray();

        StringBuilder sb = new StringBuilder();
        sb.append(format("%5d ", 0));
        for (int j = 0; j < demandArray.length; j++) {
            sb.append(format("(%3d) ", demandArray[j]));
        }
        sb.append(NEW_LINE);
        for (int i = 0; i < allocationMatrix.length; i++) {
            sb.append(format("(%3d) ", supplyArray[i]));
            for (int j = 0; j < allocationMatrix[i].length; j++) {
                sb.append(format("%5d ", allocationMatrix[i][j]));
            }
            sb.append(NEW_LINE);
        }

        return sb.toString();
    }
}
