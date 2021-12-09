package Tests;
import org.junit.jupiter.api.Test;
import Algo.*;
import api.*;



import static org.junit.jupiter.api.Assertions.*;

class NodeTest {
    int tag=0;
    double weight=0;
    String info = "";
    String loc = "0,0,0";
    Node node = new Node(1,loc);

    @Test
    void getKey() {
        int a = node.getKey();
        assertEquals(1, a);
    }

    @Test
    void getLocation() {
        GeoLocation loc1 = node.getLocation();
        assertEquals(loc1.x(), node.getLocation().x());
        assertEquals(loc1.y(),node.getLocation().y());
        assertEquals(loc1.z(), node.getLocation().z());
    }

    @Test
    void getWeight() {
        double weight = node.getWeight();
        assertEquals(0, weight);
    }

    @Test
    void setWeight() {
        node.setWeight(1.0);
        assertEquals(1,node.getWeight());
    }

    @Test
    void getInfo() {
        String s = node.getInfo();
        assertTrue(s.equals(""));
    }

    @Test
    void setInfo() {
        node.setInfo("test");
        assertTrue(node.getInfo().equals("test"));
    }

    @Test
    void getTag() {
        int e = 0;
        assertEquals(e,node.getTag());
    }

    @Test
    void setTag() {
        node.setTag(1);
        assertEquals(1,node.getTag());
    }

    @Test
    void setLocation() {
        GeoLocation loc2 = new Location (1,1,0);
        node.setLocation(loc2);
        GeoLocation loc3 = node.getLocation();
        assertEquals(loc2.x(),loc3.x());
        assertEquals(loc2.y(),loc3.y());
        assertEquals(loc2.z(),loc3.z());
    }

    @Test
    void testToString() {

        String expected =  "Node{" + "key= " + this.node.getKey() + ",location= " + this.node.getLocation() + ", tag= " + tag + ", weight= " + weight + ", info= " + info + "}";
        assertEquals(expected,node.toString());
    }
}