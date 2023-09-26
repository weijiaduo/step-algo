package com.wjd.algorithm.sort;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class BucketSortTest {

    @Test
    void sort() {
        Sort sort = new BucketSort();
        long start = System.nanoTime();
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            int size = 50 + random.nextInt(150);
            int[] expect = new int[size];
            for (int j = 0; j < size; j++) {
                expect[j] = random.nextInt(1000);
            }
            int[] actual = Arrays.copyOf(expect, expect.length);
            Arrays.sort(expect);
            sort.sort(actual);
            assertArrayEquals(actual, expect);
        }
        System.out.println(System.nanoTime() - start);
    }
}