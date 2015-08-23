package com.fuerve.whiteboard.milestone2.structures;

import java.util.NoSuchElementException;

/**
 * Double-ended queue interface (I need both stacks and queues).
 * I'm not as kind as Java's implementation, which has the offer and poll semantics.
 */
public interface Deque<T> extends Queue<T> {
    /**
     * Pushes an element onto the head of the structure as if it were a stack.
     * @param element The element to push.
     */
    void push(final T element) throws ClassCastException, NullPointerException, IllegalArgumentException;
    
    /**
     * Pops an element from the head of the structure as if it were a stack.
     * @return The head of the stack.
     */
    T pop() throws NoSuchElementException;
}
