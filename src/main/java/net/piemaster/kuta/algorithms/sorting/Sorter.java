package net.piemaster.kuta.algorithms.sorting;

import java.util.Collection;

public interface Sorter<T> {

    /**
     * Sorts the values within the given array.
     *
     * @param items The array to sort. Will be sorted after the method returns.
     */
    void sort(T[] items);

    /**
     * Sorts the values within the given collection.
     *
     * @param items The collection to sort. Will be sorted after the method returns.
     */
    void sort(Collection<T> items);

}
