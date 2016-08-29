package net.piemaster.kuta.algorithms.search;

/**
 * Performs binary search on arrays of {@link Comparable} elements.
 *
 * @param <T> The comparable type of the elements in the arrays.
 */
public class BinarySearcher<T extends Comparable> implements Searcher<T, T[]> {

    /**
     * Performs a binary search for a target in a subset of an array, bounded by upper and lower
     * indexes.
     *
     * @param array  The array to search within. Must be sorted and not contain null values.
     * @param target The value to search for in the array. Must not be null.
     * @param lower  Lower bound to search within the array (inclusive).
     * @param upper  Upper limit of the array (exclusive).
     * @return The index of the target element in the array, or -1 if it does not exist.
     */
    int search(T[] array, T target, int lower, int upper) {
        if (lower < 0 || upper > array.length) {
            throw new IllegalArgumentException("Bounds must be within array");
        }

        // TODO: Handle possible overflow from addition.
        int mid = (upper + lower) / 2;
        int cmp = target.compareTo(array[mid]);
        if (cmp == 0) {
            return mid;
        }

        if (mid == lower || mid == upper) {
            return -1;
        }

        if (cmp < 0) {
            return search(array, target, lower, mid);
        } else {
            return search(array, target, mid, upper);
        }
    }

    /**
     * Performs a binary search for a target in an array.
     *
     * @param array  The array to search within. Must be sorted and not contain null values.
     * @param target The value to search for in the array. Must not be null.
     * @return The index of the target element in the array, or -1 if it does not exist.
     */
    public int search(T[] array, T target) {
        if (array == null || array.length == 0) {
            return -1;
        } else if (target == null) {
            throw new IllegalArgumentException("Target must not be null");
        }

        return search(array, target, 0, array.length);
    }

}
