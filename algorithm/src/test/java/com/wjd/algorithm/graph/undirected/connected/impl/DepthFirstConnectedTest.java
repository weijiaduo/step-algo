package com.wjd.algorithm.graph.undirected.connected.impl;

import com.wjd.algorithm.graph.undirected.build.impl.FileGraphBuilder;
import com.wjd.algorithm.graph.undirected.connected.Connected;
import com.wjd.structure.graph.undirected.Graph;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DepthFirstConnectedTest {

    @Test
    void connected() {
        Graph g = new FileGraphBuilder("tinyG.txt").build();
        Connected cc = new DepthFirstConnected(g);
        assertTrue(cc.connected(0, 1));
        assertTrue(cc.connected(0, 2));
        assertTrue(cc.connected(0, 3));
        assertTrue(cc.connected(0, 4));
        assertTrue(cc.connected(0, 5));
        assertTrue(cc.connected(0, 6));

        assertFalse(cc.connected(0, 7));
        assertFalse(cc.connected(0, 8));
        assertFalse(cc.connected(0, 9));
        assertFalse(cc.connected(0, 10));
        assertFalse(cc.connected(0, 11));
        assertFalse(cc.connected(0, 12));
    }

    @Test
    void count() {
        Graph g = new FileGraphBuilder("tinyG.txt").build();
        Connected cc = new DepthFirstConnected(g);
        assertEquals(3, cc.count());
    }

    @Test
    void id() {
        Graph g = new FileGraphBuilder("tinyG.txt").build();
        Connected cc = new DepthFirstConnected(g);
        assertEquals(0, cc.id(0));
        assertEquals(0, cc.id(1));
        assertEquals(0, cc.id(2));
        assertEquals(0, cc.id(3));
        assertEquals(0, cc.id(5));
        assertEquals(0, cc.id(6));

        assertEquals(1, cc.id(7));
        assertEquals(1, cc.id(8));

        assertEquals(2, cc.id(9));
        assertEquals(2, cc.id(10));
        assertEquals(2, cc.id(11));
        assertEquals(2, cc.id(12));
    }

}