package com.wjd.algorithm.graph;

/**
 * 无向图的搜索遍历接口
 *
 * @author weijiaduo
 * @since 2023/3/5
 */
public interface Search {

    /**
     * 顶点是否已被标记（遍历过）
     *
     * @param v 指定顶点
     * @return true已标记/false未标记
     */
    boolean marked(int v);

    /**
     * 遍历到的顶点数量
     *
     * @return 顶点数量
     */
    int count();

}
