package com.wjd.algorithm.stack.monotonic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class PrevSmallerTest {

    @Test
    void forward() {
        int[] arr = new int[]{73, 74, 75, 71, 69, 72, 76, 73};
        int[] expect = new int[]{-1, 0, 1, -1, -1, 4, 5, 5};
        int[] actual = new PrevSmaller().forward(arr);
        assertArrayEquals(expect, actual);
    }

    @Test
    void backward() {
        int[] arr = new int[]{73, 74, 75, 71, 69, 72, 76, 73};
        int[] expect = new int[]{-1, 0, 1, -1, -1, 4, 5, 5};
        int[] actual = new PrevSmaller().backward(arr);
        assertArrayEquals(expect, actual);
    }

}