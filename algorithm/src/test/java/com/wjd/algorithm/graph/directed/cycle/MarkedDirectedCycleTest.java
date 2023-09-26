package com.wjd.algorithm.graph.directed.cycle;

import com.wjd.algorithm.graph.Cycle;
import com.wjd.algorithm.graph.directed.build.impl.FileDigraphBuilder;
import com.wjd.structure.graph.directed.Digraph;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MarkedDirectedCycleTest {

    @Test
    void hasCycle() {
        Digraph dg = new FileDigraphBuilder("tinyDG.txt").build();
        Cycle cycle = new MarkedDirectedCycle(dg);
        assertTrue(cycle.hasCycle());
    }

    @Test
    void cycle() {
        Digraph dg = new FileDigraphBuilder("tinyDG.txt").build();
        Cycle cycle = new MarkedDirectedCycle(dg);

        List<Integer> actual = new ArrayList<>();
        for (int v : cycle.cycle()) {
            actual.add(v);
        }
        assertEquals("[2, 3]", actual.toString());
    }

}