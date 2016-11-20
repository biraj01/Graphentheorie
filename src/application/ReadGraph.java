package application;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static oracle.jrockit.jfr.events.Bits.intValue;


/*
 * Dieser Klasse liest einer .gka datein, und speichert die Kanten, Knoten sowie der Typ von der Graph
 */
public class ReadGraph {

	private static final String directed = "(.*)\\s*(->)\\s*([^:\\s]*);";
	private static final String undirected = "(.*)\\s*(--)\\s*([^:\\s]*);";
	private static final String directedWeightedGraph = "(.*)\\s*(->)\\s*([^:]*)\\s*(:?)\\s*(\\(.+\\)|[0-9]+);";
	private static final String undirectedWeightedGraph = "(.*)\\s*(--)\\s*([^:]*)\\s*(:?)\\s*([0-9]*);";
	private String typeofGraph;
	private Set<String> vertex = new HashSet<>();
	private List<String[]> edge = new ArrayList<>();
	private boolean errorinGraph = false;
	private Graph graph ;

	
	public Graph getGraph() {
		return graph;
	}

	public void setGraph(Graph graph) {
		this.graph = graph;
	}

	public List<String[]> getEdge() {
		return edge;
	}

	public void setEdge(List<String[]> edge) {
		this.edge = edge;
	}

	public Set<String> getVertex() {
		return vertex;
	}

	public void setVertex(Set<String> vertex) {
		this.vertex = vertex;
	}

	public String getTypeofGraph() {
		return typeofGraph;
	}

	public void setTypeofGraph(String typeofGraph) {
		this.typeofGraph = typeofGraph;
	}

	public boolean isErrorinGraph() {
		return errorinGraph;
	}

	public void setErrorinGraph(boolean errorinGraph) {
		this.errorinGraph = errorinGraph;
	}

	/*
	 * Liest die .gka Datein und findet der Graphtyp
	 */
	private void findGraphType(File file) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String firstline = reader.readLine();
			//System.out.println(firstline);
			if (firstline.matches(directed)) {
				setTypeofGraph("directed");
			} else if (firstline.matches(undirected)) {
				setTypeofGraph("undirected");
			} else if (firstline.matches(undirectedWeightedGraph)) {
				setTypeofGraph("undirectedWeightedGraph");
			} else if (firstline.matches(directedWeightedGraph)) {
				setTypeofGraph("directedWeightedGraph");
			} else {
				setTypeofGraph("ErrorGraph");
				System.err.println("Unkown Graph");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/*
	 * Initialisiert den Graphen mit Vertices und Edges
	 */
	public void initGraph(File file){
		getEdgesundVertex(file); // set edges and vertices for the graph
		graph = new MultiGraph("graph");
		graph.addAttribute("ui.stylesheet", "url('C:\\Users\\Marcel\\Documents\\IntelliJ-Programme\\GKA_Praktikum2\\src\\application\\stylesheet.css')");
		graph.addAttribute("ui.stylesheet", "url('C:\\Users\\Biraj\\workspace\\GKA_Praktikum1\\src\\application\\stylesheet.css')");
		graph.setStrict(false);
		graph.setAutoCreate(true);
		//Iterator<String> vertexIt = vertex.iterator();
		Iterator<String[]> edgeIt = edge.iterator();
		if (typeofGraph.equals("directed")) {
		  graph.addAttribute("type", "directed");
			while (edgeIt.hasNext()) {
				String[] arr = edgeIt.next();
				graph.addEdge(arr.toString(), arr[0], arr[1], true);
			}
			
		} else if (typeofGraph.equals("undirected")) {
		  graph.addAttribute("type", "undirected");
			while (edgeIt.hasNext()) {
				String[] arr = edgeIt.next();
				graph.addEdge(arr.toString(), arr[0], arr[1]);

			}
		} else if (typeofGraph.equals("undirectedWeightedGraph")) {
		  graph.addAttribute("type", "undirected");
			while (edgeIt.hasNext()) {
				String[] arr = edgeIt.next();
				Edge edge = graph.addEdge(arr.toString(), arr[0], arr[1]);
				edge.setAttribute("ui.label", arr[2]);
				edge.setAttribute("edgeLength", Double.parseDouble(arr[2]));
			}
		} else if (typeofGraph.equals("directedWeightedGraph")) {
		  graph.addAttribute("type", "directed");
			while (edgeIt.hasNext()) {
				String[] arr = edgeIt.next();
				Edge edge = graph.addEdge(arr.toString(), arr[0], arr[1], true);
				edge.setAttribute("ui.label", arr[2]);
				edge.setAttribute("edgeLength", Double.parseDouble(arr[2]));
			}

		}
		for (Node node : graph) {
      node.addAttribute("ui.label", node.getId() + "(" + node.getIndex() + ")" );
    }

	}
	
	public void zeichneGraph(File file){
		initGraph(file);
		graph.display();
	}
	

	/*
	 * Liest die datein und speichert die Kanten und Knoten
	 */
	public void getEdgesundVertex(File file) {
		findGraphType(file);
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			if (typeofGraph.equals("directed")) {
				handleDirected(reader);
			} else if (typeofGraph.equals("undirected")) {
				handleUnDirected(reader);
			} else if (typeofGraph.equals("directedWeightedGraph")) {
				handleDirectedwithLength(reader);
			} else if (typeofGraph.equals("undirectedWeightedGraph")) {
				handleUndirectedwithLength(reader);
			} else {
				System.out.println("Der Graph ist nicht richtig");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void handleDirectedwithLength(BufferedReader reader) throws IOException {
		String line;
		Pattern pattern = Pattern.compile(directedWeightedGraph);
		while ((line = reader.readLine()) != null) {
			Matcher m = pattern.matcher(line);
			//System.out.println(line);
			if (m.matches()) {
				vertex.add(m.group(1).trim());
				vertex.add(m.group(3).trim());
				String[] temp = new String[3];
				temp[0] = m.group(1).trim();
				temp[1] = m.group(3).trim();
				temp[2] = m.group(5).trim();
				edge.add(temp);
			} else {
				if (line.matches("\\s*")) {
					System.out.println("Leer Zeile");
				} else {
					System.err.println("Error mit graph" + line + " ist nicht in richtige Format");
					setErrorinGraph(true);
					break;
				}

			}
		}
	}

	private void handleUndirectedwithLength(BufferedReader reader) throws IOException {
		String line;
		Pattern pattern = Pattern.compile(undirectedWeightedGraph);
		while ((line = reader.readLine()) != null) {
			Matcher m = pattern.matcher(line);
			if (m.matches()) {
				vertex.add(m.group(1).trim());
				vertex.add(m.group(3).trim());
				String[] temp = new String[3];
				temp[0] = m.group(1).trim();
				temp[1] = m.group(3).trim();
				temp[2] = m.group(5).trim();
				edge.add(temp);
			} else {
				if (line.matches("\\s*")) {
					System.out.println("Leer Zeile");
				} else {
					System.err.println("Error mit graph" + line + " ist nicht in richtige Format");
					setErrorinGraph(true);
					break;
				}
			}
		}
	}

	private void handleUnDirected(BufferedReader reader) throws IOException {
		String line;
		Pattern pattern = Pattern.compile(undirected);
		while ((line = reader.readLine()) != null) {
			Matcher m = pattern.matcher(line);
			if (m.matches()) {
				//System.out.println(m.group(1) + " " + m.group(2) + " " + m.group(3));
				vertex.add(m.group(1).trim());
				vertex.add(m.group(3).trim());
				String[] temp = new String[2];
				temp[0] = m.group(1).trim();
				temp[1] = m.group(3).trim();
				edge.add(temp);
			} else {
				if (line.matches("\\s*")) {
					System.out.println("Leer Zeile");
				} else {
					System.err.println("Error mit graph" + line + " ist nicht in richtige Format");
					setErrorinGraph(true);
					break;
				}
			}
		}
	}

	private void handleDirected(BufferedReader reader) throws IOException {
		String line;
		Pattern pattern = Pattern.compile(directed);
		while ((line = reader.readLine()) != null) {
			Matcher m = pattern.matcher(line);
			if (m.matches()) {
				vertex.add(m.group(1).trim());
				vertex.add(m.group(3).trim());
				String[] temp = new String[2];
				temp[0] = m.group(1).trim();
				temp[1] = m.group(3).trim();
				edge.add(temp);
			} else {
				if (line.matches("\\s*")) {
					System.out.println("Leer Zeile gefunden");
				} else {
					System.err.println(line + " ist nicht in richtige Format");
					setErrorinGraph(true);
					break;
				}
			}
		}
	}

	public void saveGraph(Graph g, String name, String auswahl) throws IOException {
		switch (auswahl) {
			case "directedWeighted":  saveDirectedWeightedGraph(g, name);
				break;
			case "directed":  saveDirectedGraph(g, name);
				break;
			case "undirectedWeighted":  saveUndirectedWeightedGraph(g, name);
				break;
			case "undirected":  saveUndirectedGraph(g, name);
				break;
		}
	}

	private void saveDirectedGraph(Graph g, String name) throws IOException{
		List<String> lines = new LinkedList<>();
		Node tmpNode;

		Path file = Paths.get(name);

		for ( Node n : g ) {
			Iterator<Edge> edges = n.getEachLeavingEdge().iterator();
			while(edges.hasNext()){
				tmpNode = edges.next().getTargetNode();
				lines.add(n + " -> " + tmpNode + ";");
			}
		}
		Files.write(file, lines, Charset.forName("UTF-8"));
	}

	private void saveDirectedWeightedGraph(Graph g, String name) throws IOException{
		List<String> lines = new LinkedList<>();
		Node tmpNode;

		Path file = Paths.get(name);

		for ( Node n : g ) {
			Iterator<Edge> edges = n.getEachLeavingEdge().iterator();
			//lines.add(n + "--" + edges);
			while(edges.hasNext()){
				tmpNode = edges.next().getTargetNode();
				lines.add(n + " -> " + tmpNode + " : " + intValue(n.getEdgeBetween(tmpNode).getAttribute("edgeLength")) + ";");
			}
		}
		Files.write(file, lines, Charset.forName("UTF-8"));
		//Files.write(file, lines, Charset.forName("UTF-8"), StandardOpenOption.APPEND);
	}

	private void saveUndirectedWeightedGraph(Graph g, String name) throws IOException{
		List<String> lines = new LinkedList<>();
		Node tmpNode;

		Path file = Paths.get(name);

		for ( Node n : g ) {
			Iterator<Edge> edges = n.getEachEdge().iterator();
			while(edges.hasNext()){
				tmpNode = edges.next().getTargetNode();
				lines.add(n + " -- " + tmpNode + ";");
			}
		}
		Files.write(file, lines, Charset.forName("UTF-8"));
	}

	private void saveUndirectedGraph(Graph g, String name) throws IOException{
		List<String> lines = new LinkedList<>();
		Node tmpNode;

		Path file = Paths.get(name);

		for ( Node n : g ) {
			Iterator<Edge> edges = n.getEachLeavingEdge().iterator();
			while(edges.hasNext()){
				tmpNode = edges.next().getTargetNode();
				lines.add(n + " -- " + tmpNode + ";");
			}
		}
		Files.write(file, lines, Charset.forName("UTF-8"));
	}

}
