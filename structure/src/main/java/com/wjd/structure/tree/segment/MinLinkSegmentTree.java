package com.wjd.structure.tree.segment;

/**
 * 区间最小值线段树
 *
 * @author weijiaduo
 * @since 2022/9/12
 */
public class MinLinkSegmentTree extends LinkSegmentTree {

    public MinLinkSegmentTree(int low, int high) {
        super(low, high);
    }

    @Override
    protected int mergeQuery(Node node, int start, int end, Integer lVal, Integer rVal) {
        int min = Integer.MAX_VALUE;
        if (lVal != null) {
            min = lVal;
        }
        if (rVal != null) {
            min = Math.min(rVal, min);
        }
        return min;
    }

    @Override
    protected void writeUp(Node node, int start, int end) {
        node.val = Math.min(node.left.val, node.right.val);
    }

    @Override
    protected void writeDown(Node node, int start, int end, int val) {
        node.val = val;
        node.add = val;
    }
}
