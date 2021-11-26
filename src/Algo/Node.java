package Algo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;

import api.EdgeData;
import api.GeoLocation;
import api.NodeData;

public class Node implements NodeData, Serializable {

    private static int nodeID = 0;
    private int id;
    private Location locateNode;
    private double nodeWeight = 0;
    private String nodeInfo = "";
    private int nodeTag = -1;
    private HashMap<Integer, EdgeData> Map = new LinkedHashMap<>();
    private static double maxX = 10, maxY = 10, minY = -10, minX = -10;

    /**
     * Constructor only for given location
     * @param location
     */
    public Node(Location location){
        maxX = Math.max(location.x(), maxX);
        minX = Math.min(location.y(), minX);
        maxY = Math.max(location.y(), maxY);
        minY = Math.min(location.y(), minY);
        this.id = nodeID;
        nodeID++;
        locateNode = location;
    }

    /**
     * Constructor for id and given location
     * @param id
     * @param location
     */
    public Node(int id, Location location) {
        maxX = Math.max(location.x(), maxX);
        maxY = Math.max(location.y(), maxY);
        minX = Math.min(location.x(), minX);
        minY = Math.min(location.y(), minY);
        this.id = id;
        if (nodeID <= this.getKey())
            nodeID = this.getKey() + 1;
        locateNode = location;
    }

    /**
     * Empty Constructor
     */
    public Node(){
        this.locateNode = new Location();
        this.nodeTag = 0;
        this.nodeInfo = "";
        this.nodeID = 0;
    }

    /**
     * Copy Constructor
     * @param other
     */
    public Node(NodeData other){
        this.id = other.getKey();
        if (nodeID <= other.getKey()){
            nodeID = other.getKey() + 1;
        }
        locateNode = new Location(other.getLocation().x(), other.getLocation().y(), other.getLocation().z());
        maxX = Math.max(locateNode.x(), maxX);
        minX = Math.min(locateNode.x(), minX);
        maxY = Math.max(locateNode.y(), maxY);
        minY = Math.min(locateNode.y(), minY);
        nodeWeight = other.getWeight();
        nodeInfo = other.getInfo();
        nodeTag = other.getTag();
    }

    public static double getMaxX() {
        return maxX;
    }

    public static double getMaxY() {
        return maxY;
    }

    public static double getMinY() {
        return minY;
    }

    public static double getMinX() {
        return minX;
    }

    public static int getNodeID() {
        return nodeID;
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
        return this.nodeWeight;
    }

    @Override
    public void setWeight(double w) {
        this.nodeWeight = w;
    }

    @Override
    public String getInfo() {
        return nodeInfo;
    }

    @Override
    public void setInfo(String s) {
        this.nodeInfo = s;
    }

    @Override
    public int getTag() {
        return this.nodeTag;
    }

    @Override
    public void setTag(int t) {
        this.nodeTag = t;
    }

    public static int setNodeID(int nodeID){
        return nodeID;
    }

    public String toString(){
        return "Node (" + "location=" + locateNode + ",nodeID=" + nodeID + ",nodeTag=" + nodeTag + ",nodeInfo" + nodeInfo + ")";
    }

    /**
     * Setting the new location
     * @param p - new new location  (position) of this node.
     */
    @Override
    public void setLocation(GeoLocation p) {
        this.locateNode.setY(p.y());
        this.locateNode.setX(p.x());
        this.locateNode.setZ(p.z());
    }
}
