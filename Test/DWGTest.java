
import api.DirectedWeightedGraph;
import api.EdgeData;
import api.GeoLocation;
import api.NodeData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertTrue;

class DWGTest {


    DWGAlgo testGraph = new DWGAlgo();


    @Test
    void getNode() {
        testGraph.load("data/G1.json");
        DWG graph = (DWG)testGraph.getGraph();


        Point_3D testPoint =  new Point_3D(1,2,0);
        NodeData testNode = new Nodes(testPoint,26);

        graph.addNode(testNode);
        Assertions.assertEquals(graph.getNode(graph.nodeSize()-1).getLocation().x(), testNode.getLocation().x());
        Assertions.assertEquals(graph.getNode(graph.nodeSize()-1).getLocation().y(), testNode.getLocation().y());
        Assertions.assertEquals(graph.getNode(graph.nodeSize()-1).getLocation().z(), testNode.getLocation().z());

        Point_3D testPoint2 = new Point_3D(2,3,0);
        NodeData testNode2 = new Nodes(testPoint2,32);

        graph.addNode(testNode2);
        Assertions.assertEquals(graph.getNode(graph.nodeSize()-1).getLocation().x(), testNode2.getLocation().x());
        Assertions.assertEquals(graph.getNode(graph.nodeSize()-1).getLocation().y(), testNode2.getLocation().y());
        Assertions.assertEquals(graph.getNode(graph.nodeSize()-1).getLocation().z(), testNode2.getLocation().z());


    }

    @Test
    void getEdge() {
        testGraph.load("data/G1.json");
        DWG graph = (DWG)testGraph.getGraph();

        graph.connect(0,3,15);
        graph.connect(2,12,7);
        graph.connect(4,15,8);

        Assertions.assertEquals(graph.getEdge(0,3).getWeight(),15);
        Assertions.assertEquals(graph.getEdge(2,12).getWeight(),7);
        Assertions.assertEquals(graph.getEdge(4,15).getWeight(),8);

    }

    @Test
    void addNode() {
        testGraph.load("data/G1.json");
        DWG graph = (DWG)testGraph.getGraph();


        Point_3D testPoint =  new Point_3D(4,8,2);
        NodeData testNode = new Nodes(testPoint,26);

        graph.addNode(testNode);
        Assertions.assertEquals(graph.getNode(graph.nodeSize()-1).getLocation().x(), testNode.getLocation().x());
        Assertions.assertEquals(graph.getNode(graph.nodeSize()-1).getLocation().y(), testNode.getLocation().y());
        Assertions.assertEquals(graph.getNode(graph.nodeSize()-1).getLocation().z(), testNode.getLocation().z());

        Point_3D testPoint2 = new Point_3D(12,9,3);
        NodeData testNode2 = new Nodes(testPoint2,32);

        graph.addNode(testNode2);
        Assertions.assertEquals(graph.getNode(graph.nodeSize()-1).getLocation().x(), testNode2.getLocation().x());
        Assertions.assertEquals(graph.getNode(graph.nodeSize()-1).getLocation().y(), testNode2.getLocation().y());
        Assertions.assertEquals(graph.getNode(graph.nodeSize()-1).getLocation().z(), testNode2.getLocation().z());
    }

    @Test
    void connect() {

            testGraph.load("data/G1.json");
            DWG graph = (DWG)testGraph.getGraph();
            int nbrEdges = graph.edgeSize();

            graph.connect(0,3,15);
            Assertions.assertEquals(graph.edgeSize(),nbrEdges+1);

            graph.connect(2,12,7);
            Assertions.assertEquals(graph.edgeSize(),nbrEdges+2);

            graph.connect(4,15,8);
             Assertions.assertEquals(graph.edgeSize(),nbrEdges+3);



        }

    @Test
    void edgeIter() {
        testGraph.load("data/G1.json");
        DWG graph = (DWG)testGraph.getGraph();

        ArrayList<int[]> testParameter = new ArrayList<>();
        for (Nodes node : graph.getNodeList()) {
            for (EdgeData edge: node.getEdgeMap().values()) {
                int[] param = {edge.getSrc(),edge.getDest()};
                testParameter.add(param);
            }
        }
        Iterator edgeIt = graph.edgeIter();
        int i =0;
        while (edgeIt.hasNext()){

            EdgeData next = (EdgeData) edgeIt.next();
            Assertions.assertEquals(next.getSrc(),testParameter.get(i)[0]);
            Assertions.assertEquals(next.getDest(),testParameter.get(i)[1]);
        i++;
        }


    }

    @Test
    void removeNode() {
        testGraph.load("data/G1.json");
        DWG graph = (DWG)testGraph.getGraph();


        int nbrNode = graph.nodeSize();
        graph.removeNode(16);
        Assertions.assertEquals(graph.nodeSize(),nbrNode-1);
        graph.removeNode(15);
        Assertions.assertEquals(graph.nodeSize(),nbrNode-2);
        graph.removeNode(14);
        Assertions.assertEquals(graph.nodeSize(),nbrNode-3);

    }

    @Test
    void removeEdge() {
        testGraph.load("data/G1.json");
        DWG graph = (DWG)testGraph.getGraph();


        int nbrEdge = graph.edgeSize();
        graph.removeEdge(0,16);
        Assertions.assertEquals(graph.edgeSize(),nbrEdge-1);
        graph.removeEdge(2,6);
        Assertions.assertEquals(graph.edgeSize(),nbrEdge-2);
        graph.removeEdge(3,4);
        Assertions.assertEquals(graph.edgeSize(),nbrEdge-3);

    }

    @Test
    void nodeSize() {
        testGraph.load("data/G1.json");
        DWG graph = (DWG) testGraph.getGraph();

        Iterator nodeIt = graph.nodeIter();
        int i = 0;
        while (nodeIt.hasNext()) {
            nodeIt.next();
            i++;
        }
        Assertions.assertEquals(graph.nodeSize() , i);
        graph.removeNode(16);
        Assertions.assertEquals(graph.nodeSize(),i-1);
        graph.removeNode(15);
        Assertions.assertEquals(graph.nodeSize(),i-2);
    }

    @Test
    void edgeSize() {
        testGraph.load("data/G1.json");
        DWG graph = (DWG) testGraph.getGraph();

        Iterator edgeIt = graph.edgeIter();
        int i =0;
        while (edgeIt.hasNext()){
            edgeIt.next();
            i++;
        }
        Assertions.assertEquals(graph.edgeSize(),i);
        graph.removeEdge(0,16);
        Assertions.assertEquals(graph.edgeSize(),i-1);
        graph.removeNode(6);
        Assertions.assertEquals(graph.edgeSize(),i-7);
    }
}