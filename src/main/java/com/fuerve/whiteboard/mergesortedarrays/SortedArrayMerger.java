package com.fuerve.whiteboard.mergesortedarrays;

/**
 * An implementation for merging sorted arrays of integers.
 */
public class SortedArrayMerger {
    
    /**
     * Two-argument implementation.  Linear in the sum of the length of both arrays.
     * @param a The first array.
     * @param b The second array.
     * @return The sorted merger of the values contained in both arrays.  In the event that
     * one input array is null or empty, returns the other.  If both inputs are null or empty,
     * returns the empty array.
     */
    public static int[] merge(final int[] a, final int[] b) {
        if (a == null && b == null) {
            return new int[0];
        } else if (a == null) {
            return b;
        } else if (b == null) {
            return a;
        }

        final int count = a.length + b.length;
        final int[] result = new int[count];
        int[] current = a;
        int[] other = b;
        int currentIndex = 0;
        int otherIndex = 0;
        
        for (int i = 0; i < count; i++) {
            if (currentIndex >= current.length) {
                result[i] = other[otherIndex];
                otherIndex++;
            } else if (current[currentIndex] <= other[otherIndex]) {
                result[i] = current[currentIndex];
                currentIndex++;
            } else {
                result[i] = other[otherIndex];
                otherIndex++;
                
                final int[] temp = current;
                final int tempIndex = currentIndex;
                current = other;
                currentIndex = otherIndex;
                other = temp;
                otherIndex = tempIndex;
            }
        }
        
        return result;
    }
}
