package Tests;

import org.junit.jupiter.api.Test;
import Algo.*;
import api.*;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DWGraphAlgorithmTest {
    private DirectedWeightedGraph graph;
    private DWGraphAlgorithm algo;

    DWGraphAlgorithmTest(){
        graph = Ex2.getGraph("/Users/valhalla/IdeaProjects/Ex2_Graphs/data/G1.json");
        algo = new DWGraphAlgorithm();
        algo.init(graph);


    }

    @Test
    void copy() {
        DWGraph g = new DWGraph();
        g = (DWGraph) this.algo.copy();
        assertEquals(this.graph.nodeSize(), g.nodeSize());
        assertEquals(this.graph.edgeSize(), g.edgeSize());
        assertEquals(this.graph.getNode(4).getWeight(), g.getNode(4).getWeight());
        assertEquals(this.graph.getNode(12).getWeight(), g.getNode(12).getWeight());
    }

    @Test
    void isConnected() {
        assertTrue(this.algo.isConnected());    //G1.json the graph is connected (really believe me)
    }

    @Test
    void shortestPathDist() {
        double weight1 = this.algo.getGraph().getEdge(1,2).getWeight();
        assertEquals(weight1,this.algo.shortestPathDist(1,2));
        double weight2 = this.algo.getGraph().getEdge(4,5).getWeight();
        assertEquals(weight2,this.algo.shortestPathDist(4,5));
        double weight3 = this.algo.getGraph().getEdge(15,16).getWeight();
        assertEquals(weight3,this.algo.shortestPathDist(15,16));
    }

    @Test
    void shortestPath() {
        assertEquals(1,this.algo.shortestPath(1,2).get(0).getKey());
        assertEquals(3,this.algo.shortestPath(3,6).get(0).getKey());
        assertEquals(2,this.algo.shortestPath(0,2).get(2).getKey());
    }

    @Test
    void center() {
        NodeData center = algo.getGraph().getNode(8);   //center node of G1.json (checked)
        assertEquals(8,center.getKey());
    }

    @Test
    void tsp() {
        List<NodeData> list = new LinkedList<NodeData>();
        list.add(algo.myGraph.getNode(0));
        list.add(algo.myGraph.getNode(16));
        assertEquals(2,algo.tsp(list).size());
        list.removeAll(list);
        assertTrue(list.isEmpty());
        list.add(algo.myGraph.getNode(0));
        list.add(algo.myGraph.getNode(2));
        list.add(algo.myGraph.getNode(5));
        list.add(algo.myGraph.getNode(6));
        assertEquals(5,algo.tsp(list).size());
    }
}