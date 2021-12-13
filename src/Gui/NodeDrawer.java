package Gui;

import api.GeoLocation;
import api.NodeData;


public class NodeDrawer {
    private final DrawArea drawArea;
    
    public NodeDrawer(DrawArea drawArea) {
        this.drawArea = drawArea;
    }

    public void drawNode(NodeData node) {
        GeoLocation nodeLocation = node.getLocation();
        Dot dot = new Dot(node.getKey(), nodeLocation.x(), nodeLocation.y());
        this.drawArea.add(dot, 100);
        this.drawArea.repaint();
    }
}
