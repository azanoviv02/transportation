package com.netcracker.transportation.algorithms.auction.auxillary.entities;

import com.netcracker.transportation.utils.MatrixFormatter;

import static com.netcracker.transportation.utils.GeneralUtils.getFilledDoubleMatrix;

public class PriceMatrix {

    public final static double INITIAL_PRICE = 1.0;

    public static PriceMatrix init(int sourceAmount,
                                   int sinkAmount) {
        double[][] priceMatrix = getFilledDoubleMatrix(
                sourceAmount,
                sinkAmount,
                INITIAL_PRICE
        );
        return new PriceMatrix(priceMatrix);
    }

    private final double[][] priceMatrix;

    public PriceMatrix(double[][] priceMatrix) {
        this.priceMatrix = priceMatrix;
    }

    public double[][] getPriceMatrix() {
        return priceMatrix;
    }

    public double getPriceForFlow(Flow flow) {
        int sourceIndex = flow.getSource().getSourceIndex();
        int sinkIndex = flow.getSink().getSinkIndex();
        if (sinkIndex < 0) {
            throw new IllegalStateException();
        }
        if (sourceIndex < 0) {
            return INITIAL_PRICE;
        }
        return priceMatrix[sourceIndex][sinkIndex];
    }

    public void setPriceForFlow(Flow flow, double price) {
        int sourceIndex = flow.getSource().getSourceIndex();
        int sinkIndex = flow.getSink().getSinkIndex();
        if (sourceIndex < 0 || sinkIndex < 0) {
            throw new IllegalStateException();
        }
        priceMatrix[sourceIndex][sinkIndex] = price;
    }

    public void updatePriceForFlow(Flow flow, double difference) {
        int sourceIndex = flow.getSource().getSourceIndex();
        int sinkIndex = flow.getSink().getSinkIndex();
        if (sourceIndex < 0 || sinkIndex < 0) {
            throw new IllegalStateException();
        }
        priceMatrix[sourceIndex][sinkIndex] += difference;
    }

    @Override
    public String toString() {
        return MatrixFormatter.formatDoubleMatrix(priceMatrix);
    }
}
