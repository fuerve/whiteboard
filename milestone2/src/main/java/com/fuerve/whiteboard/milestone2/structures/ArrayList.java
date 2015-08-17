package com.fuerve.whiteboard.milestone2.structures;

/**
 * ArrayList implementation.
 */
public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 16;
    private static final int GROWTH_FACTOR = 2;
    
    private int size;
    private T[] store;
    
    /**
     * Initializes a new instance of ArrayList with a default initial capacity.
     */
    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }
    
    /**
     * Initializes a new instance of ArrayList with a specific initial capacity.
     * @param The initial capacity of the ArrayList.
     */
    @SuppressWarnings("unchecked")
    public ArrayList(final int ccapacity) {
        store = (T[]) new Object[ccapacity];
        size = 0;
    }
    
    /**
     * Initializes a new instance of ArrayList and populates it with a deep
     * copy of any other Collection.
     * @param other The other Collection from which to seed this ArrayList.
     */
    @SuppressWarnings("unchecked")
    public ArrayList(final Collection<? extends T> other) {
        size = other.size();
        store = (T[]) new Object[size];
        int index = 0;
        for (Iterator<? extends T> iter = other.iterator(); iter.hasNext(); ) {
            store[index] = iter.next();
            index++;
        }
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
        return (findInArray(element, false) != -1);
    }

    /* (non-Javadoc)
     * @see com.fuerve.whiteboard.milestone2.Collection#add(java.lang.Object)
     */
    @Override
    public boolean add(final T element) throws UnsupportedOperationException,
            ClassCastException, NullPointerException, IllegalArgumentException {
        if (size == store.length) {
            growStore();
        }
        
        store[size] = element;
        size++;
        
        return true;
    }

    /* (non-Javadoc)
     * @see com.fuerve.whiteboard.milestone2.Collection#remove(java.lang.Object)
     */
    @Override
    public boolean remove(final T element) throws UnsupportedOperationException,
            ClassCastException, NullPointerException {
        if (size <= 0) {
            throw new IndexOutOfBoundsException();
        } else {
            boolean shift = false;
            for (int i = 0; i < size; i++) {
                if (!shift) {
                    if (element == null || store[i] == null) {
                        if (element == store[i]) {
                            size--;
                            shift = true;
                        }
                    } else if (element == store[i] || element.equals(store[i])) {
                        size--;
                        shift = true;
                    }
                    if (shift) {
                        store[i] = store[i + 1];
                    }
                } else {
                    store[i] = store[i + 1];
                }
            }
            if (shift) {
                store[size + 1] = null;
            }
            return shift;
        }
    }

    /* (non-Javadoc)
     * @see com.fuerve.whiteboard.milestone2.Collection#iterator()
     */
    @Override
    public Iterator<T> iterator() {
        return new ArrayListIterator<T>(store, size);
    }

    /* (non-Javadoc)
     * @see com.fuerve.whiteboard.milestone2.List#add(int, java.lang.Object)
     */
    @Override
    public void add(final int index, final T element) throws UnsupportedOperationException,
            ClassCastException, NullPointerException, IllegalArgumentException,
            IndexOutOfBoundsException {
        if (index > size) {
            throw new IndexOutOfBoundsException();
        } else {
            if (size + 1 > store.length) {
                growStore();
            }
            if (index == size) {
                store[index] = element;
            } else {
                for (int i = index + 1; i < size; i++) {
                    store[i] = store[i - 1];
                }
                store[index] = element;
            }
            size++;
        }

    }

    /* (non-Javadoc)
     * @see com.fuerve.whiteboard.milestone2.List#get(int)
     */
    @Override
    public T get(final int index) throws IndexOutOfBoundsException {
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        } else {
            return store[index];
        }
    }

    /* (non-Javadoc)
     * @see com.fuerve.whiteboard.milestone2.List#set(int, java.lang.Object)
     */
    @Override
    public T set(final int index, final T element) throws ClassCastException,
            NullPointerException, IllegalArgumentException,
            IndexOutOfBoundsException {
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        } else {
            final T previous = store[index];
            store[index] = element;
            return previous;
        }
    }

    /* (non-Javadoc)
     * @see com.fuerve.whiteboard.milestone2.List#remove(int)
     */
    @Override
    public T remove(final int index) throws UnsupportedOperationException,
            IndexOutOfBoundsException {
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        } else {
            final T element = store[index];
            if (index == size - 1) {
                store[index] = null;
                size--;
            } else {
                size--;
                for (int i = index; i < size; i++) {
                    store[i] = store[i + 1];
                }
            }
            return element;
        }
    }

    /* (non-Javadoc)
     * @see com.fuerve.whiteboard.milestone2.List#indexOf(java.lang.Object)
     */
    @Override
    public int indexOf(final Object element) throws ClassCastException,
            NullPointerException {
        return findInArray(element, false);
    }

    /* (non-Javadoc)
     * @see com.fuerve.whiteboard.milestone2.List#lastIndexOf(java.lang.Object)
     */
    @Override
    public int lastIndexOf(final Object element) throws ClassCastException,
            NullPointerException {
        return findInArray(element, true);
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
     * @param backwards Whether to traverse backwards.
     * @return The index of the element, or -1 if it doesn't exist in the array.
     */
    private int findInArray(final Object element, final boolean backwards) {
        @SuppressWarnings("unchecked")
        final T obj = (T) element;
        int i = backwards ? size - 1 : 0;
        while ((backwards && i >= 0) || (!backwards && i < size)) {
            if (obj == null || store[i] == null) {
                if (obj == store[i]) {
                    return i;
                }
            } else if (store[i] == obj || store[i].equals(obj)) {
                return i;
            }
            
            if (backwards) {
                i--;
            } else {
                i++;
            }
        }
        
        return -1;
    }

    /**
     * Iterator class specific to the ArrayList.
     * @param <T> The type stored in the structure.
     */
    public static class ArrayListIterator<T> implements Iterator<T> {
        private final T[] store;
        private final int size;
        private int index;
        
        /**
         * Initializes a new instance of ArrayListIterator<T>.
         * @param sstore The memory store backing the structure.
         * @param ssize The size of the structure.
         */
        public ArrayListIterator(final T[] sstore, final int ssize) {
            store = sstore;
            size = ssize;
            index = 0;
        }
        
        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public T next() {
            if (store == null || index >= size) {
                throw new ArrayIndexOutOfBoundsException(index);
            } else {
                final T element = store[index];
                index++;
                return element;
            }
        }

        @Override
        public void remove() {
            // TODO Auto-generated method stub
            
        }
    }
}
