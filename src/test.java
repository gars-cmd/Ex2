import java.util.ArrayList;
import api.*;



public class test {
    public static void main(String[] args) {
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
    }
}
