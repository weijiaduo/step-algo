package com.wjd.algorithm.binary;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LastLessEqualSearchTest {

    @Test
    void search1() {
        Search search = new LastLessEqualSearch();
        int[] arr = {1, 2, 2, 3, 5, 5, 6, 7};
        int target = 4;
        int expect = -1;
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] <= target) {
                expect = i;
                break;
            }
        }
        assertEquals(expect, search.search(arr, target));
    }

    @Test
    void search2() {
        Search search = new LastLessEqualSearch(2);
        int[] arr = {1, 2, 2, 3, 5, 5, 6, 7};
        int target = 4;
        int expect = -1;
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] <= target) {
                expect = i;
                break;
            }
        }
        assertEquals(expect, search.search(arr, target));
    }

    @Test
    void search3() {
        Search search = new LastLessEqualSearch(3);
        int[] arr = {1, 2, 2, 3, 5, 5, 6, 7};
        int target = 4;
        int expect = -1;
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] <= target) {
                expect = i;
                break;
            }
        }
        assertEquals(expect, search.search(arr, target));
    }

}