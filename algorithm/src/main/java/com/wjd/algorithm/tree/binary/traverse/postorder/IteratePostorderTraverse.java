package com.wjd.algorithm.tree.binary.traverse.postorder;

import com.wjd.algorithm.tree.Traverse;
import com.wjd.structure.tree.binary.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 后序遍历-迭代法
 *
 * @author weijiaduo
 * @since 2022/8/28
 */
public class IteratePostorderTraverse implements Traverse<TreeNode> {

    /**
     * 遍历结果集合
     */
    List<TreeNode> list;

    @Override
    public List<TreeNode> traverse(TreeNode root) {
        list = new ArrayList<>();
        iterate(root);
        return list;
    }

    /**
     * 迭代实现，递归改成迭代
     *
     * @param root 根节点
     */
    private void iterate(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root, prev = null;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                // 保存现场 nextPC
                stack.push(cur);
                // 左子节点
                cur = cur.left;
            } else {
                cur = stack.pop();
                if (cur.right != null && cur.right != prev) {
                    // 保存现场 nextPC
                    stack.push(cur);
                    // 右子节点
                    cur = cur.right;
                } else {
                    // 根节点
                    list.add(cur);
                    prev = cur;
                    cur = null;
                }
            }
        }
    }

}
