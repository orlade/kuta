package net.piemaster.kuta.algorithms.sorting;

import java.util.List;
import java.util.ListIterator;

public abstract class Sorter<T extends Comparable> {

    /**
     * Sorts the values within the given array.
     *
     * @param items The array to sort. Will be sorted after the method returns.
     */
    public abstract void sort(T[] items);

    /**
     * Casts each item in an array of Objects into a {@link Comparable}, a supertype of {@code T},
     * and copies each into a new {@code Comparable[]} array which may be cast to an array of type
     * {@code T[]}.
     * <p/>
     * Naturally this will fail with a {@link ClassCastException} if any of the Object elements are
     * not instances of {@link Comparable}.
     *
     * @param items An array of Objects to cast and copy to an array of {@code T} elements.
     */
    @SuppressWarnings("unchecked")
    private T[] convertArray(Object[] items) {
        T[] convertedItems = (T[]) new Comparable[items.length];
        for (int i = 0; i < items.length; i++) {
            convertedItems[i] = (T) items[i];
        }
        return convertedItems;
    }

    /**
     * Sorts the values within the given List.
     * <p/>
     * Taken directly from {@link java.util.Collections#sort(List)}.
     *
     * @param list The list to sort. Will be sorted after the method returns.
     */
    public void sort(List<T> list) {
        T[] array = convertArray(list.toArray());
        sort(array);
        ListIterator<T> iter = list.listIterator();
        for (T item : array) {
            iter.next();
            iter.set(item);
        }
    }

}
