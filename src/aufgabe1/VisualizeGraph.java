package aufgabe1;

import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.JApplet;
import javax.swing.JFrame;

import org.jgraph.JGraph;
import org.jgraph.graph.DefaultEdge;
import org.jgraph.graph.PortView;
import org.jgrapht.ListenableGraph;
import org.jgrapht.demo.JGraphAdapterDemo;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.ListenableDirectedGraph;
import org.jgrapht.graph.ListenableUndirectedGraph;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.swing.mxGraphComponent;

public class VisualizeGraph extends JApplet {

	private static final Color DEFAULT_BG_COLOR = Color.decode("#FAFBFF");
	private static final Dimension DEFAULT_SIZE = new Dimension(530, 320);
	private JGraphXAdapter<String, DefaultEdge> jgAdapter;

	
	public void init() {
		ListenableGraph g = new ListenableDirectedGraph<>(DefaultEdge.class);

		jgAdapter = new JGraphXAdapter(g);
		getContentPane().add(new mxGraphComponent(jgAdapter));
		ReadGraph graphadt = new ReadGraph();
		File file = new File("C:\\Users\\Biraj\\workspace\\GKA_Praktikummitjavafx\\asserts\\graph2.gka");
		graphadt.makeGraph(file);
		Set<String> vertices = graphadt.getVertex();
		System.out.println(vertices.size());
		List<String[]> edges = graphadt.getEdge();

		Iterator it = vertices.iterator();
		while (it.hasNext()) {
			System.out.println("true");
			String s = it.next().toString();
			g.addVertex(s);
		}

		Iterator itEdge = edges.iterator();
		while (itEdge.hasNext()) {
			System.out.println("has edge");
			String[] arr = (String[]) itEdge.next();
			g.addEdge(arr[0], arr[1]);
		}

		mxCircleLayout layout = new mxCircleLayout(jgAdapter);
		layout.setRadius(350);
		layout.execute(jgAdapter.getDefaultParent());
	}

	public static void main(String[] args) {

		VisualizeGraph graph = new VisualizeGraph();
		graph.init();
		JFrame frame = new JFrame();
		frame.setSize(1000, 1000);
		frame.getContentPane().add(graph);
		frame.setVisible(true);

	}
}
