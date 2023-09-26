package com.wjd.algorithm.tree.btree.build;

import com.wjd.algorithm.tree.TreeBuilder;
import com.wjd.structure.tree.btree.BTNode;

/**
 * B-树构建器
 *
 * @author weijiaduo
 * @since 2023/1/2
 */
public interface BTreeBuilder<K extends Comparable<K>, V, T> extends TreeBuilder<BTNode<K, V>, T> {
}
