package algorithm;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import application.ReadGraph;

public class FloydWarshall {

	private double[][] distancematrix;
	private int [][] transismatrix;
	private Graph g;
	private boolean hasNegativeCycle;
	private Node start;
	private Node end;
	private int count;
	private double pathLength;

	public FloydWarshall(Graph g) {
		this.g = g;
		initMatrix();

	}

	public FloydWarshall(Graph g, String start, String end) {
		this.g = g;
		initMatrix();
		this.start = g.getNode(start);
		this.end = g.getNode(end);
		;

	}

	public double[][] getDistanzmatrix() {
		return distancematrix;
	}

	public void setDistanzmatrix(double[][] distanzmatrix) {
		this.distancematrix = distanzmatrix;
	}

	public int[][] getTransismatrix() {
		return transismatrix;
	}

	public void setTransismatrix(int[][] transismatrix) {
		this.transismatrix = transismatrix;
	}

	public double getPathLength() {
		return pathLength;
	}

	public void setPathLength(double pathLength) {
		this.pathLength = pathLength;
	}

	private void initMatrix() {
		int size = g.getNodeSet().size();
		this.distancematrix = new double[size][size];
		this.transismatrix = new int[size][size];
		// initialise diagonal to zero and other to infinitive
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
			  transismatrix[i][j] = -1;
				count++;
				if (i == j) {
					distancematrix[i][j] = 0;
				} else {
					distancematrix[i][j] = Double.POSITIVE_INFINITY;
				}
			}
		}
		// Initialise other direct distance in the matrix
		for (int i = 0; i < size; i++) {
			Node n = g.getNode(i);
			for (Edge e : n.getEnteringEdgeSet()) {
				count++;
				distancematrix[e.getSourceNode().getIndex()][e.getTargetNode().getIndex()] = e.getAttribute("edgeLength");
			}
		}
	}

	public void updateMatrix() {
		for (int i = 0; i < g.getNodeSet().size(); i++) {
			for (int v = 0; v < g.getNodeSet().size(); v++) {
				for (int w = 0; w < g.getNodeSet().size(); w++) {
					if (distancematrix[v][w] > distancematrix[v][i] + distancematrix[i][w]) {
						distancematrix[v][w] = distancematrix[v][i] + distancematrix[i][w];
						transismatrix[v][w] = i;
						count++;
					}
				}
				// check for negative cycle
				if (distancematrix[v][v] < 0.0) {
					hasNegativeCycle = true;
					System.err.println("Negative cycle not allowed");
					System.exit(-1);
				}
			}
		}
	}

	public void output() {
		for (int i = 0; i < g.getNodeSet().size(); i++) {
			for (int j = 0; j < g.getNodeSet().size(); j++) {
				System.out.print(distancematrix[i][j] + " | ");
			}
			System.out.println();
		}
	}

	public void outputTransMatrix() {
		for (int i = 0; i < g.getNodeSet().size(); i++) {
			for (int j = 0; j < g.getNodeSet().size(); j++) {
				System.out.print(transismatrix[i][j] + " | ");
			}
			System.out.println();
		}
	}

	public void outputDistance() {
	  pathLength = distancematrix[start.getIndex()][end.getIndex()];
	  System.out.print("Der kürzeste Pfad zwischen " + start.getId() + " und " + end.getId() + " ist:");
		System.out.println(pathLength);
		//System.out.println(transismatrix[start.getIndex()][end.getIndex()]);
		System.out.println("Anzahl Zugriff: " + count);
	}
	
	
	public String [] constructPath(){
	  List<String> path = new ArrayList<>();
	  if(!hasNegativeCycle){
	    path.add(end.getId());
	    int stop = end.getIndex(); 
	    while(transismatrix[start.getIndex()][stop] != -1){
	      int nodeId = transismatrix[start.getIndex()][stop];
	      path.add(g.getNode(nodeId).getId());
	      stop = nodeId;
	    }
	    path.add(start.getId());
	  }
	  Collections.reverse(path);
	  String [] arr = path.toArray(new String[path.size()]);
	  return arr;
	}
	
}