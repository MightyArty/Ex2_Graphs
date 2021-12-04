package Algo;

import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.NodeData;
import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class DWGraphAlgorithm implements DirectedWeightedGraphAlgorithms {

    public DWGraph myGraph;
    public boolean OK = true;

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
return false;
    }

    @Override
    public double shortestPathDist(int src, int dest) {

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
        return null;
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
        Gson gson = new Gson();
        String out = gson.toJson(myGraph.getNodes());
        boolean result = false;
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(out);
            writer.flush();
            result = true;
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean load(String file) {
        return false;
    }
}
