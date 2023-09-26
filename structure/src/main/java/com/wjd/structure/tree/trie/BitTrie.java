package com.wjd.structure.tree.trie;

import java.util.List;

/**
 * 二进制位字典树
 *
 * @author weijiaduo
 * @since 2022/7/8
 */
public class BitTrie {

    static class Node {
        // 子节点
        Node[] children = new Node[2];
        // 是否是字符串末尾节点
        boolean isEnd = false;
    }

    /**
     * 根节点
     */
    private Node root;

    /**
     * 构建字典树
     *
     * @param numbers 初始化单词列表
     * @return 字典树
     */
    public static BitTrie build(List<Integer> numbers) {
        BitTrie trie = new BitTrie();
        for (Integer number : numbers) {
            trie.insert(number);
        }
        return trie;
    }

    /**
     * 插入单词
     *
     * @param number 单词
     */
    public void insert(Integer number) {
        root = insert(root, number, Integer.SIZE - 1);
    }

    private Node insert(Node root, int number, int index) {
        if (root == null) {
            root = new Node();
        }
        if (index < 0) {
            root.isEnd = true;
            return root;
        }
        int value = (number >> index) & 1;
        root.children[value] = insert(root.children[value], number, index - 1);
        return root;
    }

    /**
     * 最大的异或值
     *
     * @param num 要异或的数值
     * @return 最大异或值
     */
    public int maxXOR(int num) {
        int ret = 0;
        Node cur = root;
        for (int i = Integer.SIZE - 1; i >= 0; i--) {
            int value = (num >> i) & 1;
            int xor = value;
            if (cur != null) {
                if (cur.children[1 - value] != null) {
                    xor = 1;
                    cur = cur.children[1 - value];
                } else {
                    xor = 0;
                    cur = cur.children[value];
                }
            }
            ret = (ret << 1) | xor;
        }
        return ret;
    }

}
