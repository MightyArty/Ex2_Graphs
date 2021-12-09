package Algo;

import api.DirectedWeightedGraph;
import api.EdgeData;
import api.NodeData;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DWGraphTest {
    HashMap<Integer,NodeData> Nodes = new HashMap<Integer, NodeData>();
    DWGraphAlgorithm graphAlgoTest = new DWGraphAlgorithm();
    DWGraph graphTest = new DWGraph();

    String first = "0,0,0";
    String second = "0,1,0";
    String third = "1,1,0";
    Node one =new Node(0,first);
    Node two =new Node(1,second);
    Node three =new Node(2,third);

    @Test
    void getNode() {
        DWGraph graph = new DWGraph();
        String location = ("1,1,1");
        NodeData node = new Node(1,location);
        graph.addNode(node);
        assertEquals(node,graph.getNode(1));
        }

    @Test
    void getEdge() {
        graphTest.addNode(one);
        graphTest.addNode(two);
        EData x = new EData(0,1,8);
        graphTest.connect(0,1,8);
        assertEquals(graphTest.getEdge(0,1).getDest(),x.getDest());
        assertEquals(graphTest.getEdge(0,1).getSrc(),x.getSrc());
        assertEquals(graphTest.getEdge(0,1).getWeight(),x.getWeight());
    }



    @Test
    void addNode() {
        graphTest.addNode(one);
        graphTest.addNode(two);
        assertEquals(graphTest.getNode(0),one);
        assertEquals(graphTest.getNode(1),two);
    }

    @Test
    void connect() { // need to fix

        DirectedWeightedGraph testNEW = new DWGraph();
        testNEW.addNode(one);
        testNEW.addNode(two);
        testNEW.connect(one.getKey(), two.getKey(), 5);
        assertEquals(false, graphAlgoTest.isConnected());



    }

    @Test
    void nodeIter() { // V
        try{
            graphTest.addNode(one);
            graphTest.addNode(two);
            Iterator<NodeData> it = graphTest.nodeIter();
            int nexter=0;

            while(it.hasNext()) {
                it.next();
                nexter++;
            }
            assertEquals(2,nexter);
        }
        catch (RuntimeException e ){
            assertEquals(RuntimeException.class,e.getClass());
        }
    }

    @Test
    void edgeIter() { // V
        try{
            graphTest.addNode(one);
            graphTest.addNode(two);
            graphTest.addNode(three);
            graphTest.connect(0,1,0);
            graphTest.connect(0,2,2);
            Iterator<EdgeData> it = graphTest.edgeIter(0);
            int nexter=0;
            while(it.hasNext()) {
                it.next();
                nexter++;
            }
            assertEquals(2,nexter);
            graphTest.connect(0,1,0);
        }
        catch (RuntimeException e ){
            assertEquals(RuntimeException.class,e.getClass());
        }
    }


    @Test
    void removeNode() {  //need to be fixed
//        NodeData vertex = new Node(one);
        graphTest.addNode(one);
        graphTest.addNode(two);
        graphTest.addNode(three);
//        System.out.println(graphTest.getNodes());
//        graphTest.connect(0,1,0);
//        graphTest.connect(2,0,0);
        graphTest.removeNode(one.getKey());
        System.out.println(graphTest.getNodes());
//        assertEquals(graphTest.getNodes(),2);
//        assertEquals(graphTest.nodeSize(),2);
//        assertEquals(graphTest.edgeSize(),0);
//        assertNull(graphTest.removeNode(0),"The graph does not contain this vertex!");
    }

    @Test
    void removeEdge() { // V

        DirectedWeightedGraph testNEW = new DWGraph();
        testNEW.addNode(one);
        testNEW.addNode(two);
        testNEW.connect(one.getKey(),two.getKey(),5);
        EdgeData a = testNEW.removeEdge(one.getKey(),two.getKey());
        EdgeData acc = new EData(one.getKey(), two.getKey(), 5);
        assertEquals(acc.toString(),a.toString());

    }

    @Test
    void nodeSize() { // V
        graphTest.addNode(one);
        graphTest.addNode(two);
        graphTest.addNode(three);
        assertEquals(graphTest.nodeSize(),3);
    }

    @Test
    void edgeSize() { // V
        graphTest.addNode(one);
        graphTest.addNode(two);
        graphTest.addNode(three);
        graphTest.connect(0,1,0);
        graphTest.connect(2,0,0);
        assertEquals(graphTest.edgeSize(),2);
    }

    @Test
    void getMC() { // need to update
        graphTest.addNode(one);
        graphTest.addNode(two);
        graphTest.addNode(three);
        graphTest.connect(0,2,5);
        graphTest.connect(0,1,2);
        graphTest.removeEdge(0,2);
        graphTest.removeNode(0);
        assertEquals(graphTest.getMC(),7);
    }
}