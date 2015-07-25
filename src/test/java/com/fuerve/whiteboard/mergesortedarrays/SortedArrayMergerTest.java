package com.fuerve.whiteboard.mergesortedarrays;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 *  Tests for the SortedArrayMerger class.
 */
public class SortedArrayMergerTest {

    /**
     * Test method for {@link com.fuerve.whiteboard.mergesortedarrays.SortedArrayMerger#merge(int[], int[])}.
     */
    @Test
    public void testMergeArraysSameLength() {
        final int[] expected = { 1, 2, 3, 4, 5, 6 };
        assertArrayEquals(expected, SortedArrayMerger.merge(new int[] { 1, 3, 5 }, new int[] { 2, 4, 6 }));
    }
    
    /***/
    @Test
    public void testMergeArraysSameLengthReverseOrder() {
        final int[] expected = { 1, 2, 3, 4, 5, 6 };
        assertArrayEquals(expected, SortedArrayMerger.merge(new int[] { 2, 4, 6 }, new int[] { 1, 3, 5 }));
    }
    
    /***/
    @Test
    public void testMergeArraysWithBroaderSegments() {
        final int[] expected = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };
        assertArrayEquals(expected,
                SortedArrayMerger.merge(
                        new int[] { 1, 3, 4, 5, 10, 11, 12 },
                        new int[] { 2, 6, 7, 8, 9 }
                )
        );
    }
    
    /***/
    @Test
    public void testMergeArraysNull() {
        assertArrayEquals(new int[0], SortedArrayMerger.merge(null, null));
    }
    
    /***/
    @Test
    public void testMergeOneArrayNull() {
        final int[] expected = { 1, 3, 5 };
        assertArrayEquals(expected, SortedArrayMerger.merge(new int[] { 1, 3, 5 }, null));
    }
    
    /***/
    @Test
    public void testMergeArraysEmpty() {
        assertArrayEquals(new int[0], SortedArrayMerger.merge(new int[0], new int[0]));
    }
    
    /***/
    @Test
    public void testMergeOneArrayEmpty() {
        final int[] expected = { 2, 4, 6 };
        assertArrayEquals(expected, SortedArrayMerger.merge(new int[0], new int[] { 2, 4, 6 }));
    }
    
    /***/
    @Test
    public void testMergeSingleElementArrays() {
        final int[] expected = { 1, 2 };
        assertArrayEquals(expected, SortedArrayMerger.merge(new int[] { 2 }, new int[] { 1 }));
    }
    
    /***/
    @Test
    public void testMergeOneArraySingleElement() {
        final int[] expected = { 1 };
        assertArrayEquals(expected, SortedArrayMerger.merge(null, new int[] { 1 }));
    }
    
    /***/
    @Test
    public void testMergeUnsortedArrays() {
        // Garbage in, garbage out.
        final int[] expected = { 5, 3, 1, 6, 4, 2 };
        assertArrayEquals(expected, SortedArrayMerger.merge(new int[] { 5, 3, 1 }, new int[] { 6, 4, 2 }));
    }

}
