package com.wjd.structure.tree.thread;

import com.wjd.structure.tree.binary.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 中序线索二叉树
 *
 * @author weijiaduo
 * @since 2023/11/18
 */
public class InorderThreadTree implements ThreadTree {

    /**
     * 根节点
     */
    private ThreadNode root;

    /**
     * 哨兵节点（遍历树时用到）
     */
    private ThreadNode head;

    /**
     * 前一个节点（构建树时用到）
     */
    private ThreadNode prev;

    public InorderThreadTree(TreeNode root) {
        inThread(root);
    }

    @Override
    public void inThread(TreeNode root) {
        this.root = trans(root);
        head = new ThreadNode(-1);
        prev = head;
        build(this.root);
        if (prev.right == null) {
            prev.rTag = true;
        }
        prev = null;
    }

    /**
     * 二叉树节点转成线索二叉树节点
     *
     * @param root 二叉树根节点
     * @return 线索树根节点
     */
    private ThreadNode trans(TreeNode root) {
        if (root == null) {
            return null;
        }
        ThreadNode t = new ThreadNode(root.val);
        t.left = trans(root.left);
        t.right = trans(root.right);
        return t;
    }

    /**
     * 递归遍历构建线索树
     *
     * @param root 根节点
     */
    private void build(ThreadNode root) {
        if (root == null) {
            return;
        }

        if (!root.lTag) {
            build(root.left);
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

        if (!root.rTag) {
            build(root.right);
        }
    }

    @Override
    public List<Integer> iterator() {
        List<Integer> list = new ArrayList<>();
        ThreadNode next = head.right;
        while (next != null) {
            list.add(next.val);
            if (next.rTag) {
                // 有后驱节点
                next = next.right;
                continue;
            }

            // 无后驱节点，右子树
            // 找到第一个非前驱的左子节点
            next = next.right;
            while (next != null && !next.lTag) {
                next = next.left;
            }
        }
        return list;
    }

}
