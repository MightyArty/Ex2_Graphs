import api.EdgeData;

public class EData implements EdgeData {
    private int Src, Dest;
    private int tag;
    private double Weight;
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
        this.tag = -1000000000;
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

    /**
     * The id of the source node of this edge.
     * @return
     */
    @Override
    public int getSrc() {
        return this.Src;

    }

    /**
     * The id of the destination node of this edge
     * @return
     */
    @Override
    public int getDest() {
        return this.Dest;
    }

    /**
     * @return the weight of this edge (positive value).
     */
    @Override
    public double getWeight() {
        return this.Weight;
    }

    /**
     * Returns the remark (meta data) associated with this edge.
     * @return
     */
    @Override
    public String getInfo() {
        return this.info;
    }

    @Override
    public String toString(){
        return "EData{" + "source=" + Src + ",destination=" + Dest + ",weight=" + Weight + ",info=" + info + "and the tag=" + tag + "}";
    }

    /**
     * Allows changing the remark (meta data) associated with this edge.
     * @param s
     */
    @Override
    public void setInfo(String s) {
        this.info=s;
    }

    /**
     * Temporal data (aka color: e,g, white, gray, black)
     * which can be used be algorithms
     * @return
     */
    @Override
    public int getTag() {
        return tag;
    }

    /**
     * This method allows setting the "tag" value for temporal marking an edge - common
     * practice for marking by algorithms.
     * @param t - the new value of the tag
     */
    @Override
    public void setTag(int t) {
        this.tag=t;
    }
}
