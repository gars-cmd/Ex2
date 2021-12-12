import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;

import java.util.Scanner;

/**
 * This class is the main class for Ex2 - your implementation will be tested using this class.
 */
public class Ex2 {
    /**
     * This static function will be used to test your implementation
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraph getGrapg(String json_file) {
        DirectedWeightedGraphAlgorithms graphalgo=new DWGAlgo();
        graphalgo.load(json_file);
        DirectedWeightedGraph ans = graphalgo.getGraph();
        // ****** Add your code here ******
        //
        // ********************************
        return ans;
    }
    /**
     * This static function will be used to test your implementation
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraphAlgorithms getGrapgAlgo(String json_file) {

        DirectedWeightedGraphAlgorithms ans = new DWGAlgo();
        ans.load(json_file);
        // ****** Add your code here ******
        //
        // ********************************
        return ans;
    }
    /**
     * This static function will run your GUI using the json fime.
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     *
     */
    public static void runGUI(String json_file) {
        DirectedWeightedGraphAlgorithms alg = getGrapgAlgo(json_file);
        DWG graph = (DWG) alg.getGraph();
        new myGraghP(graph.getNodeList(),json_file);
        // ****** Add your code here ******
        //
        // ********************************
    }
    public static void main(String[] args){

        Scanner sc= new Scanner(System.in);
        String str= sc.nextLine();
        String d="data/";
        runGUI(d+str);
    }
}
