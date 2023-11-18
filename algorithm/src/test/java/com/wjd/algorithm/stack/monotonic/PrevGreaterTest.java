package com.wjd.algorithm.stack.monotonic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class PrevGreaterTest {

    @Test
    void forward() {
        int[] arr = new int[]{73, 74, 75, 71, 69, 72, 76, 73};
        int[] expect = new int[]{-1, -1, -1, 2, 3, 2, -1, 6};
        int[] actual = new PrevGreater().forward(arr);
        assertArrayEquals(expect, actual);
    }

    @Test
    void backward() {
        int[] arr = new int[]{73, 74, 75, 71, 69, 72, 76, 73};
        int[] expect = new int[]{-1, -1, -1, 2, 3, 2, -1, 6};
        int[] actual = new PrevGreater().backward(arr);
        assertArrayEquals(expect, actual);
    }

}