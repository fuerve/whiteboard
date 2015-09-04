package com.fuerve.whiteboard.milestone2.algorithms;

import com.fuerve.whiteboard.milestone2.structures.ArrayList;
import com.fuerve.whiteboard.milestone2.structures.List;

/**
 * Collection utilities, such as sorting and searching.
 */
public final class Collections {
    /**
     * Hidden constructor.
     */
    private Collections() {
        
    }
    
    /**
     * Sorts a list in the natural order of its elements.
     * @param list The list to sort.
     * @return The sorted list.
     */
    public static <T extends Comparable<T>> void sort(final List<T> list) {
        if (list == null) {
            throw new IllegalArgumentException("Collections was asked to sort a null list");
        } else if (list.size() == 1) {
            return;
        } else {
            quickSort(list, 0, list.size() - 1);
        }
    }
    
    /**
     * Executes an iteration of quicksort.
     * @param list The list to be sorted.
     * @param leftIndex The left index of the sublist.
     * @param rightIndex The right index of the sublist.
     */
    private static <T extends Comparable<T>> void quickSort(final List<T> list, final int leftIndex, final int rightIndex) {
        if (leftIndex < rightIndex) {
            final T pivot = getMedian(list, leftIndex, rightIndex);
            final int pivotIndex = partition(list, leftIndex, rightIndex, pivot);
            quickSort(list, leftIndex, pivotIndex - 1);
            quickSort(list, pivotIndex + 1, rightIndex);
        }
    }
    
    /**
     * Finds the index of partitioned split of a list based on a pivot value.
     * @param list The list to partition.
     * @param leftIndex The left index into the list that demarcates the sublist.
     * @param rightIndex The right index into the list that demarcates the sublist.
     * @param pivot The pivot value around which to partition.
     * @return
     */
    private static <T extends Comparable<T>> int partition(final List<T> list, final int leftIndex, final int rightIndex, final T pivot) {
        int left = leftIndex;
        int right = rightIndex - 1;
        while (left < right) {
            while (list.get(left).compareTo(pivot) < 0) {
                left++;
            }
            while ((right + 1) > leftIndex && list.get(right).compareTo(pivot) > 0) {
                right--;
            }
            if (left >= right) {
                break;
            } else {
                swap(list, left, right);
            }
        }
        swap(list, left, rightIndex);
        return left;
    }
    
    /**
     * Given a list and a subrange within that list, finds something like the median.
     * @param list The list.
     * @param leftIndex The left index of the list subrange.
     * @param rightIndex The right index of the list subrange.
     * @return The appropriate pivot value.
     */
    private static <T extends Comparable<T>> T getMedian(final List<T> list, final int leftIndex, final int rightIndex) {
        final int center = (leftIndex + rightIndex) / 2;
        if (list.get(leftIndex).compareTo(list.get(center)) > 0) {
            swap(list, leftIndex, center);
        }
        
        if (list.get(leftIndex).compareTo(list.get(rightIndex)) > 0) {
            swap(list, leftIndex, rightIndex);
        }
        
        if (list.get(center).compareTo(list.get(rightIndex)) > 0) {
            swap(list, center, rightIndex);
        }
        
        swap(list, center, rightIndex);
        return list.get(rightIndex);
    }
    
    /**
     * Swaps two values in a list by ordinal.
     * @param list The list in which to swap.
     * @param i The first ordinal.
     * @param j The second ordinal.
     */
    private static <T extends Comparable<T>> void swap(final List<T> list, final int i, final int j) {
        final T temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
    
    /**
     * Given a sorted list, performs a binary search for a given element and returns its index
     * into the list.
     * @param list The list to search.
     * @param element The element for which to search.
     * @return The index of the element, or -1 if it does not exist.
     */
    public static <T extends Comparable<T>> int search(final List<T> list, final T element) {
        if (list == null || list.size() <= 0) {
            return -1;
        } else {
            return binarySearch(list, element, 0, list.size() - 1);
        }
    }
    
    /**
     * Given a sorted list, performs a binary search for a given element and returns its index
     * into the list.
     * @param list The list to search.
     * @param element The element for which to search.
     * @param leftIndex The left boundary of the sublist to search.
     * @param rightIndex The right boundary of the sublist to search.
     * @return
     */
    private static <T extends Comparable<T>> int binarySearch(final List<T> list, final T element,
            final int leftIndex, final int rightIndex) {
        if (leftIndex >= rightIndex) {
            if (list.get(leftIndex) == element || list.get(leftIndex).equals(element)) {
                return leftIndex;
            } else {
                return -1;
            }
        } else {
            final int centerIndex = (leftIndex + rightIndex) / 2;
            final T elementAtCenter = list.get(centerIndex);
            if (elementAtCenter.compareTo(element) > 0) {
                return binarySearch(list, element, leftIndex, centerIndex - 1);
            } else if (elementAtCenter.compareTo(element) == 0) {
                return centerIndex;
            } else {
                return binarySearch(list, element, centerIndex + 1, rightIndex);
            }
        }
    }
    
    /**
     * Gets the maximal sum subset of the given input.
     * @param input The input from which to derive the maximal sum subset.
     * @return The maximal sum subset.
     */
    public static List<Integer> maximalSumSubset(final List<Integer> input) {
        final Integer[] arrayInput = (Integer[]) new Integer[input.size()];
        for (int i = 0; i < input.size(); i++) {
            arrayInput[i] = input.get(i);
        }
        final Integer[] arrayOutput = maximalSumSubset(arrayInput);
        final List<Integer> output = new ArrayList<Integer>();
        for (int i = 0; i < arrayOutput.length; i++) {
            output.add(arrayOutput[i]);
        }
        return output;
    }
    
    /**
     * Gets the maximal sum subset of the given input.
     * @param input The input from which to derive the maximal sum subset.
     * @return The maximal sum subset.
     */
    public static Integer[] maximalSumSubset(final Integer[] input) {
        if (input == null || input.length == 0) {
            return null;
        }
        int currentSum = 0;
        int greatestSum = Integer.MIN_VALUE;
        
        int start = -1, end = 0;
        
        for (int i = 0; i < input.length; i++) {
            if (currentSum == Integer.MIN_VALUE) {
                currentSum = input[i];
            } else {
                currentSum += input[i];
            }
            
            if (currentSum > greatestSum) {
                greatestSum = currentSum;
                end = i;
            } else {
                start++;
            }
        }
        
        final Integer[] result = new Integer[(end - start) + 1];
        for (int i = start, j = 0; i <= end; i++, j++) {
            result[j] = input[i];
        }
        return result;
    }
}
