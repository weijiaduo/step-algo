package com.wjd.structure.graph.directed;

/**
 * 有向边
 *
 * @author weijiaduo
 * @since 2023/3/15
 */
public class DirectedEdge implements Comparable<DirectedEdge> {

    /**
     * 边的起点
     */
    private final int v;
    /**
     * 边的终点
     */
    private final int w;
    /**
     * 边的权重
     */
    private final double weight;

    public DirectedEdge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    /**
     * @return 边的起点
     */
    public int from() {
        return v;
    }

    /**
     * @return 边的终点
     */
    public int to() {
        return w;
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
    public int compareTo(DirectedEdge o) {
        return Double.compare(weight, o.weight);
    }

    @Override
    public String toString() {
        return String.format("(%d, %d, %.2f)", v, w, weight);
    }

}
