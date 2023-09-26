package com.wjd.algorithm.strings.regex.impl;

import com.wjd.algorithm.strings.regex.Pattern;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NFAPatternTest {

    @Test
    void match() {
        String[][] input = {
                {"(A*B|AC)D", "AAAABD"},
                {"(A*B|AC)D", "AAAAC"},
                {"(a|(bc)*d)*", "abcbcd"},
                {"(a|(bc)*d)*", "abcbcbcdaaaabcbcdaaaddd"}
        };
        Boolean[] expect = {
                true,
                false,
                true,
                true
        };
        for (int i = 0; i < input.length; i++) {
            Pattern pattern = new NFAPattern(input[i][0]);
            boolean ret = pattern.match(input[i][1]);
            assertEquals(expect[i], ret);
        }
    }

}