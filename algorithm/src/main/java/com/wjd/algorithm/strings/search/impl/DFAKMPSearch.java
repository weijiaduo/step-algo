package com.wjd.algorithm.strings.search.impl;

import com.wjd.algorithm.strings.search.Search;

/**
 * KMP(DFA) 算法
 *
 * @author weijiaduo
 * @since 2023/3/29
 */
public class DFAKMPSearch implements Search {

    /**
     * 字符集数量，暂定为 ASCII 码值
     */
    private static final int R = 256;

    @Override
    public int search(String pat, String txt) {
        int[][] dfa = getDFA(pat, R);
        int m = pat.length(), n = txt.length();
        int i = 0, j = 0;
        for (; i < n && j < m; i++) {
            // 状态机状态转移
            j = dfa[j][txt.charAt(i)];
        }
        // 如果能抵达最终状态，匹配成功
        if (j == m) {
            return i - j;
        }
        return -1;
    }

    /**
     * 获取 DFA 自动机状态转移数组
     *
     * @param pat 模式串
     * @param R   字符数量
     * @return 状态转移数组
     */
    private int[][] getDFA(String pat, int R) {
        int m = pat.length();
        // dfa[i][c] 表示状态 i 遇到字符 c 以后要转移的状态
        // 比如 dfa[3]['c'] = 1 表示状态 3 遇到字符 'c' 会跳到状态 1
        int[][] dfa = new int[m][R];
        // 开始匹配
        dfa[0][pat.charAt(0)] = 1;
        // pre 表示影子状态，即相等前后缀的位置
        int pre = 0;
        for (int j = 1; j < m; j++) {
            int c = pat.charAt(j);
            // 匹配失败，默认转移到上一个状态
            for (int x = 0; x < R; x++) {
                dfa[j][x] = dfa[pre][x];
            }
            // 匹配成功，转移到下一个状态
            dfa[j][c] = j + 1;
            // 影子状态转移
            pre = dfa[pre][c];
        }
        return dfa;
    }

}
