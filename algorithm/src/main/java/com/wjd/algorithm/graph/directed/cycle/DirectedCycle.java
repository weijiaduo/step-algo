package com.wjd.algorithm.graph.directed.cycle;

import com.wjd.algorithm.graph.Cycle;
import com.wjd.structure.graph.directed.Digraph;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 有向图的环检测实现
 *
 * @author weijiaduo
 * @since 2023/3/10
 */
public class DirectedCycle implements Cycle {

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
    /**
     * 栈数组
     */
    private final boolean[] onStack;

    public DirectedCycle(Digraph dg) {
        edgeFrom = new int[dg.vs()];
        Arrays.fill(edgeFrom, -1);
        marked = new boolean[dg.vs()];
        Arrays.fill(marked, false);
        onStack = new boolean[dg.vs()];
        Arrays.fill(onStack, false);
        hasCycle = false;
        s = -1;

        for (int v = 0; !hasCycle && v < dg.vs(); v++) {
            if (!marked[v]) {
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
        onStack[v] = true;
        marked[v] = true;
        for (int w : dg.adj(v)) {
            if (hasCycle) {
                break;
            }
            edgeFrom[w] = v;
            if (onStack[w]) {
                hasCycle = true;
                s = v;
                break;
            }
            dfs(dg, w);
        }
        onStack[v] = false;
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
