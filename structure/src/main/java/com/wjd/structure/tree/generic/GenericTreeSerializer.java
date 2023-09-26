package com.wjd.structure.tree.generic;

import java.util.*;

/**
 * 树序列化器
 *
 * @author weijiaduo
 * @since 2023/6/29
 */
public class GenericTreeSerializer {

    /**
     * 序列化树
     *
     * @param root 树根节点
     * @return 序列化值
     */
    public Integer[] serialize(Node root) {
        if (root == null) {
            return null;
        }

        List<Integer> values = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        int notNull = queue.size();
        queue.offer(null);
        while (notNull > 0) {
            Node node = queue.poll();
            notNull--;
            values.add(node != null ? node.val : null);
            if (node == null) {
                continue;
            }

            // 子节点列表
            List<Node> children = node.children;
            if (children != null && !children.isEmpty()) {
                for (Node child : children) {
                    queue.offer(child);
                }
                notNull = queue.size();
            }
            // 每组子节点以 null 结尾
            queue.offer(null);
        }
        return values.toArray(new Integer[0]);
    }

    /**
     * 反序列化树
     *
     * @param values 序列化值
     * @return 树
     */
    public Node deserialize(Integer[] values) {
        if (values == null || values.length == 0) {
            return null;
        }

        Node dummy = new Node(-1);
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(dummy);
        int n = values.length, i = 0;
        while (!queue.isEmpty()) {
            Node parent = queue.poll();
            parent.children = new ArrayList<>();
            while (i < n) {
                Integer val = values[i++];
                if (val == null) {
                    break;
                }
                Node child = new Node(val);
                parent.children.add(child);
                queue.offer(child);
            }
        }
        return dummy.children.get(0);
    }

}
