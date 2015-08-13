package com.fuerve.whiteboard.milestone2.structures;

import java.util.NoSuchElementException;

/**
 * List implementation.
 */
public class LinkedList<T> implements List<T>, Deque<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size = 0;
    
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
        @SuppressWarnings("unchecked")
        final Iterator<T> iter = (Iterator<T>) other.iterator();
        while (iter.hasNext()) {
            add(iter.next());
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
        return head == null;
    }

    /* (non-Javadoc)
     * @see com.fuerve.whiteboard.milestone2.Collection#contains(java.lang.Object)
     */
    @Override
    public boolean contains(final Object element) {
        return findIndexOf(element, false) >= 0;
    }

    /* (non-Javadoc)
     * @see com.fuerve.whiteboard.milestone2.Collection#add(java.lang.Object)
     */
    @Override
    public boolean add(final T element) throws UnsupportedOperationException,
            ClassCastException, NullPointerException, IllegalArgumentException {
        final Node<T> newNode = new Node<T>(element);
        boolean added = false;
        if (head == null) {
            head = newNode;
            tail = newNode;
            head.next = newNode;
            head.prev = newNode;
            added = true;
        } else if (tail == head) {
            tail = newNode;
            head.next = newNode;
            tail.prev = head;
            head.prev = tail;
            tail.next = head;
            added = true;
        } else {
            tail.next = newNode;
            head.prev = newNode;
            newNode.prev = tail;
            newNode.next = head;
            tail = newNode;
            added = true;
        }
        
        if (added) {
            size++;
        }
        
        return added;
    }

    /* (non-Javadoc)
     * @see com.fuerve.whiteboard.milestone2.Collection#remove(java.lang.Object)
     */
    @Override
    public boolean remove(final T element) throws UnsupportedOperationException,
            ClassCastException, NullPointerException {
        if (head == null) {
            return false;
        }
        
        if (head.value == null || element == null) {
            if (head.value == element) {
                if (tail == head) {
                    head = null;
                    tail = null;
                    size = 0;
                    return true;
                } else {
                    head = head.next;
                    tail.prev = head;
                    size--;
                    return true;
                }
            }
        }  else if (head.value != null && element != null) {
            if (head.value == element || head.value.equals(element)) {
                if (tail == head) {
                    head = null;
                    tail = null;
                    size = 0;
                    return true;
                } else {
                    head = head.next;
                    tail.prev = head;
                    size--;
                    return true;
                }
            }
        }
        
        Node<T> current = head.next;
        while (current != head) {
            if (current.value == null || element == null) {
                if (current.value == element) {
                    removeElement(current);
                    return true;
                }
            } else if (current.value != null && element != null) {
                if (current.value == element || current.value.equals(element)) {
                    removeElement(current);
                    return true;
                }
            }
            
            current = current.next;
        }
        
        return false;
    }

    /* (non-Javadoc)
     * @see com.fuerve.whiteboard.milestone2.Collection#iterator()
     */
    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator<T>(head);
    }

    /* (non-Javadoc)
     * @see com.fuerve.whiteboard.milestone2.Deque#remove()
     */
    @Override
    public T remove() throws NoSuchElementException {
        return remove(size - 1);
    }

    /* (non-Javadoc)
     * @see com.fuerve.whiteboard.milestone2.Deque#peek()
     */
    @Override
    public T peek() {
        if (head == null) {
            return null;
        } else {
            return head.value;
        }
    }

    /* (non-Javadoc)
     * @see com.fuerve.whiteboard.milestone2.Deque#push(java.lang.Object)
     */
    @Override
    public void push(final T element) throws ClassCastException,
            NullPointerException, IllegalArgumentException {
        add(0, element);
    }

    /* (non-Javadoc)
     * @see com.fuerve.whiteboard.milestone2.Deque#pop()
     */
    @Override
    public T pop() throws NoSuchElementException {
        return remove(0);
    }

    /* (non-Javadoc)
     * @see com.fuerve.whiteboard.milestone2.List#add(int, java.lang.Object)
     */
    @Override
    public void add(final int index, final T element) throws UnsupportedOperationException,
            ClassCastException, NullPointerException, IllegalArgumentException,
            IndexOutOfBoundsException {
        if ((head == null && index > 0) || index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        
        if (index == size) {
            add(element);
            return;
        }
        
        final Node<T> newNode = new Node<T>(element);
        if (index == 0) {
            newNode.prev = tail;
            newNode.next = head;
            head = newNode;
        } else {
            int count = 1;
            Node<T> current = head.next;
            while (count < index) {
                current = current.next;
                count++;
            }
            // This is always a headscratcher for me.
            // Given the current node, we want to insert the new node after it?  Or before it?  Well, at the ordinal given.
            // current is at that ordinal.
            // newNode needs to shove current out of the way.
            // So current.prev.next needs to be newNode.
            // newNode.next needs to be current.
            // newNode.prev needs to be current.prev.
            // current.prev needs to be newNode.
            // So what am I missing?
            current.prev.next = newNode;
            newNode.prev = current.prev;
            newNode.next = current;
            current.prev = newNode;
        }
        
        size++;
    }

    /* (non-Javadoc)
     * @see com.fuerve.whiteboard.milestone2.List#get(int)
     */
    @Override
    public T get(final int index) throws IndexOutOfBoundsException {
        if (head == null || index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        
        if (index == 0) {
            return head.value;
        }
        
        Node<T> current = head;
        int count = 0;
        while (count < index) {
            current = current.next;
            count++;
        }
        
        return current.value;
    }

    /* (non-Javadoc)
     * @see com.fuerve.whiteboard.milestone2.List#set(int, java.lang.Object)
     */
    @Override
    public T set(final int index, final T element) throws ClassCastException,
            NullPointerException, IllegalArgumentException,
            IndexOutOfBoundsException {
        if (head == null || index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        
        if (index == 0) {
            final T oldValue = head.value;
            head.value = element;
            return oldValue;
        }
        
        Node<T> current = head;
        int count = 0;
        while (count < index) {
            current = current.next;
            count++;
        }
        
        final T oldValue = current.value;
        current.value = element;
        return oldValue;
    }

    /* (non-Javadoc)
     * @see com.fuerve.whiteboard.milestone2.List#remove(int)
     */
    @Override
    public T remove(final int index) throws UnsupportedOperationException,
            IndexOutOfBoundsException {
        if (head == null || index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        
        if (index == size) {
            final T oldValue = tail.value;
            tail = tail.prev;
            size--;
            if (size == 0) {
                head = null;
                tail = null;
            }
            return oldValue;
        } else if (index == 0) {
            final T oldValue = head.value;
            head = head.next;
            size--;
            if (size == 0) {
                head = null;
                tail = null;
            }
            return oldValue;
        }
        
        Node<T> current = head;
        int count = 0;
        while (count < index) {
            current = current.next;
            count++;
        }
        
        final T oldValue = current.value;
        current.prev.next = current.next;
        current.next.prev = current.prev;
        size--;
        return oldValue;
    }

    /* (non-Javadoc)
     * @see com.fuerve.whiteboard.milestone2.List#indexOf(java.lang.Object)
     */
    @Override
    public int indexOf(final Object element) throws ClassCastException,
            NullPointerException {
        return findIndexOf(element, false);
    }

    /* (non-Javadoc)
     * @see com.fuerve.whiteboard.milestone2.List#lastIndexOf(java.lang.Object)
     */
    @Override
    public int lastIndexOf(final Object element) throws ClassCastException,
            NullPointerException {
        return findIndexOf(element, true);
    }
    
    private void removeElement(final Node<T> current) {
        if (tail == current) {
            tail = current.prev;
        }
        current.next.prev = current.prev;
        current.prev.next = current.next;
        size--;
    }
    
    private int findIndexOf(final Object element, final boolean reverse) {
        Node<T> initial;
        if (reverse) {
            initial = tail;
        } else {
            initial = head;
        }
        
        if (initial == null) {
            return -1;
        }
        
        if (initial.value == null || element == null) {
            if (initial.value == element) {
                return reverse ? size - 1 : 0;
            }
        } else if (initial.value != null && element != null) {
            if (initial.value == element || initial.value.equals(element)) {
                return reverse ? size - 1 : 0;
            }
        }
        
        Node<T> current;
        int index = -1;
        if (reverse) {
            current = initial.prev;
            index = size - 2;
        } else {
            current = initial.next;
            index = 1;
        }
        
        while (current != initial) {
            if (current.value == null || element == null) {
                if (current.value == element) {
                    return index;
                }
            } else if (current.value != null && element != null) {
                if (current.value == element || current.value.equals(element)) {
                    return index;
                }
            }
            
            if (reverse) {
                current = current.prev;
                index--;
            } else {
                current = current.next;
                index++;
            }
        }
        
        return -1;
    }
    
    /**
     * Container for a single element of the list.
     * @param <T> The contained type.
     */
    public static class Node<T> {
        public T value;
        public Node<T> next;
        public Node<T> prev;
        
        /**
         * Initializes a new instance of Node with a value and null references.
         * @param vvalue The value of the node.
         */
        public Node(final T vvalue) {
            this(vvalue, null, null);
        }
        
        /**
         * Initializes a new instance of Node with a value and positional references.
         * @param vvalue The value of the node.
         * @param nnext The node in the next position in the list.
         * @param pprev The node in the previous position in the list.
         */
        public Node(final T vvalue, final Node<T> nnext, final Node<T> pprev) {
            value = vvalue;
            next = nnext;
            prev = pprev;
        }
    }
    
    /**
     * Iterator specific to the linked list implementation.
     * @param <T> Contained type.
     */
    public static class LinkedListIterator<T> implements Iterator<T> {
        private Node<T> head;
        private Node<T> current;
        private boolean started = false;
        
        /**
         * Initializes a new instance of LinkedListIterator.
         * @param hhead The head of the list.
         */
        public LinkedListIterator(final Node<T> hhead) {
            head = hhead;
            current = head;
        }
        
        @Override
        public boolean hasNext() {
            return !(current == null || (current == head && started));
        }

        @Override
        public T next() {
            if (current == null || (current == head && started)) {
                return null;
            } else {
                final T value = current.value;
                current = current.next;
                started = true;
                return value;
            }
        }
        
    }

}
