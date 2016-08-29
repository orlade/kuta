package net.piemaster.kuta.data.collections.trees;

import java.util.Collection;

/**
 * A tree data structure. The contents of a tree are recursively defined as the contents of all the
 * nodes in a tree, so the {@code Tree} class is simply the root node of a tree for semantic
 * convenience.
 *
 * @param <K> The type of keys in the tree nodes.
 * @param <V> The type of values in the tree nodes.
 */
public interface Tree<K, V> extends Collection<V> {


}
