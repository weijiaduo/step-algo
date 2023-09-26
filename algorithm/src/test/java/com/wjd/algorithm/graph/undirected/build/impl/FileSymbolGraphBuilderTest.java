package com.wjd.algorithm.graph.undirected.build.impl;

import com.wjd.algorithm.graph.undirected.build.SymbolGraphBuilder;
import com.wjd.structure.graph.undirected.Graph;
import com.wjd.structure.graph.undirected.SymbolGraph;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileSymbolGraphBuilderTest {

    @Test
    void build() {
        SymbolGraphBuilder builder = new FileSymbolGraphBuilder("routes.txt");
        SymbolGraph sg = builder.build();

        assertEquals(10, sg.graph().vs());
        assertEquals(18, sg.graph().es());

        int index = sg.idx("ORD");
        Graph g = sg.graph();
        Iterable<Integer> it = g.adj(index);
        List<Integer> actual = new ArrayList<>();
        for (int i : it) {
            actual.add(i);
        }
        Collections.sort(actual);
        assertEquals("[1, 3, 5, 6, 7, 8]", actual.toString());
    }
}