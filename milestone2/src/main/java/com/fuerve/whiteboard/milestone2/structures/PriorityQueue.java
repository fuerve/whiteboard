package com.fuerve.whiteboard.milestone2.structures;

import java.util.NoSuchElementException;

/**
 * Priority queue implementation
 */
public class PriorityQueue<T> implements Queue<T> {
    private static final int DEFAULT_CAPACITY = 16;
    private static final int GROWTH_FACTOR = 2;
    
    private T[] store;
    private int size;
    
    /**
     * Ctor.
     */
    public PriorityQueue() {
        this(DEFAULT_CAPACITY);
    }
    
    /**
     * Copy ctor.
     */
    @SuppressWarnings("unchecked")
    public PriorityQueue(Collection<? extends T> other) {
        size = other.size();
        store = (T[]) new Object[size];
        int index = 0;
        for (Iterator<? extends T> iter = other.iterator(); iter.hasNext(); ) {
            store[index] = iter.next();
            index++;
        }
        minHeapify();
    }
    
    @SuppressWarnings("unchecked")
    public PriorityQueue(final int initialCapacity) {
        store = (T[]) new Object[initialCapacity];
        size = 0;
    }
    
    /* (non-Javadoc)
     * @see com.fuerve.whiteboard.milestone2.structures.Collection#size()
     */
    @Override
    public int size() {
        return size;
    }

    /* (non-Javadoc)
     * @see com.fuerve.whiteboard.milestone2.structures.Collection#isEmpty()
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /* (non-Javadoc)
     * @see com.fuerve.whiteboard.milestone2.structures.Collection#contains(java.lang.Object)
     */
    @Override
    public boolean contains(Object element) {
        return (findInArray(element) != -1);
    }

    /* (non-Javadoc)
     * @see com.fuerve.whiteboard.milestone2.structures.Collection#add(java.lang.Object)
     */
    @Override
    public boolean add(T element) throws UnsupportedOperationException,
            ClassCastException, NullPointerException, IllegalArgumentException {
        if (size >= store.length) {
            growStore();
        }
        
        store[size] = element;
        int current = size;
        size++;

        while (current > 0) {
            final int parent = (current - 1) >>> 1;
            if (greaterThanOrEqual(current, parent)) {
                break;
            }
            swap(current, parent);
            current = parent;
        }

        return true;
    }
    
    /* (non-Javadoc)
     * @see com.fuerve.whiteboard.milestone2.structures.Collection#remove(java.lang.Object)
     */
    @Override
    public boolean remove(T element) throws UnsupportedOperationException,
            ClassCastException, NullPointerException {
        if (size <= 0) {
            throw new IndexOutOfBoundsException();
        } else {
            final int index = findInArray(element);
            if (index == -1) {
                return false;
            } else {
                size--;
                swap(index, size);
                store[size] = null;
                minHeapify();
                return true;
            }
        }
    }

    /* (non-Javadoc)
     * @see com.fuerve.whiteboard.milestone2.structures.Collection#iterator()
     */
    @Override
    public Iterator<T> iterator() {
        return new PriorityQueueIterator<T>(store, size);
    }

    /* (non-Javadoc)
     * @see com.fuerve.whiteboard.milestone2.structures.Queue#remove()
     */
    @Override
    public T remove() throws NoSuchElementException {
        final T value = store[0];
        size--;
        if (size == 0) {
            store[0] = null;
        } else {
            store[0] = store[size];
            store[size] = null;
        }
        minHeapify();
        return value;
    }

    /* (non-Javadoc)
     * @see com.fuerve.whiteboard.milestone2.structures.Queue#peek()
     */
    @Override
    public T peek() {
        return store[0];
    }
    
    private void growStore() {
        @SuppressWarnings("unchecked")
        final T[] newStore = (T[]) new Object[store.length * GROWTH_FACTOR];
        for (int i = 0; i < store.length; i++) {
            newStore[i] = store[i];
        }
        store = newStore;
    }
    
    /**
     * Attempts to find the index of an object in the array.
     * @param element The element to find.
     * @return The index of the element, or -1 if it doesn't exist in the array.
     */
    private int findInArray(final Object element) {
        @SuppressWarnings("unchecked")
        final T obj = (T) element;
        int i = 0;
        while (i < size) {
            if (obj == null || store[i] == null) {
                if (obj == store[i]) {
                    return i;
                }
            } else if (store[i] == obj || store[i].equals(obj)) {
                return i;
            }
            
            i++;
        }
        
        return -1;
    }
    
    private void minHeapify() {
        for (int i = (size >>> 1) - 1; i >= 0; i--) {
            siftDown(i);
        }
    }
    
    private void siftDown(final int index) {
        final int frontier = size >>> 1;
        int current = index;
        while (current < frontier) {
            int child = (current << 1) + 1;
            int right = child + 1;
            if (right < size && greaterThan(child, right)) {
                child = right;
            }
            if (lessThanOrEqual(index, child)) {
                break;
            }
            swap(current, child);
            current = child;
        }
    }
    
    private void swap(final int left, final int right) {
        final T temp = store[left];
        store[left] = store[right];
        store[right] = temp;
    }
    

    @SuppressWarnings("unchecked")
    private boolean greaterThanOrEqual(final int i, final int j) {
        return ((Comparable<T>) store[i]).compareTo(store[j]) >= 0;
    }
    
    @SuppressWarnings("unchecked")
    private boolean greaterThan(final int i, final int j) {
        return ((Comparable<T>) store[i]).compareTo(store[j]) > 0;
    }
    
    @SuppressWarnings("unchecked")
    private boolean lessThanOrEqual(final int i, final int j) {
        return ((Comparable<T>) store[i]).compareTo(store[j]) <= 0;
    }
    
    public static class PriorityQueueIterator<T> implements Iterator<T> {
        private final T[] store;
        private final int size;
        private int index;
        
        /**
         * Ctor.
         * @param sstore The memory store of the priority queue.
         * @param ssize The size of the queue.
         */
        public PriorityQueueIterator(final T[] sstore, final int ssize) {
            store = sstore;
            size = ssize;
            index = 0;
        }
        
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public T next() {
            if (index >= size) {
                throw new IndexOutOfBoundsException();
            } else {
                final T value = store[index];
                index++;
                return value;
            }
        }
        
    }
}
