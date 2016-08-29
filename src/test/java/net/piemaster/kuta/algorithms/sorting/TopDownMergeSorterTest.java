package net.piemaster.kuta.algorithms.sorting;

public class TopDownMergeSorterTest extends SorterTest {

    @Override
    protected Sorter<Integer> buildSorter() {
        return new TopDownMergeSorter<>();
    }

}
