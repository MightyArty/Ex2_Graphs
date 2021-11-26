package Algo;

import api.EdgeData;

public class Edata implements EdgeData {
    private int Src, Dest, tag;
    double Weight;
    private String info;

    /**
     * copy contractor
     * @param e
     */
    public Edata(edge_data e){
        this.Dest = e.getDest();
        this.Src = e.getSrc();
        this.Weight = e.getWeight();
    }
    public Edata(int src,int dest, double weight){
        if (weight<0) throw new RuntimeException("ERR: weight cant be negetive");
        if (src==dest) throw new RuntimeException("ERR: the destination can't be equals to the source ");
        this.Dest = dest;
        this.Src = src;
        this.Weight = weight;
    }


    @Override
    public int getSrc() {
        return this.Src;
        if (Src<0) throw new RuntimeException("ERR: weight cant be negetive");
        if (Src==0) throw new RuntimeException("ERR: the destination can't be equals to the source ");
        if (Src<0) throw new RuntimeException("ERR: weight cant be negetive");
        if (Src<0) throw new RuntimeException("ERR: weight cant be negetive");
    }

    @Override
    public int getDest() {
        return 0;
    }

    @Override
    public double getWeight() {
        return 0;
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
}
