package com.wjd.structure.graph.directed.impl;

import com.wjd.structure.graph.directed.Digraph;
import com.wjd.structure.graph.util.IntBag;

/**
 * 基于邻接矩阵实现的有向图
 *
 * @author weijiaduo
 * @since 2023/3/7
 */
public class MatrixDigraph implements Digraph {

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
    private final boolean[][] adj;

    public MatrixDigraph(int vs) {
        this.vs = vs;
        adj = new boolean[vs][vs];
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
    public void addEdge(int v, int w) {
        if (hasEdge(v, w)) {
            return;
        }
        adj[v][w] = true;
        es++;
    }

    @Override
    public boolean hasEdge(int v, int w) {
        return adj[v][w];
    }

    @Override
    public Iterable<Integer> adj(int v) {
        IntBag bag = new IntBag();
        for (int i = 0; i < vs; i++) {
            if (adj[v][i]) {
                bag.add(i);
            }
        }
        return bag;
    }

    @Override
    public Digraph reverse() {
        Digraph rdg = new MatrixDigraph(vs);
        for (int v = 0; v < vs; v++) {
            for (int w = 0; w < vs; w++) {
                if (adj[v][w]) {
                    rdg.addEdge(w, v);
                }
            }
        }
        return rdg;
    }
}
