package Algo;

import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.EdgeData;
import api.NodeData;

import java.util.*;

public class DWGraphAlgorithm implements DirectedWeightedGraphAlgorithms {

    // HashMap that holds the information gathered by the Dijkstra algorithm for each node
    private HashMap<Integer, Node> dNodeMap;

    public DirectedWeightedGraph myGraph;
    public NodeData myData;
    public EdgeData myEdge;
    public boolean OK = true;

    /**
     * Constructor
     *
     * @param graph
     */
    public DWGraphAlgorithm(DirectedWeightedGraph graph) {
        this.myGraph = graph;
    }

    /**
     * Empty Constructor
     */
    public DWGraphAlgorithm() {
    }

    @Override
    public void init(DirectedWeightedGraph g) {
        this.myGraph = new DWGraph(g);
    }

    @Override
    public DirectedWeightedGraph getGraph() {
        return null;
    }

    @Override
    public DirectedWeightedGraph copy() {
        return null;
    }

    //    @Override
    public boolean isConnected() {
        return false;
    }

    @Override
    public double shortestPathDist(int src, int dest) {

        if (myGraph == null || myGraph.getNode(src) == null || myGraph.getNode(dest) == null) {
            return -1;
        }
        if (src == dest) {
            return 0;
        }

        return shortAssist(src, dest);

    }

    /**
     * Returning the shortest path from src to dest - as list of NODES
     * example : src --> node1 --> node2 --> node3 --> ... --> dest
     *
     * @param src  - start node
     * @param dest - end (target) node
     * @return
     */
    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        shortestPathDist(src, dest);
        List<NodeData> ans = new ArrayList<>();
        ans.add(myGraph.getNode(dest));
        int index = dest;
        while (index != src) {
            ans.add(dNodeMap.get(index));
            index = dNodeMap.get(index).getKey();
        }


        Collections.reverse(ans);
        return ans;
    }

    @Override
    public NodeData center() {
        return null;
    }

    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
        return null;
    }

    @Override
    public boolean save(String file) {
        return false;
    }

    @Override
    public boolean load(String file) {
        return false;
    }
    // David

    private double shortAssist(int src, int dest) {
        DWGraph graph = (DWGraph) myGraph;
        Node curr = (Node) graph.getNode(src);

        dNodeMap.put(src, curr);

        // Looping on all the vertices
        for (Iterator<NodeData> it = graph.nodeIter(); it.hasNext(); ) {
            Node temp = (Node) it.next();
            if (temp.getKey() != src) {
                // set all the others nodes to be infinity
                temp.setWeight(Double.POSITIVE_INFINITY);
                temp.setTag(-1);
                temp.setInfo("Not visited");
            }
        }
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(curr);
        while (!pq.isEmpty()) {
            Iterator<EdgeData> edge = graph.edgeIter(curr.getKey());
            while (edge.hasNext() && pq.peek().getInfo() != "Visited") { // new condition
                EData next = (EData) edge.next();
                if (curr.getKey() != next.getDest()) {
                    double sumWeight = next.getWeight() + dNodeMap.get(next.getSrc()).getWeight();
                    //check if the weight is smaller than current weight
                    if (graph.getNode(next.getDest()).getWeight() > sumWeight) {
                        //init the node weight
                        graph.getNode(next.getDest()).setWeight(sumWeight);
                        // init the node parent
                        graph.getNode(next.getDest()).setTag(next.getSrc());
                        dNodeMap.put(next.getDest(), curr);
                    }

                    pq.add((Node) graph.getNode(next.getDest()));
                }
            }
            graph.getNode(pq.peek().getKey()).setInfo("Visited");
            pq.poll();
            curr = pq.peek();
        }
        double ans = graph.getNode(dest).getWeight();
        return ans;
    }
}



