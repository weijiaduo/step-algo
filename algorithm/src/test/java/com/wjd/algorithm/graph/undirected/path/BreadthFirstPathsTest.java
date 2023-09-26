package com.wjd.algorithm.graph.undirected.path;

import com.wjd.algorithm.graph.Paths;
import com.wjd.algorithm.graph.undirected.build.impl.FileGraphBuilder;
import com.wjd.structure.graph.undirected.Graph;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BreadthFirstPathsTest {

    @Test
    void hasPath() {
        Graph g = new FileGraphBuilder("tinyG.txt").build();
        Paths paths = new BreadthFirstPaths(g, 0);
        assertTrue(paths.hasPathTo(1));
        assertTrue(paths.hasPathTo(2));
        assertTrue(paths.hasPathTo(3));
        assertTrue(paths.hasPathTo(4));
        assertTrue(paths.hasPathTo(5));
        assertTrue(paths.hasPathTo(6));

        assertFalse(paths.hasPathTo(7));
        assertFalse(paths.hasPathTo(8));
        assertFalse(paths.hasPathTo(9));
        assertFalse(paths.hasPathTo(10));
        assertFalse(paths.hasPathTo(11));
        assertFalse(paths.hasPathTo(12));
    }

    @Test
    void pathTo() {
        Graph g = new FileGraphBuilder("tinyG.txt").build();

        Paths paths = new BreadthFirstPaths(g, 0);
        Iterable<Integer> path = paths.pathTo(6);
        List<Integer> actual = new ArrayList<>();
        for (int v : path) {
            actual.add(v);
        }
        assertEquals("[0, 6]", actual.toString());

        paths = new BreadthFirstPaths(g, 0);
        path = paths.pathTo(3);
        actual = new ArrayList<>();
        for (int v : path) {
            actual.add(v);
        }
        assertEquals("[0, 5, 3]", actual.toString());
    }

}