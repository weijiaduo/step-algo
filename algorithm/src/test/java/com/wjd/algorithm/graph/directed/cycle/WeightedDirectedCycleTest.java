package com.wjd.algorithm.graph.directed.cycle;

import com.wjd.algorithm.graph.Cycle;
import com.wjd.algorithm.graph.directed.build.impl.FileWeightedDigraphBuilder;
import com.wjd.structure.graph.directed.WeightedDigraph;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WeightedDirectedCycleTest {

    @Test
    void hasCycle() {
        WeightedDigraph wdg = new FileWeightedDigraphBuilder("tinyEWD.txt").build();
        Cycle cycle = new WeightedDirectedCycle(wdg);
        assertTrue(cycle.hasCycle());

        wdg = new FileWeightedDigraphBuilder("tinyEWDAG.txt").build();
        cycle = new WeightedDirectedCycle(wdg);
        assertFalse(cycle.hasCycle());
    }

    @Test
    void cycle() {
        WeightedDigraph wdg = new FileWeightedDigraphBuilder("tinyEWD.txt").build();
        Cycle cycle = new WeightedDirectedCycle(wdg);

        List<Integer> actual = new ArrayList<>();
        for (int v : cycle.cycle()) {
            actual.add(v);
        }
        assertEquals("[4, 5]", actual.toString());
    }

}