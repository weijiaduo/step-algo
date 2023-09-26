package com.wjd.algorithm.graph.undirected.mst.impl;

import com.wjd.algorithm.graph.undirected.mst.MinSpanningTree;
import com.wjd.structure.graph.undirected.Edge;
import com.wjd.structure.graph.undirected.WeightedGraph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 基于 Prim 算法实现的 MST
 *
 * @author weijiaduo
 * @since 2023/3/12
 */
public class PrimMST implements MinSpanningTree {

    /**
     * 边集合
     */
    private final Queue<Edge> edges;
    /**
     * 权值和
     */
    private double weight;
    /**
     * 标记数组
     */
    private final boolean[] marked;
    /**
     * 最小堆
     */
    private final PriorityQueue<Edge> minPQ;

    public PrimMST(WeightedGraph wg) {
        edges = new LinkedList<>();
        weight = 0;
        marked = new boolean[wg.vs()];
        Arrays.fill(marked, false);
        minPQ = new PriorityQueue<>();

        find(wg);
    }

    /**
     * 寻找加权无向图的最小生成树
     *
     * @param wg 加权无向图
     */
    private void find(WeightedGraph wg) {
        visit(wg, 0);
        while (!minPQ.isEmpty()) {
            Edge edge = minPQ.poll();
            int v = edge.eight();
            int w = edge.other(v);
            if (marked[v] && marked[w]) {
                continue;
            }

            edges.offer(edge);
            weight += edge.weight();
            if (marked[v]) {
                visit(wg, w);
            } else {
                visit(wg, v);
            }
        }
    }

    /**
     * 访问顶点，并将其所有未访问的边加入堆中
     *
     * @param wg 加权无向图
     * @param s  顶点
     */
    private void visit(WeightedGraph wg, int s) {
        marked[s] = true;
        for (Edge e : wg.adj(s)) {
            int v = e.other(s);
            if (!marked[v]) {
                minPQ.offer(e);
            }
        }
    }

    @Override
    public Iterable<Edge> edges() {
        return edges;
    }

    @Override
    public double weight() {
        return weight;
    }

}
