package net.piemaster.jad.data.collections.lists;

import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * A naive singly-linked list. Strives for the simplest implementation, not the most efficient.
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
    public boolean add(T item) {
        // Null is allowed.
        Node node = new Node(item);
        if (head == null) {
            head = node;
        } else {
            getLastNode().setNext(node);
        }
        return true;
    }

    @Override
    public void add(int index, T element) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Cannot add outside list bounds");
        }
        java.util.ListIterator<T> iter = listIterator();
        while (iter.nextIndex() < index) {
            if (!iter.hasNext()) {
                throw new IndexOutOfBoundsException("Cannot add outside list bounds");
            }
            iter.next();
        }
        iter.add(element);
    }

    // NAIVE
    @Override
    public boolean addAll(Collection<? extends T> c) {
        java.util.ListIterator<T> iter = listIterator();
        while (iter.hasNext()) {
            iter.next();
        }
        boolean changed = false;
        for (T item : c) {
            iter.add(item);
            changed = true;
        }
        return changed;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Cannot set outside list bounds");
        }
        if (c == null) {
            throw new NullPointerException("Must provide collection to add");
        } else if (c.isEmpty()) {
            return false;
        }

        java.util.ListIterator<T> iter = listIterator();
        while (iter.nextIndex() < index) {
            if (!iter.hasNext()) {
                throw new IndexOutOfBoundsException("Cannot set outside list bounds");
            }
            iter.next();
        }

        for (T item : c) {
            iter.add(item);
        }
        return true;
    }

    @Override
    public T set(int index, T element) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Cannot set outside list bounds");
        }
        int i = 0;
        Node current = head;
        while (current != null && i < index) {
            current = current.getNext();
            i++;
        }
        if (current == null) {
            throw new IndexOutOfBoundsException("Cannot set outside list bounds");
        }
        T oldValue = current.getValue();
        current.setValue(element);
        return oldValue;
    }

    // DELETION

    @Override
    public boolean remove(Object item) {
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
        if (index < 0 || head == null) {
            throw new IndexOutOfBoundsException("Cannot remove outside list bounds");
        }

        java.util.ListIterator<T> iter = listIterator();
        T value = null;
        while (iter.previousIndex() < index) {
            if (!iter.hasNext()) {
                throw new IndexOutOfBoundsException("Cannot remove outside list bounds");
            }
            value = iter.next();
        }
        iter.remove();
        return value;
    }

    // NAIVE
    @Override
    public boolean removeAll(Collection<?> c) {
        boolean changed = false;
        for (Object item : c) {
            changed |= remove(item);
        }
        return changed;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        java.util.ListIterator<T> iter = listIterator();
        boolean changed = false;
        while (iter.hasNext()) {
            if (!c.contains(iter.next())) {
                iter.remove();
                changed = true;
            }
        }
        return changed;
    }

    @Override
    public void clear() {
        head = null;
    }

    // SEARCH

    @Override
    public boolean contains(Object item) {
        return indexOf(item) != -1;
    }

    // NAIVE
    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object item : c) {
            if (!contains(item)) {
                return false;
            }
        }
        return true;
    }

    public T get(int index) {
        if (index < 0 || head == null) {
            throw new IndexOutOfBoundsException("Cannot get outside list bounds");
        }
        try {
            return listIterator(index).next();
        } catch (Exception e) {
            throw new IndexOutOfBoundsException("Cannot get outside list bounds");
        }
    }

    @Override
    public int indexOf(Object item) {
        java.util.ListIterator<T> iter = listIterator();
        while (iter.hasNext()) {
            T value = iter.next();
            if (item == null && value == null || item != null && item.equals(value)) {
                return iter.previousIndex();
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        java.util.ListIterator<T> iter = listIterator();
        int index = -1;
        while (iter.hasNext()) {
            T value = iter.next();
            if (o == null && value == null || o != null && o.equals(value)) {
                index = iter.previousIndex();
            }
        }
        return index;
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

    // ITERATORS

    @Override
    public java.util.ListIterator<T> listIterator() {
        return new ListIterator();
    }

    @Override
    public java.util.ListIterator<T> listIterator(int index) {
        return new ListIterator(index);
    }

    @Override
    public java.util.List<T> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || fromIndex > toIndex) {
            throw new IndexOutOfBoundsException();
        }
        java.util.ListIterator<T> iter = listIterator(fromIndex);
        LinkedList<T> sublist = new LinkedList<T>();
        while (iter.nextIndex() < toIndex) {
            if (!iter.hasNext()) {
                throw new IndexOutOfBoundsException();
            }
            sublist.add(iter.next());
        }
        return sublist;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public int size() {
        java.util.ListIterator<T> iter = listIterator();
        while(iter.hasNext()) {
            iter.next();
        }
        return iter.nextIndex();
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator();
    }

    @Override
    public Object[] toArray() {
        return toArray(new Object[0]);
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        int size = size();
        if (a.length < size) {
            a = (T1[]) Array.newInstance(a.getClass().getComponentType(), size);
        }

        int i = 0;
        for (T item : this) {
            a[i++] = (T1) item;
        }
        while (i < a.length) {
            a[i++] = null;
        }
        return a;
    }

    // OBJECT METHODS

    @Override
    public boolean equals(Object other) {
        // Element-wise comparison with other list.
        if (other instanceof java.util.List) {
            java.util.ListIterator iter = listIterator();
            java.util.ListIterator otherIter = ((java.util.List) other).listIterator();
            while (iter.hasNext() || otherIter.hasNext()) {
                if (iter.hasNext() != otherIter.hasNext()) {
                    return false;
                }
                Object value = iter.next();
                Object otherValue = otherIter.next();
                if (value == null && otherValue != null
                        || value != null && !value.equals(otherValue)) {
                    return false;
                }
            }
            return true;
        }
        return super.equals(other);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Iterator<T> iterator = iterator();
        if (iterator.hasNext()) {
            sb.append(iterator.next());
        }
        while (iterator.hasNext()) {
            sb.append(", ").append(iterator.next());
        }
        return sb.append("]").toString();
    }

    /**
     * Iterates over the items in the list with additional list features.
     */
    private class ListIterator implements java.util.ListIterator<T> {
        private int previousIndex = -1;
        private int nextIndex = 0;

        private Node previous, current = null;
        private Node next = head;

        public ListIterator() {
        }

        public ListIterator(int index) {
            if (index < 0) {
                throw new IndexOutOfBoundsException("Cannot create iterator outside list bounds");
            }
            while (nextIndex < index) {
                if (!hasNext()) {
                    throw new IndexOutOfBoundsException("Cannot create iterator outside list bounds");
                }
                next();
            }
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public T next() {
            if (next == null) {
                throw new NoSuchElementException("No more elements in list");
            }
            T value = next.getValue();

            // In case current was removed.
            if (current != null) {
                previous = current;
            }
            current = next;
            next = next.getNext();
            nextIndex++;
            previousIndex++;
            return value;
        }

        @Override
        public boolean hasPrevious() {
            return current != null || previous != null;
        }

        @Override
        public T previous() {
            if (previous == null) {
                throw new NoSuchElementException("No previous element");
            }
            return previous.getValue();
        }

        @Override
        public int nextIndex() {
            return nextIndex;
        }

        @Override
        public int previousIndex() {
            return previousIndex;
        }

        @Override
        public void remove() {
            if (current == null) {
                throw new IllegalStateException("Cannot remove before next or twice in a row");
            }
            if (current == head) {
                head = next;
            }
            current = null;
            if (previous != null) {
                previous.setNext(next);
            }
            nextIndex--;
        }

        @Override
        public void set(T t) {
            if (current == null) {
                throw new IllegalStateException("Cannot set after remove");
            }
            current.setValue(t);
        }

        @Override
        public void add(T t) {
            Node newNode = new Node(t);
            if (current != null) {
                current.setNext(newNode);
            }
            if (next != null) {
                newNode.setNext(next);
            }
            if (next == head) {
                head = newNode;
            }
            previous = current;
            current = newNode;
            previousIndex++;
            nextIndex++;
        }
    }

}
