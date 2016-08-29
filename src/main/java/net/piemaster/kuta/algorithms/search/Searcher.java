package net.piemaster.kuta.algorithms.search;

/**
 * Performs search on custom types.
 *
 * @param <T> The type of the elements in the data structure.
 * @param <S> The type of the data structure, typically parameterized by T.
 */
public interface Searcher<T, S> {

    /**
     * Performs a search for a target in a data structure.
     *
     * @param structure The data structure to search within.
     * @param target    The value to search for in the array.
     * @return The index of the target element in the data structure, or -1 if it does not exist. If
     * the data structure does not have a concept of an index, return a negative number if the
     * element does not exist, and a non-negative number otherwise.
     */
    int search(S structure, T target);

}
