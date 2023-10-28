package com.wjd.structure.tree.binary;

/**
 * 二叉树实现类
 *
 * @author weijiaduo
 * @since 2023/10/29
 */
public class BinaryTreeImpl implements BinaryTree {

    /**
     * 根节点
     */
    private TreeNode root;

    public BinaryTreeImpl() {
    }

    public BinaryTreeImpl(Integer[] values) {
        root = TreeNode.build(values);
    }

    @Override
    public TreeNode query(int val) {
        return query(root, val);
    }

    /**
     * 寻找指定值的节点
     * <p>
     * 如果有重复值，会返回遍历到的第一个
     *
     * @param h   当前根节点
     * @param val 指定值
     * @return 指定值对应的节点
     */
    private TreeNode query(TreeNode h, int val) {
        if (h == null) {
            return null;
        }
        if (h.val == val) {
            return h;
        }
        TreeNode l = query(h.left, val);
        if (l != null) {
            return l;
        }
        return query(h.right, val);
    }

    @Override
    public void remove(int val) {
        root = remove(root, val);
    }

    /**
     * 移除指定值的所有节点
     *
     * @param h   当前根节点
     * @param val 指定值
     * @return 新的根节点
     */
    private TreeNode remove(TreeNode h, int val) {
        if (h == null) {
            return null;
        }

        h.left = remove(h.left, val);
        h.right = remove(h.right, val);
        if (h.val != val) {
            return h;
        }

        TreeNode nh;
        if (h.right != null) {
            // 右子树不为空，左子树拼到最左边
            nh = h.right;
            TreeNode p = h.right;
            while (p.left != null) {
                p = p.left;
            }
            p.left = h.left;
        } else {
            // 右子树为空，返回左子树
            nh = h.left;
        }
        h.left = h.right = null;
        return nh;
    }

    @Override
    public void insert(int val) {
        root = insert(root, val);
    }

    /**
     * 插入指定值
     *
     * @param h   当前根节点
     * @param val 指定值
     * @return 新的根节点
     */
    private TreeNode insert(TreeNode h, int val) {
        if (h == null) {
            return new TreeNode(val);
        }

        // 随机插入左右子树
        if (direction(h, val) % 2 == 0) {
            h.left = insert(h.left, val);
        } else {
            h.right = insert(h.right, val);
        }
        return h;
    }

    /**
     * 指定值的插入方向
     *
     * @param h   当前根节点
     * @param val 指定值
     * @return 偶数表示左边；奇数表示有右边
     */
    protected int direction(TreeNode h, int val) {
        return h.val ^ val;
    }

    @Override
    public String toString() {
        return TreeNode.toString(root);
    }

}
