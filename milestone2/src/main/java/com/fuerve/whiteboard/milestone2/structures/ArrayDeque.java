package com.fuerve.whiteboard.milestone2.structures;

import java.util.NoSuchElementException;

/**
 * Double-ended queue implementation.
 */
public class ArrayDeque<T> implements Deque<T> {
    /**
     * Initializes a new instance of ArrayDeque.
     */
    public ArrayDeque() {
        
    }
    
    /**
     * Initializes a new instance of ArrayDeque and populates it with
     * the contents of another Collection.
     * @param other The Collection to copy.
     */
    public ArrayDeque(final Collection<? extends T> other) {
        // TODO
    }
    
    /**
     * Initializes a new instance of ArrayDeque with an initial capacity.
     * @param ccapacity The initial capacity.
     */
    public ArrayDeque(final int ccapacity) {
        // TODO
    }
    
    /* (non-Javadoc)
     * @see com.fuerve.whiteboard.milestone2.Collection#size()
     */
    @Override
    public int size() {
        // TODO Auto-generated method stub
        return 0;
    }

    /* (non-Javadoc)
     * @see com.fuerve.whiteboard.milestone2.Collection#isEmpty()
     */
    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return false;
    }

    /* (non-Javadoc)
     * @see com.fuerve.whiteboard.milestone2.Collection#contains(java.lang.Object)
     */
    @Override
    public boolean contains(Object element) {
        // TODO Auto-generated method stub
        return false;
    }

    /* (non-Javadoc)
     * @see com.fuerve.whiteboard.milestone2.Collection#add(java.lang.Object)
     */
    @Override
    public boolean add(T element) throws UnsupportedOperationException,
            ClassCastException, NullPointerException, IllegalArgumentException {
        // TODO Auto-generated method stub
        return false;
    }

    /* (non-Javadoc)
     * @see com.fuerve.whiteboard.milestone2.Collection#remove(java.lang.Object)
     */
    @Override
    public boolean remove(T element) throws UnsupportedOperationException,
            ClassCastException, NullPointerException {
        // TODO Auto-generated method stub
        return false;
    }

    /* (non-Javadoc)
     * @see com.fuerve.whiteboard.milestone2.Collection#iterator()
     */
    @Override
    public Iterator<T> iterator() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see com.fuerve.whiteboard.milestone2.Deque#remove()
     */
    @Override
    public T remove() throws NoSuchElementException {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see com.fuerve.whiteboard.milestone2.Deque#peek()
     */
    @Override
    public T peek() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see com.fuerve.whiteboard.milestone2.Deque#push(java.lang.Object)
     */
    @Override
    public void push(T element) throws ClassCastException,
            NullPointerException, IllegalArgumentException {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see com.fuerve.whiteboard.milestone2.Deque#pop()
     */
    @Override
    public T pop() throws NoSuchElementException {
        // TODO Auto-generated method stub
        return null;
    }

}
