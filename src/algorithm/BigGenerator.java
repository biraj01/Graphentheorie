package algorithm;



import application.ReadGraph;
import org.graphstream.algorithm.generator.BaseGenerator;
import org.graphstream.algorithm.generator.Generator;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.stream.Pipe;

import java.io.IOException;
import java.util.Iterator;
import java.util.Random;

/**
 * Created by Marcel on 19.11.2016.
 */
public class BigGenerator extends BaseGenerator implements Pipe {

    /**
     * Used to generate node names.
     */
    protected int nodeNames = 0;

    /**
     * The dimension of the space.
     */
    protected int dimension = 2;

    /**
     * The threshold that defines whether or not a link is created between to
     * nodes. Since the coordinate system is defined between 0 and 1, the
     * threshold has to be set between these two bounds.
     */
    private double threshold = 0.45;

    /**
     * Minimum range for weighted edges
     */
    private int rangeMin = 0;

    /**
     * Maximum range for weighted edges
     */
    private int rangeMax = 200;

    /**
     * Maximum range for weighted edges
     */
    private int nodes = 10;

    /**
     * New random Euclidean graph generator. By default no attributes are added
     * to nodes and edges. It is possible to make edge randomly directed.
     * @param directed
     *            If true the edges are directed.
     * @param randomlyDirectedEdges
     *            If true edge, are directed and the direction is chosen at
     *            randomly.
     */
    public BigGenerator(boolean directed,
                        boolean randomlyDirectedEdges) {
        super(directed, randomlyDirectedEdges);
        initDimension(dimension);
        setUseInternalGraph(true);
    }


    /**
     * New random Euclidean graph generator. By default no attributes are added
     * to nodes and edges.
     */
    public BigGenerator() {
        super();
        initDimension(dimension);
        setUseInternalGraph(true);
    }

    /**
     * New random Euclidean graph generator. By default no attributes are added
     * to nodes and edges. It is possible to make edge randomly directed. You
     * may also specify a dimension for the space.
     *
     * @param dimension
     *            The dimension of the space for the graph. By default it is
     *            two.
     * @param directed
     *            If true the edges are directed.
     * @param randomlyDirectedEdges
     *            If true edge, are directed and the direction is chosen at
     *            randomly.
     */
    public BigGenerator(int dimension, boolean directed,
                        boolean randomlyDirectedEdges) {
        super(directed, randomlyDirectedEdges);
        initDimension(dimension);
        setUseInternalGraph(true);
    }

    /**
     * New random Euclidean graph generator. By default no attributes are added
     * to nodes and edges. It is possible to make edge randomly directed. You
     * may also specify a dimension for the space. Also you can add a threshold,
     * a minimum and maximum range of weighted edges.
     *
     * @param dimension
     *            The dimension of the space for the graph. By default it is
     *            two.
     * @param directed
     *            If true the edges are directed.
     * @param randomlyDirectedEdges
     *            If true edge, are directed and the direction is chosen at
     *            randomly.
     * @param threshold
     *            The threshold for Random randomDirectedEdges
     * @param rangeMin
     *            Minimum range for weighted edges
     * @param rangeMax
     *            Maximum range for weighted edges
     * @param nodes
     *            Number of nodes to add
     */
    public BigGenerator(int dimension, boolean directed, boolean randomlyDirectedEdges,
                        double threshold, int rangeMin, int rangeMax, int nodes) {
        super(directed, randomlyDirectedEdges);
        this.threshold = threshold;
        this.rangeMin = rangeMin;
        this.rangeMax = rangeMax;
        this.nodes = nodes;
        initDimension(dimension);
        setUseInternalGraph(true); // Funktion aus BaseGenerator
    }



    public void initEvents(){
        this.begin();
        for(int i=0; i<nodes-1; i++) {
            this.nextEvents();
        }
    }

    private void initDimension(int dimension) {
        this.dimension = dimension;
        super.setNodeAttributesRange(0f, 1f);
        if (dimension > 0) {
            if (dimension == 2) {
                super.addNodeAttribute("x");
                super.addNodeAttribute("y");
            } else if (dimension == 3) {
                super.addNodeAttribute("x");
                super.addNodeAttribute("y");
                super.addNodeAttribute("z");
            } else {
                for (int i = 0; i < dimension; i++)
                    super.addNodeAttribute("x" + i);
            }
        } else
            System.err.println("dimension has to be higher that zero");
    }

    public void initEdgeWeights(Graph g){
        int rangeMin = getRangeMin();
        int rangeMax = getRangeMax();

        for (Node n : g) {
            Iterator<Edge> edges = n.getEachLeavingEdge().iterator();
            while(edges.hasNext()) {
                Edge tmp = edges.next();

                Random r = new Random();
                double randomValue = rangeMin + (rangeMax - rangeMin) * r.nextDouble();

                tmp.setAttribute("edgeLength", randomValue);

            }
        }
    }


    /**
     * Start the generator. A single node is added.
     *
     * @see Generator#begin()
     */
    public void begin() {
        String id = Integer.toString(nodeNames++);

        addNode(id);
    }

    /**
     * Step of the generator. Add a new node and connect it with some others.
     *
     * @see Generator#nextEvents()
     */
    public boolean nextEvents() {
        String id = Integer.toString(nodeNames++);

        addNode(id);

        for (Node n : internalGraph.getEachNode()) {
            if (!id.equals(n.getId()) && distance(id, n.getId()) < threshold)
                addEdge(id + "-" + n.getId(), id, n.getId());
        }

        return true;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.graphstream.algorithm.generator.Generator#end()
     */
    @Override
    public void end() {
        super.end();
    }

    /**
     * Distance between two nodes.
     *
     * @param n1
     *            first node
     * @param n2
     *            second node
     * @return distance between n1 and n2
     */
    private double distance(String n1, String n2) {
        double d = 0.0;

        if (dimension == 2) {
            double x1 = internalGraph.getNode(n1).getNumber("x");
            double y1 = internalGraph.getNode(n1).getNumber("y");
            double x2 = internalGraph.getNode(n2).getNumber("x");
            double y2 = internalGraph.getNode(n2).getNumber("y");

            d = Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2);
        } else if (dimension == 3) {
            double x1 = internalGraph.getNode(n1).getNumber("x");
            double y1 = internalGraph.getNode(n1).getNumber("y");
            double x2 = internalGraph.getNode(n2).getNumber("x");
            double y2 = internalGraph.getNode(n2).getNumber("y");
            double z1 = internalGraph.getNode(n1).getNumber("z");
            double z2 = internalGraph.getNode(n2).getNumber("z");

            d = Math.pow(z1 - z2, 2) + Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2);
        } else {
            for (int i = 0; i < dimension; i++) {
                double xi1 = internalGraph.getNode(n1).getNumber("x" + i);
                double xi2 = internalGraph.getNode(n2).getNumber("x" + i);

                d += Math.pow(xi1 - xi2, 2);
            }
        }

        return Math.sqrt(d);
    }







    //GETTER

    public double getThreshold() {
        return threshold;
    }

    public int getRangeMin() {
        return rangeMin;
    }

    public int getRangeMax() {
        return rangeMax;
    }

    public int getNodes() {
        return nodes;
    }

    //SETTER
    /**
     * Set the threshold that defines whether or not a link is created between
     * to notes. Since the coordinate system is defined between 0 and 1, the
     * threshold has to be set between these two bounds.
     *
     * @param threshold
     *            The defined threshold.
     */
    public void setThreshold(double threshold) {
        if (threshold <= 1f && threshold >= 0f)
            this.threshold = threshold;
    }

    public void setRangeMin(int rangeMin) {
        this.rangeMin = rangeMin;
    }

    public void setRangeMax(int rangeMax) {
        this.rangeMax = rangeMax;
    }

    public void setNodes(int nodes) {
        this.nodes = nodes;
    }











































    //Empty Methods


    /*    public static void main(String[] args) {
        ReadGraph gf = new ReadGraph();
        Graph graph = new SingleGraph("random euclidean");
        Generator gen = new BigGenerator(3, true, true, 0.45, 0, 200, 100);
        gen.addSink(graph);
        gen.begin();

        for(int i=0; i<99; i++) {
            gen.nextEvents();
        }

        initEdgeWeights(graph);

        gen.end();
        graph.display(false);

        try {
            gf.saveGraph(graph, "BIG.gka", "directedWeighted");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    /*protected void nodeAttributeHandling(String nodeId, String key, Object val) {
        if (key != null && key.matches("x|y|z") && val instanceof Float) {
            int i = ((int) key.charAt(0)) - (int) 'x';

            if (i < dimension)
                internalGraph.getNode(nodeId).addAttribute(key, val);
        }
    }*/


    public void edgeAttributeAdded(String sourceId, long timeId, String edgeId,
                                   String attribute, Object value) {
    }

    public void edgeAttributeChanged(String sourceId, long timeId,
                                     String edgeId, String attribute, Object oldValue, Object newValue) {
    }

    public void edgeAttributeRemoved(String sourceId, long timeId,
                                     String edgeId, String attribute) {
    }

    public void graphAttributeAdded(String sourceId, long timeId,
                                    String attribute, Object value) {
    }

    public void graphAttributeChanged(String sourceId, long timeId,
                                      String attribute, Object oldValue, Object newValue) {
    }

    public void graphAttributeRemoved(String sourceId, long timeId,
                                      String attribute) {
    }

    public void nodeAttributeAdded(String sourceId, long timeId, String nodeId,
                                   String attribute, Object value) {
        //nodeAttributeHandling(nodeId, attribute, value);
    }

    public void nodeAttributeChanged(String sourceId, long timeId,
                                     String nodeId, String attribute, Object oldValue, Object newValue) {
        //nodeAttributeHandling(nodeId, attribute, newValue);
    }

    public void nodeAttributeRemoved(String sourceId, long timeId,
                                     String nodeId, String attribute) {
    }

    public void edgeAdded(String sourceId, long timeId, String edgeId,
                          String fromNodeId, String toNodeId, boolean directed) {
    }

    public void edgeRemoved(String sourceId, long timeId, String edgeId) {
    }

    public void graphCleared(String sourceId, long timeId) {
    }

    public void nodeAdded(String sourceId, long timeId, String nodeId) {
    }

    public void nodeRemoved(String sourceId, long timeId, String nodeId) {
    }

    public void stepBegins(String sourceId, long timeId, double step) {
    }
}
