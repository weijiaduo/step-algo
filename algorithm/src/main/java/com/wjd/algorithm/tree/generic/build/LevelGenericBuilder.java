package com.wjd.algorithm.tree.generic.build;

import com.wjd.structure.tree.generic.Node;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

/**
 * 基于层次遍历的树构建器
 *
 * @author weijiaduo
 * @since 2022/12/10
 */
public class LevelGenericBuilder implements GenericBuilder<Integer[]> {

    @Override
    public Node build(Integer[] values) {
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
