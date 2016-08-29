package net.piemaster.kuta.data.collections.trees;

import com.google.common.collect.testing.CollectionTestSuiteBuilder;
import com.google.common.collect.testing.TestStringCollectionGenerator;
import com.google.common.collect.testing.features.CollectionFeature;
import com.google.common.collect.testing.features.CollectionSize;
import junit.framework.Test;
import junit.framework.TestSuite;
import org.junit.runner.RunWith;
import org.junit.runners.AllTests;

import java.util.Arrays;
import java.util.Collection;

/**
 * Performs Guava's Collection test suite on Tree implementations.
 */
@RunWith(AllTests.class)
public class TreeTestSuite {

    /**
     * Runs the Guava list tests by adding its generated Test cases to the JUnit suite.
     */
    public static Test suite() {
        // TODO: Refactor to test multiple different Tree implementations.
        return new TreeTestSuite().allTests();
    }

    /**
     * Returns the full suite of Guava list test cases.
     */
    private TestSuite allTests() {
        TestStringCollectionGenerator generator = new TestStringCollectionGenerator() {
            @Override
            protected Collection<String> create(String[] elements) {
                // Use the test class's builder method.
                return createCollection(elements);
            }
        };

        return CollectionTestSuiteBuilder.using(generator)
                .named("Tree Tests")
                .withFeatures(
                        CollectionFeature.GENERAL_PURPOSE,
                        CollectionSize.ANY)
                .createTestSuite();
    }

    /**
     * Returns a new instance of the Tree class under test, populated with the given elements.
     *
     * @param elements The elements to add to the new Tree before returning.
     * @return The new Tree instance populated with {@code elements}.
     */
    protected Collection<String> createCollection(String[] elements) {
        return new SimpleBinaryTree<>(Arrays.asList(elements));
    }


}
