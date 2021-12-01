package Algo;

import api.DirectedWeightedGraph;
import api.EdgeData;
import api.NodeData;

import java.util.*;

public class DWGraph implements DirectedWeightedGraph {
    private HashMap<Integer, NodeData> vertexes;
    private HashMap<Integer, HashMap<Integer, EData>> edges;
    // private HashMap<Integer, HashSet<Integer>> againstDirection;
    private int edgeSize;
    private int mc;

    /**
     * Copy Constructor
     */
    public DWGraph() {
        this.vertexes = new HashMap<>();
        this.edges = new HashMap<>();
        //this.againstDirection = new HashMap<>();
        this.edgeSize = 0;
        this.mc = 0;
    }

    /**
     * returns the Ex2.node_data by the node_id,
     *
     * @param key - the node_id
     * @return the Ex2.node_data by the node_id, null if none.
     */
    @Override
    public NodeData getNode(int key) {
        if (this.vertexes.containsKey(key))
            return this.vertexes.get(key);
        return null;
    }

    /**
     * returns the data of the edge (src,dest), null if none.
     * Note: this method should run in O(1) time.
     *
     * @param src
     * @param dest
     * @return
     */
    @Override
    public EdgeData getEdge(int src, int dest) {
        if (!vertexes.containsKey(src) || !vertexes.containsKey(dest) || src == dest)
            throw new RuntimeException("There is no source or destination or the source is equal to destination");
        else return edges.get(src).get(dest);
    }

    /**
     * adds a new node to the graph with the given Ex2.node_data.
     * Note: this method should run in O(1) time.
     *
     * @param n
     */
    @Override
    public void addNode(NodeData n) {
        if (n == null || this.vertexes.containsKey(n.getKey()))
            return;
        vertexes.put(n.getKey(), n);
        this.mc++;
//        int key = n.getKey();
//        this.graph.put(key, n);
//        edges.put(key, new HashMap<>());
//        againstDirection.put(key, new HashSet<>());
//        this.mc++;
    }

    /**
     * Connects an edge with weight w between node src to node dest.
     * * Note: this method should run in O(1) time.
     *
     * @param src  - the source of the edge.
     * @param dest - the destination of the edge.
     * @param w    - positive weight representing the cost (aka time, price, etc) between src-->dest.
     */
    @Override
    public void connect(int src, int dest, double w) {
        if (!this.vertexes.containsKey(src) || this.vertexes.containsKey(dest) || src == dest || w < 0)
            return;
        EData e = new EData(src, dest, w);
        HashMap<Integer, EData> temp = edges.get(src);
        temp.put(dest, e);
        //check if there is any edge from the src
        if (edges.containsKey(src)) {
            Collection<EData> t = temp.values();
            if (t.contains(dest)) return;
                //add the edge for the src vertex to dest vertex
            else
                edges.put(src, temp);

        } else
            //add a new edge
            edges.put(src, temp);
        this.edgeSize++;
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
     *
     * @param key
     * @return the data of the removed node (null if none).
     */
    @Override
    public NodeData removeNode(int key) {
        HashMap<Integer, EData> temp = this.edges.get(key);
        if (!this.vertexes.containsKey(key) && !temp.containsKey(key)) {
            return null;
        }
        Node del = (Node) this.vertexes.get(key);
        if (temp.containsKey(key)) {
            this.vertexes.remove(key);
            this.edges.get(key).clear();

        } else {
            this.vertexes.remove(key);
            this.edges.remove(key);
        }
        this.mc++;
        return del;
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
