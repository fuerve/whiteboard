package com.fuerve.whiteboard.cartesianproduct;

/**
 * Implementation of the Cartesian product of string arrays.
 */
public class CartesianProduct {
    /**
     * Given a set of input arrays of strings, returns a single comma-delimited string
     * consisting of the complete Cartesian product of those arrays.
     * @param inputs The array of arrays of strings.
     * @return The comma-delimited product of the input strings.
     */
    public static String cartesianProduct(final String[][] inputs) {
        if (inputs == null) {
            return null;
        }
        
        final StringBuilder sb = new StringBuilder();
        
        product("", 0, inputs, sb);
        
        return sb.toString();
    }
    
    /**
     * Recursively generates the Cartesian product of the input arrays.
     * @param prefix The current prefix, consisting of the current position in all arrays leading up to this call.
     * @param index The index into the inputs array.
     * @param inputs The inputs array.
     * @param sb The StringBuilder to which each entry shall be appended.
     */
    private static void product(final String prefix, final int index, final String[][] inputs, final StringBuilder sb) {
        for (int i = 0; i < inputs[index].length; i++) {
            if (index >= inputs.length - 1) {
                sb.append(prefix + inputs[index][i]);
            } else {
                product(prefix + inputs[index][i], index + 1, inputs, sb);
            }
            if (i < inputs[index].length - 1) {
                sb.append(", ");
            }
        }
    }
}
