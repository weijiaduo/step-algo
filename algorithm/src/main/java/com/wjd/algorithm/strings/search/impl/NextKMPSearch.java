package com.wjd.algorithm.strings.search.impl;

import com.wjd.algorithm.strings.search.Search;

/**
 * KMP(Next Match Table) 算法
 *
 * @author weijiaduo
 * @since 2023/3/28
 */
public class NextKMPSearch implements Search {

    @Override
    public int search(String pat, String txt) {
        int[] next = getNext(pat);
        int i = 0, j = 0;
        int m = pat.length(), n = txt.length();
        while (i < n && j < m) {
            if (j == -1 || txt.charAt(i) == pat.charAt(j)) {
                // 匹配主串和模式串的下一个字符
                i++;
                j++;
            } else {
                // 一次性滑到最长相等前后缀的位置开始匹配
                j = next[j];
            }
        }
        // 匹配成功
        if (j == m) {
            return i - j;
        }
        return -1;
    }

    /**
     * 获取 Next Match Table
     * <p>
     * next[i] 表示模式串下一次进行比较的索引位置
     * <p>
     * next[i] 也表示 pat[0...j-1] 的最长相等前后缀的长度
     * <p>
     * next 数组实际就是 pmt 数组往右移动一位得到的
     *
     * @param pat 模式串
     * @return next 数组
     */
    private int[] getNext(String pat) {
        int m = pat.length();
        int[] next = new int[m];
        next[0] = -1;
        int i = 0, j = -1;
        while (i < m - 1) {
            if (j == -1 || pat.charAt(i) == pat.charAt(j)) {
                i++;
                j++;
                // 最长相等前后缀长度，索引右移了1位
                next[i] = j;
            } else {
                // 找次长相等前后缀递归匹配
                j = next[j];
            }
        }
        return next;
    }

}
