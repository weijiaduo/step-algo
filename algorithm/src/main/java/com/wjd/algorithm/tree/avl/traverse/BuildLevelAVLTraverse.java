package com.wjd.algorithm.tree.avl.traverse;

import com.wjd.structure.tree.avl.AVLTNode;

import java.util.ArrayList;
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
     * 遍历结果集合
     */
    List<AVLTNode> list;

    @Override
    public List<AVLTNode> traverse(AVLTNode node) {
        list = new ArrayList<>();
        bfs(node);
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
