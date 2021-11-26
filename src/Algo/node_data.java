package Algo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;

import api.EdgeData;
import api.GeoLocation;
import api.NodeData;


public class node_data implements NodeData, Serializable {

    private static int nodeID = 0;
    private int id;
    private GeoLocation locateNode = null;
    private double nodeWeight = 0;
    private String nodeInfo = "";
    private int nodeTag = -1;
    private HashMap<Integer, EdgeData> Map = new LinkedHashMap<>();
    private static double maxX = 10, maxY = 10, minY = -10, minX = -10;

            //////// Constructor ////////
    public node_data(GeoLocation location){
        maxX = Math.max(location.x(), maxX);
        minX = Math.min(location.y(), minX);
        maxY = Math.max(location.y(), maxY);
        minY = Math.min(location.y(), minY);
        this.id = nodeID;
        nodeID++;
        locateNode = location;
    }

    public node_data(int id,GeoLocation location) {
        maxX = Math.max(location.x(), maxX);
        maxY = Math.max(location.y(), maxY);
        minX = Math.min(location.x(), minX);
        minY = Math.min(location.y(), minY);
        this.id = id;
        if (nodeID <= this.getKey())
            nodeID = this.getKey() + 1;
        locateNode = location;
    }

    @Override
    public int getKey() {
        return this.id;
    }

    @Override
    public GeoLocation getLocation() {
        return this.locateNode;
    }

    @Override
    public double getWeight() {
        return 0;
    }

    @Override
    public void setWeight(double w) {

    }

    @Override
    public String getInfo() {
        return null;
    }

    @Override
    public void setInfo(String s) {

    }

    @Override
    public int getTag() {
        return 0;
    }

    @Override
    public void setTag(int t) {

    }

    @Override
    public void setLocation(GeoLocation p) {

    }
}
