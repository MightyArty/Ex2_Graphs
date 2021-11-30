package Algo;

import api.DirectedWeightedGraph;
import api.EdgeData;
import api.NodeData;

import java.util.*;

public class DWGraph implements DirectedWeightedGraph {
    private HashMap<Integer, NodeData> nodes;
    private HashMap<Vector<Integer>, EdgeData> edges;
    private HashMap<Integer, HashSet<Integer>> againstDirection;
    private int edgeSize;
    private int mc;

    /**
     * Copy Constructor
     */
    public DWGraph(){
        this.nodes = new HashMap<>();  // new node map
        this.edges = new HashMap<>();   // new edges map
        this.edgeSize = 0;
        this.mc = 0;
    }

    /**
     * returns the Ex2.node_data by the node_id,
     * @param key - the node_id
     * @return the Ex2.node_data by the node_id, null if none.
     */
    @Override
    public NodeData getNode(int key) {
        if(!this.nodes.containsKey(key))
            throw new RuntimeException("The key does not exist!");
        return nodes.get(key);
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
        if(!nodes.containsKey(src) || !nodes.containsKey(dest) || src == dest)
            throw new RuntimeException("There is no source or destination or the source is equal to destination so put a right data!");
        Vector<Integer> v = new Vector<>(src,dest);
        return edges.get(v);
    }

    public HashMap<Integer, NodeData> getNodes(){
        return nodes;
    }

    public HashMap<Vector<Integer>, EdgeData> getEdges(){
        return edges;
    }

    /**
     * adds a new node to the graph with the given Ex2.node_data.
     * Note: this method should run in O(1) time.
     * @param n
     */
    @Override
    public void addNode(NodeData n) {
        if(n == null || this.nodes.containsKey(n.getKey()) || nodes.get(n.getKey()) != null)
            throw new RuntimeException("The new node does not exist or the key already exist");
        nodes.put(n.getKey(),(Node)n);
        this.mc ++;
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
        if(!this.nodes.containsKey(src) || !this.nodes.containsKey(dest) || src == dest || w < 0)
            return;
        EdgeData edge = new EData(src, dest, w);
        Vector<Integer> v = new Vector<>(src,dest);
        edges.remove(v);
        edges.put(v,edge);
        Node node = (Node) nodes.get(src);
        node.addFromSRC(dest,edge);
        node = (Node) nodes.get(dest);
        node.addToDEST(src,edge);
        this.mc ++;
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
        if(!this.nodes.containsKey(key))
            throw new RuntimeException("The graph does not contain this node!");
        NodeData deleted = this.nodes.get(key); // getting the needed node to delete
        this.nodes.remove(key);
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
}
