package net.piemaster.jad.data.collections.lists

import net.piemaster.jad.data.collections.CollectionTest
import spock.lang.Shared

abstract class ListTest extends CollectionTest {

    @Shared
    def INSERT_STRING = "baz"

    protected instance = createInstance(String.class)
    protected multiInstance = createInstanceWithItems(String.class, TEST_STRINGS)

    def "get index in empty list"() {
        expect:
        instance.indexOf(TEST_STRING) == -1
    }

    def "get index in singleton list"() {
        given:
        instance.add(TEST_STRING)

        expect:
        instance.indexOf(query) == index

        where:
        query       | index
        TEST_STRING | 0
        "not there" | -1
    }

    def "get index in multi-node list"() {
        expect:
        multiInstance.indexOf(query) == index

        where:
        query           | index
        TEST_STRINGS[0] | 0
        TEST_STRINGS[1] | 1
        "not there"     | -1
    }

    def "get item in empty list"() {
        when:
        instance.get(0)

        then:
        thrown(IndexOutOfBoundsException)
    }

    def "get item in singleton list"() {
        given:
        instance.add(TEST_STRING)

        expect:
        instance.get(index) == item

        where:
        index | item
        0     | TEST_STRING
    }

    def "get item in multi-node list"() {
        expect:
        multiInstance.get(index) == item

        where:
        index | item
        0     | TEST_STRINGS[0]
        1     | TEST_STRINGS[1]
    }

    def "get throws exception if index out of range"() {
        given:
        instance = createInstanceWithItems(String.class, items)

        when:
        instance.get(index)

        then:
        thrown(IndexOutOfBoundsException)

        where:
        items                     | index
        new String[0]             | -1
        new String[0]             | 0
        [TEST_STRING] as String[] | -1
        [TEST_STRING] as String[] | 1
        TEST_STRINGS              | -1
        TEST_STRINGS              | 2
    }

    def "insert a node in a multi-node list"() {
        given:
        instance = createInstanceWithItems(String.class, TEST_STRINGS)

        when:
        instance.insert(INSERT_STRING, index)

        then:
        instance.size() == TEST_STRINGS.length + (value == null ? 0 : 1)
        instance.get(index) == value

        where:
        index | value
        0     | INSERT_STRING
        1     | INSERT_STRING
        2     | INSERT_STRING
    }

    def "insert item outside bounds throws exception"() {
        when:
        instance.insert(INSERT_STRING, index)

        then:
        thrown(IndexOutOfBoundsException)

        where:
        index << [-2, -1, 1, 2]
    }

    def "remove node by index from instance"() {
        given:
        instance = createInstanceWithItems(String.class, values as String[])
        final beforeSize = instance.size()

        when:
        final result = instance.remove(target)

        then:
        result
        instance.size() == beforeSize - 1

        where:
        values          | target
        ["a"]           | 0
        ["a", "b"]      | 0
        ["a", "b"]      | 1
        ["a", "b", "c"] | 0
        ["a", "b", "c"] | 1
        ["a", "b", "c"] | 2
    }

    def "remove by index outside bounds throws exception"() {
        when:
        instance.remove(index)

        then:
        thrown(IndexOutOfBoundsException)

        where:
        index << [-2, -1, 1, 2]
    }

    abstract def <T> List<T> createInstance(Class<T> cls)

    abstract def <T> List<T> createInstanceWithItems(Class<T> cls, T... items)

}
