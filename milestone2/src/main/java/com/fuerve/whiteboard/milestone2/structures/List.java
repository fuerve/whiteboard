package com.fuerve.whiteboard.milestone2.structures;

/**
 * Interface for a list structure.  Modeled after Java's own set of structures,
 * but the code is derived only from the interface specification as an exercise.
 */
public interface List<T> extends Collection<T> {
    /**
     * Adds an element to the list at a given index.
     * @param index The index at which to insert the element.
     * @param element The element to insert.
     */
    void add(final int index, final T element) throws UnsupportedOperationException, ClassCastException, NullPointerException,
        IllegalArgumentException, IndexOutOfBoundsException;
    
    /**
     * Gets an element from a given index into the list.
     * @param index The index at which to obtain the element.
     * @return The element at the index.
     */
    T get(final int index) throws IndexOutOfBoundsException;
    
    /**
     * Sets an element at a given index.
     * @param index The index to set.
     * @param element The element to set.
     * @return The element that was replaced, if any.
     */
    T set(final int index, final T element) throws ClassCastException, NullPointerException, IllegalArgumentException,
        IndexOutOfBoundsException;
    
    /**
     * Removes an element at a given index.
     * @param index The index of the element to remove.
     * @return The value that was removed, if any.
     */
    T remove(final int index) throws UnsupportedOperationException, IndexOutOfBoundsException;
    
    /**
     * Gets the index of a given object in this collection, if it exists.
     * @param element The object for which to search.
     * @return The index of the object, or -1 if it does not exist in the list.
     */
    int indexOf(final Object element) throws ClassCastException, NullPointerException;
    
    /**
     * Gets the last index of a given object in this collection, if it exists.
     * @param element The object for which to search.
     * @return The index of the object, or -1 if it does not exist in the list.
     */
    int lastIndexOf(final Object element) throws ClassCastException, NullPointerException;
}
