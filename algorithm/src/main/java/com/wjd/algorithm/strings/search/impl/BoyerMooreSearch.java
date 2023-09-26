package com.wjd.algorithm.strings.search.impl;

import com.wjd.algorithm.strings.search.Search;

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

    @Override
    public int search(String pat, String txt) {
        int[] bc = getBadChars(pat);
        int[][] gs = getGoodSuffix(pat);
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
            int bcOffset = j - bc[txt.charAt(i + j)];
            /* 好后缀规则 */
            int gsOffset = 1;
            int gsLength = m - 1 - j;
            if (gsLength > 0) {
                gsOffset = m;
                if (gs[0][gsLength] > -1) {
                    // 完全后缀匹配
                    gsOffset = j - gs[0][gsLength] + 1;
                } else {
                    // 部分后缀匹配，寻找最长的相等前后缀
                    for (int k = gsLength - 1; k > 0; k--) {
                        if (gs[1][k] > -1) {
                            gsOffset = m - k;
                            break;
                        }
                    }
                }
            }
            // 取坏字符和好后缀的最大偏移量
            i = i + Math.max(bcOffset, gsOffset);
        }
        return -1;
    }

    /**
     * 好后缀
     * <p>
     * suffix[0] 表示完全后缀，suffix[0][i] 表示后缀长度为 i 时，相同后缀的起始索引
     * <p>
     * suffix[1] 表示部分后缀，suffix[1][i] 表示后缀长度为 i 时，前后缀是否相等
     *
     * @param pat 模式串
     * @return 好后缀数组
     */
    private int[][] getGoodSuffix(String pat) {
        int m = pat.length();
        int[][] suffix = new int[2][m];
        for (int i = 0; i < m; i++) {
            suffix[0][i] = suffix[1][i] = -1;
        }
        for (int i = 0; i < m - 1; i++) {
            int j = i, k = 0;
            while (j >= 0 && pat.charAt(j) == pat.charAt(m - 1 - k)) {
                // 完全匹配后缀
                suffix[0][++k] = j;
                j--;
            }
            // 部分匹配后缀，前后缀相等
            if (j < 0) {
                suffix[1][k] = 1;
            }
        }
        return suffix;
    }

    /**
     * 坏字符
     * <p>
     * bc[c] 表示字符 c 在模式串中最后一次出现的索引
     *
     * @param pat 模式串
     * @return 坏字符索引
     */
    private int[] getBadChars(String pat) {
        int[] bc = new int[R];
        for (int i = 0; i < R; i++) {
            bc[i] = -1;
        }
        int m = pat.length();
        for (int i = 0; i < m; i++) {
            bc[pat.charAt(i)] = i;
        }
        return bc;
    }

}
