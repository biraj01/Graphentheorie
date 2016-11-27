package test.Dijkstra;

import algorithm.DijkstraShortestPath;
import application.ReadGraph;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Marcel & Biraj on 20.11.2016.
 */
public class DijkstraShortestPathTest {
    ReadGraph gf;
    DijkstraShortestPath dsp;

    @Before
    public void setUp() {

        gf = new ReadGraph();
        // File file = new
        // File("C:\\Users\\Marcel\\Documents\\IntelliJ-Programme\\GKA_Praktikum2\\asserts\\graph10b.gka");
        File file = new File("C:\\Users\\Biraj\\workspace\\GKA_Praktikum1\\asserts\\graph10b.gka");
        gf.initGraph(file);
        gf.zeichneGraph(file);
    }


    @Test
    public void testRightOutputDirectedWeightedGraph1() {
        gf = new ReadGraph();
        // File file = new
        // File("C:\\Users\\Marcel\\Documents\\IntelliJ-Programme\\GKA_Praktikum2\\asserts\\graph10b.gka");
        File file = new File("C:\\Users\\Biraj\\workspace\\GKA_Praktikum1\\asserts\\graph10b.gka");
        gf.initGraph(file); // initialize the graph
        Graph g = gf.getGraph();

        Node vertexStart = g.getNode("v4");
        Node vertexEnd = g.getNode("v4");
        String [] pathArr = null;
        //testList.add(g.getNode("v4"));

        dsp = new DijkstraShortestPath(g, vertexStart.getId(), vertexEnd.getId());

        dsp.init(g);
        dsp.doSearch();

        assertEquals(pathArr, dsp.getPath());
    }

    @Test
    public void testRightOutputDirectedWeightedGraph2() {
        gf = new ReadGraph();
        // File file = new
        // File("C:\\Users\\Marcel\\Documents\\IntelliJ-Programme\\GKA_Praktikum2\\asserts\\graph10b.gka");
        File file = new File("C:\\Users\\Biraj\\workspace\\GKA_Praktikum1\\asserts\\graph10b.gka");
        gf.initGraph(file); // initialize the graph
        Graph g = gf.getGraph();

        Node vertexStart = g.getNode("v4");
        Node vertexEnd = g.getNode("v3");
        String [] pathArr = {"v4", "v3"};
        dsp = new DijkstraShortestPath(g, vertexStart.getId(), vertexEnd.getId());
        dsp.init(g);
        dsp.doSearch();
        boolean equal = Arrays.equals(pathArr, dsp.getPath());
        assertEquals(true, equal);
        assertEquals(dsp.getTotalLength(),2.0);
        System.out.println("########################################################");

    }



    @Test
    public void testRightOutputDirectedWeightedGraph3() {
        gf = new ReadGraph();
        // File file = new
        // File("C:\\Users\\Marcel\\Documents\\IntelliJ-Programme\\GKA_Praktikum2\\asserts\\graph10b.gka");
        File file = new File("C:\\Users\\Biraj\\workspace\\GKA_Praktikum1\\asserts\\graph10b.gka");
        gf.initGraph(file); // initialize the graph
        Graph g = gf.getGraph();

        Node vertexStart = g.getNode("v4");
        Node vertexEnd = g.getNode("v10");
        String [] pathArr = {"v4", "v3","v1","v7","v8","v10"};
        dsp = new DijkstraShortestPath(g, vertexStart.getId(), vertexEnd.getId());

        dsp.init(g);
        dsp.doSearch();
        boolean equal = Arrays.equals(pathArr, dsp.getPath());
        assertEquals(true, equal);
        assertEquals(dsp.getTotalLength(), 28.0);
        System.out.println("########################################################");
    }

    @Test
    public void testRightOutputUndirectedWeightedGraph1() {
        gf = new ReadGraph();
        // File file = new
        // File("C:\\Users\\Marcel\\Documents\\IntelliJ-Programme\\GKA_Praktikum2\\asserts\\graph3.gka");
        File file = new File("C:\\Users\\Biraj\\workspace\\GKA_Praktikum1\\asserts\\graph3.gka");
        gf.initGraph(file); // initialize the graph
        Graph g = gf.getGraph();

        Node vertexStart = g.getNode("Bremen");
        Node vertexEnd = g.getNode("Detmold");
        String [] pathArr = {"Bremen", "Hamburg","Walsrode","Hameln","Detmold"};
        dsp = new DijkstraShortestPath(g, vertexStart.getId(), vertexEnd.getId());
        dsp.init(g);
        dsp.doSearch();
        boolean equal = Arrays.equals(pathArr, dsp.getPath());
        assertEquals(true, equal);
        assertEquals(dsp.getTotalLength(), 389.0);
        System.out.println("########################################################");
    }


    @Test
    public void testRightOutputUndirectedWeightedGraph2() {
        gf = new ReadGraph();
        // File file = new
        // File("C:\\Users\\Marcel\\Documents\\IntelliJ-Programme\\GKA_Praktikum2\\asserts\\graph3.gka");
        File file = new File("C:\\Users\\Biraj\\workspace\\GKA_Praktikum1\\asserts\\graph3.gka");
        gf.initGraph(file); // initialize the graph
        Graph g = gf.getGraph();

        Node vertexStart = g.getNode("Bremen");
        Node vertexEnd = g.getNode("Hamburg");
        String [] pathArr = {"Bremen", "Hamburg"};
        dsp = new DijkstraShortestPath(g, vertexStart.getId(), vertexEnd.getId());
        dsp.init(g);
        dsp.doSearch();
        boolean equal = Arrays.equals(pathArr, dsp.getPath());
        assertEquals(true, equal);
        assertEquals(dsp.getTotalLength(), 127.0);
        System.out.println("########################################################");
    }

}