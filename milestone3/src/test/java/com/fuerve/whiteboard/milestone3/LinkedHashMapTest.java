package com.fuerve.whiteboard.milestone3;

import static org.junit.Assert.*;

import org.junit.Test;

import com.fuerve.whiteboard.milestone3.LinkedHashMap.Entry;
import com.fuerve.whiteboard.milestone3.LinkedHashMap.Iterator;

/**
 * Tests for the LinkedHashMap class.
 */
public class LinkedHashMapTest {
    /**
     * Test method for {@link com.fuerve.whiteboard.linkedhashmap.LinkedHashMap#getIterator()}.
     */
    @Test
    public void testGetIterator() {
        final LinkedHashMap<String, Integer> target = new LinkedHashMap<String, Integer>();
        target.put("foo", 1);
        target.put("bar", 2);
        
        final Iterator<String, Integer> iter = target.getIterator();
        assertTrue(iter.hasNext());
        Entry<String, Integer> entry = iter.next();
        assertEquals("foo", entry.getKey());
        assertEquals((Integer) 1, entry.getValue());
        
        entry = iter.next();
        assertEquals("bar", entry.getKey());
        assertEquals((Integer) 2, entry.getValue());
    }
    
    /***/
    @Test
    public void testAccessOrdering() {
        final LinkedHashMap<String, Integer> target = new LinkedHashMap<String, Integer>(true);
        target.put("foo", 1);
        target.put("bar", 2);
        
        Iterator<String, Integer> iter = target.getIterator();
        assertTrue(iter.hasNext());
        Entry<String, Integer> entry = iter.next();
        assertEquals("bar", entry.getKey());
        assertEquals((Integer) 2, entry.getValue());
        
        entry = iter.next();
        assertEquals("foo", entry.getKey());
        assertEquals((Integer) 1, entry.getValue());
        
        target.get("foo");
        iter = target.getIterator();
        entry = iter.next();
        assertEquals("foo", entry.getKey());
        
    }
    
    /***/
    @Test
    public void testRemove() {
        final LinkedHashMap<String, Integer> target = new LinkedHashMap<String, Integer>();
        target.put("foo", 1);
        target.put("bar", 2);
        
        assertEquals(2, target.size());
        assertEquals(new Integer(1), target.remove("foo"));
        assertEquals(1, target.size());
        
        Iterator<String, Integer> iter = target.getIterator();
        assertTrue(iter.hasNext());
        final Entry<String, Integer> actual = iter.next();
        assertFalse(iter.hasNext());
        assertEquals("bar", actual.getKey());
        assertEquals(new Integer(2), actual.getValue());
    }

    /***/
    @Test
    public void testRemoveAccessOrdering() {
        final LinkedHashMap<String, Integer> target = new LinkedHashMap<String, Integer>(true);
        target.put("foo", 1);
        target.put("bar", 2);
        target.put("baz", 3);
        
        assertEquals(3, target.size());
        assertEquals(new Integer(2), target.remove("bar"));
        assertEquals(2, target.size());
        
        target.get("foo");
        
        Iterator<String, Integer> iter = target.getIterator();
        assertTrue(iter.hasNext());
        Entry<String, Integer> actual = iter.next();
        assertTrue(iter.hasNext());
        assertEquals("foo", actual.getKey());
        assertEquals(new Integer(1), actual.getValue());
        
        actual = iter.next();
        assertFalse(iter.hasNext());
        assertEquals("baz", actual.getKey());
        assertEquals(new Integer(3), actual.getValue());
    }
}
