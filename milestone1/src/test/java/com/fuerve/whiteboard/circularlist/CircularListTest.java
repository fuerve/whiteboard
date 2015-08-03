package com.fuerve.whiteboard.circularlist;

import static org.junit.Assert.*;

import org.junit.Test;

import com.fuerve.whiteboard.circularlist.CircularList.Iterator;

/**
 * Tests for the CircularList class.
 */
public class CircularListTest {

    /**
     * Test method for {@link com.fuerve.whiteboard.circularlist.CircularList#contains(java.lang.Object)}.
     */
    @Test
    public void testContains() {
        final CircularList<Integer> target = new CircularList<Integer>();
        target.insert(null, 1);
        target.insert(1, 2);
        assertTrue(target.contains(1));
    }
    
    @Test
    public void testContainsItemNotPresent() {
        final CircularList<Integer> target = new CircularList<Integer>();
        target.insert(null, 1);
        assertFalse(target.contains(2));
    }
    
    /***/
    @Test
    public void testContainsEmptyList() {
        final CircularList<Integer> target = new CircularList<Integer>();
        assertFalse(target.contains(1));
    }
    
    /***/
    @Test
    public void testContainsSingleItemList() {
        final CircularList<Integer> target = new CircularList<Integer>();
        target.insert(null, 1);
        assertTrue(target.contains(1));
    }
    
    /***/
    @Test
    public void testContainsMultipleContiguousLikeItems() {
        final CircularList<Integer> target = new CircularList<Integer>();
        target.insert(null, 1);
        target.insert(1, 2);
        target.insert(2, 2);
        assertTrue(target.contains(2));
    }
    
    /***/
    @Test
    public void testContainsMultipleSeparateLikeItems() {
        final CircularList<Integer> target = new CircularList<Integer>();
        target.insert(null, 1);
        target.insert(1, 2);
        target.insert(2, 3);
        target.insert(3, 2);
        assertTrue(target.contains(2));
    }

    /**
     * Test method for {@link com.fuerve.whiteboard.circularlist.CircularList#delete(java.lang.Object)}.
     */
    @Test
    public void testDelete() {
        final CircularList<Integer> target = new CircularList<Integer>();
        target.insert(null, 1);
        target.insert(1, 2);
        assertTrue(target.delete(1));
        assertFalse(target.contains(1));
    }
    
    /***/
    @Test
    public void testDeleteItemNotPresent() {
        final CircularList<Integer> target = new CircularList<Integer>();
        target.insert(null, 1);
        target.insert(1, 2);
        assertFalse(target.delete(3));
        assertFalse(target.contains(3));
    }
    
    /***/
    @Test
    public void testDeleteMultipleInstances() {
        final CircularList<Integer> target = new CircularList<Integer>();
        target.insert(null, 1);
        target.insert(1, 1);
        target.insert(1, 2);
        assertTrue(target.delete(1));
        assertFalse(target.contains(1));
    }
    
    /***/
    @Test
    public void testDeleteEmptyList() {
        final CircularList<Integer> target = new CircularList<Integer>();
        assertFalse(target.delete(1));
        assertFalse(target.contains(1));
    }
    
    /***/
    @Test
    public void testDeleteSingleItemList() {
        final CircularList<Integer> target = new CircularList<Integer>();
        target.insert(null, 1);
        assertTrue(target.delete(1));
        assertFalse(target.contains(1));
    }
    
    /***/
    @Test
    public void testDeleteNullItem() {
        final CircularList<Integer> target = new CircularList<Integer>();
        target.insert(null, null);
        target.insert(null, 2);
        assertTrue(target.delete(null));
        assertFalse(target.contains(null));
    }

    /**
     * Test method for {@link com.fuerve.whiteboard.circularlist.CircularList#insert(java.lang.Object, java.lang.Object)}.
     */
    @Test
    public void testInsert() {
        final CircularList<Integer> target = new CircularList<Integer>();
        target.insert(null, 1);
        target.insert(1, 2);
        assertTrue(target.contains(1));
        assertTrue(target.contains(2));
    }
    
    /***/
    @Test
    public void testInsertAfterDoesNotExist() {
        final CircularList<Integer> target = new CircularList<Integer>();
        target.insert(null, 1);
        target.insert(1, 2);
        target.insert(3, 3);
        assertTrue(target.contains(1));
        assertTrue(target.contains(2));
        assertTrue(target.contains(3));
    }
    
    /***/
    @Test
    public void testInsertNull() {
        final CircularList<Integer> target = new CircularList<Integer>();
        target.insert(null, 1);
        target.insert(1, null);
        assertTrue(target.contains(1));
        assertTrue(target.contains(null));
    }

    /**
     * Test method for {@link com.fuerve.whiteboard.circularlist.CircularList#getIterator()}.
     */
    @Test
    public void testGetIterator() {
        final CircularList<Integer> target = new CircularList<Integer>();
        target.insert(null, 1);
        target.insert(1, 2);
        final Iterator<Integer> iter = target.getIterator();
        assertTrue(iter.hasNext());
        assertEquals((Integer) 1, iter.next());
        assertEquals((Integer) 2, iter.next());
    }
    
    /***/
    @Test
    public void testIteratorEmptyList() {
        final CircularList<Integer> target = new CircularList<Integer>();
        final Iterator<Integer> iter = target.getIterator();
        assertNotNull(iter);
        assertFalse(iter.hasNext());
    }
    
    /***/
    @Test
    public void testIteratorSingleItemList() {
        final CircularList<Integer> target = new CircularList<Integer>();
        target.insert(null, 1);
        final Iterator<Integer> iter = target.getIterator();
        assertTrue(iter.hasNext());
        assertEquals((Integer) 1, iter.next());
    }
    
    /***/
    @Test
    public void testIteratorNullValues() {
        final CircularList<Integer> target = new CircularList<Integer>();
        target.insert(null, 1);
        target.insert(1, null);
        final Iterator<Integer> iter = target.getIterator();
        assertTrue(iter.hasNext());
        assertEquals((Integer) 1, iter.next());
        assertEquals(null, iter.next());
    }

}
