package com.wjd.algorithm.tree.binary.util;

import com.wjd.structure.tree.binary.TreeNode;

/**
 * 二叉树工具类
 *
 * @author weijiaduo
 * @since 2022/12/11
 */
public final class BinaryTreeUtils {

    private BinaryTreeUtils() {
    }

    /**
     * 是否是相等的树
     *
     * @param root1 根节点
     * @param root2 根节点
     * @return true相等/false不相等
     */
    public static boolean isSameTree(TreeNode root1, TreeNode root2) {
        if (root1 == null || root2 == null) {
            return root1 == root2;
        }
        if (root1.val != root2.val) {
            return false;
        }
        return isSameTree(root1.left, root2.left) && isSameTree(root1.right, root2.right);
    }

    /**
     * 是否是对称树
     *
     * @param root1 根节点
     * @param root2 根节点
     * @return true对称/false不对称
     */
    public boolean isSymmetric(TreeNode root1, TreeNode root2) {
        if (root1 == null || root2 == null) {
            return root1 == root2;
        }
        if (root1.val != root2.val) {
            return false;
        }
        return isSymmetric(root1.left, root2.right) && isSymmetric(root1.right, root2.left);
    }

    /**
     * 最大深度（根节点到最远叶子节点）
     *
     * @param root 根节点
     * @return 最大深度
     */
    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    /**
     * 最小深度（根节点到最近叶子节点）
     *
     * @param root 根节点
     * @return 最小深度
     */
    public static int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // 只有一个 null 子节点的，不是叶子节点
        if (root.left == null) {
            return minDepth(root.right) + 1;
        }
        if (root.right == null) {
            return minDepth(root.left) + 1;
        }
        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }

}
