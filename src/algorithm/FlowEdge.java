package algorithm;

public class FlowEdge<V,E> {
  
  
    private int source;
    private int target;
    private double capacity;   
    private double flow;
    private FlowEdge<V, E> reversed;
    private E originalEdge;

 
    public FlowEdge(int target, int source, double capacity, E originalEdge) {
      this.target = target;
      this.source = source;
      this.capacity = capacity;
      this.originalEdge = originalEdge;
    }

    
    public FlowEdge<V, E> getReversed() {
      return reversed;
    }

    public void setReversed(FlowEdge<V, E> reversed) {
      this.reversed = reversed;
    }

    public double getCapacity() {
      return capacity;
    }

    public void setCapacity(double capacity) {
      this.capacity = capacity;
    }

    public double getFlow() {
      return flow;
    }

    public void setFlow(double flow) {
      this.flow = flow;
    }

    public int getTarget() {
      return target;
    }

    public int getSource() {
      return source;
    }

    public E getOriginalEdge() {
      return originalEdge;
    }

  }


