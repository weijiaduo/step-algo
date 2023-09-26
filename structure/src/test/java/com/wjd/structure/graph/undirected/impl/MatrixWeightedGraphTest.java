package com.wjd.structure.graph.undirected.impl;

import com.wjd.structure.graph.undirected.Edge;
import com.wjd.structure.graph.undirected.WeightedGraph;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MatrixWeightedGraphTest {

    @Test
    void vs() {
        WeightedGraph wg = new MatrixWeightedGraph(2);
        assertEquals(2, wg.vs());
        wg = new MatrixWeightedGraph(19);
        assertEquals(19, wg.vs());
        wg = new MatrixWeightedGraph(25);
        assertEquals(25, wg.vs());
        wg = new MatrixWeightedGraph(72);
        assertEquals(72, wg.vs());
    }

    @Test
    void es() {
        WeightedGraph wg = new MatrixWeightedGraph(12);
        assertEquals(0, wg.es());
        wg.addEdge(new Edge(1, 2, 4.4));
        wg.addEdge(new Edge(1, 6, 1.2));
        wg.addEdge(new Edge(1, 8, 3.7));
        wg.addEdge(new Edge(2, 6, 2.5));
        assertEquals(4, wg.es());
    }

    @Test
    void addEdge() {
        WeightedGraph wg = new MatrixWeightedGraph(12);
        wg.addEdge(new Edge(1, 2, 4.4));
        wg.addEdge(new Edge(1, 6, 1.2));
        wg.addEdge(new Edge(1, 8, 3.7));
        wg.addEdge(new Edge(2, 6, 2.5));
        assertEquals(4, wg.es());
        wg.addEdge(new Edge(1, 6, 1.2));
        wg.addEdge(new Edge(2, 6, 2.5));
        assertEquals(4, wg.es());
    }

    @Test
    void hasEdge() {
        WeightedGraph wg = new MatrixWeightedGraph(12);
        wg.addEdge(new Edge(1, 2, 4.4));
        wg.addEdge(new Edge(1, 6, 1.2));
        wg.addEdge(new Edge(1, 8, 3.7));
        wg.addEdge(new Edge(2, 6, 2.5));

        assertTrue(wg.hasEdge(1, 2));
        assertTrue(wg.hasEdge(1, 6));
        assertTrue(wg.hasEdge(1, 8));
        assertTrue(wg.hasEdge(2, 6));
    }

    @Test
    void adj() {
        WeightedGraph wg = new MatrixWeightedGraph(12);
        wg.addEdge(new Edge(1, 2, 4.4));
        wg.addEdge(new Edge(1, 6, 1.2));
        wg.addEdge(new Edge(1, 8, 3.7));
        wg.addEdge(new Edge(2, 6, 2.5));

        Iterable<Edge> it = wg.adj(1);
        List<Edge> actual = new ArrayList<>();
        for (Edge e : it) {
            actual.add(e);
        }
        Collections.sort(actual);
        assertEquals("[(1, 6, 1.20), (1, 8, 3.70), (1, 2, 4.40)]", actual.toString());
    }

    @Test
    void edges() {
        WeightedGraph wg = new MatrixWeightedGraph(12);
        wg.addEdge(new Edge(1, 2, 4.4));
        wg.addEdge(new Edge(1, 6, 1.2));
        wg.addEdge(new Edge(1, 8, 3.7));
        wg.addEdge(new Edge(2, 6, 2.5));

        Iterable<Edge> it = wg.edges();
        List<Edge> actual = new ArrayList<>();
        for (Edge e : it) {
            actual.add(e);
        }
        Collections.sort(actual);
        assertEquals("[(1, 6, 1.20), (2, 6, 2.50), (1, 8, 3.70), (1, 2, 4.40)]", actual.toString());
    }
}