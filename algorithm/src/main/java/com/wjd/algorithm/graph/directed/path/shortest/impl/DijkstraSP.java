package com.wjd.algorithm.graph.directed.path.shortest.impl;

import com.wjd.algorithm.graph.directed.path.shortest.ShortestPaths;
import com.wjd.structure.graph.directed.DirectedEdge;
import com.wjd.structure.graph.directed.WeightedDigraph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * 基于 Dijkstra 算法的最短路径实现
 * <p>
 * 要求：
 * <p>
 * - 不能有负权重的边
 * <p>
 * - 可以有环
 *
 * @author weijiaduo
 * @since 2023/3/15
 */
public class DijkstraSP implements ShortestPaths {

    /**
     * 距离
     */
    private final double[] distTo;
    /**
     * 边的终点 -> 边
     */
    private final DirectedEdge[] edgeFrom;
    /**
     * 标记数组
     */
    private final boolean[] marked;
    /**
     * 最小值堆
     */
    private final PriorityQueue<Node> minHeap;

    public DijkstraSP(WeightedDigraph wdg, int s) {
        distTo = new double[wdg.vs()];
        Arrays.fill(distTo, Double.POSITIVE_INFINITY);
        edgeFrom = new DirectedEdge[wdg.vs()];
        marked = new boolean[wdg.vs()];
        Arrays.fill(marked, false);
        minHeap = new PriorityQueue<>();

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
        minHeap.offer(new Node(s, 0));
        while (!minHeap.isEmpty()) {
            Node node = minHeap.poll();
            if (!marked[node.v]) {
                relax(wdg, node.v);
            }
        }
    }

    /**
     * 放松，更新最短路径
     *
     * @param wdg 加权有向图
     * @param v   放松的顶点
     */
    private void relax(WeightedDigraph wdg, int v) {
        marked[v] = true;
        for (DirectedEdge e : wdg.adj(v)) {
            int w = e.to();
            if (!marked[w] && distTo[v] + e.weight() < distTo[w]) {
                distTo[w] = distTo[v] + e.weight();
                edgeFrom[w] = e;
                minHeap.offer(new Node(w, distTo[w]));
            }
        }
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

    private static class Node implements Comparable<Node> {
        /**
         * 顶点
         */
        int v;
        /**
         * 顶点距离
         */
        double dist;

        Node(int v, double dist) {
            this.v = v;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return Double.compare(dist, o.dist);
        }
    }

}
