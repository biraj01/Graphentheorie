package test.FloydWarshall;

import static junit.framework.TestCase.assertEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.graphstream.graph.Graph;
import org.junit.Before;
import org.junit.Test;

import algorithm.FloydWarshall;
import application.ReadGraph;

public class FloydWarshallTest {

    ReadGraph gf;
    FloydWarshallTest fw;

    @Before
    public void setUp() {

        gf = new ReadGraph();
        //File file = new File("C:\\Users\\Marcel\\Documents\\IntelliJ-Programme\\GKA_Praktikum2\\asserts\\graph11.gka");
        File file = new File("C:\\Users\\Biraj\\workspace\\GKA_Praktikum1\\asserts\\graph11.gka");
        gf.initGraph(file);
        gf.zeichneGraph(file);
    }

    @Test
    public void testDirectedWeightedGraph2() {
        ReadGraph gf = new ReadGraph();
        File file = new File("C:\\Users\\Biraj\\workspace\\GKA_Praktikum1\\asserts\\graph10c.gka");
        gf.initGraph(file); // initialize the graph
        Graph g = gf.getGraph();
        gf.zeichneGraph(file); // draw the graph
        System.out.println();
        double inf = Double.POSITIVE_INFINITY;
        double[][] ergMatrix = { { 0.0, 4.0, 2.0, 3.0 }, { 0.0, 4.0, 2.0, 3.0 }, { 0.0, 4.0, 2.0, 3.0 },
                { 0.0, 4.0, 2.0, 3.0 } };
        FloydWarshall fw1 = new FloydWarshall(g, "a", "c");
        fw1.updateMatrix();
        fw1.outputDistance();
        fw1.output();
        System.out.println("##############################");
        Arrays.stream(fw1.constructPath()).forEach(e->System.out.print(e + "->"));
        String [] testArr  = {"a","c"};
        boolean equals = Arrays.equals(testArr, fw1.constructPath());
        assertEquals(true, equals);
        System.out.println();
        boolean equal = false;
        for (int i = 0; i < ergMatrix.length; i++) {
            for (int j = 0; j < ergMatrix.length; j++) {
                if (ergMatrix[i][j] == fw1.getDistanzmatrix()[i][j]) {
                    equal = true;
                }
            }
        }

        fw1.outputTransMatrix();
        assertEquals(true, equal);

    }

    @Test
    public void testDirectedWeightedGraph1() {
        ReadGraph gf = new ReadGraph();
        File file = new File("C:\\Users\\Biraj\\workspace\\GKA_Praktikum1\\asserts\\graph11.gka");
        gf.initGraph(file); // initialize the graph
        Graph g = gf.getGraph();
        gf.zeichneGraph(file); // draw the graph
        System.out.println();
        double inf = Double.POSITIVE_INFINITY;
        double[][] ergMatrix = { { 0.0, 5.0, 6.0, 8.0, 18.0, 11.0, 12.0 }, { inf, 0.0, 1.0, 3.0, 13.0, 6.0, 7.0 },
                { inf, inf, 0.0, 2.0, 12.0, 5.0, 6.0 }, { inf, inf, inf, 0.0, 10.0, 3.0, 4.0 },
                { inf, inf, inf, inf, 0.0, inf, inf }, { inf, inf, inf, inf, inf, 0.0, 1.0 },
                { inf, inf, inf, inf, inf, inf, 0.0 },

        };
        FloydWarshall fw1 = new FloydWarshall(g, "a", "d");
        fw1.updateMatrix();
        fw1.outputDistance();
        fw1.output();
        Arrays.stream(fw1.constructPath()).forEach(e->System.out.print(e + "->"));
        String [] testArr  = {"a", "b","c","e" ,"d"};
        boolean equals = Arrays.equals(testArr, fw1.constructPath());
        assertEquals(true, equals);
        System.out.println("##############################");
        boolean equal = false;
        System.out.println();
        for (int i = 0; i < ergMatrix.length; i++) {
            for (int j = 0; j < ergMatrix.length; j++) {
                if (ergMatrix[i][j] == fw1.getDistanzmatrix()[i][j]) {
                    equal = true;
                }
            }
        }

        System.out.println("##############################");
        fw1.outputTransMatrix();
        assertEquals(true, equal);
        double epsilon = 0000001;
        boolean lengthEqual = false;
        if (Math.abs(fw1.getPathLength() - 11.0) < epsilon){
          lengthEqual = true;
        }
        assertEquals(true,lengthEqual);
     //   assertEquals(11.0, fw1.getPathLength());

    }

}