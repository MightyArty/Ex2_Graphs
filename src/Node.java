import api.GeoLocation;
import api.NodeData;
import java.io.Serializable;

public class Node implements NodeData, Serializable {

    private static int keys = 0;
    private int key;
    private int tag=0;
    private double weight;
    private String info;
    private Location location;


    public Node(double x, double y, double z, int key){
        this.key = key;
        this.location = new Location(x, y, z);
    }

    /**
     * Copy constructor
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

    /**
     * Empty Constructor
     */
    public Node(){
        this.weight = 0;
        this.info = "";
        this.tag = -1;
        this.location = null;
    }

    /**
     * Returns the key (id) associated with this node.
     * @return
     */
    @Override
    public int getKey() {
        return this.key;
    }

    /** Returns the location of this node, if none return null.
     * @return
     */
    @Override
    public GeoLocation getLocation() {
        return this.location;
    }

    /**
     * Returns the weight associated with this node.
     * @return
     */
    @Override
    public double getWeight() {
        return this.weight;
    }

    /**
     * Allows changing this node's weight.
     * @param w - the new weight
     */
    @Override
    public void setWeight(double w) {
        this.weight = w;
    }

    /**
     * Returns the remark (meta data) associated with this node.
     * @return
     */
    @Override
    public String getInfo() {
        return this.info;
    }

    /**
     * Allows changing the remark (meta data) associated with this node.
     * @param s
     */
    @Override
    public void setInfo(String s) {
        this.info = s;
    }

    /**
     * Temporal data (aka color: e,g, white, gray, black)
     * which can be used be algorithms
     * @return
     */
    @Override
    public int getTag() {
        return this.tag;
    }

    /**
     * Allows setting the "tag" value for temporal marking an node - common
     * practice for marking by algorithms.
     * @param t - the new value of the tag
     */
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

    @Override
    public String toString(){
        return "Node{" + "key= " + this.key + ",location= " + location + ", tag= " + tag + ", weight= " + weight + ", info= " + info  + "}" + "\n";
    }
}
