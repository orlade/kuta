package net.piemaster.jad.data.collections;

public interface Collection<T> {

    void add(T item);

    boolean remove(T item);

    boolean contains(T item);

    int size();

}
