package com.fuerve.whiteboard.milestone2.structures;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * Directed graph.
 */
public class DirectedGraph<T> implements Iterable<T> {
    // The goal here is to build a directed, acyclic graph and a depth-first iterator.
    // This sort of thing is essential in any system that tracks dependencies, such as
    // build systems.  This implementation is intended to be general-purpose, though
    // still fairly minimal.
    // Basing this closely on an undirected graph implementation I did a few weeks ago
    // (see milestone 1).
    private int E;
    private final Map<String, Vertex<T>> vertices;
    private final Map<String, List<String>> adjacencies;
    private final Map<Vertex<T>, Integer> inDegree;
    
    /**
     * Ctor.
     */
    public DirectedGraph() {
        E = 0;
        vertices = new HashMap<String, Vertex<T>>();
        adjacencies = new HashMap<String, List<String>>();
        inDegree = new HashMap<Vertex<T>, Integer>();
    }
    
    /**
     * Gets the number of vertices in the graph.
     * @return The number of vertices in the graph.
     */
    public int getNumberOfVertices() {
        return vertices.size();
    }
    
    /**
     * Gets the number of edges in the graph.
     * @return The number of edges in the graph.
     */
    public int getNumberOfEdges() {
        return E;
    }
    
    /**
     * Gets whether a vertex of the given name is contained in this graph.
     * @param name The name of the vertex.
     * @return True if the vertex is contained in the graph.
     */
    public boolean contains(final String name) {
        return vertices.containsKey(name);
    }
    
    /**
     * Adds a single vertex to the graph.
     * @param name The name of the vertex.
     * @param value The value of the vertex.
     */
    public void addVertex(final String name, final T value) {
        final Vertex<T> vertex = new Vertex<T>(name, value);
        vertices.put(name, vertex);
        establishAdjacencyList(vertex);
        incrementInDegree(vertex);
    }
    
    /**
     * Adds an edge between two vertices of the graph.  These edges
     * are directed.
     * @param source The source of the edge.
     * @param destination The destination of the edge.
     */
    public void addEdge(final String source, final String destination) {
        if (vertices.containsKey(source) && vertices.containsKey(destination)) {
            final Vertex<T> sourceVertex = vertices.get(source);
            final Vertex<T> destinationVertex = vertices.get(destination);
            final List<String> currentAdjacencies = establishAdjacencyList(sourceVertex);
            currentAdjacencies.add(destination);
            incrementInDegree(destinationVertex);
            E++;
        }
    }
    
    /**
     * Helper method to build the adjacency list for a given vertex.
     * @param vertex The vertex for which to build the adjacency list.
     */
    private List<String> establishAdjacencyList(final Vertex<T> vertex) {
        if (!adjacencies.containsKey(vertex.name)) {
            adjacencies.put(vertex.name, new ArrayList<String>());
        }
        return adjacencies.get(vertex.name);
    }
    
    /**
     * Increments the in-degree of a vertex.  If this is the first time
     * that this method is being called on a given vertex, the in-degree
     * starts at 0.
     * @param vertex The vertex or which to set the in-degree.
     */
    private void incrementInDegree(final Vertex<T> vertex) {
        if (!inDegree.containsKey(vertex)) {
            inDegree.put(vertex, 0);
        } else {
            inDegree.put(vertex, inDegree.get(vertex) + 1);
        }
    }
    

    @Override
    public Iterator<T> iterator() {
        return preorderIterator();
    }
    
    public Iterator<T> iterator(final String origin) {
        return preorderIterator(origin);
    }
    
    public Iterator<T> preorderIterator() {
        // Default to iterating from the first vertex added to the graph.
        return preorderIterator(vertices.keySet().toArray(new String[0])[0]);
    }
    
    public Iterator<T> preorderIterator(final String origin) {
        return new PreorderDepthFirstIterator<T>(this, vertices.get(origin));
    }
    
    public Iterator<T> topologicalOrderIterator() {
//        return topologicalOrderIterator(vertices.keySet().toArray(new String[0])[0]);
        return new TopologicalOrderIterator<T>(this);
    }
    
    /*public Iterator<T> topologicalOrderIterator(final String origin) {
        return new TopologicalOrderIterator<T>(this, vertices.get(origin));
    }*/
    
    /**
     * Container class for a single vertex.
     * @param <T> The type contained in the vertex.
     */
    public static class Vertex<T> {
        public String name;
        public T value;
        
        /**
         * Ctor.
         * @param nname The name of the vertex.
         * @param vvalue The value of the vertex.
         */
        public Vertex(final String nname, final T vvalue) {
            name = nname;
            value = vvalue;
        }
    }
    
    /**
     * Depth-first iterator implementation for traversal of the directed graph.
     * @param <T> The type contained in each vertex.
     */
    public static class PreorderDepthFirstIterator<T> implements Iterator<T> {
        private final DirectedGraph<T> graph;
        private final Set<Vertex<T>> visited;
        private final Deque<Iterator<String>> stack;
        private Vertex<T> next;
        
        /**
         * Ctor.
         * @param ggraph The graph to which this iterator applies.
         */
        public PreorderDepthFirstIterator(final DirectedGraph<T> ggraph, final Vertex<T> origin) {
            graph = ggraph;
            visited = new HashSet<Vertex<T>>();
            stack = new ArrayDeque<Iterator<String>>();

            if (origin == null) {
                throw new IllegalArgumentException();
            } else {
                stack.push(graph.adjacencies.get(origin.name).iterator());
            }
            next = origin;
        }
        
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public T next() {
            if (next == null) {
                throw new NoSuchElementException();
            }
            
            // Borrowing heavily from an online implementation, because this is
            // something I've always had a hard time figuring out.
            // See the first reply to this for a great example implementation of an iterative graph traversal.
            // http://codereview.stackexchange.com/questions/48518/depth-first-search-breadth-first-search-implementation
            try {
                visited.add(next);
                return next.value;
            } finally {
                advanceToNext();
            }
        }
        
        private void advanceToNext() {
            Iterator<String> adj = stack.peek();
            do {
                while (!adj.hasNext()) {
                    stack.pop();
                    if (stack.isEmpty()) {
                        next = null;
                        return;
                    }
                    adj = stack.peek();
                }
                
                next = graph.vertices.get(adj.next());
            } while (visited.contains(next));
            // Push all adjacencies of the current node.
            stack.push(graph.adjacencies.get(next.name).iterator());
        }
        
    }
    
    /**
     * Depth-first iterator implementation for traversal of a topological ordering
     * of the graph.
     * @param <T> The type contained in each vertex.
     */
    public static class TopologicalOrderIterator<T> implements Iterator<T> {
        private final DirectedGraph<T> graph;
        private final Deque<Vertex<T>> order;
        private final Map<Vertex<T>, Integer> inDegree;
        private final Deque<Vertex<T>> queue;
        
        /**
         * Ctor.
         * @param ggraph The graph to which this iterator applies.
         */
        public TopologicalOrderIterator(final DirectedGraph<T> ggraph /*, final Vertex<T> origin */) {
            graph = ggraph;
            order = new ArrayDeque<Vertex<T>>();
            queue = new ArrayDeque<Vertex<T>>();
            inDegree = new HashMap<Vertex<T>, Integer>();
            inDegree.putAll(graph.inDegree);
            
            for (final Entry<Vertex<T>, Integer> entry : inDegree.entrySet()) {
                if (entry.getValue().equals(0)) {
                    queue.add(entry.getKey());
                    order.push(entry.getKey());
                }
            }
            while (!queue.isEmpty()) {
                //graph.adjacencies.get(key)
                final List<String> adj = graph.adjacencies.get(queue.remove().name);
                for (final String adjacency : adj) {
                    final Vertex<T> vertex = graph.vertices.get(adjacency);
                    decrementInDegree(vertex);
                    if (inDegree.get(vertex) == 0 && !order.contains(vertex)) {
                        order.push(vertex);
                        queue.add(vertex);
                    }
                }
            }
        }
        
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasNext() {
            return !order.isEmpty();
        }

        @Override
        public T next() {
            if (order == null || order.isEmpty()) {
                throw new NoSuchElementException();
            }
            
            return order.pop().value;
        }
        
        private void decrementInDegree(final Vertex<T> vertex) {
            final int oldValue = inDegree.get(vertex);
            final int newValue = oldValue - 1;
            inDegree.put(vertex, newValue);
        }

    }
}
