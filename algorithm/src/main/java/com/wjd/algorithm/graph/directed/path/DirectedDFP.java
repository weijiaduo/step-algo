package com.wjd.algorithm.graph.directed.path;

import com.wjd.algorithm.graph.Paths;
import com.wjd.structure.graph.directed.Digraph;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 基于深度优先搜索实现的路径
 *
 * @author weijiaduo
 * @since 2023/3/11
 */
public class DirectedDFP implements Paths {

    /**
     * 边的终点->起点
     */
    private final int[] edgeFrom;
    /**
     * 标记数组
     */
    private final boolean[] marked;

    public DirectedDFP(Digraph dg, int s) {
        edgeFrom = new int[dg.vs()];
        Arrays.fill(edgeFrom, -1);
        marked = new boolean[dg.vs()];
        Arrays.fill(marked, false);
        dfs(dg, s);
    }

    /**
     * 深度搜索
     *
     * @param dg 有向图
     * @param v  当前顶点
     */
    private void dfs(Digraph dg, int v) {
        marked[v] = true;
        for (int w : dg.adj(v)) {
            if (!marked[w]) {
                edgeFrom[w] = v;
                dfs(dg, w);
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
