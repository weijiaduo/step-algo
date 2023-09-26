package com.wjd.structure.queue;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CircularDequeTest {

    @Test
    public void test() {
        CircularDeque circularDeque = new CircularDeque(3); // 设置容量大小为3
        assertTrue(circularDeque.insertLast(1));
        assertTrue(circularDeque.insertLast(2));
        assertTrue(circularDeque.insertFront(3));
        assertFalse(circularDeque.insertFront(4));
        assertEquals(2, circularDeque.getRear());
        assertTrue(circularDeque.isFull());
        assertTrue(circularDeque.deleteLast());
        assertTrue(circularDeque.insertFront(4));
        assertEquals(4, circularDeque.getFront());
        assertTrue(circularDeque.deleteFront());
    }

    @Test
    public void test2() {
        CircularDeque circularDeque = new CircularDeque(4); // 设置容量大小为3
        assertTrue(circularDeque.insertFront(9));
        assertTrue(circularDeque.deleteLast());
        assertEquals(-1, circularDeque.getRear());
        assertEquals(-1, circularDeque.getFront());
        assertEquals(-1, circularDeque.getFront());
        assertFalse(circularDeque.deleteFront());
        assertTrue(circularDeque.insertFront(6));
        assertTrue(circularDeque.insertLast(5));
        assertTrue(circularDeque.insertFront(9));
        assertEquals(9, circularDeque.getFront());
        assertTrue(circularDeque.insertFront(6));
    }

}