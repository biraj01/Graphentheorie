package test.Compare;

import algorithm.DijkstraShortestPath;
import algorithm.FloydWarshall;
import application.ReadGraph;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Marcel on 20.11.2016.
 */
public class CompareAlgorithmTest {
    ReadGraph gf;
    DijkstraShortestPath dsp;

    @Before
    public void setUp() {

        gf = new ReadGraph();
        File file = new File("C:\\Users\\Biraj\\workspace\\GKA_Praktikum1\\asserts\\graph3b.gka");
        gf.initGraph(file);
        gf.zeichneGraph(file);
    }

    @Test
    public void testCompareDijkstraFloydWeightedGraph1() {
        gf = new ReadGraph();
        File file = new File("C:\\Users\\Biraj\\workspace\\GKA_Praktikum1\\asserts\\graph3b.gka");
        gf.initGraph(file); // initialize the graph
        Graph g = gf.getGraph();
        gf.zeichneGraph(file); // draw the graph

        Node vertexStart = g.getNode("Hannover");
        Node vertexEnd = g.getNode("Paderborn");
        LinkedList<Node> testList = new LinkedList<Node>();
        testList.add(g.getNode("Hannover"));
        testList.add(g.getNode("Oldenburg"));
        testList.add(g.getNode("Cuxhaven"));
        testList.add(g.getNode("Bremen"));
        testList.add(g.getNode("Bremerhaven"));
        testList.add(g.getNode("Rotenburg"));
        testList.add(g.getNode("Uelzen"));
        testList.add(g.getNode("Hameln"));
        testList.add(g.getNode("Paderborn"));

        dsp = new DijkstraShortestPath(g, vertexStart.getId(), vertexEnd.getId());

        dsp.init(g);
        dsp.doSearch();
        System.out.println("Vergleich Dijkstra und FloydWarshall für Graph3");
        assertEquals(testList, dsp.getPath(vertexEnd));
        assertEquals(dsp.getTotalLength(), 946.0);
        System.out.println("########################################################");
    }

    @Test
    public void testCompareDijkstraFloydWeightedGraph2() {
        ReadGraph gf = new ReadGraph();
        File file = new File("C:\\Users\\Biraj\\workspace\\GKA_Praktikum1\\asserts\\graph3b.gka");
        gf.initGraph(file); // initialize the graph
        Graph g = gf.getGraph();
        System.out.println("Vergleich Dijkstra und FloydWarshall für Graph3");
        FloydWarshall fw = new FloydWarshall(g, "Hannover", "Paderborn");
        fw.updateMatrix();
        // fw.output();
        // fw.outputTransMatrix();
        fw.outputDistance();

        assertEquals( fw.getPathLength() , 946.0);
        System.out.println("########################################################");
    }

    @Test
    public void testBigGraphCompareDijkstraAndFloydWarshallTest() throws IOException {
        DijkstraShortestPath dsp;

        File file = new File("C:\\Users\\Biraj\\Desktop\\GKA_Praktikum\\GKA_Praktikum2\\asserts\\BIG.gka");
        gf.initGraph(file); // initialize the graph
        Graph g = gf.getGraph();


        Node vertexStart = g.getNode("8");
        Node vertexEnd = g.getNode("40");
        LinkedList<Node> testList = new LinkedList<Node>();
        testList.add(g.getNode("8"));
        testList.add(g.getNode("63"));
        testList.add(g.getNode("54"));
        testList.add(g.getNode("81"));
        testList.add(g.getNode("88"));
        testList.add(g.getNode("34"));
        testList.add(g.getNode("2"));
        testList.add(g.getNode("40"));

        dsp = new DijkstraShortestPath(g, vertexStart.getId(), vertexEnd.getId());

        dsp.init(g);
        dsp.doSearch();
        System.out.println("Dijkstra für BIG");
        assertEquals(testList, dsp.getPath(vertexEnd));
        System.out.println("########################################################");


        System.out.println("FloydWarshall für BIG");
        FloydWarshall fw = new FloydWarshall(g, "8", "40");
        fw.updateMatrix();
        fw.outputDistance();

        assertEquals(75.0, fw.getPathLength());
        System.out.println("########################################################");
        assertEquals(dsp.getTotalLength(), fw.getPathLength());
    }

}
