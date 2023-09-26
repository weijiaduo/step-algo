package com.wjd.structure.tree.bst;

import com.wjd.structure.tree.binary.TreeNode;

/**
 * 二叉搜索树实现
 *
 * @author weijiaduo
 * @since 2022/12/14
 */
public class BSTreeImpl implements BSTree {

    /**
     * 根节点
     */
    private TreeNode root;

    public BSTreeImpl() {
    }

    public BSTreeImpl(int[] values) {
        for (int val : values) {
            insert(val);
        }
    }

    /**
     * 获取根节点
     *
     * @return 根节点
     */
    public TreeNode getRoot() {
        return root;
    }

    @Override
    public TreeNode query(int val) {
        TreeNode node = root;
        while (node != null) {
            if (node.val == val) {
                return node;
            } else if (val < node.val) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return null;
    }

    @Override
    public void insert(int val) {
        root = insert(root, val);
    }

    /**
     * 插入指定值
     *
     * @param root 当前节点
     * @param val  插入值
     * @return 当前节点
     */
    private TreeNode insert(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        if (val == root.val) {
            return root;
        }
        if (val < root.val) {
            root.left = insert(root.left, val);
        } else {
            root.right = insert(root.right, val);
        }
        return root;
    }

    @Override
    public void remove(int val) {
        root = remove(root, val);
    }

    /**
     * 移除指定节点
     *
     * @param root 当前节点
     * @param val  指定删除值
     * @return 当前节点
     */
    private TreeNode remove(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        if (root.val == val) {
            return deleteNode(root);
        }
        if (val < root.val) {
            root.left = remove(root.left, val);
        } else {
            root.right = remove(root.right, val);
        }
        return root;
    }

    /**
     * 删除指定节点
     *
     * @param node 指定节点
     * @return 新节点
     */
    private TreeNode deleteNode(TreeNode node) {
        if (node.left == null) {
            return node.right;
        } else if (node.right == null) {
            return node.left;
        } else {
            TreeNode t = node;
            // 后继节点替代当前节点
            node = min(t.right);
            node.right = deleteMin(t.right);
            // 左节点赋值要放 deleteMin 之后
            node.left = t.left;
        }
        return node;
    }

    /**
     * 删除最小节点
     *
     * @param root 根节点
     * @return 根节点
     */
    private TreeNode deleteMin(TreeNode root) {
        if (root == null) {
            return null;
        }
        if (root.left == null) {
            return root.right;
        }
        root.left = deleteMin(root.left);
        return root;
    }

    /**
     * 最小值节点
     *
     * @param root 根节点
     * @return 最小值节点
     */
    private TreeNode min(TreeNode root) {
        if (root == null) {
            return null;
        }
        if (root.left == null) {
            return root;
        }
        return min(root.left);
    }

}
