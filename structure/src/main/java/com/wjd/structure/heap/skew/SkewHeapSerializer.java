package com.wjd.structure.heap.skew;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 斜堆序列化器
 *
 * @author weijiaduo
 * @since 2023/9/27
 */
public class SkewHeapSerializer {

    public <T extends Comparable<T>> String[] serialize(SkewHeapNode<T> root) {
        if (root == null) {
            return new String[0];
        }

        List<String> values = new ArrayList<>();
        Queue<SkewHeapNode<T>> queue = new LinkedList<>();
        queue.offer(root);
        int notNull = queue.size();
        while (notNull > 0) {
            SkewHeapNode<T> node = queue.poll();
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
