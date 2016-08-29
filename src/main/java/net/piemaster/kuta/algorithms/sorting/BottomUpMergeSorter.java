package net.piemaster.kuta.algorithms.sorting;

import java.util.Arrays;

/**
 * Performs Merge Sort to sort arrays and collections.
 */
public class BottomUpMergeSorter<T extends Comparable> extends MergeSorter<T> {

    /**
     * {@inheritDoc}
     */
    @Override
    public void sort(T[] array) {
        int stepSize = 1;

        // Take increasingly large steps until the entire array is only a single step.
        while (stepSize < array.length) {
            // Initialize pointers to the start and end of the first slice (low - mid), and the
            // start and end of the second slice (mid - high).
            int low = 0;
            int mid, high;

            // So long as there are at least two slices left, take two steps.
            // If there is only one slice left, there is nothing to do.
            while (low + stepSize < array.length) {
                // Get indexes for two contiguous slices of size stepSize.
                mid = low + stepSize;
                high = Math.min(mid + stepSize, array.length);

                // Slice the array with the indexes.
                T[] left = Arrays.copyOfRange(array, low, mid);
                T[] right = Arrays.copyOfRange(array, mid, high);

                // Merge the slices.
                T[] merged = merge(left, right);

                // Write the merged array back over the original array.
                // Could be replaced with `System.arraycopy(merged, 0, array, low, merged.length);`.
                for (int i = 0; i < merged.length; i++) {
                    array[low + i] = merged[i];
                }

                // Step the low pointer forward.
                low = high;
            }

            // Double the step size to merge two of the previous step's merged pairs.
            stepSize *= 2;
        }
    }

}
