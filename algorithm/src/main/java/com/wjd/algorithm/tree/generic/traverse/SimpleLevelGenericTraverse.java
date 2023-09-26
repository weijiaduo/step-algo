package com.wjd.algorithm.tree.generic.traverse;

import com.wjd.algorithm.tree.ListVisitor;
import com.wjd.structure.tree.generic.Node;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

/**
 * 按层次返回每层的数据
 *
 * @author weijiaduo
 * @since 2022/12/10
 */
public class SimpleLevelGenericTraverse implements GenericTraverse {

    /**
     * 列表访问者
     */
    private ListVisitor<Node> visitor;

    @Override
    public List<Node> traverse(Node node) {
        visitor = new ListVisitor<>();
        bfs(node);
        List<Node> list = visitor.getList();
        visitor = null;
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

        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                if (node == null) {
                    continue;
                }
                visitor.visit(node);
                List<Node> children = node.children;
                if (children != null) {
                    for (Node child : children) {
                        queue.offer(child);
                    }
                }
            }
        }
    }

}
