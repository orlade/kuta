package net.piemaster.kuta.algorithms.sorting;

/**
 * Performs Insertion Sort to sort arrays and collections.
 */
public class InsertionSorter<T extends Comparable> extends Sorter<T> {

    /**
     * {@inheritDoc}
     */
    @Override
    public void sort(T[] array) {
        // Step through each element to shift it back into place.
        for (int i = 0; i < array.length; i++) {

            // Step backwards through each element to the left of i.
            for (int j = i - 1; j >= 0; j--) {

                // While the element is smaller than the previous element, shift it left.
                if (array[j + 1].compareTo(array[j]) < 0) {
                    T temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                } else {
                    // Every previous element is sorted, so stop once the element is in place.
                    break;
                }
            }
        }
    }

}
