package aufgabe1;

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


	public void makeGraph(File file) {
		BufferedReader reader = null;
		//List<String> graph = new ArrayList<>();
		try {
			reader = new BufferedReader(new FileReader(file));
			String line;
			Pattern pattern = Pattern.compile("(.*) (.*) (.*);");
			while ((line = reader.readLine()) != null) {
			//	graph.add(line);
				Matcher m = pattern.matcher(line);
				while (m.find()) {
					if (m.group(2).equals("--")) {
						typeofGraph = "undirection";
						 System.out.println(m.group(1)+"und"+m.group(3)+"finish");
					}
					vertex.add(m.group(1));
					vertex.add(m.group(3));
					String[] temp = new String[2];
					temp[0] = m.group(1);
					temp[1] = m.group(3);
					edge.add(temp);
					// System.out.println(Arrays.toString(edge.toArray()));
					//edge.put(m.group(1), m.group(3));
					 //System.out.println(m.group(2));
				}
				// System.out.println(vertex.toString());
				// System.out.println(line);
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

	
}
