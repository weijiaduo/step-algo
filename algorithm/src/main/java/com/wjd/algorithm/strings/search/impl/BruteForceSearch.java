package com.wjd.algorithm.strings.search.impl;

import com.wjd.algorithm.strings.search.Search;

/**
 * 暴力搜索法
 *
 * @author weijiaduo
 * @since 2023/3/28
 */
public class BruteForceSearch implements Search {

    @Override
    public int search(String pat, String txt) {
        int n = txt.length();
        int m = pat.length();
        for (int i = 0; i <= n - m; i++) {
            int j = 0;
            for (; j < m; j++) {
                if (txt.charAt(i + j) != pat.charAt(j)) {
                    break;
                }
            }
            // 匹配成功
            if (j == m) {
                return i;
            }
        }
        return -1;
    }

}
