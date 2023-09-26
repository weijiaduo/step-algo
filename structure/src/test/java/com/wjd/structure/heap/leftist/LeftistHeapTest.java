package com.wjd.structure.heap.leftist;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LeftistHeapTest {

    @Test
    void mergeEmpty() {
        LeftistHeap<Integer> nonNullHeap = new LeftistHeapImpl<>();
        LeftistHeap<Integer> nullHeap = new LeftistHeapImpl<>();

        nonNullHeap.insert(1);
        nonNullHeap.insert(2);
        nonNullHeap.insert(3);
        nonNullHeap.merge(nullHeap);
        assertEquals("[1(1), 2(0), 3(0)]", nonNullHeap.toString());

        nullHeap = new LeftistHeapImpl<>();
        nullHeap.merge(nonNullHeap);
        assertEquals("[1(1), 2(0), 3(0)]", nullHeap.toString());
    }

    @Test
    void merge() {
        LeftistHeap<Integer> heap1 = new LeftistHeapImpl<>();
        heap1.insert(10);
        heap1.insert(40);
        heap1.insert(24);
        heap1.insert(30);
        heap1.insert(36);
        heap1.insert(20);
        heap1.insert(12);
        heap1.insert(16);
        assertEquals("[10(2), 24(1), 12(1), 30(0), 36(0), 20(0), 16(0), null, null, null, null, 40(0)]", heap1.toString());
        LeftistHeap<Integer> heap2 = new LeftistHeapImpl<>();
        heap2.insert(17);
        heap2.insert(13);
        heap2.insert(11);
        heap2.insert(15);
        heap2.insert(19);
        heap2.insert(21);
        heap2.insert(23);
        assertEquals("[11(2), 15(1), 13(1), 19(0), 21(0), 17(0), 23(0)]", heap2.toString());

        heap1.merge(heap2);
        assertEquals("[10(2), 11(2), 24(1), 15(1), 12(1), 30(0), 36(0), 19(0), 21(0), 13(1), 20(0), null, " +
                "null, null, null, null, null, null, null, 17(0), 16(0), 40(0), null, null, null, 23(0)]", heap1.toString());
    }

    @Test
    void insert() {
        LeftistHeap<Integer> heap = new LeftistHeapImpl<>();
        heap.insert(5);
        assertEquals("[5(0)]", heap.toString());
        heap.insert(3);
        assertEquals("[3(0), 5(0)]", heap.toString());
        heap.insert(7);
        assertEquals("[3(1), 5(0), 7(0)]", heap.toString());
        heap.insert(9);
        assertEquals("[3(1), 5(0), 7(0), null, null, 9(0)]", heap.toString());
        heap.insert(2);
        assertEquals("[2(0), 3(1), null, 5(0), 7(0), null, null, 9(0)]", heap.toString());
        heap.insert(4);
        assertEquals("[2(1), 3(1), 4(0), 5(0), 7(0), null, null, null, null, 9(0)]", heap.toString());
    }

    @Test
    void removeFirst() {
        LeftistHeap<Integer> heap = new LeftistHeapImpl<>();
        heap.insert(5);
        heap.insert(3);
        heap.insert(7);
        heap.insert(9);
        heap.insert(2);
        heap.insert(4);
        assertEquals("[2(1), 3(1), 4(0), 5(0), 7(0), null, null, null, null, 9(0)]", heap.toString());

        Integer first = heap.removeFirst();
        assertEquals(2, first);
        assertEquals("[3(1), 5(0), 4(0), null, null, 7(0), null, 9(0)]", heap.toString());

        first = heap.removeFirst();
        assertEquals(3, first);
        assertEquals("[4(1), 7(0), 5(0), 9(0)]", heap.toString());

        first = heap.removeFirst();
        assertEquals(4, first);
        assertEquals("[5(0), 7(0), null, 9(0)]", heap.toString());

        first = heap.removeFirst();
        assertEquals(5, first);
        assertEquals("[7(0), 9(0)]", heap.toString());

        first = heap.removeFirst();
        assertEquals(7, first);
        assertEquals("[9(0)]", heap.toString());
    }

    @Test
    void first() {
        LeftistHeap<Integer> heap = new LeftistHeapImpl<>();
        heap.insert(5);
        heap.insert(3);
        heap.insert(7);
        heap.insert(9);
        heap.insert(2);
        heap.insert(4);
        assertEquals("[2(1), 3(1), 4(0), 5(0), 7(0), null, null, null, null, 9(0)]", heap.toString());

        Integer first = heap.first();
        assertEquals(2, first);
        heap.removeFirst();

        first = heap.first();
        assertEquals(3, first);
        heap.removeFirst();

        first = heap.first();
        assertEquals(4, first);
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
        LeftistHeap<Integer> heap = new LeftistHeapImpl<>();
        heap.insert(5);
        heap.insert(3);
        heap.insert(7);
        heap.insert(9);
        heap.insert(2);
        heap.insert(4);
        assertEquals("[2(1), 3(1), 4(0), 5(0), 7(0), null, null, null, null, 9(0)]", heap.toString());

        Integer size = heap.size();
        assertEquals(6, size);
        heap.removeFirst();

        size = heap.size();
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
        LeftistHeap<Integer> heap = new LeftistHeapImpl<>();
        heap.insert(5);
        heap.insert(3);
        heap.insert(7);
        heap.insert(9);
        heap.insert(2);
        heap.insert(4);
        assertEquals("[2(1), 3(1), 4(0), 5(0), 7(0), null, null, null, null, 9(0)]", heap.toString());

        assertFalse(heap.isEmpty());
        heap.removeFirst();
        heap.removeFirst();
        heap.removeFirst();
        heap.removeFirst();
        assertFalse(heap.isEmpty());

        heap.removeFirst();
        heap.removeFirst();
        assertTrue(heap.isEmpty());
    }

}
