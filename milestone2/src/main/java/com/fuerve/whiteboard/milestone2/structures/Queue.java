package com.fuerve.whiteboard.milestone2.structures;

import java.util.NoSuchElementException;

/**
 * Basic queue interface.
 */
public interface Queue<T> extends Collection<T> {
    /**
     * Removes the next item from the queue.
     * @return The dequeued item.
     * @throws NoSuchElementException The queue is empty.
     */
    T remove() throws NoSuchElementException;
    
    /**
     * Gets the next item in the queue without dequeuing it.
     * @return The next item in the queue.
     */
    T peek();
}
