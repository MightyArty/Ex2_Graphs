package utils;

import Algo.DWGraph;
import Algo.Location;
import api.GeoLocation;
import api.NodeData;
import java.awt.Dimension;
import java.util.HashMap;


public final class ResponsiveEditorTools {

    public static final double PADDING_X = 20;
    public static final double PADDING_Y = 20;
    public static double windowWidth = 0.0;
    public static double windowHeight = 0.0;

    public static double maxDistX = -1, maxDistY = -1, scaleX = 1, scaleY = 1;
    public static NodeData leftNode, rightNode, topNode, bottomNode;
    public static double minX, minY, maxX, maxY;

    /**
     * compute the distance between two nodes in the X axis
     * @param node1: the first node
     * @param node2: second node
     * @return distance in X axis between node1 and node2
     */
    public static double distanceX(NodeData node1, NodeData node2) {
        return Math.abs(node1.getLocation().x() - node2.getLocation().x());
    }

    public static double distanceY(NodeData node1, NodeData node2) {
        return Math.abs(node1.getLocation().y() - node2.getLocation().y());
    }

    /**
     * compute the coordinates of the most left/right/top and bottom nodes of the graph
     * @param graph
     */

    public static void computeMaxDistance(DWGraph graph) {
        HashMap<Integer, NodeData> nodes = graph.getNodes();
        if (!nodes.isEmpty()) {

            // get the first node
            int topLeftNodeId = nodes.keySet().iterator().next();
            leftNode = nodes.get(topLeftNodeId);
            rightNode = nodes.get(topLeftNodeId);
            topNode = nodes.get(topLeftNodeId);
            bottomNode = nodes.get(topLeftNodeId);

            for (Integer key : nodes.keySet()) {
                NodeData node = nodes.get(key);
                GeoLocation nodeLocation = node.getLocation();
                // get most left node/ right node
                if (nodeLocation.x() < leftNode.getLocation().x()) {
                    leftNode = node;
                } else if (nodeLocation.x() > rightNode.getLocation().x()) {
                    rightNode = node;
                }
                // get most top node/ bottom node
                if (nodeLocation.y() < topNode.getLocation().y()) {
                    topNode = node;
                } else if (nodeLocation.y() > bottomNode.getLocation().y()) {
                    bottomNode = node;
                }
            }
            maxDistX = distanceX(leftNode, rightNode);
            maxDistY = distanceY(topNode, bottomNode);
            minX = leftNode.getLocation().x();
            minY = topNode.getLocation().y();
            maxX = rightNode.getLocation().x();
            maxY = bottomNode.getLocation().y();
        }
    }

    /**
     * compute the scale on X axis and Y axis
     * @param dimension: the dimensions of the window
     */
    public static void computeScaleParams(Dimension dimension) {
        windowWidth = dimension.width - 4 * PADDING_X;
        windowHeight = dimension.height - 6 * PADDING_Y;
        scaleX = (windowWidth / maxDistX);
        scaleY = (windowHeight / maxDistY);
    }

    /**
     * rescale a node location using computed scale params
     * @param location: the location of the graph
     * @return newLocation: the new location after scaling
     */
    public static GeoLocation scaleLocation(GeoLocation location) {
        double newX = (location.x() - minX) * scaleX + PADDING_X;
        double newY = (location.y() - minY) * scaleY + PADDING_Y;
        return new Location(newX, newY, 0.0);
    }

    /**
     * rescale all the nodes of the graph
     * @param graph
     * @param dimension: window dimensions
     * @return graph: new scaled graph
     */
    public static DWGraph scaleGraphToEditor(DWGraph graph, Dimension dimension) {
        computeMaxDistance(graph);
        computeScaleParams(dimension);
        HashMap<Integer, NodeData> nodes = graph.getNodes();
        for (Integer key : nodes.keySet()) {
            NodeData node = nodes.get(key);
            GeoLocation nodeLocation = node.getLocation();
            GeoLocation newLocation = scaleLocation(nodeLocation);
            nodes.get(key).setLocation(newLocation);
        }
        return graph;
    }
}
