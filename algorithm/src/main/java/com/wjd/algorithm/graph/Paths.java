package com.wjd.algorithm.graph;

/**
 * 路径接口
 *
 * @author weijiaduo
 * @since 2023/3/5
 */
public interface Paths {

    /**
     * 是否有路径
     *
     * @param v 目标顶点
     * @return true存在路径/false不存在路径
     */
    boolean hasPathTo(int v);

    /**
     * 到顶点 t 的路径
     *
     * @param v 目标顶点
     * @return s->v
     */
    Iterable<Integer> pathTo(int v);

}
