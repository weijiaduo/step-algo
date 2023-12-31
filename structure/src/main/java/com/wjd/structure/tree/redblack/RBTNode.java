package com.wjd.structure.tree.redblack;

/**
 * 红黑树节点
 *
 * @author weijiaduo
 * @since 2023/1/24
 */
public class RBTNode {

    /**
     * 红色
     */
    public static final boolean RED = true;
    /**
     * 黑色
     */
    public static final boolean BLACK = false;

    /**
     * 父节点
     */
    public RBTNode parent;
    /**
     * 左子节点
     */
    public RBTNode left;
    /**
     * 右子节点
     */
    public RBTNode right;
    /**
     * 节点值
     */
    public int val;
    /**
     * 节点颜色
     */
    public boolean color = RED;

    public RBTNode(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        String type = color ? "r" : "b";
        return type + val;
    }

}
