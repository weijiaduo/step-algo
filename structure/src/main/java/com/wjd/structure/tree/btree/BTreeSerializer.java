package com.wjd.structure.tree.btree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * B-树序列化器
 *
 * @author weijiaduo
 * @since 2023/6/29
 */
public class BTreeSerializer<K extends Comparable<K>, V> {

    /**
     * 序列化 B-树
     *
     * @param root 根节点
     * @return 序列化值
     */
    public List<List<K>> serialize(BTNode<K, V> root) {
        List<List<K>> values = new ArrayList<>();
        Queue<BTNode<K, V>> queue = new LinkedList<>();
        queue.offer(root);
        int notNull = queue.size();
        while (notNull > 0) {
            BTNode<K, V> node = queue.poll();
            notNull--;
            if (node == null) {
                values.add(new ArrayList<>(0));
                continue;
            }

            // 当前节点的元素 key 集合
            values.add(new ArrayList<>(node.keys()));

            // 子节点
            for (BTNode<K, V> child : node.children()) {
                queue.offer(child);
                if (child != null) {
                    notNull = queue.size();
                }
            }
        }
        return values;
    }

    /**
     * 反序列化 B-树
     *
     * @param values 序列化值
     * @return 根节点
     */
    public BTNode<K, K> deserialize(List<List<K>> values) {
        if (values == null || values.isEmpty()) {
            return null;
        }

        // 由于不知道 m 的原始值，暂时只能通过元素数量推测
        int m = 0;
        for (List<K> list : values) {
            if (list == null) {
                continue;
            }
            m = Math.max(list.size() + 1, m);
        }

        int i = 0;
        BTNode<K, K> root = new BTNode<>(m);
        List<K> first = values.get(i++);
        for (K value : first) {
            root.add(value, value);
        }
        Queue<BTNode<K, K>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            BTNode<K, K> node = queue.poll();
            if (node == null) {
                continue;
            }

            // 子节点个数 = 节点元素格式 + 1
            int childSize = node.size() + 1;
            for (int j = 0; j < childSize && i < values.size(); j++) {
                List<K> childValues = values.get(i++);
                if (childValues == null || childValues.isEmpty()) {
                    node.setChild(j, null);
                } else {
                    BTNode<K, K> child = new BTNode<>(m);
                    for (K value : childValues) {
                        child.add(value, value);
                    }
                    node.setChild(j, child);
                    queue.offer(child);
                }
            }
        }
        return root;
    }

}
