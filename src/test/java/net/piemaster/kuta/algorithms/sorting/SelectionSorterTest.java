package net.piemaster.kuta.algorithms.sorting;

public class SelectionSorterTest extends SorterTest {

    @Override
    protected Sorter<Integer> buildSorter() {
        return new SelectionSorter<Integer>();
    }

}
