package com.wjd.algorithm.graph.directed.order;

import com.wjd.algorithm.graph.Order;
import com.wjd.algorithm.graph.directed.build.impl.FileDigraphBuilder;
import com.wjd.structure.graph.directed.Digraph;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PreDFOTest {

    @Test
    void order() {
        Digraph dg = new FileDigraphBuilder("tinyDAG.txt").build();
        Order order = new PreDFO(dg);

        List<Integer> actual = new ArrayList<>();
        for (int v : order.order()) {
            actual.add(v);
        }
        assertEquals("[0, 6, 4, 9, 12, 10, 11, 1, 5, 2, 3, 7, 8]", actual.toString());
    }

}