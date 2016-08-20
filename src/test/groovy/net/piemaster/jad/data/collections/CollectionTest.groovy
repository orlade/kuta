package net.piemaster.jad.data.collections

import spock.lang.Specification

abstract class CollectionTest extends Specification {

    static final TEST_STRING = "foo"

    static final TEST_STRINGS = ["foo", "bar"] as String[]

    protected instance = createInstance(String.class)

    abstract <T> Collection<T> createInstance(Class<T> cls)
    abstract <T> Collection<T> createInstanceWithItems(Class<T> cls, T... items)

    def "construct empty"() {
        when:
        instance = createInstance(String.class)

        then:
        instance.size() == 0
    }

    def "construct with items"() {
        when:
        instance = createInstanceWithItems(String.class, TEST_STRINGS)

        then:
        instance.size() == TEST_STRINGS.length
    }

    def "construct empty with null"() {
        when:
        instance = createInstanceWithItems(String.class, null)

        then:
        instance.size() == 0
    }

    def "add to empty instance"() {
        when:
        instance.add(TEST_STRING)

        then:
        instance.size() == 1
    }

    def "add to non-empty instance"() {
        given:
        instance.add("a")
        instance.add("b")

        when:
        instance.add(TEST_STRING)

        then:
        instance.size() == 3
    }

    def "remove from empty instance"() {
        when:
        final result = instance.remove(TEST_STRING)

        then:
        !result
        instance.size() == 0
    }

    def "remove existing value from singleton instance"() {
        given:
        instance.add(TEST_STRING)

        when:
        final result = instance.remove(TEST_STRING)

        then:
        result
        instance.size() == 0
    }

    def "remove non-existent value from singleton instance"() {
        given:
        instance.add(TEST_STRINGS[1])

        when:
        final result = instance.remove(TEST_STRINGS[0])

        then:
        !result
        instance.size() == 1
    }

    def "remove node from instance"() {
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
        ["a"]           | "a"
        ["a", "b"]      | "a"
        ["a", "b"]      | "b"
        ["a", "b", "c"] | "a"
        ["a", "b", "c"] | "b"
        ["a", "b", "c"] | "c"
    }

}
