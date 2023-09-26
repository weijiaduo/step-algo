package com.wjd.structure.graph.undirected.impl;

import com.wjd.structure.graph.undirected.Graph;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ListGraphTest {

    @Test
    void vs() {
        Graph g = new ListGraph(2);
        assertEquals(2, g.vs());
        g = new ListGraph(23);
        assertEquals(23, g.vs());
        g = new ListGraph(201);
        assertEquals(201, g.vs());
    }

    @Test
    void es() {
        Graph g = new ListGraph(20);
        g.addEdge(19, 2);
        g.addEdge(1, 3);
        g.addEdge(4, 8);
        g.addEdge(1, 12);
        assertEquals(4, g.es());
        g.addEdge(3, 12);
        assertEquals(5, g.es());
    }

    @Test
    void hasEdge() {
        Graph g = new ListGraph(20);
        g.addEdge(19, 2);
        g.addEdge(1, 3);
        g.addEdge(4, 8);
        g.addEdge(1, 12);

        assertTrue(g.hasEdge(19, 2));
        assertTrue(g.hasEdge(1, 3));
        assertTrue(g.hasEdge(4, 8));
        assertTrue(g.hasEdge(1, 12));
    }

    @Test
    void adj() {
        Graph g = new ListGraph(20);
        g.addEdge(19, 2);
        g.addEdge(1, 3);
        g.addEdge(4, 8);
        g.addEdge(1, 12);

        Iterable<Integer> it = g.adj(1);
        List<Integer> actual = new ArrayList<>();
        for (int i : it) {
            actual.add(i);
        }
        Collections.sort(actual);
        assertEquals("[3, 12]", actual.toString());
    }

}