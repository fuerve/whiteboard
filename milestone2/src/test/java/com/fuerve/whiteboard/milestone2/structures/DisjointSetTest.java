package com.fuerve.whiteboard.milestone2.structures;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Tests for the DisjointSet class.
 */
public class DisjointSetTest {

    @Test
    public void testGraphCycles() {
        final List<String> vertices = new ArrayList<String>();
        final List<Edge> edges = new ArrayList<Edge>();
        final DisjointSet<String> target = new DisjointSet<String>();
        
        vertices.add("A");
        vertices.add("B");
        vertices.add("C");
        vertices.add("D");
        edges.add(new Edge("A", "B"));
        edges.add(new Edge("B", "C"));
        edges.add(new Edge("C", "A"));
        edges.add(new Edge("C", "D"));
        
        for (final String vertex : vertices) {
            target.makeSet(vertex);
        }
        
        boolean found = false;
        
        for (int i = 0; i < edges.size(); i++) {
            final String x = target.find(edges.get(i).source).getValue();
            final String y = target.find(edges.get(i).destination).getValue();
            if ((x != null && y != null) && y.equals(x)) {
                found = true;
                break;
            } else {
                target.union(x, y);
            }
        }
        
        assertTrue(found);
    }
    
    @Test
    public void testGraphNoCycles() {
        final List<String> vertices = new ArrayList<String>();
        final List<Edge> edges = new ArrayList<Edge>();
        final DisjointSet<String> target = new DisjointSet<String>();
        
        vertices.add("A");
        vertices.add("B");
        vertices.add("C");
        vertices.add("D");
        edges.add(new Edge("A", "B"));
        edges.add(new Edge("B", "C"));
        edges.add(new Edge("C", "D"));
        
        for (final String vertex : vertices) {
            target.makeSet(vertex);
        }
        
        boolean found = false;
        
        for (int i = 0; i < edges.size(); i++) {
            final String x = target.find(edges.get(i).source).getValue();
            final String y = target.find(edges.get(i).destination).getValue();
            if ((x != null && y != null) && y.equals(x)) {
                found = true;
                break;
            } else {
                target.union(x, y);
            }
        }
        
        assertFalse(found);
    }
    
    private static class Edge {
        public String source;
        public String destination;
        
        public Edge(final String ssource, final String ddestination) {
            source = ssource;
            destination = ddestination;
        }
    }

}
