package com.netcracker.transportation.algorithms.auction.auxillary.entities;

public class Source {

    private final int sourceIndex;

    public Source(int sourceIndex) {
        this.sourceIndex = sourceIndex;
    }

    public int getSourceIndex() {
        return sourceIndex;
    }

    @Override
    public String toString() {
        return Integer.toString(sourceIndex);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Source)) return false;

        Source source = (Source) o;

        return getSourceIndex() == source.getSourceIndex();
    }

    @Override
    public int hashCode() {
        return getSourceIndex();
    }
}
