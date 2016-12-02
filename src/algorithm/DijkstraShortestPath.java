package algorithm;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;


public class DijkstraShortestPath {

  private Graph graph;
  private Node vertexStart;
  private Node vertexEnd;
  private Set<Node> settledNodes;
  private Set<Node> unsettledNodes;
  private int count;
  private Map<Node, Node> predecessor;
  private double totalLength;

  public Node getVertexStart() {
    return vertexStart;
  }

  public void setVertexStart(Node vertexStart) {
    this.vertexStart = vertexStart;
  }

  public Node getVertexEnd() {
    return vertexEnd;
  }

  public void setVertexEnd(Node vertexEnd) {
    this.vertexEnd = vertexEnd;
  }

  public double getTotalLength() {
    return totalLength;
  }

  public void setTotalLength(double totalLength) {
    this.totalLength = totalLength;
  }

  public DijkstraShortestPath(Graph graph, String source, String target) {
    this.graph = graph;
    vertexStart = graph.getNode(source);
    vertexEnd = graph.getNode(target);
    settledNodes = new HashSet<Node>();
    unsettledNodes = new HashSet<Node>();
    predecessor = new HashMap<>();
  }

  public void init(Graph graph) {
    Iterator<Node> nodeIt = graph.getNodeIterator();
    while (nodeIt.hasNext()) {
      count++;
      Node n = (Node) nodeIt.next();
      if (n.getId().equals(this.vertexStart.getId())) {
        n.setAttribute("entfernung", 0.0);
        count++;
        n.setAttribute("vorgaenger", this.vertexStart.getId());
        count++;
      } else {
        n.setAttribute("entfernung", Double.POSITIVE_INFINITY);
        n.setAttribute("vorgaenger", "Undefined");
        // n.setAttribute("ok", false);
        count += 2;
      }
    }

  }

  public void doSearch() {
    unsettledNodes.add(vertexStart);
    // vertexStart.setAttribute("ui.color","red");
    while (unsettledNodes.size() > 0) { // bis alle Knoten auf false gesetzt ist
      count++;
      Node node = getMin(unsettledNodes);
      settledNodes.add(node);
      unsettledNodes.remove(node);
      findMinimalDistance(node);
    }

  }

  private Node getMin(Set<Node> vertexes) {
    Node minimum = null;
    for (Node node : vertexes) {
      if (minimum == null) {
        minimum = node;
        count++;
      } else {
        if ((double) node.getAttribute("entfernung") < (double) minimum.getAttribute("entfernung")) {
          minimum = node;
          count++;
        }
      }
    }
    return minimum;
  }

  private void findMinimalDistance(Node node) {
    Iterator<Node> nodes;
    if (graph.getAttribute("type").equals("undirected")) {
      nodes = node.getNeighborNodeIterator();
      count++;
    } else {
      Iterator<Edge> edges = node.getEachLeavingEdge().iterator();
      Set<Node> temp = new HashSet<>();
      while (edges.hasNext()) {
        count++;
        temp.add(edges.next().getTargetNode());
      }
      nodes = temp.iterator();
    }

    while (nodes.hasNext()) {
      count++;
      Node target = nodes.next();
      if ((double) target.getAttribute("entfernung") > (double) node.getAttribute("entfernung")
          + (double) node.getEdgeBetween(target).getAttribute("edgeLength")) {
        target.setAttribute("entfernung",
            (double) node.getAttribute("entfernung") + (double) node.getEdgeBetween(target).getAttribute("edgeLength"));
        predecessor.put(target, node);
        target.setAttribute("ui.color", 1);
        unsettledNodes.add(target);
      }
    }
  }

  public String[] getPath() {
    LinkedList<String> path = new LinkedList<String>();
    Node step = vertexEnd;
    // check if a path exists
    if (predecessor.get(step) == null) {
      return null;
    }
    path.add(step.getId());
    while (predecessor.get(step) != null) {
      step = predecessor.get(step);
      path.add(step.getId());
    }
    // Put it into the correct order
    Collections.reverse(path);
    printPath(path);
    return path.toArray(new String[path.size()]);

  }

  private void printPath(List<String> path) {
    System.out.println("Der kürzeste Pfad zwischen " + vertexStart + " und " + vertexEnd + " ist: ");
    for (int i = 0; i < path.size() - 1; i++) {
      System.out.print(path.get(i).toString() + " -> ");
    }
    System.out.print(path.get(path.size() - 1));
    System.out.println("  über " + (path.size()) + " kanten");
    System.out.println(
        "Gesamte Pfad länge:  " + (graph.getNode(path.get(path.size() - 1))).getAttribute("entfernung").toString());
    System.out.println("Anzahl Zugriff: " + count);
    totalLength = graph.getNode((path.get(path.size() - 1))).getAttribute("entfernung");

  }

}