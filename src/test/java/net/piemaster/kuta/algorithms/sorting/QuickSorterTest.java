package net.piemaster.kuta.algorithms.sorting;

public class QuickSorterTest extends SorterTest {

    @Override
    protected Sorter<Integer> buildSorter() {
        return new QuickSorter<>();
    }

}
