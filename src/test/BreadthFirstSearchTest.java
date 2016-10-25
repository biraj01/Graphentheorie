package test;

import algorithm.BreadthFirstSearch;
import application.ReadGraphFile;
import org.jgrapht.Graph;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Marcel on 23.10.2016.
 */
public class BreadthFirstSearchTest {

    ReadGraphFile gf;
    BreadthFirstSearch bfs;

    @Before
    public void setUp(){gf = new ReadGraphFile("bsp/bsp1.graph");}
    @Test
    public void testRightOutPut(){

    }
}
