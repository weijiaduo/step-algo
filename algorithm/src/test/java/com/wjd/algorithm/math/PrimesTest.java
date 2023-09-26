package com.wjd.algorithm.math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class PrimesTest {

    @Test
    public void test() {
        int n = 100000;
        int[] f1 = new Primes().brute(n);
        int[] f2 = new Primes().eratosthenes(n);
        int[] f3 = new Primes().line(n);
        assertArrayEquals(f2, f1);
        assertArrayEquals(f3, f1);
    }

}