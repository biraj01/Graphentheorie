package application;

import org.jgrapht.Graph;
import org.jgrapht.graph.AbstractGraph;
import org.jgrapht.graph.Pseudograph;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * Dieser Klasse liest einer .gka datein, und speichert die Kanten, Knoten sowie der Typ von der Graph
 */
public class ReadGraph {

	private static final String directed = "(.*)\\s*(->)\\s*([^:\\s]*);";
	private static final String undirected = "(.*)\\s*(--)\\s*([^:\\s]*);";
	private static final String directedWeightedGraph = "(.*)\\s*(->)\\s*([^:]*)\\s*(:?)\\s*(\\(.+\\)|[0-9]+);";
	private static final String undirectedWeightedGraph = "(.*)\\s*(--)\\s*([^:]*)\\s*(:?)\\s*([0-9]*);";
	private Graph graph;
	private String typeofGraph;
	private Set<String> vertex = new HashSet<>();
	private List<String[]> edge = new ArrayList<>();
	private boolean errorinGraph = false;

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
			System.out.println(firstline);
			if (firstline.matches(directed)) {
				System.out.println("The graph is directed");
				setTypeofGraph("directed");
			} else if (firstline.matches(undirected)) {
				System.out.println("The graph is undirected");
				setTypeofGraph("undirected");
			} else if (firstline.matches(undirectedWeightedGraph)) {
				System.out.println("The graph is undirected with length");
				setTypeofGraph("undirectedWeightedGraph");
			} else if (firstline.matches(directedWeightedGraph)) {
				System.out.println("The graph is directed with length");
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/*
	 * Liest die datein und speichert die Kanten und Knoten
	 */
	public void makeGraph(File file) {
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

		// TODo:
		// Find what type of graph it in the File with the help of Regular
		// expression
		// Case1: Undirected without Edge length
		// Add Vertex in Map and Edge in list of array
		// Graph type is Graphtype.1
		// Case2: Directed without length
		// Graph type is Graphtype.2
		// Add Vertex in Map and Edge in list of array
		// Case3: Undirected with length
		// Graph type is Graphtype.2
		// Add Vertex in Map and (Edge and length) in list of array[3]
		// Case4 Directed with length
		// Graph type is Graphtype.2
		// Add Vertex in Map and (Edge and length) in list of array[3]

	}

	private void handleDirectedwithLength(BufferedReader reader) throws IOException {
		String line;
		Pattern pattern = Pattern.compile(directedWeightedGraph);
		while ((line = reader.readLine()) != null) {
			Matcher m = pattern.matcher(line);
			System.out.println(line);
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
				System.out.println(m.group(1) + " " + m.group(2) + " " + m.group(3));
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
				System.out.println(m.group(1) + " " + m.group(2) + " " + m.group(3));
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

	public void createGraph(){
		//Graph<String, String[]> tmpGraph = new Graph<>();
		AbstractGraph<String, String[]> tmpGraph = new Pseudograph<String, String[]>(String[].class);
		for (String s: vertex ) {
			tmpGraph.addVertex(s);
		}

		for (String[] sE: edge ) {
			//tmpGraph.addEdge(sE[0], sE[1]);
			tmpGraph.addEdge(sE[0], sE[1], sE);
		}
		graph = tmpGraph;
		//System.out.println("created Graph");
	}

	public Graph getGraph() {
		return graph;
	}
}
}
