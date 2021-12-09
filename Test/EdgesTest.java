import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EdgesTest {

    @Test
    void getSrc() {
        Edges e=new Edges(3,10,5);
        assertTrue(e.getSrc()==3);
        assertFalse(e.getSrc()==2);
        Edges e1=new Edges(12,10,8);
        assertTrue(e1.getSrc()==12);
        assertFalse(e1.getSrc()==3);
    }

    @Test
    void getDest() {
        Edges e=new Edges(11,10,5);
        assertTrue(e.getSrc()==11);
        assertFalse(e.getSrc()==4);
        Edges e1=new Edges(28,10,8);
        assertTrue(e1.getSrc()==28);
        assertFalse(e1.getSrc()==3);
    }

    @Test
    void getWeight() {
        Edges e=new Edges(3,10,5);
        assertTrue(e.getWeight()==10);
        assertFalse(e.getWeight()==2);
        Edges e1=new Edges(12,2.4,8);
        assertTrue(e1.getWeight()==2.4);
        assertFalse(e1.getWeight()==3);
    }
}