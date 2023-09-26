package com.wjd.structure.graph.undirected;

/**
 * 加权无向图接口
 *
 * @author weijiaduo
 * @since 2023/3/5
 */
public interface WeightedGraph {

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
     * @param edge 边
     */
    void addEdge(Edge edge);

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
    Iterable<Edge> adj(int v);

    /**
     * 无向图的所有边
     *
     * @return 边集合
     */
    Iterable<Edge> edges();

}
