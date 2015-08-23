package com.fuerve.whiteboard.milestone2.algorithms;

import static org.junit.Assert.*;

import org.junit.Test;

import com.fuerve.whiteboard.milestone2.structures.ArrayList;
import com.fuerve.whiteboard.milestone2.structures.Iterator;
import com.fuerve.whiteboard.milestone2.structures.LinkedList;
import com.fuerve.whiteboard.milestone2.structures.List;

/**
 * Tests for the Collections utility class.
 */
public class CollectionsTest {

    /**
     * Test method for {@link com.fuerve.whiteboard.milestone2.algorithms.Collections#sort(com.fuerve.whiteboard.milestone2.structures.Collection)}.
     */
    @Test
    public void testSortArrayList() {
        final List<Integer> target = new ArrayList<Integer>();
        target.add(3);
        target.add(2);
        target.add(1);
        
        Collections.sort(target);
        
        final Iterator<Integer> iter = target.iterator();
        for (int i = 1; i <= 3; i++) {
            assertEquals(new Integer(i), iter.next());
        }
    }
    
    /***/
    @Test
    public void testSortSeveralValuesArrayList() {
        final List<Integer> target = new ArrayList<Integer>();
        for (int i = 99; i >= 0; i--) {
            target.add(i);
        }
        
        Collections.sort(target);
        
        final Iterator<Integer> iter = target.iterator();
        for (int i = 0; i < 100; i++) {
            assertEquals(new Integer(i), iter.next());
        }
    }
    
    /***/
    @Test
    public void testSortManyValuesArrayList() {
        final List<Integer> target = new ArrayList<Integer>();
        for (int i = 49999; i >= 0; i--) {
            target.add(i);
        }
        
        Collections.sort(target);
        for (int i = 0; i < 50000; i++) {
            assertEquals(new Integer(i), target.get(i));
        }
    }
    
    /***/
    @Test(expected=IllegalArgumentException.class)
    public void testSortNullListArrayList() {
        Collections.sort(null);
    }
    
    /***/
    @Test
    public void testSortSingleItemListArrayList() {
        final List<Integer> target = new ArrayList<Integer>();
        target.add(4);
        Collections.sort(target);
        // Nothing changed?  Good.
        assertEquals(1, target.size());
        assertEquals(new Integer(4), target.get(0));
    }

    /**
     * Test method for {@link com.fuerve.whiteboard.milestone2.algorithms.Collections#binarySearch(com.fuerve.whiteboard.milestone2.structures.List, java.lang.Object)}.
     */
    @Test
    public void testSearchArrayList() {
        final List<Integer> target = new ArrayList<Integer>();
        for (int i = 9; i >= 0; i--) {
            target.add(i);
        }
        Collections.sort(target);
        assertEquals(-1, Collections.search(target, 50000));
        assertEquals(3, Collections.search(target, 3));
        assertEquals(7, Collections.search(target, 7));
    }
    
    /***/
    @Test
    public void testSearchManyValuesArrayList() {
        final List<Integer> target = new ArrayList<Integer>();
        for (int i = 49999; i >= 0; i--) {
            target.add(i);
        }
        Collections.sort(target);
        assertEquals(-1, Collections.search(target, 50000));
        assertEquals(31235, Collections.search(target, 31235));
        assertEquals(150, Collections.search(target, 150));
    }
    
    /***/
    @Test
    public void testSearchEmptyListArrayList() {
        final List<Integer> target = new ArrayList<Integer>();
        assertEquals(-1, Collections.search(target, 5));
    }
    
    /***/
    @Test
    public void testSearchNullListArrayList() {
        assertEquals(-1, Collections.search(null, new Integer(5)));
    }
    
    /**
     * Test method for {@link com.fuerve.whiteboard.milestone2.algorithms.Collections#sort(com.fuerve.whiteboard.milestone2.structures.Collection)}.
     */
    @Test
    public void testSortLinkedList() {
        final List<Integer> target = new LinkedList<Integer>();
        target.add(3);
        target.add(2);
        target.add(1);
        
        Collections.sort(target);
        
        final Iterator<Integer> iter = target.iterator();
        for (int i = 1; i <= 3; i++) {
            assertEquals(new Integer(i), iter.next());
        }
    }
    
    /***/
    @Test
    public void testSortSeveralValuesLinkedList() {
        final List<Integer> target = new LinkedList<Integer>();
        for (int i = 99; i >= 0; i--) {
            target.add(i);
        }
        
        Collections.sort(target);
        
        final Iterator<Integer> iter = target.iterator();
        for (int i = 0; i < 100; i++) {
            assertEquals(new Integer(i), iter.next());
        }
    }
    
    /***/
    @Test
    public void testSortManyValuesLinkedList() {
        final List<Integer> target = new LinkedList<Integer>();
        for (int i = 9999; i >= 0; i--) {
            target.add(i);
        }
        
        Collections.sort(target);
        for (int i = 0; i < 10000; i++) {
            assertEquals(new Integer(i), target.get(i));
        }
    }
    
    /***/
    @Test(expected=IllegalArgumentException.class)
    public void testSortNullListLinkedList() {
        Collections.sort(null);
    }
    
    /***/
    @Test
    public void testSortSingleItemListLinkedList() {
        final List<Integer> target = new LinkedList<Integer>();
        target.add(4);
        Collections.sort(target);
        // Nothing changed?  Good.
        assertEquals(1, target.size());
        assertEquals(new Integer(4), target.get(0));
    }

    /**
     * Test method for {@link com.fuerve.whiteboard.milestone2.algorithms.Collections#binarySearch(com.fuerve.whiteboard.milestone2.structures.List, java.lang.Object)}.
     */
    @Test
    public void testSearchLinkedList() {
        final List<Integer> target = new LinkedList<Integer>();
        for (int i = 9; i >= 0; i--) {
            target.add(i);
        }
        Collections.sort(target);
        assertEquals(-1, Collections.search(target, 50000));
        assertEquals(3, Collections.search(target, 3));
        assertEquals(7, Collections.search(target, 7));
    }
    
    /***/
    @Test
    public void testSearchManyValuesLinkedList() {
        final List<Integer> target = new LinkedList<Integer>();
        for (int i = 9999; i >= 0; i--) {
            target.add(i);
        }
        Collections.sort(target);
        assertEquals(-1, Collections.search(target, 50000));
        assertEquals(1235, Collections.search(target, 1235));
        assertEquals(150, Collections.search(target, 150));
    }
    
    /***/
    @Test
    public void testSearchEmptyListLinkedList() {
        final List<Integer> target = new LinkedList<Integer>();
        assertEquals(-1, Collections.search(target, 5));
    }
    
    /***/
    @Test
    public void testSearchNullListLinkedList() {
        assertEquals(-1, Collections.search(null, new Integer(5)));
    }
}
