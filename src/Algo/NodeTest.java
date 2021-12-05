package Algo;

import api.GeoLocation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NodeTest {
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
        int tag=node.getTag();
        assertEquals(tag,node.getTag());
    }

    @Test
    void setTag() {
        node.setTag(1);
        assertEquals(1,node.getTag());
    }

    @Test
    void addFromSRC() {

    }

    @Test
    void addToDEST() {

    }

    @Test
    void getFromSRCIter() {
    }

    @Test
    void getToDESTIter() {
    }

    @Test
    void getKeys() {
    }

    @Test
    void getFromSRC() {
    }

    @Test
    void getToDEST() {
    }

    @Test
    void removeSRC() {
        Node a=this.node;
        a.removeSRC()
        assertEquals();
    }

    @Test
    void removeDEST() {
    }

    @Test
    void setLocation() {
        Location loc2 = new Location (1,1,0);
        node.setLocation(loc2);
        GeoLocation loc3 = node.getLocation();
        assertEquals(loc2.x(),loc3.x());
        assertEquals(loc2.y(),loc3.y());
        assertEquals(loc2.z(),loc3.z());
    }
}