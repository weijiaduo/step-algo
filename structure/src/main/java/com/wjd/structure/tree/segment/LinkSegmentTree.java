package com.wjd.structure.tree.segment;

/**
 * 指针线段树模板（动态开点）
 *
 * @author weijiaduo
 * @since 2022/7/7
 */
public abstract class LinkSegmentTree implements SegmentTree {

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
         * 左右节点
         */
        public Node left, right;
    }

    /**
     * 根节点
     */
    protected final Node root;
    /**
     * 区间最小值
     */
    protected final int low;
    /**
     * 区间最大值
     */
    protected final int high;

    public LinkSegmentTree(int low, int high) {
        this.low = low;
        this.high = high;
        this.root = new Node();
    }

    /**
     * 查询指定区间的值
     *
     * @param l 目标区间[l, r]的左边界
     * @param r 目标区间[l, r]的右边界
     * @return 区间值
     */
    @Override
    public int query(int l, int r) {
        return query(root, low, high, l, r);
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

        // 分别取左右子区间的值
        Integer lResult = null, rResult = null;
        int mid = middle(start, end);
        if (l <= mid) {
            lResult = query(node.left, start, mid, l, r);
        }
        if (r > mid) {
            rResult = query(node.right, mid + 1, end, l, r);
        }

        // 合并子区间的查询结果
        return mergeQuery(node, start, end, lResult, rResult);
    }

    /**
     * 更新指定区间的值
     *
     * @param l   目标区间[l, r]的左边界
     * @param r   目标区间[l, r]的右边界
     * @param val 更新的值
     */
    @Override
    public void update(int l, int r, int val) {
        update(root, low, high, l, r, val);
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
            update(node.left, start, mid, l, r, val);
        }
        if (r > mid) {
            update(node.right, mid + 1, end, l, r, val);
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
        // 动态开点，指向动态创建的对象
        if (node.left == null) {
            node.left = new Node();
        }
        if (node.right == null) {
            node.right = new Node();
        }

        // 没有懒标记，无需再往下推
        if (node.add == 0) {
            return;
        }

        // 把懒标记下推给子节点
        int mid = middle(start, end);
        writeDown(node.left, start, mid, node.add);
        writeDown(node.right, mid + 1, end, node.add);

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
