package com.wjd.algorithm.strings.trick;

/**
 * Manacher 算法，用于处理最长回文串的问题
 *
 * @author weijiaduo
 * @since 2023/11/18
 */
public class Manacher {

    /**
     * 思路：利用已有的回文串，加快后面回文串的比较
     * <p>
     * 复杂度：时间 O(n) 空间 O(n)
     */
    public String compute(String s) {
        // 预处理字符串
        char[] cs = process(s);
        int n = cs.length;
        // 以每个位置为中心的回文串半径
        int[] rs = new int[n];

        int c = 0, center = 0, right = 0;
        for (int i = 0; i < n; i++) {
            // 找到 i 关于 center 对称的位置
            if (i < right) {
                int mi = 2 * center - i;
                rs[i] = Math.min(rs[mi], right - i);
            }

            // 以 i 为中心，向两边扩展回文串
            int lp = i - rs[i] - 1, rp = i + rs[i] + 1;
            while (lp >= 0 && rp < n && cs[lp] == cs[rp]) {
                rs[i]++;
                lp--;
                rp++;
            }

            // 回文串的右边界变大了
            if (i + rs[i] > right) {
                center = i;
                right = i + rs[i];
            }

            // 更新最长回文串中心
            if (rs[i] > rs[c]) {
                c = i;
            }
        }

        // 在原始字符串的起始索引
        int start = (c - rs[c]) / 2;
        return s.substring(start, start + rs[c]);
    }

    /**
     * 对字符串进行预处理，加上特殊的字符
     * <p>
     * 比如字符串 "abcdefg"，预处理后变成 "#a#b#c#d#e#f#g#"
     *
     * @param s 原始字符串
     * @return 处理后的字符串
     */
    private char[] process(String s) {
        if (s.length() == 0) {
            return new char[0];
        }
        int n = s.length(), k = 0;
        char[] chars = new char[2 * n + 1];
        for (int i = 0; i < n; i++) {
            chars[k++] = '#';
            chars[k++] = s.charAt(i);
        }
        chars[k] = '#';
        return chars;
    }

}
