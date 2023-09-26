package com.wjd.algorithm.tree.thread.traverse;

import com.wjd.structure.tree.thread.ThreadNode;

/**
 * 线索二叉树的遍历
 *
 * @author weijiaduo
 * @since 2022/12/3
 */
public interface ThreadTraverse {

    /**
     * @return 第一个节点
     */
    ThreadNode first();

    /**
     * @return 下一个节点
     */
    ThreadNode next();

}
