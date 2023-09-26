package com.wjd.algorithm.tree.btree.traverse;

import com.wjd.structure.tree.btree.BTNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * B-树层序遍历
 *
 * @author weijiaduo
 * @since 2023/1/2
 */
public class LevelBTreeTraverse<K extends Comparable<K>, V> implements BTreeTraverse<K, V> {

    /**
     * 层次遍历
     *
     * @param root B-树根节点
     * @return 层次遍历列表
     */
    public List<List<K>> traverse(BTNode<K, V> root) {
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

}
