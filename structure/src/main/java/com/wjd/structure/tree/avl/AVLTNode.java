package com.wjd.structure.tree.avl;

/**
 * AVL 树节点
 *
 * @author weijiaduo
 * @since 2023/2/22
 */
public class AVLTNode {

    /**
     * 节点值
     */
    public int val;
    /**
     * 左子节点
     */
    public AVLTNode left;
    /**
     * 右子节点
     */
    public AVLTNode right;
    /**
     * 节点高度，只有一个节点时高度为 1
     */
    public int height;

    public AVLTNode(int val) {
        this.val = val;
        this.height = 1;
    }

    @Override
    public String toString() {
        return String.valueOf(val);
    }
}
