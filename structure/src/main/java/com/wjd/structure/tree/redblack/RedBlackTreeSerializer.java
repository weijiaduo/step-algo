package com.wjd.structure.tree.redblack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 红黑树序列化器
 *
 * @author weijiaduo
 * @since 2023/6/29
 */
public class RedBlackTreeSerializer {

    /**
     * 序列化红黑树
     *
     * @param root 根节点
     * @return 序列化值
     */
    public String[] serialize(RBTNode root) {
        if (root == null) {
            return new String[0];
        }

        List<String> values = new ArrayList<>();
        Queue<RBTNode> queue = new LinkedList<>();
        queue.offer(root);
        int notNull = queue.size();
        while (notNull > 0) {
            RBTNode node = queue.poll();
            notNull--;
            values.add(node != null ? node.toString() : null);
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
        return values.toArray(new String[0]);
    }

}
