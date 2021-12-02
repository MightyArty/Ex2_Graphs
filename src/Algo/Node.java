package Algo;

import java.awt.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import api.EdgeData;
import api.GeoLocation;
import api.NodeData;

public class Node implements NodeData, Serializable {

    private static int keys = 0;
    private int key;
    private int tag;
    private double weight;
    private String info;
    private Location location;
    private Color current = Color.WHITE;    //setting the color WHITE to start with (assuming the vertex has no friends yet)
//    private HashMap<Integer, EdgeData> fromSRC; //hash map representing the start of path
//    private HashMap<Integer, EdgeData> toDEST;  //hash map representing the end of path

    /**
     * Class constructor
     * @param node
     */
    public Node(NodeData node){
        this.key = node.getKey();
        this.location = new Location(node.getLocation().x(), node.getLocation().y(), node.getLocation().z());
        this.weight = node.getWeight();
        this.tag = 0;
        this.info = node.getInfo();
    }

    /**
     * Constructor for given key and location
     * @param key
     * @param loc
     */
    public Node(int key, String loc) {
        this.key = key;
        this.weight = 0;
        this.info = "";
        this.tag = 0;

        String[] locArr = loc.split(",");
        double x = Double.parseDouble(locArr[0]); // x coordinate
        double y = Double.parseDouble(locArr[1]); // y coordinate
        double z = Double.parseDouble(locArr[2]); // z coordinate

        this.location = new Location(x,y,z);
    }

    @Override
    public int getKey() {
        return this.key;
    }

    @Override
    public GeoLocation getLocation() {
        return this.location;
    }

    @Override
    public double getWeight() {
        return this.weight;
    }

    @Override
    public void setWeight(double w) {
        this.weight = w;
    }

    @Override
    public String getInfo() {
        return this.info;
    }

    @Override
    public void setInfo(String s) {
        this.info = s;
    }

    @Override
    public int getTag() {
        return this.tag;
    }

    @Override
    public void setTag(int t) {
        this.tag = t;
    }

    /**
     * Setting the new location
     * @param p - new new location  (position) of this node.
     */
    @Override
    public void setLocation(GeoLocation p) {
        if(p == null)
            this.location = new Location();
        else this.location = new Location(p);
    }
}
