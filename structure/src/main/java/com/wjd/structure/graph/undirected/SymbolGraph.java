package com.wjd.structure.graph.undirected;

/**
 * 无向符号图接口
 *
 * @author weijiaduo
 * @since 2023/3/8
 */
public interface SymbolGraph {

    /**
     * 指定符号是否在图中
     *
     * @param key 符号
     * @return true在图中/false不在图中
     */
    boolean contains(String key);

    /**
     * 指定符号对应的顶点编号
     *
     * @param key 符号
     * @return 顶点编号（0~n-1）
     */
    int idx(String key);

    /**
     * 指定顶点编号对应的符号
     *
     * @param index 顶点编号
     * @return 符号
     */
    String key(int index);

    /**
     * 符号图对应的编号图
     *
     * @return 编号图
     */
    Graph graph();

    /**
     * 添加边
     *
     * @param v 顶点v
     * @param w 顶点w
     */
    void addEdge(String v, String w);

    /**
     * 是否存在边
     *
     * @param v 顶点v
     * @param w 顶点w
     */
    boolean hasEdge(String v, String w);

    /**
     * 顶点的邻接顶点
     *
     * @param v 指定顶点
     * @return 邻接顶点
     */
    Iterable<String> adj(String v);

}
