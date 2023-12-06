package com.wjd.algorithm.strings.search;

/**
 * 暴力搜索法
 * <p>
 * 检查主串的所有子串，看是否和模式串匹配
 * <p>
 * 以每个字符为起点开始匹配，一旦匹配失败，就重头开始
 * <p>
 * 复杂度：时间 O(mn) 空间 O(1)
 *
 * @author weijiaduo
 * @since 2023/3/28
 */
public class BruteForceSearch implements Search {

    /**
     * 模式串
     */
    final String pat;

    public BruteForceSearch(String pat) {
        this.pat = pat;
    }

    @Override
    public int search(String txt) {
        int m = pat.length(), n = txt.length();
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
