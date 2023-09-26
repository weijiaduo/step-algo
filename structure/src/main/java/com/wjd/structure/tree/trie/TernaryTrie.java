package com.wjd.structure.tree.trie;

import java.util.List;

/**
 * 三向查找字典树
 *
 * @author weijiaduo
 * @since 2023/3/31
 */
public class TernaryTrie implements Trie {

    /**
     * 字典树节点
     */
    private static class Node {
        // 字符
        char ch;
        // 是否是结尾字符
        boolean end;
        // 小于当前字符的
        Node lt;
        // 等于当前字符的
        Node eq;
        // 大于当前字符的
        Node gt;
    }

    /**
     * 根节点
     */
    private Node root;

    /**
     * Build ternary trie.
     *
     * @param words the words
     * @return the ternary trie
     */
    public static TernaryTrie build(List<String> words) {
        TernaryTrie trie = new TernaryTrie();
        for (String word : words) {
            trie.insert(word);
        }
        return trie;
    }

    @Override
    public void insert(String word) {
        root = insert(root, word, 0);
    }

    /**
     * 插入新单词到指定节点下
     *
     * @param root  根节点
     * @param word  单词
     * @param index 单词索引
     * @return 根节点
     */
    private Node insert(Node root, String word, int index) {
        if (index >= word.length()) {
            return root;
        }
        char ch = word.charAt(index);
        if (root == null) {
            root = new Node();
            root.ch = ch;
        }
        if (ch == root.ch) {
            // 抵达单词的结尾
            if (index == word.length() - 1) {
                root.end = true;
                return root;
            }
            root.eq = insert(root.eq, word, index + 1);
        } else if (ch < root.ch) {
            root.lt = insert(root.lt, word, index);
        } else {
            root.gt = insert(root.gt, word, index);
        }
        return root;
    }

    @Override
    public boolean search(String word) {
        return search(root, word, 0);
    }

    /**
     * 在指定根节点下查找指定单词
     *
     * @param root  根节点
     * @param word  单词
     * @param index 单词索引
     * @return true/false
     */
    private boolean search(Node root, String word, int index) {
        if (root == null || index >= word.length()) {
            return false;
        }
        char ch = word.charAt(index);
        if (ch == root.ch) {
            // 匹配单词结束
            if (index == word.length() - 1) {
                return root.end;
            }
            return search(root.eq, word, index + 1);
        } else if (ch < root.ch) {
            return search(root.lt, word, index);
        } else {
            return search(root.gt, word, index);
        }
    }

    @Override
    public boolean startsWith(String prefix) {
        return startWith(root, prefix, 0);
    }

    /**
     * 是否存在某个字符串前缀
     *
     * @param root   根节点
     * @param prefix 前缀
     * @param index  前缀索引
     * @return true/false
     */
    private boolean startWith(Node root, String prefix, int index) {
        if (root == null || index >= prefix.length()) {
            return false;
        }
        char ch = prefix.charAt(index);
        if (ch == root.ch) {
            // 匹配前缀结束
            if (index == prefix.length() - 1) {
                return true;
            }
            return startWith(root.eq, prefix, index + 1);
        } else if (ch < root.ch) {
            return startWith(root.lt, prefix, index);
        } else {
            return startWith(root.gt, prefix, index);
        }
    }

    @Override
    public String minPrefix(String word) {
        int index = minPrefix(root, word, 0);
        return index < 0 ? word : word.substring(0, index + 1);
    }

    /**
     * 查找最小前缀结尾在指定单词的索引
     *
     * @param root  根节点
     * @param word  单词
     * @param index 单词索引
     * @return 最小前缀结尾在单词中的索引
     */
    private int minPrefix(Node root, String word, int index) {
        if (root == null || index >= word.length()) {
            return -1;
        }
        char ch = word.charAt(index);
        if (ch == root.ch) {
            // 找到最小前缀了
            if (root.end) {
                return index;
            }
            return minPrefix(root.eq, word, index + 1);
        } else if (ch < root.ch) {
            return minPrefix(root.lt, word, index);
        } else {
            return minPrefix(root.gt, word, index);
        }
    }

    @Override
    public boolean match(String word) {
        return match(root, word, 0);
    }

    /**
     * 在字典树中匹配指定的单词
     *
     * @param root  根节点
     * @param word  单词
     * @param index 单词索引
     * @return true/false
     */
    private boolean match(Node root, String word, int index) {
        if (root == null || index >= word.length()) {
            return false;
        }
        char ch = word.charAt(index);
        if (ch == '.') {
            // 匹配单词结束
            if (index == word.length() - 1) {
                return root.end;
            }
            // 因为是模糊匹配，所以需要遍历所有分支
            return match(root.eq, word, index + 1)
                    || match(root.lt, word, index + 1)
                    || match(root.gt, word, index + 1)
                    || match(root.lt, word, index)
                    || match(root.gt, word, index);
        } else if (ch == root.ch) {
            // 匹配单词结束
            if (index == word.length() - 1) {
                return root.end;
            }
            return match(root.eq, word, index + 1);
        } else if (ch < root.ch) {
            return match(root.lt, word, index);
        } else {
            return match(root.gt, word, index);
        }
    }

}
