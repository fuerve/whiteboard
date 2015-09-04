package com.fuerve.whiteboard.milestone2.structures;

import java.util.HashMap;
import java.util.Map;

/**
 * Disjoint set, or Union-Find data structure.
 */
public class DisjointSet<T> {
    private Map<T, Node<T>> map;
    
    // I'll be reading the description of this structure on Wikipedia et al and attempting to implement it.
    // I've implemented this before, but it's been a long time.
    public DisjointSet() {
        map = new HashMap<T, Node<T>>();
    }
    
    
    public void makeSet(final T value) {
        final Node<T> newNode = new Node<T>(value);
        newNode.setParent(newNode);
        map.put(value, newNode);
    }
    
    public Node<T> find(final T value) {
        if (map.containsKey(value)) {
            Node<T> current = map.get(value);
            if (current == null || current.getParent() == current) {
                return current;
            } else {
                while (current != null && current.getParent() != current) {
                    current = current.parent;
                }
                return current;
            }
        }
        return null;
    }
    
    public void union(final T x, final T y) {
        final Node<T> xRoot = find(x);
        final Node<T> yRoot = find(y);
        if (xRoot == null || yRoot == null) {
            return;
        } else {
            if (xRoot.getRank() < yRoot.getRank()) {
                xRoot.setParent(yRoot);
            } else if (xRoot.getRank() > yRoot.getRank()) {
                yRoot.setParent(xRoot);
            } else {
                yRoot.setParent(xRoot);
                xRoot.incrementRank();
            }
        }
    }
    
    /**
     * Container for a single member of the disjoint set.
     *
     * @param <T> The type contained within the structure.
     */
    public static class Node<T> {
        private Node<T> parent;
        private T value;
        private int rank;
        
        /**
         * Default constructor.
         */
        public Node() {
            parent = this;
            rank = 0;
        }
        
        /**
         * Initializes a new instance of Node with a value.
         * @param vvalue The value of this node.
         */
        public Node(final T vvalue) {
            this();
            value = vvalue;
        }
        
        /**
         * Gets this node's parent.
         * @return The parent node.
         */
        public Node<T> getParent() {
            return parent;
        }
        
        /**
         * Sets this node's parent.
         * @param pparent The parent node.
         */
        public void setParent(final Node<T> pparent) {
            parent = pparent;
        }
        
        /**
         * Gets this node's value.
         * @return The value of this node.
         */
        public T getValue() {
            return value;
        }
        
        /**
         * Sets this node's value.
         * @param vvalue The value of this node.
         */
        public void setValue(final T vvalue) {
            value = vvalue;
        }
        
        /**
         * Gets the rank of this node.
         * @return This node's rank.
         */
        public int getRank() {
            return rank;
        }
        
        /**
         * Sets the rank of this node.
         * @param rrank This node's rank.
         */
        public void setRank(final int rrank) {
            rank = rrank;
        }
        
        /**
         * Increments this node's rank by 1.
         */
        public void incrementRank() {
            rank++;
        }
        
        @Override
        public String toString() {
            return "Node: " + value + "Rank: " + rank + " Parent " + parent.getValue();
        }
    }
}
