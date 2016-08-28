package net.piemaster.kuta.algorithms.search

import spock.lang.Specification

class BinarySearcherTest extends Specification {

    def searcher = new BinarySearcher()

    def "it finds existing values"() {
        expect:
        searcher.search(array, target) == index

        where:
        target | index | array
        1      | 0     | [1] as Integer[]
        1      | 0     | [1, 2] as Integer[]
        1      | 0     | [1, 2, 3, 4, 5] as Integer[]
    }

    def "it returns non-negative for multiple values"() {
        when:
        final result = searcher.search(array, target)

        then:
        result >= 0
        result < array.length

        where:
        target | array
        1      | [1, 1] as Integer[]
        1      | [1, 1, 1] as Integer[]
        1      | [1, 1, 2] as Integer[]
        1      | [1, 1, 1, 2] as Integer[]
        1      | [1, 1, 2, 2, 2] as Integer[]
        1      | [0, 0, 1, 1, 2, 2] as Integer[]
    }

    def "it return -1 for non-existing values"() {
        expect:
        searcher.search(array, target) == -1

        where:
        target | array
        1      | [] as Integer[]
        1      | [0, 2] as Integer[]
        0      | [1, 2] as Integer[]
        3      | [0, 1, 2] as Integer[]
        1      | [0, 2, 3, 4, 5] as Integer[]
    }

}
