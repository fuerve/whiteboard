package com.fuerve.whiteboard.milestone2.structures;

/**
 * Iterable interface.
 */
public interface Iterable<T> extends java.lang.Iterable<T> {
    /**
     * Gets an iterator into the iterable structure.
     * @return An iterator into the structure.
     */
    Iterator<T> iterator();
}
