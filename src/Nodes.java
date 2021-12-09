import java.util.ArrayList;
import java.util.HashMap;

import api.EdgeData;
import api.GeoLocation;
import api.NodeData;

public class Nodes implements NodeData {

    private Point_3D point;
    private int id;
    private double weight = 0;
    private String info = null;
    private int tag = 0;
    //private ArrayList<Edges> edgeList = new ArrayList<>(0);
    private HashMap<Integer , EdgeData> edgeMap = new HashMap<>();
    private ArrayList<Edges> edgeListtoMe = new ArrayList<>();
    private HashMap<Integer , Double> minDistMap = new HashMap<>();

    public Nodes(Point_3D point, int id) {
        // this.point.setX(point.x());
        // this.point.setY(point.y());
        // this.point.setZ(point.z());
        this.point = point;
        this.id = id;

    }

    public Nodes(Nodes other) {
        this.point = new Point_3D(other.getLocation());
        // this.edgeList = new ArrayList<>();
        this.edgeMap = new HashMap<>();

        // for (int i = 0; i < other.edgeList.size(); i++) {
        //     this.edgeList.add(other.edgeList.get(i));
        // }
        this.edgeMap = other.edgeMap;
        this.id = other.getKey();

    }

    // public ArrayList<Edges> getEdgeList() {
    //     return this.edgeList;
    // }

    public HashMap<Integer,EdgeData> getEdgeMap(){
        return this.edgeMap;
    }

    public ArrayList<Edges> getEdgeListToMe() {
        return this.edgeListtoMe;
    }

    @Override
    public int getKey() {
        // TODO Auto-generated method stub

        return this.id;
    }

    @Override
    public Point_3D getLocation() {
        // TODO Auto-generated method stub
        return this.point;
    }

    @Override
    public void setLocation(GeoLocation p) {
        // TODO Auto-generated method stub
        this.point.setX(p.x());
        this.point.setY(p.y());
        this.point.setZ(p.z());

    }

    @Override
    public double getWeight() {
        // TODO Auto-generated method stub
        return this.weight;
    }

    @Override
    public void setWeight(double w) {
        // TODO Auto-generated method stub
        this.weight = w;

    }

    @Override
    public String getInfo() {
        // TODO Auto-generated method stub
        return this.info;
    }

    @Override
    public void setInfo(String s) {
        // TODO Auto-generated method stub
        this.info = s;

    }

    @Override
    public int getTag() {
        // TODO Auto-generated method stub
        return this.tag;
    }

    @Override
    public void setTag(int t) {
        // TODO Auto-generated method stub
        this.tag = t;
    }

    public String toString() {
        String ans = "";
        ans += ("Nodes : x=" + this.point.x() + " y=" + this.point.y() + " z=" + this.point.z() + " id="
                + this.getKey());
        return ans;
    }
    public HashMap<Integer,Double> getMinDist(){
        return this.minDistMap;
    }

}
