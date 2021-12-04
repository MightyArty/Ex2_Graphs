import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.NodeData;
import com.google.gson.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class DWGraphAlgorithm implements DirectedWeightedGraphAlgorithms {

    public DWGraph myGraph;

    /**
     * Inits the graph on which this set of algorithms operates on.
     * @param g
     */
    @Override
    public void init(DirectedWeightedGraph g) {
        this.myGraph = (DWGraph) g;
    }

    /**
     * Empty constructor
     */
    public DWGraphAlgorithm(){
        this.myGraph = new DWGraph();
    }

    @Override
    public DirectedWeightedGraph getGraph() {
        return this.myGraph;
    }

    @Override
    public DirectedWeightedGraph copy() {
        DWGraph copy = this.myGraph;
        return copy;
    }

    @Override
    public boolean isConnected() {
        return false;
    }

    @Override
    public double shortestPathDist(int src, int dest) {

        return 0;
    }


    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        return null;
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
        Gson gson = new Gson();
        String out = gson.toJson(myGraph.getNodes());
        boolean result = false;
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(out);
            writer.flush();
            result = true;
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean load(String file) {
        boolean flag = false;
        try {
            JsonElement element = JsonParser.parseReader(new FileReader(file));
            JsonObject object = element.getAsJsonObject();
            JsonArray nodes = object.get("Nodes").getAsJsonArray();
            JsonArray edges = object.get("Edges").getAsJsonArray();
            for(int i = 0 ; i < edges.size() ; i++){
                JsonObject e = new Gson().fromJson(edges.get(i), JsonObject.class);
                String first = e.get("src").getAsString();
                String second = e.get("dest").getAsString();
                String third = e.get("w").getAsString();
                int src = Integer.parseInt(first);
                int dest = Integer.parseInt(second);
                double weight = Integer.parseInt(third);
                myGraph.connect(src,dest,weight);
            }
            for(int i = 0 ; i < nodes.size() ; i++){
                JsonObject n = new Gson().fromJson(nodes.get(i), JsonObject.class);
                String position = n.get("pos").getAsString();
                String[] positionArr = position.split(",");
                double[] arr = new double[3];
                for(int k = 0 ; k < positionArr.length ; k++){
                    arr[k] = Double.parseDouble(positionArr[k]);
                }

                String id = n.get("id").getAsString();
                double x = arr[0];
                double y = arr[1];
                double z = arr[2];
                int actualID = Integer.parseInt(id);
                Node newNode = new Node(x,y,z,actualID);
                myGraph.addNode(newNode);
            }
            flag = true;
        }
        catch (FileNotFoundException exception){
            exception.printStackTrace();
        }
        return flag;
    }
}
