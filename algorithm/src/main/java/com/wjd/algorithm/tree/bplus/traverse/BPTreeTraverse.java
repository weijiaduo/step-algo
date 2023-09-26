package com.wjd.algorithm.tree.bplus.traverse;

import com.wjd.structure.tree.bplus.BPTNode;

import java.util.List;

/**
 * B+树遍历
 *
 * @author weijiaduo
 * @since 2023/1/20
 */
public interface BPTreeTraverse<K extends Comparable<K>, V> {

    List<List<K>> traverse(BPTNode<K, V> node);

}
