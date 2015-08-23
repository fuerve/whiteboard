package com.fuerve.whiteboard.milestone2.structures;

/**
 * Interface for a collection.  Modeled after Java's own set of structures,
 * but the code is derived only from the interface specification as an exercise.
 */
public interface Collection<T> extends Iterable<T> {
    /**
     * Gets the size of the collection.
     * @return The size of the collection.
     */
    int size();
    
    /**
     * Gets whether the collection is empty.
     * @return True if the collection is empty.
     */
    boolean isEmpty();
    
    /**
     * Gets whether an object is contained in the collection.
     * @param element The element for which to search.
     * @return True if the element exists in the collection.
     */
    boolean contains(final Object element);
    
    /**
     * Adds an element to the collection.
     * @param element The element to add.
     * @return True if the element was added.
     */
    boolean add(final T element) throws UnsupportedOperationException, ClassCastException, NullPointerException,
        IllegalArgumentException;
    
    /**
     * Removes an element from the collection.
     * @param element The element to remove from the collection.
     * @return True if the element was removed.
     */
    boolean remove(final T element) throws UnsupportedOperationException, ClassCastException, NullPointerException;
    
    /**
     * Gets an iterator into this collection.
     * @return An iterator into this collection.
     */
    Iterator<T> iterator();
}
