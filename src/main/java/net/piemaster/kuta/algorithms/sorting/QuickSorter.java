package net.piemaster.kuta.algorithms.sorting;

/**
 * Performs Quick Sort to sort arrays and collections.
 */
public class QuickSorter<T extends Comparable> extends Sorter<T> {

    /**
     * {@inheritDoc}
     */
    @Override
    public void sort(T[] array) {
        sort(array, 0, array.length);
    }

    /**
     * Recursively sorts the subset of the array between the lower and upper bounds.
     *
     * @param array The array to sort. The array is updated in-place.
     * @param lower The lower bound of the subset of the array to sort.
     * @param upper The upper bound of the subset of the array to sort.
     */
    protected void sort(T[] array, int lower, int upper) {
        // Recursive base case: If there are only zero or one elements, the array is already sorted.
        if (upper - lower < 2) {
            return;
        }

        // Naive choice of pivot: last element. Smarter strategies can yield better performance.
        int pivotIndex = upper - 1;
        T pivot = array[pivotIndex];

        // We want an outcome where the pivot is somewhere in the middle; all elements to the left
        // are smaller, and all elements to the right are larger.
        //
        // Walk from both ends towards the middle. Pause when an element is found that should be on
        // the other side of the pivot (i.e. left value > pivot, or right value < pivot).
        int left = lower;
        int right = upper - 2;
        while (left < right) {
            while (left < pivotIndex && array[left].compareTo(pivot) < 0) {
                left++;
            }
            while (right > lower && array[right].compareTo(pivot) > 0) {
                right--;
            }

            // If the pointers haven't crossed, swap the values so they're on the correct sides.
            if (left < right) {
                T temp = array[left];
                array[left] = array[right];
                array[right] = temp;
            }
        }

        // Once the pointers have crossed, the scan is done, so it's time to update the pivot.
        //
        // If the array subset was pre-sorted, the pivot is in the correct place already.
        // Therefore only swap the pivot if the left pointer found a value larger than it.
        if (array[left].compareTo(pivot) > 0) {
            array[pivotIndex] = array[left];
            array[left] = pivot;
        }

        // Once the two sides are relatively sorted, recursively quicksort both of them.
        sort(array, lower, left);
        sort(array, left + 1, upper);
    }

}
