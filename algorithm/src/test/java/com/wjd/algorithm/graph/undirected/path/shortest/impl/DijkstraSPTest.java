package com.wjd.algorithm.graph.undirected.path.shortest.impl;

import com.wjd.algorithm.graph.undirected.build.impl.FileWeightedGraphBuilder;
import com.wjd.algorithm.graph.undirected.path.shortest.ShortestPaths;
import com.wjd.structure.graph.undirected.Edge;
import com.wjd.structure.graph.undirected.WeightedGraph;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DijkstraSPTest {

    @Test
    void hasPathTo() {
        WeightedGraph wg = new FileWeightedGraphBuilder("tinyEWG.txt").build();
        ShortestPaths sp = new DijkstraSP(wg, 0);

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
        WeightedGraph wg = new FileWeightedGraphBuilder("tinyEWG.txt").build();
        ShortestPaths sp = new DijkstraSP(wg, 0);

        assertEquals("0.00", String.format("%.2f", sp.distTo(0)));
        assertEquals("0.35", String.format("%.2f", sp.distTo(1)));
        assertEquals("0.26", String.format("%.2f", sp.distTo(2)));
        assertEquals("0.43", String.format("%.2f", sp.distTo(3)));
        assertEquals("0.38", String.format("%.2f", sp.distTo(4)));
        assertEquals("0.44", String.format("%.2f", sp.distTo(5)));
        assertEquals("0.58", String.format("%.2f", sp.distTo(6)));
        assertEquals("0.16", String.format("%.2f", sp.distTo(7)));
    }

    @Test
    void pathTo() {
        WeightedGraph wg = new FileWeightedGraphBuilder("tinyEWG.txt").build();
        ShortestPaths sp = new DijkstraSP(wg, 0);

        assertEquals("[(0, 7, 0.16), (1, 7, 0.19)]", toStr(sp.pathTo(1)));
        assertEquals("[(0, 2, 0.26)]", toStr(sp.pathTo(2)));
        assertEquals("[(0, 2, 0.26), (2, 3, 0.17)]", toStr(sp.pathTo(3)));
        assertEquals("[(0, 4, 0.38)]", toStr(sp.pathTo(4)));
        assertEquals("[(0, 7, 0.16), (5, 7, 0.28)]", toStr(sp.pathTo(5)));
        assertEquals("[(6, 0, 0.58)]", toStr(sp.pathTo(6)));
        assertEquals("[(0, 7, 0.16)]", toStr(sp.pathTo(7)));
    }

    String toStr(Iterable<Edge> it) {
        List<Edge> list = new ArrayList<>();
        for (Edge i : it) {
            list.add(i);
        }
        return list.toString();
    }

}