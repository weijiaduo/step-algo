package com.wjd.structure.queue;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayQueueTest {

    @Test
    void enqueue() {
        Queue queue = new ArrayQueue(3);
        assertTrue(queue.enqueue(1));
        assertTrue(queue.enqueue(2));
        assertTrue(queue.enqueue(3));
        assertFalse(queue.enqueue(4));
        assertFalse(queue.enqueue(5));
    }

    @Test
    void dequeue() {
        Queue queue = new ArrayQueue(3);
        assertTrue(queue.enqueue(1));
        assertTrue(queue.enqueue(2));
        assertTrue(queue.enqueue(3));
        assertEquals(1, queue.dequeue());
        assertEquals(2, queue.dequeue());
        assertEquals(3, queue.dequeue());
    }

    @Test
    void size() {
        Queue queue = new ArrayQueue(3);
        assertTrue(queue.enqueue(1));
        assertEquals(1, queue.size());
        assertTrue(queue.enqueue(2));
        assertEquals(2, queue.size());
        assertTrue(queue.enqueue(3));
        assertEquals(3, queue.size());
    }

    @Test
    void isEmpty() {
        Queue queue = new ArrayQueue(3);
        assertTrue(queue.isEmpty());
        assertTrue(queue.enqueue(1));
        assertFalse(queue.isEmpty());
        assertEquals(1, queue.dequeue());
        assertTrue(queue.isEmpty());
    }

    @Test
    void isFull() {
        Queue queue = new ArrayQueue(3);
        assertFalse(queue.isFull());
        assertTrue(queue.enqueue(1));
        assertFalse(queue.isFull());
        assertTrue(queue.enqueue(2));
        assertTrue(queue.enqueue(3));
        assertTrue(queue.isFull());
        assertEquals(1, queue.dequeue());
        assertFalse(queue.isFull());
    }
}