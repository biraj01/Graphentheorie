package algorithm;

import java.io.File;
import java.util.*;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

import application.ReadGraph;

public class BreadthFirst {
	private Graph graph;
	private Node vertexStart;
	private Node vertexEnd;

	public BreadthFirst(Graph graph, String source, String target) {
		this.graph = graph;
		vertexStart = graph.getNode(source);
		vertexEnd = graph.getNode(target);
	}

	
	public static void main(String [] args){
		
		ReadGraph  gf = new ReadGraph();
        File file = new File("C:\\Users\\Marcel\\Documents\\IntelliJ-Programme\\GKA_Praktikum\\asserts\\graph3.gka");
        gf.initGraph(file);	//initialize the graph
       	Graph g =  gf.getGraph();
        gf.zeichneGraph(file); //draw the graph
        BreadthFirst bf = new BreadthFirst(g, "Uelzen", "Hannover");
        System.out.println(bf.doSearch());
        
	}

	public String doSearch() {
		//List<List<Node>> allPaths = new ArrayList<>();
		String ergPath = "Kein Ergebnis.";

		if (graph != null && !vertexStart.equals(vertexEnd)) {
			Queue<Node> queue = new LinkedList<>();
			Map<Node, Node> map = new HashMap<>();
			queue.add(vertexStart); // put the source vertex in the queue
			map.put(vertexStart, null); // map to remember the predecessor
			Node temp;
			while (!queue.isEmpty() && !queue.contains(vertexEnd)) { // do BFS until Queue is not empty and
																	// doesn't contain the target vertex
				temp = queue.poll(); // take the first vertex out of the queue
			   	Iterator<Edge> it  = temp.getEachEdge().iterator(); // get all edges of given vertex

				while(it.hasNext()){
					Edge e = it.next();
					System.out.println(e.toString());
					Node targetVertex = e.getTargetNode(); // get the target vertex of given edge

					if (!map.containsKey(targetVertex)) { // don't add if targetVertex ist already in the map
						queue.add(targetVertex);
						map.put(targetVertex, temp);
					}
				}
			}
			if (queue.contains(vertexEnd)) { // if queue contains the target vertex
				ergPath = getPath(map);
			}
		}
		return ergPath;
	}


	private String getPath(Map<Node, Node> map){
		Node current = vertexEnd;
		String path = "";

		int countEdges = 0;

		while (!current.equals(vertexStart)) {
			path = " --> " + current.toString() + path;
			current = map.get(current);
			countEdges++;
		}
		return "Der Kurzeste Weg von " + vertexStart.toString() + " nach " + vertexEnd.toString() + " ist:\n"
				+ vertexStart.toString() + path + "\n" + "Über " + countEdges + " Kante(n).";
	}
}
