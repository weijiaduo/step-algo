package com.wjd.algorithm.strings.sorts;

import com.wjd.algorithm.strings.utils.FileStringsReader;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class Quick3WayTest {

    @Test
    void sortEquals() {
        String[] strings = new FileStringsReader(Quick3Way.class, "words3.txt").read();
        String[] expect = Arrays.copyOf(strings, strings.length);
        Arrays.sort(expect);
        System.out.println(Arrays.toString(strings));

        Sort sort = new Quick3Way();
        sort.sort(strings);
        System.out.println(Arrays.toString(strings));
        assertArrayEquals(expect, strings);
    }

    @Test
    void sortDiff() {
        String[] strings = new FileStringsReader(Quick3Way.class, "shells.txt").read();
        String[] expect = Arrays.copyOf(strings, strings.length);
        Arrays.sort(expect);
        System.out.println(Arrays.toString(strings));

        Sort sort = new Quick3Way();
        sort.sort(strings);
        System.out.println(Arrays.toString(strings));
        assertArrayEquals(expect, strings);
    }

}