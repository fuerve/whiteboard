package com.fuerve.whiteboard.listreversal;

import static org.junit.Assert.*;

import org.junit.Test;

import com.fuerve.whiteboard.listreversal.MyLinkedList.Iterator;

/**
 *  Tests for the MyLinkedList class.
 */
public class MyLinkedListTest {

    /**
     * Test method for {@link com.fuerve.whiteboard.listreversal.MyLinkedList#getIterator()}.
     */
    @Test
    public void testGetIterator() {
        final MyLinkedList<Integer> target = new MyLinkedList<Integer>();
        target.append(1);
        target.append(2);
        
        Iterator<Integer> iter = target.getIterator();
        
        assertTrue(iter.hasNext());
        int sum = 0;
        while (iter.hasNext()) {
            sum += iter.next();
        }
        assertEquals(3, sum);
    }
    
    /***/
    @Test
    public void testGetIteratorEmptyList() {
        final MyLinkedList<Integer> target = new MyLinkedList<Integer>();
        Iterator<Integer> iter = target.getIterator();
        assertFalse(iter.hasNext());
        assertNull(iter.next());
    }
    
    /***/
    @Test
    public void testGetIteratorSingleItemList() {
        final MyLinkedList<Integer> target = new MyLinkedList<Integer>();
        target.append(1);
        Iterator<Integer> iter = target.getIterator();
        
        assertTrue(iter.hasNext());
        int sum = 0;
        while (iter.hasNext()) {
            sum += iter.next();
        }
        assertEquals(1, sum);
    }

    /**
     * Test method for {@link com.fuerve.whiteboard.listreversal.MyLinkedList#contains(java.lang.Object)}.
     */
    @Test
    public void testContains() {
        final MyLinkedList<Integer> target = new MyLinkedList<Integer>();
        target.append(1);
        target.append(2);
        
        assertTrue(target.contains(1));
        assertTrue(target.contains(2));
    }
    
    /***/
    @Test
    public void testContainsFalse() {
        final MyLinkedList<Integer> target = new MyLinkedList<Integer>();
        target.append(1);
        target.append(2);
        
        assertFalse(target.contains(3));
    }
    
    /***/
    @Test
    public void testContainsEmptyList() {
        final MyLinkedList<Integer> target = new MyLinkedList<Integer>();
        assertFalse(target.contains(1));
    }
    
    /**
     * Test method for {@link com.fuerve.whiteboard.listreversal.MyLinkedList#insert(java.lang.Object, java.lang.Object)}.
     */
    @Test
    public void testInsert() {
        final MyLinkedList<Integer> target = new MyLinkedList<Integer>();
        target.append(1);
        target.insert(1, 2);
        
        assertTrue(target.contains(2));
        
        Iterator<Integer> iter = target.getIterator();
        iter.next();
        assertEquals((Integer) 2, (Integer) iter.next());
    }
    
    /***/
    @Test
    public void testInsertAtEnd() {
        final MyLinkedList<Integer> target = new MyLinkedList<Integer>();
        target.append(1);
        target.append(2);
        target.insert(null, 3);
        
        assertTrue(target.contains(3));
        
        Iterator<Integer> iter = target.getIterator();
        iter.next();
        iter.next();
        assertEquals((Integer) 3, (Integer) iter.next());
    }
    
    /***/
    @Test
    public void testAppend() {
        final MyLinkedList<Integer> target = new MyLinkedList<Integer>();
        target.append(1);
        target.append(2);
        
        assertTrue(target.contains(1));
        assertTrue(target.contains(2));
    }

    /**
     * Test method for {@link com.fuerve.whiteboard.listreversal.MyLinkedList#delete(java.lang.Object)}.
     */
    @Test
    public void testDelete() {
        final MyLinkedList<Integer> target = new MyLinkedList<Integer>();
        target.append(1);
        target.append(2);
        target.append(3);
        assertTrue(target.delete(2));
        assertFalse(target.contains(2));
    }
    
    /***/
    @Test
    public void testDeleteNodeDoesntExist() {
        final MyLinkedList<Integer> target = new MyLinkedList<Integer>();
        target.append(1);
        target.append(3);
        assertFalse(target.delete(2));
        assertFalse(target.contains(2));
    }
    
    /***/
    @Test
    public void testDeleteMultipleNodes() {
        final MyLinkedList<Integer> target = new MyLinkedList<Integer>();
        target.append(1);
        target.append(2);
        target.append(3);
        target.append(2);
        assertTrue(target.delete(2));
        assertFalse(target.contains(2));
    }
    
    /***/
    @Test
    public void testDeleteLastNode() {
        final MyLinkedList<Integer> target = new MyLinkedList<Integer>();
        target.append(1);
        target.append(2);
        target.append(3);
        assertTrue(target.delete(3));
        assertFalse(target.contains(3));
    }
    
    /***/
    @Test
    public void testDeleteFirstNode() {
        final MyLinkedList<Integer> target = new MyLinkedList<Integer>();
        target.append(1);
        target.append(2);
        target.append(3);
        assertTrue(target.delete(1));
        assertFalse(target.contains(1));
    }
    
    /***/
    @Test
    public void testDeleteMultipleHeadNodes() {
        final MyLinkedList<Integer> target = new MyLinkedList<Integer>();
        target.append(1);
        target.append(1);
        target.append(1);
        target.append(2);
        assertTrue(target.delete(1));
        assertFalse(target.contains(1));
    }
    
    /***/
    @Test
    public void testDeleteMultipleHeadNodesWithNoSubsequent() {
        final MyLinkedList<Integer> target = new MyLinkedList<Integer>();
        target.append(1);
        target.append(1);
        target.append(1);
        assertTrue(target.delete(1));
        assertFalse(target.contains(1));
        
        final Iterator<Integer> iter = target.getIterator();
        assertFalse(iter.hasNext());
    }

    /**
     * Test method for {@link com.fuerve.whiteboard.listreversal.MyLinkedList#reverse()}.
     */
    @Test
    public void testReverse() {
        final int[] expected = { 5, 4, 3, 2, 1 };
        final MyLinkedList<Integer> target = new MyLinkedList<Integer>();
        for (int i = 1; i <= 5; i++) {
            target.append(i);
        }
        target.reverse();
        
        final int[] actual = new int[5];
        final Iterator<Integer> iter = target.getIterator();
        int index = 0;
        while (iter.hasNext()) {
            actual[index] = iter.next();
            index++;
        }
        
        assertArrayEquals(expected, actual);
    }
    
    /***/
    @Test
    public void testReverseEmptyList() {
        final MyLinkedList<Integer> target = new MyLinkedList<Integer>();
        target.reverse();
        // No exceptions?  Awesome.
    }
    
    /***/
    @Test
    public void testReverseSingleItemList() {
        final int[] expected = { 1 };
        final MyLinkedList<Integer> target = new MyLinkedList<Integer>();
        target.append(1);
        target.reverse();
        
        final int[] actual = new int[1];
        final Iterator<Integer> iter = target.getIterator();
        int index = 0;
        while (iter.hasNext()) {
            actual[index] = iter.next();
            index++;
        }
        
        assertEquals(1, index);
        assertArrayEquals(actual, expected);
    }

}
