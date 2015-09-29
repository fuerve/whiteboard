package com.fuerve.whiteboard.milestone3;

import static org.junit.Assert.*;

import org.junit.Test;

import com.fuerve.whiteboard.milestone3.LinkedHashMap.Entry;
import com.fuerve.whiteboard.milestone3.LinkedHashMap.Iterator;

/**
 * Tests for the LRUCache class.
 */
public class LRUCacheTest {

    /**
     * Test method for {@link com.fuerve.whiteboard.milestone3.LRUCache#put(java.lang.Object, java.lang.Object)}.
     */
    @Test(expected=IndexOutOfBoundsException.class)
    public void testBasicCachingAndEviction() {
        final LRUCache<Integer, Integer> target = new LRUCache<Integer, Integer>(5);
        
        for (int i = 0; i <= 5; i++) {
            target.put(new Integer(i), new Integer(i));
        }
        
        assertEquals(5, target.size());
        
        Iterator<Integer, Integer> iter = target.getIterator();
        for (int i = 5; i >= 1; i--) {
            final Entry<Integer, Integer> next = iter.next();
            assertEquals(new Integer(i), next.getValue());
            //System.out.println(next.getValue());
        }
        
        assertFalse(iter.hasNext());
        
        for (int i = 5; i >= 1; i--) {
            target.get(new Integer(i));
        }
        
        iter = target.getIterator();
        for (int i = 1; i <= 5; i++) {
            final Entry<Integer, Integer> next = iter.next();
            assertEquals(new Integer(i), next.getValue());
            //System.out.println(next.getValue());
        }
        
        assertFalse(iter.hasNext());
        
        iter.next();
    }

}
