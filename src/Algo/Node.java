package Algo;

import java.awt.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;

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
     * Main Constructor
     * @param key
     * @param tag
     * @param weight
     * @param info
     * @param location
     */
    public Node(int key, int tag, double weight, String info, Location location){
        this.key = key;
        this.tag = tag;
        this.weight = weight;
        if(info == null)
            this.info = new String();
        else this.info = info;
        if(location == null)
            this.location = new Location();
        else this.location = location;
    }

    /**
     * Constructor for key and given location
     * @param key
     * @param location
     */
    public Node(int key, Location location) {
        this.key = key;
        if(location == null) return;
        this.location = location;
        this.fromSRC = new HashMap<>();
        this.toDEST = new HashMap<>();
    }

    /**
     * Empty Constructor
     */
    public Node(){
        this.key = keys++;
        this.location = new Location();
        this.weight = 0;
        this.tag = 0;
        this.info = "";
    }

    /**
     * Copy Constructor
     * @param other
     */
    public Node(NodeData other){
        if (other == null) return;
        this.key = other.getKey();
        this.location = new Location(other.getLocation());
        this.weight = other.getWeight();
        this.tag = other.getTag();
        this.info = new String(other.getInfo());
        this.fromSRC = new HashMap<>();
        this.toDEST = new HashMap<>();
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
     * return the src
     * @param key
     * @return
     */
    public EdgeData getFromSRC(int key){
        return fromSRC.get(key);
    }

    /**
     * return the dest
     * @param key
     * @return
     */
    public EdgeData getFromDEST(int key){
        return toDEST.get(key);
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
