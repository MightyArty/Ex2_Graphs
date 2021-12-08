import api.DirectedWeightedGraph;
import api.EdgeData;
import api.NodeData;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

public class DWGraph implements DirectedWeightedGraph {
    private HashMap<Integer, NodeData> nodes;
    private HashMap<Vector<Integer>, EdgeData> edges;   // vector --> (src,dest)
    private int edgeSize;
    private int mc;

    public DWGraph(DirectedWeightedGraph graph){
        nodes = new HashMap<>();
        edges = new HashMap<>();
    }

    /**
     * Empty Constructor
     */
    public DWGraph(){
        this.nodes = new HashMap<>();  // new node map
        this.edges = new HashMap<>();   // new edges map
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
        if(!this.nodes.containsKey(key))
            throw new RuntimeException("The key does not exist!");
        return nodes.get(key);
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
        nodes.put(n.getKey(),n);
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
        edges.remove(v);    //דריסה
        edges.put(v,edge);
        Node node = (Node) nodes.get(src);
        node.addFromSRC(dest,edge); // map from the src
        node = (Node) nodes.get(dest);
        node.addToDEST(src,edge);   // map from the dest
        this.edgeSize ++;
        this.mc ++;
    }

    @Override
    public Iterator<NodeData> nodeIter() {
        return nodes.values().iterator();
    }

    @Override
    public Iterator<EdgeData> edgeIter() {
        return this.edges.values().iterator();
    }

    /**
     * This method returns an Iterator for edges getting out of the given node (all the edges starting (source) at the given node).
     * Note: if the graph was changed since the iterator was constructed - a RuntimeException should be thrown.
     * @return Iterator<EdgeData>
     */
    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {
        Node node = (Node) nodes.get(node_id);
        return node.getToDESTIter();    // maybe need to fix
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
            throw new RuntimeException("The graph does not contain this vertex!");
        Node vertex = (Node) nodes.remove(key);
        Iterator<EdgeData> i = vertex.getFromSRCIter();
        while (i.hasNext()){
            EdgeData runner = i.next();
            Vector<Integer> v = new Vector<>(runner.getSrc(), runner.getDest());
            edges.remove(v);
            this.edgeSize --;
            Node src = (Node) nodes.get(runner.getSrc());
            src.removeSRC(runner.getSrc());
        }

        i = vertex.getToDESTIter();
        while (i.hasNext()){
            EdgeData runner = i.next();
            Vector<Integer> v = new Vector<>(runner.getSrc(), runner.getDest());
            edges.remove(v);
            this.edgeSize --;
            Node dest = (Node) nodes.get(runner.getDest());
            dest.removeDEST(runner.getDest());
        }
        this.mc ++;
        return vertex;
    }

//    @Override
    public EdgeData removeEdge(int src, int dest) {
        Node vertex = (Node) nodes.get(dest);
        vertex.removeSRC(src);
        vertex = (Node) nodes.get(src);
        vertex.removeDEST(dest);
        Vector <Integer> v = new Vector<>(src,dest);
        this.mc ++;
        this.edgeSize --;
        return edges.remove(v);
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

    private static JSONObject parseJSON(String json_file) throws JSONException, IOException{
        String out = new String(Files.readAllBytes(Paths.get(json_file)));
        return new JSONObject(out);
    }
}
