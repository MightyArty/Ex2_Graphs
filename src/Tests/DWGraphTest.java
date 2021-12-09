package Tests;

import org.junit.jupiter.api.Test;
import Algo.*;
import api.*;

import java.util.List;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class DWGraphTest {
    DWGraph graphTest = new DWGraph();

    String first = "0,0,0";
    String second = "0,1,0";
    String third = "1,1,0";
    Node one = new Node(0, first);
    Node two = new Node(1, second);
    Node three = new Node(2, third);

    @Test
    void getNode() {
        DWGraph graph = new DWGraph();
        String location = ("1,1,1");
        NodeData node = new Node(1, location);
        graph.addNode(node);
        assertEquals(node, graph.getNode(1));
    }

    @Test
    void getEdge() {
        graphTest.addNode(one);
        graphTest.addNode(two);
        EData x = new EData(0, 1, 8);
        graphTest.connect(0, 1, 8);
        assertEquals(graphTest.getEdge(0, 1).getDest(), x.getDest());
        assertEquals(graphTest.getEdge(0, 1).getSrc(), x.getSrc());
        assertEquals(graphTest.getEdge(0, 1).getWeight(), x.getWeight());
    }

    @Test
    void addNode() {
        graphTest.addNode(one);
        graphTest.addNode(two);
        assertEquals(graphTest.getNode(0), one);
        assertEquals(graphTest.getNode(1), two);
    }

    @Test
    void connect() {
        DirectedWeightedGraph testNEW = new DWGraph();
        testNEW.addNode(one);
        testNEW.addNode(two);
        testNEW.connect(one.getKey(), two.getKey(), 5);
        EdgeData e = new EData(one.getKey(), two.getKey(), 5);
        assertEquals(e.toString(), testNEW.getEdge(one.getKey(), two.getKey()).toString());
    }

    @Test
    void nodeIter() {
        graphTest.addNode(one);
        graphTest.addNode(two);
        Iterator<NodeData> it = graphTest.nodeIter();
        List<NodeData> e = new LinkedList<>();
        e.add(one);
        e.add(two);
        int i = 0;
        while (it.hasNext()) {
            assertEquals(e.get(i++).toString(), it.next().toString());
        }
    }

    @Test
    void edgeIter() {
        EdgeData edge = new EData(0,1,5);
        graphTest.addNode(one);
        graphTest.addNode(two);
        graphTest.connect(one.getKey(),two.getKey(),5);
        Iterator<EdgeData> it = graphTest.edgeIter();
        List<EdgeData> e = new LinkedList<>();
        e.add(edge);
        assertEquals(e.get(0).toString(), it.next().toString());
    }

    @Test
    void removeNode() {
        graphTest.addNode(one);
        graphTest.addNode(two);
        graphTest.addNode(three);
        graphTest.connect(0, 1, 0);
        graphTest.connect(2, 0, 0);
        NodeData e = graphTest.getNode(one.getKey());
        NodeData actual = graphTest.removeNode(one.getKey());
        assertEquals(e, actual);
    }

    @Test
    void removeEdge() {
        DirectedWeightedGraph testNEW = new DWGraph();
        testNEW.addNode(one);
        testNEW.addNode(two);
        testNEW.connect(one.getKey(), two.getKey(), 5);
        EdgeData a = testNEW.removeEdge(one.getKey(), two.getKey());
        EdgeData acc = new EData(one.getKey(), two.getKey(), 5);
        assertEquals(acc.toString(), a.toString());
    }

    @Test
    void nodeSize() {
        graphTest.addNode(one);
        graphTest.addNode(two);
        graphTest.addNode(three);
        assertEquals(graphTest.nodeSize(), 3);
    }

    @Test
    void edgeSize() {
        graphTest.addNode(one);
        graphTest.addNode(two);
        graphTest.addNode(three);
        graphTest.connect(0, 1, 0);
        graphTest.connect(2, 0, 0);
        assertEquals(graphTest.edgeSize(), 2);
    }

    @Test
    void getMC() {
        graphTest.addNode(one);
        graphTest.addNode(two);
        graphTest.addNode(three);
        graphTest.connect(0, 2, 5);
        graphTest.connect(0, 1, 2);
        graphTest.removeEdge(0, 2);
        assertEquals(graphTest.getMC(), 6);
    }
}