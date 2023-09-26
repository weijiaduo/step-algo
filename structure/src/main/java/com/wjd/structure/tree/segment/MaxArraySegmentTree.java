package com.wjd.structure.tree.segment;

/**
 * 区间最大值线段树
 *
 * @author weijiaduo
 * @since 2022/9/12
 */
public class MaxArraySegmentTree extends ArraySegmentTree {

    public MaxArraySegmentTree(int low, int high) {
        super(low, high);
    }

    /**
     * @implNote 查询指定区间的最大值
     */
    @Override
    protected int mergeQuery(Node node, int start, int end, Integer lVal, Integer rVal) {
        int max = Integer.MIN_VALUE;
        if (lVal != null) {
            max = lVal;
        }
        if (rVal != null) {
            max = Math.max(rVal, max);
        }
        return max;
    }

    /**
     * @implNote 向上取区间最大值
     */
    @Override
    protected void writeUp(Node node, int start, int end) {
        node.val = Math.max(tree[node.left].val, tree[node.right].val);
    }

    /**
     * @implNote 向下更新节点值
     */
    @Override
    protected void writeDown(Node node, int start, int end, int val) {
        node.val = val;
        node.add = val;
    }

}
