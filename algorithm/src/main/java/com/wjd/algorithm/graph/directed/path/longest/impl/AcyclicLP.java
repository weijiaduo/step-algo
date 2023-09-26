package com.wjd.algorithm.graph.directed.path.longest.impl;

import com.wjd.algorithm.graph.directed.order.Topological;
import com.wjd.algorithm.graph.directed.path.longest.LongestPaths;
import com.wjd.structure.graph.directed.DirectedEdge;
import com.wjd.structure.graph.directed.WeightedDigraph;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 无环加权有向图的最长路径算法
 * <p>
 * 要求：
 * <p>
 * - 不能有环
 * <p>
 * - 可以有负权重的边
 *
 * @author weijiaduo
 * @since 2023/3/15
 */
public class AcyclicLP implements LongestPaths {

    /**
     * 距离
     */
    private final double[] distTo;
    /**
     * 边的终点 -> 边
     */
    private final DirectedEdge[] edgeFrom;

    public AcyclicLP(WeightedDigraph wdg, int s) {
        distTo = new double[wdg.vs()];
        Arrays.fill(distTo, Double.NEGATIVE_INFINITY);
        edgeFrom = new DirectedEdge[wdg.vs()];
        find(wdg, s);
    }

    /**
     * 搜索单点最长路径
     *
     * @param wdg 加权有向图
     * @param s   起点
     */
    private void find(WeightedDigraph wdg, int s) {
        Topological tpl = new Topological(wdg);
        if (tpl.isDAG()) {
            distTo[s] = 0;
            for (int v : tpl.order()) {
                relax(wdg, v);
            }
        }
    }

    /**
     * 放松，更新最长路径
     *
     * @param wdg 加权有向图
     * @param v   放松的顶点
     */
    private void relax(WeightedDigraph wdg, int v) {
        for (DirectedEdge e : wdg.adj(v)) {
            int w = e.to();
            if (distTo[v] + e.weight() > distTo[w]) {
                distTo[w] = distTo[v] + e.weight();
                edgeFrom[w] = e;
            }
        }
    }

    @Override
    public double distTo(int v) {
        return distTo[v];
    }

    @Override
    public boolean hasPathTo(int v) {
        return distTo[v] > Double.NEGATIVE_INFINITY;
    }

    @Override
    public Iterable<DirectedEdge> pathTo(int v) {
        LinkedList<DirectedEdge> stack = new LinkedList<>();
        DirectedEdge edge = edgeFrom[v];
        while (edge != null) {
            stack.push(edge);
            edge = edgeFrom[edge.from()];
        }
        return stack;
    }

}
