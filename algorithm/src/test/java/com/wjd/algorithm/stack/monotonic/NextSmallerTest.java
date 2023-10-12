package com.wjd.algorithm.stack.monotonic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NextSmallerTest {

    @Test
    void forward() {
        int[] arr = new int[]{73, 74, 75, 71, 69, 72, 76, 73};
        int[] expect = new int[]{3, 3, 3, 4, -1, -1, 7, -1};
        int[] actual = new NextSmaller().forward(arr);
        assertArrayEquals(expect, actual);
    }

    @Test
    void backward() {
        int[] arr = new int[]{73, 74, 75, 71, 69, 72, 76, 73};
        int[] expect = new int[]{3, 3, 3, 4, -1, -1, 7, -1};
        int[] actual = new NextSmaller().backward(arr);
        assertArrayEquals(expect, actual);
    }

}