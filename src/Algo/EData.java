package Algo;

import api.EdgeData;

public class EData implements EdgeData {
    private int Src, Dest;
    private int tag;
    double Weight;
    private String info;

    /**
     * Constructor
     * @param src
     * @param dest
     * @param weight
     */
    public EData(int src, int dest, double weight) {
        if (weight < 0) throw new RuntimeException("ERR: weight cant be negative");
        if (src == dest) throw new RuntimeException("ERR: the destination can't be equals to the source ");
        this.Dest = dest;
        this.Src = src;
        this.Weight = weight;
    }

    /**
     * Copy Constructor
     * @param e
     */
    public EData(EdgeData e) {
        this.Dest = e.getDest();
        this.Src = e.getSrc();
        this.Weight = e.getWeight();
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
