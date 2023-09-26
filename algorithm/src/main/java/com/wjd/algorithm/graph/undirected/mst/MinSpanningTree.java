package com.wjd.algorithm.graph.undirected.mst;

import com.wjd.structure.graph.undirected.Edge;

/**
 * 最小生成树
 *
 * @author weijiaduo
 * @since 2023/3/12
 */
public interface MinSpanningTree {

    /**
     * 最小生成树的边
     *
     * @return 边迭代器
     */
    Iterable<Edge> edges();

    /**
     * 最小生成树的权重
     *
     * @return 权重
     */
    double weight();

}
