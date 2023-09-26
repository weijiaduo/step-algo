package com.wjd.algorithm.graph.directed.connected;

/**
 * 强连通分量
 *
 * @author weijiaduo
 * @since 2023/3/11
 */
public interface StrongConnected {

    /**
     * 顶点 v 和顶点 w 是否连通
     *
     * @param v 顶点 v
     * @param w 顶点 w
     * @return true连通/false不连通
     */
    boolean connected(int v, int w);

    /**
     * @return 连通分量
     */
    int count();

    /**
     * 顶点对应的连通标识符
     *
     * @param v 顶点
     * @return [0, count() - 1]
     */
    int id(int v);

}
