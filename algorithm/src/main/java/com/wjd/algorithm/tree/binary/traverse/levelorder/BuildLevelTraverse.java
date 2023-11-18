package com.wjd.algorithm.tree.binary.traverse.levelorder;

import com.wjd.algorithm.tree.Traverse;
import com.wjd.structure.tree.binary.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 构建层次遍历
 * <p>
 * 根据构建层次列表，可以构建出一棵唯一二叉树
 *
 * @author weijiaduo
 * @since 2022/8/28
 */
public class BuildLevelTraverse implements Traverse<TreeNode> {

    /**
     * 遍历结果集合
     */
    List<TreeNode> list;

    @Override
    public List<TreeNode> traverse(TreeNode root) {
        list = new ArrayList<>();
        bfs(root);
        return list;
    }

    /**
     * 广度优先遍历
     *
     * @param root 根节点
     */
    private void bfs(TreeNode root) {
        if (root == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int notNull = queue.size();
        while (notNull > 0) {
            TreeNode node = queue.poll();
            notNull--;
            list.add(node);
            if (node == null) {
                continue;
            }

            queue.offer(node.left);
            if (node.left != null) {
                notNull = queue.size();
            }

            queue.offer(node.right);
            if (node.right != null) {
                notNull = queue.size();
            }
        }
    }

}
