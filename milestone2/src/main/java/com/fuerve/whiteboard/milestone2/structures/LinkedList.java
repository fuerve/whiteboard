package com.fuerve.whiteboard.milestone2.structures;

import java.util.NoSuchElementException;

/**
 * List implementation.
 */
public class LinkedList<T> implements List<T>, Deque<T> {
    /**
     * Initializes a new instance of LinkedList.
     */
    public LinkedList() {
        
    }
    
    /**
     * Initializes a new LinkedList and populates it with the contents of another
     * Collection.
     * @param other The Collection to copy into this LinkedList.
     */
    public LinkedList(final Collection<? extends T> other) {
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

    /* (non-Javadoc)
     * @see com.fuerve.whiteboard.milestone2.List#add(int, java.lang.Object)
     */
    @Override
    public void add(int index, T element) throws UnsupportedOperationException,
            ClassCastException, NullPointerException, IllegalArgumentException,
            IndexOutOfBoundsException {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see com.fuerve.whiteboard.milestone2.List#get(int)
     */
    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see com.fuerve.whiteboard.milestone2.List#set(int, java.lang.Object)
     */
    @Override
    public T set(int index, T element) throws ClassCastException,
            NullPointerException, IllegalArgumentException,
            IndexOutOfBoundsException {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see com.fuerve.whiteboard.milestone2.List#remove(int)
     */
    @Override
    public T remove(int index) throws UnsupportedOperationException,
            IndexOutOfBoundsException {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see com.fuerve.whiteboard.milestone2.List#indexOf(java.lang.Object)
     */
    @Override
    public int indexOf(Object element) throws ClassCastException,
            NullPointerException {
        // TODO Auto-generated method stub
        return 0;
    }

    /* (non-Javadoc)
     * @see com.fuerve.whiteboard.milestone2.List#lastIndexOf(java.lang.Object)
     */
    @Override
    public int lastIndexOf(Object element) throws ClassCastException,
            NullPointerException {
        // TODO Auto-generated method stub
        return 0;
    }

}
