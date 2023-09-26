package com.wjd.algorithm.graph.directed.path.longest.impl;

import com.wjd.algorithm.graph.directed.build.impl.FileWeightedDigraphBuilder;
import com.wjd.algorithm.graph.directed.path.longest.LongestPaths;
import com.wjd.structure.graph.directed.DirectedEdge;
import com.wjd.structure.graph.directed.WeightedDigraph;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AcyclicLPTest {

    @Test
    void hasPathTo() {
        WeightedDigraph wdg = new FileWeightedDigraphBuilder("tinyEWDAG.txt").build();
        LongestPaths lp = new AcyclicLP(wdg, 5);

        assertTrue(lp.hasPathTo(0));
        assertTrue(lp.hasPathTo(1));
        assertTrue(lp.hasPathTo(2));
        assertTrue(lp.hasPathTo(3));
        assertTrue(lp.hasPathTo(4));
        assertTrue(lp.hasPathTo(5));
        assertTrue(lp.hasPathTo(6));
        assertTrue(lp.hasPathTo(7));
    }

    @Test
    void distTo() {
        WeightedDigraph wdg = new FileWeightedDigraphBuilder("tinyEWDAG.txt").build();
        LongestPaths lp = new AcyclicLP(wdg, 5);

        assertEquals("2.44", String.format("%.2f", lp.distTo(0)));
        assertEquals("0.32", String.format("%.2f", lp.distTo(1)));
        assertEquals("2.77", String.format("%.2f", lp.distTo(2)));
        assertEquals("0.61", String.format("%.2f", lp.distTo(3)));
        assertEquals("2.06", String.format("%.2f", lp.distTo(4)));
        assertEquals("0.00", String.format("%.2f", lp.distTo(5)));
        assertEquals("1.13", String.format("%.2f", lp.distTo(6)));
        assertEquals("2.43", String.format("%.2f", lp.distTo(7)));
    }

    @Test
    void pathTo() {
        WeightedDigraph wdg = new FileWeightedDigraphBuilder("tinyEWDAG.txt").build();
        LongestPaths lp = new AcyclicLP(wdg, 5);

        Iterable<DirectedEdge> it = lp.pathTo(0);
        List<DirectedEdge> actual = new ArrayList<>();
        for (DirectedEdge i : it) {
            actual.add(i);
        }
        assertEquals("[(5, 1, 0.32), (1, 3, 0.29), (3, 6, 0.52), (6, 4, 0.93), (4, 0, 0.38)]", actual.toString());

        it = lp.pathTo(1);
        actual = new ArrayList<>();
        for (DirectedEdge i : it) {
            actual.add(i);
        }
        assertEquals("[(5, 1, 0.32)]", actual.toString());

        it = lp.pathTo(2);
        actual = new ArrayList<>();
        for (DirectedEdge i : it) {
            actual.add(i);
        }
        assertEquals("[(5, 1, 0.32), (1, 3, 0.29), (3, 6, 0.52), (6, 4, 0.93), (4, 7, 0.37), (7, 2, 0.34)]", actual.toString());

        it = lp.pathTo(3);
        actual = new ArrayList<>();
        for (DirectedEdge i : it) {
            actual.add(i);
        }
        assertEquals("[(5, 1, 0.32), (1, 3, 0.29)]", actual.toString());

        it = lp.pathTo(4);
        actual = new ArrayList<>();
        for (DirectedEdge i : it) {
            actual.add(i);
        }
        assertEquals("[(5, 1, 0.32), (1, 3, 0.29), (3, 6, 0.52), (6, 4, 0.93)]", actual.toString());

        it = lp.pathTo(6);
        actual = new ArrayList<>();
        for (DirectedEdge i : it) {
            actual.add(i);
        }
        assertEquals("[(5, 1, 0.32), (1, 3, 0.29), (3, 6, 0.52)]", actual.toString());

        it = lp.pathTo(7);
        actual = new ArrayList<>();
        for (DirectedEdge i : it) {
            actual.add(i);
        }
        assertEquals("[(5, 1, 0.32), (1, 3, 0.29), (3, 6, 0.52), (6, 4, 0.93), (4, 7, 0.37)]", actual.toString());
    }
}