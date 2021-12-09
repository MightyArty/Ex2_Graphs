package Algo;

import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.NodeData;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class DWGraphAlgorithmTest {
    String first = "1,1,0";
    String second = "2,2,0";
    String third = "3,3,0";
    Node one =new Node(0,first);
    Node two =new Node(1,second);
    Node three =new Node(2,third);
    DWGraphAlgorithm test = new DWGraphAlgorithm();

    @Test
    void init() {
        NodeData node = new Node(0,"1,2,3");
        HashMap<Integer, NodeData> dNodeMapTest = new HashMap();
        dNodeMapTest.put(node.getKey(),node);
        DirectedWeightedGraph graph = new DWGraph();
        DirectedWeightedGraphAlgorithms myGraphTest =  new DWGraphAlgorithm();
        myGraphTest.init(graph);
        assertEquals(graph.getNode(0).toString(),"id=0,location=1,2,3 ");
    }

    @Test
    void getGraph() {
    }

    @Test
    void copy() {
        DWGraphAlgorithm graph = new DWGraphAlgorithm();
        boolean test = graph.load("src/G1.json");
        DirectedWeightedGraph g = graph.copy();

        assertEquals(graph.getGraph().getNode(0).toString(),graph.myGraph.getNode(0).toString());
        assertEquals(graph.getGraph().getNode(1).toString(),graph.myGraph.getNode(1).toString());
        assertEquals(graph.getGraph().getNode(2).toString(),graph.myGraph.getNode(2).toString());
    }

    @Test
    void isConnected() {
        DirectedWeightedGraphAlgorithms a = new DWGraphAlgorithm();
        DirectedWeightedGraph g1 = new DWGraph();
        NodeData n1 = new Node(1,first);
        NodeData n2 = new Node(2,second);
        NodeData n3 = new Node(3,third);
        g1.addNode(n1);
        g1.addNode(n2);
        g1.addNode(n3);
        g1.connect(n1.getKey(),n2.getKey(),1.2);
        g1.connect(n2.getKey(), n3.getKey(), 3.4);
        DirectedWeightedGraph g2 = new DWGraph((DWGraph) g1);
        a.init(g1);
        assertFalse(a.isConnected());
        a.init(g2);
        a.getGraph().connect(n3.getKey(), n1.getKey(), 3);
        assertTrue(a.isConnected());
        test.load("/Users/david/IdeaProjects/Ex2_Graphs-new/data/G2.json");
        assertTrue(test.isConnected());

    }

    @Test
    void shortestPathDist() {
    }

    @Test
    void shortestPath() {
    }

    @Test
    void center() {
    }

    @Test
    void tsp() {
    }

    @Test
    void save() {
    }


    @Test
    void load() {
        DWGraphAlgorithm graphTest = new DWGraphAlgorithm();
        boolean test =graphTest.load("src/G1.json");
        DWGraphAlgorithm gg = (DWGraphAlgorithm) graphTest.getGraph();

        assertEquals(gg.myGraph.getNode(0).toString(),"35.19589389346247,32.10152879327731,0.0");
        assertEquals(gg.myGraph.getNode(5).toString(),"pos=35.212111165456015,32.106235628571426,0.0, id=5");
    }
}