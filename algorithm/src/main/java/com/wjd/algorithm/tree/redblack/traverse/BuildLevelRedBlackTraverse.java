package com.wjd.algorithm.tree.redblack.traverse;

import com.wjd.structure.tree.redblack.RBTNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 构建层次遍历
 *
 * @author weijiaduo
 * @since 2023/1/25
 */
public class BuildLevelRedBlackTraverse implements RedBlackTraverse {

    /**
     * 列表访问者
     */
    private List<RBTNode> list;

    @Override
    public List<RBTNode> traverse(RBTNode node) {
        list = new ArrayList<>();
        bfs(node);
        return list;
    }

    /**
     * 广度优先遍历
     *
     * @param root 根节点
     */
    private void bfs(RBTNode root) {
        if (root == null) {
            return;
        }

        Queue<RBTNode> queue = new LinkedList<>();
        queue.offer(root);
        int notNull = queue.size();
        while (notNull > 0) {
            RBTNode node = queue.poll();
            notNull--;
            list.add(node);
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
