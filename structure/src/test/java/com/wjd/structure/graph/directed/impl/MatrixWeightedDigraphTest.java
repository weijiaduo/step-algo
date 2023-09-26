package com.wjd.structure.graph.directed.impl;

import com.wjd.structure.graph.directed.DirectedEdge;
import com.wjd.structure.graph.directed.WeightedDigraph;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MatrixWeightedDigraphTest {

    @Test
    void vs() {
        WeightedDigraph wdg = new MatrixWeightedDigraph(2);
        assertEquals(2, wdg.vs());
        wdg = new MatrixWeightedDigraph(19);
        assertEquals(19, wdg.vs());
        wdg = new MatrixWeightedDigraph(25);
        assertEquals(25, wdg.vs());
        wdg = new MatrixWeightedDigraph(72);
        assertEquals(72, wdg.vs());
    }

    @Test
    void es() {
        WeightedDigraph wdg = new MatrixWeightedDigraph(12);
        assertEquals(0, wdg.es());
        wdg.addEdge(new DirectedEdge(1, 2, 4.4));
        wdg.addEdge(new DirectedEdge(1, 6, 1.2));
        wdg.addEdge(new DirectedEdge(1, 8, 3.7));
        wdg.addEdge(new DirectedEdge(2, 6, 2.5));
        assertEquals(4, wdg.es());
    }

    @Test
    void addEdge() {
        WeightedDigraph wdg = new MatrixWeightedDigraph(12);
        wdg.addEdge(new DirectedEdge(1, 2, 4.4));
        wdg.addEdge(new DirectedEdge(1, 6, 1.2));
        wdg.addEdge(new DirectedEdge(1, 8, 3.7));
        wdg.addEdge(new DirectedEdge(2, 6, 2.5));
        assertEquals(4, wdg.es());
        wdg.addEdge(new DirectedEdge(1, 6, 1.2));
        assertEquals(4, wdg.es());
        wdg.addEdge(new DirectedEdge(6, 1, 2.5));
        assertEquals(5, wdg.es());
    }

    @Test
    void hasEdge() {
        WeightedDigraph wdg = new MatrixWeightedDigraph(12);
        wdg.addEdge(new DirectedEdge(1, 2, 4.4));
        wdg.addEdge(new DirectedEdge(1, 6, 1.2));
        wdg.addEdge(new DirectedEdge(1, 8, 3.7));
        wdg.addEdge(new DirectedEdge(2, 6, 2.5));

        assertTrue(wdg.hasEdge(1, 2));
        assertTrue(wdg.hasEdge(1, 6));
        assertTrue(wdg.hasEdge(1, 8));
        assertTrue(wdg.hasEdge(2, 6));
    }

    @Test
    void adj() {
        WeightedDigraph wdg = new MatrixWeightedDigraph(12);
        wdg.addEdge(new DirectedEdge(1, 2, 4.4));
        wdg.addEdge(new DirectedEdge(1, 6, 1.2));
        wdg.addEdge(new DirectedEdge(1, 8, 3.7));
        wdg.addEdge(new DirectedEdge(2, 6, 2.5));

        Iterable<DirectedEdge> it = wdg.adj(1);
        List<DirectedEdge> actual = new ArrayList<>();
        for (DirectedEdge e : it) {
            actual.add(e);
        }
        Collections.sort(actual);
        assertEquals("[(1, 6, 1.20), (1, 8, 3.70), (1, 2, 4.40)]", actual.toString());
    }

    @Test
    void edges() {
        WeightedDigraph wdg = new MatrixWeightedDigraph(12);
        wdg.addEdge(new DirectedEdge(1, 2, 4.4));
        wdg.addEdge(new DirectedEdge(1, 6, 1.2));
        wdg.addEdge(new DirectedEdge(1, 8, 3.7));
        wdg.addEdge(new DirectedEdge(2, 6, 2.5));

        Iterable<DirectedEdge> it = wdg.edges();
        List<DirectedEdge> actual = new ArrayList<>();
        for (DirectedEdge e : it) {
            actual.add(e);
        }
        Collections.sort(actual);
        assertEquals("[(1, 6, 1.20), (2, 6, 2.50), (1, 8, 3.70), (1, 2, 4.40)]", actual.toString());
    }

}