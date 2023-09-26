package com.wjd.structure.tree.avl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * AVL 平衡二叉树序列化器
 *
 * @author weijiaduo
 * @since 2023/6/29
 */
public class AVLTreeSerializer {

    /**
     * 序列化 AVL 平衡二叉树
     *
     * @param root 根节点
     * @return 序列化值
     */
    public Integer[] serialize(AVLTNode root) {
        if (root == null) {
            return new Integer[0];
        }

        List<Integer> values = new ArrayList<>();
        Queue<AVLTNode> queue = new LinkedList<>();
        queue.offer(root);
        int notNull = queue.size();
        while (notNull > 0) {
            AVLTNode node = queue.poll();
            notNull--;
            values.add(node != null ? node.val : null);
            if (node == null) {
                continue;
            }

            // 左子节点
            queue.offer(node.left);
            if (node.left != null) {
                notNull = queue.size();
            }
            // 右子节点
            queue.offer(node.right);
            if (node.right != null) {
                notNull = queue.size();
            }
        }
        return values.toArray(new Integer[0]);
    }

}
