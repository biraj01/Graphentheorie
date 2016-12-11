package algorithm;

import application.ReadGraph;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.stream.file.FileSourceGEXF;

import java.io.File;
import java.util.*;


public class EdmondsKarp {
    private Graph graph;
    private int source;
    private int target;
    private double[][] flowMatrix;
    private int[] parentTable;
    private double[][] residualMatrix; // restmatrix
    private double[][] capacityMatrix; // kapazitätsmatrix
    private double erg;
    private int SIZE;

    public EdmondsKarp(Graph graph, String s, String t) {
        this.graph = graph;
        this.source = graph.getNode(s).getIndex();
        this.target = graph.getNode(t).getIndex();

        SIZE = graph.getNodeSet().size();
        residualMatrix = new double[SIZE][SIZE];
        capacityMatrix = new double[SIZE][SIZE];

        initMatrizen();

        erg = edmondsKarp(source, target);
    }


    private void initMatrizen() {
        // initialise with MIN_VALUE
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                residualMatrix[i][j] = -Double.MIN_VALUE;
                capacityMatrix[i][j] = -Double.MIN_VALUE;
            }
        }

        for (Node n : graph) {
            Iterator<Edge> edges = n.getEachLeavingEdge().iterator();
            while (edges.hasNext()) {
                Edge tmp = edges.next();

                if (residualMatrix[tmp.getSourceNode().getIndex()][tmp.getTargetNode().getIndex()] < 0)
                    residualMatrix[tmp.getSourceNode().getIndex()][tmp.getTargetNode().getIndex()] = tmp.getAttribute("edgeLength");
                if (capacityMatrix[tmp.getSourceNode().getIndex()][tmp.getTargetNode().getIndex()] < 0)
                    capacityMatrix[tmp.getSourceNode().getIndex()][tmp.getTargetNode().getIndex()] = tmp.getAttribute("edgeLength");
            }
        }
    }


    /**
     * Finds the maximum flow in a flow network.
     *
     * @param s source
     * @param t sink
     * @return maximum flow
     */
    public double edmondsKarp(int s, int t) {
        double maxFlow = 0.0; // initial flow to zero
        int size = graph.getNodeSet().size();
        flowMatrix = new double[size][size];

        while (true) {
            double foundCapacity = bfs(s, t, flowMatrix);
            if (foundCapacity == 0.0) {
                break;
            }
            maxFlow = maxFlow + foundCapacity;

            // backtrack search, and write flow
            int v = t;
            while (v != s) {
                int u = parentTable[v];
                flowMatrix[u][v] = flowMatrix[u][v] + foundCapacity;
                flowMatrix[v][u] = flowMatrix[v][u] - foundCapacity;
                residualMatrix[u][v] = residualMatrix[u][v] - foundCapacity;
                residualMatrix[v][u] = residualMatrix[v][u] + foundCapacity;
                v = u;
            }

        }
        //System.out.println(maxFlow);
        return maxFlow;
    }


    private double bfs(int s, int t, double[][] flowMatrix) {
        int size = graph.getNodeSet().size();
        parentTable = new int[size];

        for (int u = 0; u < size; u++) { // evtl nicht stimmig
            parentTable[u] = -1;
        }
        parentTable[s] = -2; //make sure source is not rediscovered
        double[] capacityOfFoundPath = new double[size];
        capacityOfFoundPath[s] = Double.MAX_VALUE;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(s);
        while (!queue.isEmpty()) {
            Node uNode = graph.getNode(queue.poll());
            Iterator<Node> it = graph.getNodeIterator(); // get all edges of given vertex
            while (it.hasNext()) {
                Node vNode = it.next();
                if (!vNode.equals(uNode)) {
                    int u = uNode.getIndex();
                    int v = vNode.getIndex();
                    //if there is a available residual capacity, and v is not seen before in search
                    if (residualMatrix[u][v] > 0.0 && parentTable[v] == -1) {
                        parentTable[v] = u;
                        capacityOfFoundPath[v] = Math.min(capacityOfFoundPath[u], capacityMatrix[u][v] - flowMatrix[u][v]); //findet den niedrigsten flow im pfad
                        if (v != t) {
                            queue.offer(v);
                        } else {
                            return capacityOfFoundPath[t];
                        }
                    }
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        ReadGraph gf = new ReadGraph();
        //File file = new File("C:\\Users\\Marcel\\Documents\\IntelliJ-Programme\\GKA_Praktikum\\asserts\\networkTest.gka");
        File file = new File("C:\\Users\\Marcel\\Documents\\IntelliJ-Programme\\GKA_Praktikum\\asserts\\networkTest3.gka");
        gf.initGraph(file);    //initialize the graph
        Graph graph = gf.getGraph();
        //gf.zeichneGraph(file); //draw the graph


        EdmondsKarp ek = new EdmondsKarp(graph, "s", "t");
        System.out.println(ek.getMaximumFlow());
    }

    public int getSource() {
        return source;
    }

    public int getTarget() {
        return target;
    }

    public double[][] getFlowMatrix() {
        return flowMatrix;
    }

    public double[][] getT() {
        return residualMatrix;
    }

    public double[][] getC() {
        return capacityMatrix;
    }

    public double getMaximumFlow() {
        return erg;
    }

}



