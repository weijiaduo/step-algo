package com.wjd.algorithm.graph.directed.path.shortest.impl;

import com.wjd.algorithm.graph.directed.build.impl.FileWeightedDigraphBuilder;
import com.wjd.algorithm.graph.directed.path.shortest.ShortestPaths;
import com.wjd.structure.graph.directed.DirectedEdge;
import com.wjd.structure.graph.directed.WeightedDigraph;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AcyclicSPTest {

    @Test
    void hasPathTo() {
        WeightedDigraph wdg = new FileWeightedDigraphBuilder("tinyEWDAG.txt").build();
        ShortestPaths sp = new AcyclicSP(wdg, 5);

        assertTrue(sp.hasPathTo(0));
        assertTrue(sp.hasPathTo(1));
        assertTrue(sp.hasPathTo(2));
        assertTrue(sp.hasPathTo(3));
        assertTrue(sp.hasPathTo(4));
        assertTrue(sp.hasPathTo(5));
        assertTrue(sp.hasPathTo(6));
        assertTrue(sp.hasPathTo(7));
    }

    @Test
    void distTo() {
        WeightedDigraph wdg = new FileWeightedDigraphBuilder("tinyEWDAG.txt").build();
        ShortestPaths sp = new AcyclicSP(wdg, 5);

        assertEquals("0.73", String.format("%.2f", sp.distTo(0)));
        assertEquals("0.32", String.format("%.2f", sp.distTo(1)));
        assertEquals("0.62", String.format("%.2f", sp.distTo(2)));
        assertEquals("0.61", String.format("%.2f", sp.distTo(3)));
        assertEquals("0.35", String.format("%.2f", sp.distTo(4)));
        assertEquals("0.00", String.format("%.2f", sp.distTo(5)));
        assertEquals("1.13", String.format("%.2f", sp.distTo(6)));
        assertEquals("0.28", String.format("%.2f", sp.distTo(7)));
    }

    @Test
    void pathTo() {
        WeightedDigraph wdg = new FileWeightedDigraphBuilder("tinyEWDAG.txt").build();
        ShortestPaths sp = new AcyclicSP(wdg, 5);

        Iterable<DirectedEdge> it = sp.pathTo(0);
        List<DirectedEdge> actual = new ArrayList<>();
        for (DirectedEdge i : it) {
            actual.add(i);
        }
        assertEquals("[(5, 4, 0.35), (4, 0, 0.38)]", actual.toString());

        it = sp.pathTo(1);
        actual = new ArrayList<>();
        for (DirectedEdge i : it) {
            actual.add(i);
        }
        assertEquals("[(5, 1, 0.32)]", actual.toString());

        it = sp.pathTo(2);
        actual = new ArrayList<>();
        for (DirectedEdge i : it) {
            actual.add(i);
        }
        assertEquals("[(5, 7, 0.28), (7, 2, 0.34)]", actual.toString());

        it = sp.pathTo(3);
        actual = new ArrayList<>();
        for (DirectedEdge i : it) {
            actual.add(i);
        }
        assertEquals("[(5, 1, 0.32), (1, 3, 0.29)]", actual.toString());

        it = sp.pathTo(4);
        actual = new ArrayList<>();
        for (DirectedEdge i : it) {
            actual.add(i);
        }
        assertEquals("[(5, 4, 0.35)]", actual.toString());

        it = sp.pathTo(6);
        actual = new ArrayList<>();
        for (DirectedEdge i : it) {
            actual.add(i);
        }
        assertEquals("[(5, 1, 0.32), (1, 3, 0.29), (3, 6, 0.52)]", actual.toString());

        it = sp.pathTo(7);
        actual = new ArrayList<>();
        for (DirectedEdge i : it) {
            actual.add(i);
        }
        assertEquals("[(5, 7, 0.28)]", actual.toString());
    }

}