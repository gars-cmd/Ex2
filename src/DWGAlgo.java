import api.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;


public class DWGAlgo implements DirectedWeightedGraphAlgorithms {

    private DWG graph;

    @Override
    public void init(DirectedWeightedGraph g) {

        ArrayList<Nodes> graphList = new ArrayList<>();

        while (g.nodeIter().hasNext()) {
            NodeData node = g.nodeIter().next();
            GeoLocation point = node.getLocation();
            int id = node.getKey();
            NodeData currNode = new Nodes((Point_3D) point, id);

        }
    }

    public void SetGraph(DWG g) {
        this.graph = g;
    }

    @Override
    public DirectedWeightedGraph getGraph() {
        return this.graph;
    }


    @Override
    public DirectedWeightedGraph copy() {
        ArrayList<Nodes> nodeCopy = new ArrayList<>();
        for (Nodes node : this.graph.getNodeList()) {
            Nodes copy = new Nodes(node.getLocation(), node.getKey());
            for (int key : node.getEdgeMap().keySet()) {
                copy.getEdgeMap().put(key, node.getEdgeMap().get(key));
            }
            nodeCopy.add(node);
        }
        DWG copy = new DWG(nodeCopy);
        return copy;
    }

    @Override
    public boolean isConnected() {
        this.graph.initializeTag(); // make all node's tag = 0
        int v = 0;
        BFS(this.graph, v);
        for (int i = 0; i < this.graph.nodeSize(); i++) {
            Nodes curr = (Nodes) this.graph.getNode(i);
            if (curr.getTag() != 1) {
                return false;
            }
        }
        this.graph.initializeTag(); // reset tag values for all nodes in the graph
        ArrayList<Edges> reverseEdges = new ArrayList<>();
        for (int i = 0; i < this.graph.nodeSize(); i++) {
            Nodes curr = (Nodes) this.graph.getNode(i);
            for (EdgeData edge : curr.getEdgeMap().values()) {
                int src = edge.getSrc();
                int dest = edge.getDest();
                double weight = edge.getWeight();
                reverseEdges.add(new Edges(dest, weight, src));
            }
        }
        DWG reverseOne = new DWG(this.graph.getNodeList(), reverseEdges);
        BFS(reverseOne, v);
        for (int i = 0; i < reverseOne.nodeSize(); i++) {
            Nodes curr = (Nodes) reverseOne.getNode(i);
            if (curr.getTag() != 1) {
                return false;
            }
        }
        return true;
    }

    private void DFS(DWG graph, int v) {
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
    private void BFS(DWG graph , int v){
         Queue<NodeData>  queue = new LinkedList<>();
         queue.add(graph.getNode(v));
         graph.getNode(v).setTag(1);
         while (!queue.isEmpty()){
             Nodes node = (Nodes) queue.poll();
             for (int nodes: node.getEdgeMap().keySet()) {
                    if (graph.getNode(nodes).getTag()==0){
                        queue.add((Nodes)graph.getNode(nodes));
                        graph.getNode(nodes).setTag(1);
                    }
             }

         }
    }

    @Override
    public double shortestPathDist(int src, int dest) {
        newDijska(this.graph, src);
        Nodes source = (Nodes) this.getGraph().getNode(src);
        if (source.getMinDist().get(dest) == Double.MAX_VALUE) return -1;
        else return source.getMinDist().get(dest);
    }

    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        int[] list = newDijska(this.graph, src);
        List<NodeData> Fans = new ArrayList<>();
        Fans.add(this.graph.getNode(dest));
        printService(Fans, list, dest);
        Fans.add(this.graph.getNode(src));
        List<NodeData> ans = new ArrayList<>();
        for (int i = Fans.size() - 1; i > -1; i--) {
            ans.add(Fans.get(i));
        }
        return ans;

    }

    @Override
    public NodeData center() {
        if (this.isConnected()) {
            double eloignement, dist;
            ArrayList<double[]> distList = new ArrayList<>();
            for (Nodes node : this.graph.getNodeList()) {
//            //we update the mindistance to all the other nodes of all the nodes
                newDijska(this.graph, node.getKey());
                eloignement = Double.MIN_VALUE;
                for (Nodes node2 : this.graph.getNodeList()) {
//                  // for all the nodes in the graph we set the maxEccentricity to be the max of mindistance
                    if (node.getKey() == node2.getKey()) continue;
                    dist = node.getMinDist().get(node2.getKey());
                    if (dist > eloignement) eloignement = dist;
                }
                //for every iteration over the nodes we save our result
                double[] sample = {eloignement, node.getKey()};
                distList.add(sample);
            }
            //for all the possibility center we found , we will choose the one with the minimal minimum distance
            double max = Double.MAX_VALUE;
            int center = -1;
            for (double[] samples : distList) {
                if (samples[0] < max) {
                    max = samples[0];
                    center = (int) samples[1];
                }
            }
            return this.graph.getNode(center);
        } else return null;
    }

    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
        List<NodeData> ans = new ArrayList<>();
        ans = tspUtile((ArrayList<NodeData>) cities);
        Nodes end = (Nodes) ans.get(ans.size() - 1);
        Nodes first = (Nodes) ans.get(0);
        List<NodeData> ans2 = shortestPath(end.getKey(), first.getKey());
        for (int i = 0; i < ans2.size(); i++) {
            ans.add(ans2.get(i));
        }
        return ans;
    }

    @Override
    public boolean save(String file) {
        try {
            GraphToJson(this.getGraph(), file);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean load(String file) {
        try {
            ArrayList[] arrayGraph = jsonToGraph(file);
            this.graph = new DWG(arrayGraph[0], arrayGraph[1]);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    private List<NodeData> tspUtile(ArrayList<NodeData> cities) {
        ArrayList<NodeData> citiesCopy = new ArrayList<>();
        ArrayList<NodeData> ans = new ArrayList<>();
        for (int i = 0; i < cities.size(); i++) {
            citiesCopy.add(cities.get(i));
        }


        int j = 0;
        ArrayList<Integer> skipped = new ArrayList<>();
        //citiesCopy.remove(i);
        while (citiesCopy.size()>1) {

            newDijska(this.graph, citiesCopy.get(j).getKey());
            Nodes curr = (Nodes) (citiesCopy.get(j));
            skipped.add(citiesCopy.get(j).getKey());
//            citiesCopy.remove(j);
            int attempt = wanted(curr.getMinDist(), curr.getKey(), skipped);
            while (!cities.contains(this.graph.getNode(attempt))) {
                skipped.add(attempt);
                attempt = wanted(curr.getMinDist(), curr.getKey(), skipped);
            }
            ArrayList<NodeData> temp = (ArrayList<NodeData>) shortestPath(curr.getKey(), attempt);
            ans = append(ans, temp);
            citiesCopy.remove(curr);
            for (int k = 0; k <citiesCopy.size() ; k++) {
                if (citiesCopy.get(k).getKey()==attempt) j = k;
            }
//            citiesCopy.remove(curr);




        }
        return ans;
    }

    private ArrayList<NodeData> append(ArrayList<NodeData> first, ArrayList<NodeData> second) {
        for (int i = 0; i < second.size(); i++) {
            first.add(second.get(i));
        }
        return first;
    }

    private int wanted(HashMap<Integer, Double> list, int curr, ArrayList<Integer> skipped) {
        int minkey = -1;
        double minWeight = Double.MAX_VALUE;
        Nodes currN = (Nodes) this.graph.getNode(curr);
        for (int key : list.keySet()) {
            if (skipped.contains(key)) continue;
            if (key == curr) continue;
            else {
                if (minWeight > currN.getMinDist().get(key)) {
                    minkey = key;
                    minWeight = currN.getMinDist().get(key);
                }
            }
        }
        return minkey;
    }

    private int getClosest(DWG graph, int src) {
        double min = Double.MAX_VALUE;
        int minKey = -1;
        Nodes source = (Nodes) graph.getNode(src);
        for (EdgeData edge : source.getEdgeMap().values()) {
            if (edge.getWeight() < min && graph.getNode(edge.getDest()).getTag() == 0) {
                minKey = edge.getDest();
                min = edge.getWeight();
            }
        }
        return minKey;
    }

    private void update(DWG graph, int src, int dest) {
        Nodes source = (Nodes) graph.getNode(src);
        Nodes destination = (Nodes) graph.getNode(dest);
        for (int key : destination.getEdgeMap().keySet()) {
            if (source.getMinDist().get(key) > destination.getEdgeMap().get(key).getWeight() + source.getMinDist().get(dest)) {
                source.getMinDist().replace(key, destination.getEdgeMap().get(key).getWeight() + source.getMinDist().get(dest));
            }
        }
    }

    private ArrayList[] jsonToGraph(String jsonPath) throws IOException, ParseException, org.json.simple.parser.ParseException {
        ArrayList[] list_graph = new ArrayList[2];
        JSONParser jsonParse = new JSONParser();
        FileReader reader = new FileReader(jsonPath);
        Object obj = jsonParse.parse(reader);
        JSONObject graphJson = (JSONObject) obj;
        ArrayList<Nodes> node_list = new ArrayList<>();
        ArrayList<Edges> edge_list = new ArrayList<>();
        JSONArray Edges_array = (JSONArray) graphJson.get("Edges");
//        for (int i = 0; i < Edges_array.size(); i++) {
            for (Object edge: Edges_array) {
//            JSONObject edgeElement = (JSONObject) Edges_array.get(i);
                JSONObject edgeElement = (JSONObject) edge;
            int srcE = Math.toIntExact((long) edgeElement.get("src"));
            double wE = (double) edgeElement.get("w");
            int destE = Math.toIntExact((long) edgeElement.get("dest"));
            edge_list.add(new Edges(srcE, wE, destE));

        }
        JSONArray Nodes_array = (JSONArray) graphJson.get("Nodes");
//        for (int i = 0; i < Nodes_array.size(); i++) {
        for (Object node : Nodes_array) {
//            JSONObject nodeElement = (JSONObject) Nodes_array.get(i);
            JSONObject nodeElement = (JSONObject) node;
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

    public int[] newDijska(DWG graph, int src) {
        Queue<Nodes> set = new LinkedList<>();
        Nodes source = (Nodes) graph.getNode(src);
        int[] prev = new int[graph.nodeSize()];
        for (Nodes node : graph.getNodeList()) {
            source.getMinDist().put(node.getKey(), Double.MAX_VALUE);
            set.add(node);
        }
        for (int i = 0; i < graph.nodeSize(); i++) {
            prev[i] = -1;
        }
        source.getMinDist().replace(src, 0.0);
        for (int key : source.getEdgeMap().keySet()) {
            source.getMinDist().replace(key, source.getEdgeMap().get(key).getWeight());
        }
        set.remove(source);

        while (!set.isEmpty()) {
            int u = findCLosest(set, src);
            Nodes unode = (Nodes) graph.getNode(u);
            //update(graph,src,u);
            set.remove(unode);
            for (Nodes node : set) {
                int key = node.getKey();
                if (unode.getEdgeMap().containsKey(key) && key != src) {
                    double alt = source.getMinDist().get(u) + unode.getEdgeMap().get(node.getKey()).getWeight();
                    if (alt < source.getMinDist().get(node.getKey())) {
                        prev[node.getKey()] = u;
                        source.getMinDist().replace(node.getKey(), alt);

                    }
                }
            }
        }
//    System.out.println(Arrays.toString(prev));
        return prev;
    }

    private int findCLosest(Queue<Nodes> set, int src) {
        Nodes source = (Nodes) graph.getNode(src);
        Double minDist = Double.MAX_VALUE;
        int minKey = -1;
        Double actualDist = Double.MAX_VALUE;
        for (Nodes node : set) {
            actualDist = source.getMinDist().get(node.getKey());
            if (actualDist < minDist) {
                minDist = actualDist;
                minKey = node.getKey();
            }
        }
        return minKey;
    }

    private void printService(List<NodeData> list, int[] reference, int place) {
        if (reference[place] == -1) ;
        else {
            list.add(this.graph.getNode(reference[place]));
            printService(list, reference, reference[place]);
        }
    }

    private void GraphToJson(DirectedWeightedGraph g, String filename) throws IOException {
        DWG graph = (DWG) g;
        ArrayList<NodeData> nodeList = new ArrayList<>();
        ArrayList<EdgeData> edgeList = new ArrayList<>();
        for (Nodes node : graph.getNodeList()) {
            nodeList.add(node);
            for (EdgeData edge : node.getEdgeMap().values()) {
                edgeList.add(edge);
            }
        }
///////Add Edges to JSON
        JSONArray JEdgeList = new JSONArray();

        for (EdgeData edge : edgeList) {
            JSONObject Jedge = new JSONObject();
            Jedge.put("src", edge.getSrc());
            Jedge.put("w", edge.getWeight());
            Jedge.put("dest", edge.getDest());
            JEdgeList.add(Jedge);
        }
///////Add Nodes to JSON
        JSONArray JNodeList = new JSONArray();

        for (NodeData node : nodeList) {
            JSONObject JNode = new JSONObject();
            JNode.put("pos", "" + node.getLocation().x() + "," + node.getLocation().y() + "," + node.getLocation().z() + "");
            JNode.put("id", node.getKey());
            JNodeList.add(JNode);
        }
///////Add Titles of GRAPH to JSon

        JSONObject Format = new JSONObject();
        Format.put("Edges", JEdgeList);
        Format.put("Nodes", JNodeList);


///////Write to JSON


        try (FileWriter file = new FileWriter(filename)) {
            file.write(Format.toJSONString());
            file.flush();

        }
    }
}



