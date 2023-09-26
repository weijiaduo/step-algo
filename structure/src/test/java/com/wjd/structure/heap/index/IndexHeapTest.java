package com.wjd.structure.heap.index;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IndexHeapTest {

    @Test
    void insert() {
        IndexHeap<Integer> indexHeap = new IndexHeapImpl<>(10);
        indexHeap.insert(0, 1);
        assertEquals("[(0, 1)]", indexHeap.toString());
        indexHeap.insert(2, 3);
        assertEquals("[(2, 3), (0, 1)]", indexHeap.toString());
        indexHeap.insert(5, 6);
        assertEquals("[(5, 6), (0, 1), (2, 3)]", indexHeap.toString());
        indexHeap.insert(3, 4);
        assertEquals("[(5, 6), (3, 4), (2, 3), (0, 1)]", indexHeap.toString());
        indexHeap.insert(6, 7);
        assertEquals("[(6, 7), (5, 6), (2, 3), (0, 1), (3, 4)]", indexHeap.toString());
        indexHeap.insert(1, 2);
        assertEquals("[(6, 7), (5, 6), (2, 3), (0, 1), (3, 4), (1, 2)]", indexHeap.toString());
        indexHeap.insert(8, 9);
        assertEquals("[(8, 9), (5, 6), (6, 7), (0, 1), (3, 4), (1, 2), (2, 3)]", indexHeap.toString());
        indexHeap.insert(7, 8);
        assertEquals("[(8, 9), (7, 8), (6, 7), (5, 6), (3, 4), (1, 2), (2, 3), (0, 1)]", indexHeap.toString());
    }

    @Test
    void removeFirst() {
        IndexHeap<Integer> indexHeap = new IndexHeapImpl<>(10);
        indexHeap.insert(0, 1);
        indexHeap.insert(2, 3);
        indexHeap.insert(5, 6);
        indexHeap.insert(3, 4);
        indexHeap.insert(6, 7);
        indexHeap.insert(1, 2);
        indexHeap.insert(8, 9);
        indexHeap.insert(7, 8);
        assertEquals("[(8, 9), (7, 8), (6, 7), (5, 6), (3, 4), (1, 2), (2, 3), (0, 1)]", indexHeap.toString());

        assertEquals(9, indexHeap.removeFirst());
        assertEquals("[(7, 8), (5, 6), (6, 7), (0, 1), (3, 4), (1, 2), (2, 3)]", indexHeap.toString());
        assertEquals(8, indexHeap.removeFirst());
        assertEquals("[(6, 7), (5, 6), (2, 3), (0, 1), (3, 4), (1, 2)]", indexHeap.toString());
        assertEquals(7, indexHeap.removeFirst());
        assertEquals("[(5, 6), (3, 4), (2, 3), (0, 1), (1, 2)]", indexHeap.toString());
        assertEquals(6, indexHeap.removeFirst());
        assertEquals("[(3, 4), (1, 2), (2, 3), (0, 1)]", indexHeap.toString());
        assertEquals(4, indexHeap.removeFirst());
        assertEquals("[(2, 3), (1, 2), (0, 1)]", indexHeap.toString());
        assertEquals(3, indexHeap.removeFirst());
        assertEquals("[(1, 2), (0, 1)]", indexHeap.toString());
        assertEquals(2, indexHeap.removeFirst());
        assertEquals("[(0, 1)]", indexHeap.toString());
        assertEquals(1, indexHeap.removeFirst());
    }

    @Test
    void remove() {
        IndexHeap<Integer> indexHeap = new IndexHeapImpl<>(10);
        indexHeap.insert(8, 9);
        indexHeap.insert(7, 8);
        indexHeap.insert(2, 3);
        indexHeap.insert(6, 7);
        indexHeap.insert(5, 6);
        indexHeap.insert(0, 1);
        indexHeap.insert(1, 2);
        indexHeap.insert(3, 4);
        assertEquals("[(8, 9), (7, 8), (2, 3), (6, 7), (5, 6), (0, 1), (1, 2), (3, 4)]", indexHeap.toString());

        assertEquals(1, indexHeap.remove(0));
        assertEquals("[(8, 9), (7, 8), (3, 4), (6, 7), (5, 6), (2, 3), (1, 2)]", indexHeap.toString());
        assertEquals(8, indexHeap.remove(7));
        assertEquals("[(8, 9), (6, 7), (3, 4), (1, 2), (5, 6), (2, 3)]", indexHeap.toString());
        assertEquals(9, indexHeap.remove(8));
        assertEquals("[(6, 7), (5, 6), (3, 4), (1, 2), (2, 3)]", indexHeap.toString());
        assertEquals(6, indexHeap.remove(5));
        assertEquals("[(6, 7), (2, 3), (3, 4), (1, 2)]", indexHeap.toString());
        assertEquals(2, indexHeap.remove(1));
        assertEquals("[(6, 7), (2, 3), (3, 4)]", indexHeap.toString());
        assertEquals(7, indexHeap.remove(6));
        assertEquals("[(3, 4), (2, 3)]", indexHeap.toString());
        assertEquals(3, indexHeap.remove(2));
        assertEquals("[(3, 4)]", indexHeap.toString());
    }

    @Test
    void first() {
        IndexHeap<Integer> indexHeap = new IndexHeapImpl<>(10);
        indexHeap.insert(0, 1);
        indexHeap.insert(2, 3);
        indexHeap.insert(5, 6);
        indexHeap.insert(3, 4);
        indexHeap.insert(6, 7);
        indexHeap.insert(1, 2);
        indexHeap.insert(8, 9);
        indexHeap.insert(7, 8);
        assertEquals("[(8, 9), (7, 8), (6, 7), (5, 6), (3, 4), (1, 2), (2, 3), (0, 1)]", indexHeap.toString());

        assertEquals(9, indexHeap.first());
        indexHeap.removeFirst();
        assertEquals(8, indexHeap.first());
        indexHeap.removeFirst();
        assertEquals(7, indexHeap.first());
        indexHeap.removeFirst();
        assertEquals(6, indexHeap.first());
        indexHeap.removeFirst();
        assertEquals(4, indexHeap.first());
        indexHeap.removeFirst();
        assertEquals(3, indexHeap.first());
        indexHeap.removeFirst();
        assertEquals(2, indexHeap.first());
        indexHeap.removeFirst();
        assertEquals(1, indexHeap.first());
    }

    @Test
    void firstIndex() {
        IndexHeap<Integer> indexHeap = new IndexHeapImpl<>(10);
        indexHeap.insert(0, 1);
        indexHeap.insert(2, 3);
        indexHeap.insert(5, 6);
        indexHeap.insert(3, 4);
        indexHeap.insert(6, 7);
        indexHeap.insert(1, 2);
        indexHeap.insert(8, 9);
        indexHeap.insert(7, 8);
        assertEquals("[(8, 9), (7, 8), (6, 7), (5, 6), (3, 4), (1, 2), (2, 3), (0, 1)]", indexHeap.toString());

        assertEquals(8, indexHeap.firstIndex());
        indexHeap.removeFirst();
        assertEquals(7, indexHeap.firstIndex());
        indexHeap.removeFirst();
        assertEquals(6, indexHeap.firstIndex());
        indexHeap.removeFirst();
        assertEquals(5, indexHeap.firstIndex());
        indexHeap.removeFirst();
        assertEquals(3, indexHeap.firstIndex());
        indexHeap.removeFirst();
        assertEquals(2, indexHeap.firstIndex());
        indexHeap.removeFirst();
        assertEquals(1, indexHeap.firstIndex());
        indexHeap.removeFirst();
        assertEquals(0, indexHeap.firstIndex());
    }

    @Test
    void contains() {
        IndexHeap<Integer> indexHeap = new IndexHeapImpl<>(10);
        indexHeap.insert(0, 1);
        indexHeap.insert(2, 3);
        indexHeap.insert(5, 6);
        indexHeap.insert(3, 4);
        indexHeap.insert(6, 7);
        indexHeap.insert(1, 2);
        indexHeap.insert(8, 9);
        indexHeap.insert(7, 8);
        assertEquals("[(8, 9), (7, 8), (6, 7), (5, 6), (3, 4), (1, 2), (2, 3), (0, 1)]", indexHeap.toString());

        assertTrue(indexHeap.containsIndex(0));
        assertTrue(indexHeap.containsIndex(1));
        assertTrue(indexHeap.containsIndex(2));
        assertTrue(indexHeap.containsIndex(3));
        assertTrue(indexHeap.containsIndex(5));
        assertTrue(indexHeap.containsIndex(6));
        assertTrue(indexHeap.containsIndex(7));
        assertTrue(indexHeap.containsIndex(8));

        assertFalse(indexHeap.containsIndex(4));
        assertFalse(indexHeap.containsIndex(9));
        assertFalse(indexHeap.containsIndex(10));
        assertFalse(indexHeap.containsIndex(11));
    }

    @Test
    void size() {
        IndexHeap<Integer> indexHeap = new IndexHeapImpl<>(10);
        indexHeap.insert(0, 1);
        indexHeap.insert(2, 3);
        indexHeap.insert(5, 6);
        indexHeap.insert(3, 4);
        indexHeap.insert(6, 7);
        indexHeap.insert(1, 2);
        indexHeap.insert(8, 9);
        indexHeap.insert(7, 8);
        assertEquals("[(8, 9), (7, 8), (6, 7), (5, 6), (3, 4), (1, 2), (2, 3), (0, 1)]", indexHeap.toString());

        assertEquals(8, indexHeap.size());
        indexHeap.removeFirst();
        assertEquals(7, indexHeap.size());
        indexHeap.removeFirst();
        assertEquals(6, indexHeap.size());
        indexHeap.removeFirst();
        assertEquals(5, indexHeap.size());
        indexHeap.removeFirst();
        assertEquals(4, indexHeap.size());
        indexHeap.removeFirst();
        assertEquals(3, indexHeap.size());
        indexHeap.removeFirst();
        assertEquals(2, indexHeap.size());
        indexHeap.removeFirst();
        assertEquals(1, indexHeap.size());
    }

}