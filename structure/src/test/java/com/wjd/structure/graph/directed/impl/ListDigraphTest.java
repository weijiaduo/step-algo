package com.wjd.structure.graph.directed.impl;

import com.wjd.structure.graph.directed.Digraph;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ListDigraphTest {

    @Test
    void vs() {
        Digraph dg = new ListDigraph(2);
        assertEquals(2, dg.vs());
        dg = new ListDigraph(23);
        assertEquals(23, dg.vs());
        dg = new ListDigraph(201);
        assertEquals(201, dg.vs());
    }

    @Test
    void es() {
        Digraph dg = new ListDigraph(20);
        dg.addEdge(19, 2);
        dg.addEdge(1, 3);
        dg.addEdge(4, 8);
        dg.addEdge(1, 12);
        assertEquals(4, dg.es());
        dg.addEdge(3, 12);
        assertEquals(5, dg.es());
        dg.addEdge(3, 12);
        assertEquals(5, dg.es());
    }

    @Test
    void hasEdge() {
        Digraph dg = new ListDigraph(20);
        dg.addEdge(19, 2);
        dg.addEdge(1, 3);
        dg.addEdge(4, 8);
        dg.addEdge(1, 12);

        assertTrue(dg.hasEdge(19, 2));
        assertTrue(dg.hasEdge(1, 3));
        assertTrue(dg.hasEdge(4, 8));
        assertTrue(dg.hasEdge(1, 12));
    }

    @Test
    void adj() {
        Digraph dg = new ListDigraph(20);
        dg.addEdge(19, 2);
        dg.addEdge(1, 3);
        dg.addEdge(4, 8);
        dg.addEdge(1, 12);

        Iterable<Integer> it = dg.adj(1);
        List<Integer> actual = new ArrayList<>();
        for (int i : it) {
            actual.add(i);
        }
        Collections.sort(actual);
        assertEquals("[3, 12]", actual.toString());
    }

    @Test
    void reverse() {
        Digraph dg = new ListDigraph(20);
        dg.addEdge(19, 2);
        dg.addEdge(1, 3);
        dg.addEdge(4, 8);
        dg.addEdge(1, 12);

        Digraph rdg = dg.reverse();
        assertFalse(rdg.hasEdge(19, 2));
        assertFalse(rdg.hasEdge(1, 3));
        assertFalse(rdg.hasEdge(4, 8));
        assertFalse(rdg.hasEdge(1, 12));

        assertTrue(rdg.hasEdge(2, 19));
        assertTrue(rdg.hasEdge(3, 1));
        assertTrue(rdg.hasEdge(8, 4));
        assertTrue(rdg.hasEdge(12, 1));
    }
    
}