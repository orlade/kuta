package net.piemaster.jad.data.collections.lists;

import lombok.Getter;
import lombok.Setter;

/**
 * A singly-linked list.
 * <p/>
 * New items are appended to the tail of the list.
 *
 * @param <T> The type of data stored in the list.
 */
public class LinkedList<T> implements List<T> {

    /**
     * A single element in the linked list.
     */
    @Getter
    @Setter
    class Node {
        private T value;
        private Node next;

        public Node(T value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return String.format("Node[%s]", value);
        }
    }

    /**
     * The first node in the list, from which all traversals begin.
     */
    private Node head;

    public LinkedList() {
    }

    public LinkedList(T... items) {
        if (items == null) {
            return;
        }
        for (T item : items) {
            add(item);
        }
    }

    // INSERTION

    @Override
    public void add(T item) {
        Node node = new Node(item);
        if (head == null) {
            head = node;
        } else {
            getLastNode().setNext(node);
        }
    }

    @Override
    public void insert(T item, int index) {
        int i = 0;
        Node previous = null;
        Node current = head;

        if (index < 0) {
            throw new IndexOutOfBoundsException(
                    String.format("Index %d must be non-negative", index));
        }

        // Update the "previous" pointer to the node before the insert.
        while (i < index) {
            if (current == null) {
                throw new IndexOutOfBoundsException(
                        String.format("Insert index %d > size %d", index, i));
            }
            previous = current;
            current = current.getNext();
            i++;
        }

        Node node = new Node(item);
        node.setNext(current);
        if (previous != null) {
            previous.setNext(node);
        } else {
            head = node;
        }
    }

    // DELETION

    @Override
    public boolean remove(T item) {
        Node previous = null;
        Node current = head;
        while (current != null) {
            if (item == null && current.getValue() == null ||
                    item != null && item.equals(current.getValue())) {
                if (previous == null) {
                    head = current.getNext();
                } else {
                    previous.setNext(current.getNext());
                }
                return true;
            }
            previous = current;
            current = current.getNext();
        }
        return false;
    }

    @Override
    public T remove(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Index must be non-negative");
        }
        if (head == null) {
            throw new IndexOutOfBoundsException("Index exceeds list size of 0");
        }

        int i = 0;
        Node current = head;
        Node previous = null;

        while (i < index) {
            previous = current;
            current = current.getNext();
            if (current == null) {
                throw new IndexOutOfBoundsException("Index exceeds list size of " + i);
            }
            i++;
        }

        if (previous != null) {
            previous.setNext(current.getNext());
        } else {
            head = current.getNext();
        }
        return current.getValue();
    }

    // SEARCH

    @Override
    public boolean contains(T item) {
        return indexOf(item) != -1;
    }

    public T get(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Index must be non-negative");
        }
        if (head == null) {
            throw new IndexOutOfBoundsException("Index exceeds list size of 0");
        }

        int i = 0;
        Node current = head;

        while (i < index) {
            current = current.getNext();
            if (current == null) {
                throw new IndexOutOfBoundsException("Index exceeds list size of " + i);
            }
            i++;
        }
        return current.getValue();
    }

    @Override
    public int indexOf(T item) {
        int i = 0;
        Node current = head;

        while (current != null) {
            if (item == null && current.getValue() == null ||
                    item != null && item.equals(current.getValue())) {
                return i;
            }
            current = current.getNext();
            i++;
        }
        return -1;
    }

    Node getLastNode() {
        if (head == null) {
            return null;
        }

        Node current = head;
        while (current.getNext() != null) {
            current = current.getNext();
        }
        return current;
    }

    // PROPERTIES

    @Override
    public int size() {
        int count = 0;
        Node current = head;
        while (current != null) {
            current = current.getNext();
            count++;
        }
        return count;
    }


}
