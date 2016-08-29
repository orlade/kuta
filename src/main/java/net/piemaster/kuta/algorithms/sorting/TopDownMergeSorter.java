package net.piemaster.kuta.algorithms.sorting;

import java.util.Arrays;

/**
 * Performs Merge Sort to sort arrays and collections.
 */
public class TopDownMergeSorter<T extends Comparable> extends MergeSorter<T> {

    /**
     * {@inheritDoc}
     */
    @Override
    public void sort(T[] array) {
        // Recursive base case: If there are only zero or one elements, the array is already sorted.
        if (array.length < 2) {
            return;
        }

        T[][] parts = split(array);
        sort(parts[0]);
        sort(parts[1]);
        T[] merged = merge(parts[0], parts[1]);

        // Write the merged array back over the original array.
        // Could be replaced with `System.arraycopy(merged, 0, array, 0, merged.length);`.
        for (int i = 0; i < merged.length; i++) {
            array[i] = merged[i];
        }
    }

    /**
     * Splits an array in half, return an array with two elements: the left half followed by the
     * right half. Uses integer division, so the right half will be one element longer if the array
     * is an odd length.
     *
     * @param array The array to split. Must contain at least two elements.
     * @return An array of {@code [leftHalf, rightHalf]}.
     */
    protected T[][] split(T[] array) {
        if (array.length < 2) {
            throw new IllegalStateException("Cannot split array of length < 2");
        }
        int mid = array.length / 2;

        return (T[][]) new Comparable[][]{
                Arrays.copyOfRange(array, 0, mid),
                Arrays.copyOfRange(array, mid, array.length)
        };
    }

}
