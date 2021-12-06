import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import api.*;
import java.io.*;

public class test {
    public static void main(String[] args) throws IOException, ParseException {
        
        ArrayList[] array_graph = jsonToGraph(
                "/mnt/c/Users/avido/Documents/Universite/matalot/OOP_2021/Assignments/Ex2/data/G1.json");

        DWG testgraph = new DWG(array_graph[0], array_graph[1]);
        
        //System.out.println(testgraph.nodeSize());
        //testgraph.addNode(alone);
        // System.out.println(testgraph.nodeSize());
        // for (int i = 0; i < testgraph.nodeSize(); i++) {
        //     System.out.println(testgraph.getNode(i).toString());
        // }
        System.out.println(isConnected(testgraph));
        // for (Nodes node2 : testgraph.getNodeList()) {
        //     for (EdgeData edge : node2.getEdgeMap().values()) {
        //         System.out.println(edge.toString());
        //     }
        // }
    }

    public static ArrayList[] jsonToGraph(String jsonPath) throws IOException, ParseException {
        ArrayList[] list_graph = new ArrayList[2];
        JSONParser jsonParse = new JSONParser();
        FileReader reader = new FileReader(jsonPath);
        Object obj = jsonParse.parse(reader);
        JSONObject graphJson = (JSONObject) obj;
        ArrayList<Nodes> node_list = new ArrayList<>();
        ArrayList<Edges> edge_list = new ArrayList<>();
        JSONArray Edges_array = (JSONArray) graphJson.get("Edges");
        for (int i = 0; i < Edges_array.size(); i++) {
            JSONObject edgeElement = (JSONObject) Edges_array.get(i);
            int srcE = Math.toIntExact((long) edgeElement.get("src"));
            double wE = (double) edgeElement.get("w");
            int destE = Math.toIntExact((long) edgeElement.get("dest"));
            edge_list.add(new Edges(srcE, wE, destE));

        }
        JSONArray Nodes_array = (JSONArray) graphJson.get("Nodes");
        for (int i = 0; i < Nodes_array.size(); i++) {
            JSONObject nodeElement = (JSONObject) Nodes_array.get(i);
            String pos = (String) nodeElement.get("pos");
            String[] posData = pos.split(",", 0);
            Point_3D new_point = new Point_3D(Double.parseDouble(posData[0]), Double.parseDouble(posData[1]),
                    Double.parseDouble(posData[2]));
            node_list.add(new Nodes(new_point, Math.toIntExact((long) nodeElement.get("id"))));
        }
        list_graph[0] = node_list;
        list_graph[1] = edge_list;
        return list_graph;
    }

    public static boolean isConnected(DWG graph) {
        graph.initializeTag(); // make all node's tag = 0
        int v = 0;
        DFS(graph, v);
        for (int i = 0; i < graph.nodeSize(); i++) {
            Nodes curr = (Nodes) graph.getNode(i);
            if (curr.getTag() != 1) {
                return false;
            }
        }
        graph.initializeTag(); // reset tag values for all nodes in the graph
        ArrayList<Edges> reverseEdges = new ArrayList<>();
        for (int i = 0; i < graph.nodeSize(); i++) {
            Nodes curr = (Nodes) graph.getNode(i);
            for (EdgeData edge : curr.getEdgeMap().values()) {
                int src = edge.getSrc();
                int dest = edge.getDest();
                double weight = edge.getWeight();
                reverseEdges.add(new Edges(dest, weight,src));
            }
        }
        DWG reverseOne = new DWG(graph.getNodeList(), reverseEdges);
        DFS(reverseOne, v);
        for (int i = 0; i < reverseOne.nodeSize(); i++) {
            Nodes curr = (Nodes) reverseOne.getNode(i);
            if (curr.getTag() != 1) {
                return false;
            }
        }
        return true;
    }

    private static void DFS(DWG graph, int v) {
        Nodes curr = (Nodes) graph.getNode(v);
        curr.setTag(1); // means visited
        for (EdgeData edges : curr.getEdgeMap().values()) {
            //NodeData isVisited = graph.getNode(curr.getEdgeMap().get(edges).getDest());
            NodeData isVisited = graph.getNode((edges.getDest()));
            if (isVisited.getTag() == 0) {
                DFS(graph, edges.getDest());
            }
        }
    }

}
