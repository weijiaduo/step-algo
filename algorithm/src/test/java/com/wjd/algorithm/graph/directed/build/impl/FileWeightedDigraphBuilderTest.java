package com.wjd.algorithm.graph.directed.build.impl;

import com.wjd.algorithm.graph.directed.build.WeightedDigraphBuilder;
import com.wjd.structure.graph.directed.DirectedEdge;
import com.wjd.structure.graph.directed.WeightedDigraph;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FileWeightedDigraphBuilderTest {

    @Test
    void build() {
        WeightedDigraphBuilder builder = new FileWeightedDigraphBuilder("tinyEWD.txt");
        WeightedDigraph wdg = builder.build();

        assertEquals(8, wdg.vs());
        assertEquals(15, wdg.es());

        Iterable<DirectedEdge> it = wdg.adj(0);
        List<DirectedEdge> actual = new ArrayList<>();
        for (DirectedEdge i : it) {
            actual.add(i);
        }
        assertEquals("[(0, 4, 0.38), (0, 2, 0.26)]", actual.toString());

        it = wdg.adj(1);
        actual = new ArrayList<>();
        for (DirectedEdge i : it) {
            actual.add(i);
        }
        assertEquals("[(1, 3, 0.29)]", actual.toString());

        it = wdg.adj(2);
        actual = new ArrayList<>();
        for (DirectedEdge i : it) {
            actual.add(i);
        }
        assertEquals("[(2, 7, 0.34)]", actual.toString());

        it = wdg.adj(3);
        actual = new ArrayList<>();
        for (DirectedEdge i : it) {
            actual.add(i);
        }
        assertEquals("[(3, 6, 0.52)]", actual.toString());

        it = wdg.adj(4);
        actual = new ArrayList<>();
        for (DirectedEdge i : it) {
            actual.add(i);
        }
        assertEquals("[(4, 5, 0.35), (4, 7, 0.37)]", actual.toString());

        it = wdg.adj(5);
        actual = new ArrayList<>();
        for (DirectedEdge i : it) {
            actual.add(i);
        }
        assertEquals("[(5, 4, 0.35), (5, 7, 0.28), (5, 1, 0.32)]", actual.toString());

        it = wdg.adj(6);
        actual = new ArrayList<>();
        for (DirectedEdge i : it) {
            actual.add(i);
        }
        assertEquals("[(6, 2, 0.40), (6, 0, 0.58), (6, 4, 0.93)]", actual.toString());

        it = wdg.adj(7);
        actual = new ArrayList<>();
        for (DirectedEdge i : it) {
            actual.add(i);
        }
        assertEquals("[(7, 5, 0.28), (7, 3, 0.39)]", actual.toString());
    }
}