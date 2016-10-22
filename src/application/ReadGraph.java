package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.omg.IOP.TAG_MULTIPLE_COMPONENTS;

public class ReadGraph {

	private String typeofGraph;
	private Set<String> vertex = new HashSet<>();
	private List<String[]> edge = new ArrayList<>();

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

	BufferedReader reader = null;
	String directed = "(.*)\\s*(->)\\s*([^:\\s]*);";
	String undirected = "(.*)\\s*(--)\\s*([^:\\s]*);";
	String directedwithlength = "(.*)\\s*(->)\\s*([^:]*)\\s*(:?)\\s*([0-9]*);";
	String undirectedwithlength = "(.*)\\s*(--)\\s*([^:]*)\\s*(:?)\\s*([0-9]*);";

	public void makeGraph(File file) {
		try {
			reader = new BufferedReader(new FileReader(file));
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
			String firstline = reader.readLine();
			if (firstline.matches(directed)) {
				System.out.println("The graph is directed");
				setTypeofGraph("directed");
				handleDirected(reader);
			} else if (firstline.matches(undirected)) {
				System.out.println("The graph is undirected");
				setTypeofGraph("undirected");
				handleUnDirected(reader);
			} else if (firstline.matches(undirectedwithlength)) {
				System.out.println("The graph is undirected with length");
				setTypeofGraph("undirectedWeightedGraph");
				handleUndirectedwithLength(reader);
			} else if (firstline.matches(directedwithlength)) {
				System.out.println("The graph is directed with length");
				setTypeofGraph("directedWeightedGraph");
				handleDirectedwithLength(reader);
			} else {
				System.out.println("Unkown Graph");
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
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

	private void handleDirectedwithLength(BufferedReader reader) throws IOException {
		String line;
		Pattern pattern = Pattern.compile(directedwithlength);
		while ((line = reader.readLine()) != null) {
			Matcher m = pattern.matcher(line);
			if(m.matches()) {
				System.out.println(m.group(1) + "." + m.group(2) + "." + m.group(3) +"." +  m.group(4) + "." + m.group(5));
				vertex.add(m.group(1).trim());
				vertex.add(m.group(3).trim());
				String[] temp = new String[3];
				temp[0] = m.group(1).trim();
				temp[1] = m.group(3).trim();
				temp[2] = m.group(5).trim();
				edge.add(temp);
			}
		}
	}

	private void handleUndirectedwithLength(BufferedReader reader) throws IOException {
		String line;
		Pattern pattern = Pattern.compile(undirectedwithlength);
		while ((line = reader.readLine()) != null) {
			Matcher m = pattern.matcher(line);
			if(m.matches()){
			System.out.println(m.group(1) + "." + m.group(2) + "." + m.group(3) + "." + m.group(4) + "." + m.group(5));
			vertex.add(m.group(1).trim());
			vertex.add(m.group(3).trim());
			String[] temp = new String[3];
			temp[0] = m.group(1).trim();
			temp[1] = m.group(3).trim();
			temp[2] = m.group(5).trim();
			edge.add(temp);
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
			}
		}
	}

}
