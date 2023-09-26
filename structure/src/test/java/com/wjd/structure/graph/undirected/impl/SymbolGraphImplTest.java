package com.wjd.structure.graph.undirected.impl;

import com.wjd.structure.graph.undirected.SymbolGraph;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class SymbolGraphImplTest {

    @Test
    void contains() {
        List<String> keys = Arrays.asList("A", "B", "C");
        Set<String> keySet = new HashSet<>(keys);
        SymbolGraph sg = new SymbolGraphImpl(keySet);

        assertTrue(sg.contains("A"));
        assertTrue(sg.contains("B"));
        assertTrue(sg.contains("C"));

        assertFalse(sg.contains("D"));
    }

    @Test
    void index() {
        List<String> keys = Arrays.asList("A", "B", "C");
        Set<String> keySet = new LinkedHashSet<>(keys);
        SymbolGraph sg = new SymbolGraphImpl(keySet);

        assertEquals(0, sg.idx("A"));
        assertEquals(1, sg.idx("B"));
        assertEquals(2, sg.idx("C"));
    }

    @Test
    void key() {
        List<String> keys = Arrays.asList("A", "B", "C");
        Set<String> keySet = new LinkedHashSet<>(keys);
        SymbolGraph sg = new SymbolGraphImpl(keySet);

        assertEquals("A", sg.key(0));
        assertEquals("B", sg.key(1));
        assertEquals("C", sg.key(2));
    }

    @Test
    void addEdge() {
        List<String> keys = Arrays.asList("A", "B", "C");
        Set<String> keySet = new LinkedHashSet<>(keys);
        SymbolGraph sg = new SymbolGraphImpl(keySet);

        sg.addEdge("A", "C");
        sg.addEdge("A", "B");
        sg.addEdge("B", "C");

        assertTrue(sg.hasEdge("A", "C"));
        assertTrue(sg.hasEdge("A", "B"));
        assertTrue(sg.hasEdge("B", "C"));
    }

    @Test
    void adj() {
        List<String> keys = Arrays.asList("A", "B", "C");
        Set<String> keySet = new LinkedHashSet<>(keys);
        SymbolGraph sg = new SymbolGraphImpl(keySet);

        sg.addEdge("A", "C");
        sg.addEdge("A", "B");
        sg.addEdge("B", "C");

        Iterable<String> it = sg.adj("A");
        List<String> actual = new ArrayList<>();
        for (String s : it) {
            actual.add(s);
        }
        Collections.sort(actual);
        assertEquals("[B, C]", actual.toString());
    }

}