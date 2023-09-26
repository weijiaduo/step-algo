package com.wjd.algorithm.math;

/**
 * 丑数
 * <p>
 * 只包含质因子 2、3 和 5 的数称作丑数（Ugly Number）。
 * <p>
 * 求按从小到大的顺序的第 n 个丑数。
 *
 * @author weijiaduo
 * @since 2023/7/15
 */
public class UglyNumber {

    /**
     * 思路：3指针，分别指向2、3、5的倍数，下一个丑数肯定是从这3个数里面出来的
     * <p>
     * 复杂度：时间 O(n) 空间 O(n)
     */
    public int nthUglyNumber(int n) {
        int[] nums = new int[n];
        int p2 = 0, p3 = 0, p5 = 0;
        nums[0] = 1;
        for (int i = 1; i < n; i++) {
            // 下一个丑数
            int next2 = nums[p2] * 2;
            int next3 = nums[p3] * 3;
            int next5 = nums[p5] * 5;
            int next = Math.min(next2, Math.min(next3, next5));

            // 不用 else 是为了去掉重复的数字
            if (next == next2) {
                p2++;
            }
            if (next == next3) {
                p3++;
            }
            if (next == next5) {
                p5++;
            }
            nums[i] = next;
        }
        return nums[n - 1];
    }

}
