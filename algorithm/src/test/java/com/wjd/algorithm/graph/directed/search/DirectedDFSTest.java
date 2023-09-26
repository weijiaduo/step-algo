package com.wjd.algorithm.graph.directed.search;

import com.wjd.algorithm.graph.Search;
import com.wjd.algorithm.graph.directed.build.impl.FileDigraphBuilder;
import com.wjd.structure.graph.directed.Digraph;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DirectedDFSTest {

    @Test
    void marked() {
        Digraph dg = new FileDigraphBuilder("tinyDG.txt").build();
        Search search = new DirectedDFS(dg, 0);

        List<Integer> actual = new ArrayList<>();
        for (int v = 0; v < dg.vs(); v++) {
            if (search.marked(v)) {
                actual.add(v);
            }
        }
        assertEquals("[0, 1, 2, 3, 4, 5]", actual.toString());
    }

    @Test
    void count() {
        Digraph dg = new FileDigraphBuilder("tinyDG.txt").build();
        Search search = new DirectedDFS(dg, 0);
        assertEquals(6, search.count());

        search = new DirectedDFS(dg, 7);
        assertEquals(13, search.count());

        search = new DirectedDFS(dg, 12);
        assertEquals(10, search.count());
    }

}