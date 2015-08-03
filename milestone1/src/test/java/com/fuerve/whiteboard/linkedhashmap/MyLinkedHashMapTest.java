package com.fuerve.whiteboard.linkedhashmap;

import static org.junit.Assert.*;

import org.junit.Test;

import com.fuerve.whiteboard.linkedhashmap.MyLinkedHashMap.Entry;
import com.fuerve.whiteboard.linkedhashmap.MyLinkedHashMap.Iterator;

/**
 * Tests for the MyLinkedHashMapTest
 */
public class MyLinkedHashMapTest {

    /**
     * Test method for {@link com.fuerve.whiteboard.linkedhashmap.MyLinkedHashMap#getIterator()}.
     */
    @Test
    public void testGetIterator() {
        final MyLinkedHashMap<String, Integer> target = new MyLinkedHashMap<String, Integer>();
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

}
