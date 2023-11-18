package com.wjd.algorithm.strings.trick;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ManacherTest {

    @Test
    void compute() {
        String[] strs = new String[]{"babad", "cbbd"};
        String[] expects = new String[]{"bab", "bb"};
        for (int i = 0; i < strs.length; i++) {
            String actual = new Manacher().compute(strs[i]);
            assertEquals(expects[i], actual);
        }
    }

}