package com.wjd.algorithm.graph.directed.connected.impl;

import com.wjd.algorithm.graph.directed.connected.StrongConnected;
import com.wjd.algorithm.graph.directed.order.ReversePostDFO;
import com.wjd.structure.graph.directed.Digraph;

import java.util.Arrays;

/**
 * Kosaraju 强连通分量实现
 *
 * @author weijiaduo
 * @since 2023/3/12
 */
public class KosarajuStrongConnected implements StrongConnected {

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

    public KosarajuStrongConnected(Digraph dg) {
        marked = new boolean[dg.vs()];
        Arrays.fill(marked, false);
        id = new int[dg.vs()];
        Arrays.fill(id, -1);
        count = 0;

        // 按逆向图的逆后序，进行深度优先遍历
        Iterable<Integer> rpo = new ReversePostDFO(dg.reverse()).order();
        for (Integer v : rpo) {
            if (!marked[v]) {
                dfs(dg, v);
                count++;
            }
        }
    }

    /**
     * 深度搜索
     *
     * @param dg 有向图
     * @param v  当前顶点
     */
    private void dfs(Digraph dg, int v) {
        marked[v] = true;
        id[v] = count;
        for (int w : dg.adj(v)) {
            if (!marked[w]) {
                dfs(dg, w);
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
