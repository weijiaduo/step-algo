package com.wjd.algorithm.tree.btree.build;

import com.wjd.structure.tree.btree.BTNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * B-树层级构建器
 *
 * @author weijiaduo
 * @since 2023/1/2
 */
public class LevelBTreeBuilder implements BTreeBuilder<Integer, Integer, List<List<Integer>>> {

    @Override
    public BTNode<Integer, Integer> build(List<List<Integer>> values) {
        if (values == null || values.isEmpty()) {
            return null;
        }

        // 由于不知道 m 的原始值，暂时只能通过元素数量推测
        int m = 0;
        for (List<Integer> list : values) {
            if (list == null) {
                continue;
            }
            m = Math.max(list.size() + 1, m);
        }

        int i = 0;
        BTNode<Integer, Integer> root = new BTNode<>(m);
        List<Integer> first = values.get(i++);
        for (Integer value : first) {
            root.add(value, value);
        }
        Queue<BTNode<Integer, Integer>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            BTNode<Integer, Integer> node = queue.poll();
            if (node == null) {
                continue;
            }

            // 子节点个数 = 节点元素格式 + 1
            int childSize = node.size() + 1;
            for (int j = 0; j < childSize && i < values.size(); j++) {
                List<Integer> childValues = values.get(i++);
                if (childValues == null || childValues.isEmpty()) {
                    node.setChild(j, null);
                } else {
                    BTNode<Integer, Integer> child = new BTNode<>(m);
                    for (Integer value : childValues) {
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
