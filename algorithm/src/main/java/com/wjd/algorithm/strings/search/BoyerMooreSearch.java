package com.wjd.algorithm.strings.search;

import java.util.Arrays;

/**
 * Boyer Moore 算法
 *
 * @author weijiaduo
 * @since 2023/3/29
 */
public class BoyerMooreSearch implements Search {

    /**
     * 字符集大小
     */
    private static final int R = 256;

    /**
     * 模式串
     */
    final String pat;
    /**
     * 坏字符数组
     */
    int[] bc;
    /**
     * 完全后缀索引
     */
    int[] suffix;
    /**
     * 相同前后缀
     */
    boolean[] prefix;

    public BoyerMooreSearch(String pat) {
        this.pat = pat;
        // 坏字符和好后缀
        generateBC(this.pat);
        generateGS(this.pat);
    }

    @Override
    public int search(String txt) {
        int m = pat.length(), n = txt.length();
        int i = 0;
        while (i <= n - m) {
            // 从后往前匹配
            int j = m - 1;
            while (j >= 0 && txt.charAt(i + j) == pat.charAt(j)) {
                j--;
            }
            // 匹配成功
            if (j < 0) {
                return i;
            }

            /* 坏字符规则 */
            int bcOffset = moveByBadChar(txt.charAt(i + j), j);
            /* 好后缀规则 */
            int gsOffset = moveByGoodSuffix(j);
            // 取坏字符和好后缀的最大偏移量
            i = i + Math.max(bcOffset, gsOffset);
        }
        return -1;
    }

    /**
     * 构建前后缀数组
     *
     * @param pat 模式串
     */
    private void generateGS(String pat) {
        // 初始化前后缀数组
        int m = pat.length();
        suffix = new int[m];
        prefix = new boolean[m];
        for (int i = 0; i < m; i++) {
            suffix[i] = -1;
            prefix[i] = false;
        }

        // 计算模式串中每个子串的位置
        for (int i = 0; i < m - 1; i++) {
            // 求 pat[0...i] 和 pat[0...m-1] 的公共后缀子串
            int j = 0;
            for (; j <= i; j++) {
                if (pat.charAt(i - j) != pat.charAt(m - 1 - j)) {
                    break;
                }
                // 完全匹配后缀
                suffix[j + 1] = i - j;
            }
            // 部分匹配后缀，前后缀相等
            if (j > i) {
                prefix[j] = true;
            }
        }
    }

    /**
     * 返回模式串往后滑动的位数
     *
     * @param pos 模式串当前位置
     */
    private int moveByGoodSuffix(int pos) {
        // 检测是否有好后缀
        int m = suffix.length;
        int gsLength = m - pos - 1;
        if (gsLength < 1) {
            return 1;
        }

        // 2. 好后缀在未匹配模式子串中存在，则将主串与模式串的好后缀进行对齐
        int index = suffix[gsLength];
        if (index != -1) {
            return pos - index + 1;
        }

        // 3. 好后缀在未匹配模式子串中只出现了部分好后缀，则将模式串与主串的部分好后缀进行对齐
        for (int i = gsLength - 1; i > 0; i--) {
            if (prefix[i]) {
                return m - i;
            }
        }

        // 1. 好后缀在未匹配模式子串中不存在，则直接滑动模式串到主串的好后缀后面
        return m;
    }

    /**
     * 构建坏字符数组
     *
     * @param pat 模式串
     */
    private void generateBC(String pat) {
        // 初始化 bc
        bc = new int[R];
        Arrays.fill(bc, -1);

        // 计算模式串中每个字符最后出现的位置
        for (int i = 0; i < pat.length(); i++) {
            int a = pat.charAt(i);
            bc[a] = i;
        }
    }

    /**
     * 返回模式串往后滑动的位数
     *
     * @param badChar 坏字符
     * @param pos     模式串当前位置
     */
    private int moveByBadChar(char badChar, int pos) {
        // 1. 坏字符在模式串中不存在，则模式串直接滑过坏字符
        int index = bc[badChar];
        if (index == -1) {
            return pos + 1;
        }

        // 2. 坏字符在模式串中存在，则将模式串的坏字符与主串的坏字符对齐
        int moves = pos - index;
        // 避免模式串倒退
        return moves > 0 ? moves : 1;
    }

}
