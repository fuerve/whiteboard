package com.fuerve.whiteboard.milestone2.algorithms;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for the Fibonnaci class.
 */
public class FibonnaciTest {

    /**
     * Test method for {@link com.fuerve.whiteboard.milestone2.algorithms.Fibonnaci#naiveFibonnaci(int)}.
     */
    @Test
    public void testNaiveFibonnaciBaseCases() {
        Fibonnaci.naiveEvaluations = 0;
        assertEquals(1, Fibonnaci.naiveFibonnaci(1));
        assertEquals(1, Fibonnaci.naiveFibonnaci(2));
        System.out.println("testNaiveFibonnaciBaseCases calls: " + Fibonnaci.naiveEvaluations);
    }
    
    /***/
    @Test
    public void testNaiveFibonnaciPathological() {
        Fibonnaci.naiveEvaluations = 0;
        assertEquals(0, Fibonnaci.naiveFibonnaci(0));
        assertEquals(0, Fibonnaci.naiveFibonnaci(-1));
        assertEquals(0, Fibonnaci.naiveFibonnaci(Integer.MIN_VALUE));
        System.out.println("testNaiveFibonnaciPathological calls: " + Fibonnaci.naiveEvaluations);
    }
    
    /***/
    @Test
    public void testNaiveFibonnaciLarge() {
        Fibonnaci.naiveEvaluations = 0;
        final int result = Fibonnaci.naiveFibonnaci(42);
        System.out.println("testNaiveFibonnaciLarge calls: " + Fibonnaci.naiveEvaluations);
        assertEquals(267914296, result);
    }

    /**
     * Test method for {@link com.fuerve.whiteboard.milestone2.algorithms.Fibonnaci#dynamicFibonnaci(int)}.
     */
    @Test
    public void testDynamicFibonnaciBaseCases() {
        Fibonnaci.dynamicEvaluations = 0;
        assertEquals(1, Fibonnaci.dynamicFibonnaci(1));
        assertEquals(1, Fibonnaci.dynamicFibonnaci(2));
        System.out.println("testDynamicFibonnaciBaseCases calls: " + Fibonnaci.dynamicEvaluations);
    }
    
    /***/
    @Test
    public void testDynamicFibonnaciPathological() {
        Fibonnaci.dynamicEvaluations = 0;
        assertEquals(0, Fibonnaci.dynamicFibonnaci(-1));
        assertEquals(0, Fibonnaci.dynamicFibonnaci(0));
        assertEquals(0, Fibonnaci.dynamicFibonnaci(Integer.MIN_VALUE));
        System.out.println("testDynamicFibonnaciPathological calls: " + Fibonnaci.dynamicEvaluations);
    }
    /***/
    @Test
    public void testDynamiccFibonnaciLarge() {
        Fibonnaci.dynamicEvaluations = 0;
        final int result = Fibonnaci.dynamicFibonnaci(42);
        System.out.println("testDynamicFibonnaciLarge calls: " + Fibonnaci.dynamicEvaluations);
        assertEquals(267914296, result);
    }

}
