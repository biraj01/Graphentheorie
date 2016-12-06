package algorithm;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;

import java.util.*;

import static oracle.jrockit.jfr.events.Bits.intValue;


public class BigGenerator {

    private int nodeNames = 0;
    private int edgeCount = 0;
    private boolean directed = false;
    private int rangeMin = 0;
    private int rangeMax = 0;
    private Graph graph;
    private String source;
    private String target;
    private String typeOfGraph;

    public BigGenerator(int vertices, int edges, boolean directed, int rangeMin, int rangeMax) {
        this.rangeMin = rangeMin;
        this.rangeMax = rangeMax;
        this.directed = directed;

        if (directed) {
            typeOfGraph = "directedWeighted";
            createGraph(vertices, edges);
            initEdgeWeights();
        } else {
            new BigGenerator(vertices, edges, rangeMin, rangeMax);
        }
    }

    /**
     * Netzwerke
     **/
    public BigGenerator(int vertices, int edges, String source, String target, boolean directed, int rangeMin, int rangeMax) {
        //check if range is not negative

        this.rangeMin = rangeMin;
        this.rangeMax = rangeMax;
        this.directed = directed;
        this.target = target;
        this.source = source;

        if (directed) {
            typeOfGraph = "directedWeighted";
            createGraph(vertices - 2, edges - edges / 10); // -2 for source and target (getting added later)
        } else {
            new BigGenerator(vertices - 2, edges - edges / 10, rangeMin, rangeMax); // edges/10 for source and target
        }

        if (!source.equals(target) && source != null && target != null && source != "" && target != "") {
            initNetwork(source, target, vertices, edges);
            initEdgeWeights();
        } else {
            initNetwork("source", "target", vertices, edges);
            initEdgeWeights();
        }
    }

    private void initNetwork(String source, String target, int vertices, int edges) {
        graph.addNode(source);
        graph.addNode(target);


        int exitCount = 0;
        int leftEdgesSource = (edges / 10) / 2;
        int leftEdgesTarget = leftEdgesSource;

        while (leftEdgesSource >= 0) {
            Random r = new Random();
            if (exitCount < edges * vertices) {
                Node n1 = graph.getNode(source);
                Node n2 = graph.getNode(intValue((vertices) * r.nextDouble()));
                if (!(n1.getId().equals(n2.getId())) && !(n1.hasEdgeBetween(n2) || n2.hasEdgeBetween(n1)) && !(n2.getId().equals(target))) {
                    exitCount = 0;
                    graph.addEdge(n1.getId() + "-" + n2.getId(), n1.getId(), n2.getId(), true);
                    leftEdgesSource--;
                } else {
                    exitCount++;
                }
            } else {
                break;
            }
        }
        exitCount = 0;
        while (leftEdgesTarget >= 0) {
            Random r = new Random();
            if (exitCount < edges * vertices) {
                Node n1 = graph.getNode(intValue((vertices) * r.nextDouble()));
                Node n2 = graph.getNode(target);
                if (!(n1.getId().equals(n2.getId())) && !(n1.hasEdgeBetween(n2) || n2.hasEdgeBetween(n1)) && !(n1.getId().equals(source))) {
                    exitCount = 0;
                    graph.addEdge(n1.getId() + "-" + n2.getId(), n1.getId(), n2.getId(), true);
                    leftEdgesTarget--;
                } else {
                    exitCount++;
                }
            } else {
                break;
            }
        }

    }

    public BigGenerator(int vertices, int edges, int rangeMin, int rangeMax) {
        this.rangeMin = rangeMin;
        this.rangeMax = rangeMax;

        typeOfGraph = "undirectedWeighted";
        createGraph(vertices, edges);
        initEdgeWeights();
    }

    public BigGenerator(int vertices, int edges, boolean directed) {
        this.directed = directed;

        if (directed) {
            typeOfGraph = "directed";
            createGraph(vertices, edges);
        } else {
            new BigGenerator(vertices, edges);
        }
    }

    public BigGenerator(int vertices, int edges) {
        typeOfGraph = "undirected";
        createGraph(vertices, edges);
    }

    private void createGraph(int vertices, int edges) {
        graph = new MultiGraph("Big");
        int exitCount = 0;

        for (int i = 0; i < vertices; i++) {
            String id = Integer.toString(nodeNames++);
            graph.addNode(id);
        }

        while (edgeCount < edges) {
            Random r = new Random();

            Node n1 = graph.getNode(intValue((vertices) * r.nextDouble()));
            Node n2 = graph.getNode(intValue((vertices) * r.nextDouble()));
            if (exitCount < edges * vertices) {
                if (!(n1.getId().equals(n2.getId())) && !(n1.hasEdgeBetween(n2) || n2.hasEdgeBetween(n1))) {
                    exitCount = 0;
                    if (isDirected()) {
                        graph.addEdge(n1.getId() + "-" + n2.getId(), n1.getId(), n2.getId(), true);
                        edgeCount++;
                    } else {
                        graph.addEdge(n1.getId() + "-" + n2.getId(), n1.getId(), n2.getId());
                        edgeCount++;
                    }
                } else {
                    exitCount++;
                }
            } else {
                break;
            }
        }
    }

    public void initEdgeWeights() {
        int rangeMin = getRangeMin();
        int rangeMax = getRangeMax() - 1;

        for (Node n : graph) {
            Iterator<Edge> edges = n.getEachLeavingEdge().iterator();
            while (edges.hasNext()) {
                Edge tmp = edges.next();

                Random r = new Random();
                double randomValue = rangeMin + (rangeMax - rangeMin) * r.nextDouble();

                tmp.setAttribute("edgeLength", randomValue + 1);

            }
        }
    }


    public boolean isDirected() {
        return directed;
    }

    public Graph getGraph() {
        return graph;
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
    }

    public int getRangeMin() {
        return rangeMin;
    }

    public int getRangeMax() {
        return rangeMax;
    }


}
