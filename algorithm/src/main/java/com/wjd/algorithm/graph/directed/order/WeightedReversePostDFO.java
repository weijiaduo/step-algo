package com.wjd.algorithm.graph.directed.order;

import com.wjd.algorithm.graph.Order;
import com.wjd.structure.graph.directed.DirectedEdge;
import com.wjd.structure.graph.directed.WeightedDigraph;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 基于深度优先的逆后序
 *
 * @author weijiaduo
 * @since 2023/3/15
 */
public class WeightedReversePostDFO implements Order {

    /**
     * 逆后序顺序
     */
    private final Deque<Integer> orders;
    /**
     * 标记数组
     */
    private final boolean[] marked;

    public WeightedReversePostDFO(WeightedDigraph wdg) {
        orders = new LinkedList<>();
        marked = new boolean[wdg.vs()];
        Arrays.fill(marked, false);
        for (int v = 0; v < wdg.vs(); v++) {
            if (!marked[v]) {
                dfs(wdg, v);
            }
        }
    }

    /**
     * 深度搜索
     *
     * @param wdg 加权有向图
     * @param v   当前顶点
     */
    private void dfs(WeightedDigraph wdg, int v) {
        marked[v] = true;
        for (DirectedEdge e : wdg.adj(v)) {
            int w = e.to();
            if (!marked[w]) {
                dfs(wdg, w);
            }
        }
        orders.push(v);
    }

    @Override
    public Iterable<Integer> order() {
        return orders;
    }

}
