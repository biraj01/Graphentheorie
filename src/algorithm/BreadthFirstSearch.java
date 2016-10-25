package algorithm;


import datatypes.Edge;
import datatypes.Vertex;
import org.jgrapht.Graph;
import org.jgrapht.graph.Pseudograph;
import org.jgrapht.graph.WeightedPseudograph;

import java.util.*;

public class BreadthFirstSearch {

    private Graph graph;
    private Vertex source, target;

    public BreadthFirstSearch(Graph graph, Vertex source, Vertex target){
        this.graph = graph;
        this.source = source;
        this.target = target;
    }

    public String doSearch(){
        if(graph != null && source != null && target != null && !source.equals(target)
                && graph.containsVertex(source) && graph.containsVertex(target)){
            Queue<Vertex> queue = new LinkedList<>();
            Map<Vertex, Vertex> map = new HashMap<>();

            Set<Edge> edges;

            queue.add(source);
            map.put(source, null);
            Vertex temp;

            while(!queue.isEmpty() && !queue.contains(target)){
                temp = queue.poll();
                edges = graph.edgesOf(temp);

                for(Edge e : edges){
                    Vertex targetVertex = (Vertex) graph.getEdgeTarget(e);
                    if(graph instanceof WeightedPseudograph || graph instanceof Pseudograph){
                        Vertex targetVertex2;
                        if(!map.containsKey(targetVertex2 = (Vertex) graph.getEdgeSource(e))){
                            queue.add(targetVertex2);
                            map.put(targetVertex2, temp);
                        }
                    }

                    if(!map.containsKey(targetVertex)){
                        queue.add(targetVertex);
                        map.put(targetVertex, temp);
                    }
                }
            }
            if(queue.contains(target)){
                Vertex current = target;
                String path = "";

                int countEdges = 0;

                while(!current.equals(source)){
                    path = " --> " + current.toString() + path;
                    current = map.get(current);
                    countEdges++;
                }
                return "Der Kürzeste Weg von " + source.toString()
                        + " nach " + target.toString() + " ist :\n"
                        + source.toString() + path + "\n"
                        + "über " + countEdges + " Kante(n).";
            }
        }
        return "Kein Ergebnis.";
    }
}
