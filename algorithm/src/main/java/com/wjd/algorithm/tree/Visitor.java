package com.wjd.algorithm.tree;

import com.wjd.structure.tree.binary.TreeNode;

/**
 * 访问者模式
 *
 * @author weijiaduo
 * @since 2022/11/26
 */
public interface Visitor<T> {

    void visit(T node);

}
