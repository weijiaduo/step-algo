package com.wjd.algorithm.binary;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LastEqualSearchTest {

    @Test
    void search() {
        Search search = new LastEqualSearch();
        int[] arr = {1, 2, 2, 3, 4, 4, 4, 5, 5, 6, 7};
        int target = 5;
        int expect = -1;
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] == target) {
                expect = i;
                break;
            }
        }
        assertEquals(expect, search.search(arr, target));
    }
}