package com.wjd.structure.tree.trie;

import java.util.List;

/**
 * 字母字典树
 *
 * @author weijiaduo
 * @since 2022/7/8
 */
public class LetterTrie implements Trie {

    /**
     * 基础字母，相对索引
     */
    char base = 'a';
    /**
     * 下一层节点
     */
    LetterTrie[] children = new LetterTrie[26];
    /**
     * 是否是字符串末尾节点
     */
    boolean isEnd = false;

    public LetterTrie() {
        this(false);
    }

    public LetterTrie(boolean upper) {
        base = upper ? 'A' : 'a';
    }

    /**
     * 构建字典树
     *
     * @param words 初始化单词列表
     * @return 字典树
     */
    public static LetterTrie build(List<String> words) {
        LetterTrie trie = new LetterTrie();
        for (String word : words) {
            trie.insert(word);
        }
        return trie;
    }

    /**
     * 插入单词
     *
     * @param word 单词
     */
    @Override
    public void insert(String word) {
        LetterTrie cur = this;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            LetterTrie trie = cur.children[ch - base];
            if (trie == null) {
                trie = new LetterTrie();
                cur.children[ch - base] = trie;
            }
            cur = trie;
        }
        cur.isEnd = true;
    }

    /**
     * 是否包含指定单词
     *
     * @param word 单词
     * @return 单词是否存在
     */
    @Override
    public boolean search(String word) {
        LetterTrie trie = searchPrefix(word);
        return trie != null && trie.isEnd;
    }

    /**
     * 是否包含指定前缀
     *
     * @param prefix 前缀
     * @return 是否有前缀
     */
    @Override
    public boolean startsWith(String prefix) {
        return searchPrefix(prefix) != null;
    }

    /**
     * 搜索指定的前缀
     *
     * @param prefix 前缀
     * @return 前缀的最后一个节点
     */
    private LetterTrie searchPrefix(String prefix) {
        LetterTrie cur = this;
        for (int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);
            cur = cur.children[ch - base];
            if (cur == null) {
                return null;
            }
        }
        return cur;
    }

    /**
     * 查找指定单词的最短路径
     *
     * @param word 单词
     * @return 最短路径/单词本身
     */
    @Override
    public String minPrefix(String word) {
        LetterTrie cur = this;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            cur = cur.children[ch - base];
            if (cur == null) {
                return word;
            }
            if (cur.isEnd) {
                return word.substring(0, i + 1);
            }
        }
        return null;
    }

    /**
     * 是否匹配字符串表达式
     *
     * @param word 字符串表达式
     * @return true/false
     */
    @Override
    public boolean match(String word) {
        LetterTrie trie = dfsMatch(this, word, 0);
        return trie != null && trie.isEnd;
    }

    /**
     * 匹配字符串
     */
    private LetterTrie dfsMatch(LetterTrie root, String word, int index) {
        if (root == null) {
            return null;
        }
        if (index == word.length()) {
            return root.isEnd ? root : null;
        }
        char ch = word.charAt(index);
        if (Character.isLetter(ch)) {
            LetterTrie child = root.children[ch - base];
            if (child == null) {
                return null;
            }
            return dfsMatch(child, word, index + 1);
        } else {
            // . 可以匹配任意字符
            for (LetterTrie t : root.children) {
                LetterTrie ret = dfsMatch(t, word, index + 1);
                if (ret != null) {
                    return ret;
                }
            }
            return null;
        }
    }

}
