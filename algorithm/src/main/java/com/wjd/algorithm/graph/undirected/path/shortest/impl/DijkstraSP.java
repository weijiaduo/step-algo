package com.wjd.algorithm.graph.undirected.path.shortest.impl;

import com.wjd.algorithm.graph.undirected.path.shortest.ShortestPaths;
import com.wjd.structure.graph.undirected.Edge;
import com.wjd.structure.graph.undirected.WeightedGraph;

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
    private final Edge[] edgeFrom;
    /**
     * 标记数组
     */
    private final boolean[] marked;
    /**
     * 最小值堆
     */
    private final PriorityQueue<Node> minHeap;

    public DijkstraSP(WeightedGraph wg, int s) {
        distTo = new double[wg.vs()];
        Arrays.fill(distTo, Double.POSITIVE_INFINITY);
        edgeFrom = new Edge[wg.vs()];
        marked = new boolean[wg.vs()];
        Arrays.fill(marked, false);
        minHeap = new PriorityQueue<>();

        find(wg, s);
    }

    /**
     * 搜索单点最短路径
     *
     * @param wg 加权无向图
     * @param s  起点
     */
    private void find(WeightedGraph wg, int s) {
        distTo[s] = 0;
        minHeap.offer(new Node(s, 0));
        while (!minHeap.isEmpty()) {
            Node node = minHeap.poll();
            if (!marked[node.v]) {
                relax(wg, node.v);
            }
        }
    }

    /**
     * 放松，更新最短路径
     *
     * @param wg 加权无向图
     * @param v  放松的顶点
     */
    private void relax(WeightedGraph wg, int v) {
        marked[v] = true;
        for (Edge e : wg.adj(v)) {
            int w = e.other(v);
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
    public Iterable<Edge> pathTo(int v) {
        LinkedList<Edge> stack = new LinkedList<>();
        Edge edge = edgeFrom[v];
        while (edge != null) {
            int w = edge.other(v);
            stack.push(edge);
            v = w;
            edge = edgeFrom[v];
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
