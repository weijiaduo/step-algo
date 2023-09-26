package com.wjd.structure.hashtable;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OpenHashTableTest {

    @Test
    void put() {
        HashTable hashTable = new OpenHashTable();
        assertEquals(0, hashTable.size());
        hashTable.put(1, 1);
        hashTable.put(2, 2);
        hashTable.put(3, 3);
        assertEquals(3, hashTable.size());
        hashTable.put(4, 4);
        assertEquals(4, hashTable.size());
        hashTable.put(5, 5);
        assertEquals(5, hashTable.size());
    }

    @Test
    void remove() {
        HashTable hashTable = new OpenHashTable();
        hashTable.put(1, 1);
        hashTable.put(2, 2);
        hashTable.put(3, 3);
        hashTable.put(4, 4);
        assertEquals(4, hashTable.size());
        hashTable.remove(3);
        assertEquals(3, hashTable.size());
        hashTable.remove(2);
        assertEquals(2, hashTable.size());
        hashTable.remove(2);
        assertEquals(2, hashTable.size());
        hashTable.remove(1);
        assertEquals(1, hashTable.size());
        hashTable.remove(4);
        assertEquals(0, hashTable.size());
        hashTable.remove(4);
        assertEquals(0, hashTable.size());
    }

    @Test
    void get() {
        HashTable hashTable = new OpenHashTable();
        hashTable.put(1, 1);
        hashTable.put(2, 2);
        hashTable.put(3, 3);
        hashTable.put(4, 4);
        assertEquals(2, hashTable.get(2));
        assertEquals(4, hashTable.get(4));
        hashTable.put(4, 2);
        assertEquals(2, hashTable.get(4));
        hashTable.put(4, 1);
        assertEquals(1, hashTable.get(4));
        hashTable.remove(2);
        assertEquals(-1, hashTable.get(2));
    }

    @Test
    void contains() {
        HashTable hashTable = new OpenHashTable();
        hashTable.put(1, 1);
        hashTable.put(2, 2);
        hashTable.put(3, 3);
        hashTable.put(4, 4);
        assertTrue(hashTable.contains(3));
        assertTrue(hashTable.contains(4));
        assertFalse(hashTable.contains(5));
        hashTable.remove(2);
        assertFalse(hashTable.contains(2));
    }
}