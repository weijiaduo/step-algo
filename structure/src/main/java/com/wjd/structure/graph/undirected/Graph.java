package com.wjd.structure.graph.undirected;

/**
 * 无向图接口
 *
 * @author weijiaduo
 * @since 2023/3/5
 */
public interface Graph {

    /**
     * @return 顶点数量
     */
    int vs();

    /**
     * @return 边数量
     */
    int es();

    /**
     * 添加边
     *
     * @param v (v, w)
     * @param w (v, w)
     */
    void addEdge(int v, int w);

    /**
     * 是否存在边
     *
     * @param v <v, w>
     * @param w <v, w>
     * @return true存在边/false不存在边
     */
    boolean hasEdge(int v, int w);

    /**
     * 指定顶点的相邻边
     *
     * @param v 指定顶点编号
     * @return 相邻边迭代器
     */
    Iterable<Integer> adj(int v);

}
