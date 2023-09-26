package com.wjd.algorithm.binary;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FirstEqualSearchTest {

    @Test
    void search() {
        Search search = new FirstEqualSearch();
        int[] arr = {1, 2, 2, 3, 4, 4, 4, 5, 5, 6, 7};
        int target = 5;
        int expect = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                expect = i;
                break;
            }
        }
        assertEquals(expect, search.search(arr, target));
    }
}