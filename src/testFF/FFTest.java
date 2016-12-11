package testFF;

import static junit.framework.TestCase.assertEquals;

import java.io.File;

import org.graphstream.graph.Graph;
import org.junit.Before;
import org.junit.Test;
import algorithm.FF;
import application.ReadGraph;

public class FFTest {
  
  ReadGraph gf;

  @Before
  public void setUp() {
      gf = new ReadGraph();
  }


  @Test
  public void testEqualMaxFlowWithNetwork1() {
      File file = new File("C:\\Users\\Biraj\\workspace\\GKA_Praktikum1\\asserts\\networkTest.gka");
      gf.initGraph(file); // initialize the graph
      Graph graph = gf.getGraph();
      FF ff = new FF(graph, "0", "5");
      System.out.println("max flow ist:" + ff.getMaximumFlow());
      assertEquals(23.0, ff.getMaximumFlow());
  }

  @Test
  public void testEqualMaxFlowWithNetwork2() {
      File file = new File("C:\\Users\\Biraj\\workspace\\GKA_Praktikum1\\asserts\\networkTest3.gka");
      gf.initGraph(file); // initialize the graph
      Graph graph = gf.getGraph();
      FF ff = new FF(graph, "s", "t");
      System.out.println("max flow ist:" + ff.getMaximumFlow());
      assertEquals(19.0, ff.getMaximumFlow());
  }

  @Test
  public void testEqualMaxFlowWithNetwork3() {
      File file = new File("C:\\Users\\Biraj\\workspace\\GKA_Praktikum1\\asserts\\networkTest3.gka");
      gf.initGraph(file); // initialize the graph
      Graph graph = gf.getGraph();
      FF ff = new FF(graph, "s", "v4");

      assertEquals(17.0, ff.getMaximumFlow());
  }

  

}
