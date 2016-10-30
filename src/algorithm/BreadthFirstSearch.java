package algorithm;


import org.jgraph.graph.DefaultEdge;
import org.jgrapht.Graph;
import org.jgrapht.graph.Pseudograph;
import org.jgrapht.graph.WeightedPseudograph;
import sun.security.provider.certpath.Vertex;

import java.util.*;

public class BreadthFirstSearch {

    private Graph graph;
    //private Vertex source, target;
    private String source, target;
    private List<String[]> edges;
    private Set<String> vertices;

    public BreadthFirstSearch(Graph graph, String source, String target){
        this.graph = graph;
        this.source = source;
        this.target = target;
    }



    public String doSearch(){

        if(graph != null && source != null && target != null && !source.equals(target)  // legit parameters?
                && graph.containsVertex(source) && graph.containsVertex(target)){
            Queue<String> queue = new LinkedList<>();
            Map<String, String> map = new HashMap<>();

            Set<String[]> edges;

            queue.add(source);                  //put the source vertex in the queue
            map.put(source, null);              // ...
            String temp;

            while(!queue.isEmpty() && !queue.contains(target)){ //do BFS until Queue is not empty and doesn't contain the target vertex
                temp = queue.poll();            //take the first vertex out of the queue
                edges = graph.edgesOf(temp);    //get all edges of given vertex

                for(String[] e : edges){     //for every edge in our edge set
                    String targetVertex = graph.getEdgeTarget(e).toString(); //get the target vertex of given edge
                    if(graph instanceof WeightedPseudograph || graph instanceof Pseudograph){ //do if graph is weighted
                        String targetVertex2;
                        if(!map.containsKey(targetVertex2 = graph.getEdgeSource(e).toString())){
                            queue.add(targetVertex2);
                            map.put(targetVertex2, temp); //predecessor
                        }
                    }

                    if(!map.containsKey(targetVertex)){
                        queue.add(targetVertex);
                        map.put(targetVertex, temp);
                    }
                }
            }
            if(queue.contains(target)){ //if queue contains the target vertex
                String current = target;
                String path = "";

                int countEdges = 0;

                while(!current.equals(source)){
                    path = " --> " + current.toString() + path;
                    current = map.get(current);
                    countEdges++;
                }
                return "Der Kürzeste Weg von " + source.toString()
                        + " nach " + target.toString() + " ist:\n"
                        + source.toString() + path + "\n"
                        + "über " + countEdges + " Kante(n).";
            }
        }
        return "Kein Ergebnis.";
    }

}
