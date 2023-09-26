package com.wjd.algorithm.graph.directed.order;

import com.wjd.algorithm.graph.Order;
import com.wjd.algorithm.graph.directed.build.impl.FileDigraphBuilder;
import com.wjd.structure.graph.directed.Digraph;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReversePostDFOTest {

    @Test
    void order() {
        Digraph dg = new FileDigraphBuilder("tinyDAG.txt").build();
        Order order = new ReversePostDFO(dg);

        List<Integer> actual = new ArrayList<>();
        for (int v : order.order()) {
            actual.add(v);
        }
        assertEquals("[8, 7, 2, 3, 0, 5, 1, 6, 9, 11, 10, 12, 4]", actual.toString());
    }
}