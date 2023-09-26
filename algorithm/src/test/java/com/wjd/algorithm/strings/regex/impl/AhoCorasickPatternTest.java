package com.wjd.algorithm.strings.regex.impl;

import com.wjd.algorithm.strings.regex.Pattern;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class AhoCorasickPatternTest {

    @Test
    void match() {
        List<String> patterns = List.of("abcdef", "abhab", "bcd", "cde", "cdfkcdf");
        String text = "bcabcdebcedfabcdefababkabhabk";
        Pattern pattern = new AhoCorasickPattern(patterns);
        assertTrue(pattern.match(text));
    }

    @Test
    void groups() {
        List<String> patterns = List.of("abcdef", "abhab", "bcd", "cde", "cdfkcdf");
        String text = "bcabcdebcedfabcdefababkabhabk";
        Map<String, List<Integer>> expect = new HashMap<>();
        expect.put("abcdef", List.of(12));
        expect.put("abhab", List.of(23));
        expect.put("bcd", List.of(3, 13));
        expect.put("cde", List.of(4, 14));

        AhoCorasickPattern pattern = new AhoCorasickPattern(patterns);
        pattern.match(text);
        Map<String, List<Integer>> groups = pattern.groups();
        assertEquals(expect, groups);
    }
}