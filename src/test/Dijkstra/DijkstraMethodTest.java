package test.Dijkstra;

import algorithm.DijkstraShortestPath;
import application.ReadGraph;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Marcel on 20.11.2016.
 */
public class DijkstraMethodTest {
    ReadGraph gf;
    DijkstraShortestPath dsp;

    @Before
    public void setUp() {
        gf = new ReadGraph();
    }

    @Test
    public void testGetPathRightPath() {
        File file = new File("C:\\Users\\Biraj\\workspace\\GKA_Praktikum1\\asserts\\graph3.gka");
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
        dsp = new DijkstraShortestPath(g, vertexStart.getId(), vertexEnd.getId());

        dsp.init(g);
        dsp.doSearch();

        dsp.getPath(vertexEnd);
        assertEquals(testList, dsp.getPath(vertexEnd));
        System.out.println("########################################################");
    }

    @Test
    public void testGetPathNoPath() {
        File file = new File("C:\\Users\\Biraj\\workspace\\GKA_Praktikum1\\asserts\\graph3.gka");
        gf.initGraph(file); // initialize the graph
        Graph g = gf.getGraph();
        Node vertexStart = g.getNode("Bremen");
        Node vertexEnd = g.getNode("Detmold");
        Node nullTest = g.getNode("Berlin");
        LinkedList<Node> testList = new LinkedList<Node>();
        testList.add(g.getNode("Bremen"));
        testList.add(g.getNode("Hamburg"));
        testList.add(g.getNode("Walsrode"));
        testList.add(g.getNode("Hameln"));
        testList.add(g.getNode("Detmold"));
        dsp = new DijkstraShortestPath(g, vertexStart.getId(), vertexEnd.getId());

        dsp.init(g);
        dsp.doSearch();

        dsp.getPath(vertexEnd);
        assertEquals(null, dsp.getPath(nullTest));
        System.out.println("########################################################");
    }

}
