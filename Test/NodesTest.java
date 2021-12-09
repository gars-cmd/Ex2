import static org.junit.jupiter.api.Assertions.*;

class NodesTest {

    @org.junit.jupiter.api.Test
    void getEdgeMap() {

    }

    @org.junit.jupiter.api.Test
    void getEdgeListToMe() {
    }

    @org.junit.jupiter.api.Test
    void getKey() {
        Point_3D p=new Point_3D(5,5,5);
        Nodes temp=new Nodes(p,5);
        assertTrue(temp.getKey()==5);
    }

    @org.junit.jupiter.api.Test
    void getLocation() {
        Point_3D p=new Point_3D(5,5,5);
        Nodes temp=new Nodes(p,5);
        assertTrue(temp.getLocation()==p);
    }

    @org.junit.jupiter.api.Test
    void setLocation() {
        Point_3D p=new Point_3D(5,5,5);
        Nodes temp=new Nodes(p,5);
        Point_3D p1=new Point_3D(10,10,10);
        temp.setLocation(p1);
        assertTrue(temp.getLocation().distance(p1)==0);
    }



    @org.junit.jupiter.api.Test
    void getTag() {
        Point_3D p=new Point_3D(5,5,5);
        Nodes temp=new Nodes(p,5);
        temp.setTag(10);
        assertTrue(temp.getTag()==10);
        assertFalse(temp.getTag()==7);

    }




    @org.junit.jupiter.api.Test
    void getMinDist() {

    }
}