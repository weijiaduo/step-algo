package com.wjd.algorithm.graph.directed.order;

import com.wjd.algorithm.graph.Order;
import com.wjd.structure.graph.directed.Digraph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 基于深度优先的后序
 *
 * @author weijiaduo
 * @since 2023/3/11
 */
public class PostDFO implements Order {

    /**
     * 后序顺序
     */
    private final Queue<Integer> orders;
    /**
     * 标记数组
     */
    private final boolean[] marked;

    public PostDFO(Digraph dg) {
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
        orders.offer(v);
    }

    @Override
    public Iterable<Integer> order() {
        return orders;
    }

}
