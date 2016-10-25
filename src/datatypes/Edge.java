package datatypes;

import org.jgraph.graph.DefaultEdge;


/**
 * Created by Marcel on 23.10.2016.
 */
public class Edge extends DefaultEdge implements IEdge {
    @Override
    public String formatted(){
        return ((Vertex) this.getSource()).getName()
                + ","
                + ((Vertex) this.getTarget()).getName();
    }
}
