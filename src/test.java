import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.EdgeData;
import api.NodeData;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.swing.*;
import java.awt.*;
import java.io.FileReader;
import java.io.IOException;



public class test {
    public static void main(String[] args) throws IOException, ParseException, org.json.simple.parser.ParseException {

        DirectedWeightedGraphAlgorithms launchGraph2 = new DWGAlgo();
        launchGraph2.load("data/G1.json");
        System.out.println(launchGraph2.isConnected());
        ArrayList<Nodes> MYG = new ArrayList<>();
        myGraghP myg = new myGraghP(MYG);
        System.out.println(launchGraph2.shortestPathDist(0, 13));


        //System.out.println(testgraph.nodeSize());
        //testgraph.addNode(alone);
        // System.out.println(testgraph.nodeSize());
        // for (int i = 0; i < testgraph.nodeSize(); i++) {
        //     System.out.println(testgraph.getNode(i).toString());
        // }

        // for (Nodes node2 : testgraph.getNodeList()) {
        //     for (EdgeData edge : node2.getEdgeMap().values()) {
        //         System.out.println(edge.toString());
        //     }
        // }
        // ArrayList[] array_graph = jsonToGraph("./data/G1.json");

        // ArrayList<Nodes> arrNodes= new ArrayList<>();
        //   arrNodes=array_graph[0];
        //   ArrayList<Edges> arrEdges=new ArrayList<>();
        //   arrEdges=array_graph[1];
        //   for (int i = 0; i <arrNodes.size() ; i++) {
        //       for (int j = 0; j <arrEdges.size() ; j++) {
        //           if(arrEdges.get(j).getSrc()==i){
        //               arrNodes.get(i).getEdgeList().add(arrEdges.get(j));

        //           }
        //       }
        //   }
        //   System.out.println(arrNodes.get(1).getEdgeList().get(0).tostring());


    }
}
