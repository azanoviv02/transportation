package com.netcracker.transportation.algorithms.auction;

//public class AuctionAlgorithm implements TransportationProblemSolver {
public class AuctionAlgorithmFail {
//
//    /**
//     * Method for calculating total cost for an already solved problem.
//     *
//     * @param costMatrix
//     * @param flowMatrix
//     * @return
//     */
//    public int getTotalCost(int[][] costMatrix, int[][] flowMatrix) {
//        int totalCost = 0;
//
//        for (int i = 0; i < costMatrix.length; i++) {
//            for (int j = 0; j < costMatrix[0].length; j++) {
//                totalCost += costMatrix[i][j] * flowMatrix[i][j];
//            }
//        }
//
//        return totalCost;
//    }
//
//    @Override
//    public int[][] getFlowMatrix(int[][] costMatrix, int[] sourceArray, int[] sinkArray) {
//
//        int numRows = costMatrix.length;
//        int numCols = costMatrix[0].length;
//
//        int[] reserveSourceArray = Arrays.copyOf(sourceArray, sourceArray.length);
//        int[] reserveSinkArray = Arrays.copyOf(sinkArray, sinkArray.length);
//
//        double[][] priceMatrix = new double[numRows][numCols];
//        fillMatrix(priceMatrix, 1.0);
//
//        int[][] flowMatrix = new int[numRows][numCols];
//        fillMatrix(flowMatrix, MAX_VALUE);
//
//        for (double epsilon = 1.0; epsilon > 1.0 / numRows; epsilon *= .25) {
//            fillMatrix(flowMatrix, MAX_VALUE);
//            while (!assignmentIsComplete(flowMatrix, reserveSourceArray, reserveSinkArray)) {
//                auctionRound(flowMatrix, priceMatrix, costMatrix, sourceArray, sinkArray, epsilon);
//            }
//        }
//
//        return flowMatrix;
//    }
//
//
//    private void auctionRound(int[][] flowMatrix,
//                              double[][] priceMatrix,
//                              int[][] costMatrix,
//                              int[] sourceArray,
//                              int[] sinkArray,
//                              double epsilon) {
//
//        int numRows = costMatrix.length;
//        int numCols = costMatrix[0].length;
//
//        double[][] bids = new double[numRows][numCols];
//
//        List<Integer> tempBidded = new LinkedList<>();
//        List<Double> tempBids = new LinkedList<>();
//        List<Integer> nonAssigned = new LinkedList<>();
//
//	    /* Compute the bids of each unassigned individual and store them in temp */
//        for (int i = 0; i < numRows; i++) {
//            int remainingSourceCapacity = getRemainingSourceCapacity(flowMatrix, sourceArray, i);
//            if (remainingSourceCapacity != 0) {
//                nonAssigned.add(i);
//
//                /*
//                    Need the best and second best profit of each object to this person
//                    where profit is calculated row_{j} - prices{j}
//                */
//                Queue<Flow> profitQueue = new PriorityQueue<>();
//                for (int j = 0; j < numCols; j++) {
//                    int cost = costMatrix[i][j];
//                    for(int k = 0; k < numRows; k++){
//                        int flowValue = flowMatrix[k][j];
//                        double profit = cost - priceMatrix[k][j];
//                        profitQueue.add(new Flow(k, j, flowValue, profit));
//                    }
//                }
//                LinkedList<Flow> biddedFlowList = new LinkedList<>();
//                int biddedFlowSum = 0;
//                while (biddedFlowSum < remainingSourceCapacity) {
//                    Flow biddedFlow = profitQueue.remove();
//                    biddedFlowList.add(biddedFlow);
//                }
//                Flow nonBiddedFlow = biddedFlowList.removeLast();
//                for (Flow flow : biddedFlowList) {
//                    int sinkIndex = flow.getSinkIndex();
//                    int flowValue = flow.getFlowValue();
//                    flowMatrix[i][sinkIndex] += flowValue;
//                }
//                int nonBiddedFlowSinkIndex = nonBiddedFlow.getSinkIndex();
//                int totalFlowFromSource = getTotalFlowFromSource(flowMatrix, i, nonBiddedFlowSinkIndex);
//                flowMatrix[i][nonBiddedFlowSinkIndex] = sourceArray[i] - totalFlowFromSource;
//
//
//			    /* Computes the highest reasonable bid for the best object for this person */
//                double bidForI = optValForI - secOptValForI + epsilon;
//
//			    /* Stores the bidding info for future use */
//                tempBidded.add(optObjForI);
//                tempBids.add(bidForI);
//            }
//        }
//
//        /*
//            Each object which has received a bid determines the highest bidder and
//            updates its price accordingly
//        */
//        for (int j = 0; j < numCols; j++) {
//            List<Integer> indexList = getIndicesWithValue(tempBidded, j);
//            if (indexList.size() != 0) {
//
//			    /* Need the highest bid for object j */
//                double highestBidForJ = -MAX_VALUE;
//                int i_j = -1;
//                for (int i = 0; i < indexList.size(); i++) {
//                    double curVal = tempBids.get(indexList.get(i));
//                    if (curVal > highestBidForJ) {
//                        highestBidForJ = curVal;
//                        i_j = indexList.get(i);
//                    }
//                }
//
//			    /* Find the other person who has object j and make them unassigned */
//                for (int i = 0; i < flowMatrix.size(); i++) {
//                    if (flowMatrix.get(i) == j) {
//                        flowMatrix.set(i, MAX_VALUE);
//                        break;
//                    }
//                }
//
//			    /* Assign object j to i_j and update the price array */
//                flowMatrix.set(nonAssigned.get(i_j), j);
//                priceMatrix[j] += highestBidForJ;
//            }
//        }
//    }
//
//    private static List<Integer> getFilledList(int n, int value) {
//        return new ArrayList<>(Collections.nCopies(n, value));
//    }
//
//    private static List<Integer> getIndicesWithValue(final List<Integer> list, final int value) {
//        return IntStream
//                .range(0, list.size())
//                .filter(i -> list.get(i) == value)
//                .boxed()
//                .collect(Collectors.toList());
//    }
//
//    private static boolean matrixContains(int[][] matrix, int value) {
//        for (int[] row : matrix) {
//            for (int cell : row) {
//                if (cell == value) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
//
//    public static boolean assignmentIsComplete(int[][] flowMatrix, int[] sourceArray, int[] sinkArray) {
//        int numRows = flowMatrix.length;
//        int numCols = flowMatrix[0].length;
//
//        for (int i = 0; i < numRows; i++) {
//            int maxSource = sourceArray[i];
//            int currentSource = 0;
//            for (int j = 0; j < numCols; j++) {
//                currentSource += flowMatrix[i][j];
//            }
//            if (currentSource != maxSource) {
//                return false;
//            }
//        }
//
//        for (int j = 0; j < numCols; j++) {
//            int maxSink = sinkArray[j];
//            int currentSink = 0;
//            for (int i = 0; i < numRows; i++) {
//                currentSink += flowMatrix[i][j];
//            }
//            if (currentSink != maxSink) {
//                return false;
//            }
//        }
//
//        return true;
//    }
//
//    public static boolean sourceIsComplete(int[][] flowMatrix, int[] sourceArray, int sourceIndex){
//        return getRemainingSourceCapacity(flowMatrix, sourceArray, sourceIndex) == 0;
//    }
//
//    public static int getRemainingSourceCapacity(int[][] flowMatrix, int[] sourceArray, int sourceIndex){
//        int numCols = flowMatrix[0].length;
//
//        int maxSource = sourceArray[sourceIndex];
//        int currentSource = 0;
//        for (int j = 0; j < numCols; j++) {
//            currentSource += flowMatrix[sourceIndex][j];
//        }
//
//        return maxSource - currentSource;
//    }
//
//    private static int getTotalFlowFromSource(int[][] flowMatrix, int sourceIndex, int exludedSinkIndex){
//        int numSinks = flowMatrix[0].length;
//        return IntStream
//                .range(0,numSinks)
//                .filter(j -> j != exludedSinkIndex)
//                .map(j -> flowMatrix[sourceIndex][j])
//                .sum();
//    }
//
//    private static void fillMatrix(double[][] matrix, double value) {
//        for (double[] row : matrix) {
//            Arrays.fill(row, value);
//        }
//    }
//
//    private static void fillMatrix(int[][] matrix, int value) {
//        for (int[] row : matrix) {
//            Arrays.fill(row, value);
//        }
//    }
//
//    private static class Flow implements Comparable<Flow> {
//        private final int sourceIndex;
//        private final int sinkIndex;
//        private final int flowValue;
//        private final double profit;
//
//        public Flow(int sourceIndex, int sinkIndex, int flowValue, double profit) {
//            this.sourceIndex = sourceIndex;
//            this.sinkIndex = sinkIndex;
//            this.flowValue = flowValue;
//            this.profit = profit;
//        }
//
//        public int getSourceIndex() {
//            return sourceIndex;
//        }
//
//        public int getSinkIndex() {
//            return sinkIndex;
//        }
//
//        public int getFlowValue() {
//            return flowValue;
//        }
//
//        public double getProfit() {
//            return profit;
//        }
//
//        @Override
//        public int compareTo(Flow o) {
//            return Double.compare(o.profit, this.profit);
//        }
//}
}
