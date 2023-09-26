package com.wjd.algorithm.graph.directed.order;

import com.wjd.algorithm.graph.Order;
import com.wjd.structure.graph.directed.Digraph;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 基于深度优先的逆后序
 *
 * @author weijiaduo
 * @since 2023/3/11
 */
public class ReversePostDFO implements Order {

    /**
     * 逆后序顺序
     */
    private final Deque<Integer> orders;
    /**
     * 标记数组
     */
    private final boolean[] marked;

    public ReversePostDFO(Digraph dg) {
        orders = new LinkedList<>();
        marked = new boolean[dg.vs()];
        Arrays.fill(marked, false);
        for (int v = 0; v < dg.vs(); v++) {
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
        marked[v] = true;
        for (int w : dg.adj(v)) {
            if (!marked[w]) {
                dfs(dg, w);
            }
        }
        orders.push(v);
    }

    @Override
    public Iterable<Integer> order() {
        return orders;
    }

}
