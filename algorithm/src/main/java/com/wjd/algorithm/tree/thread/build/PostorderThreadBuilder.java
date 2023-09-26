package com.wjd.algorithm.tree.thread.build;

import com.wjd.structure.tree.binary.TreeNode;
import com.wjd.structure.tree.thread.ThreadNode;

/**
 * 后序线索二叉树构建
 *
 * @author weijiaduo
 * @since 2022/12/4
 */
public class PostorderThreadBuilder implements ThreadBuilder {

    private ThreadNode prev;

    @Override
    public ThreadNode build(TreeNode root) {
        ThreadNode newRoot = new ThreadNode(root);
        prev = null;
        dfs(newRoot);
        if (prev.right == null) {
            prev.rTag = true;
        }
        prev = null;
        return newRoot;
    }

    private void dfs(ThreadNode root) {
        if (root == null) {
            return;
        }

        if (!root.lTag) {
            if (root.left != null) {
                root.left.parent = root;
            }
            dfs(root.left);
        }
        if (!root.rTag) {
            if (root.right != null) {
                root.right.parent = root;
            }
            dfs(root.right);
        }

        // 后驱节点
        if (prev != null && prev.right == null) {
            prev.right = root;
            prev.rTag = true;
        }
        // 前驱节点
        if (root.left == null) {
            root.left = prev;
            root.lTag = true;
        }
        prev = root;
    }

}
