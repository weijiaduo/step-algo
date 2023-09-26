package com.wjd.algorithm.graph;

/**
 * 图的顶点顺序
 *
 * @author weijiaduo
 * @since 2023/3/10
 */
public interface Order {

    /**
     * 获取顶点顺序
     *
     * @return 顶点顺序迭代器
     */
    Iterable<Integer> order();

}
