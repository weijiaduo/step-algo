package com.wjd.structure.tree.thread;

import com.wjd.structure.tree.binary.TreeNode;

/**
 * 线索二叉树节点
 *
 * @author weijiaduo
 * @since 2022/12/2
 */
public class ThreadNode {

    /**
     * 节点值
     */
    public int val;
    /**
     * 左子节点
     */
    public ThreadNode left;
    /**
     * 右子节点
     */
    public ThreadNode right;
    /**
     * 父节点（后序线索二叉树用）
     */
    public ThreadNode parent;
    /**
     * 线索节点true/子节点false
     */
    public boolean lTag;
    /**
     * 线索节点true/子节点false
     */
    public boolean rTag;

    public ThreadNode(TreeNode node) {
        this.val = node.val;
        this.left = node.left != null ? new ThreadNode(node.left) : null;
        this.right = node.right != null ? new ThreadNode(node.right) : null;
    }

    @Override
    public String toString() {
        return "" + val;
    }

}
