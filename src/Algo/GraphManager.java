package Algo;

import api.DirectedWeightedGraphAlgorithms;
import api.EdgeData;
import api.GeoLocation;
import io.IOHandler;
import api.NodeData;
import Gui.Dot;
import Gui.DrawArea;
import Gui.EdgeDrawer;
import Gui.NodeDrawer;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class GraphManager {

    private NodeDrawer nodeDrawer;
    private EdgeDrawer edgeDrawer;
    private IOHandler ioHandler;
    private DrawArea drawArea;
    private static int autoKey;
    private DWGraph graph;
    private DirectedWeightedGraphAlgorithms algorithm;
    private final Color PATH_COLOR = Color.CYAN;
    private List<NodeData> path;

    public GraphManager() {
        this.ioHandler = new IOHandler();
        this.graph = new DWGraph();
        this.path = new ArrayList<>();
    }

    public GraphManager(DrawArea drawArea) {
        this();
        initDrawers(drawArea);
    }

    public void initAlgo(DirectedWeightedGraphAlgorithms algorithm) {
        this.algorithm = algorithm;
    }

    public int getMaxKey() {
        int maxKey = -1;
        HashMap<Integer, NodeData> nodes = this.graph.getNodes();
        for (Integer key : nodes.keySet()) {
            maxKey = Math.max(key, maxKey);
        }
        return maxKey + 1;
    }

    public void save() {
        String filePath = ioHandler.getDestinationPath();
        this.algorithm.save(filePath);
    }

    public boolean load() {
        graph = ioHandler.load();
        autoKey = getMaxKey();
        if (graph != null) {
            this.algorithm = new DWGraphAlgorithm();
            this.algorithm.init(graph);

        }
        return graph != null && (!graph.getNodes().isEmpty() || !graph.getNewEdges().isEmpty() || !graph.getReversedEdges().isEmpty());
    }

    public NodeData getNode(int key) {
        return graph.getNode(key);
    }

    public HashMap<Integer, NodeData> getNodes() {
        return graph.getNodes();
    }

    public void clearDrawArea() {
        this.drawArea.clear();
    }

    public int getIdFromCoordinates(GeoLocation location) {
        HashMap<Integer, NodeData> nodes = graph.getNodes();
        for (Integer key : nodes.keySet()) {
            NodeData node = nodes.get(key);
            if (node.getLocation().distance(location) < Dot.DIAMETER) {
                return node.getKey();
            }
        }
        return -1;
    }

    public void drawGraph() {
        HashMap<Integer, NodeData> nodes = graph.getNodes();
        HashMap<Integer, HashMap<Integer, EdgeData>> newEdges = graph.getNewEdgesHashMap();
        HashMap<Integer, HashMap<Integer, EdgeData>> reversedEdges = graph.getReversedEdges();
        // draw all nodes
        for (Integer key : nodes.keySet()) {
            NodeData node = nodes.get(key);

            HashMap<Integer, EdgeData> nodeDirectEdges = newEdges.get(key);
            HashMap<Integer, EdgeData> nodeReversedEdges = reversedEdges.get(key);
            // draw corresponding edges for each node
            for (Integer edgeKey : newEdges.keySet()) {
                if (nodeDirectEdges != null) {
                    EdgeData edge = nodeDirectEdges.get(edgeKey);
                    if (edge != null) {
                        addEdge(edge);
                    }
                }
                if (nodeReversedEdges != null) {
                    EdgeData reverseEdge = nodeReversedEdges.get(edgeKey);
                    if (reverseEdge != null) {
                        addEdge(reverseEdge);
                    }
                }
            }
            nodeDrawer.drawNode(node);
        }
        this.algorithm.init(graph);
    }

    public void redrawGraph() {
        clearDrawArea();
        drawGraph();
    }

    public void addNode(NodeData node) {
        nodeDrawer.drawNode(node);
        graph.addNode(node);
        this.algorithm.init(graph);
    }

    public void addEdge(EdgeData edge) {
        NodeData srcNode = graph.getNode(edge.getSrc());
        NodeData dstNode = graph.getNode(edge.getDest());
        graph.connect(edge.getSrc(), edge.getDest(), edge.getWeight());
        this.algorithm.init(graph);
        if (inPath(edge.getSrc(), edge.getDest())) {
            edgeDrawer.drawEdge(edge, srcNode.getLocation(), dstNode.getLocation(), edge.getWeight(), PATH_COLOR);
        } else {
            edgeDrawer.drawEdge(edge, srcNode.getLocation(), dstNode.getLocation(), edge.getWeight(), Color.DARK_GRAY);
        }
    }

    public void removeNode(int id) {
        graph.removeNode(id);
        redrawGraph();
        this.drawArea.repaint();
        this.algorithm.init(graph);
    }

    public boolean removeEdge(int src, int dst) {
        boolean removed = graph.removeEdge(src, dst) != null;
        if (removed) {
            this.redrawGraph();
        }
        return removed;
    }

    public void updateWeight(int src, int dst, double weight) {
        graph.getEdge(src, dst);
    }

    public void deleteGraph() {
        this.clearDrawArea();
        this.path.clear();
        this.graph = new DWGraph();
        this.redrawGraph();
    }

    public DWGraph getGraph() {
        return graph;
    }

    public void setGraph(DWGraph graph) {
        this.graph = graph;
        this.path.clear();
        this.redrawGraph();
        if (graph != null) {
            this.algorithm = new DWGraphAlgorithm();
            this.algorithm.init(graph);
        }
    }

    public boolean isConnected() {
        this.algorithm.init(graph);
        boolean connected = this.algorithm.isConnected();
        return connected;
    }

    public int getGeneratedKey() {
        return autoKey++;
    }

    public double shortestPathDist(int src, int dest) {
        double distance;
        try {
            distance = algorithm.shortestPathDist(src, dest);
        } catch (Exception e) {
            distance = -1;
        }
        return distance;
    }

    public List<NodeData> shortestPath(int src, int dest) {
        return algorithm.shortestPath(src, dest);
    }

    public NodeData getGraphCenter() {
        return this.algorithm.center();
    }

    public List<NodeData> tsp(List<NodeData> cities) {
        return algorithm.tsp(cities);
    }

    public void renderPath(List<NodeData> path) {
        if (path != null) {
            this.path = path;
        }
        redrawGraph();
    }

    private boolean inPath(int src, int dest) {
        boolean srcInPath = false, dstInPath = false;
        for (NodeData node : path) {
            if (node.getKey() == src) {
                srcInPath = true;
                if (dstInPath) {
                    break;
                }
            }
            if (node.getKey() == dest) {
                dstInPath = true;
                if (srcInPath) {
                    break;
                }
            }
        }
        return srcInPath && dstInPath;
    }

    public final void initDrawers(DrawArea drawArea) {
        this.drawArea = drawArea;
        this.nodeDrawer = new NodeDrawer(drawArea);
        this.edgeDrawer = new EdgeDrawer(drawArea);
    }

    public void loadFromFile(String json_file) {
        graph = ioHandler.loadFromJson(json_file);
        if (graph != null) {
            this.algorithm = new DWGraphAlgorithm();
            this.algorithm.init(graph);
        }
    }

    public DirectedWeightedGraphAlgorithms getAlgorithm() {
        return this.algorithm;
    }

}
