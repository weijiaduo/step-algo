package com.wjd.algorithm.graph.undirected.bipartite.impl;

import com.wjd.algorithm.graph.undirected.bipartite.Bipartite;
import com.wjd.structure.graph.undirected.Graph;

import java.util.Arrays;

/**
 * 二分图检测实现
 *
 * @author weijiaduo
 * @since 2023/3/5
 */
public class BipartiteImpl implements Bipartite {

    /**
     * 是否可以二分
     */
    private boolean isBipartite;
    /**
     * 标记数组，顶点->所属分区标志符
     */
    private final int[] marked;

    public BipartiteImpl(Graph g) {
        marked = new int[g.vs()];
        Arrays.fill(marked, -1);
        isBipartite = true;

        for (int v = 0; isBipartite && v < g.vs(); v++) {
            if (marked[v] == -1) {
                marked[v] = 1;
                dfs(g, v, v);
            }
        }
    }

    /**
     * 深度搜索
     *
     * @param g 无向图
     * @param v 当前顶点
     * @param u 上个顶点
     */
    private void dfs(Graph g, int v, int u) {
        marked[v] = 1 - marked[u];
        for (int w : g.adj(v)) {
            if (!isBipartite) {
                break;
            }
            if (w == u) {
                continue;
            }
            if (marked[w] != -1) {
                if (marked[w] == marked[v]) {
                    isBipartite = false;
                    break;
                }
            } else {
                dfs(g, w, v);
            }
        }
    }

    @Override
    public boolean isBipartite() {
        return isBipartite;
    }

    @Override
    public int id(int v) {
        if (!isBipartite) {
            return -1;
        }
        return marked[v];
    }

}
