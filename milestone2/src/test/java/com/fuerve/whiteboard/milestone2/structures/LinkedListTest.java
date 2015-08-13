package com.fuerve.whiteboard.milestone2.structures;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for the LinkedList class.
 */
public class LinkedListTest {
    
    /**
     * Copy constructor test.
     */
    @Test
    public void testLinkedListCopyConstructor() {
        final Collection<Integer> toCopy = new LinkedList<Integer>();
        for (int i = 0; i < 100; i++) {
            toCopy.add(i);
        }

        final Collection<Integer> target = new LinkedList<Integer>(toCopy);
        assertEquals(100, target.size());
        final Iterator<Integer> iter = target.iterator();
        for (int i = 0; i < 100; i++) {
            assertEquals(new Integer(i), iter.next());
        }
    }
    
    /**
     * Test method for {@link com.fuerve.whiteboard.milestone2.structures.LinkedList#size()}.
     */
    @Test
    public void testSize() {
        final Collection<Integer> target = new LinkedList<Integer>();
        assertEquals(0, target.size());
        target.add(1);
        target.add(2);
        assertEquals(2, target.size());
    }

    /**
     * Test method for {@link com.fuerve.whiteboard.milestone2.structures.LinkedList#isEmpty()}.
     */
    @Test
    public void testIsEmpty() {
        final Collection<Integer> target = new LinkedList<Integer>();
        assertTrue(target.isEmpty());
        target.add(1);
        assertFalse(target.isEmpty());
    }

    /**
     * Test method for {@link com.fuerve.whiteboard.milestone2.structures.LinkedList#contains(java.lang.Object)}.
     */
    @Test
    public void testContains() {
        final Collection<Integer> target = new LinkedList<Integer>();
        target.add(1);
        target.add(2);
        assertTrue(target.contains(1));
        assertFalse(target.contains(3));
    }
    
    /***/
    @Test
    public void testContainsNull() {
        final Collection<Integer> target = new LinkedList<Integer>();
        target.add(1);
        target.add(null);
        target.add(2);
        assertTrue(target.contains(1));
        assertTrue(target.contains(null));
        assertTrue(target.contains(2));
    }

    /**
     * Test method for {@link com.fuerve.whiteboard.milestone2.structures.LinkedList#add(java.lang.Object)}.
     */
    @Test
    public void testAddT() {
        final Collection<Integer> target = new LinkedList<Integer>();
        target.add(1);
        target.add(2);
        assertEquals(2, target.size());
    }

    /**
     * Test method for {@link com.fuerve.whiteboard.milestone2.structures.LinkedList#remove(java.lang.Object)}.
     */
    @Test
    public void testRemoveT() {
        final Collection<Integer> target = new LinkedList<Integer>();
        target.add(1);
        target.add(2);
        target.remove(1);
        final Iterator<Integer> iter = target.iterator();
        assertEquals(new Integer(2), iter.next());
        assertEquals(1, target.size());
        assertTrue(target.remove(2));
    }
    
    /***/
    @Test
    public void testRemoveTNull() {
        final Collection<Integer> target = new LinkedList<Integer>();
        target.add(1);
        target.add(2);
        target.add(null);
        target.add(3);
        assertTrue(target.remove(null));
        assertEquals(3, target.size());
        assertTrue(target.remove(3));
        assertFalse(target.remove(4));
    }

    /**
     * Test method for {@link com.fuerve.whiteboard.milestone2.structures.LinkedList#iterator()}.
     */
    @Test
    public void testIterator() {
        final Collection<Integer> target = new LinkedList<Integer>();
        for (int i = 0; i < 100; i++) {
            target.add(i);
        }
        assertEquals(100, target.size());
        final Iterator<Integer> iter = target.iterator();
        for (int i = 0; i < 100; i++) {
            final Integer value = iter.next();
            assertEquals(new Integer(i), value);
        }
    }

    /**
     * Test method for {@link com.fuerve.whiteboard.milestone2.structures.LinkedList#add(int, java.lang.Object)}.
     */
    @Test
    public void testAddIntT() {
        final List<Integer> target = new LinkedList<Integer>();
        final Collection<Integer> reference = target;
        
        target.add(0, 0);
        target.add(1, 2);
        target.add(1, 1);
        
        final Iterator<Integer> iter = reference.iterator();
        for (int i = 0; i < 2; i++) {
            assertEquals(new Integer(i), iter.next());
        }
    }

    /**
     * Test method for {@link com.fuerve.whiteboard.milestone2.structures.LinkedList#get(int)}.
     */
    @Test
    public void testGet() {
        final List<Integer> target = new LinkedList<Integer>();
        final Collection<Integer> reference = target;
        
        reference.add(0);
        reference.add(1);
        reference.add(2);
        
        assertEquals(new Integer(2), target.get(2));
    }
    
    /***/
    @Test(expected=IndexOutOfBoundsException.class)
    public void testGetOutOfBounds() {
        final List<Integer> target = new LinkedList<Integer>();
        final Collection<Integer> reference = target;
        
        reference.add(0);
        reference.add(1);
        reference.add(2);

        target.get(3);
    }

    /**
     * Test method for {@link com.fuerve.whiteboard.milestone2.structures.LinkedList#set(int, java.lang.Object)}.
     */
    @Test
    public void testSet() {
        final List<Integer> target = new LinkedList<Integer>();
        final Collection<Integer> reference = target;
        
        target.add(0, 0);
        target.add(1, 2);
        target.add(2, 1);
        assertEquals(new Integer(2), target.set(1, 0));
        assertEquals(new Integer(1), target.set(2, 0));
        
        final Iterator<Integer> iter = reference.iterator();
        int count = 0;
        while (iter.hasNext()) {
            assertEquals(new Integer(0), iter.next());
            count++;
        }
        assertEquals(3, count);
    }
    
    /***/
    @Test(expected=IndexOutOfBoundsException.class)
    public void testSetOutOfBounds() {
        final List<Integer> target = new LinkedList<Integer>();
        target.add(0, 0);
        target.add(1, 1);
        target.add(2, 2);
        target.set(3, 3);
    }

    /**
     * Test method for {@link com.fuerve.whiteboard.milestone2.structures.LinkedList#remove(int)}.
     */
    @Test
    public void testRemoveInt() {
        final List<Integer> target = new LinkedList<Integer>();
        target.add(0, 0);
        target.add(1, 1);
        target.add(2, 2);
        
        assertEquals(new Integer(1), target.remove(1));
        
        assertEquals(2, target.size());
        assertFalse(target.contains(1));
        
        assertEquals(new Integer(2), target.remove(1));
        assertFalse(target.contains(2));
        assertEquals(1, target.size());
    }
    
    /***/
    @Test(expected=IndexOutOfBoundsException.class)
    public void testRemoveIntOutOfBounds() {
        final List<Integer> target = new LinkedList<Integer>();
        target.add(0, 0);
        target.remove(2);
    }
    
    /**
     * Test method for {@link com.fuerve.whiteboard.milestone2.structures.LinkedList#indexOf(java.lang.Object)}.
     */
    @Test
    public void testIndexOf() {
        final List<Integer> target = new LinkedList<Integer>();
        target.add(0, 0);
        target.add(1, 1);
        target.add(2, 2);
        
        assertEquals(0, target.indexOf(0));
        assertEquals(2, target.indexOf(2));
        assertEquals(-1, target.indexOf(3));
    }
    
    /**
     * Test method for {@link com.fuerve.whiteboard.milestone2.structures.LinkedList#lastIndexOf(java.lang.Object)}.
     */
    @Test
    public void testLastIndexOf() {
        final List<Integer> target = new LinkedList<Integer>();
        target.add(0, 0);
        target.add(1, 1);
        target.add(2, 1);
        
        assertEquals(2, target.lastIndexOf(1));
        assertEquals(-1, target.lastIndexOf(3));
    }
    
    /***/
    @Test
    public void testLastIndexOfTailNull() {
        final List<Integer> target = new LinkedList<Integer>();
        target.add(0, 0);
        target.add(1, 1);
        target.add(2, null);
        
        assertEquals(3, target.size());
        assertEquals(1, target.lastIndexOf(1));
        assertEquals(-1, target.lastIndexOf(3));
        assertEquals(2, target.lastIndexOf(null));
    }
    
    /***/
    @Test
    public void testPush() {
        final Deque<Integer> target = new LinkedList<Integer>();
        final List<Integer> reference = (LinkedList<Integer>) target;
        target.push(0);
        target.push(1);
        target.push(null);
        
        assertEquals(3, target.size());
        assertEquals(null, reference.get(0));
    }
    
    /***/
    @Test
    public void testPop() {
        final Deque<Integer> target = new LinkedList<Integer>();
        target.add(0);
        target.add(1);
        target.add(null);
        
        assertEquals(3, target.size());
        assertEquals(new Integer(0), target.pop());
        assertEquals(new Integer(1), target.pop());
        assertEquals(null, target.pop());
    }
    
    /***/
    @Test
    public void testPeek() {
        final Deque<Integer> target = new LinkedList<Integer>();
        target.add(0);
        target.add(1);
        target.add(null);
        
        assertEquals(new Integer(0), target.peek());
    }
    
    /***/
    @Test
    public void testRemove() {
        final Deque<Integer> target = new LinkedList<Integer>();
        target.add(0);
        target.add(1);
        target.add(null);
        
        assertEquals(3, target.size());
        assertEquals(null, target.remove());
        assertEquals(2, target.size());
    }
}
