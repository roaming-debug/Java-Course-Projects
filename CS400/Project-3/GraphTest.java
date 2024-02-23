// --== CS400 File Header Information ==--
// Name: Ezra Ge
// Email: ege2@wisc.edu
// Team: AA
// TA: BRIANNA COCHRAN
// Lecturer: Gary
// Notes to Grader: N/A

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests the implementation of CS400Graph for the individual component of
 * Project Three: the implementation of Dijsktra's Shortest Path algorithm.
 */
public class GraphTest {

    private CS400Graph<String> graph;
    
    /**
     * Instantiate graph from last week's shortest path activity.
     */
    @BeforeEach
    public void createGraph() {
        graph = new CS400Graph<>();
        // insert vertices A-F
        graph.insertVertex("A");
        graph.insertVertex("B");
        graph.insertVertex("C");
        graph.insertVertex("D");
        graph.insertVertex("E");
        graph.insertVertex("F"); // the vertex that was
        // insert edges from Week 11. Shortest Path Activity
        graph.insertEdge("A","B",6);
        graph.insertEdge("A","C",2);
        graph.insertEdge("A","D",5);
        graph.insertEdge("B","E",1);
        graph.insertEdge("B","C",2);
        graph.insertEdge("C","B",3);
        graph.insertEdge("C","F",1);
        graph.insertEdge("D","E",3);
        graph.insertEdge("E","A",4);
        graph.insertEdge("F","A",1);
        graph.insertEdge("F","D",1);
    }

    /**
     * Checks the distance/total weight cost from the vertex A to F.
     */
    @Test
    public void testPathCostAtoF() {
        assertTrue(graph.getPathCost("A", "F") == 3);
    }

    /**
     * Checks the distance/total weight cost from the vertex A to D.
     */
    @Test
    public void testPathCostAtoD() {
        assertTrue(graph.getPathCost("A", "D") == 4);
    }

    /**
     * Checks the ordered sequence of data within vertices from the vertex 
     * A to D.
     */
    @Test
    public void testPathAtoD() {
        assertTrue(graph.shortestPath("A", "D").toString().equals(
            "[A, C, F, D]"
        ));
    }

    /**
     * Checks the ordered sequence of data within vertices from the vertex 
     * A to F.
     */
    @Test
    public void testPathAtoF() {
        assertTrue(graph.shortestPath("A", "F").toString().equals(
            "[A, C, F]"
        ));
    }

    /**
     * Check the ending vertex of the highest path cost starting from D is B
     */
    @Test
    public void testFromDFurthestPathCost() {
        String furthest = null;
        int max = graph.getPathCost("D", "A");
        for (char i = 'B'; i <= 'F'; i++) {
            if(i == 'D') continue;
            int pathCost = graph.getPathCost("D", Character.toString(i));
            if(pathCost > max) {
                furthest = Character.toString(i);
                max = pathCost;
            }
        }
        assertEquals("B", furthest);
    }

    /**
     * Check the shortest path from D to B is the sequence that we expected
     */
    @Test
    public void testFromDFurthestPath() {
        assertTrue(graph.shortestPath("D", "B").toString().equals("[D, E, A, C, B]"));
    }

    /**
     * Check the path cost from E to F is the value we expected
     */
    @Test
    public void testPathCostEtoF() {
        assertTrue(graph.getPathCost("E", "F") == 7);
    }

    /**
     * Check the predecessor of the final vertex of the path from E to F
     */
    @Test
    public void testPredecessorFinalNodeEtoF() {
        List<String> path = graph.shortestPath("E", "F");
        assertTrue(path.get(path.size()-2).equals("C"));
    }

    /**
     * Check the path cost from B to F is the value we expected
     */
    @Test
    public void testPathCostBtoF() {
        assertTrue(graph.getPathCost("B", "F") == 3);
    }
}
