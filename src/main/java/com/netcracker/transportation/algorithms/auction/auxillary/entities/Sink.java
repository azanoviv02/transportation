package com.netcracker.transportation.algorithms.auction.auxillary.entities;

public class Sink {

    private final int sinkIndex;

    public Sink(int sinkIndex) {
        this.sinkIndex = sinkIndex;
    }

    public int getSinkIndex() {
        return sinkIndex;
    }

    @Override
    public String toString() {
        return Integer.toString(sinkIndex);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sink)) return false;

        Sink sink = (Sink) o;

        return getSinkIndex() == sink.getSinkIndex();
    }

    @Override
    public int hashCode() {
        return getSinkIndex();
    }
}
