package Algo;

import api.DirectedWeightedGraph;
import api.EdgeData;
import api.NodeData;

import java.util.*;

public class DWGraph implements DirectedWeightedGraph {
    private HashMap<Integer, NodeData> nodes;
    private HashMap<Integer, HashMap<Integer, EdgeData>> graph;
    private HashMap<Vector<Integer>, EdgeData> edges;
    // private HashMap<Integer, HashSet<Integer>> againstDirection;
    private int edgeSize;
    private int mc;

    /**
     * Copy Constructor
     */
    public DWGraph() {
        this.nodes = new HashMap<>();
        this.graph = new HashMap<>();
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
        if (this.nodes.containsKey(key))
            return this.nodes.get(key);
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
        if (!nodes.containsKey(src) || !nodes.containsKey(dest) || src == dest)
            throw new RuntimeException("There is no source or destination or the source is equal to destination");
        Vector<Integer> v = new Vector<>(src, dest);
        return this.edges.get(v);
    }

    /**
     * adds a new node to the graph with the given Ex2.node_data.
     * Note: this method should run in O(1) time.
     *
     * @param n
     */
    @Override
    public void addNode(NodeData n) {
        if (n == null || this.nodes.containsKey(n.getKey()))
            return;
        nodes.put(n.getKey(), n);
        this.mc++;
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
        if (!this.nodes.containsKey(src) || !this.nodes.containsKey(dest) || src == dest || w < 0)
            return;
        EData e = new EData(src, dest, w);
        HashMap<Integer, EdgeData> temp = new HashMap<>();
        //check if there is any edge from the src
        if (graph.containsKey(src)) {
            temp = graph.get(src); //src=1 dest =3
            temp.put(dest, e);
            Collection<EdgeData> t = temp.values();
            if (t.contains(dest)) return;
                //add the edge for the src vertex to dest vertex
            else
                graph.get(src).put(dest, e);

        } else {
            //add a new edge
            temp.put(src, e);
            graph.put(src, temp);
        }
        this.edgeSize++;
        this.mc++;
    }

    @Override
    public Iterator<NodeData> nodeIter() {
        return nodes.values().iterator();
    }

    @Override
    public Iterator<EdgeData> edgeIter() {
        return this.edges.values().iterator();
    }

    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {
        Node temp = (Node) this.nodes.get(node_id);
        Iterator<Map.Entry<Integer, NodeData>> iterator = this.nodes.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, NodeData> entry = iterator.next();
            if (node_id == entry.getKey()) {

            }
        }


        return null;
    }

    /**
     * Deletes the node (with the given ID) from the graph -
     * and removes all edges which starts or ends at this node.
     * This method should run in O(k), V.degree=k, as all the edges should be removed.
     * @param key
     * @return the data of the removed node (null if none).
     */
    @Override
    public NodeData removeNode(int key) {
        //check if the node is does not exist
        if (!this.nodes.containsKey(key))
            return null;
        Node node = (Node) nodes.remove(key);
        Iterator<EdgeData> iterator = node.getNodeIterator();
        while (iterator.hasNext()){
            EdgeData runner = iterator.next();
            Vector<Integer> vector = new Vector<>(runner.getSrc(), runner.getDest());
            edges.remove(vector);
            graph.get(runner).remove(vector);

        }
        return node;
    }

    @Override
    public EdgeData removeEdge(int src, int dest) {
        return null;
    }

    @Override
    public int nodeSize() {
        return 0;
    }

    @Override
    public int edgeSize() {
        return 0;
    }

    @Override
    public int getMC() {
        return 0;
    }

    /**
     * Iterator for the edges map
     * @return
     */
    public Iterator<EdgeData> getEdgeIterator(){
        return edges.values().iterator();
    }

    /**
     * Iterator for nodes map
     * @return
     */
    public Iterator<NodeData> getNodeIterator(){
        return nodes.values().iterator();
    }
}
