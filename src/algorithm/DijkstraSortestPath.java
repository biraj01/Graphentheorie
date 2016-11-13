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

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import application.ReadGraph;

public class DijkstraSortestPath {

	private Graph graph;
	private Node vertexStart;
	private Node vertexEnd;
	private Set<Node> settledNodes;
	private Set<Node> unsettledNodes;
	private String entfernung;
	private String vorganger;
	private boolean ok;
	Map<Node, Node> predecessoders;

	public DijkstraSortestPath(Graph graph, String source, String target) {
		this.graph = graph;
		vertexStart = graph.getNode(source);
		vertexEnd = graph.getNode(target);
	}

	public static void main(String[] args) {

		ReadGraph gf = new ReadGraph();
		File file = new File("C:\\Users\\Biraj\\workspace\\GKA_Praktikum1\\asserts\\graph10b.gka");
		gf.initGraph(file); // initialize the graph
		Graph g = gf.getGraph();
		gf.zeichneGraph(file); // draw the graph
		DijkstraSortestPath bf = new DijkstraSortestPath(g, "v4", "v10");
		// System.out.println(bf.doSearch());
		bf.init(g);
		bf.doSearch();
		bf.getPath(bf.vertexEnd);

	}

	public void init(Graph graph) {

		Iterator<Node> nodeIt = graph.getNodeIterator();
		while (nodeIt.hasNext()) {
			Node n = (Node) nodeIt.next();
			if (n.getId().equals(this.vertexStart.getId())) {
				n.setAttribute("entfernung", 0.0);
				n.setAttribute("vorgaenger", this.vertexStart.getId());
				System.out.println("entfernung: " + n.getAttribute("entfernung").toString());
			} else {
				n.setAttribute("entfernung", Double.POSITIVE_INFINITY);
				n.setAttribute("vorgaenger", "Undefined");
				n.setAttribute("ok", false);
				System.out.println(n.getId());
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
			} else {
				if ((double) node.getAttribute("entfernung") < (double) minimum.getAttribute("entfernung")) {
					minimum = node;
				}
			}
		}
		return minimum;
	}

	private void findMinimalDistance(Node node) {
		Iterator<Node> nodes = node.getNeighborNodeIterator();
		while (nodes.hasNext()) {
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
		for (Node n : path) {
			System.out.print(n.toString() + " -> ");
		}
		return path;

	}

}
