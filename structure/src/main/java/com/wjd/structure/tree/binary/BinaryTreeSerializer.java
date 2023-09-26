package com.wjd.structure.tree.binary;

import java.util.*;

/**
 * 二叉树序列化器
 *
 * @author weijiaduo
 * @since 2023/6/29
 */
public class BinaryTreeSerializer {

    /**
     * 序列化二叉树
     *
     * @param root 二叉树根节点
     * @return 序列化值
     */
    public Integer[] serialize(TreeNode root) {
        if (root == null) {
            return null;
        }

        List<Integer> values = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int notNull = queue.size();
        while (notNull > 0) {
            TreeNode node = queue.poll();
            notNull--;
            values.add(node != null ? node.val : null);
            if (node == null) {
                continue;
            }

            queue.offer(node.left);
            if (node.left != null) {
                notNull = queue.size();
            }

            queue.offer(node.right);
            if (node.right != null) {
                notNull = queue.size();
            }
        }
        return values.toArray(new Integer[0]);
    }

    /**
     * 反序列化二叉树
     *
     * @param values 序列化值
     * @return 二叉树
     */
    public TreeNode deserialize(Integer[] values) {
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
                Integer lv = values[i++];
                if (lv != null) {
                    parent.left = new TreeNode(lv);
                    queue.offer(parent.left);
                }
            }
            if (i < n) {
                Integer rv = values[i++];
                if (rv != null) {
                    parent.right = new TreeNode(rv);
                    queue.offer(parent.right);
                }
            }
        }
        return root;
    }

}
