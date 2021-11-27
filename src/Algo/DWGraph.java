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
     * Copy Constructor
     */
    public DWGraph(){
        this.graph = new HashMap<>();
        this.edges = new HashMap<>();
        this.againstDirection = new HashMap<>();
        this.edgeSize = 0;
        this.mc = 0;
    }

    @Override
    public NodeData getNode(int key) {
        if(this.graph.containsKey(key))
            return this.graph.get(key);
        return null;
    }

    @Override
    public EdgeData getEdge(int src, int dest) {
        return null;
    }

    @Override
    public void addNode(NodeData n) {

    }

    @Override
    public void connect(int src, int dest, double w) {

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

    @Override
    public NodeData removeNode(int key) {
        return null;
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
}
