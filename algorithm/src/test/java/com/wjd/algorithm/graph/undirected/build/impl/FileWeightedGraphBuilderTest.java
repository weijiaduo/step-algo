package com.wjd.algorithm.graph.undirected.build.impl;

import com.wjd.algorithm.graph.undirected.build.WeightedGraphBuilder;
import com.wjd.structure.graph.undirected.Edge;
import com.wjd.structure.graph.undirected.WeightedGraph;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FileWeightedGraphBuilderTest {

    @Test
    void build() {
        WeightedGraphBuilder builder = new FileWeightedGraphBuilder("tinyEWG.txt");
        WeightedGraph wg = builder.build();

        assertEquals(8, wg.vs());
        assertEquals(16, wg.es());

        Iterable<Edge> it = wg.adj(0);
        List<Edge> actual = new ArrayList<>();
        for (Edge i : it) {
            actual.add(i);
        }
        assertEquals("[(0, 7, 0.16), (0, 4, 0.38), (0, 2, 0.26), (6, 0, 0.58)]", actual.toString());

        it = wg.adj(1);
        actual = new ArrayList<>();
        for (Edge i : it) {
            actual.add(i);
        }
        assertEquals("[(1, 5, 0.32), (1, 7, 0.19), (1, 2, 0.36), (1, 3, 0.29)]", actual.toString());

        it = wg.adj(2);
        actual = new ArrayList<>();
        for (Edge i : it) {
            actual.add(i);
        }
        assertEquals("[(2, 3, 0.17), (0, 2, 0.26), (1, 2, 0.36), (2, 7, 0.34), (6, 2, 0.40)]", actual.toString());

        it = wg.adj(3);
        actual = new ArrayList<>();
        for (Edge i : it) {
            actual.add(i);
        }
        assertEquals("[(2, 3, 0.17), (1, 3, 0.29), (3, 6, 0.52)]", actual.toString());

        it = wg.adj(4);
        actual = new ArrayList<>();
        for (Edge i : it) {
            actual.add(i);
        }
        assertEquals("[(4, 5, 0.35), (4, 7, 0.37), (0, 4, 0.38), (6, 4, 0.93)]", actual.toString());

        it = wg.adj(5);
        actual = new ArrayList<>();
        for (Edge i : it) {
            actual.add(i);
        }
        assertEquals("[(4, 5, 0.35), (5, 7, 0.28), (1, 5, 0.32)]", actual.toString());

        it = wg.adj(6);
        actual = new ArrayList<>();
        for (Edge i : it) {
            actual.add(i);
        }
        assertEquals("[(6, 2, 0.40), (3, 6, 0.52), (6, 0, 0.58), (6, 4, 0.93)]", actual.toString());

        it = wg.adj(7);
        actual = new ArrayList<>();
        for (Edge i : it) {
            actual.add(i);
        }
        assertEquals("[(4, 7, 0.37), (5, 7, 0.28), (0, 7, 0.16), (1, 7, 0.19), (2, 7, 0.34)]", actual.toString());
    }
}