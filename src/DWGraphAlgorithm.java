import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.EdgeData;
import api.NodeData;
import com.google.gson.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class DWGraphAlgorithm implements DirectedWeightedGraphAlgorithms {

    public DWGraph myGraph;

    /**
     * Inits the graph on which this set of algorithms operates on.
     * @param g
     */
    @Override
    public void init(DirectedWeightedGraph g) {
        this.myGraph = (DWGraph) g;
    }

    /**
     * Empty constructor
     */
    public DWGraphAlgorithm(){
        this.myGraph = new DWGraph();
    }

    @Override
    public DirectedWeightedGraph getGraph() {
        return this.myGraph;
    }

    @Override
    public DirectedWeightedGraph copy() {
        DWGraph copy = this.myGraph;
        return copy;
    }

    @Override
    public boolean isConnected() {
        if (DFS() > 1)// There is more than one component
            return false;
        else {
            int edgeSize = this.myGraph.edgeSize();
            int nodeSize = this.myGraph.nodeSize();
            if (edgeSize == nodeSize * (nodeSize - 1)) return true;
            DWGraph temp = this.myGraph;
            Node node = (Node) this.myGraph.getNode(0);
            //painting the nodes to gray
            DFSConnect(temp, node);
            Iterator<Node> i = (Iterator<Node>) temp.getNode(0);
            //check if all the nodes is GRAY
            while (i.hasNext()) {
                //if one of the nodes is not gray, the graph is not connected
                if (i.next().getCurrent() != Color.GRAY) return false;
            }
            //transposing the graph
            temp = tran();
            DFSConnect(temp,node);
            //check if all the nodes is GRAY again
            i = (Iterator<Node>) temp.getNode(0);
            while(i.hasNext())
                //if one of the nodes is not gray, the transpose graph is not connected
                if (i.next().getCurrent() != Color.GRAY) return false;
        }
        return true;
    }

    @Override
    public double shortestPathDist(int src, int dest) {

        return 0;
    }

    /**
     * Computes the shortest path between src to dest - as an ordered List of nodes:
     * src--> n1-->n2-->...dest
     * if no such path --> returns null;
     * @param src - start node
     * @param dest - end (target) node
     * @return list of the path nodes
     */
    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        return null;
    }

    /**
     * Finds the NodeData which minimizes the max distance to all the other nodes.
     * Assuming the graph isConnected, elese return null.
     * @return the Node data to which the max shortest path to all the other nodes is minimized.
     */
    @Override
    public NodeData center() {
        if (!isConnected())
            return null;
        // the first node of the graph
        Iterator<NodeData> node = myGraph.nodeIter();
        double maximumValue = Double.MAX_VALUE;
        int compare = 0;
        // go through all the nodes starting at the first
        while (node.hasNext()){
            NodeData first = node.next();
            double minimumValue = 0;
            Iterator<NodeData> current = myGraph.nodeIter();
            // this while is for check the first one with all the others' node at each run
            while (current.hasNext()){
                NodeData second = current.next();
                if (myGraph.getEdge(first.getKey(), second.getKey()) != null){
                    if (myGraph.getEdge(first.getKey(), second.getKey()).getWeight() > minimumValue)
                        minimumValue = myGraph.getEdge(first.getKey(), second.getKey()).getWeight();
                }
            }
            if(maximumValue > minimumValue){
                maximumValue = minimumValue;
                compare = first.getKey();
            }
            if(compare != 0)
                return myGraph.getNode(compare);
        }
        return null;
    }

    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
        return null;
    }

    /**
     * Saves this weighted (directed) graph to the given
     * file name - in JSON format
     * @param file - the file name (may include a relative path).
     * @return true - iff the file was successfully saved
     */
    @Override
    public boolean save(String file) {
        JSONObject graph = new JSONObject();
        JSONArray edges = new JSONArray();
        JSONObject edge;
        JSONArray nodes = new JSONArray();
        JSONObject node;

        boolean flag = false;

        // saving the nodes of out graph
        Iterator<NodeData> nodeIter = myGraph.nodeIter();
        while (nodeIter.hasNext()){
            NodeData nodeRunner = nodeIter.next();
            node = toJson(nodeRunner);
            nodes.put(node);

            Iterator<EdgeData> edgeIter = myGraph.edgeIter(nodeRunner.getKey());
            while (edgeIter.hasNext()){
                EdgeData edgeRunner = edgeIter.next();
                edgeRunner = edgeIter.next();
                edge = toJson(edgeRunner);
                edges.put(edge);
            }
        }

        try{
            graph.put("nodes", nodes);
            graph.put("edges", edges);
            graph.put("nodeAmount", myGraph.nodeSize());
            graph.put("edgesAmount", myGraph.edgeSize());
            graph.put("mc", myGraph.getMC());
        }
        catch (JSONException exception){
            exception.printStackTrace();
        }

        try{
            FileWriter g = new FileWriter(file);
            g.write(graph.toString());
            g.flush();
            g.close();
            flag = true;
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * JSON format save for the nodes
     * @param node the current node
     * @return json object
     */
    private JSONObject toJson(NodeData node){
        JSONObject object = new JSONObject();
        try {
            object.put("key",node.getKey());
            object.put("info",node.getInfo());
            object.put("location",node.getLocation());
            object.put("tag",node.getTag());
            object.put("weight",node.getWeight());
        }
        catch (JSONException exception){
            exception.printStackTrace();
        }
        return object;
    }

    /**
     * JSON format save for the edges
     * @param edge the current edge
     * @return json object
     */
    private JSONObject toJson(EdgeData edge){
        JSONObject object = new JSONObject();
        try {
            object.put("src",edge.getSrc());
            object.put("dest",edge.getDest());
            object.put("weight",edge.getWeight());
            object.put("info",edge.getInfo());
            object.put("tag",edge.getInfo());
        }
        catch (JSONException exception){
            exception.printStackTrace();
        }
        return object;
    }

    /**
     * This method loads a graph to this graph algorithm.
     * if the file was successfully loaded - the underlying graph
     * of this class will be changed (to the loaded one), in case the
     * graph was not loaded the original graph should remain "as is".
     * @param file - file name of JSON file
     * @return true - iff the graph was successfully loaded.
     */
    @Override
    public boolean load(String file) {
        boolean flag = false;
        try {
            JsonElement element = JsonParser.parseReader(new FileReader(file));
            JsonObject object = element.getAsJsonObject();
            JsonArray nodes = object.get("Nodes").getAsJsonArray();
            JsonArray edges = object.get("Edges").getAsJsonArray();
            for(int i = 0 ; i < edges.size() ; i++){
                JsonObject e = new Gson().fromJson(edges.get(i), JsonObject.class);
                String first = e.get("src").getAsString();
                String second = e.get("dest").getAsString();
                String third = e.get("w").getAsString();
                int src = Integer.parseInt(first);
                int dest = Integer.parseInt(second);
                double weight = Integer.parseInt(third);
                myGraph.connect(src,dest,weight);
            }
            for(int i = 0 ; i < nodes.size() ; i++){
                JsonObject n = new Gson().fromJson(nodes.get(i), JsonObject.class);
                String position = n.get("pos").getAsString();
                String[] positionArr = position.split(",");
                double[] arr = new double[3];
                for(int k = 0 ; k < positionArr.length ; k++){
                    arr[k] = Double.parseDouble(positionArr[k]);
                }

                String id = n.get("id").getAsString();
                double x = arr[0];
                double y = arr[1];
                double z = arr[2];
                int actualID = Integer.parseInt(id);
                Node newNode = new Node(x,y,z,actualID);
                myGraph.addNode(newNode);
            }
            flag = true;
        }
        catch (FileNotFoundException exception){
            exception.printStackTrace();
        }
        return flag;
    }

    public int DFS() {
        DWGraph temp=this.myGraph;
        int count = 0;
        Iterator<NodeData> i = temp.nodeIter();
        while (i.hasNext()) {
            Node node = (Node) i;
            if (node.getCurrent() == Color.WHITE) {
                count++;
                DFS_visit(temp, node);
            }
        }
        return count;
    }

    public void DFS_visit(DWGraph graph, Node n) {
        n.setCurrent(Color.GRAY);
        Iterator<EdgeData> i = graph.edgeIter(n.getKey());
        while (i.hasNext()) {
            EdgeData edge = i.next();
            Node v = (Node) graph.getNode(edge.getDest());
            if (v.getCurrent() == Color.WHITE) {
                DFS_visit(graph, v);
            }
        }
        n.setCurrent(Color.BLACK);
    }

    public void DFSConnect(DWGraph g, Node node) {
        node.setCurrent(Color.GRAY);
        Iterator<EdgeData> i = g.edgeIter(node.getKey());
        //running on all the edges
        while (i.hasNext()) {
            EdgeData edge = i.next();
            //check if we didn't pass this node
            if (node.getCurrent() == Color.WHITE) {
                Node v = (Node) g.getNode(edge.getDest());
                DFSConnect(g, v);
            }
        }
    }

    public DWGraph tran() {
        DWGraph ans = new DWGraph();
        Iterator<NodeData> i = this.myGraph.nodeIter();
        //add the all nodes to the ans graph
        while (i.hasNext()) {
            ans.addNode(i.next());
        }
        i = this.myGraph.nodeIter();
        //adding the all edges that exist in myGraph
        while (i.hasNext()) {
            Node node = (Node) i.next();
            Iterator<EdgeData> edge = this.myGraph.edgeIter(node.getKey());
            while (edge.hasNext()) {
                EData e = (EData) edge.next();
                ans.connect(e.getDest(), e.getSrc(), e.getWeight());
            }
        }
        return ans;
    }
}
