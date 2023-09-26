package com.wjd.algorithm.graph.undirected.cycle;

import com.wjd.algorithm.graph.Cycle;
import com.wjd.structure.graph.undirected.Graph;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 无向图的环检测实现
 *
 * @author weijiaduo
 * @since 2023/3/5
 */
public class UndirectedCycle implements Cycle {

    /**
     * 是否有环
     */
    private boolean hasCycle;
    /**
     * 环上的一个顶点（有环时才有，任意一个顶点）
     */
    private int s;
    /**
     * 边的终点->起点
     */
    private final int[] edgeFrom;
    /**
     * 标记数组
     */
    private final boolean[] marked;

    public UndirectedCycle(Graph g) {
        edgeFrom = new int[g.vs()];
        Arrays.fill(edgeFrom, -1);
        marked = new boolean[g.vs()];
        Arrays.fill(marked, false);
        hasCycle = false;
        s = -1;

        for (int v = 0; !hasCycle && v < g.vs(); v++) {
            if (!marked[v]) {
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
        marked[v] = true;
        for (int w : g.adj(v)) {
            if (hasCycle) {
                break;
            }
            if (w == u) {
                continue;
            }
            edgeFrom[w] = v;
            if (marked[w]) {
                hasCycle = true;
                s = v;
                break;
            }
            dfs(g, w, v);
        }
    }

    @Override
    public boolean hasCycle() {
        return hasCycle;
    }

    @Override
    public Iterable<Integer> cycle() {
        if (!hasCycle) {
            return null;
        }

        LinkedList<Integer> stack = new LinkedList<>();
        stack.push(s);
        for (int x = edgeFrom[s]; x != s; x = edgeFrom[x]) {
            stack.push(x);
        }
        return stack;
    }

}
