package com.wjd.algorithm.math;

/**
 * 最大公约数
 *
 * @author weijiaduo
 * @since 2023/7/2
 */
public class Gcd {

    /**
     * 递归法
     */
    public int dfs(int a, int b) {
        return b > 0 ? dfs(b, a % b) : a;
    }

    /**
     * 迭代法
     */
    public int iterate(int a, int b) {
        while (a != 0) {
            int tmp = a;
            a = b % a;
            b = tmp;
        }
        return b;
    }

}
