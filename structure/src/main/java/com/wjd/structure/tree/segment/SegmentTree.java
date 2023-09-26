package com.wjd.structure.tree.segment;

/**
 * 线段树
 *
 * @author weijiaduo
 * @since 2022/9/12
 */
public interface SegmentTree {

    /**
     * 查询指定区间的值
     *
     * @param l 目标区间[l, r]的左边界
     * @param r 目标区间[l, r]的右边界
     * @return 区间值
     */
    int query(int l, int r);

    /**
     * 更新指定区间的值
     *
     * @param l   目标区间[l, r]的左边界
     * @param r   目标区间[l, r]的右边界
     * @param val 更新的值
     */
    void update(int l, int r, int val);

}
