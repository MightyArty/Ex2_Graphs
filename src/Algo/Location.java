package Algo;

import api.GeoLocation;

public class Location implements GeoLocation {

    private double x,y,z;

    /**
     * Constructor
     * @param x
     * @param y
     * @param z
     */
    public Location(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Copy constructor
     */
    public Location(){
        this.x = 0;
        this.y = 0;
        this.z = 0;
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

    public void setX(double x){
        this.x = x;
    }

    public void setY(double y){
        this.y = y;
    }

    public void setZ(double z){
        this.z = z;
    }

    public String toString(){
        return "Location (" + "x=" + this.x + ",y=" + this.y + ",z=" + this.z + ")";
    }
}
