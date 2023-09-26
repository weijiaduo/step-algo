package com.wjd.algorithm.graph.undirected.bipartite.impl;

import com.wjd.algorithm.graph.undirected.bipartite.Bipartite;
import com.wjd.structure.graph.undirected.Graph;
import com.wjd.structure.graph.undirected.impl.ListGraph;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BipartiteImplTest {

    @Test
    void isBipartite() {
        Graph g = new ListGraph(20);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(0, 5);
        g.addEdge(0, 6);
        g.addEdge(1, 3);
        g.addEdge(3, 5);
        g.addEdge(4, 5);
        g.addEdge(4, 6);
        g.addEdge(6, 7);
        g.addEdge(7, 8);
        g.addEdge(8, 10);
        g.addEdge(9, 10);
        g.addEdge(9, 11);
        g.addEdge(10, 12);
        g.addEdge(11, 12);

        Bipartite bipartite = new BipartiteImpl(g);
        assertTrue(bipartite.isBipartite());
    }

    @Test
    void id() {
        Graph g = new ListGraph(20);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(0, 5);
        g.addEdge(0, 6);
        g.addEdge(1, 3);
        g.addEdge(3, 5);
        g.addEdge(4, 5);
        g.addEdge(4, 6);
        g.addEdge(6, 7);
        g.addEdge(7, 8);
        g.addEdge(8, 10);
        g.addEdge(9, 10);
        g.addEdge(9, 11);
        g.addEdge(10, 12);
        g.addEdge(11, 12);

        Bipartite bipartite = new BipartiteImpl(g);
        assertEquals(0, bipartite.id(0));
        assertEquals(0, bipartite.id(3));
        assertEquals(0, bipartite.id(4));
        assertEquals(0, bipartite.id(7));
        assertEquals(0, bipartite.id(10));
        assertEquals(0, bipartite.id(11));

        assertEquals(1, bipartite.id(1));
        assertEquals(1, bipartite.id(2));
        assertEquals(1, bipartite.id(5));
        assertEquals(1, bipartite.id(6));
        assertEquals(1, bipartite.id(8));
        assertEquals(1, bipartite.id(9));
        assertEquals(1, bipartite.id(12));
    }
}