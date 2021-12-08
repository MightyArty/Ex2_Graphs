import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.EdgeData;
import api.NodeData;
import com.google.gson.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class DWGraphAlgorithm implements DirectedWeightedGraphAlgorithms {

    public DirectedWeightedGraph myGraph;
    private HashMap<Integer, NodeData> dNodeMap; // map that holds info of the Dijkstra algo for each node

    /**
     * Inits the graph on which this set of algorithms operates on.
     *
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

    @Override
    public DirectedWeightedGraph getGraph() {
        return this.myGraph;
    }

    @Override
    public DirectedWeightedGraph copy() {
        DirectedWeightedGraph copy = this.myGraph;
        return copy;
    }

    @Override
    public boolean isConnected() {
//        if (DFS() > 1) {// There is more than one component
//            System.out.println(DFS());
//            return false;
//        } else {
            int edgeSize = this.myGraph.edgeSize();
            int nodeSize = this.myGraph.nodeSize();
            // if the number of edges equal to number of vertex*(vertex-1) than the graph is connected
            if (edgeSize == nodeSize * (nodeSize - 1)) return true;
            DirectedWeightedGraph temp = this.myGraph;
            int key = 0;
            Iterator<NodeData> t = temp.nodeIter();
            //check which key is the first to start from
            key = t.next().getKey();
            Node node = (Node) this.myGraph.getNode(key);
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

        return shortAssist(src, dest);
    }

    /**
     * Dijkstra algo for finding the shortest path
     *
     * @param src  the source node of the graph --> at each iteration
     * @param dest the destination node of the graph --> at each iteration
     * @return
     */
    private double shortAssist(int src, int dest) {
        DWGraph graph = (DWGraph) myGraph;
        NodeData curr =graph.getNode(src);
        dNodeMap.put(src, curr);

        // Looping on all the vertices
        for (Iterator<NodeData> it = graph.nodeIter(); it.hasNext(); ) {
            NodeData temp =  it.next();
            if (temp.getKey() != src) {
                // set all the others nodes to be infinity
                temp.setWeight(Double.POSITIVE_INFINITY);
                temp.setTag(-1);
                temp.setInfo("Not visited");
            }
        }
        Queue<NodeData> pq = new ArrayDeque<>();
        pq.add(curr);
        while (!pq.isEmpty()) {
            Iterator<EdgeData> edge = graph.edgeIter(curr.getKey());
            while (edge.hasNext() && pq.peek().getInfo() != "Visited") { // new condition
                EdgeData next = edge.next();
                if (curr.getKey() != next.getDest()) {
                    double sumWeight = next.getWeight() + graph.getNode(next.getSrc()).getWeight();
                    //check if the weight is smaller than current weight
                    if (graph.getNode(next.getDest()).getWeight() > sumWeight) {
                        //init the node weight
                        graph.getNode(next.getDest()).setWeight(sumWeight);
                        //dNodeMap.get(next.getSrc()).setWeight(sumWeight);
                        // init the node parent
                        graph.getNode(next.getDest()).setTag(next.getSrc());
                        dNodeMap.get(next.getSrc()).setTag(next.getSrc());
                        dNodeMap.put(next.getDest(), curr);
                    }
                    NodeData t = graph.getNode(next.getDest());
                    if(t.getInfo()!="Visited")
                    pq.add(graph.getNode(next.getDest()));
                }
            }
            graph.getNode(pq.peek().getKey()).setInfo("Visited");
            pq.poll();
            curr = pq.peek();
        }
       // System.out.println(this.dNodeMap);
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
        if(shortestPathDist(src, dest)==-1)return null;
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
     * @return the Node data to which the max shortest path to all the other nodes is minimized.
     */
    @Override
    public NodeData center() {
        if (!isConnected())
            return null;
        // the first node of the graph
        Iterator<NodeData> node = myGraph.nodeIter();
        double minimum = Double.MAX_VALUE;
        int compare = -1;
        // go through all the nodes starting at the first
        while (node.hasNext()) {
            NodeData first = node.next();
            double maximum = 0;
            //   Iterator<NodeData> current = myGraph.nodeIter();
            // this while is for check the first one with all the others' node at each run
            while (node.hasNext()) {
                NodeData second = node.next();
                // maybe need to compare the first to the second or delete ?
                if (myGraph.getEdge(first.getKey(), second.getKey()) != null && first.getKey() != second.getKey()) {
                    if (myGraph.getEdge(first.getKey(), second.getKey()).getWeight() > maximum)
                        maximum = myGraph.getEdge(first.getKey(), second.getKey()).getWeight();
                }
            }
            if (minimum > maximum) {
                minimum = maximum;
                compare = first.getKey();
            }
            if (compare >= 0)
                return myGraph.getNode(compare);
        }
        return null;
    }

    /**
     * Computes a list of consecutive nodes which go over all the nodes in cities.
     * the sum of the weights of all the consecutive (pairs) of nodes (directed) is the "cost" of the solution -
     * the lower the better.
     */
    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
        DWGraphAlgorithm algo = new DWGraphAlgorithm();
        // init the graph with data from cities list
        for (int i = 0; i < cities.size(); i++) {
            int index = cities.get(i).getKey();
            algo.getGraph().addNode(cities.get(index));
        }

        Iterator<EdgeData> iterator = algo.getGraph().edgeIter();
        while (iterator.hasNext()) {
            int counter = 0;
            int index = 0;
            EdgeData runner = iterator.next();
            int src = runner.getSrc();
            int dest = runner.getDest();
            double weight = runner.getWeight();
            List<NodeData> temp = cities;

            while (!temp.isEmpty()) {
                int key = temp.get(index).getKey();
                if (key == src || key == dest) {
                    counter++;
                }
                temp.remove(index);
                index++;
            }
            if (counter == 2)
                algo.getGraph().connect(src, dest, weight);
        }

        //check if the graph is connected
        if (!algo.isConnected()) return null;
        List<NodeData> ans = new LinkedList<>();
        //if cities size is one
        if (cities.size() == 1) {
            ans.add(cities.get(0));
            return ans;
        }
        double min = Integer.MAX_VALUE;
        int size = 0;
        int index = 0;
        //running all over the nodes in the algo.graph(cities nodes)
        while (cities.size() > size) {
            int curr = cities.get(index).getKey();
            //running in circle between the cities and checking which is the best travel
            List<NodeData> temp = shortestPath(curr, curr);
            double max = this.dNodeMap.get(curr).getWeight(); // the weight of the travel
            if (min > max) {
                min = max;
                ans = temp;
            }
            index++;
            size++;
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
                edgeRunner = edgeIter.next();
                edge = toJson(edgeRunner);
                edges.put(edge);
            }
        }

        try {
            graph.put("nodes", nodes);
            graph.put("edges", edges);
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
     *
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
            object.put("weight", node.getWeight());
        } catch (JSONException exception) {
            exception.printStackTrace();
        }
        return object;
    }

    /**
     * JSON format save for the edges
     *
     * @param edge the current edge
     * @return json object
     */
    private JSONObject toJson(EdgeData edge) {
        JSONObject object = new JSONObject();
        try {
            object.put("src", edge.getSrc());
            object.put("dest", edge.getDest());
            object.put("weight", edge.getWeight());
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
     *
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
                String position = n.get("pos").getAsString();
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

//    private int DFS() {
//        DirectedWeightedGraph temp = this.myGraph;
//        int count = 0;
//        Iterator<NodeData> i = temp.nodeIter();
//        while (i.hasNext()) {
//            NodeData node = i.next();
//            if (node.getTag() == 0) {
//                count++;
//                DFS_visit(temp, node);
//            }
//        }
//        return count;
//    }
//
//    private void DFS_visit(DirectedWeightedGraph graph, NodeData n) {
//        n.setTag(1);
//        Iterator<EdgeData> i = graph.edgeIter(n.getKey());
//        while (i.hasNext()) {
//            EdgeData edge = i.next();
//            NodeData v = graph.getNode(edge.getDest());
//            if (v.getTag() == 0) {
//                DFS_visit(graph, v);
//            }
//        }
//        n.setTag(2);
//    }

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
            Node node = (Node) i.next();
            Iterator<EdgeData> edge = this.myGraph.edgeIter(node.getKey());
            while (edge.hasNext()) {
                EdgeData e = edge.next();
                ans.connect(e.getDest(), e.getSrc(), e.getWeight());
            }
        }
        return ans;
    }

}

