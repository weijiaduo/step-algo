package com.wjd.algorithm.math;

/**
 * 整数次方
 * <p>
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn）。
 * <p>
 * 不得使用库函数，同时不需要考虑大数问题。
 *
 * @author weijiaduo
 * @since 2023/7/15
 */
public class Power {

    /**
     * 思路：快速幂 + 递归，每轮翻倍计算幂
     * <p>
     * 比如，2, 2^2, 2^4, 2^8, 2^16, 2^32 ...
     * <p>
     * 复杂度：时间 O(logn) 空间 O(logn)
     *
     * @param x 底数
     * @param n 指数
     * @return 幂
     */
    public double dfs(double x, int n) {
        long y = n;
        return y >= 0 ? dfs(x, y) : 1.0 / dfs(x, -y);
    }

    /**
     * 递归法：整数幂
     *
     * @param x 底数
     * @param y 指数，y >= 0
     * @return 幂
     */
    private double dfs(double x, long y) {
        if (y == 0) {
            return 1.0;
        }
        double half = dfs(x, y >> 1);
        return y % 2 == 0 ? half * half : half * half * x;
    }

    /**
     * 思路：快速幂 + 迭代，每轮翻倍计算幂
     * <p>
     * 比如，2, 2^2, 2^4, 2^8, 2^16, 2^32 ...
     * <p>
     * 复杂度：时间 O(logn) 空间 O(1)
     *
     * @param x 底数
     * @param n 指数
     * @return 冥
     */
    public double iterate(double x, int n) {
        long y = n;
        return y >= 0 ? iterate(x, y) : 1.0 / iterate(x, -y);
    }

    /**
     * 迭代法：整数幂
     *
     * @param x 底数
     * @param y 指数，y >= 0
     * @return 幂
     */
    private double iterate(double x, long y) {
        double ans = 1.0;
        while (y != 0) {
            if ((y & 1) == 1) {
                ans *= x;
            }
            x = x * x;
            y >>= 1;
        }
        return ans;
    }

}
