package Algo;

import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.NodeData;

import java.util.List;

public class DWGraphAlgorithm implements DirectedWeightedGraphAlgorithms {

    public DirectedWeightedGraph myGraph;
    public boolean OK = true;

    /**
     * Constructor
     * @param graph
     */
    public DWGraphAlgorithm(DirectedWeightedGraph graph){
        this.myGraph = graph;
    }

    /**
     * Empty Constructor
     */
    public DWGraphAlgorithm(){}

    @Override
    public void init(DirectedWeightedGraph g) {
        this.myGraph = new DWGraph(g);
    }

    @Override
    public DirectedWeightedGraph getGraph() {
        return null;
    }

    @Override
    public DirectedWeightedGraph copy() {
        return null;
    }

    @Override
    public boolean isConnected() {
        return false;
    }

    @Override
    public double shortestPathDist(int src, int dest) {
        return 0;
    }

    @Override
    public List<NodeData> shortestPath(int src, int dest) {
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
        return false;
    }

    @Override
    public boolean load(String file) {
        return false;
    }
}
