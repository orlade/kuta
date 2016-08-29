package net.piemaster.kuta.algorithms.sorting;

/**
 * Performs Merge Sort to sort arrays and collections.
 */
public abstract class MergeSorter<T extends Comparable> extends Sorter<T> {

    /**
     * Merges two sorted arrays into one sorted array. The merge operation is common for different
     * implementations of merge sort.
     *
     * @param leftArray  One sorted array to merge.
     * @param rightArray The other sorted array to merge.
     * @return The sorted array created by merging the two input arrays.
     */
    protected T[] merge(T[] leftArray, T[] rightArray) {
        int leftLength = leftArray.length;
        int rightLength = rightArray.length;
        int mergedLength = leftLength + rightLength;

        // Create and cast an array of Comparables because only items of type T will be inserted.
        @SuppressWarnings("unchecked")
        T[] mergedArray = (T[]) new Comparable[mergedLength];

        int i = 0;
        int j = 0;

        while (i < leftLength && j < rightLength) {
            T left = leftArray[i];
            T right = rightArray[j];

            // If the values are equal, take the item from the left array for a stable sort.
            if (left.compareTo(right) <= 0) {
                mergedArray[i + j] = left;
                i++;
            } else {
                mergedArray[i + j] = right;
                j++;
            }
        }

        // If either array is non-empty, copy the remaining elements directly to the merged array.
        // After the previous while loop, one of the arrays must be empty,
        while (i < leftLength) {
            mergedArray[i + j] = leftArray[i];
            i++;
        }
        while (j < rightLength) {
            mergedArray[i + j] = rightArray[j];
            j++;
        }

        return mergedArray;
    }

}
