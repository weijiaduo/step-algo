package com.wjd.algorithm.graph.directed.cycle;

import com.wjd.algorithm.graph.Cycle;
import com.wjd.structure.graph.directed.Digraph;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 有向图的环检测实现
 *
 * @author weijiaduo
 * @since 2023/6/16
 */
public class MarkedDirectedCycle implements Cycle {

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
     * <p>
     * 等于 0 表示未访问；大于 0 表示正在访问；小于 0 表示已访问过
     */
    private final int[] marked;

    public MarkedDirectedCycle(Digraph dg) {
        edgeFrom = new int[dg.vs()];
        Arrays.fill(edgeFrom, -1);
        marked = new int[dg.vs()];
        hasCycle = false;
        s = -1;

        for (int v = 0; !hasCycle && v < dg.vs(); v++) {
            if (marked[v] == 0) {
                dfs(dg, v);
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
        marked[v] = 1;
        for (int w : dg.adj(v)) {
            if (marked[w] < 0) {
                continue;
            }
            edgeFrom[w] = v;
            if (marked[w] > 0) {
                hasCycle = true;
                s = v;
            } else {
                dfs(dg, w);
            }
            if (hasCycle) {
                return;
            }
        }
        marked[v] = -1;
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
