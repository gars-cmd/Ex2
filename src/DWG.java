import java.util.ArrayList;
import java.util.Iterator;


import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.EdgeData;
import api.NodeData;

public class DWG implements DirectedWeightedGraph {

    private ArrayList<Nodes> graph = new ArrayList<>();
    private int nbrNodes = 0;
    private int nbrEdges = 0;
    // private ArrayList<ArrayList<Integer>> destList = new ArrayList<>();

    public DWG(ArrayList<Nodes> nodes, ArrayList<Edges> edges) { // another way to create a graph with two list nodes,
                                                                 // edges to handle easily the read from json
        for (Nodes node : nodes) {
            this.addNode(node);
        }
        for (Edges edge : edges) {
            this.connect(edge.getSrc(), edge.getDest(), edge.getWeight());
        }
    }

    public DWG(ArrayList<Nodes> nodeList){
        for (Nodes node: nodeList) {
            this.addNode(node);
        }
    }

    @Override
    public NodeData getNode(int key) {
        // TODO Auto-generated method stub
        return this.graph.get(key);
    }

    @Override
    public EdgeData getEdge(int src, int dest) {
        // for (int i = 0; i < this.graph.get(src).getEdgeList().size(); i++) {
        //     if (this.graph.get(src).getEdgeList().get(i).getDest() == dest)
        //         return this.graph.get(src).getEdgeList().get(i);

        return this.graph.get(src).getEdgeMap().get(dest);
        }
        

    @Override
    public void addNode(NodeData n) {

        Point_3D new_point = new Point_3D(n.getLocation().x(), n.getLocation().y(), n.getLocation().z());
        Nodes new_Node = new Nodes(new_point, this.graph.size());
        this.graph.add(new_Node);
        // ArrayList<Integer> curr = new ArrayList();
        // destList.add(curr);
        nbrNodes++;

    }

    @Override
    public void connect(int src, int dest, double w) {
        Edges new_edge = new Edges(src, w, dest);
        // this.graph.get(src).getEdgeList().add(new_edge);
        this.graph.get(src).getEdgeMap().put(dest, new_edge);
        // destList.get(dest).add(src);
        this.graph.get(dest).getEdgeListToMe().add(new_edge);
        nbrEdges++;
    }

    @Override
    public Iterator<NodeData> nodeIter() {
        Iterator dWGIterator = this.graph.iterator();
        return dWGIterator;
    }

    @Override
    public Iterator<EdgeData> edgeIter() {
        ArrayList<EdgeData> edgesList = new ArrayList<>();
        for (int i = 0; i < this.graph.size(); i++) {
            while (edgeIter(i).hasNext()) {
                EdgeData e = edgeIter().next();
                edgesList.add(e);
            }
        }
        Iterator edgesIterator = edgesList.iterator();
        return edgesIterator;
    }

    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {
        // Iterator nodeIterator = this.graph.get(node_id).getEdgeList().iterator();
        
        return this.graph.get(node_id).getEdgeMap().values().iterator();
    }

    @Override
    public NodeData removeNode(int key) {

        for (int i = 0; i < this.graph.get(key).getEdgeListToMe().size(); i++) {
            int src = this.graph.get(key).getEdgeListToMe().get(i).getSrc();
            this.graph.get(src).getEdgeMap().remove(key);
            nbrEdges--;
        }
        this.graph.remove(key);
        for (EdgeData curr : this.graph.get(key).getEdgeMap().values()) {
            nbrEdges--;
        }
        this.graph.remove(key);
    //    for (int i = 0; i < destList.get(key).size(); i++) {
    //        this.graph.get(destList.get(key).get(i)).getEdgeList().remove()
           
    //    } 
        nbrNodes--;
        
        return null;
    }

    @Override
    public EdgeData removeEdge(int src, int dest) {
        // for (int i = 0; i < this.graph.get(src).getEdgeList().size(); i++) {
        //     if (this.graph.get(src).getEdgeList().get(i).getDest() == dest) {
        //         this.graph.get(src).getEdgeList().remove(i);
        this.graph.get(src).getEdgeMap().remove(dest);
                nbrEdges--;
            //}
        //}
        return null;
    }

    @Override
    public int nodeSize() {
        return nbrNodes;


    }

    @Override
    public int edgeSize() {
        return nbrEdges;

    }

    @Override
    public int getMC() {
        // TODO Auto-generated method stub
        return 0;
    }

    public void initializeTag() {
        for (int i = 0; i < this.nodeSize(); i++) {
            NodeData curr = this.getNode(i);
            curr.setTag(0);
        }
    }

    public ArrayList<Nodes> getNodeList() {
        return this.graph;
    }
}
