package test.Dijkstra;

import algorithm.DijkstraShortestPath;
import application.ReadGraph;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;
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
        String [] pathArr = {"Bremen","Hamburg","Walsrode","Hameln","Detmold"};
        dsp = new DijkstraShortestPath(g, vertexStart.getId(), vertexEnd.getId());
        dsp.init(g);
        dsp.doSearch();
        boolean equal = Arrays.equals(pathArr, dsp.getPath());
        assertEquals(true, equal);
        System.out.println("########################################################");
    }

    @Test
    public void testGetPathNoPath() {
        File file = new File("C:\\Users\\Biraj\\workspace\\GKA_Praktikum1\\asserts\\graph3.gka");
        gf.initGraph(file); // initialize the graph
        Graph g = gf.getGraph();
        Node vertexStart = g.getNode("Bremen");
        Node vertexEnd = g.getNode("Berlin");
        LinkedList<Node> testList = new LinkedList<Node>();
        dsp = new DijkstraShortestPath(g, vertexStart.getId(), vertexEnd.getId());
        dsp.init(g);
        dsp.doSearch();

        dsp.getPath();
        assertEquals(null, dsp.getPath());
        System.out.println("########################################################");
    }

}
