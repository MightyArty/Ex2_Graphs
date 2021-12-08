import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.EdgeData;
import api.NodeData;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

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

    public static void main(String[] args) {
       // DWGraphAlgorithm algo=new DWGraphAlgorithm();
        //DWGraph temp= new DWGraph();
        DirectedWeightedGraph temp = loadFromJson("C:\\Users\\97252\\IdeaProjects\\Ex2_Graphs\\src\\G1.json");

  //      algo.getGraph().connect(0,16,1.5677693324851103);
//        algo.getGraph().connect(1,0,1.8635670623870366);
       DWGraphAlgorithm n = new DWGraphAlgorithm();

       n.init(temp);

        Iterator<EdgeData> i =n.getGraph().edgeIter(0);
//        while(i.hasNext()){
//            System.out.println(i.next());
//        }
        //n.getGraph().removeEdge(0,16);
        //n.getGraph().removeEdge(0,1);
        //System.out.println(n.getGraph().removeNode(0));
      //System.out.println(n.shortestPathDist(0,4));
       // System.out.println(n.shortestPath(0,4));
              //  System.out.println(n.center());
        //Iterator<NodeData> a=n.getGraph().nodeIter();
        //while(a.hasNext()) System.out.println(a.next());
       // System.out.println(n.isConnected());
      //  System.out.println(n.getGraph().removeNode(0));
      //  System.out.println(n.getGraph().edgeIter().next());
       // System.out.println(n.getGraph().removeNode(0));
       // System.out.println(n.isConnected());
      // EdgeData s1 =  n.edgeIter(0).next();
      //  String e1 = s1.toString();
//       System.out.println(t1.edgeIter(0).toString());
//        System.out.println(e1);


      //  System.out.println(n.getGraph().edgeSize());
       // System.out.println(n.isConnected());
      //  System.out.println(graph.getEdge(0,16).toString());
        //System.out.println(graph.getEdge(1,0).toString());
//        Queue<Integer> q =new ArrayDeque<>();
//        q.add(10);
//        q.add(20);
//        q.add(111);
//        System.out.println(q.peek());
//        System.out.println(q);
        System.out.println(n.center());
    }
}