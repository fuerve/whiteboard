package com.fuerve.whiteboard.milestone2.structures;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

/**
 * Tests for the PriorityQueue class.
 */
public class PriorityQueueTest {
    /**
     * Test method for {@link com.fuerve.whiteboard.milestone2.structures.PriorityQueue#PriorityQueue(com.fuerve.whiteboard.milestone2.structures.Collection)}.
     */
    @Test
    public void testPriorityQueueCollectionOfQextendsT() {
        final Collection<Integer> origin = new ArrayList<Integer>();
        for (int i = 99; i >= 0; i--) {
            origin.add(i);
        }
        
        final Queue<Integer> target = new PriorityQueue<Integer>(origin);
        assertEquals(100, target.size());
        for (int i = 0; i < 100; i++) {
            assertEquals(new Integer(i), target.remove());
        }
        assertEquals(0, target.size());
    }

    /**
     * Test method for {@link com.fuerve.whiteboard.milestone2.structures.PriorityQueue#size()}.
     */
    @Test
    public void testSize() {
        final Collection<Integer> target = new PriorityQueue<Integer>();
        assertEquals(0, target.size());
        target.add(0);
        assertEquals(1, target.size());
        for (int i = 1; i < 50000; i++) {
            target.add(i);
        }
        assertEquals(50000, target.size());
    }

    /**
     * Test method for {@link com.fuerve.whiteboard.milestone2.structures.PriorityQueue#isEmpty()}.
     */
    @Test
    public void testIsEmpty() {
        final Collection<Integer> target = new PriorityQueue<Integer>();
        assertTrue(target.isEmpty());
        target.add(0);
        assertFalse(target.isEmpty());
    }

    /**
     * Test method for {@link com.fuerve.whiteboard.milestone2.structures.PriorityQueue#contains(java.lang.Object)}.
     */
    @Test
    public void testContains() {
        final Collection<Integer> target = new PriorityQueue<Integer>();
        assertFalse(target.contains(0));
        target.add(0);
        assertTrue(target.contains(0));
    }

    /**
     * Test method for {@link com.fuerve.whiteboard.milestone2.structures.PriorityQueue#add(java.lang.Object)}.
     */
    @Test
    public void testAdd() {
        final Collection<Integer> target = new PriorityQueue<Integer>();
        assertFalse(target.contains(0));
        target.add(0);
        assertTrue(target.contains(0));
    }

    /**
     * Test method for {@link com.fuerve.whiteboard.milestone2.structures.PriorityQueue#remove(java.lang.Object)}.
     */
    @Test
    public void testRemoveT() {
        final Queue<Integer> target = new PriorityQueue<Integer>();
        assertFalse(target.contains(0));
        target.add(0);
        assertTrue(target.contains(0));
        target.remove(0);
        assertFalse(target.contains(0));
        
        target.add(1);
        target.add(0);
        target.add(2);
        target.add(7);
        target.add(4);
        target.add(3);
        target.remove(7);
        
        assertEquals(5, target.size());
        for (int i = 0; i < 5; i++) {
            final Integer actual = target.remove();
            assertEquals(new Integer(i), actual);
        }
    }

    /**
     * Test method for {@link com.fuerve.whiteboard.milestone2.structures.PriorityQueue#iterator()}.
     */
    @Test
    public void testIterator() {
        final Collection<Integer> target = new PriorityQueue<Integer>();
        final Set<Integer> expected = new HashSet<Integer>();
        for (int i = 0; i < 99; i++) {
            target.add(i);
            expected.add(i);
        }
        
        final Iterator<Integer> iter = target.iterator();
        while (iter.hasNext()) {
            expected.remove(iter.next());
        }
        assertTrue(expected.isEmpty());
    }

    /**
     * Test method for {@link com.fuerve.whiteboard.milestone2.structures.PriorityQueue#remove()}.
     */
    @Test
    public void testRemove() {
        final Queue<Integer> target = new PriorityQueue<Integer>();
        target.add(1);
        target.add(2);
        target.remove(2);
        assertTrue(target.contains(1));
        assertFalse(target.contains(2));
    }

    /**
     * Test method for {@link com.fuerve.whiteboard.milestone2.structures.PriorityQueue#peek()}.
     */
    @Test
    public void testPeek() {
        final Queue<Integer> target = new PriorityQueue<Integer>();
        target.add(1);
        target.add(2);
        assertEquals(new Integer(1), target.peek());
    }

    /***/
    @Test
    public void testOrdering() {
        final Queue<Integer> target = new PriorityQueue<Integer>();
        for (int i = 29999; i >= 0; i--) {
            target.add(i);
        }
        
        int last = target.peek();
        while (!target.isEmpty()) {
            final Integer current = target.remove();
            assertTrue(current >= last);
            last = current;
        }
        assertTrue(target.isEmpty());
    }
}
