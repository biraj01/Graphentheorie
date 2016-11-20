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

  public double getPathLength() {
    return pathLength;
  }

  public void setPathLength(double pathLength) {
    this.pathLength = pathLength;
  }

  private void initMatrix() {
    int size = g.getNodeSet().size();
    this.distanzmatrix = new double[size][size];
    this.transismatrix = new String[size][size];
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        count++;
        if (i == j) {
          distanzmatrix[i][j] = 0;
        } else {
          distanzmatrix[i][j] = Double.POSITIVE_INFINITY;
        }
      }
    }
    // initialise diagonal to zero and other to infinitive
    System.out.println();
    for (int i = 0; i < size; i++) {
      Node n = g.getNode(i);
      for (Edge e : n.getEnteringEdgeSet()) {
        count++;
        distanzmatrix[e.getSourceNode().getIndex()][e.getTargetNode().getIndex()] = e.getAttribute("edgeLength");
      }

    }
    // Initialise other direct distance in the matrix
  }

  public void updateMatrix() {
    for (int i = 0; i < g.getNodeSet().size(); i++) {
      for (int v = 0; v < g.getNodeSet().size(); v++) {
        for (int w = 0; w < g.getNodeSet().size(); w++) {
          if (distanzmatrix[v][w] > distanzmatrix[v][i] + distanzmatrix[i][w]) {
            distanzmatrix[v][w] = distanzmatrix[v][i] + distanzmatrix[i][w];
            transismatrix[v][w] = g.getNode(i).getId();
            count++;
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
    pathLength = distanzmatrix[start.getIndex()][end.getIndex()];
  }

  public void output() {
    for (int i = 0; i < g.getNodeSet().size(); i++) {
      for (int j = 0; j < g.getNodeSet().size(); j++) {
        System.out.print(distanzmatrix[i][j] + " | ");
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
    System.out.print("Der kürzeste Pfad zwischen " + start.getId() + " und " + end.getId() + " ist:");
    System.out.println(pathLength + " über ");
    System.out.println(transismatrix[start.getIndex()][end.getIndex()]);
    System.out.println("Anzahl Zugriff: " + count);
  }

  public static void main(String[] args) {
    ReadGraph gf = new ReadGraph();
    File file = new File("C:\\Users\\Biraj\\workspace\\GKA_Praktikum1\\asserts\\graph3.gka");
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

    FloydWarshall fw1 = new FloydWarshall(g, "Hannover", "Paderborn");
    fw1.updateMatrix();
    fw1.outputDistance();
  }

}
