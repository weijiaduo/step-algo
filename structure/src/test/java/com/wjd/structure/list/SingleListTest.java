package com.wjd.structure.list;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SingleListTest {

    @Test
    void get() {
        List list = new SingleList();
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals(3, list.get(2));
    }

    @Test
    void add() {
        List list = new SingleList();
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals(3, list.get(2));
        assertEquals(2, list.get(1));
        assertEquals(1, list.get(0));
    }

    @Test
    void insert() {
        List list = new SingleList();
        list.insert(0, 1);
        list.insert(0, 2);
        list.insert(1, 3);
        assertEquals(2, list.get(0));
        assertEquals(3, list.get(1));
        assertEquals(1, list.get(2));
    }

    @Test
    void remove() {
        List list = new SingleList();
        list.insert(0, 1);
        list.insert(0, 2);
        list.insert(0, 3);
        assertEquals(3, list.remove(0));
        assertEquals(1, list.get(1));
    }

    @Test
    void contains() {
        List list = new SingleList();
        list.add(1);
        assertFalse(list.contains(2));
        list.add(2);
        list.add(3);
        assertTrue(list.contains(2));
        assertFalse(list.contains(4));
    }

    @Test
    void size() {
        List list = new SingleList();
        list.add(1);
        assertEquals(1, list.size());
        list.add(2);
        list.add(3);
        assertEquals(3, list.size());
        list.remove(2);
        assertEquals(2, list.size());
    }

    @Test
    void tempTest() {
        List list = new SingleList();
        list.insert(0, 7);
        list.insert(0, 2);
        list.insert(0, 1);
        list.insert(3, 0);
        list.remove(2);
        list.insert(0, 6);
        list.add(4);
        assertEquals(4, list.get(4));
    }

}