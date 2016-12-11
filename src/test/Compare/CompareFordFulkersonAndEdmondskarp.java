package test.Compare;

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
public class CompareFordFulkersonAndEdmondskarp {
    ReadGraph gf;

    @Before
    public void setUp() {
        gf = new ReadGraph();
    }


    @Test
    public void testCompareGraph4() {
        File file = new File("C:\\Users\\Marcel\\Documents\\IntelliJ-Programme\\GKA_Praktikum\\asserts\\graph4.gka");
        gf.initGraph(file); // initialize the graph
        Graph graph = gf.getGraph();

        final long timeStart = System.nanoTime();
        EdmondsKarp ek = new EdmondsKarp(graph, "q", "s");
        ek.findMaxFlow();
        final long timeEnd = System.nanoTime();
        //final long timeStart = System.currentTimeMillis();

        /*
        System.out.println("Laufszeit von Edmonds and Karp: " + (timeEnd - timeStart) + " Nanosek.");

        final long timeStart2 = System.nanoTime();
        FordFulkerson ff = new EdmondsKarp(graph, "q", "s");
        final long timeEnd2 = System.nanoTime();

        System.out.println("Laufszeit von Ford Fulkerson: " + (timeEnd2 - timeStart2) + " Nanosek.");

        long erg = Math.min((timeEnd - timeStart), (timeEnd - timeStart));
        if((timeEnd - timeStart) > (timeEnd2 - timeStart2)){
            System.out.println("\nDer Gewinner ist Ford Fulkerson");
        } else {
            System.out.println("\nDer Gewinner ist Edmonds and Karp");
        }


        assertEquals(ff.getMaximumFlow(), ek.getMaximumFlow());
        assertEquals(25.0, ek.getMaximumFlow());
        assertEquals(25.0, ff.getMaximumFlow());*/
    }

    @Test
    public void testCompareBigNet() {
        File file = new File("C:\\Users\\Marcel\\Documents\\IntelliJ-Programme\\GKA_Praktikum\\asserts\\BigNet_02_MarcelLange_BirajDhungel.gka");
        gf.initGraph(file); // initialize the graph
        Graph graph = gf.getGraph();

        final long timeStart = System.nanoTime();
        EdmondsKarp ek = new EdmondsKarp(graph, "q", "s");
        ek.findMaxFlow();
        final long timeEnd = System.nanoTime();
        //final long timeStart = System.currentTimeMillis();

        /*
        System.out.println("Laufszeit von Edmonds and Karp: " + (timeEnd - timeStart) + " Nanosek.");

        final long timeStart2 = System.nanoTime();
        FordFulkerson ff = new EdmondsKarp(graph, "q", "s");
        final long timeEnd2 = System.nanoTime();

        System.out.println("Laufszeit von Ford Fulkerson: " + (timeEnd2 - timeStart2) + " Nanosek.");

        long erg = Math.min((timeEnd - timeStart), (timeEnd - timeStart));
        if((timeEnd - timeStart) > (timeEnd2 - timeStart2)){
            System.out.println("\nDer Gewinner ist Ford Fulkerson");
        } else {
            System.out.println("\nDer Gewinner ist Edmonds and Karp");
        }


        assertEquals(ff.getMaximumFlow(), ek.getMaximumFlow());*/
    }
}
