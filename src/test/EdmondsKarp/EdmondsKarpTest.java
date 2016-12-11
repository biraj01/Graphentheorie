package test.EdmondsKarp;

import algorithm.EdmondsKarp;
import application.ReadGraph;
import org.graphstream.graph.Graph;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Marcel on 11.12.2016.
 */
public class EdmondsKarpTest {
    ReadGraph gf;

    @Before
    public void setUp() {
        gf = new ReadGraph();
    }


    @Test
    public void testEqualMaxFlowWithNetwork1() {
        File file = new File("C:\\Users\\Marcel\\Documents\\IntelliJ-Programme\\GKA_Praktikum\\asserts\\networkTest.gka");
        gf.initGraph(file); // initialize the graph
        Graph graph = gf.getGraph();
        EdmondsKarp ek = new EdmondsKarp(graph, "0", "5");

        assertEquals(23.0, ek.getMaximumFlow());
    }

    @Test
    public void testEqualMaxFlowWithNetwork2() {
        File file = new File("C:\\Users\\Marcel\\Documents\\IntelliJ-Programme\\GKA_Praktikum\\asserts\\networkTest2.gka");
        gf.initGraph(file); // initialize the graph
        Graph graph = gf.getGraph();
        EdmondsKarp ek = new EdmondsKarp(graph, "s", "t");

        assertEquals(9.0, ek.getMaximumFlow());
    }

    @Test
    public void testEqualMaxFlowWithNetwork3() {
        File file = new File("C:\\Users\\Marcel\\Documents\\IntelliJ-Programme\\GKA_Praktikum\\asserts\\networkTest3.gka");
        gf.initGraph(file); // initialize the graph
        Graph graph = gf.getGraph();
        EdmondsKarp ek = new EdmondsKarp(graph, "s", "t");

        assertEquals(19.0, ek.getMaximumFlow());
    }

    @Test
    public void testEqualMaxFlowWithBig() {
        File file = new File("C:\\Users\\Marcel\\Documents\\IntelliJ-Programme\\GKA_Praktikum\\asserts\\BigNet_02_MarcelLange_BirajDhungel.gka");
        gf.initGraph(file); // initialize the graph
        Graph graph = gf.getGraph();
        EdmondsKarp ek = new EdmondsKarp(graph, "s", "t");

        assertEquals(12412.0, ek.getMaximumFlow());
    }
}