package com.wjd.structure.tree.segment;

/**
 * 区间和线段树
 *
 * @author weijiaduo
 * @since 2022/9/11
 */
public class SumLinkSegmentTree extends LinkSegmentTree {

    public SumLinkSegmentTree(int low, int high) {
        super(low, high);
    }

    /**
     * @implNote 查询指定区间和
     */
    @Override
    protected int mergeQuery(Node node, int start, int end, Integer lVal, Integer rVal) {
        int sum = 0;
        if (lVal != null) {
            sum += lVal;
        }
        if (rVal != null) {
            sum += rVal;
        }
        return sum;
    }

    /**
     * @implNote 向上更新区间和
     */
    @Override
    protected void writeUp(Node node, int start, int end) {
        node.val = node.left.val + node.right.val;
    }

    /**
     * @implNote 向下更新区间和，同时更新懒惰标记
     */
    @Override
    protected void writeDown(Node node, int start, int end, int val) {
        // 区间值更新，包括了子节点的值更新总和
        node.val += (end - start + 1) * val;
        // 懒惰标记，对区间进行「加减」的更新操作，懒惰标记需要累加，不能直接覆盖
        node.add += val;
    }

}
