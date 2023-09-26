package com.wjd.structure.tree.segment;

/**
 * 数组线段树模板（动态估点）
 *
 * @author weijiaduo
 * @since 2022/9/12
 */
public abstract class ArraySegmentTree implements SegmentTree {

    public static class Node {
        /**
         * 节点值
         */
        public int val;
        /**
         * 懒惰标记
         */
        public int add;
        /**
         * 左右节点数组下标
         */
        public int left, right;
    }

    /**
     * 树节点数组
     */
    protected final Node[] tree;
    /**
     * 区间最小值
     */
    protected final int low;
    /**
     * 区间最大值
     */
    protected final int high;
    /**
     * 当前节点数量
     */
    protected int size;

    public ArraySegmentTree(int low, int high) {
        this.low = low;
        this.high = high;

        // 区间估点 4n
        int n = 4 * high;
        tree = new Node[n];

        // 初始化根节点
        tree[1] = new Node();
        size = 1;
    }

    @Override
    public int query(int l, int r) {
        return query(tree[1], low, high, l, r);
    }

    /**
     * 查询指定区间的值
     *
     * @param node  当前节点
     * @param start 当前区间[start, end]的左边界
     * @param end   当前区间[start, end]的右边界
     * @param l     目标区间[l, r]的左边界
     * @param r     目标区间[l, r]的右边界
     * @return 区间值
     */
    protected int query(Node node, int start, int end, int l, int r) {
        if (l <= start && end <= r) {
            return node.val;
        }

        // 访问节点前，先向下推送更新
        pushDown(node, start, end);

        // 分别查询左右子区间的值
        Integer lResult = null, rResult = null;
        int mid = middle(start, end);
        if (l <= mid) {
            lResult = query(tree[node.left], start, mid, l, r);
        }
        if (r > mid) {
            rResult = query(tree[node.right], mid + 1, end, l, r);
        }

        // 合并子区间的查询结果
        return mergeQuery(node, start, end, lResult, rResult);
    }

    @Override
    public void update(int l, int r, int val) {
        update(tree[1], low, high, l, r, val);
    }

    /**
     * 更新指定区间的值
     *
     * @param node  当前节点
     * @param start 当前区间[start, end]的左边界
     * @param end   当前区间[start, end]的右边界
     * @param l     目标区间[l, r]的左边界
     * @param r     目标区间[l, r]的右边界
     * @param val   更新的值
     */
    protected void update(Node node, int start, int end, int l, int r, int val) {
        if (l <= start && end <= r) {
            // 更新符合区间要求的节点值
            writeDown(node, start, end, val);
            return;
        }

        // 访问节点前，先向下推送更新
        pushDown(node, start, end);

        // 递归更新左右子区间
        int mid = middle(start, end);
        if (l <= mid) {
            update(tree[node.left], start, mid, l, r, val);
        }
        if (r > mid) {
            update(tree[node.right], mid + 1, end, l, r, val);
        }

        // 子节点更新后，向上推送更新
        pushUp(node, start, end);
    }

    /**
     * 子节点的值上推到父节点
     *
     * @param node  父节点
     * @param start 当前区间[start, end]的左边界
     * @param end   当前区间[start, end]的右边界
     */
    protected void pushUp(Node node, int start, int end) {
        writeUp(node, start, end);
    }

    /**
     * 父节点的值下推到子节点
     *
     * @param node  当前节点
     * @param start 当前区间[start, end]的左边界
     * @param end   当前区间[start, end]的右边界
     */
    protected void pushDown(Node node, int start, int end) {
        // 动态开点，新节点总是添加到数组末尾
        if (node.left == 0) {
            node.left = ++size;
            tree[node.left] = new Node();
        }
        if (node.right == 0) {
            node.right = ++size;
            tree[node.right] = new Node();
        }

        // 没有懒标记，无需再往下推
        if (node.add == 0) {
            return;
        }

        // 把懒标记下推给子节点
        int mid = middle(start, end);
        writeDown(tree[node.left], start, mid, node.add);
        writeDown(tree[node.right], mid + 1, end, node.add);

        // 懒标记已处理
        node.add = 0;
    }

    /**
     * 合并指定区间的查询结果
     *
     * @param node  当前节点
     * @param start 当前区间[start, end]的左边界
     * @param end   当前区间[start, end]的右边界
     * @param lVal  左区间的查询结果
     * @param rVal  右区间的查询结果
     * @return 区间值
     */
    protected abstract int mergeQuery(Node node, int start, int end, Integer lVal, Integer rVal);

    /**
     * 向上更新节点的值
     *
     * @param node  当前节点
     * @param start 当前区间[start, end]的左边界
     * @param end   当前区间[start, end]的右边界
     */
    protected abstract void writeUp(Node node, int start, int end);

    /**
     * 向下更新节点的值
     *
     * @param node  当前节点
     * @param start 当前区间[start, end]的左边界
     * @param end   当前区间[start, end]的右边界
     * @param val   更新的值
     */
    protected abstract void writeDown(Node node, int start, int end, int val);

    /**
     * 划分左右区间的中点
     *
     * @param start 左边界[start, end]
     * @param end   右边界[start, end]
     * @return 中点
     */
    protected int middle(int start, int end) {
        return start + (end - start) / 2;
    }

}
