package Algo;

import api.DirectedWeightedGraph;
import api.EdgeData;
import api.NodeData;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;

public class DWGraph implements DirectedWeightedGraph {
    private HashMap<Integer, NodeData> NMap = new LinkedHashMap<>();
    private HashMap<Integer, HashMap<Integer, EdgeData>> EMap = new LinkedHashMap<>();
    private int EdgeSize = 0;
    private int myMc = 0;
    // need to delete in the end
    // need to delete in the end
    // need to delete in the end
    // need to delete in the end
    // need to delete in the end
    // need to delete in the end
    // need to delete in the end
    // need to delete in the end

    /**
     * Empty Constructor
     */
    public DWGraph(){}

    /**
     * Copy Constructor
     * @param graph
     */
    public DWGraph(DirectedWeightedGraph graph){
        // need to work !!
    }

    @Override
    public NodeData getNode(int key) {
        if (NMap.containsKey(key))
            return NMap.get(key);
        return null;
    }

    @Override
    public EdgeData getEdge(int src, int dest) {
        if (EMap.containsKey(src))
            if (EMap.get(src).containsKey(dest))
                return EMap.get(src).get(dest);
        return null;
    }

    @Override
    public void addNode(NodeData n) {
        if (Node.getNodeID() < n.getKey())
            Node.setNodeID(n.getKey()+1);
        NMap.put(n.getKey(), n);
        HashMap<Integer,EdgeData> temp = new LinkedHashMap<>();
        EMap.put(n.getKey(),temp);
        myMc++;
    }

    @Override
    public void connect(int src, int dest, double w) {
        if (!(NMap.containsKey(dest)&&NMap.containsKey(src)))
            throw new RuntimeException("not Exist");
        EdgeData edata = new EData(src,dest,w);
        if (EMap.containsKey(src)){
            EMap.get(src).put(dest,edata);
        }else {
            HashMap<Integer,EdgeData> temp = new LinkedHashMap<>();
            temp.put(dest,edata);
            EMap.put(src,temp);
        }
        EdgeSize++;
        myMc++;
    }

    @Override
    public Iterator<NodeData> nodeIter() {
        return null;
    }

    @Override
    public Iterator<EdgeData> edgeIter() {
        return null;
    }

    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {
        if (!NMap.containsKey(node_id)) {
            return null;
        }
        if (!EMap.containsKey(node_id)) {
            return null;
        }
        return (Iterator<EdgeData>) EMap.get(node_id).values();
    }


    @Override
    public NodeData removeNode(int key) {
        if (NMap.containsKey(key)){
            myMc++;
            for (int i =0; i<NMap.size();i++){
                if (NMap.containsKey(i)){
                    removeEdge(i,key);
                }
            }
            return NMap.remove(key);
        }
        else return null;
    }

    @Override
    public EdgeData removeEdge(int src, int dest) {
        if (!(NMap.containsKey(src)&&NMap.containsKey(dest))){
            return null;
        }
        myMc++;
        if (EMap.containsKey(src)) {
            EdgeData temp = EMap.get(src).remove(dest);

            if (temp != null) {
                EdgeSize--;
            }
            return temp;
        }
        return null;
    }

    @Override
    public int nodeSize() {
        return NMap.size();
    }

    @Override
    public int edgeSize() {
        return EdgeSize;
    }

    @Override
    public int getMC() {
        return myMc;
    }
}
