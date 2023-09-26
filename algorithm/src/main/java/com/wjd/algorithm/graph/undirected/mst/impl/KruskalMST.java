package com.wjd.algorithm.graph.undirected.mst.impl;

import com.wjd.algorithm.graph.undirected.mst.MinSpanningTree;
import com.wjd.structure.graph.undirected.Edge;
import com.wjd.structure.graph.undirected.WeightedGraph;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 基于 Kruskal 算法实现的 MST
 *
 * @author weijiaduo
 * @since 2023/3/13
 */
public class KruskalMST implements MinSpanningTree {

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
    private final int[] marked;

    public KruskalMST(WeightedGraph wg) {
        edges = new LinkedList<>();
        weight = 0;
        marked = new int[wg.vs()];
        for (int i = 0; i < marked.length; i++) {
            marked[i] = i;
        }

        find(wg);
    }

    /**
     * 找加权无向图的最小生成树
     *
     * @param wg 加权无向图
     */
    private void find(WeightedGraph wg) {
        PriorityQueue<Edge> minPQ = new PriorityQueue<>();
        for (Edge e : wg.edges()) {
            minPQ.offer(e);
        }

        int vs = marked.length;
        while (!minPQ.isEmpty() && edges.size() < vs - 1) {
            Edge edge = minPQ.poll();
            int v = edge.eight();
            int w = edge.other(v);
            if (connected(v, w)) {
                continue;
            }

            edges.add(edge);
            weight += edge.weight();
            union(v, w);
        }
    }

    /**
     * 两个顶点是否连通
     *
     * @param v 顶点
     * @param w 顶点
     * @return true/false
     */
    private boolean connected(int v, int w) {
        return root(v) == root(w);
    }

    /**
     * 合并两个顶点所在的两棵树
     *
     * @param v 顶点
     * @param w 顶点
     */
    private void union(int v, int w) {
        int vr = root(v);
        int wr = root(w);
        if (vr == wr) {
            return;
        }
        marked[wr] = vr;
    }

    /**
     * 顶点所在连通树的根节点
     *
     * @param v 顶点
     * @return 根节点索引
     */
    private int root(int v) {
        if (marked[v] == v) {
            return v;
        }
        marked[v] = root(marked[v]);
        return marked[v];
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
