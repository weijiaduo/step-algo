package com.wjd.algorithm.graph.undirected.search;

import com.wjd.algorithm.graph.Search;
import com.wjd.algorithm.graph.undirected.build.impl.FileGraphBuilder;
import com.wjd.structure.graph.undirected.Graph;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DepthFirstSearchTest {

    @Test
    void marked() {
        Graph g = new FileGraphBuilder("tinyG.txt").build();
        Search search = new DepthFirstSearch(g, 1);
        List<Integer> actual = new ArrayList<>();
        for (int v = 0; v < g.vs(); v++) {
            if (search.marked(v)) {
                actual.add(v);
            }
        }
        assertEquals("[0, 1, 2, 3, 4, 5, 6]", actual.toString());
    }

    @Test
    void count() {
        Graph g = new FileGraphBuilder("tinyG.txt").build();
        Search search = new DepthFirstSearch(g, 0);
        assertEquals(7, search.count());

        search = new DepthFirstSearch(g, 7);
        assertEquals(2, search.count());

        search = new DepthFirstSearch(g, 10);
        assertEquals(4, search.count());
    }

}