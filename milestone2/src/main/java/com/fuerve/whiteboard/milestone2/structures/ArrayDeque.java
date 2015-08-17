package com.fuerve.whiteboard.milestone2.structures;

import java.util.NoSuchElementException;

/**
 * Double-ended queue implementation.
 */
public class ArrayDeque<T> implements Deque<T> {
    private static final int DEFAULT_CAPACITY = 16;
    private static final int GROWTH_FACTOR = 2;
    
    private int size;
    private int head;
    private int tail;
    private T[] store;
    
    /**
     * Initializes a new instance of ArrayDeque.
     */
    public ArrayDeque() {
        this(DEFAULT_CAPACITY);
    }
    
    /**
     * Initializes a new instance of ArrayDeque and populates it with
     * the contents of another Collection.
     * @param other The Collection to copy.
     */
    @SuppressWarnings("unchecked")
    public ArrayDeque(final Collection<? extends T> other) {
        int initialCapacity = DEFAULT_CAPACITY;
        if (other.size() > DEFAULT_CAPACITY) {
            int power = 1;
            while (power < other.size()) {
                power *= 2;
            }
            initialCapacity = power;
        }
        store = (T[]) new Object[initialCapacity];
        final int center = store.length / 2;
        final int delta = other.size() / 2;
        head = center - delta;
        tail = head + 1;
        final Iterator<? extends T> iter = other.iterator();
        while (iter.hasNext()) {
            store[tail++] = iter.next();
            size++;
        }
    }
    
    /**
     * Initializes a new instance of ArrayDeque with an initial capacity.
     * @param ccapacity The initial capacity.
     */
    @SuppressWarnings("unchecked")
    public ArrayDeque(final int ccapacity) {
        store = (T[]) new Object[ccapacity];
        size = 0;
        head = store.length / 2;
        tail = (store.length / 2) + 1;
    }
    
    /* (non-Javadoc)
     * @see com.fuerve.whiteboard.milestone2.Collection#size()
     */
    @Override
    public int size() {
        return size;
    }

    /* (non-Javadoc)
     * @see com.fuerve.whiteboard.milestone2.Collection#isEmpty()
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /* (non-Javadoc)
     * @see com.fuerve.whiteboard.milestone2.Collection#contains(java.lang.Object)
     */
    @Override
    public boolean contains(final Object element) {
        if (size == 0) {
            return false;
        }
        for (int i = head; i <= tail; i++) {  // TODO: I think I have an off-by-one issue here.
            if (store[i] == null || element == null) {
                if (store[i] == element) {
                    return true;
                }
            } else if (store[i] != null && element != null) {
                if (store[i] == element || store[i].equals(element)) {
                    return true;
                }
            }
        }
        
        return false;
    }

    /* (non-Javadoc)
     * @see com.fuerve.whiteboard.milestone2.Collection#add(java.lang.Object)
     */
    @Override
    public boolean add(final T element) throws UnsupportedOperationException,
            ClassCastException, NullPointerException, IllegalArgumentException {
        if (tail + 1 >= store.length) {
            growStore();
        }
        
        store[tail] = element;
        tail++;
        size++;
        return true;
    }

    /* (non-Javadoc)
     * @see com.fuerve.whiteboard.milestone2.Collection#remove(java.lang.Object)
     */
    @Override
    public boolean remove(T element) throws UnsupportedOperationException,
            ClassCastException, NullPointerException {
        if (size == 0) {
            return false;
        }
        
        for (int i = head + 1; i < tail; i++) {
            if (element == null || store[i] == null) {
                if (element == store[i]) {
                    // Remove the element at i and shift the whole array down.
                    removeAt(i);
                    return true;
                }
            } else if (element != null && store[i] != null) {
                if (element == store[i] || element.equals(store[i])) {
                    removeAt(i);
                    return true;
                }
            }
        }
        
        
        return false;
    }

    /* (non-Javadoc)
     * @see com.fuerve.whiteboard.milestone2.Collection#iterator()
     */
    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator<T>(store, size, head, tail);
    }

    /* (non-Javadoc)
     * @see com.fuerve.whiteboard.milestone2.Deque#remove()
     */
    @Override
    public T remove() throws NoSuchElementException {
        if (size == 0) {
            throw new NoSuchElementException();
        }

        head++;
        final T value = store[head];
        store[head] = null;
        size--;
        return value;
    }

    /* (non-Javadoc)
     * @see com.fuerve.whiteboard.milestone2.Deque#peek()
     */
    @Override
    public T peek() {
        if (size == 0) {
            return null;
        } else {
            return store[head + 1];
        }
    }

    /* (non-Javadoc)
     * @see com.fuerve.whiteboard.milestone2.Deque#push(java.lang.Object)
     */
    @Override
    public void push(T element) throws ClassCastException,
            NullPointerException, IllegalArgumentException {
        if (head < 0) {
            growStore();
        }
        
        store[head] = element;
        head--;
        size++;
    }

    /* (non-Javadoc)
     * @see com.fuerve.whiteboard.milestone2.Deque#pop()
     */
    @Override
    public T pop() throws NoSuchElementException {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        
        head++;
        final T value = store[head];
        store[head] = null;
        size--;
        return value;
    }
    
    /**
     * Grows the dynamic backing store.
     */
    private void growStore() {
        @SuppressWarnings("unchecked")
        final T[] newStore = (T[]) new Object[store.length * GROWTH_FACTOR];
        final int oldCenter = store.length / 2;
        final int newCenter = newStore.length / 2;
        final int start = newCenter - (oldCenter - head);
        final int end = newCenter + (tail - oldCenter);
        for (int i = start + 1, j = head + 1; i < end; i++, j++) {
            newStore[i] = store[j];
        }
        store = newStore;
        head = start;
        tail = end;
    }
    
    /**
     * Removes an element at a given index.
     * @param index The index at which to remove the element.
     */
    private void removeAt(final int index) {
        if (index == head + 1) {
            head++;
            store[head] = null;
        } else if (index == tail - 1) {
            tail--;
            store[tail] = null;
        } else {
            for (int i = index; i < tail - 1; i++) {
                store[i] = store[i + 1];
            }
            tail--;
            store[tail] = null;
        }
        size--;
    }
    
    /**
     * ArrayDeque-specific forward iterator implementation.
     * @param <T> The type contained in the deque.
     */
    public static class ArrayDequeIterator<T> implements Iterator<T> {
        private T[] store;
        private int size;
        private int head;
        private int tail;
        private int current;
        
        /**
         * Initializes a new ArrayDequeIterator.
         * @param sstore The memory store.
         * @param ssize The size of the deque.
         * @param hhead The index to the head of the deque.
         * @param ttail The index to the tail of the deque.
         */
        public ArrayDequeIterator(final T[] sstore, final int ssize, final int hhead, final int ttail) {
            store = sstore;
            size = ssize;
            head = hhead;
            tail = ttail;
            current = head + 1;
        }
        
        @Override
        public boolean hasNext() {
            if (size == 0) {
                return false;
            }
            
            return current < tail;
        }

        @Override
        public T next() {
            if (current > tail) {
                return null;
            } else {
                return store[current++];
            }
        }
        
    }

}
