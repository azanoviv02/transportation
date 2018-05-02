package com.netcracker.transportation.algorithms.auction.auxillary.entities;

import static java.lang.String.format;

public class Bid implements Comparable<Bid> {

    private final Source source;
    private final Sink sink;
    private final Flow flow;
    private final double bidValue;

    public Bid(Source source,
               Sink sink,
               Flow flow,
               double bidValue) {
        this.source = source;
        this.sink = sink;
        this.flow = flow;
        this.bidValue = bidValue;
    }

    public Source getSource() {
        return source;
    }

    public Sink getSink() {
        return sink;
    }

    public Flow getFlow() {
        return flow;
    }

    public double getBidValue() {
        return bidValue;
    }

    @Override
    public String toString() {
        return format("(%s, %s, %d)", sink, sink, bidValue);
    }

    @Override
    public int compareTo(Bid o) {
        return Double.compare(this.bidValue, o.bidValue);
    }
}
