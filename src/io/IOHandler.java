package io;

import api.NodeData;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import Algo.*;

import java.io.File;
import java.io.FileReader;

/**
 * Allows for input/output operations like reading and writing from/to a json file
 */

public class IOHandler {


    /**
     * returns the DirectedWeightedGraph with the data loaded from the json file given in param,
     *
     * @param file - the path to json file
     * @return the loaded graph, null if none.
     */

    public DWGraph loadFromJson(String file) {
        DWGraph graph = new DWGraph();
        JSONParser p = new JSONParser();
        try {
            JSONObject object = (JSONObject) p.parse(new FileReader(file));
            JSONArray nodes = (JSONArray) object.get("Nodes");
            JSONArray edges = (JSONArray) object.get("Edges");

            for (int i = 0; i < nodes.size(); i++) {
                JSONObject current = (JSONObject) nodes.get(i);
                String pos = current.get("pos").toString();
                String id = current.get("id").toString();
                NodeData node = new Node(Integer.parseInt(id), pos);
                graph.addNode(node);
            }

            for (int i = 0; i < edges.size(); i++) {
                JSONObject current = (JSONObject) edges.get(i);
                if ((current.get("w") != null) && (current.get("src") != null) && (current.get("dest") != null)) {
                    int src = Integer.parseInt(current.get("src").toString());
                    int dest = Integer.parseInt(current.get("dest").toString());
                    double weight = Double.parseDouble(current.get("w").toString());
                    graph.connect(src, dest, weight);
                }
            }
        } catch (Exception e) {
            return null;
        }
        return graph;
    }

    /**
     * make use of loadFromJson method to and the FileChooser class to open a file,
     * returns the DirectedWeightedGraph with the data loaded from the json file loaded using the FileChooser,
     *
     * @return the loaded graph, Empty graph if given an invalid path.
     */

    public DWGraph load() {
        FileChooser fileChooser = new FileChooser();
        File openedFile = fileChooser.chooseFile();
        if (openedFile != null) {
            return this.loadFromJson(openedFile.getAbsolutePath());
        } else {
            return new DWGraph();
        }
    }

    /**
     * Help method to retrieve a destination path using the FileChooser class
     * @return path: a valid path chosen by the user
     */

    public String getDestinationPath() {
        String path;
        try {
            FileChooser fileChooser = new FileChooser();
            path = fileChooser.chooseSaveLocation().getAbsolutePath();
        } catch (Exception e) {
            path = "";
        }
        return path;
    }


}
