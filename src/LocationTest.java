import api.GeoLocation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LocationTest {
    final double EPSILON = 0.0000001;
    double t =5.0;
    GeoLocation loc1 = new Location(0, 0, 0);


    @Test
    void distance() {
        GeoLocation loc1 = new Location(0, 0, 0);
        GeoLocation loc2 = new Location(1, 1, 1);
        double dis = loc1.distance(loc2);
        assertEquals(dis, 1.7320508075688772, EPSILON);
        loc1 = new Location(1, 2, 3);
        loc2 = new Location(-2, 4.7, 7);
        dis = loc1.distance(loc2);
        assertEquals(dis, 5.682429, EPSILON);
    }

    @Test
    void setX() {
        Location loc3 = new Location(1,2,0);
        loc3.setX(5);
        assertEquals(5,loc3.x());

    }

    @Test
    void setY() {
        Location loc3 = new Location(1,2,0);
        loc3.setY(6);
        assertEquals(6,loc3.y());
    }

    @Test
    void setZ() {
        Location loc3 = new Location(1,2,0);
        loc3.setZ(10);
        assertEquals(10,loc3.z());
    }

    @Test
    void testToString() {
        double x=0,y=0,z=0;
        String expected = "Location (" + "x=" + x + ",y=" + y + ",z=" + z + ")" ;
        assertEquals(expected,loc1.toString());
    }
}