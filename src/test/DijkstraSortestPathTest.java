package test;

import algorithm.DijkstraSortestPath;
import algorithm.FloydWarshall;
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
  DijkstraSortestPath dsp;

  @Before
  public void setUp() {

    gf = new ReadGraph();
    // File file = new
    // File("C:\\Users\\Marcel\\Documents\\IntelliJ-Programme\\GKA_Praktikum2\\asserts\\graph10b.gka");
    File file = new File("C:\\Users\\Biraj\\workspace\\GKA_Praktikum1\\asserts\\graph10b.gka");
    gf.initGraph(file);
    gf.zeichneGraph(file);
  }
  
  
  @Test
  public void testRightOutputDirectedWeightedGraph1() {
    gf = new ReadGraph();
    // File file = new
    // File("C:\\Users\\Marcel\\Documents\\IntelliJ-Programme\\GKA_Praktikum2\\asserts\\graph10b.gka");
    File file = new File("C:\\Users\\Biraj\\workspace\\GKA_Praktikum1\\asserts\\graph10b.gka");
    gf.initGraph(file); // initialize the graph
    Graph g = gf.getGraph();

    Node vertexStart = g.getNode("v4");
    Node vertexEnd = g.getNode("v4");
    LinkedList<Node> testList = null;
    //testList.add(g.getNode("v4"));
    
    dsp = new DijkstraSortestPath(g, vertexStart.getId(), vertexEnd.getId());

    dsp.init(g);
    dsp.doSearch();

    assertEquals(testList, dsp.getPath(vertexEnd));
  }
  
  @Test
  public void testRightOutputDirectedWeightedGraph2() {
    gf = new ReadGraph();
    // File file = new
    // File("C:\\Users\\Marcel\\Documents\\IntelliJ-Programme\\GKA_Praktikum2\\asserts\\graph10b.gka");
    File file = new File("C:\\Users\\Biraj\\workspace\\GKA_Praktikum1\\asserts\\graph10b.gka");
    gf.initGraph(file); // initialize the graph
    Graph g = gf.getGraph();

    Node vertexStart = g.getNode("v4");
    Node vertexEnd = g.getNode("v3");
    LinkedList<Node> testList = new LinkedList<>();
    testList.add(g.getNode("v4"));
    testList.add(g.getNode("v3"));
    
    dsp = new DijkstraSortestPath(g, vertexStart.getId(), vertexEnd.getId());

    dsp.init(g);
    dsp.doSearch();

    assertEquals(testList, dsp.getPath(vertexEnd));
    System.out.println("########################################################");

  }



  @Test
  public void testRightOutputDirectedWeightedGraph3() {
    gf = new ReadGraph();
    // File file = new
    // File("C:\\Users\\Marcel\\Documents\\IntelliJ-Programme\\GKA_Praktikum2\\asserts\\graph10b.gka");
    File file = new File("C:\\Users\\Biraj\\workspace\\GKA_Praktikum1\\asserts\\graph10b.gka");
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

    dsp = new DijkstraSortestPath(g, vertexStart.getId(), vertexEnd.getId());

    dsp.init(g);
    dsp.doSearch();
    assertEquals(testList, dsp.getPath(vertexEnd));
    System.out.println("########################################################");
  }
  
  @Test
  public void testRightOutputUndirectedWeightedGraph1() {
    gf = new ReadGraph();
    // File file = new
    // File("C:\\Users\\Marcel\\Documents\\IntelliJ-Programme\\GKA_Praktikum2\\asserts\\graph3.gka");
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

    dsp = new DijkstraSortestPath(g, vertexStart.getId(), vertexEnd.getId());

    dsp.init(g);
    dsp.doSearch();

    assertEquals(testList, dsp.getPath(vertexEnd));
    System.out.println("########################################################");
  }
  
  
  @Test
  public void testRightOutputUndirectedWeightedGraph2() {
    gf = new ReadGraph();
    // File file = new
    // File("C:\\Users\\Marcel\\Documents\\IntelliJ-Programme\\GKA_Praktikum2\\asserts\\graph3.gka");
    File file = new File("C:\\Users\\Biraj\\workspace\\GKA_Praktikum1\\asserts\\graph3.gka");
    gf.initGraph(file); // initialize the graph
    Graph g = gf.getGraph();

    Node vertexStart = g.getNode("Bremen");
    Node vertexEnd = g.getNode("Hamburg");
    LinkedList<Node> testList = new LinkedList<Node>();
    testList.add(g.getNode("Bremen"));
    testList.add(g.getNode("Hamburg"));

    dsp = new DijkstraSortestPath(g, vertexStart.getId(), vertexEnd.getId());

    dsp.init(g);
    dsp.doSearch();

    assertEquals(testList, dsp.getPath(vertexEnd));
    System.out.println("########################################################");
  }
  

  
  
  

  @Test
  public void testCompareDijkstraFloydWeightedGraph1() {
    gf = new ReadGraph();
    File file = new File("C:\\Users\\Biraj\\workspace\\GKA_Praktikum1\\asserts\\graph3b.gka");
    gf.initGraph(file); // initialize the graph
    Graph g = gf.getGraph();
    gf.zeichneGraph(file); // draw the graph

    Node vertexStart = g.getNode("Hannover");
    Node vertexEnd = g.getNode("Paderborn");
    LinkedList<Node> testList = new LinkedList<Node>();
    testList.add(g.getNode("Hannover"));
    testList.add(g.getNode("Oldenburg"));
    testList.add(g.getNode("Cuxhaven"));
    testList.add(g.getNode("Bremen"));
    testList.add(g.getNode("Bremerhaven"));
    testList.add(g.getNode("Rotenburg"));
    testList.add(g.getNode("Uelzen"));
    testList.add(g.getNode("Hameln"));
    testList.add(g.getNode("Paderborn"));

    dsp = new DijkstraSortestPath(g, vertexStart.getId(), vertexEnd.getId());

    dsp.init(g);
    dsp.doSearch();
    System.out.println("Vergleich Dijkstra und FloydWarshall für Graph3");
    assertEquals(testList, dsp.getPath(vertexEnd));
    System.out.println("########################################################");
  }

  @Test
  public void testCompareDijkstraFloydWeightedGraph2() {
    ReadGraph gf = new ReadGraph();
    File file = new File("C:\\Users\\Biraj\\workspace\\GKA_Praktikum1\\asserts\\graph3.gka");
    gf.initGraph(file); // initialize the graph
    Graph g = gf.getGraph();
    System.out.println("Vergleich Dijkstra und FloydWarshall für Graph3");
    FloydWarshall fw = new FloydWarshall(g, "Hannover", "Paderborn");
    fw.updateMatrix();
    // fw.output();
    // fw.outputTransMatrix();
    fw.outputDistance();

     assertEquals( fw.getPathLength() , 946.0);
    System.out.println("########################################################");
  }

}
