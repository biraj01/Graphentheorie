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
import java.util.Arrays;
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

        Node vertexStart = g.getNode("Münster");
        Node vertexEnd = g.getNode("Cuxhaven");
        String [] pathArr = {"Münster","Minden","Hannover","Oldenburg","Cuxhaven"};
        dsp = new DijkstraShortestPath(g, vertexStart.getId(), vertexEnd.getId());
        dsp.init(g);
        dsp.doSearch();
        System.out.println("Vergleich Dijkstra und FloydWarshall für Graph3");
        boolean equal = Arrays.equals(pathArr, dsp.getPath());
        assertEquals(true, equal);
        assertEquals(dsp.getTotalLength(), 481.0);
        System.out.println("########################################################");
    }

    @Test
    public void testCompareDijkstraFloydWeightedGraph2() {
        ReadGraph gf = new ReadGraph();
        File file = new File("C:\\Users\\Biraj\\workspace\\GKA_Praktikum1\\asserts\\graph3b.gka");
        gf.initGraph(file); // initialize the graph
        Graph g = gf.getGraph();
        System.out.println("Vergleich Dijkstra und FloydWarshall für Graph3");
        FloydWarshall fw = new FloydWarshall(g, "Münster", "Cuxhaven");
        fw.updateMatrix();
        fw.outputDistance();
        Arrays.stream(fw.constructPath()).forEach(e->System.out.print(e + "->"));
        String [] pathArr = {"Münster","Minden","Hannover","Oldenburg","Cuxhaven"};
        boolean equals = Arrays.equals(pathArr, fw.constructPath());
        assertEquals(true, equals);

        assertEquals( fw.getPathLength() , 481.0);
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
        String [] pathArr = {"8","63","54","81","88","34","2","40"};
        dsp = new DijkstraShortestPath(g, vertexStart.getId(), vertexEnd.getId());
        dsp.init(g);
        dsp.doSearch();
        System.out.println("Dijkstra für BIG");
        boolean equal = Arrays.equals(pathArr, dsp.getPath());
        assertEquals(true, equal);
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
