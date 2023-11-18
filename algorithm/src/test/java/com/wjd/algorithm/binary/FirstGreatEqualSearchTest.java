package com.wjd.algorithm.binary;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FirstGreatEqualSearchTest {

    @Test
    void search1() {
        Search search = new FirstGreatEqualSearch();
        int[] arr = {1, 2, 2, 3, 5, 5, 6, 7};
        int target = 4;
        int expect = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= target) {
                expect = i;
                break;
            }
        }
        assertEquals(expect, search.search(arr, target));
    }

    @Test
    void search2() {
        Search search = new FirstGreatEqualSearch(2);
        int[] arr = {1, 2, 2, 3, 5, 5, 6, 7};
        int target = 4;
        int expect = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= target) {
                expect = i;
                break;
            }
        }
        assertEquals(expect, search.search(arr, target));
    }

    @Test
    void search3() {
        Search search = new FirstGreatEqualSearch(3);
        int[] arr = {1, 2, 2, 3, 5, 5, 6, 7};
        int target = 4;
        int expect = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= target) {
                expect = i;
                break;
            }
        }
        assertEquals(expect, search.search(arr, target));
    }

}