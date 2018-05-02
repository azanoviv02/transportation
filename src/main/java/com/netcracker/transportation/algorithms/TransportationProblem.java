package com.netcracker.transportation.algorithms;

public class TransportationProblem {

    private final int[][] benefitMatrix;
    private final int[] supplyArray;
    private final int[] demandArray;

    public TransportationProblem(int[][] benefitMatrix,
                                 int[] supplyArray,
                                 int[] demandArray) {
        this.benefitMatrix = benefitMatrix;
        this.supplyArray = supplyArray;
        this.demandArray = demandArray;
    }

    public int[][] getBenefitMatrix() {
        return benefitMatrix;
    }

    public int[] getSupplyArray() {
        return supplyArray;
    }

    public int[] getDemandArray() {
        return demandArray;
    }
}
