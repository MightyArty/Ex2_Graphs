package Algo;

import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.EdgeData;
import api.NodeData;

import java.util.*;

public class DWGraphAlgorithm implements DirectedWeightedGraphAlgorithms {

    // HashMap that holds the information gathered by the Dijkstra algorithm for each node
    private HashMap<Integer, HashMap<Integer, dNodeData>> dNodeMap;

    public DirectedWeightedGraph myGraph;
    public NodeData myData;
    public EdgeData myEdge;
    public boolean OK = true;

    /**
     * Constructor
     *
     * @param graph
     */
    public DWGraphAlgorithm(DirectedWeightedGraph graph) {
        this.myGraph = graph;
    }

    /**
     * Empty Constructor
     */
    public DWGraphAlgorithm() {
    }

    @Override
    public void init(DirectedWeightedGraph g) {
        this.myGraph = new DWGraph(g);
    }

    @Override
    public DirectedWeightedGraph getGraph() {
        return null;
    }

    @Override
    public DirectedWeightedGraph copy() {
        return null;
    }

    //    @Override
    public boolean isConnected() {
        return false;
    }

    @Override
    public double shortestPathDist(int src, int dest) {

        if (myGraph == null || myGraph.getNode(src) == null || myGraph.getNode(dest) == null) {
            return -1;
        }
        if (src == dest) {
            return 0;
        }
        // Using the Dijkstra algorithm to find the shortest path according to the weight from src to dest
        shortAssist(src, dest);
        // If the hashMap does not contain the dest key, then it did not reach the dest node, return -1


        return 0;
    }

    /**
     * Returning the shortest path from src to dest - as list of NODES
     * example : src --> node1 --> node2 --> node3 --> ... --> dest
     *
     * @param src  - start node
     * @param dest - end (target) node
     * @return
     */
    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        // if there is no src or no dest just return
        if (myGraph.getNode(src) == null || myGraph.getNode(dest) == null) {
            return null;
        }
        Queue<EData> Qdist = new LinkedList<EData>();
        Queue<EData> Qnode = new LinkedList<EData>();

        HashMap<Integer, Double> dist = new HashMap<Integer, Double>(myGraph.nodeSize());
        HashMap<Integer, ArrayList<Integer>> path = new HashMap<Integer, ArrayList<Integer>>(myGraph.nodeSize());
        EData currNode = null;
        Node index = (Node) myGraph.getNode(src);
        dist.put(src, (double) 0);
        path.put(src, new ArrayList<Integer>());
        path.get(src).add(src);
        int j = 0;
//
    }

    @Override
    public NodeData center() {
        return null;
    }

    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
        return null;
    }

    @Override
    public boolean save(String file) {
        return false;
    }

    @Override
    public boolean load(String file) {
        return false;
    }
    // David

    private void shortAssist(int src, int dest) {

//        dNodeMap = new HashMap<>();
        HashMap<Integer, dNodeData> cNodeMap = new HashMap<>();
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        // Adds the src node to the queue
        NodeData srcNode = myGraph.getNode(src);
        queue.add(srcNode.getKey());
        //  holds the src with weight zero and puts it in the map
        dNodeData n = new dNodeData();
        cNodeMap.put(src, n);
        dNodeMap.put(src, cNodeMap);
        int tempSrc = src;
        // Looping while queue is not empty
        while (!queue.isEmpty()) {
            // Gets the node with the lowest weight from the priority queue
//            NodeData node = queue.poll();
            // Loop over all the edges of this specific node

            Iterator<EdgeData> iterator = myGraph.edgeIter(tempSrc);
//            for (EdgeData edge : myGraph.edgeIter(Node.getKey())) {
            while (iterator.hasNext()) {

                EdgeData next = iterator.next();
                // Calculate the weight sum by adding to the current weight of the edge to the previous sum weight
                double sumWeight = next.getWeight() + dNodeMap.get(next.getSrc()).get(next.getSrc()).getWeightSum();
                dNodeData temp = new dNodeData();
                temp.setWeightSum(sumWeight);
                cNodeMap.put(next.getDest(), temp);
                dNodeMap.put(next.getDest(), cNodeMap);
//                int tempNode = queue.poll();



//              NodeData neighbor = myGraph.getNode(edge.getDest());
                // Check if the neighbor does not exists in the map and the queue
//                if (!dNodeMap.containsKey(neighbor.getKey()) && !queue.contains(neighbor)) {
//                    // Add the neighbor to the map and to the queue
//                    dNodeMap.put(edge.getDest(), new dNodeData(sumWeight, node));
//                    queue.add(neighbor);
            }
            if (queue.isEmpty()) {
                // need to be done - to check which veret's are connected to this node
                // and to add them to the queue
            tempSrc = queue.peek();
            }

            // If the neighbor already exists in the map and the weightSum is lower, then replace it in the map
//                else if (sumWeight < dNodeMap.get(edge.getDest()).getWeightSum()) {
//                    dNodeMap.put(edge.getDest(), new dNodeData(sumWeight, node));
        }
        // If the current node is equal to dest then return
        if (dest != null && node.getKey() == dest) {
            return;
        }
    }
}
    }

// to check if there's a path between 2 verts - DFS / BFS / something else

private class dNodeData {
    // The summed weight from src to this node
    private double weightSum;
    // The parent of this node from src
    private Node nodeParent;

    // Constructor //
    public dNodeData() {
        this.nodeParent = null;
        this.weightSum = 0;
    }

    public void setNodeParent(Node nodeParent) {
        this.nodeParent = nodeParent;
    }

    public void setWeightSum(double weightSum) {
        this.weightSum = weightSum;
    }


    public Node getNodeParent() {
        return nodeParent;
    }

    public double getWeightSum() {
        return weightSum;
    }

}

}
