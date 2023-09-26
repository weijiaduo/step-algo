package com.wjd.structure.tree.redblack;

import java.util.Arrays;

/**
 * 左偏向（Left-Leaning）红黑树（2-3树）
 * <p>
 * 基于自顶向下
 * <p>
 * 1. 红链接只能出现在左边
 * <p>
 * 2. 不能出现 2 条相邻的红链接
 *
 * @author weijiaduo
 * @since 2023/1/24
 */
public class LLRBTree implements RBTree {

    /**
     * 红色
     */
    private static final boolean RED = true;
    /**
     * 黑色
     */
    private static final boolean BLACK = false;

    /**
     * 根节点
     */
    private RBTNode root;

    @Override
    public Integer get(int val) {
        RBTNode node = get(root, val);
        return node != null ? node.val : null;
    }

    /**
     * 查找指定值对应的节点
     *
     * @param h   当前节点
     * @param val 指定 key，也是 value
     * @return 指定节点
     */
    private RBTNode get(RBTNode h, int val) {
        // 没有找到
        if (h == null) {
            return null;
        }
        // 找到对应的节点
        if (h.val == val) {
            return h;
        }
        if (h.val > val) {
            return get(h.left, val);
        } else {
            return get(h.right, val);
        }
    }

    @Override
    public void insert(int val) {
        RBTNode newNode = new RBTNode(val);
        root = insertNode(root, newNode);
        root.color = BLACK;
    }

    /**
     * 插入新数据
     * <p>
     * 自顶向下 -> 自底向上调整树结构
     *
     * @param h       当前节点
     * @param newNode 新节点
     * @return 新当前节点
     */
    private RBTNode insertNode(RBTNode h, RBTNode newNode) {
        // 插入新叶子节点
        if (h == null) {
            return newNode;
        }

        // 预分解 4-节点为 3 个节点
        if (isRed(h.left) && isRed(h.right)) {
            flipColor(h, RED);
        }

        // 值已存在，直接返回
        if (h.val == newNode.val) {
            return h;
        }
        if (h.val > newNode.val) {
            h.left = insertNode(h.left, newNode);
        } else {
            h.right = insertNode(h.right, newNode);
        }
        return balance(h);
    }

    @Override
    public void remove(int val) {
        if (get(val) == null) {
            return;
        }

        // 预合并，确保传给 remove(root) 的是 3-节点
        if (root != null && !hasRed(root)) {
            root.color = RED;
        }
        root = remove(root, val);
        if (isRed(root)) {
            root.color = BLACK;
        }
    }

    /**
     * 删除指定值的节点
     * <p>
     * 自顶向下 -> 自底向上调整树结构
     *
     * @param h   当前节点，保证 h 是 3/4-节点，或者 null
     * @param val 指定值
     * @return 新当前节点
     */
    private RBTNode remove(RBTNode h, int val) {
        if (h == null) {
            return null;
        }
        if (h.val == val) {
            return removeNode(h);
        }
        if (h.val > val) {
            // 预合并，保证左子节点是 3/4-节点
            if (!hasRed(h.left)) {
                h = moveRedLeft(h);
            }
            h.left = remove(h.left, val);
        } else {
            // 红色节点临时转到右边
            if (isRed(h.left)) {
                h = rotateRight(h);
            }
            // 预合并，保证右子节点是 3/4-节点
            if (!hasRed(h.right)) {
                h = moveRedRight(h);
            }
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
    private RBTNode removeNode(RBTNode h) {
        if (isRed(h)) {
            // 红色叶子节点，直接删除
            if (h.right == null) {
                return null;
            }
            // 红色内部节点，用后继节点替换删除
            final int deleteVal = h.val;
            if (!hasRed(h.right)) {
                // 保证当前节点是 3/4-节点
                h = moveRedRight(h);
            }
            if (h.val == deleteVal) {
                // 节点没变化，替换删除
                RBTNode t = min(h.right);
                t.right = deleteMin(h.right);
                t.left = h.left;
                t.color = h.color;
                h = t;
            } else {
                // 节点旋走了，递归删除
                h.right = removeNode(h.right);
            }
        } else {
            // 黑色节点，转成红色再删除
            h = rotateRight(h);
            h.right = removeNode(h.right);
        }
        return balance(h);
    }

    /**
     * 最小值节点
     *
     * @param h 当前节点
     * @return 最小值节点
     */
    private RBTNode min(RBTNode h) {
        if (h == null || h.left == null) {
            return h;
        }
        return min(h.left);
    }

    /**
     * 删除最小值节点
     */
    public void deleteMin() {
        // 确保传给 deleteMin(root) 的是 3-节点
        if (root != null && !hasRed(root)) {
            root.color = RED;
        }
        root = deleteMin(root);
        // 保证根节点始终是黑色
        if (isRed(root)) {
            root.color = BLACK;
        }
    }

    /**
     * 删除最小值节点
     *
     * @param h 当前节点，保证 h 是 3/4-节点，或者 null
     * @return 新当前节点
     */
    private RBTNode deleteMin(RBTNode h) {
        if (h == null) {
            return null;
        }
        // 删除最小值节点
        if (h.left == null) {
            return null;
        }
        // 左子节点是 2-节点，递归前需先转成 3/4-节点
        if (!hasRed(h.left)) {
            h = moveRedLeft(h);
        }
        // 递归删除最小值
        h.left = deleteMin(h.left);
        return balance(h);
    }

    /**
     * 删除最大值节点
     */
    public void deleteMax() {
        // 确保传给 deleteMax(root) 的是 3-节点
        if (root != null && !hasRed(root)) {
            root.color = RED;
        }
        root = deleteMax(root);
        // 保证根节点始终是黑色
        if (isRed(root)) {
            root.color = BLACK;
        }
    }

    /**
     * 删除最大值节点
     *
     * @param h 当前节点，保证 h 是 3/4-节点，或者 null
     * @return 新当前节点
     */
    private RBTNode deleteMax(RBTNode h) {
        if (h == null) {
            return null;
        }
        // 红色节点在左边，先临时转到右边
        if (isRed(h.left)) {
            h = rotateRight(h);
        }
        // 删除最大值节点
        if (h.right == null) {
            return null;
        }
        // 右子节点是 2-节点，需先转成 3/4-节点
        if (!hasRed(h.right)) {
            h = moveRedRight(h);
        }
        // 递归删除最大值
        h.right = deleteMax(h.right);
        return balance(h);
    }

    /**
     * 左子节点（h.left）是 2-节点，转一个红色节点到左边
     * <p>
     * 1. 从右兄弟借一个
     * <p>
     * 2. 合并根左右节点
     *
     * @param h 当前节点
     * @return 新当前节点
     */
    private RBTNode moveRedLeft(RBTNode h) {
        if (isRed(h.right.left)) {
            // 右子节点是 3-节点，借一个给左子节点
            flipColor(h, BLACK);
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
        } else {
            // 右子节点也是 2-节点，合并根左右节点，临时转成 4-节点
            flipColor(h, BLACK);
        }
        return h;
    }

    /**
     * 右子节点（h.right）是 2-节点，转一个红色节点到右边
     * <p>
     * 1. 从左兄弟借一个
     * <p>
     * 2. 合并根左右节点
     *
     * @param h 当前节点
     * @return 新当前节点
     */
    private RBTNode moveRedRight(RBTNode h) {
        if (isRed(h.left.left)) {
            // 左子节点是 3-节点，借一个给右子节点
            flipColor(h, BLACK);
            h = rotateRight(h);
        } else {
            // 左子节点也是 2-节点，合并根左右节点，临时转成 4-节点
            flipColor(h, BLACK);
        }
        return h;
    }

    /**
     * 修正红黑树，平衡树结构
     *
     * @param h 当前节点
     * @return 新当前节点
     */
    private RBTNode balance(RBTNode h) {
        if (h == null) {
            return null;
        }
        // 红节点在右边（2-节点”右插入“/3-节点”中插入“）
        if (isRed(h.right)) {
            h = rotateLeft(h);
        }
        // 连续 2 个红节点（3-节点”左插入“）
        if (isRed(h.left) && isRed(h.left.left)) {
            h = rotateRight(h);
        }
        // 左右两个红节点（3-节点“右插入”）
        if (isRed(h.left) && isRed(h.right)) {
            flipColor(h, RED);
        }
        return h;
    }

    /**
     * 左旋（红节点转到左边）
     *
     * @param h 当前节点
     * @return 新当前节点
     */
    RBTNode rotateLeft(RBTNode h) {
        RBTNode right = h.right;
        h.right = right.left;
        right.left = h;
        right.color = h.color;
        h.color = RED;
        return right;
    }

    /**
     * 右旋（红节点转到右边）
     *
     * @param h 当前节点
     * @return 新当前节点
     */
    RBTNode rotateRight(RBTNode h) {
        RBTNode left = h.left;
        h.left = left.right;
        left.right = h;
        left.color = h.color;
        h.color = RED;
        return left;
    }

    /**
     * 1. 父节点染红，子节点染黑
     * <p>
     * 2. 父节点染黑，子节点染红
     *
     * @param h     当前节点
     * @param color 当前节点颜色
     */
    private void flipColor(RBTNode h, boolean color) {
        h.color = color;
        h.left.color = !color;
        h.right.color = !color;
    }

    /**
     * 是否有红色节点（是否是 3-节点）
     *
     * @param h 节点
     * @return true有红色/false无红色
     */
    private boolean hasRed(RBTNode h) {
        if (h == null) {
            return false;
        }
        // 当前节点或者左子节点是红色
        return isRed(h) || isRed(h.left);
    }

    /**
     * 是否是红色节点
     *
     * @param h 节点
     * @return true红色节点/false黑色节点
     */
    private boolean isRed(RBTNode h) {
        return h != null && h.color == RED;
    }

    @Override
    public String toString() {
        return Arrays.toString(new RedBlackTreeSerializer().serialize(root));
    }

}
