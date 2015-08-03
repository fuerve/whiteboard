package com.fuerve.whiteboard.postfixtoinfix;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for the PostfixToInfix class.
 */
public class PostfixToInfixTest {

    /**
     * Test method for {@link com.fuerve.whiteboard.postfixtoinfix.PostfixToInfix#translate(java.lang.String)}.
     */
    @Test
    public void testTranslate() {
        final String actual = PostfixToInfix.translate("1 2 +");
        assertEquals("1 + 2", actual);
    }

    /***/
    @Test
    public void testTranslateFactorsAndTerms() {
        final String actual = PostfixToInfix.translate("2 1 + 2 *");
        assertEquals("(2 + 1) * 2", actual);
    }
    
    /***/
    @Test
    public void testTranslateMultipleTerms() {
        final String actual = PostfixToInfix.translate("2 2 * 2 /");
        assertEquals("2 * 2 / 2", actual);
    }
    
    /***/
    @Test
    public void testTranslateManyTerms() {
        final String actual = PostfixToInfix.translate("2 2 + 3 * 6 - 4 /");
        assertEquals("((2 + 2) * 3 - 6) / 4", actual);
    }
}
