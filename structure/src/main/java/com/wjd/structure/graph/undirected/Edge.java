package com.wjd.structure.graph.undirected;

/**
 * 带权边
 *
 * @author weijiaduo
 * @since 2023/3/12
 */
public class Edge implements Comparable<Edge> {

    private final int v;
    private final int w;
    /**
     * 边的权重
     */
    private final double weight;

    public Edge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    /**
     * 边的任意一个顶点
     *
     * @return 顶点
     */
    public int eight() {
        return v;
    }

    /**
     * 边的另外一个顶点
     *
     * @param x 其中一个顶点
     * @return 另外一个顶点
     */
    public int other(int x) {
        if (!contains(x)) {
            throw new IllegalArgumentException(String.format("x(%d) not belong to edge(%d, %d)", x, v, w));
        }
        return x == v ? w : v;
    }

    /**
     * 是否包含指定顶点
     *
     * @param x 指定顶点
     * @return true/false
     */
    public boolean contains(int x) {
        return x == v || x == w;
    }

    /**
     * 获取边的权值
     *
     * @return 边的权值
     */
    public double weight() {
        return weight;
    }

    @Override
    public int compareTo(Edge o) {
        return Double.compare(weight, o.weight);
    }

    @Override
    public String toString() {
        return String.format("(%d, %d, %.2f)", v, w, weight);
    }
}
