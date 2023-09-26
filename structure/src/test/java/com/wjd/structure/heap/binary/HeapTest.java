package com.wjd.structure.heap.binary;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HeapTest {

    @Test
    void testBuild() {
        Integer[] values = new Integer[]{1, 2, 3, 4, 5, 6, 7};
        Heap<Integer> heap = new HeapImpl<>(values);
        assertEquals("[null, 7, 5, 6, 4, 2, 1, 3]", heap.toString());
    }

    @Test
    void testInsert() {
        Heap<Integer> heap = new HeapImpl<>(20);
        Integer[] values = new Integer[]{7, 5, 6, 4, 2, 1, 3};
        for (int val : values) {
            heap.insert(val);
        }
        assertEquals("[null, 7, 5, 6, 4, 2, 1, 3]", heap.toString());

        heap.insert(12);
        assertEquals("[null, 12, 7, 6, 5, 2, 1, 3, 4]", heap.toString());
        heap.insert(10);
        assertEquals("[null, 12, 10, 6, 7, 2, 1, 3, 4, 5]", heap.toString());
        heap.insert(11);
        assertEquals("[null, 12, 11, 6, 7, 10, 1, 3, 4, 5, 2]", heap.toString());
        heap.insert(9);
        assertEquals("[null, 12, 11, 6, 7, 10, 1, 3, 4, 5, 2, 9]", heap.toString());
        heap.insert(16);
        assertEquals("[null, 16, 11, 12, 7, 10, 6, 3, 4, 5, 2, 9, 1]", heap.toString());
        heap.insert(14);
        assertEquals("[null, 16, 11, 14, 7, 10, 12, 3, 4, 5, 2, 9, 1, 6]", heap.toString());
    }

    @Test
    void testRemoveFirst() {
        Integer[] values = new Integer[]{1, 2, 3, 4, 5, 6, 7};
        Heap<Integer> heap = new HeapImpl<>(values);
        assertEquals("[null, 7, 5, 6, 4, 2, 1, 3]", heap.toString());

        assertEquals(7, heap.removeFirst());
        assertEquals("[null, 6, 5, 3, 4, 2, 1]", heap.toString());
        assertEquals(6, heap.removeFirst());
        assertEquals("[null, 5, 4, 3, 1, 2]", heap.toString());
        assertEquals(5, heap.removeFirst());
        assertEquals("[null, 4, 2, 3, 1]", heap.toString());
        assertEquals(4, heap.removeFirst());
        assertEquals("[null, 3, 2, 1]", heap.toString());
        assertEquals(3, heap.removeFirst());
        assertEquals("[null, 2, 1]", heap.toString());
        assertEquals(2, heap.removeFirst());
        assertEquals("[null, 1]", heap.toString());
    }

    @Test
    void testFirst() {
        Integer[] values = new Integer[]{1, 2, 3, 4, 5, 6, 7};
        Heap<Integer> heap = new HeapImpl<>(values);
        assertEquals("[null, 7, 5, 6, 4, 2, 1, 3]", heap.toString());

        assertEquals(7, heap.first());
        heap.removeFirst();
        assertEquals(6, heap.first());
        heap.removeFirst();
        assertEquals(5, heap.first());
        heap.removeFirst();
        assertEquals(4, heap.first());
        heap.removeFirst();
        assertEquals(3, heap.first());
        heap.removeFirst();
        assertEquals(2, heap.first());
        heap.removeFirst();
        assertEquals(1, heap.first());
    }

    @Test
    void testSize() {
        Heap<Integer> heap = new HeapImpl<>(20);
        Integer[] values = new Integer[]{7, 5, 6, 4, 2, 1, 3};
        for (int val : values) {
            heap.insert(val);
        }
        assertEquals("[null, 7, 5, 6, 4, 2, 1, 3]", heap.toString());

        assertEquals(7, heap.size());
        heap.insert(12);
        assertEquals(8, heap.size());
        heap.insert(10);
        assertEquals(9, heap.size());
        heap.insert(11);
        assertEquals(10, heap.size());
        heap.insert(9);
        assertEquals(11, heap.size());
        heap.insert(16);
        assertEquals(12, heap.size());
        heap.insert(14);
        assertEquals(13, heap.size());

        heap.removeFirst();
        assertEquals(12, heap.size());
        heap.removeFirst();
        assertEquals(11, heap.size());
        heap.removeFirst();
        assertEquals(10, heap.size());
        heap.removeFirst();
        assertEquals(9, heap.size());
        heap.removeFirst();
        assertEquals(8, heap.size());
    }

}