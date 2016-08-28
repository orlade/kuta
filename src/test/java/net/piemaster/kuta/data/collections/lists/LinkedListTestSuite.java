package net.piemaster.kuta.data.collections.lists;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.testing.AbstractTester;
import com.google.common.collect.testing.ListTestSuiteBuilder;
import com.google.common.collect.testing.TestStringListGenerator;
import com.google.common.collect.testing.features.CollectionFeature;
import com.google.common.collect.testing.features.CollectionSize;
import com.google.common.collect.testing.features.ListFeature;
import com.google.common.collect.testing.testers.ListSubListTester;
import junit.framework.Test;
import junit.framework.TestSuite;
import org.junit.runner.RunWith;
import org.junit.runners.AllTests;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Performs Guava's List test suite on the LinkedList implementation.
 */
@RunWith(AllTests.class)
public class LinkedListTestSuite {

    // Constant for filtering out unwanted test cases.
    private static final String SUB_LIST_SUITE_NAME = ListSubListTester.class.getName();
    private static final String SUB_LIST_METHOD_PREFIX = "testSubList_";

    /**
     * Runs the Guava list tests by adding its generated Test cases to the JUnit suite.
     */
    public static Test suite() {
        return filterOutSubListTests(allTests());
    }

    /**
     * Filters any subList() related test cases out of a TestSuite.
     * <p/>
     * This is a temporary workaround for not having implemented subList yet.
     *
     * @param suite The suite to filter subList tests out of.
     * @return The input suite with the subList tests removed.
     */
    private static TestSuite filterOutSubListTests(TestSuite suite) {
        ImmutableList<Test> tests = FluentIterable.from(Collections.list(suite.tests()))
                .filter(new Predicate<Test>() {
                    @Override
                    public boolean apply(@Nullable Test input) {
                        if (input instanceof TestSuite) {
                            return !((TestSuite) input).getName().equals(SUB_LIST_SUITE_NAME);
                        }
                        if (input instanceof AbstractTester) {
                            return !((AbstractTester) input).getName().startsWith(SUB_LIST_METHOD_PREFIX);
                        }
                        return true;
                    }
                }).transform(new Function<Test, Test>() {

                    @Nullable
                    @Override
                    public Test apply(@Nullable Test input) {
                        if (input instanceof TestSuite) {
                            return filterOutSubListTests((TestSuite) input);
                        }
                        return input;
                    }
                }).toList();

        TestSuite newSuite = new TestSuite();
        newSuite.setName(suite.getName());
        for (Test test : tests) {
            newSuite.addTest(test);
        }
        return newSuite;
    }

    /**
     * Returns the full suite of Guava list test cases.
     */
    private static TestSuite allTests() {
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
