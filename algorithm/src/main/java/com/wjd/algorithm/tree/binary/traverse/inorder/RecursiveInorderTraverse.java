package com.wjd.algorithm.tree.binary.traverse.inorder;

import com.wjd.algorithm.tree.Traverse;
import com.wjd.structure.tree.binary.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 中序遍历-递归法
 *
 * @author weijiaduo
 * @since 2022/8/28
 */
public class RecursiveInorderTraverse implements Traverse<TreeNode> {

    /**
     * 遍历结果集合
     */
    List<TreeNode> list;

    @Override
    public List<TreeNode> traverse(TreeNode root) {
        list = new ArrayList<>();
        recursive(root);
        return list;
    }

    /**
     * 递归实现
     *
     * @param root 根节点
     */
    private void recursive(TreeNode root) {
        if (root == null) {
            return;
        }

        recursive(root.left);
        list.add(root);
        recursive(root.right);
    }

}
