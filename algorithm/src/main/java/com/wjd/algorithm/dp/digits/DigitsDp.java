package com.wjd.algorithm.dp.digits;

/**
 * 数位-DP
 * <p>
 * 233. 数字 1 的个数（题解）
 * 面试题 17.06. 2出现的次数（题解）
 * 600. 不含连续1的非负整数（题解）
 * 788. 旋转数字
 * 902. 最大为 N 的数字组合（视频讲解 中讲了）
 * 1012. 至少有 1 位重复的数字（题解，包含 \textit{isNum}isNum 的用法）
 * 1067. 范围内的数字计数
 * 1397. 找到所有好字符串（有难度，需要结合一个知名字符串算法）
 *
 * @author weijiaduo
 * @since 2022/9/26
 */
public class DigitsDp {

    char[] digits;
    int[][] cache;

    int[] check = {0, 0, 1, -1, -1, 1, 1, -1, 0, 1};

    /**
     * 788. 旋转数字
     *
     * @param i       当前第 i 位数字
     * @param diff    前面是否已经有旋转的数字了
     * @param isLimit 当前数字是否受限，也就是前面的数字都到上边界了
     * @param isNum   前面的数字是否可以构成一个正常整数，比如前面是 00 时就不是正常整数
     */
    private int dfs(int i, int diff, boolean isLimit, boolean isNum) {
        if (i == digits.length) {
            // diff=0表示无效旋转，diff=1表示有效旋转
            return diff;
        }

        // 不受约束时才记忆存储，受限制和不受限制的数量是不同的
        if (!isLimit && cache[i][diff] >= 0) {
            return cache[i][diff];
        }

        int sum = 0;
        // 受限制时的最大值为当前值，否则为 9
        int up = isLimit ? digits[i] - '0' : 9;
        for (int d = 0; d <= up; d++) {
            if (check[d] != -1) {
                sum += dfs(i + 1, diff | check[d], isLimit && (d == up), isNum);
            }
        }

        // 不受约束时才记忆存储，受限制和不受限制的数量是不同的
        if (!isLimit) {
            cache[i][diff] = sum;
        }
        return sum;
    }

}
