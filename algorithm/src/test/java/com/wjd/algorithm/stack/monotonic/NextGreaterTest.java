package com.wjd.algorithm.stack.monotonic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class NextGreaterTest {

    @Test
    void forward() {
        int[] arr = new int[]{73, 74, 75, 71, 69, 72, 76, 73};
        int[] expect = new int[]{1, 2, 6, 5, 5, 6, -1, -1};
        int[] actual = new NextGreater().forward(arr);
        assertArrayEquals(expect, actual);
    }

    @Test
    void backward() {
        int[] arr = new int[]{73, 74, 75, 71, 69, 72, 76, 73};
        int[] expect = new int[]{1, 2, 6, 5, 5, 6, -1, -1};
        int[] actual = new NextGreater().backward(arr);
        assertArrayEquals(expect, actual);
    }

}