package com.wjd.algorithm.binary;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KthSearchTest {

    @Test
    void search1() {
        int[] arr = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        int k = 4;
        int actual = new KthSearch().search(arr, k);
        assertEquals(4, actual);
    }

    @Test
    void search2() {
        int[] arr = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        int k = 4;
        int actual = new KthSearch(2).search(arr, k);
        assertEquals(4, actual);
    }

}