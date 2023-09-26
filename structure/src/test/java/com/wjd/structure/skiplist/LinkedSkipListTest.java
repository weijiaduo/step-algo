package com.wjd.structure.skiplist;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LinkedSkipListTest {

    @Test
    void search() {
        SkipList<Integer> skipList = new LinkedSkipList<>(10);
        skipList.add(1);
        skipList.add(2);
        skipList.add(3);
        System.out.println(skipList);
        assertFalse(skipList.search(0));   // 返回 false
        skipList.add(4);
        System.out.println(skipList);
        assertTrue(skipList.search(1));   // 返回 true
        System.out.println(skipList);
    }

    @Test
    void add() {
        SkipList<Integer> skipList = new LinkedSkipList<>(10);
        skipList.add(1);
        skipList.add(2);
        skipList.add(3);
        System.out.println(skipList);
    }

    @Test
    void erase() {
        SkipList<Integer> skipList = new LinkedSkipList<>(10);
        skipList.add(1);
        skipList.add(2);
        skipList.add(3);
        System.out.println(skipList);
        assertTrue(skipList.search(1));    // 返回 true
        assertFalse(skipList.erase(0));    // 返回 false，0 不在跳表中
        System.out.println(skipList);
        assertTrue(skipList.erase(1));     // 返回 true
        assertFalse(skipList.search(1));   // 返回 false，1 已被擦除
        System.out.println(skipList);
    }
}