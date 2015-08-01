package com.fuerve.whiteboard.cartesianproduct;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for the CartesianProduct class.
 */
public class CartesianProductTest {

    /**
     * Test method for {@link com.fuerve.whiteboard.cartesianproduct.CartesianProduct#cartesianProduct(java.lang.String[][])}.
     */
    @Test
    public void testSingleInputSingleString() {
        final String expected = "a1";
        final String[][] inputs = {
                { "a1" }
        };
        assertEquals(expected, CartesianProduct.cartesianProduct(inputs));
    }
    
    /***/
    @Test
    public void testSingleInputMultipleStrings() {
        final String expected = "a1, a2";
        final String[][] inputs = {
                { "a1", "a2" }
        };
        assertEquals(expected, CartesianProduct.cartesianProduct(inputs));
    }
    
    /***/
    @Test
    public void testMultipleInputsSingleStrings() {
        final String expected = "a1b1c1";
        final String[][] inputs = {
                { "a1" },
                { "b1" },
                { "c1" }
        };
        assertEquals(expected, CartesianProduct.cartesianProduct(inputs));
    }
    
    /***/
    @Test
    public void testMultipleInputsMultipleStrings() {
        final String expected = "a1b1c1, a1b1c2, a1b2c1, a1b2c2, a2b1c1, a2b1c2, a2b2c1, a2b2c2";
        final String[][] inputs = {
                { "a1", "a2" },
                { "b1", "b2" },
                { "c1", "c2" }
        };
        assertEquals(expected, CartesianProduct.cartesianProduct(inputs));
    }
    
    /***/
    @Test
    public void testMultipleInputsVaryingLength() {
        final String expected = "a1b1c1, a1b2c1, a2b1c1, a2b2c1, a3b1c1, a3b2c1";
        final String[][] inputs = {
                { "a1", "a2", "a3" },
                { "b1", "b2" },
                { "c1" }
        };
        assertEquals(expected, CartesianProduct.cartesianProduct(inputs));
    }
    
    /***/
    @Test
    public void testNullInputs() {
        final String expected = null;
        final String[][] inputs = null;
        assertEquals(expected, CartesianProduct.cartesianProduct(inputs));
    }
    
    /***/
    @Test
    public void testSomeEmptyInputs() {
        final String expected = "a1b1, a1b2, b1, b2, a2b1, a2b2";
        final String[][] inputs = {
                { "a1", "", "a2" },
                { "b1", "b2" }
        };
        assertEquals(expected, CartesianProduct.cartesianProduct(inputs));
    }
}
