package algorithm;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;


//Gegeben ist ein Graph, source und sink, gesucht ist der maimale Fluss "D", der über diesem 
//Graph von source nach sink transformiert werden können
public class FordFukerson {
  private Graph g;
  private Node source;
  private Node sink;
  private double maxFlow;
  LinkedList<Edge> argumentalPath;
  
  
  public FordFukerson(Graph g, String source, String sink){
    this.g = g;
    this.source = g.getNode(source);
    this.sink = g.getNode(sink);
  }
  
  public void init(){
   Iterator<Edge> edges =  g.getEdgeIterator();
   //Flow is 0.0 at the begining
   while(edges.hasNext()){
     edges.next().setAttribute("flow", 0.0);
   }
    //find all possible path and add it it the list
  }
  
  public double fordFukerson(){
    LinkedList<Edge> argumentalPath = bfs(g,source,sink);
    while((argumentalPath)!= null){
    updatePath(argumentalPath);
    bfs(g, source,  sink);
    }
    return maxFlow;
  }
  
  //Der BFS soll jetzt so implementiert werden dass es jedesmal 
  //eine neue Weg(Liste von Kanten von source nach Target) zurück gibt und wenn es kein Weg mehr gibt, gibt null zurück.
  
  private LinkedList<Edge> bfs(Graph g, Node start, Node end){
    return null;
  }
  
  public void setFlow(Edge e, double flow){
    g.setAttribute("flow", flow);
  }
  /*
   * Get remaning capacity from the edge
   */
  public double getFlow(Edge e){
    return (double)g.getAttribute("flow");
    
  }
  
  private void updatePath(List<Edge> pathList){
    int newCap = Integer.MAX_VALUE;
    for(Edge edge : pathList){
      double cap = getFlow(edge);
      cap = Math.min(newCap, cap);
    }
    for(Edge edge : pathList){
      edge.changeAttribute("capacity", newCap);
    }
  }
  
  
  
  
}
