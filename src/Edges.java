import api.EdgeData;

public class Edges implements EdgeData {

    private int src;
    private double weight;
    private int dest;
    private String info = null;
    private int tag = 0;

    public Edges(int src, double weight, int dest) {
        this.src = src;
        this.weight = weight;
        this.dest = dest;
    }

    public int getSrc() {
        // TODO Auto-generated method stub
        return this.src;
    }

    @Override
    public int getDest() {
        // TODO Auto-generated method stub
        return this.dest;
    }

    @Override
    public double getWeight() {
        // TODO Auto-generated method stub
        return this.weight;
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
        ans += ("Edges : src=" + this.src + " weight=" + this.weight + " dest=" + this.dest);
        return ans;

    }

}