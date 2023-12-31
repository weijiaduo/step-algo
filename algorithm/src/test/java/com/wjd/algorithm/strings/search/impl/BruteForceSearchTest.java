package com.wjd.algorithm.strings.search.impl;

import com.wjd.algorithm.strings.search.BruteForceSearch;
import com.wjd.algorithm.strings.search.Search;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BruteForceSearchTest {

    @Test
    void search() {
        String[][] inputs = {
                {"abracadabra", "abacadabrabracabracadabrabrabracad"},
                {"rab", "abacadabrabracabracadabrabrabracad"},
                {"bcara", "abacadabrabracabracadabrabrabracad"},
                {"rabrabracad", "abacadabrabracabracadabrabrabracad"},
                {"abacad", "abacadabrabracabracadabrabrabracad"},
                {"ABABAC", "BCBAABACAABABACAA"},
                {"aabaaaba", "ccaabaabaabaaabaab"},
                {"aabaaabb", "ccaabaabaabaaabaab"},
                {"cttacttac", "cgtgcctacttacttacttacttacgcgaa"}
        };
        Integer[] expects = {
                14,
                8,
                -1,
                23,
                0,
                9,
                8,
                -1,
                8
        };
        for (int i = 0; i < inputs.length; i++) {
            Search bfs = new BruteForceSearch(inputs[i][0]);
            assertEquals(expects[i], bfs.search(inputs[i][1]));
        }
    }

}