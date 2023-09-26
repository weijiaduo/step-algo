package com.wjd.structure.graph.directed.impl;

import com.wjd.structure.graph.directed.DirectedEdge;
import com.wjd.structure.graph.directed.WeightedDigraph;
import com.wjd.structure.graph.util.DirectedEdgeBag;

/**
 * 基于邻接矩阵实现的加权有向图
 *
 * @author weijiaduo
 * @since 2023/3/15
 */
public class MatrixWeightedDigraph implements WeightedDigraph {

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
    private final DirectedEdge[][] adj;

    public MatrixWeightedDigraph(int vs) {
        this.vs = vs;
        this.es = 0;
        adj = new DirectedEdge[vs][vs];
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
    public void addEdge(DirectedEdge edge) {
        int v = edge.from();
        int w = edge.to();
        if (hasEdge(v, w)) {
            return;
        }
        adj[v][w] = edge;
        es++;
    }

    @Override
    public boolean hasEdge(int v, int w) {
        return adj[v][w] != null;
    }

    @Override
    public Iterable<DirectedEdge> adj(int v) {
        DirectedEdgeBag bag = new DirectedEdgeBag();
        for (DirectedEdge e : adj[v]) {
            if (e != null) {
                bag.add(e);
            }
        }
        return bag;
    }

    @Override
    public Iterable<DirectedEdge> edges() {
        DirectedEdgeBag bag = new DirectedEdgeBag();
        for (int v = 0; v < vs; v++) {
            for (DirectedEdge e : adj[v]) {
                if (e != null) {
                    bag.add(e);
                }
            }
        }
        return bag;
    }

}
