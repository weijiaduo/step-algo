package com.wjd.algorithm.tree.btree.traverse;

import com.wjd.structure.tree.btree.BTNode;

import java.util.List;

/**
 * B-树遍历
 *
 * @author weijiaduo
 * @since 2023/1/18
 */
public interface BTreeTraverse<K extends Comparable<K>, V> {

    List<List<K>> traverse(BTNode<K, V> node);

}
