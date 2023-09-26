package com.wjd.algorithm.graph.undirected.path;

import com.wjd.algorithm.graph.Paths;
import com.wjd.structure.graph.undirected.Graph;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 基于深度优先搜索实现的路径
 *
 * @author weijiaduo
 * @since 2023/3/5
 */
public class DepthFirstPaths implements Paths {

    /**
     * 边的终点->起点
     */
    private final int[] edgeFrom;
    /**
     * 标记数组
     */
    private final boolean[] marked;

    /**
     * @param g 无向图
     * @param s 指定起点
     */
    public DepthFirstPaths(Graph g, int s) {
        edgeFrom = new int[g.vs()];
        Arrays.fill(edgeFrom, -1);
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
        for (int w : g.adj(v)) {
            if (!marked[w]) {
                edgeFrom[w] = v;
                dfs(g, w);
            }
        }
    }

    @Override
    public boolean hasPathTo(int v) {
        return marked[v];
    }

    @Override
    public Iterable<Integer> pathTo(int v) {
        LinkedList<Integer> stack = new LinkedList<>();
        for (int x = v; x != -1; x = edgeFrom[x]) {
            stack.push(x);
        }
        return stack;
    }

}
