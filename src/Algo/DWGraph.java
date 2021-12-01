package Algo;

import api.DirectedWeightedGraph;
import api.EdgeData;
import api.NodeData;

import java.util.*;

public class DWGraph implements DirectedWeightedGraph {
    private HashMap<Integer, NodeData> graph;
    private HashMap<Integer, HashMap<Integer, EdgeData>> edges;
    private HashMap<Integer, HashSet<Integer>> againstDirection;
    private int edgeSize;
    private int mc;



    /**
     * returns the Ex2.node_data by the node_id,
     * @param key - the node_id
     * @return the Ex2.node_data by the node_id, null if none.
     */
    @Override
    public NodeData getNode(int key) {
        if(this.graph.containsKey(key))
            return this.graph.get(key);
        return null;
    }

    /**
     * returns the data of the edge (src,dest), null if none.
     * Note: this method should run in O(1) time.
     * @param src
     * @param dest
     * @return
     */
    @Override
    public EdgeData getEdge(int src, int dest) {
        if(!graph.containsKey(src) || !graph.containsKey(dest) || src == dest)
            throw new RuntimeException("There is no source or destination or the source is equal to destination");
        else return edges.get(src).get(dest);
    }

    /**
     * adds a new node to the graph with the given Ex2.node_data.
     * Note: this method should run in O(1) time.
     * @param n
     */
    @Override
    public void addNode(NodeData n) {
        if(n == null || this.graph.containsKey(n.getKey()))
            return;
        int key = n.getKey();
        this.graph.put(key,n);
        edges.put(key,new HashMap<>());
        againstDirection.put(key,new HashSet<>());
        this.mc++;
    }

    /**
     * Connects an edge with weight w between node src to node dest.
     * * Note: this method should run in O(1) time.
     * @param src - the source of the edge.
     * @param dest - the destination of the edge.
     * @param w - positive weight representing the cost (aka time, price, etc) between src-->dest.
     */
    @Override
    public void connect(int src, int dest, double w) {
        if(!this.graph.containsKey(src) || this.graph.containsKey(dest) || src == dest || w < 0)
            return;
        HashMap<Integer, EdgeData> srcNode = edges.get(src);
        EdgeData edge = new EData(src,dest,w);
        // if the edge exist then will check the weight of the edge
        if(srcNode.containsKey(dest)){
            if(srcNode.get(dest).getWeight() == w)
                return;
            // updating only the edge
            srcNode.put(dest,edge);
        }
        // there is no edge so will create new one with the given weight
        else {
            srcNode.put(dest,edge);
            againstDirection.get(dest).add(src);
            edgeSize++;
        }
        this.mc++;
    }

    @Override
    public Iterator<NodeData> nodeIter() {
        return null;
    }

    @Override
    public Iterator<EdgeData> edgeIter() {
        return null;
    }

    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {
        return null;
    }

    /**
     * Deletes the node (with the given ID) from the graph -
     * and removes all edges which starts or ends at this node.
     * This method should run in O(k), V.degree=k, as all the edges should be removed.
     * @return the data of the removed node (null if none).
     * @param key
     */
    @Override
    public NodeData removeNode(int key) {
        if(!this.graph.containsKey(key))
            throw new RuntimeException("The graph does not contain this node!");
        NodeData deleted = this.graph.get(key); // getting the needed node to delete
        this.graph.remove(key);
        this.mc++;
        // NEED TO WORK
    }

    @Override
    public EdgeData removeEdge(int src, int dest) {
        return null;
    }

    @Override
    public int nodeSize() {
        return this.nodeSize();
    }

    @Override
    public int edgeSize() {
        return this.edgeSize;
    }

    @Override
    public int getMC() {
        return this.mc;
    }

    /**
     * Copy Constructor
     */
    public DWGraph() {
        this.graph = new HashMap<>();
        this.edges = new HashMap<>();
        this.againstDirection = new HashMap<>();
        this.edgeSize = 0;
        this.mc = 0;
    }
}
