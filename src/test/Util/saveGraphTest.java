package test.Util;

import algorithm.BigGenerator;
import application.ReadGraph;
import org.graphstream.algorithm.generator.Generator;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import org.junit.Before;
import org.junit.Test;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Marcel on 20.11.2016.
 */
public class saveGraphTest {
    ReadGraph gf;


    @Before
    public void setUp() {
        gf = new ReadGraph();
    }

    @Test
    public void testSaveDirectedWeightedGraph() throws IOException {

        File file = new File("C:\\Users\\Marcel\\Documents\\IntelliJ-Programme\\GKA_Praktikum2\\asserts\\graph10b.gka");
        gf.initGraph(file); // initialize the graph
        Graph g = gf.getGraph();


        gf.saveGraph(g, "saveDirectedWeightedGraph.gka", "directedWeighted");


        //File newFile = new File("C:\\Users\\Marcel\\Documents\\IntelliJ-Programme\\GKA_Praktikum2\\saveDirectedWeightedGraph.gka");
        //boolean compareResult = FileUtils.contentEquals(file, newFile);
        //System.out.println("Are the files are same? " + compareResult);
        //assertTrue(FileUtils.contentEquals(file, newFile));
    }

    @Test
    public void testSaveDirected() throws IOException {

        File file = new File("C:\\Users\\Marcel\\Documents\\IntelliJ-Programme\\GKA_Praktikum2\\asserts\\graph1.gka");
        gf.initGraph(file); // initialize the graph
        Graph g = gf.getGraph();


        gf.saveGraph(g, "saveDirectedGraph.gka", "directed");


        //File newFile = new File("C:\\Users\\Marcel\\Documents\\IntelliJ-Programme\\GKA_Praktikum2\\saveDirectedGraph.gka");
        //boolean compareResult = FileUtils.contentEquals(file, newFile);
        //System.out.println("Are the files are same? " + compareResult);
        //assertTrue(FileUtils.contentEquals(file, newFile));
    }

    @Test
    public void testSaveUndirectedWeightedGraph() throws IOException {

        File file = new File("C:\\Users\\Marcel\\Documents\\IntelliJ-Programme\\GKA_Praktikum2\\asserts\\graph4.gka");
        gf.initGraph(file); // initialize the graph
        Graph g = gf.getGraph();


        gf.saveGraph(g, "saveUndirectedWeightedGraph.gka", "undirectedWeighted");

        //File newFile = new File("C:\\Users\\Marcel\\Documents\\IntelliJ-Programme\\GKA_Praktikum2\\saveUndirectedWeightedGraph.gka");
        //boolean compareResult = FileUtils.contentEquals(file, newFile);
        //System.out.println("Are the files are same? " + compareResult);
        //assertTrue(FileUtils.contentEquals(file, newFile));
    }

    @Test
    public void testSaveUndirected() throws IOException {

        File file = new File("C:\\Users\\Marcel\\Documents\\IntelliJ-Programme\\GKA_Praktikum2\\asserts\\graph2.gka");
        gf.initGraph(file); // initialize the graph
        Graph g = gf.getGraph();

        gf.saveGraph(g, "saveUndirectedGraph.gka", "undirected");

        //File newFile = new File("C:\\Users\\Marcel\\Documents\\IntelliJ-Programme\\GKA_Praktikum2\\saveUndirectedGraph.gka");
        //boolean compareResult = FileUtils.contentEquals(file, newFile);
        //System.out.println("Are the files are same? " + compareResult);
        //assertTrue(FileUtils.contentEquals(file, newFile));
    }

    @Test
    public void testBig() throws IOException {

        Graph graph = new SingleGraph("random euclidean");
        // dimension, directed?, randomDirection?, threshold, rangeMin, rangeMax, nodes
        BigGenerator bg = new BigGenerator(3, true, true, 0.45, 0, 200, 100);

        bg.addSink(graph);

        bg.initEvents();
        bg.initEdgeWeights(graph);

        bg.end();
        graph.display(false);
        gf.saveGraph(graph, "saveBig.gka", "directedWeighted");

    }
}