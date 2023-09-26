package com.wjd.structure.tree.binary;

import java.util.Arrays;
import java.util.List;

/**
 * 二叉树节点
 */
public class TreeNode {

    /**
     * 节点值
     */
    public int val;
    /**
     * 左子节点
     */
    public TreeNode left;
    /**
     * 右子节点
     */
    public TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }

    /**
     * 根据层序遍历序列（空节点为null）生成树
     */
    public static TreeNode build(Integer[] values) {
        return new BinaryTreeSerializer().deserialize(values);
    }

    /**
     * 根据层序遍历序列（空节点为null）生成树
     */
    public static TreeNode build(List<Integer> list) {
        return build(list.toArray(new Integer[0]));
    }

    /**
     * 构建形态的层序遍历
     */
    public static List<Integer> traverse(TreeNode tree) {
        Integer[] values = new BinaryTreeSerializer().serialize(tree);
        return values != null ? Arrays.asList(values) : null;
    }

    @Override
    public String toString() {
        return String.valueOf(val);
    }

}
