package com.wjd.structure.heap.skew;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SkewHeapTest {

    @Test
    void mergeEmpty() {
        SkewHeap<Integer> nonNullHeap = new SkewHeapImpl<>();
        SkewHeap<Integer> nullHeap = new SkewHeapImpl<>();

        nonNullHeap.insert(6);
        nonNullHeap.insert(8);
        nonNullHeap.insert(2);
        nonNullHeap.insert(4);
        nonNullHeap.merge(nullHeap);
        assertEquals("[2, 4, 6, null, null, 8]", nonNullHeap.toString());

        nullHeap = new SkewHeapImpl<>();
        nullHeap.merge(nonNullHeap);
        assertEquals("[2, 4, 6, null, null, 8]", nonNullHeap.toString());
    }

    @Test
    void merge() {
        SkewHeap<Integer> heap1 = new SkewHeapImpl<>();
        heap1.insert(5);
        heap1.insert(3);
        heap1.insert(7);
        heap1.insert(1);
        heap1.insert(9);
        assertEquals("[1, 9, 3, null, null, 7, 5]", heap1.toString());
        SkewHeap<Integer> heap2 = new SkewHeapImpl<>();
        heap2.insert(6);
        heap2.insert(8);
        heap2.insert(2);
        heap2.insert(4);
        assertEquals("[2, 4, 6, null, null, 8]", heap2.toString());

        heap1.merge(heap2);
        assertEquals("[1, 2, 9, 3, 4, null, null, 5, 7, null, null, 6, null, null, null, 8]", heap1.toString());
    }

    @Test
    void insert() {
        SkewHeap<Integer> heap = new SkewHeapImpl<>();
        heap.insert(5);
        assertEquals("[5]", heap.toString());
        heap.insert(3);
        assertEquals("[3, 5]", heap.toString());
        heap.insert(7);
        assertEquals("[3, 7, 5]", heap.toString());
        heap.insert(1);
        assertEquals("[1, 3, null, 7, 5]", heap.toString());
        heap.insert(9);
        assertEquals("[1, 9, 3, null, null, 7, 5]", heap.toString());
    }

    @Test
    void removeFirst() {
        SkewHeap<Integer> heap = new SkewHeapImpl<>();
        heap.insert(5);
        heap.insert(3);
        heap.insert(7);
        heap.insert(1);
        heap.insert(9);
        assertEquals("[1, 9, 3, null, null, 7, 5]", heap.toString());

        Integer first = heap.removeFirst();
        assertEquals(1, first);
        assertEquals("[3, 5, 7, 9]", heap.toString());

        first = heap.removeFirst();
        assertEquals(3, first);
        assertEquals("[5, 7, 9]", heap.toString());

        first = heap.removeFirst();
        assertEquals(5, first);
        assertEquals("[7, 9]", heap.toString());

        first = heap.removeFirst();
        assertEquals(7, first);
        assertEquals("[9]", heap.toString());
    }

    @Test
    void first() {
        SkewHeap<Integer> heap = new SkewHeapImpl<>();
        heap.insert(5);
        heap.insert(3);
        heap.insert(7);
        heap.insert(1);
        heap.insert(9);
        assertEquals("[1, 9, 3, null, null, 7, 5]", heap.toString());

        Integer first = heap.first();
        assertEquals(1, first);
        heap.removeFirst();

        first = heap.first();
        assertEquals(3, first);
        heap.removeFirst();

        first = heap.first();
        assertEquals(5, first);
        heap.removeFirst();

        first = heap.first();
        assertEquals(7, first);
        heap.removeFirst();

        first = heap.first();
        assertEquals(9, first);
    }

    @Test
    void size() {
        SkewHeap<Integer> heap = new SkewHeapImpl<>();
        heap.insert(5);
        heap.insert(3);
        heap.insert(7);
        heap.insert(1);
        heap.insert(9);
        assertEquals("[1, 9, 3, null, null, 7, 5]", heap.toString());

        int size = heap.size();
        assertEquals(5, size);
        heap.removeFirst();

        size = heap.size();
        assertEquals(4, size);
        heap.removeFirst();

        size = heap.size();
        assertEquals(3, size);
        heap.removeFirst();

        size = heap.size();
        assertEquals(2, size);
        heap.removeFirst();

        size = heap.size();
        assertEquals(1, size);
        heap.removeFirst();

        size = heap.size();
        assertEquals(0, size);
    }

    @Test
    void isEmpty() {
        SkewHeap<Integer> heap = new SkewHeapImpl<>();
        heap.insert(5);
        heap.insert(3);
        heap.insert(7);
        heap.insert(1);
        heap.insert(9);
        assertEquals("[1, 9, 3, null, null, 7, 5]", heap.toString());

        assertFalse(heap.isEmpty());
        heap.removeFirst();
        heap.removeFirst();
        heap.removeFirst();
        assertFalse(heap.isEmpty());
        heap.removeFirst();
        heap.removeFirst();
        assertTrue(heap.isEmpty());
    }

}