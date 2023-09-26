package com.wjd.structure.graph.undirected.impl;

import com.wjd.structure.graph.undirected.Edge;
import com.wjd.structure.graph.undirected.WeightedGraph;
import com.wjd.structure.graph.util.EdgeBag;

/**
 * 基于邻接表实现的加权无向图
 *
 * @author weijiaduo
 * @since 2023/3/12
 */
public class ListWeightedGraph implements WeightedGraph {

    /**
     * 顶点数量
     */
    private final int vs;
    /**
     * 边数量
     */
    private int es;
    /**
     * 邻接表
     */
    private final EdgeBag[] adj;

    public ListWeightedGraph(int vs) {
        this.vs = vs;
        this.es = 0;
        adj = new EdgeBag[vs];
        for (int i = 0; i < vs; i++) {
            adj[i] = new EdgeBag();
        }
    }

    @Override
    public int vs() {
        return vs;
    }

    @Override
    public int es() {
        return es;
    }

    @Override
    public void addEdge(Edge edge) {
        int v = edge.eight();
        int w = edge.other(v);
        if (hasEdge(v, w)) {
            return;
        }
        adj[v].add(edge);
        adj[w].add(edge);
        es++;
    }

    @Override
    public boolean hasEdge(int v, int w) {
        for (Edge e : adj[v]) {
            if (e.contains(w)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterable<Edge> adj(int v) {
        return adj[v];
    }

    @Override
    public Iterable<Edge> edges() {
        EdgeBag bag = new EdgeBag();
        for (int v = 0; v < vs; v++) {
            for (Edge e : adj[v]) {
                // 无向图是双向的，要去重
                if (e.other(v) > v) {
                    bag.add(e);
                }
            }
        }
        return bag;
    }

}
