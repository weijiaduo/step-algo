package com.wjd.structure.tree.avl;

import java.util.Arrays;

/**
 * 平衡二叉树实现
 *
 * @author weijiaduo
 * @since 2023/2/21
 */
public class AVLTreeImpl implements AVLTree {

    /**
     * 根节点
     */
    private AVLTNode root;

    @Override
    public Integer get(int val) {
        AVLTNode h = get(root, val);
        return h != null ? h.val : null;
    }

    /**
     * 二分查找指定值
     *
     * @param h   当前节点
     * @param val 指定值
     * @return 指定值的节点
     */
    private AVLTNode get(AVLTNode h, int val) {
        if (h == null) {
            return null;
        }
        if (h.val == val) {
            return h;
        }
        if (val < h.val) {
            return get(h.left, val);
        } else {
            return get(h.right, val);
        }
    }

    @Override
    public void insert(int val) {
        if (get(val) != null) {
            return;
        }
        root = insert(root, val);
    }

    /**
     * 插入新节点
     *
     * @param h   当前节点
     * @param val 新节点值
     * @return 新当前节点
     */
    private AVLTNode insert(AVLTNode h, int val) {
        if (h == null) {
            return new AVLTNode(val);
        }
        if (val < h.val) {
            h.left = insert(h.left, val);
        } else {
            h.right = insert(h.right, val);
        }
        return balance(h);
    }

    @Override
    public void remove(int val) {
        root = remove(root, val);
    }

    /**
     * 删除指定节点
     *
     * @param h   当前节点
     * @param val 指定节点值
     * @return 新当前节点
     */
    private AVLTNode remove(AVLTNode h, int val) {
        if (h == null) {
            return null;
        }
        if (h.val == val) {
            return deleteNode(h);
        }
        if (val < h.val) {
            h.left = remove(h.left, val);
        } else {
            h.right = remove(h.right, val);
        }
        return balance(h);
    }

    /**
     * 删除指定节点
     *
     * @param h 被删除节点
     * @return 新当前节点
     */
    private AVLTNode deleteNode(AVLTNode h) {
        if (h.left == null) {
            return h.right;
        } else if (h.right == null) {
            return h.left;
        } else {
            AVLTNode t = h;
            // 后继节点替代当前节点
            h = min(t.right);
            h.right = deleteMin(t.right);
            // 左节点赋值要放 deleteMin 之后
            h.left = t.left;
            return balance(h);
        }
    }

    /**
     * 删除最小值节点
     *
     * @param h 当前节点
     * @return 新当前节点
     */
    private AVLTNode deleteMin(AVLTNode h) {
        if (h == null) {
            return null;
        }
        if (h.left == null) {
            return h.right;
        }
        h.left = deleteMin(h.left);
        return balance(h);
    }

    /**
     * 返回最小值节点
     *
     * @param h 当前节点
     * @return 最小值节点
     */
    private AVLTNode min(AVLTNode h) {
        if (h == null || h.left == null) {
            return h;
        }
        return min(h.left);
    }

    /**
     * 平衡树结构
     *
     * @param h 当前节点
     * @return 新当前节点
     */
    private AVLTNode balance(AVLTNode h) {
        if (h == null) {
            return null;
        }

        // 更新当前节点高度
        h.height = Math.max(height(h.left), height(h.right)) + 1;

        if (getBalanceFactor(h) > 1) {
            // Left-Right
            if (getBalanceFactor(h.left) < 0) {
                h.left = rotateLeft(h.left);
            }
            // Left-Left
            if (getBalanceFactor(h.left) >= 0) {
                h = rotateRight(h);
            }
        }
        if (getBalanceFactor(h) < -1) {
            // Right-Left
            if (getBalanceFactor(h.right) > 0) {
                h.right = rotateRight(h.right);
            }
            // Right-Right
            if (getBalanceFactor(h.right) <= 0) {
                h = rotateLeft(h);
            }
        }
        return h;
    }

    /**
     * 当前节点的平衡因子
     *
     * @param h 当前节点
     * @return 平衡因子
     */
    private int getBalanceFactor(AVLTNode h) {
        return h != null ? height(h.left) - height(h.right) : 0;
    }

    /**
     * 左旋
     *
     * @param h 当前节点
     * @return 新当前节点
     */
    private AVLTNode rotateLeft(AVLTNode h) {
        AVLTNode r = h.right;
        h.right = r.left;
        r.left = h;
        h.height = Math.max(height(h.left), height(h.right)) + 1;
        r.height = Math.max(height(r.left), height(r.right)) + 1;
        return r;
    }

    /**
     * 右旋
     *
     * @param h 当前节点
     * @return 新当前节点
     */
    private AVLTNode rotateRight(AVLTNode h) {
        AVLTNode l = h.left;
        h.left = l.right;
        l.right = h;
        h.height = Math.max(height(h.left), height(h.right)) + 1;
        l.height = Math.max(height(l.left), height(l.right)) + 1;
        return l;
    }

    /**
     * 节点高度
     *
     * @param h 当前节点
     * @return 高度
     */
    private int height(AVLTNode h) {
        return h != null ? h.height : 0;
    }

    @Override
    public String toString() {
        return Arrays.toString(new AVLTreeSerializer().serialize(root));
    }

}
