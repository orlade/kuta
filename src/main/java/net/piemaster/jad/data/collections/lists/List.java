package net.piemaster.jad.data.collections.lists;

import net.piemaster.jad.data.collections.Collection;

public interface List<T> extends Collection<T> {

    int indexOf(T item);

    T get(int index);

    void insert(T item, int index);

    T remove(int index);

}
