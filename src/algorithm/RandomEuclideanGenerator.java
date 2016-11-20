package algorithm;



import application.ReadGraph;
import org.graphstream.algorithm.generator.BaseGenerator;
import org.graphstream.algorithm.generator.Generator;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.stream.Pipe;

import java.io.File;
import java.io.IOException;

/**
 * Created by Marcel on 19.11.2016.
 */
public class RandomEuclideanGenerator extends BaseGenerator implements Pipe {
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
    protected double threshold = 0.5;

    /**
     * New random Euclidean graph generator. By default no attributes are added
     * to nodes and edges. Dimension of the space is two.
     */
    public RandomEuclideanGenerator() {
        super();
        initDimension(2);
        setUseInternalGraph(true);
    }

    /**
     * New random Euclidean graph generator. By default no attributes are added
     * to nodes and edges. You may also specify a dimension for the space.
     *
     * @param dimension
     *            The dimension of the space for the graph. By default it is
     *            two.
     */
    public RandomEuclideanGenerator(int dimension) {
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
    public RandomEuclideanGenerator(int dimension, boolean directed,
                                    boolean randomlyDirectedEdges) {
        super(directed, randomlyDirectedEdges);
        initDimension(dimension);
        setUseInternalGraph(true);
    }

    /**
     * New random Euclidean graph generator.
     *
     * @param dimension
     *            The dimension of the space for the graph. By default it is
     *            two.
     * @param directed
     *            If true the edges are directed.
     * @param randomlyDirectedEdges
     *            It true, edges are directed and the direction is chosen at
     *            random.
     * @param nodeAttribute
     *            put an attribute by that name on each node with a random
     *            numeric value.
     * @param edgeAttribute
     *            put an attribute by that name on each edge with a random
     *            numeric value.
     */
    public RandomEuclideanGenerator(int dimension, boolean directed,
                                    boolean randomlyDirectedEdges, String nodeAttribute,
                                    String edgeAttribute) {
        super(directed, randomlyDirectedEdges, nodeAttribute, edgeAttribute);
        initDimension(dimension);
        setUseInternalGraph(true);
    }


    public static void main(String[] args) {
        ReadGraph gf = new ReadGraph();
        Graph graph = new SingleGraph("random euclidean");
        Generator gen = new RandomEuclideanGenerator(3, true, true, "test", "edgeLength");
        gen.addSink(graph);
        gen.begin();
        for(int i=0; i<100; i++) {
            gen.nextEvents();
        }
        gen.end();
        graph.display(false);

        try {
            gf.saveGraph(graph, "BIG.txt", "directedWeighted");
        } catch (IOException e) {
            e.printStackTrace();
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

    protected void nodeAttributeHandling(String nodeId, String key, Object val) {
        if (key != null && key.matches("x|y|z") && val instanceof Float) {
            int i = ((int) key.charAt(0)) - (int) 'x';

            if (i < dimension)
                internalGraph.getNode(nodeId).addAttribute(key, val);
        }
    }

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
        nodeAttributeHandling(nodeId, attribute, value);
    }

    public void nodeAttributeChanged(String sourceId, long timeId,
                                     String nodeId, String attribute, Object oldValue, Object newValue) {
        nodeAttributeHandling(nodeId, attribute, newValue);
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
