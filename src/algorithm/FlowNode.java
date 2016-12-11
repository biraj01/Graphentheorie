package algorithm;

import java.util.ArrayList;
import java.util.List;

public class FlowNode<V, E> {

  

  private V node;
  private List<FlowEdge> outgoingEdges = new ArrayList<FlowEdge>();
  private boolean visited;
  private FlowEdge lastEdge;
  private double flowAmount = 0.0;
 
  public FlowNode(V node) {
    this.node = node;
  }

  public boolean isVisited() {
    return visited;
  }

  public void setLastEdge(FlowEdge lastEdge) {
    this.lastEdge = lastEdge;
  }

  public void setVisited(boolean visited) {
    this.visited = visited;
  }

  public double getFlowAmount() {
    return flowAmount;
  }

  public void setFlowAmount(double flowAmount) {
    this.flowAmount = flowAmount;
  }

  public V getNode() {
    return node;
  }

  public List<FlowEdge> getOutgoingEdges() {
    return outgoingEdges;
  }

  public FlowEdge getLastEdge() {
    return lastEdge;
  }

  public void addOutgoingEdge(FlowEdge e) {
    outgoingEdges.add(e);
  }

  public void mark(FlowEdge last, double posFlow) {
    lastEdge = last;
    flowAmount = posFlow;
  }

  public void unmark() {
    lastEdge = null;
    flowAmount = 0.0;
  }
}
