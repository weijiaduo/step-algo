package com.wjd.algorithm.strings.regex.impl;

import com.wjd.algorithm.strings.regex.Pattern;

import java.util.*;

/**
 * Aho-Corasick 算法，俗称 AC 自动机算法
 * <p>
 * 多模式匹配算法
 *
 * @author weijiaduo
 * @since 2023/4/25
 */
public class AhoCorasickPattern implements Pattern {

    /**
     * 字符集大小（假设是小写字母）
     */
    private static final int R = 26;
    /**
     * 基础字母，相对索引
     */
    private static final char BASE = 'a';

    /**
     * AC 字典树根节点
     */
    private Node root;
    /**
     * 匹配结果
     * <p>
     * 模式串 -> List[匹配起始索引]
     */
    private Map<String, List<Integer>> groups;

    /**
     * @param patterns 多模式串
     */
    public AhoCorasickPattern(List<String> patterns) {
        buildTrie(patterns);
        buildFail();
    }

    /**
     * 获取匹配结果
     *
     * @return 匹配结果
     */
    public Map<String, List<Integer>> groups() {
        return groups;
    }

    @Override
    public boolean match(String txt) {
        groups = new HashMap<>();
        Node p = root;
        for (int i = 0; i < txt.length(); i++) {
            char ch = txt.charAt(i);
            // 寻找可匹配的模式串
            while (p != null && !p.hasChild(ch)) {
                p = p.fail;
            }

            // 没有可匹配的，重新开始
            if (p == null) {
                p = root;
                continue;
            }

            // 找到了可匹配的节点
            p = p.getChild(ch);

            // 收集匹配成功的模式串
            collectMatches(p, i);
        }
        return !groups.isEmpty();
    }

    /**
     * 收集匹配成功的模式串
     *
     * @param node  当前匹配节点
     * @param index 主串匹配的结尾索引
     */
    private void collectMatches(Node node, int index) {
        Node cur = node;
        while (cur != null) {
            if (!cur.isEnd) {
                cur = cur.fail;
                continue;
            }
            List<Integer> indexes = groups
                    .computeIfAbsent(cur.data, k -> new ArrayList<>());
            indexes.add(index - cur.data.length() + 1);
            cur = cur.fail;
        }
    }

    /**
     * 构建字典树
     *
     * @param patterns 多模式串
     */
    private void buildTrie(List<String> patterns) {
        root = new Node(' ');
        for (String pattern : patterns) {
            insertTrie(root, pattern);
        }
    }

    /**
     * 插入新模式串
     *
     * @param root    当前节点
     * @param pattern 模式串
     */
    private void insertTrie(Node root, String pattern) {
        Node cur = root;
        for (char ch : pattern.toCharArray()) {
            if (!cur.hasChild(ch)) {
                cur.setChild(ch);
            }
            cur = cur.getChild(ch);
        }
        cur.isEnd = true;
        cur.data = pattern;
    }

    /**
     * 构建失败指针
     */
    private void buildFail() {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        root.fail = null;
        while (!queue.isEmpty()) {
            Node p = queue.poll();
            Node pf = p.fail;
            for (Node c : p.children) {
                if (c == null) {
                    continue;
                }
                c.fail = findFail(c, pf);
                queue.offer(c);
            }
        }
    }

    /**
     * 寻找当前节点的失败指针
     *
     * @param node 当前节点
     * @param pf   上一个失败指针
     * @return 当前节点的失败指针
     */
    private Node findFail(Node node, Node pf) {
        if (pf == null) {
            // 默认失败指针是根节点
            return root;
        }
        Node nf = pf.getChild(node.value);
        if (nf != null) {
            return nf;
        } else {
            return findFail(node, pf.fail);
        }
    }

    private static class Node {

        // 节点值
        char value;
        // 是否是结尾
        boolean isEnd;
        // isEnd=true 时记录模式串
        String data;
        // 子节点
        Node[] children;
        // 失败指针
        Node fail;

        Node(char value) {
            this.value = value;
            children = new Node[R];
            fail = null;
        }

        boolean hasChild(char ch) {
            return children[ch - BASE] != null;
        }

        Node getChild(char ch) {
            return children[ch - BASE];
        }

        void setChild(char ch) {
            children[ch - BASE] = new Node(ch);
        }
    }

}
