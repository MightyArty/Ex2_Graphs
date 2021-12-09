import api.GeoLocation;

public class Location implements GeoLocation {

    /**
     * This class represents a geo location <x,y,z>, (aka Point3D data)
     */
    private double x,y,z;

    /**
     * Constructor
     * @param x (x,0,0)
     * @param y (0,y,0)
     * @param z (0,0,z)
     */
    public Location(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Empty Constructor
     */
    public Location(){
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    /**
     * Copy Constructor
     * @param other
     */
    public Location(GeoLocation other){
        this.x = other.x();
        this.y = other.y();
        this.z = other.z();
    }

    @Override
    public double x() {
        return this.x;
    }

    @Override
    public double y() {
        return this.y;
    }

    @Override
    public double z() {
        return this.z;
    }

    @Override
    public double distance(GeoLocation g) {
        return Math.sqrt(Math.pow(this.x - g.x(), 2) + Math.pow(this.y - g.y(), 2) + Math.pow(this.z - g.z(), 2));
    }

    public String toString(){
        return "Location (" + "x=" + this.x + ",y=" + this.y + ",z=" + this.z + ")";
    }
}
