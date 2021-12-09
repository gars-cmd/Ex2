import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Point_3DTest {

    @Test
    void setX() {
        Point_3D p=new Point_3D(10,5,5);
        p.setX(5);
        assertTrue(5==p.x());
        p.setX(17);
        assertTrue(17==p.x());
        assertFalse(18==p.x());
    }

    @Test
    void setY() {
        Point_3D p=new Point_3D(10,5,5);
        p.setY(5);
        assertTrue(5==p.y());
        p.setY(17);
        assertTrue(17==p.y());
        assertFalse(p.y()==12);
    }

    @Test
    void setZ() {
        Point_3D p=new Point_3D(10,5,5);
        p.setZ(5);
        assertTrue(5==p.z());
        p.setZ(17);
        assertTrue(17==p.z());
        assertFalse(30==p.z());
    }

    @Test
    void distance() {
        Point_3D p=new Point_3D(10,5,0);
        Point_3D p2=new Point_3D(10,5,0);
        double dist=p2.distance(p);
        assertTrue(0==dist);
        Point_3D p3=new Point_3D(0,0,0);
        Point_3D p4=new Point_3D(10,0,0);
        dist=p4.distance(p3);
        assertTrue(dist==10);

    }
}