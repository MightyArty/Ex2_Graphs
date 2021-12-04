package Algo;

import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.EdgeData;
import api.NodeData;
import com.google.gson.Gson;

import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class DWGraphAlgorithm implements DirectedWeightedGraphAlgorithms {

    public DWGraph myGraph;
    public boolean OK = true;

    /**
     * Inits the graph on which this set of algorithms operates on.
     *
     * @param g
     */
    @Override
    public void init(DirectedWeightedGraph g) {
        this.myGraph = (DWGraph) g;
    }

    /**
     * Empty constructor
     */
    public DWGraphAlgorithm() {
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
     * Returning the shortest path from src to dest - as list of NODES
     * example : src --> node1 --> node2 --> node3 --> ... --> dest
     *
     * @param src  - start node
     * @param dest - end (target) node
     * @return
     */
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
        Gson gson = new Gson();
        String out = gson.toJson(myGraph.getNodes());
        boolean result = false;
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(out);
            writer.flush();
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean load(String file) {
        return false;
    }

    public int DFS() {
        int count = 0;
        Iterator<NodeData> i = this.myGraph.nodeIter();
        while (i.hasNext()) {
            Node node = (Node) i;
            if (node.getCurrent() == Color.WHITE) {
                count++;
                DFS_visit((DWGraph) this.myGraph, node);
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
                ans.connect(e.getSrc(), e.getDest(), e.getWeight());
            }
        }
        return ans;
    }

}
