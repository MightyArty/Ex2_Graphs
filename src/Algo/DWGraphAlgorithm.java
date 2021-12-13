package Algo;

import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.EdgeData;
import api.NodeData;
import com.google.gson.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class DWGraphAlgorithm implements DirectedWeightedGraphAlgorithms {

    public DirectedWeightedGraph myGraph;
    private HashMap<Integer, NodeData> dNodeMap; // map that holds info of the Dijkstra algo for each node

    /**
     * Inits the graph on which this set of algorithms operates on
     * @param g
     */
    @Override
    public void init(DirectedWeightedGraph g) {
        this.myGraph = g;
        this.dNodeMap = new HashMap<>();
    }

    /**
     * Empty constructor
     */
    public DWGraphAlgorithm() {
        this.myGraph = new DWGraph();
        this.dNodeMap = new HashMap<>();
    }

    /**
     * Returns the underlying graph of which this class works.
     * @return the graph
     */
    @Override
    public DirectedWeightedGraph getGraph() {
        return this.myGraph;
    }

    /**
     * Computes a deep copy of this weighted graph.
     * @return the copied graph
     */
    @Override
    public DirectedWeightedGraph copy() {
        DirectedWeightedGraph copy = this.myGraph;
        return copy;
    }

    /**
     * Returns true if and only if (iff) there is a valid path from each node to each
     * other node. NOTE: assume directional graph (all n*(n-1) ordered pairs).
     * @return
     */
    @Override
    public boolean isConnected() {
        DirectedWeightedGraph graph = copy();
        int edgeSize = graph.edgeSize();
        int nodeSize = graph.nodeSize();

        // if the number of edges equal to number of vertex*(vertex-1) than the graph is connected
        if (edgeSize == nodeSize * (nodeSize - 1)) return true;
        DirectedWeightedGraph temp = graph;
        int key = 0;
        Iterator<NodeData> t = temp.nodeIter();

        //check which key is the first to start from
        key = t.next().getKey();
        Node node = (Node) graph.getNode(key);

        //painting the nodes to gray
        DFSConnect(temp, node);
        Iterator<NodeData> i = temp.nodeIter();

        //check if all the nodes are GRAY
        while (i.hasNext())
            //if one of the nodes is not gray, the graph is not connected
            if (i.next().getTag() != 1) return false;

        //transposing the graph
        temp = tran();
        DFSConnect(temp, node);

        //check if all the nodes are GRAY again
        i = temp.nodeIter();

        while (i.hasNext())
            //if one of the nodes is not gray, the transpose graph is not connected
            if (i.next().getTag() != 1) return false;
        return true;
    }

    /**
     * Computes the length of the shortest path between src to dest
     * Note: if no such path --> returns  -1
     *
     * @param src  - start node
     * @param dest - end (target) node
     * @return the length of the shortest path
     */
    @Override
    public double shortestPathDist(int src, int dest) {
        if (myGraph == null || myGraph.getNode(src) == null || myGraph.getNode(dest) == null) {
            return -1;
        }
        if (src == dest) {
            return 0;
        }
        DirectedWeightedGraph graph = new DWGraph((DWGraph) this.myGraph);
        NodeData curr = graph.getNode(src);
        curr.setWeight(0);
        dNodeMap.put(src, curr);

        // Looping on all the vertices
        for (Iterator<NodeData> it = graph.nodeIter(); it.hasNext(); ) {    // O(n)
            NodeData temp = it.next();
            if (temp.getKey() != src) {
                // set all the others nodes to be infinity
                graph.getNode(temp.getKey()).setWeight(Double.POSITIVE_INFINITY);
                graph.getNode(temp.getKey()).setInfo("Not visited");
                graph.getNode(temp.getKey()).setTag(-1);
            }
        }
        curr.setInfo("Not visited");
        Queue<NodeData> pq = new ArrayDeque<>();
        pq.add(curr);
        while (!pq.isEmpty()) { // O(n)
            Iterator<EdgeData> edge = graph.edgeIter(curr.getKey());
            while (edge.hasNext() && pq.peek().getInfo() != "Visited") { // O(n+number) --> O(n)
                EdgeData next = edge.next();
                if (curr.getKey() != next.getDest()) {
                    double sumWeight = next.getWeight() + graph.getNode(next.getSrc()).getWeight();
                    //check if the weight is smaller than current weight
                    if (graph.getNode(next.getDest()).getWeight() > sumWeight) {
                        //init the node weight
                        graph.getNode(next.getDest()).setWeight(sumWeight);
                        // init the node parent
                        graph.getNode(next.getDest()).setTag(next.getSrc());
                        dNodeMap.put(next.getDest(), curr);
                    }
                    NodeData t = graph.getNode(next.getDest());
                    if (t.getInfo() != "Visited")
                        pq.add(graph.getNode(next.getDest()));
                }
            }
            graph.getNode(pq.peek().getKey()).setInfo("Visited");
            pq.poll();
            curr = pq.peek();
        }
        double ans = graph.getNode(dest).getWeight();
        return ans;
    }

    /**
     * Computes the shortest path between src to dest - as an ordered List of nodes:
     * src--> n1-->n2-->...dest
     * if no such path --> returns null;
     *
     * @param src  - start node
     * @param dest - end (target) node
     * @return list of the path nodes
     */
    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        if (shortestPathDist(src, dest) == -1) return null;
        List<NodeData> ans = new ArrayList<>();
        ans.add(myGraph.getNode(dest));
        int index = dest;
        while (index != src) {
            ans.add(dNodeMap.get(index));
            index = dNodeMap.get(index).getKey();
        }
        Collections.reverse(ans);
        return ans;

    }

    /**
     * Finds the NodeData which minimizes the max distance to all the other nodes.
     * Assuming the graph isConnected, else return null.
     *
     * @return the Algo.Node data to which the max shortest path to all the other nodes is minimized.
     */
    @Override
    public NodeData center() {
        if (!isConnected())
            return null;

        int size = myGraph.nodeSize();
        double matrix[][] = new double[size][size];
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                if (i == j)
                    matrix[i][j] = 0;
                else
                    matrix[i][j] = Double.MAX_VALUE;
        Iterator<EdgeData> iter = myGraph.edgeIter();
        while (iter.hasNext()) {
            EdgeData edge = iter.next();
            matrix[edge.getSrc()][edge.getDest()] = edge.getWeight();
        }
        for (int k = 0; k < size; k++)
            for (int i = 0; i < size; i++)
                for (int j = 0; j < size; j++)
                    if (matrix[i][j] > matrix[i][k] + matrix[k][j])
                        matrix[i][j] = matrix[i][k] + matrix[k][j];

        NodeData ans = null;
        double min = Double.MAX_VALUE;

        for (int i = 0; i < size; i++) {
            double max = -1;
            for (int j = 0; j < size; j++) {
                if (matrix[i][j] > max)
                    max = matrix[i][j];
            }
            if (max == Double.MAX_VALUE) return null;
            if (min > max) {
                min = max;
                ans = myGraph.getNode(i);
            }
        }
        return ans;
    }

    /**
     * Computes a list of consecutive nodes which go over all the nodes in cities.
     * the sum of the weights of all the consecutive (pairs) of nodes (directed) is the "cost" of the solution -
     * the lower the better.
     */
    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
        if(cities.isEmpty())
            throw new RuntimeException("The list of cities should not be empty");

        List<NodeData> ans = new LinkedList<>();
        List<Integer> getCities = new ArrayList<>();

        // getting all the nodes from given list 'cities' to new list of INTEGERS
        for(NodeData node : cities){
            getCities.add(node.getKey());
        }

        // if there are only one node in the given list just return this node
        if(getCities.size() == 1) {
            ans.add(cities.get(0));
            return ans;
        }

        // first node
        int first = getCities.get(0);

        // second node
        int second = getCities.get(1);

        while (!getCities.isEmpty()){
            if(!ans.isEmpty() && ans.get(ans.size() - 1).getKey() == first){
                ans.remove(ans.size() - 1);
            }

            List<NodeData> list = shortestPath(first, second);  //O(n^2)
            List<Integer> temp = new ArrayList<>();
            // generate a list of integers from a given list of nodes
            for(NodeData n : list){
                temp.add(n.getKey());
            }
            getCities.removeAll(temp);
            ans.addAll(list);

            // move the src and the dest to the next index
            if(!getCities.isEmpty()){
                first = second;
                second = getCities.get(0);
            }

        }
        return ans;
    }

    /**
     * Saves this weighted (directed) graph to the given
     * file name - in JSON format
     *
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
        while (nodeIter.hasNext()) {
            NodeData nodeRunner = nodeIter.next();
            node = toJson(nodeRunner);
            nodes.put(node);

            Iterator<EdgeData> edgeIter = myGraph.edgeIter(nodeRunner.getKey());
            while (edgeIter.hasNext()) {
                EdgeData edgeRunner = edgeIter.next();
                edge = toJson(edgeRunner);
                edges.put(edge);
            }
        }
        try {
            graph.put("Nodes", nodes);
            graph.put("Edges", edges);
            graph.put("nodeAmount", myGraph.nodeSize());
            graph.put("edgesAmount", myGraph.edgeSize());
            graph.put("mc", myGraph.getMC());
        } catch (JSONException exception) {
            exception.printStackTrace();
        }

        try {
            FileWriter g = new FileWriter(file);
            g.write(graph.toString());
            g.flush();
            g.close();
            flag = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * JSON format save for the nodes
     * @param node the current node
     * @return json object
     */
    private JSONObject toJson(NodeData node) {
        JSONObject object = new JSONObject();
        try {
            object.put("key", node.getKey());
            object.put("info", node.getInfo());
            object.put("location", node.getLocation());
            object.put("tag", node.getTag());
            object.put("w", node.getWeight());
        } catch (JSONException exception) {
            exception.printStackTrace();
        }
        return object;
    }

    /**
     * JSON format save for the edges
     * @param edge the current edge
     * @return json object
     */
    private JSONObject toJson(EdgeData edge) {
        JSONObject object = new JSONObject();
        try {
            object.put("src", edge.getSrc());
            object.put("dest", edge.getDest());
            object.put("w", edge.getWeight());
            object.put("info", edge.getInfo());
            object.put("tag", edge.getInfo());
        } catch (JSONException exception) {
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
            for (int i = 0; i < edges.size(); i++) {
                JsonObject e = new Gson().fromJson(edges.get(i), JsonObject.class);
                String first = e.get("src").getAsString();
                String second = e.get("dest").getAsString();
                String third = e.get("w").getAsString();
                int src = Integer.parseInt(first);
                int dest = Integer.parseInt(second);
                double weight = Integer.parseInt(third);
                myGraph.connect(src, dest, weight);
            }
            for (int i = 0; i < nodes.size(); i++) {
                JsonObject n = new Gson().fromJson(nodes.get(i), JsonObject.class);
                String position = n.get("location").getAsString();
                String[] positionArr = position.split(",");
                double[] arr = new double[3];
                for (int k = 0; k < positionArr.length; k++) {
                    arr[k] = Double.parseDouble(positionArr[k]);
                }

                String id = n.get("id").getAsString();
                double x = arr[0];
                double y = arr[1];
                double z = arr[2];
                int actualID = Integer.parseInt(id);
                Node newNode = new Node(x, y, z, actualID);
                myGraph.addNode(newNode);
            }
            flag = true;
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }
        return flag;
    }

    /**
     * function to change the tag to '1' after we visit
     * the given node and the edge that need to be connected to it
     * @param g given graph
     * @param node given node
     */
    private void DFSConnect(DirectedWeightedGraph g, NodeData node) {
        node.setTag(1);
        Iterator<EdgeData> i = g.edgeIter(node.getKey());
        //running on all the edges
        while (i.hasNext()) {
            EdgeData edge = i.next();
            //check if we didn't pass this node
            NodeData v = g.getNode(edge.getDest());
            if (v.getTag() == 0) {
                DFSConnect(g, v);
            }
        }
    }

    /**
     * simple transpose function
     * takes every node in our graph and reverse the direction of the edge that
     * connected to this node
     * @return new transposed graph
     */
    private DirectedWeightedGraph tran() {
        DirectedWeightedGraph ans = new DWGraph();
        Iterator<NodeData> i = this.myGraph.nodeIter();
        //add the all nodes to the ans graph
        while (i.hasNext()) {
            ans.addNode(i.next());
        }
        i = this.myGraph.nodeIter();
        //adding the all edges that exist in myGraph
        while (i.hasNext()) {
            NodeData node = i.next();
            Iterator<EdgeData> edge = this.myGraph.edgeIter(node.getKey());
            while (edge.hasNext()) {
                EdgeData e = edge.next();
                ans.connect(e.getDest(), e.getSrc(), e.getWeight());
            }
        }
        return ans;
    }
}
