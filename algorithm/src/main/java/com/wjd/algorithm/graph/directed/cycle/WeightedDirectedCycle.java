package com.wjd.algorithm.graph.directed.cycle;

import com.wjd.algorithm.graph.Cycle;
import com.wjd.structure.graph.directed.DirectedEdge;
import com.wjd.structure.graph.directed.WeightedDigraph;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 加权有向图的环检测实现
 *
 * @author weijiaduo
 * @since 2023/3/15
 */
public class WeightedDirectedCycle implements Cycle {

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

    public WeightedDirectedCycle(WeightedDigraph wdg) {
        edgeFrom = new int[wdg.vs()];
        Arrays.fill(edgeFrom, -1);
        marked = new boolean[wdg.vs()];
        Arrays.fill(marked, false);
        onStack = new boolean[wdg.vs()];
        Arrays.fill(onStack, false);
        hasCycle = false;
        s = -1;

        for (int v = 0; !hasCycle && v < wdg.vs(); v++) {
            if (!marked[v]) {
                dfs(wdg, v);
            }
        }
    }

    /**
     * 深度搜索
     *
     * @param wdg 有向图
     * @param v   当前顶点
     */
    private void dfs(WeightedDigraph wdg, int v) {
        onStack[v] = true;
        marked[v] = true;
        for (DirectedEdge edge : wdg.adj(v)) {
            int w = edge.to();
            if (hasCycle) {
                break;
            }
            edgeFrom[w] = v;
            if (onStack[w]) {
                hasCycle = true;
                s = v;
                break;
            }
            dfs(wdg, w);
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
