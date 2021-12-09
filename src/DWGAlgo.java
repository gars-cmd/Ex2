import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.EdgeData;
import api.NodeData;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class DWGAlgo implements DirectedWeightedGraphAlgorithms {

    private DWG graph;

    @Override
    public void init(DirectedWeightedGraph g) {
        this.graph = (DWG) g;
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
        DFS(this.graph, v);
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
        DFS(reverseOne, v);
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

    @Override
    public double shortestPathDist(int src, int dest) {
        Dijkstra(this.graph, src,dest);
        Nodes source = (Nodes) this.getGraph().getNode(src);
        if (source.getMinDist().get(dest)==Double.MAX_VALUE) return -1;
        else return source.getMinDist().get(dest);
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
        return false;
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

    private int[] Dijkstra(DWG graph, int src,int dest) {///////////////////////////////////////////////////////

        double dist[] = new double[graph.nodeSize()];
        int parents[] = new int[graph.nodeSize()];
        ArrayList<Integer> pathList = new ArrayList<>(graph.nodeSize());
        for (int i = 0; i < graph.nodeSize(); i++) {
            dist[i] = Double.MAX_VALUE;
        }

        int count = graph.nodeSize();
        dist[src] = 0;
        graph.initializeTag();
        graph.getNode(src).setTag(1);
        Nodes source = (Nodes) graph.getNode(src);
        for (int key : source.getEdgeMap().keySet()) {
            dist[key] = source.getEdgeMap().get(key).getWeight();
            source.getMinDist().replace(key, source.getEdgeMap().get(key).getWeight());
        }
        for (Nodes node : graph.getNodeList()) {
            source.getMinDist().put(node.getKey(), Double.MAX_VALUE);
        }

        int actual = src;
        source.getMinDist().replace(src, 0.0);
        parents[src]=-1;
        while (count > 0) {
            NodeData actualNode = graph.getNode(actual);
            update(graph, src, actual);
            int v = getClosest(graph, actual);
            if (v == -1) {
                break;
            } else {
                Nodes nextNode = (Nodes) graph.getNode(v);
                nextNode.setTag(1);
                count--;
                for (int key : nextNode.getEdgeMap().keySet()) {
                    int a = key;
                    if (key == src) continue;
                    if (source.getMinDist().get(key) > nextNode.getEdgeMap().get(key).getWeight() + source.getMinDist().get(nextNode.getKey())) {
                        source.getMinDist().replace(key,nextNode.getEdgeMap().get(key).getWeight()+source.getMinDist().get(nextNode.getKey()));
                        parents[key] = v;
                        dist[key] = dist[v] + nextNode.getEdgeMap().get(key).getWeight();
                    }
                }
                actual = v;
            }

        }
        System.out.println(Arrays.toString(parents));
        System.out.println(Arrays.toString(dist));
        return parents;
    }

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////
//        pathList.add(dest);
//        int previous = prev[dest];
//        while (previous!=-1){
//            pathList.add(previous);
//            previous= prev[previous];
//        }
//        System.out.println(pathList.toString());
//        System.out.println(Arrays.toString(prev));
//        return pathList;


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

}
