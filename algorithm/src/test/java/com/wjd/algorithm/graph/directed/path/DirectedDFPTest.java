package com.wjd.algorithm.graph.directed.path;

import com.wjd.algorithm.graph.Paths;
import com.wjd.algorithm.graph.directed.build.impl.FileDigraphBuilder;
import com.wjd.structure.graph.directed.Digraph;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DirectedDFPTest {

    @Test
    void hasPathTo() {
        Digraph dg = new FileDigraphBuilder("tinyDG.txt").build();
        Paths paths = new DirectedDFP(dg, 0);

        assertTrue(paths.hasPathTo(1));
        assertTrue(paths.hasPathTo(2));
        assertTrue(paths.hasPathTo(3));
        assertTrue(paths.hasPathTo(4));
        assertTrue(paths.hasPathTo(5));

        assertFalse(paths.hasPathTo(6));
        assertFalse(paths.hasPathTo(7));
        assertFalse(paths.hasPathTo(8));
        assertFalse(paths.hasPathTo(9));
        assertFalse(paths.hasPathTo(10));
        assertFalse(paths.hasPathTo(11));
        assertFalse(paths.hasPathTo(12));
    }

    @Test
    void pathTo() {
        Digraph dg = new FileDigraphBuilder("tinyDG.txt").build();
        Paths paths = new DirectedDFP(dg, 0);

        Iterable<Integer> path = paths.pathTo(2);
        List<Integer> actual = new ArrayList<>();
        for (int v : path) {
            actual.add(v);
        }
        assertEquals("[0, 5, 4, 2]", actual.toString());

        path = paths.pathTo(3);
        actual = new ArrayList<>();
        for (int v : path) {
            actual.add(v);
        }
        assertEquals("[0, 5, 4, 2, 3]", actual.toString());

    }
}