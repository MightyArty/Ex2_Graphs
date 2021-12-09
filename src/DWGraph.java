import api.DirectedWeightedGraph;
import api.EdgeData;
import api.NodeData;

import java.util.HashMap;
import java.util.Iterator;

public class DWGraph implements DirectedWeightedGraph {
    private HashMap<Integer, NodeData> nodes;
    private HashMap<Integer, HashMap<Integer, EdgeData>> newEdges;
    private HashMap<Integer, HashMap<Integer, EdgeData>> reversedEdges;
    private int edgeSize;
    private int mc;

    public DWGraph(DWGraph graph){
        nodes = graph.nodes;
        newEdges = graph.newEdges;
        reversedEdges = graph.reversedEdges;
        this.edgeSize=graph.edgeSize;
        this.mc=graph.mc;
    }

    /**
     * Empty Constructor
     */
    public DWGraph(){
        this.nodes = new HashMap<>();  // new node map
     //   this.edges = new HashMap<>();   // new edges map
        this.newEdges = new HashMap<>();
        this.reversedEdges = new HashMap<>();
        this.edgeSize = 0;
        this.mc = 0;
    }

    /**
     * returns the Ex2.node_data by the node_id,
     *
     * @param key - the node_id
     * @return the Ex2.node_data by the node_id, null if none.
     */
    @Override
    public NodeData getNode(int key) {
        if(!this.nodes.containsKey(key))
          return null;
        return nodes.get(key);
    }

    /**
     * returns the data of the edge (src,dest), null if none.
     * Note: this method should run in O(1) time.
     *
     * @param src
     * @param dest
     * @return
     */
    @Override
    public EdgeData getEdge(int src, int dest) {
//        if(newEdges.get(src).get(dest) == null || src == dest)
//            throw new RuntimeException("There is no source or destination or the source is equal to destination so put a right data!");
        EdgeData ans = newEdges.get(src).get(dest);
        return ans;
    }


    public HashMap<Integer, NodeData> getNodes(){
        return nodes;
    }

    public HashMap<Integer, HashMap<Integer, EdgeData>> getNewEdges(){
        return newEdges;
    }

    public HashMap<Integer, HashMap<Integer, EdgeData>> getReversedEdges(){
        return reversedEdges;
    }

    /**
     * adds a new node to the graph with the given Ex2.node_data.
     * Note: this method should run in O(1) time.
     * @param n
     */
    @Override
    public void addNode(NodeData n) {
        if(n == null || this.nodes.containsKey(n.getKey()) || nodes.get(n.getKey()) != null)
            throw new RuntimeException("The new node does not exist or the key already exist");
        nodes.put(n.getKey(),n);
        this.mc ++;
    }

    /**
     * Connects an edge with weight w between node src to node dest.
     * * Note: this method should run in O(1) time.
     * @param src - the source of the edge.
     * @param dest - the destination of the edge.
     * @param w - positive weight representing the cost (aka time, price, etc) between src-->dest.
     */
    @Override
    public void connect(int src, int dest, double w) {
        if(!this.nodes.containsKey(src) || !this.nodes.containsKey(dest) || src == dest || w < 0)
            return;
        EdgeData edge = new EData(src, dest, w);
        if(newEdges.get(src)!=null) {
            HashMap<Integer, EdgeData> temp = newEdges.get(src);
            temp.put(dest, edge);
            newEdges.put(src, temp);
        } else{
            HashMap<Integer,EdgeData> temp =new HashMap<>();
            temp.put(dest,edge);
            newEdges.put(src,temp);
        }
        if(reversedEdges.get(dest)!=null) {
            HashMap<Integer, EdgeData> temp2 = reversedEdges.get(dest);
            temp2.put(src, edge);
            reversedEdges.put(dest, temp2);
        } else{
            HashMap<Integer,EdgeData> temp =new HashMap<>();
            temp.put(src,edge);
            reversedEdges.put(dest,temp);
        }
        this.edgeSize ++;
        this.mc ++;
    }

    @Override
    public Iterator<NodeData> nodeIter() {
        return nodes.values().iterator();
    }

    @Override
    public Iterator<EdgeData> edgeIter(){
        Iterator edge = this.newEdges.get(newEdges.values()).values().iterator();
        return edge;
    }

    /**
     * This method returns an Iterator for edges getting out of the given node (all the edges starting (source) at the given node).
     * Note: if the graph was changed since the iterator was constructed - a RuntimeException should be thrown.
     * @return Iterator<EdgeData>
     */
    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {
         return newEdges.get(node_id).values().iterator();
    }

    /**
     * Deletes the node (with the given ID) from the graph -
     * and removes all edges which starts or ends at this node.
     * This method should run in O(k), V.degree=k, as all the edges should be removed.
     * @return the data of the removed node (null if none).
     * @param key
     */
    @Override
    public NodeData removeNode(int key) {
        if(!this.nodes.containsKey(key))
            return null;
        NodeData vertex = nodes.remove(key);
        HashMap<Integer, EdgeData> regular = newEdges.get(key); //3
        HashMap<Integer, EdgeData> reversed = reversedEdges.get(key);
        Iterator<EdgeData> i = regular.values().iterator();
       Iterator<EdgeData> k = reversed.values().iterator();
        while (i.hasNext()){
            EdgeData runner = i.next(); //1
            EdgeData eData = newEdges.get(runner.getSrc()).get(runner.getDest()); //delete of the src 1->2
            reversedEdges.get(eData.getDest()).remove(eData.getSrc());
            edgeSize --;
        }

        while (k.hasNext()){
            EdgeData runner = k.next();
            EdgeData eData = reversedEdges.get(runner.getDest()).get(runner.getSrc());
            newEdges.get(eData.getSrc()).remove(eData.getDest());
            edgeSize --;

        }
        this.mc ++;
        return vertex;
    }

    /**
     * Deletes the edge from the graph,
     * Note: this method should run in O(1) time.
     * @param src
     * @param dest
     * @return the data of the removed edge (null if none).
     */
    @Override
    public EdgeData removeEdge(int src, int dest) {
        if(src == dest || newEdges.get(src).get(dest) == null)
            return null;

        EdgeData ans = newEdges.get(src).remove(dest);
        reversedEdges.get(dest).remove(src);
        this.mc ++;
        this.edgeSize --;
        return ans;
    }

    @Override
    public int nodeSize() {
        return this.nodes.size();
    }

    @Override
    public int edgeSize() {
        return this.edgeSize;
    }

    @Override
    public int getMC() {
        return this.mc;
    }
}
