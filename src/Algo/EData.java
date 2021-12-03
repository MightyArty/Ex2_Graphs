package Algo;

import api.EdgeData;

public class EData implements EdgeData {
    private int Src, Dest;
    private int tag;
    double Weight;
    private String info;

    /**
     * Connecting the src to dest
     * @param src - given source
     * @param dest - given destination
     * @param weight - given weight
     */
    public EData(int src, int dest, double weight) {
        if (weight < 0) throw new RuntimeException("ERR: weight cant be negative");
        if (src == dest) throw new RuntimeException("ERR: the destination can't be equals to the source ");
        this.Dest = dest;
        this.Src = src;
        this.Weight = weight;
        this.info = null;
        this.tag = -1000000000; // just for setup
    }

    /**
     * Copy Constructor
     * @param other
     */
    public EData(EdgeData other) {
        this.Dest = other.getDest();
        this.Src = other.getSrc();
        this.Weight = other.getWeight();
        this.tag = -1000000000; // just for setup
    }

    @Override
    public int getSrc() {
        return this.Src;

    }

    @Override
    public int getDest() {
        return this.Dest;
    }

    @Override
    public double getWeight() {
        return this.getWeight();
    }

    @Override
    public String getInfo() {
        return this.info;
    }

    @Override
    public String toString(){
        return "EData{" + "source=" + this.Src + ",destination=" + this.Dest + ",weight=" + this.Weight + ",info=" + this.info + "and the tag=" + this.tag + "}";
    }

    @Override
    public void setInfo(String s) {
        this.info=s;
    }

    @Override
    public int getTag() {
        return tag;
    }

    @Override
    public void setTag(int t) {
        this.tag=t;
    }
}