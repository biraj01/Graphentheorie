package test;

import application.ReadGraph;
import org.graphstream.graph.Graph;
import org.junit.Before;
import org.junit.Test;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;


import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Marcel on 20.11.2016.
 */
public class saveGraphTest {
    ReadGraph gf;


    @Before
    public void setUp() {

    }

    @Test
    public void testSaveDirectedWeightedGraph() throws IOException {
        gf = new ReadGraph();
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
        gf = new ReadGraph();
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
        gf = new ReadGraph();
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
        gf = new ReadGraph();
        File file = new File("C:\\Users\\Marcel\\Documents\\IntelliJ-Programme\\GKA_Praktikum2\\asserts\\graph2.gka");
        gf.initGraph(file); // initialize the graph
        Graph g = gf.getGraph();

        gf.saveGraph(g, "saveUndirectedGraph.gka", "undirected");


        //File newFile = new File("C:\\Users\\Marcel\\Documents\\IntelliJ-Programme\\GKA_Praktikum2\\saveUndirectedGraph.gka");
        //boolean compareResult = FileUtils.contentEquals(file, newFile);
        //System.out.println("Are the files are same? " + compareResult);
        //assertTrue(FileUtils.contentEquals(file, newFile));
    }
}
