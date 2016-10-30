package test;

import algorithm.BreadthFirstSearch;
import application.ReadGraph;
import org.jgrapht.Graph;
import org.junit.Before;
import org.junit.Test;
import sun.security.provider.certpath.Vertex;

import java.io.File;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * Created by Marcel on 23.10.2016.
 */
public class BreadthFirstSearchTest {

    ReadGraph gf;
    BreadthFirstSearch bfs;

    @Before
    public void setUp() {

        gf = new ReadGraph();
        File file = new File("C:\\Users\\Marcel\\Desktop\\Graphentheorie\\asserts\\graphTest.gka");
        gf.makeGraph(file);
    }

    @Test
    public void testRightOutput() {

        //Set<String> vertices = gf.getVertices();
        //Object[] array = vertices.toArray();
        gf.createGraph();
        Graph graph = gf.getGraph();
        Set<String> vertices = graph.vertexSet();
        Object[] array = vertices.toArray();
        bfs = new BreadthFirstSearch(gf.getGraph(), array[0].toString(), array[9].toString());

        assertEquals(
                "Der Kürzeste Weg von a nach j ist:\n" +
                        "a --> b --> j\n" +
                        "über 2 Kante(n).", bfs.doSearch());
    }


}
