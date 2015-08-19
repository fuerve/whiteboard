package com.fuerve.whiteboard.milestone2.structures;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for the BloomFilter class.
 */
public class BloomFilterTest {

    /**
     * Test method for {@link com.fuerve.whiteboard.milestone2.structures.BloomFilter#BloomFilter()}.
     */
    @Test
    public void testBloomFilter() {
        final BloomFilter<String> target = new BloomFilter<String>();
        assertFalse(target.check("foo"));
        target.add("foo");
        assertTrue(target.check("foo"));
        assertFalse(target.check("bar"));
    }

    /**
     * Test method for {@link com.fuerve.whiteboard.milestone2.structures.BloomFilter#BloomFilter(int)}.
     */
    @Test
    public void testBloomFilterInt() {
        final BloomFilter<String> target = new BloomFilter<String>(64);
        assertFalse(target.check("foo"));
        target.add("foo");
        assertTrue(target.check("foo"));
        assertFalse(target.check("bar"));
    }

    /**
     * Test method for {@link com.fuerve.whiteboard.milestone2.structures.BloomFilter#add(java.lang.Object)}.
     */
    @Test
    public void testAddAndCheck() {
        final BloomFilter<Integer> target = new BloomFilter<Integer>(128);
        for (int i = 0; i < 64; i++) {
            target.add(new Integer(i));
        }
        
        for (int i = 64; i < 128; i++) {
            assertFalse(target.check(new Integer(i)));
        }
    }
}
