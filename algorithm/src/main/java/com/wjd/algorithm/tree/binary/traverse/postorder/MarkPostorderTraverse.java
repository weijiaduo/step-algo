package com.wjd.algorithm.tree.binary.traverse.postorder;

import com.wjd.algorithm.tree.Traverse;
import com.wjd.structure.tree.binary.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 后序遍历-标记法
 *
 * @author weijiaduo
 * @since 2022/8/28
 */
public class MarkPostorderTraverse implements Traverse<TreeNode> {

    /**
     * 遍历结果集合
     */
    List<TreeNode> list;

    @Override
    public List<TreeNode> traverse(TreeNode root) {
        list = new ArrayList<>();
        mark(root);
        return list;
    }

    /**
     * 标记实现，标记访问过的节点
     *
     * @param root 根节点
     */
    private void mark(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<>();
        Deque<Boolean> marks = new LinkedList<>();
        stack.push(root);
        marks.push(false);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            Boolean mark = marks.pop();
            if (node == null || mark == null) {
                continue;
            }
            if (mark) {
                list.add(node);
            } else {
                // 倒序添加
                // 根节点
                stack.push(node);
                marks.push(true);
                // 右子节点
                stack.push(node.right);
                marks.push(false);
                // 左子节点
                stack.push(node.left);
                marks.push(false);
            }
        }
    }

}
