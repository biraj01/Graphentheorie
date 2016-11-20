package algorithm;

import java.io.File;
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
import application.ReadGraph;

public class DijkstraSortestPath {

	private Graph graph;
	private Node vertexStart;
	private Node vertexEnd;
	private Set<Node> settledNodes;
	private Set<Node> unsettledNodes;
	private int count;
	Map<Node, Node> predecessoders;

	public DijkstraSortestPath(Graph graph, String source, String target) {
		this.graph = graph;
		vertexStart = graph.getNode(source);
		vertexEnd = graph.getNode(target);
	}

	public static void main(String[] args) {

		ReadGraph gf = new ReadGraph();
		File file = new File("C:\\Users\\Biraj\\workspace\\GKA_Praktikum1\\asserts\\graph3b.gka");
		gf.initGraph(file); // initialize the graph
		Graph g = gf.getGraph();
		gf.zeichneGraph(file); // draw the graph
		DijkstraSortestPath bf = new DijkstraSortestPath(g, "Hannover", "Paderborn");
		bf.init(g);
		bf.doSearch();
		bf.getPath(bf.vertexEnd);

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
				n.setAttribute("ok", false);
				count +=3;
			}
		}

	}

	public void doSearch() {
		settledNodes = new HashSet<Node>();
		unsettledNodes = new HashSet<Node>();
		predecessoders = new HashMap<>();
		unsettledNodes.add(vertexStart);
		vertexStart.setAttribute("ui.color","red");
		while (unsettledNodes.size() > 0) {
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
	  if(graph.getAttribute("type").equals("undirected")){
	    nodes = node.getNeighborNodeIterator();
	    count++;
	  }else{
	    Iterator<Edge> edges = node.getEachLeavingEdge().iterator();
	    Set<Node> temp = new HashSet<>();
	    while(edges.hasNext()){
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
				target.setAttribute("entfernung", (double) node.getAttribute("entfernung")
						+ (double) node.getEdgeBetween(target).getAttribute("edgeLength"));
				predecessoders.put(target, node);
				target.setAttribute("ui.color",1);
				unsettledNodes.add(target);
			}
		}
	}

	public LinkedList<Node> getPath(Node target) {
		LinkedList<Node> path = new LinkedList<Node>();
		Node step = target;
		// check if a path exists
		if (predecessoders.get(step) == null) {
			return null;
		}
		path.add(step);
		while (predecessoders.get(step) != null) {
			step = predecessoders.get(step);
			path.add(step);
		}
		// Put it into the correct order
		Collections.reverse(path);
		System.out.println("Der kürzeste Pfad für dieses Graph ist: ");
		for(int i = 0; i< path.size() - 1;i++){
		  System.out.print(path.get(i).toString() + " -> ");
		}
		System.out.print(path.get(path.size()-1));
		System.out.println("  über " + (path.size() ) + " kanten");
		System.out.println((path.get(path.size()-1)).getAttribute("entfernung").toString());
		System.out.println("Anzahl Zugriff: " + count);
		return path;

	}

}
