package com.fuerve.whiteboard.milestone2.structures;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Test;

/**
 * Tests for the ArrayDeque class.
 */
public class ArrayDequeTest {
    /**
     * Copy constructor test.
     */
    @Test
    public void testArrayDequeCopyConstructor() {
        final Deque<Integer> target = new ArrayDeque<Integer>();
        for (int i = 0; i < 100; i++) {
            target.add(i);
        }
        
        final Deque<Integer> targetCopy = new ArrayDeque<Integer>(target);
        assertEquals(100, targetCopy.size());
        for (int i = 0; i < 100; i++) {
            assertEquals(new Integer(i), targetCopy.remove());
        }
        assertEquals(0, targetCopy.size());
    }

    /**
     * Test method for {@link com.fuerve.whiteboard.milestone2.structures.ArrayDeque#size()}.
     */
    @Test
    public void testSize() {
        final Deque<Integer> target = new ArrayDeque<Integer>();
        assertEquals(0, target.size());
        target.add(1);
        target.add(2);
        assertEquals(2, target.size());
    }

    /**
     * Test method for {@link com.fuerve.whiteboard.milestone2.structures.ArrayDeque#isEmpty()}.
     */
    @Test
    public void testIsEmpty() {
        final Deque<Integer> target = new ArrayDeque<Integer>();
        assertTrue(target.isEmpty());
        target.add(1);
        assertFalse(target.isEmpty());
        target.remove();
        assertTrue(target.isEmpty());
    }

    /**
     * Test method for {@link com.fuerve.whiteboard.milestone2.structures.ArrayDeque#contains(java.lang.Object)}.
     */
    @Test
    public void testContains() {
        final Deque<Integer> target = new ArrayDeque<Integer>();
        assertFalse(target.contains(1));
        target.add(1);
        assertTrue(target.contains(1));
        target.remove();
        assertFalse(target.contains(1));
    }
    
    /***/
    @Test
    public void testContainsNull() {
        final Deque<Integer> target = new ArrayDeque<Integer>();
        assertFalse(target.contains(null));
        target.add(null);
        assertTrue(target.contains(null));
        target.remove();
        assertFalse(target.contains(null));
        target.add(1);
        target.add(null);
        assertTrue(target.contains(null));
    }

    /**
     * Test method for {@link com.fuerve.whiteboard.milestone2.structures.ArrayDeque#add(java.lang.Object)}.
     */
    @Test
    public void testAdd() {
        final Deque<Integer> target = new ArrayDeque<Integer>();
        for (int i = 0; i < 100; i++) {
            assertTrue(target.add(i));
        }
        final Iterator<Integer> iter = target.iterator();
        for (int i = 0; i < 100; i++) {
            assertEquals(new Integer(i), iter.next());
        }
    }

    /**
     * Test method for {@link com.fuerve.whiteboard.milestone2.structures.ArrayDeque#remove(java.lang.Object)}.
     */
    @Test
    public void testRemoveT() {
        final Deque<Integer> target = new ArrayDeque<Integer>();
        assertFalse(target.remove(99));
        target.add(1);
        target.add(2);
        assertTrue(target.remove(2));
        assertEquals(1, target.size());
        assertFalse(target.contains(2));
        target.add(2);
        target.add(null);
        target.add(2);
        assertTrue(target.remove(2));
        assertTrue(target.contains(2));
    }
    
    /***/
    @Test
    public void testRemoveTNull() {
        final Deque<Integer> target = new ArrayDeque<Integer>();
        target.add(null);
        assertTrue(target.remove(null));
        assertTrue(target.isEmpty());
        target.add(1);
        target.add(2);
        target.add(null);
        target.add(3);
        assertTrue(target.remove(null));
        
        final Iterator<Integer> iter = target.iterator();
        int count = 1;
        while (iter.hasNext()) {
            assertEquals(new Integer(count), iter.next());
            count++;
        }
        assertEquals(4, count);
    }
    
    /***/
    @Test
    public void testRemoveTNullHead() {
        final Deque<Integer> target = new ArrayDeque<Integer>();
        target.add(null);
        target.add(1);
        assertTrue(target.remove(null));
        assertEquals(1, target.size());
        assertEquals(new Integer(1), target.remove());
    }
    
    /***/
    @Test
    public void testRemoveTNullTail() {
        final Deque<Integer> target = new ArrayDeque<Integer>();
        target.add(1);
        target.add(null);
        assertTrue(target.remove(null));
        assertEquals(1, target.size());
        assertEquals(new Integer(1), target.pop());
    }
    
    /***/
    @Test
    public void testRemoveTHead() {
        final Deque<Integer> target = new ArrayDeque<Integer>();
        target.add(1);
        target.add(2);
        assertTrue(target.remove(1));
        assertEquals(1, target.size());
        assertEquals(new Integer(2), target.remove());
    }
    
    /***/
    @Test
    public void testRemoveTTail() {
        final Deque<Integer> target = new ArrayDeque<Integer>();
        target.add(1);
        target.add(2);
        assertTrue(target.remove(2));
        assertEquals(1, target.size());
        assertEquals(new Integer(1), target.pop());
    }

    /**
     * Test method for {@link com.fuerve.whiteboard.milestone2.structures.ArrayDeque#iterator()}.
     */
    @Test
    public void testIterator() {
        final Deque<Integer> target = new ArrayDeque<Integer>();
        Iterator<Integer> iter = target.iterator();
        assertFalse(iter.hasNext());
        for (int i = 0; i < 100; i++) {
            target.add(i);
        }
        iter = target.iterator();
        for (int i = 0; i < 100; i++) {
            assertEquals(new Integer(i), iter.next());
        }
    }

    /**
     * Test method for {@link com.fuerve.whiteboard.milestone2.structures.ArrayDeque#remove()}.
     */
    @Test
    public void testRemove() {
        final Deque<Integer> target = new ArrayDeque<Integer>();
        target.add(1);
        target.add(2);
        assertEquals(new Integer(1), target.remove());
        assertEquals(new Integer(2), target.remove());
    }
    
    /***/
    @Test
    public void testRemoveNull() {
        final Deque<Integer> target = new ArrayDeque<Integer>();
        target.add(1);
        target.add(null);
        target.add(2);
        assertEquals(new Integer(1), target.remove());
        assertEquals(null, target.remove());
        assertEquals(new Integer(2), target.remove());
    }
    
    /***/
    @Test(expected=NoSuchElementException.class)
    public void testRemoveEmpty() {
        final Deque<Integer> target = new ArrayDeque<Integer>();
        target.remove();
    }

    /**
     * Test method for {@link com.fuerve.whiteboard.milestone2.structures.ArrayDeque#peek()}.
     */
    @Test
    public void testPeek() {
        final Deque<Integer> target = new ArrayDeque<Integer>();
        target.add(1);
        target.add(2);
        target.add(3);
        assertEquals(new Integer(1), target.peek());
        target.remove();
        assertEquals(new Integer(2), target.peek());
        target.remove();
        assertEquals(new Integer(3), target.peek());
        
        target.push(1);
        target.push(2);
        target.push(3);
        assertEquals(new Integer(3), target.peek());
        assertEquals(new Integer(3), target.pop());
        assertEquals(new Integer(2), target.peek());
        assertEquals(new Integer(2), target.pop());
        assertEquals(new Integer(1), target.peek());
        assertEquals(new Integer(1), target.pop());
    }
    
    /**
     * Test method for {@link com.fuerve.whiteboard.milestone2.structures.ArrayDeque#push(java.lang.Object)}.
     */
    @Test
    public void testPush() {
        final Deque<Integer> target = new ArrayDeque<Integer>();
        target.push(1);
        target.push(2);
        
        assertEquals(new Integer(2), target.remove());
        assertEquals(new Integer(1), target.remove());
        
        target.push(1);
        target.push(2);
        assertEquals(new Integer(2), target.pop());
        assertEquals(new Integer(1), target.pop());
    }

    /**
     * Test method for {@link com.fuerve.whiteboard.milestone2.structures.ArrayDeque#pop()}.
     */
    @Test
    public void testPop() {
        final Deque<Integer> target = new ArrayDeque<Integer>();
        target.add(2);
        target.add(1);
        
        assertEquals(new Integer(2), target.pop());
        assertEquals(new Integer(1), target.pop());
    }
    
    /***/
    @Test(expected=NoSuchElementException.class)
    public void testPopEmpty() {
        final Deque<Integer> target = new ArrayDeque<Integer>();
        target.pop();
    }

}
