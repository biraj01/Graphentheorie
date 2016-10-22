package application;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.JApplet;

import org.jgraph.graph.DefaultEdge;
import org.jgraph.graph.Edge;
import org.jgrapht.Graph;
import org.jgrapht.ListenableGraph;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.ListenableDirectedGraph;
import org.jgrapht.graph.ListenableDirectedWeightedGraph;
import org.jgrapht.graph.ListenableUndirectedGraph;
import org.jgrapht.graph.ListenableUndirectedWeightedGraph;
import org.jgrapht.graph.Multigraph;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.swing.mxGraphComponent;

public class VisualizeGraph extends JApplet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JGraphXAdapter<String, DefaultEdge> jgAdapter;
	private File file;
	private ListenableGraph graph;
	ReadGraph graphadt;
	Set<String> vertices;
	List<String[]> edges;
	String typeofGraph;

	public VisualizeGraph(File file) {
		this.file = file;
	}

	public void init() {
		graphadt = new ReadGraph();
		graphadt.makeGraph(file);
		vertices = graphadt.getVertex();
		edges = graphadt.getEdge();
		typeofGraph = graphadt.getTypeofGraph();
		if(typeofGraph.equals("directed")){
			System.out.println("In init");
			graph = new ListenableDirectedGraph(DefaultEdge.class);
		}else if(typeofGraph.equals("undirected")){
			System.out.println("Draw undirected");
			graph = new ListenableUndirectedGraph(DefaultEdge.class);
		}else if(typeofGraph.equals("undirectedWeightedGraph")){
			graph = new ListenableUndirectedWeightedGraph(MyEdge.class);
		}else if(typeofGraph.equals("directedWeightedGraph")){
			graph = new ListenableDirectedWeightedGraph(MyEdge.class);
		}else{
			System.out.println("Error in graph");
		}
		jgAdapter = new JGraphXAdapter(graph);
		
		getContentPane().add(new mxGraphComponent(jgAdapter));
		System.out.println("vor Draw");
		draw();
		
		mxCircleLayout layout = new mxCircleLayout(jgAdapter);
		//mxIGraphLayout layout = new mxCircleLayout(jgAdapter);
		layout.setRadius(300);
		layout.execute(jgAdapter.getDefaultParent());
	}

	private void draw() {
		if (typeofGraph.equals("undirected")) {
			drawUnweightedGraph((ListenableUndirectedGraph)graph, vertices, edges);
		} else if (typeofGraph.equals("directed")) {
			drawUnweightedGraph((ListenableDirectedGraph)graph, vertices, edges);
		} else if (typeofGraph.equals("undirectedWeightedGraph")) {
			drawWeightedUndirectedGraph((ListenableUndirectedWeightedGraph)graph, vertices, edges);
		} else if (typeofGraph.equals("directedWeightedGraph")) {
			drawWeightedDirectedGraph((ListenableDirectedWeightedGraph)graph, vertices, edges);
		}
	}

	private void drawUnweightedGraph(ListenableGraph g, Set<String> vertices, List<String[]> edges) {
		System.out.println("IN Draw");
		Iterator it = vertices.iterator();
		while (it.hasNext()) {
			String s = it.next().toString();
			System.out.println(s);
			g.addVertex(s);
		}

		Iterator itEdge = edges.iterator();
		while (itEdge.hasNext()) {
			String[] arr = (String[]) itEdge.next();
			g.addEdge(arr[0], arr[1]);
			System.out.println(arr.toString());
		}
	}

	private void drawWeightedDirectedGraph(ListenableDirectedWeightedGraph g, Set<String> vertices, List<String[]> edges) {
		System.out.println("I am here");
		Iterator it = vertices.iterator();
		while (it.hasNext()) {
			String s = it.next().toString();
			g.addVertex(s);
		}

		Iterator itEdge = edges.iterator();
		while (itEdge.hasNext()) {
			String[] arr = (String[]) itEdge.next();
			Edge edge = (Edge)g.addEdge(arr[0], arr[1]);
			//System.out.println(arr[2]);
			g.setEdgeWeight(edge, Double.parseDouble(arr[2] == null ? "0" : arr[2]));
		}
	}
	private void drawWeightedUndirectedGraph(ListenableUndirectedWeightedGraph g, Set<String> vertices, List<String[]> edges) {
		Iterator it = vertices.iterator();
		while (it.hasNext()) {
			String s = it.next().toString();
			g.addVertex(s);
		}

		Iterator itEdge = edges.iterator();
		while (itEdge.hasNext()) {
			String[] arr = (String[]) itEdge.next();
			MyEdge edge = (MyEdge)g.addEdge(arr[0], arr[1]);
			g.setEdgeWeight(edge, 5);
			//g.setEdgeWeight(edge, Double.parseDouble(arr[2]!=null ? arr[2] : "0"));
		}
	}
	
	public static class MyEdge extends DefaultWeightedEdge{
		@Override
		public String toString(){
			return String.valueOf(getWeight());
		}
	}

}
