package com.fuerve.whiteboard.milestone2.structures;

/**
 * Iterator interface.
 */
public interface Iterator<T> extends java.util.Iterator<T> {
    /**
     * Gets whether the iterable has another element.
     * @return True if there is another element in the iterable.
     */
    boolean hasNext();
    
    /**
     * Gets the next element.
     * @return The next element, or null if none exists.
     */
    T next();
}
