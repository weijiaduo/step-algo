package com.wjd.algorithm.graph.directed.order;

import com.wjd.algorithm.graph.Order;
import com.wjd.algorithm.graph.directed.cycle.DirectedCycle;
import com.wjd.algorithm.graph.directed.cycle.WeightedDirectedCycle;
import com.wjd.structure.graph.directed.Digraph;
import com.wjd.structure.graph.directed.WeightedDigraph;

/**
 * 拓扑排序（基于深度优先的逆后序）
 *
 * @author weijiaduo
 * @since 2023/3/10
 */
public class Topological implements Order {

    /**
     * 拓扑顺序
     */
    private Iterable<Integer> orders;

    public Topological(Digraph dg) {
        if (new DirectedCycle(dg).hasCycle()) {
            return;
        }
        orders = new ReversePostDFO(dg).order();
    }

    public Topological(WeightedDigraph wdg) {
        if (new WeightedDirectedCycle(wdg).hasCycle()) {
            return;
        }
        orders = new WeightedReversePostDFO(wdg).order();
    }

    @Override
    public Iterable<Integer> order() {
        return orders;
    }

    public boolean isDAG() {
        return orders != null;
    }

}
