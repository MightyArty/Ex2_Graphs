package Algo;

import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.EdgeData;
import api.NodeData;

import java.util.*;

public class DWGraphAlgorithm implements DirectedWeightedGraphAlgorithms {

    // HashMap that holds the information gathered by the Dijkstra algorithm for each node
    private HashMap<Integer, dNodeData> dNodeMap;

    public DirectedWeightedGraph myGraph;
    public Node myData;
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

    @Override
    public boolean isConnected() {
        return false;
    }

    @Override
    public double shortestPathDist(int src, int dest) {
        // declaration of variables //

            if (myGraph == null || myGraph.getNode(src) == null || myGraph.getNode(dest) == null){
                return -1;
            }
        if (src == dest) {
            return 0;
        }
        // Using the Dijkstra algorithm to find the shortest path according to the weight from src to dest
        Dijkstra(src,dest);
        // If the hashMap does not contain the dest key, then it did not reach the dest node, return -1






//        while (!storeQueue.isEmpty()) {
//            currPos = storeQueue.poll();
//            if (currPos == dest){
//                flag = true;
//            }
//            else {
//                flag = false;
//            }
//        }

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
    private  class dNodeData {
        // The summed weight from src to this node
        private double weightSum;
        // The parent of this node from src
        private Node nodeParent;

        // Constructor //
        public dNodeData(double weightSum, Node nodeParent) {
            this.nodeParent = nodeParent;
            this.weightSum = weightSum;
        }
        public Node getNodeParent() {
            return nodeParent;
        }

        public double getWeightSum(){
            return weightSum;
        }

    }

}
