package com.fuerve.whiteboard.milestone2.algorithms;

import com.fuerve.whiteboard.milestone2.structures.Collection;
import com.fuerve.whiteboard.milestone2.structures.List;

/**
 * Collection utilities, such as sorting and searching.
 */
public final class Collections {
    /**
     * Hidden constructor.
     */
    private Collections() {
        
    }
    
    /**
     * Sorts a collection in the natural order of its elements.
     * @param collection The collection to sort.
     * @return The sorted collection.
     */
    public static Collection<?> sort(final Collection<?> collection) {
        // TODO
        return null;
    }
    
    /**
     * Given a sorted list, performs a binary search for a given element and returns its index
     * into the list.
     * @param list The list to search.
     * @param element The element for which to search.
     * @return The index of the element, or -1 if it does not exist.
     */
    public static <T> int binarySearch(final List<? extends Comparable<? super T>> list, final T element) {
        // TODO
        return -1;
    }
}
