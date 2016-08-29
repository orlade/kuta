package net.piemaster.kuta.algorithms.sorting;

import com.google.common.collect.ImmutableList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

/**
 * Abstract tests for Sorter classes.
 */
public abstract class SorterTest {

    /**
     * The Sorter under test, created by {@link #buildSorter()}.
     */
    protected Sorter<Integer> sorter;

    // Input arrays
    protected static final Integer[] EMPTY_ARRAY = new Integer[0];
    protected static final Integer[] SINGLE_ARRAY = new Integer[]{1};
    protected static final Integer[] MULTIPLE_ARRAY = new Integer[]{5, 2, 4, 1, 3, 10, 9, 8, 7, 6};
    protected static final Integer[] MULTIPLE_ARRAY_SORTED = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    // Input collections
    protected static final List<Integer> EMPTY_COLL = ImmutableList.of();
    protected static final List<Integer> SINGLE_COLL = ImmutableList.of(1);
    protected static final List<Integer> MULTIPLE_COLL = ImmutableList.of(5, 2, 4, 1, 3, 10, 9, 8, 7, 6);
    protected static final List<Integer> MULTIPLE_COLL_SORTED = ImmutableList.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    /**
     * Abstract method to build a Sorter of a specific type to test.
     *
     * @return A new Sorter instance.
     */
    protected abstract Sorter<Integer> buildSorter();

    @Before
    public void setup() {
        sorter = buildSorter();
    }

    @After
    public void tearDown() {
        sorter = null;
    }

    @Test
    public void testSortArray_single() {
        Integer[] items = SINGLE_ARRAY.clone();
        sorter.sort(items);
        assertThat(items).isEqualTo(SINGLE_ARRAY);
    }

    @Test
    public void testSortArray_multiple() {
        Integer[] items = MULTIPLE_ARRAY.clone();
        sorter.sort(items);
        assertThat(items).isEqualTo(MULTIPLE_ARRAY_SORTED);
    }

    @Test
    public void testSortArray_empty() {
        Integer[] items = EMPTY_ARRAY.clone();
        sorter.sort(items);
        assertThat(items).isEqualTo(EMPTY_ARRAY);
    }


    @Test
    public void testSortList_single() {
        List<Integer> items = new ArrayList<>(SINGLE_COLL);
        sorter.sort(items);
        assertThat(items).isEqualTo(SINGLE_COLL);
    }

    @Test
    public void testSortList_multiple() {
        List<Integer> items = new ArrayList<>(MULTIPLE_COLL);
        sorter.sort(items);
        assertThat(items).isEqualTo(MULTIPLE_COLL_SORTED);
    }

    @Test
    public void testSortList_empty() {
        List<Integer> items = new ArrayList<>(EMPTY_COLL);
        sorter.sort(items);
        assertThat(items).isEqualTo(EMPTY_COLL);
    }


}
