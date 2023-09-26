package com.wjd.algorithm.graph;

/**
 * 环检测接口
 *
 * @author weijiaduo
 * @since 2023/3/5
 */
public interface Cycle {

    /**
     * 是否有环
     *
     * @return true有环/false无环
     */
    boolean hasCycle();

    /**
     * 有环时的回路（任意一个环）
     *
     * @return 路径迭代器
     */
    Iterable<Integer> cycle();

}
