package com.wjd.structure.stack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListStackTest {

    @Test
    void push() {
        Stack stack = new ListStack();
        stack.push(1);
        stack.push(2);
        assertEquals(2, stack.size());
        stack.push(3);
        assertEquals(3, stack.size());
    }

    @Test
    void pop() {
        Stack stack = new ListStack();
        stack.push(1);
        stack.push(2);
        assertEquals(2, stack.pop());
        assertEquals(1, stack.pop());
        stack.push(3);
        assertEquals(3, stack.pop());
    }

    @Test
    void top() {
        Stack stack = new ListStack();
        stack.push(1);
        stack.push(2);
        assertEquals(2, stack.top());
        stack.push(3);
        assertEquals(3, stack.top());
    }

    @Test
    void size() {
        Stack stack = new ListStack();
        assertEquals(0, stack.size());
        stack.push(1);
        assertEquals(1, stack.size());
        stack.push(2);
        assertEquals(2, stack.size());
        stack.push(3);
        assertEquals(3, stack.size());
    }

    @Test
    void isEmpty() {
        Stack stack = new ListStack();
        assertTrue(stack.isEmpty());
        stack.push(1);
        assertFalse(stack.isEmpty());
        stack.pop();
        assertTrue(stack.isEmpty());
    }

    @Test
    void isFull() {
        Stack stack = new ListStack();
        assertFalse(stack.isFull());
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertFalse(stack.isFull());
        stack.pop();
        assertFalse(stack.isFull());
    }

}