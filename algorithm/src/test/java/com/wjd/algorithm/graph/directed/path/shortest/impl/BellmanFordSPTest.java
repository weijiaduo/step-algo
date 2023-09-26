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

class BellmanFordSPTest {

    @Test
    void hasPathTo() {
        WeightedDigraph wdg = new FileWeightedDigraphBuilder("tinyEWDn.txt").build();
        ShortestPaths sp = new BellmanFordSP(wdg, 0);

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
        WeightedDigraph wdg = new FileWeightedDigraphBuilder("tinyEWDn.txt").build();
        ShortestPaths sp = new BellmanFordSP(wdg, 0);

        assertEquals("0.00", String.format("%.2f", sp.distTo(0)));
        assertEquals("0.93", String.format("%.2f", sp.distTo(1)));
        assertEquals("0.26", String.format("%.2f", sp.distTo(2)));
        assertEquals("0.99", String.format("%.2f", sp.distTo(3)));
        assertEquals("0.26", String.format("%.2f", sp.distTo(4)));
        assertEquals("0.61", String.format("%.2f", sp.distTo(5)));
        assertEquals("1.51", String.format("%.2f", sp.distTo(6)));
        assertEquals("0.60", String.format("%.2f", sp.distTo(7)));
    }

    @Test
    void pathTo() {
        WeightedDigraph wdg = new FileWeightedDigraphBuilder("tinyEWDn.txt").build();
        ShortestPaths sp = new BellmanFordSP(wdg, 0);

        assertEquals("[(0, 2, 0.26), (2, 7, 0.34), (7, 3, 0.39), (3, 6, 0.52), (6, 4, -1.25), (4, 5, 0.35), (5, 1, 0.32)]", toStr(sp.pathTo(1)));
        assertEquals("[(0, 2, 0.26)]", toStr(sp.pathTo(2)));
        assertEquals("[(0, 2, 0.26), (2, 7, 0.34), (7, 3, 0.39)]", toStr(sp.pathTo(3)));
        assertEquals("[(0, 2, 0.26), (2, 7, 0.34), (7, 3, 0.39), (3, 6, 0.52), (6, 4, -1.25)]", toStr(sp.pathTo(4)));
        assertEquals("[(0, 2, 0.26), (2, 7, 0.34), (7, 3, 0.39), (3, 6, 0.52), (6, 4, -1.25), (4, 5, 0.35)]", toStr(sp.pathTo(5)));
        assertEquals("[(0, 2, 0.26), (2, 7, 0.34), (7, 3, 0.39), (3, 6, 0.52)]", toStr(sp.pathTo(6)));
        assertEquals("[(0, 2, 0.26), (2, 7, 0.34)]", toStr(sp.pathTo(7)));
    }

    String toStr(Iterable<DirectedEdge> it) {
        List<DirectedEdge> list = new ArrayList<>();
        for (DirectedEdge i : it) {
            list.add(i);
        }
        return list.toString();
    }

}