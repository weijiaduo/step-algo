package com.wjd.algorithm.tree.binary.build;

import com.wjd.structure.tree.binary.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 基于层序的二叉树构建器
 *
 * @author weijiaduo
 * @since 2022/8/28
 */
public class LevelTreeBuilder implements BinaryTreeBuilder<Integer[]> {

    @Override
    public TreeNode build(Integer[] values) {
        if (values == null || values.length == 0) {
            return null;
        }

        int n = values.length, i = 0;
        TreeNode root = new TreeNode(values[i++]);
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode parent = queue.poll();
            if (i < n) {
                Integer leftVal = values[i++];
                if (leftVal != null) {
                    parent.left = new TreeNode(leftVal);
                    queue.offer(parent.left);
                }
            }
            if (i < n) {
                Integer rightVal = values[i++];
                if (rightVal != null) {
                    parent.right = new TreeNode(rightVal);
                    queue.offer(parent.right);
                }
            }
        }
        return root;
    }

}
