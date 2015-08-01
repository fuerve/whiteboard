package com.fuerve.whiteboard.circularlist;

/**
 * Singly-linked circular list.
 *
 */
public class CircularList<T> {
    private Node<T> node;
    
    /**
     * Determines whether an instance of the given value exists anywhere in the list.
     * @param value The value for which to check.
     * @return True if a node of the given value exists anywhere in the list.
     */
    public boolean contains(final T value) {
        final Iterator<T> iter = getIterator();
        while (iter.hasNext()) {
            final T current = iter.next();
            if (current == value || current.equals(value)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Deletes ALL instances of the given value from this list.
     * @param value The value to delete.
     * @return True if at least one deletion occurred.
     */
    public boolean delete(final T value) {
        boolean deleted = false;

        if (node == null) {
            return false;
        }
        
        final Node<T> last = findPreviousNode(node);
        while (node != null && (node.getDatum() == value || node.getDatum().equals(value))) {
            deleteItemAtPosition(last, node);
            deleted |= true;
        }
        if (node == null) {
            return deleted;
        }
        
        Node<T> start = node;
        Node<T> current = node;

        if (start == null) {
            return false;
        }
        
        while (node != null && current.next != start) {
            if (current.getDatum() == value || current.getDatum().equals(value)) {
                deleteItemAtPosition(current, current.next);
                deleted |= true;
            }
            
            current = current.next;
        }
        
        return deleted;
    }
    
    private Node<T> findPreviousNode(final Node<T> target) {
        Node<T> current = target;
        
        if (current == null) {
            return null;
        }
        
        while (current.next != target) {
            current = current.next;
        }
        
        return current;
    }
    
    private void deleteItemAtPosition(final Node<T> previous, final Node<T> current) {
        if (previous == current) {
            node = null;
        } else {
            previous.next = current.next;
            if (node == current) {
                node = current.next;
            }
        }
    }
    
    /**
     * Inserts an item into the list after the first available instance of the given value after which to insert.
     * @param insertAfter The value after which to insert.  This can be deceptive, because multiple instances
     * of a single value may exist in the list, and there is no absolute ordinality to any given point in the ring.
     * Tread with care if you choose to insert multiple instances of the same value.
     * @param value The value to insert.
     */
    public void insert(final T insertAfter, final T value) {
        if (node == null) {
            node = new Node<T>(value, null);
            node.setNext(node);
            return;
        } else if (node.getDatum() == insertAfter || node.getDatum().equals(insertAfter)) {
            final Node<T> newNode = new Node<T>(value, node);
            node.next = newNode;
            return;
        } else {
            Node<T> current = node.next;
            while (current.next != node && (current.next.getDatum() != insertAfter && !current.next.getDatum().equals(insertAfter))) {
                current = current.next;
            }
            final Node<T> newNode = new Node<T>(value, current.next);
            current.next = newNode;
            return;
        }
    }
    
    /**
     * Gets an iterator that will traverse the entire list one time.
     * @return The iterator.
     */
    public Iterator<T> getIterator() {
        return new Iterator<T>(node);
    }
    
    /**
     * Represents a single node in the list.
     * @param <T> The contained type.
     */
    public static class Node<T> {
        private T datum;
        private Node<T> next;
        
        /**
         * Ctor.
         * @param ddatum The value to set for this node.
         * @param nnext A reference to the next item in the list.
         */
        public Node(final T ddatum, final Node<T> nnext) {
            datum = ddatum;
            next = nnext;
        }
        
        /**
         * Sets the value of this node.
         * @param ddatum The value to set.
         */
        public void setDatum(final T ddatum) {
            datum = ddatum;
        }
        
        /**
         * Gets the value of this node.
         * @return The value of this node.
         */
        public T getDatum() {
            return datum;
        }
        
        /**
         * Sets the next node in the list.
         * @param nnext The next node.
         */
        public void setNext(final Node<T> nnext) {
            next = nnext;
        }
        
        /**
         * Gets the next node in the list.
         * @return The next node.
         */
        public Node<T> getNext() {
            return next;
        }
    }
    
    /**
     * An iterator for CircularList.
     * @param <T> The type contained in the list.
     */
    public static class Iterator<T> {
        private Node<T> current;
        private final Node<T> start;
        private boolean started;
        
        /**
         * Ctor.
         * @param start The node at which to start traversing the list.
         */
        public Iterator(final Node<T> sstart) {
            current = sstart;
            start = sstart;
            started = false;
        }
        
        /**
         * Determines whether the list has been exhausted.
         * @return True if there is another item in the list.
         */
        public boolean hasNext() {
            return (current != null && !(started && current == start));
        }
        
        /**
         * Gets the next value in the list.
         * @return The next value in the list.
         */
        public T next() {
            if (current == null) {
                return null;
            } else {
                final T value = current.getDatum();
                current = current.getNext();
                started = true;
                return value;
            }
        }
    }
}
