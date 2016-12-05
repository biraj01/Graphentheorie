package algorithm;

import java.util.*;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import sun.awt.image.ImageWatched;


//Gegeben ist ein Graph, source und sink, gesucht ist der maimale Fluss "D", der über diesem 
//Graph von source nach sink transformiert werden können
public class FordFukerson {
    private Graph g;
    private Node source;
    private Node sink;
    private double maxFlow;
    LinkedList<Edge> argumentalPath;

    Stack<Node> connectionPath = new Stack<>();
    List<Stack> connectionPaths = new ArrayList<>();

    LinkedList<LinkedList<Edge>> allEdgePathList = new LinkedList<>();
    //LinkedList<Edge> edgePathList;

    public FordFukerson(Graph g, String source, String sink) {
        this.g = g;
        this.source = g.getNode(source);
        this.sink = g.getNode(sink);
    }

    public void init() {
        Iterator<Edge> edges = g.getEdgeIterator();
        //Flow is 0.0 at the begining
        while (edges.hasNext()) {
            edges.next().setAttribute("flow", 0.0);
        }
        //find all possible path and add it it the list
    }

    public double fordFukerson() {
        LinkedList<Edge> argumentalPath = bfs(g, source, sink);
        while ((argumentalPath) != null) {
            updatePath(argumentalPath);
            bfs(g, source, sink);
        }
        return maxFlow;
    }

    //Der BFS soll jetzt so implementiert werden dass es jedesmal
    //eine neue Weg(Liste von Kanten von source nach Target) zurück gibt und wenn es kein Weg mehr gibt, gibt null zurück.


    /*edit marcel: ok hab jetzt rekursiv mit findAllPaths eine Liste von Stacks erstellt in denen alle Pfade drin sein sollten (noch ungetestet)
    * desweiteren wird diese Liste dann in eine LinkedList<LinkedList<Edge>> umgewandelt und wird erstmal doof als Klassenvariable abgespeichert.
    *
    * Nun ist wichtig was bfs (am besten namen noch ändern) zurückgibt, dahingegend muss das dann noch angepasst werden^^
    *
    */
    private LinkedList<Edge> bfs(Graph g, Node start, Node end) {
        findAllPaths(start, end);
        initAllNodePathsToEdgePaths();
        return null;
    }

    private void initAllNodePathsToEdgePaths(){
        for (Stack<Node> s : connectionPaths) {
            LinkedList<Edge> edgePathList = new LinkedList<>();

            while (s.size() >= 2) {
                Node current = s.peek();
                s.pop();
                Node nextNode = s.peek();

                edgePathList.addFirst(current.getEdgeBetween(nextNode));
            }
            allEdgePathList.add(edgePathList);
        }
    }

    private void findAllPaths(Node startNode, Node endNode) {
        for (Node nextNode : g) {
            if (nextNode.equals(endNode)) {
                Stack temp = new Stack();
                for (Node node : connectionPath)
                    temp.add(node);
                connectionPaths.add(temp);
            } else if (!connectionPath.contains(nextNode)) {
                connectionPath.push(nextNode);
                findAllPaths(nextNode, endNode);
                connectionPath.pop();
            }
        }
    }


    public void setFlow(Edge e, double flow) {
        g.setAttribute("flow", flow);
    }

    /*
     * Get remaning capacity from the edge
     */
    public double getFlow(Edge e) {
        return (double) g.getAttribute("flow");

    }

    private void updatePath(List<Edge> pathList) {
        int newCap = Integer.MAX_VALUE;
        for (Edge edge : pathList) {
            double cap = getFlow(edge);
            cap = Math.min(newCap, cap);
        }
        for (Edge edge : pathList) {
            edge.changeAttribute("capacity", newCap);
        }
    }

}