package com.wjd.structure.skiplist;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author weijiaduo
 * @since 2022/07/28
 */
class SimpleSkipListTest {

    @Test
    void search() {
        SimpleSkipList skipList = new SimpleSkipList(10);
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
        SimpleSkipList skipList = new SimpleSkipList(10);
        skipList.add(1);
        skipList.add(2);
        skipList.add(3);
        System.out.println(skipList);
    }

    @Test
    void erase() {
        SimpleSkipList skipList = new SimpleSkipList(10);
        skipList.add(1);
        skipList.add(2);
        skipList.add(3);
        assertTrue(skipList.search(1));    // 返回 true
        assertFalse(skipList.erase(0));    // 返回 false，0 不在跳表中
        assertTrue(skipList.erase(1));     // 返回 true
        assertFalse(skipList.search(1));   // 返回 false，1 已被擦除
        System.out.println(skipList);
    }
}