package com.wjd.algorithm.tree.binary.traverse;

import com.wjd.algorithm.tree.ListVisitor;
import com.wjd.algorithm.tree.Traverse;
import com.wjd.structure.tree.binary.TreeNode;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

/**
 * 层序遍历（不包括层级中间的 null 节点）
 *
 * @author weijiaduo
 * @since 2022/8/28
 */
public class SimpleLevelTraverse implements Traverse<TreeNode> {

    /**
     * 列表访问者
     */
    private ListVisitor<TreeNode> visitor;

    @Override
    public List<TreeNode> traverse(TreeNode root) {
        visitor = new ListVisitor<>();
        bfs(root);
        List<TreeNode> list = visitor.getList();
        visitor = null;
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
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node == null) {
                    continue;
                }
                visitor.visit(node);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
    }

}
