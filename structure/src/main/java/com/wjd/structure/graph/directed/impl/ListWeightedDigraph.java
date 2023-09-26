package com.wjd.structure.graph.directed.impl;

import com.wjd.structure.graph.directed.DirectedEdge;
import com.wjd.structure.graph.directed.WeightedDigraph;
import com.wjd.structure.graph.util.DirectedEdgeBag;

/**
 * 基于邻接表实现的加权有向图
 *
 * @author weijiaduo
 * @since 2023/3/15
 */
public class ListWeightedDigraph implements WeightedDigraph {

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
    private final DirectedEdgeBag[] adj;

    public ListWeightedDigraph(int vs) {
        this.vs = vs;
        this.es = 0;
        adj = new DirectedEdgeBag[vs];
        for (int i = 0; i < vs; i++) {
            adj[i] = new DirectedEdgeBag();
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
    public void addEdge(DirectedEdge edge) {
        if (hasEdge(edge.from(), edge.to())) {
            return;
        }
        adj[edge.from()].add(edge);
        es++;
    }

    @Override
    public boolean hasEdge(int v, int w) {
        for (DirectedEdge e : adj[v]) {
            if (e.to() == w) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterable<DirectedEdge> adj(int v) {
        return adj[v];
    }

    @Override
    public Iterable<DirectedEdge> edges() {
        DirectedEdgeBag bag = new DirectedEdgeBag();
        for (int v = 0; v < vs; v++) {
            for (DirectedEdge e : adj[v]) {
                bag.add(e);
            }
        }
        return bag;
    }

}
