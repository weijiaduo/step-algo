package com.wjd.algorithm.graph.directed.path.shortest.impl;

import com.wjd.algorithm.graph.directed.cycle.WeightedDirectedCycle;
import com.wjd.algorithm.graph.directed.path.shortest.ShortestPaths;
import com.wjd.structure.graph.directed.DirectedEdge;
import com.wjd.structure.graph.directed.WeightedDigraph;
import com.wjd.structure.graph.directed.impl.ListWeightedDigraph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 基于 Bellman Ford 算法的最短路径实现
 * <p>
 * 要求：
 * <p>
 * 1. 不能有负权重环
 *
 * @author weijiaduo
 * @since 2023/3/20
 */
public class BellmanFordSP implements ShortestPaths {

    /**
     * 距离
     */
    private final double[] distTo;
    /**
     * 边的终点 -> 边
     */
    private final DirectedEdge[] edgeFrom;
    /**
     * 负权重环
     */
    private Iterable<Integer> negativeCycle;
    /**
     * relax 边的次数
     */
    private int cost;
    private final Queue<Integer> queue;
    private final boolean[] onQueue;

    public BellmanFordSP(WeightedDigraph wdg, int s) {
        distTo = new double[wdg.vs()];
        Arrays.fill(distTo, Double.POSITIVE_INFINITY);
        edgeFrom = new DirectedEdge[wdg.vs()];
        queue = new LinkedList<>();
        onQueue = new boolean[wdg.vs()];
        Arrays.fill(onQueue, false);
        cost = 0;

        find(wdg, s);
    }

    /**
     * 搜索单点最短路径
     *
     * @param wdg 加权有向图
     * @param s   起点
     */
    private void find(WeightedDigraph wdg, int s) {
        distTo[s] = 0;
        queue.offer(s);
        while (!queue.isEmpty() && !hasNegativeCycle()) {
            int v = queue.poll();
            onQueue[v] = false;
            relax(wdg, v);
        }
    }

    /**
     * 放松，更新最短路径
     *
     * @param wdg 加权有向图
     * @param v   放松的顶点
     */
    private void relax(WeightedDigraph wdg, int v) {
        for (DirectedEdge e : wdg.adj(v)) {
            int w = e.to();
            if (distTo[v] + e.weight() < distTo[w]) {
                distTo[w] = distTo[v] + e.weight();
                edgeFrom[w] = e;
                if (!onQueue[w]) {
                    queue.offer(w);
                    onQueue[w] = true;
                }
            }

            // 一轮遍历后，就检查一次负权重环
            if (++cost % wdg.vs() == 0) {
                findNegativeCycle();
            }
        }
    }

    /**
     * 查找负权重环
     */
    private void findNegativeCycle() {
        int size = edgeFrom.length;
        WeightedDigraph spt = new ListWeightedDigraph(size);
        for (DirectedEdge edge : edgeFrom) {
            if (edge != null) {
                spt.addEdge(edge);
            }
        }
        negativeCycle = new WeightedDirectedCycle(spt).cycle();
    }

    /**
     * @return 是否有负权重环
     */
    private boolean hasNegativeCycle() {
        return negativeCycle != null;
    }

    @Override
    public double distTo(int v) {
        return distTo[v];
    }

    @Override
    public boolean hasPathTo(int v) {
        return distTo[v] < Double.POSITIVE_INFINITY;
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
