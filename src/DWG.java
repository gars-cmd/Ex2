import java.util.ArrayList;
import java.util.Iterator;

import api.DirectedWeightedGraph;
import api.EdgeData;
import api.NodeData;

public class DWG implements DirectedWeightedGraph {


    private ArrayList<Nodes> graph = new ArrayList<>();

public DWG(ArrayList<Nodes> graph){
    
    for (int i = 0; i < graph.size(); i++) {
        this.graph.add(new Nodes(graph.get(i)));
    }

}



    @Override
    public NodeData getNode(int key) {
        // TODO Auto-generated method stub
        return this.graph.get(key);
    }

    @Override
    public EdgeData getEdge(int src, int dest) {
        for (int i = 0; i < this.graph.get(src).getEdgeList().size(); i++) {
           if(this.graph.get(src).getEdgeList().get(i).getDest()==dest) return this.graph.get(src).getEdgeList().get(i);
            
        }
        return null;
    }

    @Override
    public void addNode(NodeData n) {
        
        Point_3D new_point = new Point_3D(n.getLocation().x(),n.getLocation().y(),n.getLocation().z());
        Nodes new_Node=new Nodes(new_point,this.graph.size());
        this.graph.add(new_Node);
    
    }

    @Override
    public void connect(int src, int dest, double w) {
        Edges new_edge = new Edges(src, w, dest);
        this.graph.get(src).getEdgeList().add(new_edge);
        
    }

    @Override
    public Iterator<Nodes> nodeIter() {
        // TODO Auto-generated method stub
        
        return null;
    }

    @Override
    public Iterator<Edges> edgeIter() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Iterator<Edges> edgeIter(int node_id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public NodeData removeNode(int key) {
        this.graph.remove(key);
        return null;
    }

    @Override
    public EdgeData removeEdge(int src, int dest) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int nodeSize() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int edgeSize() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getMC() {
        // TODO Auto-generated method stub
        return 0;
    }

    
}
