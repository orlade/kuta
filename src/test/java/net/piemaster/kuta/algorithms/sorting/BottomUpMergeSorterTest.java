package net.piemaster.kuta.algorithms.sorting;

public class BottomUpMergeSorterTest extends SorterTest {

    @Override
    protected Sorter<Integer> buildSorter() {
        return new BottomUpMergeSorter<>();
    }

}
