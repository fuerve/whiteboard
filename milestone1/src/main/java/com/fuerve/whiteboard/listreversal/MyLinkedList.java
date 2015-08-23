package com.fuerve.whiteboard.listreversal;

import java.util.HashSet;
import java.util.Set;

/**
 * Small linked list implementation.
 */
public class MyLinkedList<T> {
    private Node<T> head;
    
    /**
     * Gets an iterator for this list.
     * @return An iterator for this list.
     */
    public Iterator<T> getIterator() {
        return new Iterator<T>(head);
    }
    
    /**
     * Tests whether a value is contained in the list.
     * @param value The value for which to check.
     * @return True if the value is a member of the list.
     */
    public boolean contains(final T value) {
        if (head == null) {
            return false;
        }
        
        Node<T> current = head;
        while (current != null) {
            if (current.datum == value) {
                // This should catch the case where they are both null.
                return true;
            } else if (current.datum == null || value == null) {
                current = current.next;
            } else if (current.datum.equals(value)) {
                return true;
            } else {
                current = current.next;
            }
        }
        
        return false;
    }
    
    /**
     * Inserts a value into the list.
     * @param insertAfter The value after which to insert.  If this value is not
     * contained in the list, the new value is inserted at the end of the list.
     * @param value The value to insert.
     */
    public void insert(final T insertAfter, final T value) {
        final Node<T> newNode = new Node<T>(value, null);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> current = head;
            Node<T> previous = head;
            while (current.next != null) {
                previous = current;
                current = current.next;
                if (previous.datum == insertAfter || previous.datum.equals(insertAfter)) {
                    previous.next = newNode;
                    newNode.next = current;
                    return;
                }
            }
            // If we get this far, we've traversed the whole list and the insert after value wasn't found.
            current.next = newNode;
        }
    }
    
    /**
     * Inserts a value at the end of the list.
     * @param value The value to insert.
     */
    public void append(final T value) {
        final Node<T> newNode = new Node<T>(value, null);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }
    
    /**
     * Deletes a value from the list.  
     * @param value The value to delete.  All instances of this value will be deleted.
     * @return True if one or more nodes were deleted from the list.
     */
    public boolean delete(final T value) {
        boolean result = false;
        if (head == null) {
            return false;
        }/* else if (head.datum == value || head.datum.equals(value)) {
            head = head.next;
            result |= true;
        }*/
        
        while (head != null && (head.datum == value || head.datum.equals(value))) {
            head = head.next;
            result |= true;
        }
        
        if (head == null) {
            return result;
        } else {
            Node<T> current = head;
            Node<T> previous = head;
            
            while (current.next != null) {
                previous = current;
                current = current.next;
                if (current.datum == value || current.datum.equals(value)) {
                    previous.next = current.next;
                    result |= true;
                }
            }
            
            if (current.datum == value || current.datum.equals(value)) {
                previous.next = null;
                result |= true;
            }
        }
        
        return result;
    }
    
    /**
     * Reverses this list.  The head of the list will be the tail of
     * the former list, and any subsequently created iterators will yield
     * results in the reverse order of the list as it was prior to calling
     * this method.
     */
    public void reverse() {
        if (head == null || head.next == null) {
            return;
        }
        
        Node<T> oldHead = head;
        Node<T> current = head.next;
        Node<T> oldNext = head.next;
        Node<T> previous = head;
        
        oldHead.next = null;
        
        while (current != null) {
            oldNext = current.next;
            current.next = previous;
            previous = current;
            current = oldNext;
            head = previous;
        }
    }
    
    @Override
    public String toString() {
        if (head == null) {
            return "";
        }
        
        final Set<Node<T>> seen = new HashSet<Node<T>>();
        final StringBuilder sb = new StringBuilder();
        Node<T> current = head;
        while (current != null) {
            if (seen.contains(current)) {
                return "Cycle detected";
            } else {
                sb.append(" " + current.datum);
                seen.add(current);
                current = current.next;
            }
        }
        
        return sb.toString();
    }
    
    /**
     * Nested class that represents a single node in the list.
     * @param <T> The type represented by each node in the list.
     */
    public static class Node<T> {
        private T datum;
        private Node<T> next;
        
        /**
         * Default ctor.
         */
        public Node() {
            
        }
        
        /**
         * Ctor with arguments to populate the node.
         * @param ddatum The value of the node.
         * @param nnext The next item in the list.
         */
        public Node(final T ddatum, final Node<T> nnext) {
            datum = ddatum;
            next = nnext;
        }
        
        /**
         * Sets the value of the node.
         * @param ddatum The target value of the node.
         */
        public void setDatum(final T ddatum) {
            datum = ddatum;
        }
        
        /**
         * Gets the value of the node.
         * @return The value of the node.
         */
        public T getDatum() {
            return datum;
        }
        
        /**
         * Sets the reference to the next item in the list.
         * @param nnext The target next item in the list.
         */
        public void setNext(final Node<T> nnext) {
            next = nnext;
        }
        
        /**
         * Gets the reference to the next item in the list.
         * @return The next item in the list.
         */
        public Node<T> getNext() {
            return next;
        }
    }
    
    /**
     * A simple iterator implementation for our simple linked list.
     * @param <T> The type of item contained in the node.
     */
    public static class Iterator<T> {
        private Node<T> current;
        
        /**
         * Initializes the iterator with the head of the list.
         * @param head A reference to the head of the list.
         */
        public Iterator(final Node<T> head) {
            current = head;
        }
        
        /**
         * Determines whether the iterator has any more elements over which to iterate.
         * @return True if another node exists.
         */
        public boolean hasNext() {
            if (current != null) {
                return true;
            } else {
                return false;
            }
        }
        
        /**
         * Gets the next value in the list.
         * @return The next value in the list.
         */
        public T next() {
            if (current != null) {
                final T result = current.getDatum();
                current = current.next;
                return result;
            } else {
                return null;
            }
        }
    }
}
