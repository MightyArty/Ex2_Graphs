package Algo;

import api.DirectedWeightedGraph;
import api.EdgeData;
import api.NodeData;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

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
     * Getting the data from json file
     * @param json_file
     */
    public DWGraph(String json_file) {
        nodes = new HashMap<>();
        edges = new HashMap<>();

        JSONObject json = new JSONObject();
        try {
            json = parseJSON(json_file);
        }
        catch (IOException exception){
            exception.printStackTrace();
        }
        // puling the edges from json file
        JSONArray edges = json.getJSONArray("Edges");
        for (int i = 0; i < edges.length(); i++) {
            // puling the dest
            int dest = edges.getJSONObject(i).getInt("dest");
//             puling the src
            int src = edges.getJSONObject(i).getInt("src");
            // puling the weight of the edge
            int weight = edges.getJSONObject(i).getInt("w");
            EData edge = new EData(src,dest,weight);

            // NEED TO PUSH THE DATA TO THE SRC MAP AND DEST MAP !!!
        }
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
        edges.remove(v);
        edges.put(v,edge);
        Node node = (Node) nodes.get(src);
        node.addFromSRC(dest,edge);
        node = (Node) nodes.get(dest);
        node.addToDEST(src,edge);
        this.edgeSize ++;
        this.mc ++;
    }

    @Override
    public Iterator<NodeData> nodeIter() {
        return nodes.values().iterator();
    }

    @Override
    public Iterator<EdgeData> edgeIter() {
        return edges.values().iterator();
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
            throw new RuntimeException("The graph does not contain this vertex!");
        Node vertex = (Node) nodes.remove(key);
        Iterator<EdgeData> i = vertex.getFromSRCIter();
        while (i.hasNext()){
            EdgeData runner = i.next();
            Vector<Integer> v = new Vector<>(runner.getSrc(), runner.getDest());
            edges.remove(v);
            Node src = (Node) nodes.get(runner.getSrc());
            src.removeSRC(runner.getSrc());
        }

        i = vertex.getToDESTIter();
        while (i.hasNext()){
            EdgeData runner = i.next();
            Vector<Integer> v = new Vector<>(runner.getSrc(), runner.getDest());
            edges.remove(v);
            Node dest = (Node) nodes.get(runner.getDest());
            dest.removeDEST(runner.getDest());
        }
        this.mc ++;
        return vertex;
    }

    @Override
    public EdgeData removeEdge(int src, int dest) {
        Node vertex = (Node) nodes.get(dest);
        vertex.removeSRC(src);
        vertex = (Node) nodes.get(src);
        vertex.removeDEST(dest);
        Vector <Integer> v = new Vector<>(src,dest);
        this.mc ++;
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
