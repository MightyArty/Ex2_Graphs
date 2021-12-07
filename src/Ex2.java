import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import com.google.gson.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;

/**
 * This class is the main class for Ex2 - your implementation will be tested using this class.
 */
public class Ex2 {
    /**
     * This static function will be used to test your implementation
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraph getGraph(String json_file) {
        DirectedWeightedGraph ans = new DWGraph();
        ans = loadFromJSON(json_file);
        return ans;
    }
    /**
     * This static function will be used to test your implementation
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraphAlgorithms getGraphAlgo(String json_file) {
        DirectedWeightedGraph graph = loadFromJSON(json_file);
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
    private static DWGraph loadFromJSON(String file) {
        DWGraph graph = new DWGraph();
        try {
            JsonElement element = JsonParser.parseReader(new FileReader(file));
            JsonObject object = element.getAsJsonObject();
            JsonArray nodes = object.get("Nodes").getAsJsonArray();
            JsonArray edges = object.get("Edges").getAsJsonArray();
            for (int i = 0; i < edges.size(); i++) {
                JsonObject e = new Gson().fromJson(edges.get(i), JsonObject.class);
                String first = e.get("src").getAsString();
                String second = e.get("dest").getAsString();
                String third = e.get("w").getAsString();
                int src = Integer.parseInt(first);
                int dest = Integer.parseInt(second);
                double weight = Integer.parseInt(third);
                graph.connect(src, dest, weight);
            }
            for (int i = 0; i < nodes.size(); i++) {
                JsonObject n = new Gson().fromJson(nodes.get(i), JsonObject.class);
                String position = n.get("pos").getAsString();
                String[] positionArr = position.split(",");
                double[] arr = new double[3];
                for (int k = 0; k < positionArr.length; k++) {
                    arr[k] = Double.parseDouble(positionArr[k]);
                }
                String id = n.get("id").getAsString();
                double x = arr[0];
                double y = arr[1];
                double z = arr[2];
                int actualID = Integer.parseInt(id);
                Node newNode = new Node(x, y, z, actualID);
                graph.addNode(newNode);
            }

        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return graph;
    }
}