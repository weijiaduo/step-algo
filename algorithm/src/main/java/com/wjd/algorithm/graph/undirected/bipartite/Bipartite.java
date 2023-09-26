package com.wjd.algorithm.graph.undirected.bipartite;

/**
 * 二分图检测接口
 *
 * @author weijiaduo
 * @since 2023/3/5
 */
public interface Bipartite {

    /**
     * 是否是二分图
     *
     * @return true/false
     */
    boolean isBipartite();

    /**
     * 顶点所属分区的标识符
     *
     * @param v 顶点
     * @return 0/1
     */
    int id(int v);

}
