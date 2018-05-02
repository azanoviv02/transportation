package com.netcracker.transportation.algorithms.auction.auxillary.entities;

import java.util.ArrayList;
import java.util.List;

import static com.netcracker.transportation.utils.GeneralUtils.flatten;

/**
 * Class for holding Flow objects (to allow iteration over them)
 */
public class FlowHolder {

    private final List<List<Flow>> flowMatrix;
    private final List<Flow> unassignedFlowList;

    private final List<Flow> flatFlowList;

    public FlowHolder(List<List<Flow>> flowMatrix, List<Flow> unassignedFlowList) {
        this.flowMatrix = flowMatrix;
        this.unassignedFlowList = unassignedFlowList;

        this.flatFlowList = flatten(flowMatrix);
        this.flatFlowList.addAll(unassignedFlowList);
    }

    public List<List<Flow>> getFlowMatrix() {
        return flowMatrix;
    }

    public List<Flow> getFlowsForSource(Source source) {
        int sourceIndex = source.getSourceIndex();
        return flowMatrix.get(sourceIndex);
    }

    public List<Flow> getUnassignedFlowList() {
        return unassignedFlowList;
    }

    public List<Flow> getFlatFlowList() {
        return flatFlowList;
    }

    public static FlowHolder init(List<Source> sourceList,
                                  List<Sink> sinkList) {
        int sourceAmount = sourceList.size();
        int sinkAmount = sinkList.size();

        List<List<Flow>> flowMatrix = new ArrayList<>(sourceAmount);
        for (Source source : sourceList) {
            List<Flow> flowList = new ArrayList<>(sinkAmount);
            for (Sink sink : sinkList) {
                flowList.add(new Flow(source, sink));
            }
            flowMatrix.add(flowList);
        }

        Source emptySource = new Source(-1);
        List<Flow> unusedFlowList = new ArrayList<>(sinkAmount);
        for (Sink sink : sinkList) {
            unusedFlowList.add(new Flow(emptySource, sink));
        }

        return new FlowHolder(flowMatrix, unusedFlowList);
    }
}
