package com.wjd.algorithm.graph.directed.connected.impl;

import com.wjd.algorithm.graph.directed.build.impl.FileDigraphBuilder;
import com.wjd.algorithm.graph.directed.connected.StrongConnected;
import com.wjd.structure.graph.directed.Digraph;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KosarajuStrongConnectedTest {

    @Test
    void connected() {
        Digraph dg = new FileDigraphBuilder("tinyDG.txt").build();
        StrongConnected connected = new KosarajuStrongConnected(dg);

        assertTrue(connected.connected(0, 2));
        assertTrue(connected.connected(0, 3));
        assertTrue(connected.connected(0, 4));
        assertTrue(connected.connected(0, 5));
        assertFalse(connected.connected(0, 1));
        assertFalse(connected.connected(0, 6));
        assertFalse(connected.connected(0, 7));
        assertFalse(connected.connected(0, 8));
        assertFalse(connected.connected(0, 9));
        assertFalse(connected.connected(0, 10));
        assertFalse(connected.connected(0, 11));
        assertFalse(connected.connected(0, 12));

        assertTrue(connected.connected(6, 8));

        assertTrue(connected.connected(9, 10));
        assertTrue(connected.connected(9, 11));
        assertTrue(connected.connected(9, 12));
    }

    @Test
    void count() {
        Digraph dg = new FileDigraphBuilder("tinyDG.txt").build();
        StrongConnected connected = new KosarajuStrongConnected(dg);
        assertEquals(5, connected.count());
    }

    @Test
    void id() {
        Digraph dg = new FileDigraphBuilder("tinyDG.txt").build();
        StrongConnected connected = new KosarajuStrongConnected(dg);

        assertEquals(0, connected.id(1));
        assertEquals(1, connected.id(0));
        assertEquals(2, connected.id(9));
        assertEquals(3, connected.id(6));
        assertEquals(4, connected.id(7));
    }
}