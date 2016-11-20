package test;

import static junit.framework.TestCase.assertEquals;

import java.io.File;

import org.graphstream.graph.Graph;
import org.junit.Before;
import org.junit.Test;

import algorithm.DijkstraSortestPath;
import algorithm.FloydWarshall;
import application.ReadGraph;

public class FloydWarshallTest {
  
  
  ReadGraph gf;
  FloydWarshallTest fw;

  @Before
  public void setUp() {

    gf = new ReadGraph();
    // File file = new
    // File("C:\\Users\\Marcel\\Documents\\IntelliJ-Programme\\GKA_Praktikum2\\asserts\\graph10b.gka");
    File file = new File("C:\\Users\\Biraj\\workspace\\GKA_Praktikum1\\asserts\\graph11.gka");
    gf.initGraph(file);
    gf.zeichneGraph(file);
  }
  
  
  @Test
  public void testRightOutputDirectedWeightedGraph1() {
    ReadGraph gf = new ReadGraph();
    File file = new File("C:\\Users\\Biraj\\workspace\\GKA_Praktikum1\\asserts\\graph11.gka");
    gf.initGraph(file); // initialize the graph
    Graph g = gf.getGraph();
    gf.zeichneGraph(file); // draw the graph
    // FloydWarshall fw = new FloydWarshall(g);
    // fw.updateMatrix();
    // Give all the shortest distance in the matrix
    // fw.output();
    System.out.println();
    // fw.outputTransMatrix(); //hier fehlt nun sinnvoll ausgabe von der
    // transmatrix damit man der Weg zurückverfolgen kann
    double inf = Double.POSITIVE_INFINITY;
    double [][] ergMatrix = {
        {0.0, 5.0,6.0, 8.0, 18.0, 11.0, 12.0},
        {inf, 0.0, 1.0, 3.0, 13.0, 6.0, 7.0},
        {inf, inf, 0.0, 2.0, 12.0, 5.0, 6.0},
        {inf, inf, inf, 0.0, 10.0, 3.0, 4.0},
        {inf, inf, inf, inf, 0.0, inf, inf},
        {inf, inf, inf, inf, inf, 0.0, 1.0},
        {inf, inf, inf, inf, inf, inf, 0.0},
        
    };
    FloydWarshall fw1 = new FloydWarshall(g, "a", "d");
    fw1.updateMatrix();
    fw1.outputDistance();
    fw1.output();
    System.out.println("##############################");
    boolean equal = false;
    for (int i = 0; i < ergMatrix.length; i++) {
      for (int j = 0; j < ergMatrix.length; j++) {
        if(ergMatrix[i][j] == fw1.getDistanzmatrix()[i][j]){
          equal = true;
          System.out.print(ergMatrix[i][j] + " | ");
        }
      };
    }
  
  System.out.println("##############################");  
    fw1.outputTransMatrix();
    assertEquals(true, equal);

  }

}
