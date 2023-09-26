package com.wjd.algorithm.graph.directed.search;

import com.wjd.algorithm.graph.Search;
import com.wjd.structure.graph.directed.Digraph;

import java.util.Arrays;

/**
 * 深度优先搜索
 *
 * @author weijiaduo
 * @since 2023/3/5
 */
public class DirectedDFS implements Search {

    /**
     * 标记数组
     */
    private final boolean[] marked;
    /**
     * 遍历到的顶点数量
     */
    private int count;

    public DirectedDFS(Digraph dg, int s) {
        marked = new boolean[dg.vs()];
        Arrays.fill(marked, false);
        dfs(dg, s);
    }

    /**
     * 深度搜索
     *
     * @param dg 无向图
     * @param v  当前顶点
     */
    private void dfs(Digraph dg, int v) {
        marked[v] = true;
        count++;
        for (int w : dg.adj(v)) {
            if (!marked[w]) {
                dfs(dg, w);
            }
        }
    }

    @Override
    public boolean marked(int v) {
        return marked[v];
    }

    @Override
    public int count() {
        return count;
    }

}
