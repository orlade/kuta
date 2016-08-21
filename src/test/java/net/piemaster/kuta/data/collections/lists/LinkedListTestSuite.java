package net.piemaster.kuta.data.collections.lists;

import com.google.common.collect.testing.ListTestSuiteBuilder;
import com.google.common.collect.testing.TestStringListGenerator;
import com.google.common.collect.testing.features.CollectionFeature;
import com.google.common.collect.testing.features.CollectionSize;
import com.google.common.collect.testing.features.ListFeature;
import junit.framework.Test;
import org.junit.runner.RunWith;
import org.junit.runners.AllTests;

import java.util.Arrays;
import java.util.List;

/**
 * Performs Guava's List test suite on the LinkedList implementation.
 */
@RunWith(AllTests.class)
public class LinkedListTestSuite {

    public static Test suite() {
        return ListTestSuiteBuilder.using(
                new TestStringListGenerator() {
                    @Override
                    protected List<String> create(String[] elements) {
                        return new LinkedList<String>(Arrays.asList(elements));
                    }
                })
                .named("LinkedList Tests")
                .withFeatures(
                        ListFeature.GENERAL_PURPOSE,
                        CollectionFeature.ALLOWS_NULL_VALUES,
                        CollectionSize.ANY)
                .createTestSuite();
    }

}
