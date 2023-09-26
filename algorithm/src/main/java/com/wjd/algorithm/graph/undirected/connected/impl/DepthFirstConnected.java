package com.wjd.algorithm.graph.undirected.connected.impl;

import com.wjd.algorithm.graph.undirected.connected.Connected;
import com.wjd.structure.graph.undirected.Graph;

import java.util.Arrays;

/**
 * 连通分量实现
 *
 * @author weijiaduo
 * @since 2023/3/5
 */
public class DepthFirstConnected implements Connected {

    /**
     * 标记数组
     */
    private final boolean[] marked;
    /**
     * 顶点->所属连通分量
     */
    private final int[] id;
    /**
     * 当前标识符
     */
    private int count;

    public DepthFirstConnected(Graph g) {
        marked = new boolean[g.vs()];
        Arrays.fill(marked, false);
        id = new int[g.vs()];
        Arrays.fill(id, -1);
        count = 0;

        for (int v = 0; v < g.vs(); v++) {
            if (marked[v]) {
                continue;
            }
            dfs(g, v);
            count++;
        }
    }

    /**
     * 深度搜索
     *
     * @param g 无向图
     * @param v 当前顶点
     */
    private void dfs(Graph g, int v) {
        marked[v] = true;
        id[v] = count;
        for (int w : g.adj(v)) {
            if (!marked[w]) {
                dfs(g, w);
            }
        }
    }

    @Override
    public boolean connected(int v, int w) {
        return id[v] == id[w];
    }

    @Override
    public int count() {
        return count;
    }

    @Override
    public int id(int v) {
        return id[v];
    }

}
