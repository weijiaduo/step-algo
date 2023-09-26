package com.wjd.structure.tree.bplus;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * B+ 树序列化器
 *
 * @author weijiaduo
 * @since 2023/6/29
 */
public class BPTreeSerializer<K extends Comparable<K>, V> {

    /**
     * 序列化 B+ 树
     *
     * @param root 树根节点
     * @return 序列化值
     */
    public List<List<K>> serialize(BPTNode<K, V> root) {
        List<List<K>> values = new ArrayList<>();
        Queue<BPTNode<K, V>> queue = new LinkedList<>();
        queue.offer(root);
        int notNull = queue.size();
        while (notNull > 0) {
            BPTNode<K, V> node = queue.poll();
            notNull--;
            if (node == null) {
                values.add(new ArrayList<>(0));
                continue;
            }

            // 当前节点的元素 key 集合
            values.add(new ArrayList<>(node.keys()));

            // 子节点
            for (BPTNode<K, V> child : node.children()) {
                queue.offer(child);
                if (child != null) {
                    notNull = queue.size();
                }
            }
        }
        return values;
    }

}
