package net.piemaster.jad.data.collections.lists

class LinkedListTest extends ListTest {

    def "get the last node"() {
        given:
        instance = createInstanceWithItems(String.class, TEST_STRINGS)

        when:
        final result = instance.getLastNode()

        then:
        result.getValue() == TEST_STRINGS[TEST_STRINGS.length - 1]
    }

    @Override
    def <T> List<T> createInstance(Class<T> cls) {
        return new LinkedList<>()
    }

    @Override
    def <T> List<T> createInstanceWithItems(Class<T> cls, T... items) {
        return new LinkedList<>(items)
    }

}
