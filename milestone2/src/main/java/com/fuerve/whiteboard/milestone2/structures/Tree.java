package com.fuerve.whiteboard.milestone2.structures;

/**
 * Basic k-ary tree implementation.  Note that this implementation assumes
 * an in-order traversal as a traversal of the leftmost child, followed by the node itself,
 * followed by the remainder of its children, so you can use it as a binary tree if you
 * keep an eye on how you populate it.
 */
public class Tree<T> implements Iterable<T> {
    private TraversalMode defaultTraversalMode = TraversalMode.PREORDER;
    
    private T value;
    private Tree<T> parent;
    private List<Tree<T>> children;
    
    /**
     * Gets the value at this level of the tree.
     * @return The value at this level of the tree.
     */
    public T getValue() {
        return value;
    }
    
    /**
     * Sets the value at this level of the tree.
     * @param vvalue The value at this level of the tree.
     */
    public void setValue(final T vvalue) {
        value = vvalue;
    }
    
    /**
     * Gets this level's parent node, if any.
     * @return This level's parent node, or null if no parent exists.
     */
    public Tree<T> getParent() {
        return parent;
    }
    
    /**
     * Gets whether this is a leaf node.
     * @return True if this node is a leaf.
     */
    public boolean isLeaf() {
        return children == null || children.size() == 0;
    }
    
    /**
     * Gets this node's children.
     * @return An enumeration of this node's children.
     */
    public Iterable<Tree<T>> getChildren() {
        return children;
    }
    
    /**
     * Gets the child of this node at a given index.
     * @param index The index of the child.
     * @return The child at the given index.
     */
    public Tree<T> getChildAt(final int index) {
        return children.get(index);
    }
    
    /**
     * Adds a child node to this tree.
     * @param child The child node to add.
     */
    public void addChild(final Tree<T> child) {
        if (child == null || child == this) {
            throw new IllegalArgumentException("child");
        }
        if (children == null) {
            children = new ArrayList<Tree<T>>();
        }
        children.add(child);
        child.parent = this;
    }
    
    /**
     * Removes a child node from this tree.
     * @param child The child node to remove.
     */
    public boolean removeChild(final Tree<T> child) {
        if (children == null) {
            return false;
        } else {
            return children.remove(child);
        }
    }
    
    /**
     * Removes a child node from this tree.
     * @param index The index of the child node to remove.
     */
    public boolean removeChildAt(final int index) {
        if (children == null) {
            return false;
        } else {
            return children.remove(index) != null;
        }
    }
    
    /**
     * Sets the default traversal mode for the iterator() method.
     * @param mode The default traversal mode to set.
     */
    public void setDefaultTraversalMode(final TraversalMode mode) {
        defaultTraversalMode = mode;
    }
    
    /**
     * Returns a default iterator.
     */
    public Iterator<T> iterator() {
        switch (defaultTraversalMode) {
        case PREORDER:
            return preorderIterator();
        case INORDER:
            return inOrderIterator();
        case POSTORDER:
            return postorderIterator();
        default:
            return null;
        }
    }
    
    /**
     * Returns a preorder iterator.
     * @return The iterator.
     */
    public Iterator<T> preorderIterator() {
        return new PreorderIterator<T>(this);
    }
    
    /**
     * Returns an in-order iterator.
     * @return The iterator.
     */
    public Iterator<T> inOrderIterator() {
        return new InOrderIterator<T>(this);
    }
    
    /**
     * Returns a postorder iterator.
     * @return The iterator.
     */
    public Iterator<T> postorderIterator() {
        return new PostorderIterator<T>(this);
    }
    
    public static class PreorderIterator<T> implements Iterator<T> {
        private Deque<Tree<T>> deque;

        public PreorderIterator(final Tree<T> tree) {
            deque = new ArrayDeque<Tree<T>>();
            traverse(tree);
        }
        
        private void traverse(final Tree<T> node) {
            if (!node.isLeaf()) {
                for (final Tree<T> child : node.getChildren()) {
                    traverse(child);
                }
            }
            deque.add(node);
        }
        
        @Override
        public boolean hasNext() {
            return !deque.isEmpty();
        }

        @Override
        public T next() {
            if (deque.isEmpty()) {
                return null;
            }
            
            return deque.pop().getValue();
        }

        @Override
        public void remove() {
            return;
        }
        
    }
    
    public static class InOrderIterator<T> implements Iterator<T> {
        private Deque<Tree<T>> deque;

        public InOrderIterator(final Tree<T> tree) {
            deque = new ArrayDeque<Tree<T>>();
            traverse(tree);
        }
        
        private void traverse(final Tree<T> node) {
            if (!node.isLeaf()) {
                final List<Tree<T>> children = node.children;
                if (!children.isEmpty()) {
                    traverse(children.get(0));
                }
                deque.add(node);
                if (children.size() > 1) {
                    for (int i = 1; i < children.size(); i++) {
                        traverse(children.get(i));
                    }
                }
            } else {
                deque.add(node);
            }
        }
        
        @Override
        public boolean hasNext() {
            return !deque.isEmpty();
        }
        
        @Override
        public T next() {
            return deque.pop().getValue();
        }

        @Override
        public void remove() {
            return;
        }
        
    }
    
    public static class PostorderIterator<T> implements Iterator<T> {
        private Deque<Tree<T>> deque;

        public PostorderIterator(final Tree<T> tree) {
            deque = new ArrayDeque<Tree<T>>();
            traverse(tree);
        }
        
        private void traverse(final Tree<T> node) {
            deque.add(node);
            if (!node.isLeaf()) {
                for (final Tree<T> child : node.getChildren()) {
                    traverse(child);
                }
            }
        }
        
        @Override
        public boolean hasNext() {
            return !deque.isEmpty();
        }

        @Override
        public T next() {
            if (deque.isEmpty()) {
                return null;
            }
            
            return deque.remove().getValue();
        }

        @Override
        public void remove() {
            return;
        }
        
    }

    public static enum TraversalMode {
        PREORDER,
        INORDER,
        POSTORDER
    }
}
