package com.wjd.algorithm.graph.undirected.build.impl;

import com.wjd.algorithm.graph.undirected.build.GraphBuilder;
import com.wjd.structure.graph.undirected.Graph;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileGraphBuilderTest {

    @Test
    void build() {
        GraphBuilder graphBuilder = new FileGraphBuilder("tinyG.txt");
        Graph g = graphBuilder.build();

        assertEquals(13, g.vs());
        assertEquals(13, g.es());

        Iterable<Integer> it = g.adj(0);
        List<Integer> actual = new ArrayList<>();
        for (int i : it) {
            actual.add(i);
        }
        Collections.sort(actual);
        assertEquals("[1, 2, 5, 6]", actual.toString());

        it = g.adj(4);
        actual = new ArrayList<>();
        for (int i : it) {
            actual.add(i);
        }
        Collections.sort(actual);
        assertEquals("[3, 5, 6]", actual.toString());

        it = g.adj(9);
        actual = new ArrayList<>();
        for (int i : it) {
            actual.add(i);
        }
        Collections.sort(actual);
        assertEquals("[10, 11, 12]", actual.toString());

        it = g.adj(7);
        actual = new ArrayList<>();
        for (int i : it) {
            actual.add(i);
        }
        Collections.sort(actual);
        assertEquals("[8]", actual.toString());
    }
}