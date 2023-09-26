package com.wjd.structure.graph.undirected.impl;

import com.wjd.structure.graph.undirected.Edge;
import com.wjd.structure.graph.undirected.WeightedGraph;
import com.wjd.structure.graph.util.EdgeBag;

/**
 * 基于邻接矩阵实现的加权无向图
 *
 * @author weijiaduo
 * @since 2023/3/12
 */
public class MatrixWeightedGraph implements WeightedGraph {

    /**
     * 顶点数量
     */
    private final int vs;
    /**
     * 边数量
     */
    private int es;

    /**
     * 邻接矩阵
     */
    private final Edge[][] adj;

    public MatrixWeightedGraph(int vs) {
        this.vs = vs;
        adj = new Edge[vs][vs];
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
        adj[v][w] = edge;
        adj[w][v] = edge;
        es++;
    }

    @Override
    public boolean hasEdge(int v, int w) {
        return adj[v][w] != null;
    }

    @Override
    public Iterable<Edge> adj(int v) {
        EdgeBag bag = new EdgeBag();
        for (Edge e : adj[v]) {
            if (e != null) {
                bag.add(e);
            }
        }
        return bag;
    }

    @Override
    public Iterable<Edge> edges() {
        EdgeBag bag = new EdgeBag();
        for (int v = 0; v < vs; v++) {
            for (Edge e : adj[v]) {
                // 无向图是双向的，要去重
                if (e != null && e.other(v) > v) {
                    bag.add(e);
                }
            }
        }
        return bag;
    }

}
