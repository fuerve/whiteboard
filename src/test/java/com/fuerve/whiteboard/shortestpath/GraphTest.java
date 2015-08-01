package com.fuerve.whiteboard.shortestpath;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

/**
 * Tests for the Graph class.
 */
public class GraphTest {
    /**
     * Test method for {@link com.fuerve.whiteboard.shortestpath.Graph#numberOfVertices()}.
     */
    @Test
    public void testNumberOfVertices() {
        final Graph<Integer> target = new Graph<Integer>();
        assertEquals(0, target.numberOfVertices());
        target.addVertex("foo", 1);
        target.addVertex("bar", 2);
        assertEquals(2, target.numberOfVertices());
    }

    /**
     * Test method for {@link com.fuerve.whiteboard.shortestpath.Graph#numberOfEdges()}.
     */
    @Test
    public void testNumberOfEdges() {
        final Graph<Integer> target = new Graph<Integer>();
        assertEquals(0, target.numberOfEdges());
        target.addVertex("foo", 1);
        target.addVertex("bar", 2);
        assertEquals(0, target.numberOfEdges());
        target.addEdge("foo", "bar");
        assertEquals(1, target.numberOfEdges());
    }

    /**
     * Test method for {@link com.fuerve.whiteboard.shortestpath.Graph#addVertex(java.lang.String, java.lang.Object)}.
     */
    @Test
    public void testAddVertex() {
        final Graph<Integer> target = new Graph<Integer>();
        target.addVertex("foo", 1);
        assertTrue(target.contains("foo"));
    }
    
    /***/
    @Test
    public void testAddPreexistingVertex() {
        final Graph<Integer> target = new Graph<Integer>();
        target.addVertex("foo", 1);
        target.addVertex("foo", 1);
        assertEquals(1, target.numberOfVertices());
        assertTrue(target.contains("foo"));
    }
    
    /***/
    @Test
    public void testAddNullVertex() {
        final Graph<Integer> target = new Graph<Integer>();
        target.addVertex(null, 1);
        assertEquals(1, target.numberOfVertices());
        assertTrue(target.contains(null));
    }

    /**
     * Test method for {@link com.fuerve.whiteboard.shortestpath.Graph#addEdge(java.lang.String, java.lang.String)}.
     */
    @Test
    public void testAddEdge() {
        final Graph<Integer> target = new Graph<Integer>();
        target.addVertex("foo", 1);
        target.addVertex("bar", 2);
        assertTrue(target.addEdge("foo", "bar"));
        assertEquals(1, target.numberOfEdges());
    }
    
    /***/
    @Test
    public void testAddEdgeNonexistentVertex() {
        final Graph<Integer> target = new Graph<Integer>();
        target.addVertex("foo", 1);
        assertFalse(target.addEdge("foo", "bar"));
        assertEquals(0, target.numberOfEdges());
    }
    
    /***/
    @Test
    public void testAddEdgeTwoNonexistentVertices() {
        final Graph<Integer> target = new Graph<Integer>();
        assertEquals(0, target.numberOfVertices());
        assertFalse(target.addEdge("foo", "bar"));
        assertEquals(0, target.numberOfEdges());
    }
    
    /***/
    @Test
    public void testShortestPath() {
        final Graph<Integer> target = new Graph<Integer>();
        target.addVertex("foo", 1);
        target.addVertex("bar", 2);
        target.addVertex("baz", 3);
        target.addVertex("quux", 4);
        target.addVertex("frob", 5);
        
        target.addEdge("foo", "bar");
        target.addEdge("bar", "baz");
        target.addEdge("baz", "quux");
        target.addEdge("foo", "frob");
        target.addEdge("frob", "quux");
        
        final List<String> path = target.shortestPath("foo", "quux");
        assertEquals(3, path.size());
        assertEquals("foo", path.get(0));
        assertEquals("frob", path.get(1));
        assertEquals("quux", path.get(2));
    }
    
    /***/
    public void testShortestPathNoPathExists() {
        final Graph<Integer> target = new Graph<Integer>();
        target.addVertex("foo", 1);
        target.addVertex("bar", 2);
        target.addVertex("baz", 3);
        
        target.addEdge("foo", "bar");
        
        assertNull(target.shortestPath("foo", "baz"));
    }

}
