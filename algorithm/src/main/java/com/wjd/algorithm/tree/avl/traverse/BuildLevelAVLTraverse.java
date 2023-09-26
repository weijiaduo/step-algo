package com.wjd.algorithm.tree.avl.traverse;

import com.wjd.algorithm.tree.ListVisitor;
import com.wjd.structure.tree.avl.AVLTNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 构建层次遍历
 *
 * @author weijiaduo
 * @since 2023/2/22
 */
public class BuildLevelAVLTraverse implements AVLTraverse {

    /**
     * 列表访问者
     */
    private ListVisitor<AVLTNode> visitor;

    @Override
    public List<AVLTNode> traverse(AVLTNode node) {
        visitor = new ListVisitor<>();
        bfs(node);
        List<AVLTNode> list = visitor.getList();
        visitor = null;
        return list;
    }

    /**
     * 广度优先遍历
     *
     * @param root 根节点
     */
    private void bfs(AVLTNode root) {
        if (root == null) {
            return;
        }

        Queue<AVLTNode> queue = new LinkedList<>();
        queue.offer(root);
        int notNull = queue.size();
        while (notNull > 0) {
            AVLTNode node = queue.poll();
            notNull--;
            visitor.visit(node);
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
    }

}
