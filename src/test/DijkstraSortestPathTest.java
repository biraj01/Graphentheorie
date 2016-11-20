package test;

import algorithm.DijkstraSortestPath;
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
public class DijkstraSortestPathTest {
    ReadGraph gf;
    DijkstraSortestPath gps;

    @Before
    public void setUp() {

        gf = new ReadGraph();
        File file = new File("C:\\Users\\Marcel\\Documents\\IntelliJ-Programme\\GKA_Praktikum2\\asserts\\graph10b.gka");
        gf.initGraph(file);
        gf.zeichneGraph(file);
    }

    @Test
    public void testRightOutputDirectedWeightedGraph() {
        gf = new ReadGraph();
        File file = new File("C:\\Users\\Marcel\\Documents\\IntelliJ-Programme\\GKA_Praktikum2\\asserts\\graph10b.gka");
        gf.initGraph(file); // initialize the graph
        Graph g = gf.getGraph();

        Node vertexStart = g.getNode("v4");
        Node vertexEnd = g.getNode("v10");
        LinkedList<Node> testList = new LinkedList<Node>();
        testList.add(g.getNode("v4"));
        testList.add(g.getNode("v3"));
        testList.add(g.getNode("v1"));
        testList.add(g.getNode("v7"));
        testList.add(g.getNode("v8"));
        testList.add(g.getNode("v10"));

        gps = new DijkstraSortestPath(g, vertexStart.getId(), vertexEnd.getId());

        gps.init(g);
        gps.doSearch();

        assertEquals(testList, gps.getPath(vertexEnd));
        try {
            gf.saveGraph(g);
            System.out.println("we did it");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRightOutputUndirectedWeightedGraph() {
        gf = new ReadGraph();
        File file = new File("C:\\Users\\Marcel\\Documents\\IntelliJ-Programme\\GKA_Praktikum2\\asserts\\graph3.gka");
        gf.initGraph(file); // initialize the graph
        Graph g = gf.getGraph();

        Node vertexStart = g.getNode("Bremen");
        Node vertexEnd = g.getNode("Detmold");
        LinkedList<Node> testList = new LinkedList<Node>();
        testList.add(g.getNode("Bremen"));
        testList.add(g.getNode("Hamburg"));
        testList.add(g.getNode("Walsrode"));
        testList.add(g.getNode("Hameln"));
        testList.add(g.getNode("Detmold"));

        gps = new DijkstraSortestPath(g, vertexStart.getId(), vertexEnd.getId());

        gps.init(g);
        gps.doSearch();

        assertEquals(testList, gps.getPath(vertexEnd));
    }
}
