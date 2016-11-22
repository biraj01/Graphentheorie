package test.BFS;

import algorithm.BreadthFirst;
import application.ReadGraph;

import org.graphstream.graph.Graph;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

/**
 * Created by Marcel on 23.10.2016.
 */
public class BreadthFirstSearchTest {

	ReadGraph gf;
	BreadthFirst bfs;

	@Before
	public void setUp() {

		gf = new ReadGraph();
		File file = new File("C:\\Users\\Biraj\\workspace\\GKA_Praktikum1\\asserts\\graph3.gka");
		gf.initGraph(file);
		gf.zeichneGraph(file);
	}

	@Test
    public void testRightOutputDirectedGraph() {
    	 gf = new ReadGraph();
         File file = new File("C:\\Users\\Biraj\\workspace\\GKA_Praktikum1\\asserts\\graph1.gka");
         gf.initGraph(file);
         gf.zeichneGraph(file);

    	Graph graph = gf.getGraph();
    	
        bfs = new BreadthFirst(graph, "a", "j");
        assertEquals(
                "Der Kurzeste Weg von a nach j ist:\n" +
                        "a --> b --> j\n" +
                        "Über 2 Kante(n).", bfs.doSearch());
   
        }

	@Test
	public void testRightOutputUndirectedWeightedGraph() {
		gf = new ReadGraph();
		File file = new File("C:\\Users\\Biraj\\workspace\\GKA_Praktikum1\\asserts\\graph12.gka");
		gf.initGraph(file);
		gf.zeichneGraph(file);

		Graph graph = gf.getGraph();

		bfs = new BreadthFirst(graph, "a", "g");
		assertEquals(
				"Der Kurzeste Weg von a nach g ist:\n" +
						"a --> b --> c --> e --> d --> g\n" +
						"Über 5 Kante(n).", bfs.doSearch());

	}

	@Test
	public void testRightOutputUndirectedGraph() {
		gf = new ReadGraph();
		File file = new File("C:\\Users\\Biraj\\workspace\\GKA_Praktikum1\\asserts\\graph2.gka");
		gf.initGraph(file);
		gf.zeichneGraph(file);

		Graph graph = gf.getGraph();

		bfs = new BreadthFirst(graph, "a", "f");
		assertEquals(
				"Der Kurzeste Weg von a nach f ist:\n" +
						"a --> e --> f\n" +
						"Über 2 Kante(n).", bfs.doSearch());

	}

	@Test
	public void testRightOutputDirectedWeightedGraph() {
		gf = new ReadGraph();
		File file = new File("C:\\Users\\Biraj\\workspace\\GKA_Praktikum1\\asserts\\graph11.gka");
		gf.initGraph(file);
		gf.zeichneGraph(file);

		Graph graph = gf.getGraph();

		bfs = new BreadthFirst(graph, "a", "g");
		assertEquals(
				"Der Kurzeste Weg von a nach g ist:\n" +
						"a --> b --> c --> e --> d --> g\n" +
						"Über 5 Kante(n).", bfs.doSearch());

	}

}
