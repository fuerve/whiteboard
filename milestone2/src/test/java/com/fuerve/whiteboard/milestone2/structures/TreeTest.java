package com.fuerve.whiteboard.milestone2.structures;

import static org.junit.Assert.*;

import org.junit.Test;

import com.fuerve.whiteboard.milestone2.structures.Tree.InOrderIterator;
import com.fuerve.whiteboard.milestone2.structures.Tree.TraversalMode;

/**
 * Tests for the Tree class.
 */
public class TreeTest {

    /**
     * Test method for {@link com.fuerve.whiteboard.milestone2.structures.Tree#getValue()}.
     */
    @Test
    public void testGetSetValue() {
        final Tree<Integer> target = new Tree<Integer>();
        target.setValue(1);
        assertEquals(new Integer(1), target.getValue());
    }

    /**
     * Test method for {@link com.fuerve.whiteboard.milestone2.structures.Tree#getParent()}.
     */
    @Test
    public void testGetParent() {
        final Tree<Integer> target = new Tree<Integer>();
        final Tree<Integer> targetParent = new Tree<Integer>();
        targetParent.addChild(target);
        assertEquals(targetParent, target.getParent());
    }

    /**
     * Test method for {@link com.fuerve.whiteboard.milestone2.structures.Tree#isLeaf()}.
     */
    @Test
    public void testIsLeaf() {
        final Tree<Integer> target = new Tree<Integer>();
        final Tree<Integer> targetParent = new Tree<Integer>();
        targetParent.addChild(target);
        assertTrue(target.isLeaf());
        assertFalse(targetParent.isLeaf());
    }

    /**
     * Test method for {@link com.fuerve.whiteboard.milestone2.structures.Tree#getChildren()}.
     */
    @Test
    public void testGetChildren() {
        final Tree<Integer> target = new Tree<Integer>();
        target.addChild(new Tree<Integer>());
        target.addChild(new Tree<Integer>());
        final Iterable<Tree<Integer>> children = target.getChildren();
        int count = 0;
        for (@SuppressWarnings("unused") final Tree<Integer> child : children) {
            count++;
        }
        assertEquals(2, count);
    }

    /**
     * Test method for {@link com.fuerve.whiteboard.milestone2.structures.Tree#getChildAt(int)}.
     */
    @Test
    public void testGetChildAt() {
        final Tree<Integer> target = new Tree<Integer>();
        final Tree<Integer> child = new Tree<Integer>();
        child.setValue(1);
        
        target.addChild(new Tree<Integer>());
        target.addChild(new Tree<Integer>());
        target.addChild(child);
        target.addChild(new Tree<Integer>());
        
        assertEquals(child, target.getChildAt(2));
    }

    /**
     * Test method for {@link com.fuerve.whiteboard.milestone2.structures.Tree#addChild(com.fuerve.whiteboard.milestone2.structures.Tree)}.
     */
    @Test(expected=IllegalArgumentException.class)
    public void testAddChildSelf() {
        final Tree<Integer> target = new Tree<Integer>();
        target.addChild(target);
    }
    
    /***/
    @Test(expected=IllegalArgumentException.class)
    public void testAddChildNull() {
        final Tree<Integer> target = new Tree<Integer>();
        target.addChild(null);
    }

    /**
     * Test method for {@link com.fuerve.whiteboard.milestone2.structures.Tree#removeChild(com.fuerve.whiteboard.milestone2.structures.Tree)}.
     */
    @Test
    public void testRemoveChild() {
        final Tree<Integer> target = new Tree<Integer>();
        final Tree<Integer> child = new Tree<Integer>();
        child.setValue(1);
        
        target.addChild(new Tree<Integer>());
        target.addChild(new Tree<Integer>());
        target.addChild(child);
        target.addChild(new Tree<Integer>());
        
        target.removeChild(child);
        
        assertNotEquals(child, target.getChildAt(2));
    }

    /**
     * Test method for {@link com.fuerve.whiteboard.milestone2.structures.Tree#removeChildAt(int)}.
     */
    @Test
    public void testRemoveChildAt() {
        final Tree<Integer> target = new Tree<Integer>();
        final Tree<Integer> child = new Tree<Integer>();
        child.setValue(1);
        
        target.addChild(new Tree<Integer>());
        target.addChild(new Tree<Integer>());
        target.addChild(child);
        target.addChild(new Tree<Integer>());
        
        target.removeChildAt(2);
        
        assertNotEquals(child, target.getChildAt(2));
    }

    /**
     * Test method for {@link com.fuerve.whiteboard.milestone2.structures.Tree#setDefaultTraversalMode(com.fuerve.whiteboard.milestone2.structures.Tree.TraversalMode)}.
     */
    @Test
    public void testSetDefaultTraversalMode() {
        final Tree<Integer> target = new Tree<Integer>();
        target.setDefaultTraversalMode(TraversalMode.INORDER);
        final Iterator<Integer> iter = target.iterator();
        assertTrue(iter instanceof InOrderIterator<?>);
    }

    /**
     * Test method for {@link com.fuerve.whiteboard.milestone2.structures.Tree#iterator()}.
     */
    @Test
    public void testIterator() {
        final Tree<Integer> target = new Tree<Integer>();
        final Tree<Integer> child1 = new Tree<Integer>();
        final Tree<Integer> child2 = new Tree<Integer>();
        final Tree<Integer> child1_1 = new Tree<Integer>();
        
        child1_1.setValue(1);
        child1.setValue(2);
        child2.setValue(3);
        target.setValue(4);
        
        target.addChild(child1);
        target.addChild(child2);
        child1.addChild(child1_1);
        
        final Iterator<Integer> iter = target.iterator();
        for (int i = 1; i <= 4; i++) {
            assertEquals(new Integer(i), iter.next());
        }
    }

    /**
     * Test method for {@link com.fuerve.whiteboard.milestone2.structures.Tree#preorderIterator()}.
     */
    @Test
    public void testPreorderIterator() {
        final Tree<Integer> target = new Tree<Integer>();
        final Tree<Integer> child1 = new Tree<Integer>();
        final Tree<Integer> child2 = new Tree<Integer>();
        final Tree<Integer> child1_1 = new Tree<Integer>();
        
        child1_1.setValue(1);
        child1.setValue(2);
        child2.setValue(3);
        target.setValue(4);
        
        target.addChild(child1);
        target.addChild(child2);
        child1.addChild(child1_1);
        
        final Iterator<Integer> iter = target.preorderIterator();
        for (int i = 1; i <= 4; i++) {
            assertEquals(new Integer(i), iter.next());
        }
    }
    
    /***/
    @Test
    public void testPreorderIteratorSingleValue() {
        final Tree<Integer> target = new Tree<Integer>();
        
        target.setValue(1);
        
        final Iterator<Integer> iter = target.preorderIterator();
        assertEquals(new Integer(1), iter.next());
        assertFalse(iter.hasNext());
    }

    /**
     * Test method for {@link com.fuerve.whiteboard.milestone2.structures.Tree#inOrderIterator()}.
     */
    @Test
    public void testInOrderIterator() {
        final Tree<Integer> target = new Tree<Integer>();
        final Tree<Integer> child1 = new Tree<Integer>();
        final Tree<Integer> child2 = new Tree<Integer>();
        final Tree<Integer> child1_1 = new Tree<Integer>();
        
        
        child1_1.setValue(1);
        child1.setValue(2);
        target.setValue(3);
        child2.setValue(4);
        
        target.addChild(child1);
        target.addChild(child2);
        child1.addChild(child1_1);
        
        final Iterator<Integer> iter = target.inOrderIterator();
        for (int i = 1; i <= 4; i++) {
            assertEquals(new Integer(i), iter.next());
        }
    }
    
    /***/
    @Test
    public void testInOrderIteratorSingleValue() {
        final Tree<Integer> target = new Tree<Integer>();
        
        target.setValue(1);
        
        final Iterator<Integer> iter = target.inOrderIterator();
        assertEquals(new Integer(1), iter.next());
        assertFalse(iter.hasNext());
    }

    /**
     * Test method for {@link com.fuerve.whiteboard.milestone2.structures.Tree#postorderIterator()}.
     */
    @Test
    public void testPostorderIterator() {
        final Tree<Integer> target = new Tree<Integer>();
        final Tree<Integer> child1 = new Tree<Integer>();
        final Tree<Integer> child2 = new Tree<Integer>();
        final Tree<Integer> child1_1 = new Tree<Integer>();
        
        target.setValue(1);
        child1.setValue(2);
        child1_1.setValue(3);
        child2.setValue(4);
        
        target.addChild(child1);
        target.addChild(child2);
        child1.addChild(child1_1);
        
        final Iterator<Integer> iter = target.postorderIterator();
        for (int i = 1; i <= 4; i++) {
            assertEquals(new Integer(i), iter.next());
        }
    }

    /***/
    @Test
    public void testPostorderIteratorSingleValue() {
        final Tree<Integer> target = new Tree<Integer>();
        
        target.setValue(1);
        
        final Iterator<Integer> iter = target.postorderIterator();
        assertEquals(new Integer(1), iter.next());
        assertFalse(iter.hasNext());
    }
}
