package Tests;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import Algo.*;


class EDataTest {
    EData test = new EData(5, 15, 20);

    @Test
    void getSrc() {
        assertEquals(5, test.getSrc());
    }

    @Test
    void getDest() {
        assertEquals(15, test.getDest());
    }

    @Test
    void getWeight() {
        assertEquals(20, test.getWeight());
    }

    @Test
    void getInfo() {
        test.setInfo("");
        assertTrue(test.getInfo().equals(""));
    }

    @Test
    void testToString() {
        int Src = 5;
        int Dest = 15;
        double Weight = 20;
        String info = null;
        int tag = -1000000000;
        String expected = "EData{" + "source=" + Src + ",destination=" + Dest + ",weight=" + Weight + ",info=" + info + "and the tag=" + tag + "}";

        assertEquals(expected, test.toString());
    }

    @Test
    void setInfo() {
        test.setInfo("test");
        assertTrue(test.getInfo().equals("test"));
    }

    @Test
    void getTag() {
        int testTag = -1000000000;
        assertEquals(testTag, test.getTag());
    }

    @Test
    void setTag() {
        test.setTag(15);
        assertEquals(15, test.getTag());
    }
}