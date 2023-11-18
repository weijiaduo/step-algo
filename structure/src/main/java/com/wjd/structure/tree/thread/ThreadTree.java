package com.wjd.structure.tree.thread;

import com.wjd.structure.tree.binary.TreeNode;

import java.util.List;

/**
 * 线索二叉树
 *
 * @author weijiaduo
 * @since 2023/11/18
 */
public interface ThreadTree {

    /**
     * 线索化二叉树
     *
     * @param root 二叉树根节点
     */
    void inThread(TreeNode root);

    /**
     * 返回遍历结果
     *
     * @return 遍历结果集合
     */
    List<Integer> iterator();

}
