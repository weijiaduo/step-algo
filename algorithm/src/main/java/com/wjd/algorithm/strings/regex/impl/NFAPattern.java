package com.wjd.algorithm.strings.regex.impl;

import com.wjd.algorithm.graph.Search;
import com.wjd.algorithm.graph.directed.search.DirectedDFS;
import com.wjd.algorithm.strings.regex.Pattern;
import com.wjd.structure.graph.directed.Digraph;
import com.wjd.structure.graph.directed.impl.ListDigraph;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * NFA 非确定有限状态自动机
 * <p>
 * 暂支持 3 种规则：
 * <p>
 * 1. 连接操作，如 AB
 * <p>
 * 2. 或操作，如 A|B
 * <p>
 * 3. 闭包操作，如 A*
 *
 * @author weijiaduo
 * @since 2023/4/13
 */
public class NFAPattern implements Pattern {

    /**
     * 总共多少种状态
     */
    private int M;
    /**
     * 匹配转换（1.连接操作）
     */
    private char[] mt;
    /**
     * ε 空转换（2.或操作 3.闭包操作）
     */
    private Digraph et;

    public NFAPattern(String pattern) {
        initNFA(pattern);
    }

    @Override
    public boolean match(String txt) {
        return recognize(txt);
    }

    /**
     * 初始化 NFA 自动机
     *
     * @param pattern 模式串
     */
    private void initNFA(String pattern) {
        // 匹配转换（连接操作）
        mt = pattern.toCharArray();
        // 空转换（或操作、闭包操作）
        M = pattern.length();
        et = new ListDigraph(M + 1);
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < M; i++) {
            int lp = i;
            char c = mt[i];
            // 或操作
            if (c == '(' || c == '|') {
                stack.push(i);
            } else if (c == ')') {
                int or = stack.pop();
                if (mt[or] == '|') {
                    // 左括号索引
                    lp = stack.pop();
                    // 左括号指向或符号后一个
                    et.addEdge(lp, or + 1);
                    // 或符号指向右括号
                    et.addEdge(or, i);
                } else {
                    // 左括号索引
                    lp = or;
                }
            }
            // 闭包操作
            if (i < M - 1 && mt[i + 1] == '*') {
                et.addEdge(lp, i + 1);
                et.addEdge(i + 1, lp);
            }
            // 空转换
            if (c == '(' || c == ')' || c == '*') {
                et.addEdge(i, i + 1);
            }
        }
    }

    /**
     * 匹配文本
     *
     * @param txt 文本
     * @return 是否匹配
     */
    private boolean recognize(String txt) {
        Set<Integer> pc = epsilonMatch(0);
        for (int i = 0; i < txt.length(); i++) {
            char c = txt.charAt(i);
            Set<Integer> next = new HashSet<>();
            for (int j : pc) {
                if (j >= M) {
                    continue;
                }
                // 匹配转换（连接操作）
                if (mt[j] == c || mt[j] == '.') {
                    // 空转换（或操作、闭包操作）
                    next.addAll(epsilonMatch(j + 1));
                }
            }
            pc = next;
        }
        return pc.contains(M);
    }

    /**
     * 空转换集合
     *
     * @param s 起始状态
     * @return 可以通过空转换得到的所有状态
     */
    private Set<Integer> epsilonMatch(int s) {
        Set<Integer> pc = new HashSet<>();
        Search search = new DirectedDFS(et, s);
        for (int v = 0; v < et.vs(); v++) {
            if (search.marked(v)) {
                pc.add(v);
            }
        }
        return pc;
    }

}
