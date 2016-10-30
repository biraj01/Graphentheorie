package application;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;
/*
 * Diese Klasse benutzt graphstream um Graphen zu visualisieren.
 */
public class VisualizewithGS {
	private File file;
	
	public VisualizewithGS(File file) {
		this.file = file;
	}

	/*
	 * Zeichnet der Graph anhand von Knoten und Kanten, die der Klasse ReadGraph bietet.
	 */
	public void draw() {
		ReadGraph graphadt = new ReadGraph();
		graphadt.makeGraph(file);
		Set<String> vertices = graphadt.getVertex();
		List<String[]> edges = graphadt.getEdge();
		String typeofGraph = graphadt.getTypeofGraph();
		if(graphadt.isErrorinGraph()){
			System.err.println("Error in graph");
			System.exit(-1);
		}
		Graph graph = new MultiGraph("MultiGraph");
		graph.setStrict(false);
		graph.setAutoCreate(true);
		graph.display();
		Iterator<String> vertexIt = vertices.iterator();
		Iterator<String[]> edgeIt = edges.iterator();

		if (typeofGraph.equals("directed")) {
			System.out.println(edges.size());
			while (edgeIt.hasNext()) {
				System.out.println("it comes here");
				String[] arr = edgeIt.next();
				graph.addEdge(arr.toString(), arr[0], arr[1], true);
			}
			for (Node node : graph) {
				node.addAttribute("ui.label", node.getId());
			}
		} else if (typeofGraph.equals("undirected")) {
			while (edgeIt.hasNext()) {
				String[] arr = edgeIt.next();
				graph.addEdge(arr.toString(), arr[0], arr[1]);

			}
			for (Node node : graph) {
				node.addAttribute("ui.label", node.getId());
			}
		} else if (typeofGraph.equals("undirectedWeightedGraph")) {
			while (edgeIt.hasNext()) {
				String[] arr = edgeIt.next();
				Edge edge = graph.addEdge(arr.toString(), arr[0], arr[1]);
				edge.setAttribute("ui.label", arr[2]);
			}
			for (Node node : graph) {
				node.addAttribute("ui.label", node.getId());
			}
		} else if (typeofGraph.equals("directedWeightedGraph")) {
			while (edgeIt.hasNext()) {
				String[] arr = edgeIt.next();
				Edge edge = graph.addEdge(arr.toString(), arr[0], arr[1], true);
				edge.setAttribute("ui.label", arr[2]);
			}
			for (Node node : graph) {
				node.addAttribute("ui.label", node.getId());
			}

		}
	}

}
