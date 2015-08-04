package com.fuerve.whiteboard.milestone2.structures;

import static org.junit.Assert.*;

import java.lang.reflect.Field;

import org.junit.Test;

/**
 * Tests for the ArrayList class.
 */
public class ArrayListTest {

    /**
     * Test method for the copy constructor.
     */
    @Test
    public void testArrayListCopyConstructor() {
        final Collection<Integer> toCopy = new ArrayList<Integer>();
        for (int i = 0; i < 100; i++) {
            toCopy.add(i);
        }
        
        final ArrayList<Integer> target = new ArrayList<Integer>(toCopy);
        assertEquals(100, target.size());
        final Iterator<Integer> iter = target.iterator();
        for (int i = 0; i < 100; i++) {
            assertEquals(new Integer(i), iter.next());
        }
    }
    
    /**
     * Test method for {@link com.fuerve.whiteboard.milestone2.structures.ArrayList#size()}.
     */
    @Test
    public void testSize() {
        final Collection<Integer> target = new ArrayList<Integer>();
        assertEquals(0, target.size());
        target.add(1);
        target.add(2);
        assertEquals(2, target.size());
    }

    /**
     * Test method for {@link com.fuerve.whiteboard.milestone2.structures.ArrayList#isEmpty()}.
     */
    @Test
    public void testIsEmpty() {
        final Collection<Integer> target = new ArrayList<Integer>();
        assertTrue(target.isEmpty());
        target.add(1);
        assertFalse(target.isEmpty());
    }

    /**
     * Test method for {@link com.fuerve.whiteboard.milestone2.structures.ArrayList#contains(java.lang.Object)}.
     */
    @Test
    public void testContains() {
        final Collection<Integer> target = new ArrayList<Integer>();
        target.add(1);
        target.add(2);
        assertTrue(target.contains(1));
        assertFalse(target.contains(3));
    }

    
    /***/
    @Test
    public void testContainsNull() {
        final Collection<Integer> target = new ArrayList<Integer>();
        target.add(1);
        target.add(null);
        target.add(2);
        assertTrue(target.contains(1));
        assertTrue(target.contains(null));
        assertTrue(target.contains(2));
    }

    /**
     * Test method for {@link com.fuerve.whiteboard.milestone2.structures.ArrayList#add(java.lang.Object)}.
     */
    @Test
    public void testAddT() {
        final Collection<Integer> target = new ArrayList<Integer>();
        target.add(1);
        target.add(2);
        assertEquals(2, target.size());
    }

    /**
     * Test method for {@link com.fuerve.whiteboard.milestone2.structures.ArrayList#remove(java.lang.Object)}.
     */
    @Test
    public void testRemoveT() {
        final Collection<Integer> target = new ArrayList<Integer>();
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
        final Collection<Integer> target = new ArrayList<Integer>();
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
     * Test method for {@link com.fuerve.whiteboard.milestone2.structures.ArrayList#iterator()}.
     */
    @Test
    public void testIterator() {
        final Collection<Integer> target = new ArrayList<Integer>();
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
     * Test method for {@link com.fuerve.whiteboard.milestone2.structures.ArrayList#add(int, java.lang.Object)}.
     */
    @Test
    public void testAddIntT() {
        final List<Integer> target = new ArrayList<Integer>();
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
     * Test method for {@link com.fuerve.whiteboard.milestone2.structures.ArrayList#get(int)}.
     */
    @Test
    public void testGet() {
        final List<Integer> target = new ArrayList<Integer>();
        final Collection<Integer> reference = target;
        
        reference.add(0);
        reference.add(1);
        reference.add(2);
        
        assertEquals(new Integer(2), target.get(2));
    }
    
    /***/
    @Test(expected=IndexOutOfBoundsException.class)
    public void testGetOutOfBounds() {
        final List<Integer> target = new ArrayList<Integer>();
        final Collection<Integer> reference = target;
        
        reference.add(0);
        reference.add(1);
        reference.add(2);

        target.get(3);
    }

    /**
     * Test method for {@link com.fuerve.whiteboard.milestone2.structures.ArrayList#set(int, java.lang.Object)}.
     */
    @Test
    public void testSet() {
        final List<Integer> target = new ArrayList<Integer>();
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
        final List<Integer> target = new ArrayList<Integer>();
        target.add(0, 0);
        target.add(1, 1);
        target.add(2, 2);
        target.set(3, 3);
    }

    /**
     * Test method for {@link com.fuerve.whiteboard.milestone2.structures.ArrayList#remove(int)}.
     */
    @Test
    public void testRemoveInt() {
        final List<Integer> target = new ArrayList<Integer>();
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
        final List<Integer> target = new ArrayList<Integer>();
        target.add(0, 0);
        target.remove(2);
    }
    
    /**
     * Test method for {@link com.fuerve.whiteboard.milestone2.structures.ArrayList#indexOf(java.lang.Object)}.
     */
    @Test
    public void testIndexOf() {
        final List<Integer> target = new ArrayList<Integer>();
        target.add(0, 0);
        target.add(1, 1);
        target.add(2, 2);
        
        assertEquals(0, target.indexOf(0));
        assertEquals(2, target.indexOf(2));
        assertEquals(-1, target.indexOf(3));
    }

    /**
     * Test method for {@link com.fuerve.whiteboard.milestone2.structures.ArrayList#lastIndexOf(java.lang.Object)}.
     */
    @Test
    public void testLastIndexOf() {
        final List<Integer> target = new ArrayList<Integer>();
        target.add(0, 0);
        target.add(1, 1);
        target.add(2, 1);
        
        assertEquals(2, target.lastIndexOf(1));
        assertEquals(-1, target.lastIndexOf(3));
    }

    /***/
    @Test
    public void testGrowth() throws Exception {
        final List<Integer> target = new ArrayList<Integer>();
        final Field storeField = ArrayList.class.getDeclaredField("store");
        storeField.setAccessible(true);
        for (int i = 0; i < 100; i++) {
            target.add(i);
        }
        assertEquals(128, ((Object[]) storeField.get(target)).length);
    }
}
