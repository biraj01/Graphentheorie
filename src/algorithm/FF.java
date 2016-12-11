package algorithm;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

import application.ReadGraph;

public class FF {
  private Graph graph;
  private ArrayList<Node> nodeList;
  private HashMap<String, Integer> nodeID;
  private String sink, source;
  private ArrayList<FlowNode<String, Edge>> nodes;
  private LinkedList<FlowNode<String, Edge>> markedNodes;
  private double maxFlow;
  private int sinkID, sourceID;

  public FF(Graph graph, String source, String sink) {
    this.graph = graph;
    this.sink = sink;
    this.source = source;
    nodeID = new HashMap<String, Integer>();
    nodeList = new ArrayList<Node>(graph.getNodeSet());
    markedNodes = new LinkedList<FlowNode<String, Edge>>();
    nodes = new ArrayList<FlowNode<String, Edge>>();

    initialise();

    sourceID = nodeID.get(source);
    sinkID = nodeID.get(sink);

    inspectAndMark();

    System.out.println(getMaximumFlow());
  }
  

  public static void main(String[] args) {
    ReadGraph gf = new ReadGraph();
    File file = new File("C:\\Users\\Biraj\\workspace\\GKA_Praktikum1\\asserts\\networkTest3.gka");
    gf.initGraph(file); // initialize the graph
    Graph g = gf.getGraph();
    gf.zeichneGraph(file); // draw the graph
    System.out.println();
    FF ff = new FF(g, "s", "t");

  }

  private void initialise() {

    for (int i = 0; i < nodeList.size(); i++) {
      String currentNode = nodeList.get(i).getId();
      nodeID.put(currentNode, i);
      nodes.add(new FlowNode<String, Edge>(currentNode));
    }
    for (int i = 0; i < nodeList.size(); i++) {
      String vertex = nodes.get(i).getNode();
      for (Edge e : graph.getNode(vertex).getEachLeavingEdge()) {
        String neighbour = e.getTargetNode().getId();
        int j = nodeID.get(neighbour);
        FlowEdge<String, Edge> e1 = new FlowEdge<String, Edge>(j, i, e.getAttribute("edgeLength"), e);
        if (e1.getReversed() == null) {
          FlowEdge<String, Edge> e2 = new FlowEdge<String, Edge>(i, j, 0.0, null);
          e1.setReversed(e2);
          e2.setReversed(e1);
          nodes.get(j).addOutgoingEdge(e2);
        }
        nodes.get(i).addOutgoingEdge(e1);
      }
      if (vertex.equals(source)) {
        nodes.get(i).mark(null, Double.POSITIVE_INFINITY);
        markedNodes.add(nodes.get(i));
      }
    }
  }

  private void inspectAndMark() {
    Random rand = new Random();
    // suche nach vergrößernden bis keiner mehr gibts
    while (!markedNodes.isEmpty()) {
      // nehme einen random Knoten aus der List der markierten Knoten und lösche
      // ihn aus der Liste
      int r = rand.nextInt(markedNodes.size());
      FlowNode<String, Edge> node = markedNodes.remove(r);

      // Knoten ist inspiziert = true
      node.setVisited(true);

      // inspiziere den gewählten Knoten
      for (FlowEdge<String, Edge> e : node.getOutgoingEdges()) {
        // ein neuer Knoten darf nur zur Liste der markierten Knoten hinzugefügt
        // werden wenn die Kapazität noch nicht ausgenutzt und der Knoten
        // weder markiert noch inspiziert ist
        if (e.getCapacity() > e.getFlow() && !(markedNodes.contains(nodes.get(e.getTarget())))
            && !(nodes.get(e.getTarget()).isVisited())) {
          // markiere den Knoten mit VorgŠnger und mšglicher Vergš§erung
          FlowNode<String, Edge> neighbour = nodes.get(e.getTarget());
          neighbour.mark(e, Math.min(node.getFlowAmount(), (e.getCapacity() - e.getFlow())));
          markedNodes.add(neighbour);
        }
      }
      // falls senke markiert ist
      if (markedNodes.contains(nodes.get(sinkID))) {
        // vergrößernder Weg gefunden!
        increaseFlow();
      }
    }

    findMaxFlow();
  }

  private void increaseFlow() {

    double deltaFlow = nodes.get(sinkID).getFlowAmount();
    int currentID = sinkID;
    String way = "" + nodes.get(currentID).getNode();

    // anhand der Markierungen der gefundene vergroßernde Weg bis ¨
    //zum Knoten q ruckwarts durchlaufen.
    while (currentID != sourceID) {
      // addiere den verändernden Wert auf die VorwärtsKante
      nodes.get(currentID).getLastEdge().setFlow(nodes.get(currentID).getLastEdge().getFlow() + deltaFlow);
      // subtrahiere von der rückwertskante Kante
      nodes.get(currentID).getLastEdge().getReversed()
          .setFlow(nodes.get(currentID).getLastEdge().getReversed().getFlow() - deltaFlow);
      currentID = nodes.get(currentID).getLastEdge().getSource();
      way = nodes.get(currentID).getNode() + " - " + way;
    }

    System.out.println(way);
    //Anschließend werden bei allen Knoten mit Ausnahme
    //von q die Markierungen entfernt
    markedNodes.clear();
    markedNodes.push(nodes.get(sourceID));

    for (int i = 0; i < nodes.size(); i++) {
      nodes.get(i).setVisited(false);
    }
  }

  private void findMaxFlow() {
    for (FlowNode<String, Edge> n : nodes) {
      String node = n.getNode();
      for (FlowEdge<String, Node> e : n.getOutgoingEdges()) {
        double flow = e.getFlow();
        FlowNode<String, Edge> lastNode = nodes.get(e.getTarget());
        //maxFlow += flow;
        if (n.isVisited() && !(lastNode.isVisited())) {
          e.getReversed().setFlow(0.0);
          maxFlow += flow;
          System.out.println("+" + flow + " : " + node + "-" + lastNode.getNode());
        } else if (!(n.isVisited()) && lastNode.isVisited()) {
          e.getReversed().setFlow(0.0);
          maxFlow -= flow;
          if (flow < 0) {
            flow *= -1;
            System.out.println("+" + flow + " : " + node + "-" + lastNode.getNode());
          } else {
            System.out.println("-" + flow + " : " + node + "-" + lastNode.getNode());
          }
        }
      }
      n.unmark();
    }

  }

  public double getMaximumFlow() {
    System.out.println("");
    System.out.println("maximaler Fluss: " + maxFlow);
    return maxFlow;
  }
}