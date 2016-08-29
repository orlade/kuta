package net.piemaster.kuta.data.collections.trees;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;

public class SimpleBinaryTree<T> implements Tree<T, T> {

    /**
     * The number of nodes in the tree.
     */
    private int size = 0;

    /**
     * The root node of the tree. Null if the tree is empty.
     */
    private BinaryTreeNode root;

    // CONSTRUCTORS

    public SimpleBinaryTree() {
    }

    public SimpleBinaryTree(Collection<? extends T> elements) {
        if (elements != null) {
            addAll(elements);
        }
    }

    // PROPERTIES

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    // SEARCH

    @Override
    public boolean contains(Object o) {
        return false;
    }

    // INSERTION

    @Override
    public boolean add(T t) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    // DELETION

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    // TRANSFORMATION

    @Override
    public Object[] toArray() {
        return toArray(new Object[0]);
    }

    @Override
    public <T1> T1[] toArray(T1[] array) {
        if (array.length < size) {
            array = (T1[]) Array.newInstance(array.getClass().getComponentType(), size);
        }

        int i = 0;
        for (T item : this) {
            array[i++] = (T1) item;
        }
        while (i < array.length) {
            array[i++] = null;
        }
        return array;
    }

    // ITERATION

    @Override
    public Iterator<T> iterator() {
        return null;
    }

}
