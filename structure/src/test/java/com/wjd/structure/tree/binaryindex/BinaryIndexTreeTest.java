package com.wjd.structure.tree.binaryindex;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BinaryIndexTreeTest {

    @Test
    void update() {
        int[] nums = {3, 2, 1, 3};
        int[] expect = {3, 5, 1, 9};
        BinaryIndexTree bit = new BinaryIndexTree(nums.length + 1);
        for (int i = 0; i < nums.length; i++) {
            bit.update(i + 1, nums[i]);
        }
        for (int i = 0; i < nums.length; i++) {
            assertEquals(expect[i], bit.tree[i + 1]);
        }
    }

    @Test
    void query() {
        int[] nums = {3, 2, 1, 3};
        int[] expect = {3, 5, 6, 9};
        BinaryIndexTree bit = new BinaryIndexTree(nums.length + 1);
        for (int i = 0; i < nums.length; i++) {
            bit.update(i + 1, nums[i]);
        }
        for (int i = 0; i < nums.length; i++) {
            assertEquals(expect[i], bit.query(0, i + 1));
        }
    }
}