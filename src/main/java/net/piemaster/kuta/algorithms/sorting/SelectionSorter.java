package net.piemaster.kuta.algorithms.sorting;

/**
 * Performs Selection Sort to sort arrays and collections.
 */
public class SelectionSorter<T extends Comparable> extends Sorter<T> {

    /**
     * {@inheritDoc}
     */
    @Override
    public void sort(T[] array) {
        // No need to sort the last item once the rest have been sorted.
        for (int i = 0; i < array.length - 1; i++) {

            // Keep track of the smallest value seen in this pass.
            T min = array[i];
            int minIndex = i;

            // Since the first item will always be the smallest so far, start with the next.
            for (int j = i + 1; j < array.length; j++) {

                // If a value is smaller than the minimum, set it as the new minimum and record its
                // index so it can be swapped at the end.
                if (array[j].compareTo(min) < 0) {
                    min = array[j];
                    minIndex = j;
                }
            }

            // Swap the first item of this pass with the smallest item found.
            T temp = array[i];
            array[i] = min;
            array[minIndex] = temp;
        }
    }

}
