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
    private HashMap<Integer, EdgeData> fromSRC; //hash map representing the start of path
    private HashMap<Integer, EdgeData> toDEST;  //hash map representing the end of path

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
        this.fromSRC = new HashMap<>();
        this.toDEST = new HashMap<>();
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
        this.fromSRC = new HashMap<>();
        this.toDEST = new HashMap<>();

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
     * adding the src info to the map
     * @param key
     * @param edge
     */
    public void addFromSRC(int key, EdgeData edge){
        fromSRC.put(key,edge);
    }

    /**
     * adding the dest info to the map
     * @param key
     * @param edge
     */
    public void addToDEST(int key, EdgeData edge){
        toDEST.put(key,edge);
    }

    /**
     * Iterator for fromSRC map
     * @return
     */
    public Iterator<EdgeData> getFromSRCIter(){
        return fromSRC.values().iterator();
    }

    /**
     * Iterator for toDEST map
     * @return
     */
    public Iterator<EdgeData> getToDESTIter(){
        return toDEST.values().iterator();
    }

    public static int getKeys() {
        return keys;
    }

    public HashMap<Integer, EdgeData> getFromSRC() {
        return fromSRC;
    }

    public HashMap<Integer, EdgeData> getToDEST() {
        return toDEST;
    }

    /**
     * Method to remove given key from src map
     * @param key
     * @return
     */
    public EdgeData removeSRC(int key){
        return fromSRC.remove(key);
    }

    /**
     * Method to remove given key from dest map
     * @param key
     * @return
     */
    public EdgeData removeDEST(int key){
        return toDEST.remove(key);
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
