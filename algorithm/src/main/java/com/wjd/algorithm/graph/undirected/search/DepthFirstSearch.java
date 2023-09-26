package com.wjd.algorithm.graph.undirected.search;

import com.wjd.algorithm.graph.Search;
import com.wjd.structure.graph.undirected.Graph;

import java.util.Arrays;

/**
 * 深度优先搜索
 *
 * @author weijiaduo
 * @since 2023/3/5
 */
public class DepthFirstSearch implements Search {

    /**
     * 标记数组
     */
    private final boolean[] marked;
    /**
     * 遍历到的顶点数量
     */
    private int count;

    public DepthFirstSearch(Graph g, int s) {
        marked = new boolean[g.vs()];
        Arrays.fill(marked, false);
        dfs(g, s);
    }

    /**
     * 深度搜索
     *
     * @param g 无向图
     * @param v 当前顶点
     */
    private void dfs(Graph g, int v) {
        marked[v] = true;
        count++;
        for (int w : g.adj(v)) {
            if (!marked[w]) {
                dfs(g, w);
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
