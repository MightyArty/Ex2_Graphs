package Algo;

import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.NodeData;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

/**
 * This class is the main class for Algo.Ex2 - your implementation will be tested using this class.
 */
public class tempEx2 {
    /**
     * This static function will be used to test your implementation
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraph getGraph(String json_file) {
        DirectedWeightedGraph ans = loadFromJson (json_file);
        return ans;
    }
    /**
     * This static function will be used to test your implementation
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraphAlgorithms getGraphAlgo(String json_file) {
        DirectedWeightedGraph graph = loadFromJson(json_file);
        DirectedWeightedGraphAlgorithms ans = new DWGraphAlgorithm();
        ans.init(graph);
        return ans;
    }
    /**
     * This static function will run your GUI using the json fime.
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     *
     */
    public static void runGUI(String json_file) {
        DirectedWeightedGraphAlgorithms alg = getGraphAlgo(json_file);
        // need to build gui class
    }

    /**
     * for loading the json file
     * @param file given json file
     * @return a new graph
     */
    private static DWGraph loadFromJson(String file){
        DWGraph graph = new DWGraph();
        JSONParser p = new JSONParser();
        try {
            JSONObject object = (JSONObject) p.parse(new FileReader(file));
            JSONArray nodes = (JSONArray) object.get("Nodes");
            JSONArray edges = (JSONArray) object.get("Edges");
            for(int i = 0 ; i < nodes.size(); i++){
                JSONObject current = (JSONObject) nodes.get(i);
                String pos = current.get("pos").toString();
                String id = current.get("id").toString();
                NodeData node = new Node(Integer.parseInt(id), pos);
                graph.addNode(node);
            }

            for(int i = 0 ; i < edges.size() ; i++){
                JSONObject current = (JSONObject) edges.get(i);
                if((current.get("w") != null) && (current.get("src") != null) && (current.get("dest") != null)){
                    int src = Integer.parseInt(current.get("src").toString());
                    int dest = Integer.parseInt(current.get("dest").toString());
                    double weight = Double.parseDouble(current.get("w").toString());
                    graph.connect(src,dest,weight);
                }
            }
        }
        catch (IOException | ParseException e){
            e.printStackTrace();
        }
        return graph;
    }
}