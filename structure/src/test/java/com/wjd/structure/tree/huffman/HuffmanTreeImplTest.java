package com.wjd.structure.tree.huffman;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HuffmanTreeImplTest {

    @Test
    void testGetCode() {
        Map<String, Integer> weightMap = new HashMap<>();
        weightMap.put("C", 32);
        weightMap.put("D", 41);
        weightMap.put("E", 120);
        weightMap.put("K", 7);
        weightMap.put("L", 42);
        weightMap.put("M", 24);
        weightMap.put("U", 37);
        weightMap.put("Z", 2);

        HuffmanTree huffmanTree = HuffmanTreeImpl.build(weightMap);
        assertEquals("1110", huffmanTree.getCode("C"));
        assertEquals("101", huffmanTree.getCode("D"));
        assertEquals("0", huffmanTree.getCode("E"));
        assertEquals("111101", huffmanTree.getCode("K"));
        assertEquals("110", huffmanTree.getCode("L"));
        assertEquals("11111", huffmanTree.getCode("M"));
        assertEquals("100", huffmanTree.getCode("U"));
        assertEquals("111100", huffmanTree.getCode("Z"));
    }
}