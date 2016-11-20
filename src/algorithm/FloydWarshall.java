package algorithm;

import java.io.File;
import java.util.Iterator;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

import application.ReadGraph;

public class FloydWarshall {

	private double[][] distanzmatrix;
	private String[][] transismatrix;
	private Graph g;
	private boolean hasNegativeCycle;
	private Node start;
	private Node end;

	public FloydWarshall(Graph g) {
		this.g = g;
		initMatrix();

	}
	public FloydWarshall(Graph g, String start, String end) {
    this.g = g;
    initMatrix();
    this.start = g.getNode(start);
    this.end= g.getNode(end);;

  }

	private void initMatrix() {
		int size = g.getNodeSet().size();
		this.distanzmatrix = new double[size][size];
		this.transismatrix = new String[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (i == j) {
					distanzmatrix[i][j] = 0;
				} else {
					distanzmatrix[i][j] = Double.POSITIVE_INFINITY;
				}
			}
		}
		// initialise diagonal to zero and other to infinitive
		System.out.println("initialise diagonal to zero and other to infinitive");
		output();
		System.out.println();
		for (int i = 0; i < size; i++) {
			Node n = g.getNode(i);
			for (Edge e : n.getEnteringEdgeSet()) {
				distanzmatrix[e.getSourceNode().getIndex()][e.getTargetNode().getIndex()] = e
						.getAttribute("edgeLength");
			}

		}
		// Initialise other direct distance in the matrix
		System.out.println("Initialise other direct distance in the matrix");
		output();
		System.out.println();
	}

	private void updateMatrix() {
		for (int i = 0; i < g.getNodeSet().size(); i++) {
			for (int v = 0; v < g.getNodeSet().size(); v++) {
				for (int w = 0; w < g.getNodeSet().size(); w++) {
					if (distanzmatrix[v][w] > distanzmatrix[v][i] + distanzmatrix[i][w]) {
						distanzmatrix[v][w] = distanzmatrix[v][i] + distanzmatrix[i][w];
						transismatrix[v][w] =  g.getNode(i).getId();
					}
				}
				// check for negative cycle
				if (distanzmatrix[v][v] < 0.0) {
					hasNegativeCycle = true;
					System.err.println("Negative cycle not allowed");
					return;
				}
			}
		}
	}

	private void output() {
		for (int i = 0; i < g.getNodeSet().size(); i++) {
			for (int j = 0; j < g.getNodeSet().size(); j++) {
				System.out.print(distanzmatrix[i][j] + " | ");
			}
			System.out.println();
		}
	}

	private void outputTransMatrix() {
		for (int i = 0; i < g.getNodeSet().size(); i++) {
			for (int j = 0; j < g.getNodeSet().size(); j++) {
				System.out.print(transismatrix[i][j] + " | ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		ReadGraph gf = new ReadGraph();
		File file = new File("C:\\Users\\Biraj\\workspace\\GKA_Praktikum1\\asserts\\graph10c.gka");
		gf.initGraph(file); // initialize the graph
		Graph g = gf.getGraph();
		gf.zeichneGraph(file); // draw the graph
		FloydWarshall fw = new FloydWarshall(g);
		fw.updateMatrix();
		// Give all the shortest distance in the matrix
		System.out.println("Give all the shortest distance in the matrix");
		//fw.output();
		System.out.println();
		//fw.outputTransMatrix(); //hier fehlt nun sinnvoll ausgabe von der transmatrix damit man der Weg zurückverfolgen kann
		
    FloydWarshall fw1 = new FloydWarshall(g, "a", "d");
    fw1.updateMatrix();
    System.out.print("Der kürzeste Pfad zwischen "+  fw1.start.getId() + " und "  + fw1.end.getId() + " ist:"); 
    System.out.println(fw1.distanzmatrix[fw1.start.getIndex()][fw1.end.getIndex()] + " über ");
    System.out.println(fw1.transismatrix[fw1.start.getIndex()][fw1.end.getIndex()]);
    fw1.outputTransMatrix(); 
	}

}
