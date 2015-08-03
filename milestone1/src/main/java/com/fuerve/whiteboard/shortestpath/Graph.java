package com.fuerve.whiteboard.shortestpath;

import java.util.List;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Implementation of an undirected, nonweighted graph.
 */
public class Graph<T> {
    private int E;
    private final Map<String, Vertex<T>> vertices;
    private final Map<String, Set<String>> adjacencies;
    
    /**
     * Initializes a new undirected graph.
     */
    public Graph() {
        E = 0;
        vertices = new HashMap<String, Vertex<T>>();
        adjacencies = new HashMap<String, Set<String>>();
    }
    
    /**
     * Gets the number of vertices in the graph.
     * @return The number of vertices.
     */
    public int numberOfVertices() {
        return vertices.size();
    }
    
    /**
     * Gets the numer of edges in the graph.
     * @return The number of edges.
     */
    public int numberOfEdges() {
        return E;
    }
    
    /**
     * Checks to see whether a vertex of a given name exists in this graph.
     * @param name The name of the vertex.
     * @return True if a vertex of that name exists.
     */
    public boolean contains(final String name) {
        return vertices.containsKey(name);
    }
    
    /**
     * Adds a vertex to the graph.
     * @param name The name of the vertex.
     * @param value The value of the vertex.
     */
    public void addVertex(final String name, final T value) {
        if (vertices.containsKey(name)) {
            vertices.get(name).datum = value;
        } else {
            final Vertex<T> newNode = new Vertex<T>(name, value);
            vertices.put(name, newNode);
        }
    }
    
    /**
     * Adds an edge between two vertices.  This is an undirected graph,
     * so all edges are bidirectional.
     * @param v The first vertex.
     * @param w The second vertex.
     * @return True if the edge was added.  Asking to add an edge between
     * vertices that do not exist will result in a false.
     */
    public boolean addEdge(final String v, final String w) {
        if (vertices.containsKey(v) && vertices.containsKey(w)) {
            final Vertex<T> vNode = vertices.get(v);
            final Vertex<T> wNode = vertices.get(w);
            
            establishAdjacencyList(vNode);
            establishAdjacencyList(wNode);
            
            boolean addedBoth = true;
            addedBoth &= !adjacencies.get(vNode.name).contains(wNode.name);
            addedBoth &= !adjacencies.get(wNode.name).contains(vNode.name);

            adjacencies.get(wNode.name).add(vNode.name);
            adjacencies.get(vNode.name).add(wNode.name);
            
            if (addedBoth) {
                E++;
            }
            
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Gets the names of all vertices in this graph.
     * @return The names of all vertices.
     */
    public Set<String> getVertices() {
        return vertices.keySet();
    }
    
    /**
     * Gets the set of adjacencies for a given vertex.
     * @param name The name of the vertex.
     * @return The set of adjacencies.
     */
    public Set<String> getAdjacencies(final String name) {
        return adjacencies.get(name);
    }
    
    /**
     * Boiler plate to create a new adjacencies list for a given vertex.
     * @param vertex The vertex in question.
     */
    private void establishAdjacencyList(final Vertex<T> vertex) {
        if (!adjacencies.containsKey(vertex.name)) {
            adjacencies.put(vertex.name, new HashSet<String>());
        }
    }
    
    /**
     * Given a source and a destination, returns the shortest path in a list
     * of names of each vertex in the traversal.
     * @param v The source vertex.
     * @param w The destination vertex.
     * @return The shortest path between them, if any.
     */
    public List<String> shortestPath(final String v, final String w) {
        final TraversalContext context = breadthFirstSearch(v);
        
        if (!context.marked.contains(w)) {
            return null;
        }
        
        final Deque<String> path = new ArrayDeque<String>();
        String x;
        for (x = w; context.distanceTo.get(x) != 0; x = context.edgeTo.get(x)) {
            path.push(x);
        }
        path.push(x);
        
        final List<String> result = new ArrayList<String>();
        for (final String node : path) {
            result.add(node);
        }
        return result;
    }
    
    /**
     * Performs a breadth-first traversal of the graph, starting from the source point.
     * @param source The source from which to traverse the graph.
     * @return context The traversal context.
     */
    private TraversalContext breadthFirstSearch(final String source) {
        final Deque<String> queue = new ArrayDeque<String>();
        final TraversalContext context = new TraversalContext(source);
        context.marked.add(source);
        queue.add(source);
        
        while (!queue.isEmpty()) {
            final String v = queue.remove();
            for (final String w : adjacencies.get(v)) {
                if (!context.marked.contains(w)) {
                    context.edgeTo.put(w, v);
                    context.distanceTo.put(w, context.distanceTo.get(v) + 1);
                    context.marked.add(w);
                    queue.add(w);
                }
            }
        }
        
        return context;
    }
    
    /**
     * Container for a single node on the graph.
     * @param <T> The contained type.
     */
    public static class Vertex<T> {
        public String name;
        public T datum;
        
        /**
         * Ctor.
         * @param nname The name of the vertex.
         * @param ddatum The value of the vertex.
         */
        public Vertex(final String nname, final T ddatum) {
            name = nname;
            datum = ddatum;
        }
    }
    
    /**
     * Container for information about a given attempt to determine shortest path
     * between two vertices.
     */
    private class TraversalContext {
        public String source;
        public Set<String> marked;
        public Map<String, String> edgeTo;
        public Map<String, Integer> distanceTo;
        
        /**
         * Ctor.
         * @param ssource The intended source vertex from which to traverse the graph.
         */
        public TraversalContext(final String ssource) {
            source = ssource;
            marked = new HashSet<String>();
            edgeTo = new HashMap<String, String>();
            distanceTo = new HashMap<String, Integer>();
            
            for (final String name : vertices.keySet()) {
                distanceTo.put(name, Integer.MAX_VALUE);
            }
            distanceTo.put(source, 0);
        }
    }
}
