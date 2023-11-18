package com.wjd.algorithm.tree.generic.traverse;

import com.wjd.structure.tree.generic.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 树-层次遍历
 *
 * @author weijiaduo
 * @since 2022/12/10
 */
public class BuildLevelGenericTraverse implements GenericTraverse {

    /**
     * 遍历结果集合
     */
    List<Node> list;

    @Override
    public List<Node> traverse(Node node) {
        list = new ArrayList<>();
        bfs(node);
        return list;
    }

    /**
     * 广度优先遍历
     *
     * @param root 根节点
     */
    private void bfs(Node root) {
        if (root == null) {
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        int notNull = queue.size();
        queue.offer(null);
        while (notNull > 0) {
            Node node = queue.poll();
            notNull--;
            list.add(node);
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
    }

}
