package com.netcracker.transportation.algorithms.auction.auxillary.entities;

import static java.lang.String.format;

public class Flow {

    private final Source source;
    private final Sink sink;

    public Flow(Source source, Sink sink) {
        this.source = source;
        this.sink = sink;
    }

    public Source getSource() {
        return source;
    }

    public Sink getSink() {
        return sink;
    }

    @Override
    public String toString() {
        return format("(%s, %s)", source, sink);
    }
}
