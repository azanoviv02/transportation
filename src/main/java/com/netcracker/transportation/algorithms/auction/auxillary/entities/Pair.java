package com.netcracker.transportation.algorithms.auction.auxillary.entities;

import static java.lang.String.format;

public class Pair<K extends Comparable<K>, V> implements Comparable<Pair<K, V>> {

    private final K first;
    private final V second;

    public Pair(K first, V second) {
        this.first = first;
        this.second = second;
    }

    public K getFirst() {
        return first;
    }

    public V getSecond() {
        return second;
    }

    @Override
    public int compareTo(Pair<K, V> o) {
        return this.getFirst().compareTo(o.getFirst());
    }

    @Override
    public String toString() {
        return format("(%s, %s)", first, second);
    }
}
