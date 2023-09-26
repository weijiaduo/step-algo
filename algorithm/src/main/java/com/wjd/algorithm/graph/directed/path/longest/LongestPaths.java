package com.wjd.algorithm.graph.directed.path.longest;

import com.wjd.structure.graph.directed.DirectedEdge;

/**
 * 最长路径接口
 *
 * @author weijiaduo
 * @since 2023/3/15
 */
public interface LongestPaths {

    /**
     * 到顶点 v 的最短路径距离
     *
     * @param v 目标顶点
     * @return 最短路径距离
     */
    double distTo(int v);

    /**
     * 是否有路径
     *
     * @param v 目标顶点
     * @return true存在路径/false不存在路径
     */
    boolean hasPathTo(int v);

    /**
     * 到顶点 v 的最短路径
     *
     * @param v 模目标顶点
     * @return s->v
     */
    Iterable<DirectedEdge> pathTo(int v);

}
