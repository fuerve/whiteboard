package com.fuerve.whiteboard.postfixtoinfix;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Postfix to infix arithmetic translator.
 */
public class PostfixToInfix {
    /**
     * Given an input string in space-delimited postfix arithmetical notation, this method translates
     * the expression to its infix equivalent.  Only four operations are supported, with the natural
     * two levels of precedence.
     * @param input The postfix input string.
     * @return The infix translation.
     */
    public static String translate(final String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }
        
        final String[] tokens = input.split(" ");
        final Deque<Subexpression> operandStack = new ArrayDeque<Subexpression>();
        
        for (final String token : tokens) {
            if (isOperator(token)) {
                if (token != null) {
                    final Subexpression right = operandStack.pop();
                    final Subexpression left = operandStack.pop();
                    
                    if (isLowPrecedence(token)) {
                        operandStack.push(new Subexpression(left.expression + " " + token + " " + right.expression, token));
                    } else {
                        String rightExpression;
                        String leftExpression;
                        
                        if (isLowPrecedence(right.operator)) {
                            rightExpression = "(" + right.expression + ")";
                        } else {
                            rightExpression = right.expression;
                        }
                        
                        if (isLowPrecedence(left.operator)) {
                            leftExpression = "(" + left.expression + ")";
                        } else {
                            leftExpression = left.expression;
                        }
                        
                        operandStack.push(new Subexpression(leftExpression + " " + token + " " + rightExpression, token));
                    }
                }
            } else {
                operandStack.push(new Subexpression(token, ""));
            }
        }
        
        return operandStack.pop().expression;
    }
    
    /**
     * Helps determine whether a string is an arithmetical operator.
     * @param token The string to assess.
     * @return True if the string is an operator.
     */
    private static boolean isOperator(final String token) {
        if (token == null || token.isEmpty()) {
            return false;
        }
        
        if (token.equals("*") || token.equals("/") || token.equals("+") || token.equals("-")) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Determines whether an operator is low precedence (in this application, there are only two levels of precedence).
     * @param token The operator to assess.
     * @return True if the operator is low precedence.
     */
    private static boolean isLowPrecedence(final String token) {
        if (token == null || token.isEmpty()) {
            return false;
        }
        
        if (token.equals("+") || token.equals("-")) {
            return true;
        } else {
            return false;
        }
    }
    
    private static class Subexpression {
        final String expression;
        final String operator;
        
        /**
         * Ctor.
         * @param eexpression The expression as a string.
         * @param ooperator The operator.
         */
        public Subexpression(final String eexpression, final String ooperator) {
            expression = eexpression;
            operator = ooperator;
        }
    }
}
