import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import api.*;
import java.io.*;




public class test {
    public static void main(String[] args) throws IOException, ParseException {
        ArrayList<Integer> test = new ArrayList<>();
        test.add(5);
        //System.out.println(test.get(0));
        Point_3D p=new Point_3D(1,2,3);
        Point_3D d = new Point_3D(p);
        Point_3D f = new Point_3D(4,5,6);
        //System.out.println("p("+p.x()+", "+p.y()+", "+p.z()+")\n");
        //System.out.println("d("+d.x()+", "+d.y()+", "+d.z()+")");
        //Nodes node = new Nodes(p , 0);
        Nodes node = new Nodes(new Point_3D(1 , 2 , 3), 1);
        System.out.println("p("+node.getLocation().x()+", "+node.getLocation().y()+", "+node.getLocation().z()+")"+node.getKey()+"\n");
        node.setLocation(f);
        System.out.println("p("+node.getLocation().x()+", "+node.getLocation().y()+", "+node.getLocation().z()+")"+node.getKey()+"\n");
        ArrayList[] array_graph = jsonToGraph("/mnt/c/Users/avido/Documents/Universite/matalot/OOP_2021/Assignments/Ex2/data/G1.json");
        for (int i = 0; i < array_graph[0].size(); i++) {
            System.out.println(array_graph[1].get(i).toString());
        }
    }

    public static ArrayList[] jsonToGraph(String jsonPath) throws IOException, ParseException{
        ArrayList[] list_graph = new ArrayList[2];
        JSONParser jsonParse = new JSONParser();
        FileReader reader = new FileReader(jsonPath);
        Object obj = jsonParse.parse(reader);
        JSONObject graphJson = (JSONObject)obj;
        ArrayList<Nodes> node_list = new ArrayList<>();
        ArrayList<Edges> edge_list = new ArrayList<>();
        JSONArray Edges_array = (JSONArray) graphJson.get("Edges");
        for (int i = 0; i < Edges_array.size(); i++) {
            JSONObject edgeElement =  (JSONObject) Edges_array.get(i);
            int srcE = Math.toIntExact((long) edgeElement.get("src"));
            double wE = (double) edgeElement.get("w");
            int destE = Math.toIntExact((long) edgeElement.get("dest"));
            edge_list.add(new Edges(srcE, wE, destE));
                 
        }
        JSONArray Nodes_array = (JSONArray) graphJson.get("Nodes");
        for (int i = 0; i < Nodes_array.size(); i++) {
            JSONObject nodeElement = (JSONObject) Nodes_array.get(i);
            String pos  = (String) nodeElement.get("pos");
            String[] posData = pos.split(",",0);
            Point_3D new_point = new Point_3D(Double.parseDouble(posData[0]), Double.parseDouble(posData[1]), Double.parseDouble(posData[2]));
            node_list.add(new Nodes(new_point,Math.toIntExact((long)nodeElement.get("id"))));
        }
        list_graph[0] = node_list;
        list_graph[1] = edge_list;
        return list_graph;
    }

    



}
