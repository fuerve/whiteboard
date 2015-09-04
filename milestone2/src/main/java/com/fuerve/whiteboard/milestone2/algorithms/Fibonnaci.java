package com.fuerve.whiteboard.milestone2.algorithms;

/**
 * Fibonnaci numbers.
 */
public class Fibonnaci {
    public static int naiveEvaluations = 0;
    public static int dynamicEvaluations = 0;
    
    /**
     * Naive recursive implementation.
     * @param n The ordinal of the Fibonnaci number to return.
     * @return The nth Fibonnaci number.
     */
    public static int naiveFibonnaci(final int n) {
        naiveEvaluations++;
        if (n <= 0) {
            return 0;
        } else if (n == 1 || n == 2) {
            return 1;
        } else {
            return naiveFibonnaci(n - 2) + naiveFibonnaci(n - 1);
        }
    }
    
    /**
     * Dynamic Fibonnaci.
     * @param n The ordinal of the Fibonnaci number to return.
     * @return The nth Fibonnaci number.
     */
    public static int dynamicFibonnaci(final int n) {
        if (n == 1 || n == 2) {
            dynamicEvaluations++;
            return 1;
        } else if (n <= 0) {
            dynamicEvaluations++;
            return 0;
        }
        final int[] table = new int[n - 1];
        for (int i = 1, j = 0; i < n; i++, j++) {
            dynamicEvaluations++;
            int sum = 0;
            if (i == 1 || i == 2) {
                sum = 1;
            } else {
                sum = table[j - 2] + table[j - 1];
            }
            
            table[j] = sum;
        }
        return table[n - 3] + table[n - 2];
    }
}
