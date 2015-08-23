package com.fuerve.whiteboard.hashmap;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for the MyHashMap class.
 */
public class MyHashMapTest {

    /**
     * Test method for {@link com.fuerve.whiteboard.hashmap.MyHashMap#get(java.lang.Object)}.
     */
    @Test
    public void testGet() {
        final MyHashMap<String, Integer> target = new MyHashMap<String, Integer>();
        target.put("foo", 1);
        assertTrue(target.containsKey("foo"));
        assertEquals((Integer) 1, target.get("foo"));
    }

    /**
     * Test method for {@link com.fuerve.whiteboard.hashmap.MyHashMap#put(java.lang.Object, java.lang.Object)}.
     */
    @Test
    public void testPut() {
        final MyHashMap<String, Integer> target = new MyHashMap<String, Integer>();
        target.put("foo", 1);
        assertTrue(target.containsKey("foo"));
    }

    /**
     * Test method for {@link com.fuerve.whiteboard.hashmap.MyHashMap#containsKey(java.lang.Object)}.
     */
    @Test
    public void testContainsKey() {
        final MyHashMap<String, Integer> target = new MyHashMap<String, Integer>();
        target.put("foo", 1);
        assertTrue(target.containsKey("foo"));
    }

    /**
     * Test method for {@link com.fuerve.whiteboard.hashmap.MyHashMap#containsValue(java.lang.Object)}.
     */
    @Test
    public void testContainsValue() {
        final MyHashMap<String, Integer> target = new MyHashMap<String, Integer>();
        target.put("foo", 1);
        assertTrue(target.containsValue(1));
    }

}
