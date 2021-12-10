import api.NodeData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DWGAlgoTest {


    @Test
    void getGraph() {
        DWGAlgo Algograph = new DWGAlgo();
        Algograph.load("data/G0.json");

        DWG graph = (DWG)Algograph.getGraph();
        Assertions.assertTrue(!graph.getNodeList().isEmpty());

    }

    @Test
    void isConnected() {
        DWGAlgo Algograph = new DWGAlgo();
        Algograph.load("data/G0.json");

        Assertions.assertTrue(Algograph.isConnected());
        Algograph.getGraph().removeNode(3);
        Assertions.assertTrue(!Algograph.isConnected());

    }

    @Test
    void shortestPathDist() {
        DWGAlgo Algograph = new DWGAlgo();
        Algograph.load("data/G0.json");

        double sp0to2 = (Algograph.getGraph().getEdge(0,3).getWeight())+(Algograph.getGraph().getEdge(3,2).getWeight());
        Assertions.assertEquals(Algograph.shortestPathDist(0,2),sp0to2);
        Assertions.assertTrue(Algograph.shortestPathDist(1,3)==Algograph.getGraph().getEdge(1,3).getWeight());

    }

    @Test
    void shortestPath() {
        DWGAlgo Algograph = new DWGAlgo();
        Algograph.load("data/G0.json");

        int path0to2[] ={0,3,2};
        List<NodeData> ans = Algograph.shortestPath(0,2);
        for (int i = 0; i <ans.size() ; i++) {
            Assertions.assertEquals(ans.get(i).getKey(),path0to2[i]);
        }

    }

    @Test
    void center() {
        DWGAlgo AlgographG0 = new DWGAlgo();
        AlgographG0.load("data/G0.json");

        DWGAlgo AlgographG1 = new DWGAlgo();
        AlgographG1.load("data/G1.json");

        DWGAlgo AlgographG2 = new DWGAlgo();
        AlgographG2.load("data/G2.json");

        DWGAlgo AlgographG3 = new DWGAlgo();
        AlgographG3.load("data/G3.json");

        Assertions.assertEquals(AlgographG0.center(),AlgographG0.getGraph().getNode(3));
        Assertions.assertEquals(AlgographG1.center(),AlgographG1.getGraph().getNode(8));
        Assertions.assertEquals(AlgographG2.center(),AlgographG2.getGraph().getNode(0));
        Assertions.assertEquals(AlgographG3.center(),AlgographG3.getGraph().getNode(40));

    }

    @Test
    void tsp() {
    }
}