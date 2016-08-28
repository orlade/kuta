package net.piemaster.kuta.data.collections.lists;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.testing.ListTestSuiteBuilder;
import com.google.common.collect.testing.TestStringListGenerator;
import com.google.common.collect.testing.features.CollectionFeature;
import com.google.common.collect.testing.features.CollectionSize;
import com.google.common.collect.testing.features.ListFeature;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.AllTests;

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

import static com.google.common.truth.Truth.assertThat;

/**
 * Performs Guava's List test suite on the LinkedList implementation.
 */
public class LinkedListTest {

    @Test
    public void testGetNodeAt() {
        LinkedList list = new LinkedList(ImmutableList.of("a", "b", "c"));
        assertThat(list.getNodeAt(0).getValue()).isEqualTo("a");
        assertThat(list.getNodeAt(1).getValue()).isEqualTo("b");
        assertThat(list.getNodeAt(2).getValue()).isEqualTo("c");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetNodeAt_beyond() {
        LinkedList list = new LinkedList(ImmutableList.of("a", "b", "c"));
        list.getNodeAt(4);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetNodeAt_empty() {
        LinkedList list = new LinkedList();
        assertThat(list.getNodeAt(0));
    }

    @Test
    public void testGetLastNode() {
        LinkedList list = new LinkedList(ImmutableList.of("a", "b", "c"));
        assertThat(list.getLastNode().getValue()).isEqualTo("c");
    }

    @Test
    public void testGetLastNode_empty() {
        assertThat(new LinkedList().getLastNode()).isNull();
    }

}
