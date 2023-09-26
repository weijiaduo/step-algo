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

class DijkstraSPTest {

    @Test
    void hasPathTo() {
        WeightedDigraph wdg = new FileWeightedDigraphBuilder("tinyEWD.txt").build();
        ShortestPaths spt = new DijkstraSP(wdg, 0);

        assertTrue(spt.hasPathTo(0));
        assertTrue(spt.hasPathTo(1));
        assertTrue(spt.hasPathTo(2));
        assertTrue(spt.hasPathTo(3));
        assertTrue(spt.hasPathTo(4));
        assertTrue(spt.hasPathTo(5));
        assertTrue(spt.hasPathTo(6));
        assertTrue(spt.hasPathTo(7));
    }

    @Test
    void distTo() {
        WeightedDigraph wdg = new FileWeightedDigraphBuilder("tinyEWD.txt").build();
        ShortestPaths spt = new DijkstraSP(wdg, 0);

        assertEquals("0.00", String.format("%.2f", spt.distTo(0)));
        assertEquals("1.05", String.format("%.2f", spt.distTo(1)));
        assertEquals("0.26", String.format("%.2f", spt.distTo(2)));
        assertEquals("0.99", String.format("%.2f", spt.distTo(3)));
        assertEquals("0.38", String.format("%.2f", spt.distTo(4)));
        assertEquals("0.73", String.format("%.2f", spt.distTo(5)));
        assertEquals("1.51", String.format("%.2f", spt.distTo(6)));
        assertEquals("0.60", String.format("%.2f", spt.distTo(7)));
    }

    @Test
    void pathTo() {
        WeightedDigraph wdg = new FileWeightedDigraphBuilder("tinyEWD.txt").build();
        ShortestPaths spt = new DijkstraSP(wdg, 0);

        Iterable<DirectedEdge> it = spt.pathTo(1);
        List<DirectedEdge> actual = new ArrayList<>();
        for (DirectedEdge i : it) {
            actual.add(i);
        }
        assertEquals("[(0, 4, 0.38), (4, 5, 0.35), (5, 1, 0.32)]", actual.toString());

        it = spt.pathTo(2);
        actual = new ArrayList<>();
        for (DirectedEdge i : it) {
            actual.add(i);
        }
        assertEquals("[(0, 2, 0.26)]", actual.toString());

        it = spt.pathTo(3);
        actual = new ArrayList<>();
        for (DirectedEdge i : it) {
            actual.add(i);
        }
        assertEquals("[(0, 2, 0.26), (2, 7, 0.34), (7, 3, 0.39)]", actual.toString());

        it = spt.pathTo(4);
        actual = new ArrayList<>();
        for (DirectedEdge i : it) {
            actual.add(i);
        }
        assertEquals("[(0, 4, 0.38)]", actual.toString());

        it = spt.pathTo(5);
        actual = new ArrayList<>();
        for (DirectedEdge i : it) {
            actual.add(i);
        }
        assertEquals("[(0, 4, 0.38), (4, 5, 0.35)]", actual.toString());

        it = spt.pathTo(6);
        actual = new ArrayList<>();
        for (DirectedEdge i : it) {
            actual.add(i);
        }
        assertEquals("[(0, 2, 0.26), (2, 7, 0.34), (7, 3, 0.39), (3, 6, 0.52)]", actual.toString());

        it = spt.pathTo(7);
        actual = new ArrayList<>();
        for (DirectedEdge i : it) {
            actual.add(i);
        }
        assertEquals("[(0, 2, 0.26), (2, 7, 0.34)]", actual.toString());
    }
}