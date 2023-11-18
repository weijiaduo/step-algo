package com.wjd.structure.tree.thread;

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

    public ThreadNode(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return String.valueOf(val);
    }

}
