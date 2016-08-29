package net.piemaster.kuta.algorithms.sorting;

public class InsertionSorterTest extends SorterTest {

    @Override
    protected Sorter<Integer> buildSorter() {
        return new InsertionSorter<>();
    }

}
