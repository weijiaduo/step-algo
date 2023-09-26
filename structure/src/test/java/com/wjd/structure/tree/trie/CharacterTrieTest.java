package com.wjd.structure.tree.trie;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CharacterTrieTest {

    @Test
    void insert() {
        List<String> words = Arrays.asList("she", "sells", "sea", "shells", "by", "the", "sea", "shore");
        Trie trie = CharacterTrie.build(words);

        assertFalse(trie.search("season"));
        trie.insert("season");
        assertTrue(trie.search("season"));
    }

    @Test
    void search() {
        List<String> words = Arrays.asList("she", "sells", "sea", "shells", "by", "the", "sea", "shore");
        Trie trie = CharacterTrie.build(words);

        assertFalse(trie.search("season"));
        assertTrue(trie.search("sea"));
        assertTrue(trie.search("sells"));
        assertTrue(trie.search("shore"));
    }

    @Test
    void startsWith() {
        List<String> words = Arrays.asList("she", "sells", "sea", "shells", "by", "the", "sea", "shore");
        Trie trie = CharacterTrie.build(words);

        assertTrue(trie.startsWith("s"));
        assertTrue(trie.startsWith("se"));
        assertTrue(trie.startsWith("sea"));
        assertFalse(trie.startsWith("seas"));
        assertFalse(trie.startsWith("ss"));
    }

    @Test
    void minPrefix() {
        List<String> words = Arrays.asList("she", "sells", "sea", "shells", "by", "the", "sea", "shore");
        Trie trie = CharacterTrie.build(words);

        assertEquals("as", trie.minPrefix("as"));
        assertEquals("sea", trie.minPrefix("seacxed"));
        assertEquals("sells", trie.minPrefix("sellsedesa"));
        assertEquals("she", trie.minPrefix("shewiuej"));
    }

    @Test
    void match() {
        List<String> words = Arrays.asList("she", "sells", "sea", "shells", "by", "the", "sea", "shore");
        Trie trie = CharacterTrie.build(words);

        assertFalse(trie.match("sa"));
        assertTrue(trie.match("s.a"));
        assertTrue(trie.match("..a"));
        assertTrue(trie.match(".h."));
    }

}