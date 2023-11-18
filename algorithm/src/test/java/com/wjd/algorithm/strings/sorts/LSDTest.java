package com.wjd.algorithm.strings.sorts;

import com.wjd.algorithm.strings.utils.FileStringsReader;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class LSDTest {

    @Test
    void sort() {
        String[] strings = new FileStringsReader(LSD.class, "words3.txt").read();
        String[] expect = Arrays.copyOf(strings, strings.length);
        Arrays.sort(expect);
        System.out.println(Arrays.toString(strings));

        Sort sort = new LSD();
        sort.sort(strings);
        System.out.println(Arrays.toString(strings));
        assertArrayEquals(expect, strings);
    }

}