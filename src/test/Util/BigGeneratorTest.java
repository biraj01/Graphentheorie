package test.Util;

import algorithm.BigGenerator;
import application.ReadGraph;
import org.graphstream.graph.Graph;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;


public class BigGeneratorTest {
    ReadGraph gf;


    @Before
    public void setUp() {
        gf = new ReadGraph();
    }

    @Test
    public void testBigGeneratorDirected() throws IOException {
        Graph graph;
        BigGenerator tb = new BigGenerator(10, 250, true);

        graph = tb.getGraph();

        graph.display(false);
        gf.saveGraph(graph, "saveBigDirected.gka", "directed");
    }

    @Test
    public void testBigGeneratorDirectedWeighted() throws IOException {
        Graph graph;
        BigGenerator tb = new BigGenerator(10, 250, true);

        graph = tb.getGraph();

        graph.display(false);
        gf.saveGraph(graph, "saveBigDirectedWeighted.gka", "directedWeighted");
    }

    @Test
    public void testBigGeneratorUndirected() throws IOException {
        Graph graph;
        BigGenerator tb = new BigGenerator(10, 250);

        graph = tb.getGraph();

        graph.display(false);
        gf.saveGraph(graph, "saveBigUndirected.gka", "undirected");
    }

    @Test
    public void testBigGeneratorUndirectedWeighted() throws IOException {
        Graph graph;
        BigGenerator tb = new BigGenerator(10, 250, 0, 100);

        graph = tb.getGraph();

        graph.display(false);
        gf.saveGraph(graph, "saveBigUndirectedWeighted.gka", "undirectedWeighted");
    }

}
