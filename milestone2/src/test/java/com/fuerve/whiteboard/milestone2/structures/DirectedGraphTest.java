package com.fuerve.whiteboard.milestone2.structures;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

/**
 * Tests for the DirectedGraph class.
 */
public class DirectedGraphTest {

    /**
     * Test method for {@link com.fuerve.whiteboard.milestone2.structures.DirectedGraph#DirectedGraph()}.
     */
    @Ignore // Write this test in the event that Graph becomes an interface.
    @Test
    public void testDirectedGraph() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link com.fuerve.whiteboard.milestone2.structures.DirectedGraph#getNumberOfVertices()}.
     */
    @Test
    public void testGetNumberOfVertices() {
        final DirectedGraph<Integer> target = new DirectedGraph<Integer>();
        assertEquals(0, target.getNumberOfVertices());
        target.addVertex("foo", 1);
        target.addVertex("bar", 2);
        assertEquals(2, target.getNumberOfVertices());
        target.addVertex("bar", 2); // Should basically be idempotent.
        assertEquals(2, target.getNumberOfVertices());
    }

    /**
     * Test method for {@link com.fuerve.whiteboard.milestone2.structures.DirectedGraph#getNumberOfEdges()}.
     */
    @Test
    public void testGetNumberOfEdges() {
        final DirectedGraph<Integer> target = new DirectedGraph<Integer>();
        assertEquals(0, target.getNumberOfEdges());
        target.addVertex("foo", 1);
        target.addVertex("bar", 2);
        assertEquals(0, target.getNumberOfEdges());
        target.addEdge("foo", "bar");
        assertEquals(1, target.getNumberOfEdges());
        target.addEdge("bar", "foo");
        assertEquals(2, target.getNumberOfEdges());
        target.addEdge("bar", "foo"); // Strictly speaking, edges need not be unique.
        assertEquals(3, target.getNumberOfEdges());
     }

    /**
     * Test method for {@link com.fuerve.whiteboard.milestone2.structures.DirectedGraph#contains(java.lang.String)}.
     */
    @Test
    public void testContains() {
        final DirectedGraph<Integer> target = new DirectedGraph<Integer>();
        assertFalse(target.contains("foo"));
        target.addVertex("foo", 1);
        target.addVertex("bar", 2);
        assertTrue(target.contains("foo"));
    }

    /**
     * Test method for {@link com.fuerve.whiteboard.milestone2.structures.DirectedGraph#addEdge(java.lang.String, java.lang.String)}.
     */
    @Test
    public void testAddEdgeToNonexistentVertex() {
        final DirectedGraph<Integer> target = new DirectedGraph<Integer>();
        target.addVertex("foo", 1);
        target.addVertex("bar", 2);
        target.addEdge("foo", "nope");
        assertEquals(0, target.getNumberOfEdges());
    }

    /**
     * Test method for {@link com.fuerve.whiteboard.milestone2.structures.DirectedGraph#iterator()}.
     */
    @Test
    public void testPreorderIterator() {
        final DirectedGraph<Integer> target = new DirectedGraph<Integer>();
        target.addVertex("foo", 4);
        target.addVertex("bar", 3);
        target.addVertex("baz", 2);
        target.addVertex("quux", 1);
        
        target.addEdge("quux", "baz");
        target.addEdge("quux", "bar");  // Dicey.  There are multiple topological orders here.
        target.addEdge("bar", "foo");
        
        final Iterator<Integer> iter = target.iterator("quux");
        assertTrue(iter.hasNext());
        for (int i = 1; i <= 4; i++) {
            assertEquals(new Integer(i), iter.next());
        }
        assertFalse(iter.hasNext());
    }
    
    /***/
    @Test
    public void testTopologicalOrderIterator() {
        final DirectedGraph<Integer> target = new DirectedGraph<Integer>();
        target.addVertex("foo", 1);
        target.addVertex("bar", 2);
        target.addVertex("baz", 3);
        target.addVertex("quux", 4);
        
        target.addEdge("quux", "baz");
        target.addEdge("quux", "bar");  // Dicey.  There are multiple topological orders here.
        target.addEdge("bar", "foo");
        target.addEdge("quux", "foo");
/*        target.addEdge("quux", "baz");
        target.addEdge("baz", "bar");
        target.addEdge("bar", "foo");*/
        
        final Iterator<Integer> iter = target.topologicalOrderIterator();
        assertTrue(iter.hasNext());
        for (int i = 1; i <= 4; i++) {
            assertEquals(new Integer(i), iter.next());
        }
        assertFalse(iter.hasNext());
    }
    
    /***/
    @Test
    public void testTopologicalOrderIteratorComplexGraph() {
        final DirectedGraph<Integer> target = new DirectedGraph<Integer>();
        target.addVertex("A", 1);
        target.addVertex("B", 2);
        target.addVertex("C", 3);
        target.addVertex("D", 4);
        target.addVertex("E", 5);
        target.addVertex("F", 6);
        
        target.addEdge("F", "A");
        target.addEdge("F", "E");
        target.addEdge("E", "D");
        target.addEdge("E", "C");
        target.addEdge("C", "A");
        target.addEdge("C", "B");
        target.addEdge("B", "A");
        
        final Iterator<Integer> iter = target.topologicalOrderIterator();
        assertTrue(iter.hasNext());
        for (int i = 1; i <= 6; i++) {
            assertEquals(new Integer(i), iter.next());
        }
        assertFalse(iter.hasNext());
    }
    
    /***/
    @Test
    public void testTopologicalOrderIteratorCycle() {
        final DirectedGraph<Integer> target = new DirectedGraph<Integer>();
        target.addVertex("A", 1);
        target.addVertex("B", 2);
        target.addVertex("C", 3);
        
        target.addEdge("A", "B");
        target.addEdge("B", "C");
        target.addEdge("C", "A");
        
        final Iterator<Integer> iter = target.topologicalOrderIterator();
        assertFalse(iter.hasNext());
    }

}
