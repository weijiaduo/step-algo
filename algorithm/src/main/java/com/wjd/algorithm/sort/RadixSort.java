package com.wjd.algorithm.sort;

import java.util.Arrays;

/**
 * 基数排序
 * <p>
 * 时间复杂度：O(n)
 * <p>
 * 空间复杂度：O(n)
 * <p>
 * 稳定性：稳定
 *
 * @author weijiaduo
 * @since 2022/9/6
 */
public class RadixSort implements Sort {

    @Override
    public void sort(int[] arr) {
        // 找到数组最大值
        int max = Integer.MIN_VALUE;
        for (int a : arr) {
            if (a > max) {
                max = a;
            }
        }

        // 计算基数大小
        int radix = 0;
        while(max > 0) {
            max = max / 10;
            radix++;
        }

        // 从低位到高位对数组进行排序
        int exp = 1;
        for (int i = 0; i < radix; i++) {
            countSort(arr, exp);
            exp *= 10;
        }
    }

    /**
     * 计数排序
     *
     * @param arr 数组
     * @param exp 指数
     */
    private void countSort(int[] arr, int exp) {
        // 统计每个数字（0-9）的次数
        int[] counts = new int[10];
        for (int a : arr) {
            counts[(a / exp) % 10]++;
        }

        // 累计数字的次数和
        for (int i = 1; i < counts.length; i++) {
            counts[i] += counts[i - 1];
        }

        // 更新排序结果到原数组
        int[] copy = Arrays.copyOf(arr, arr.length);
        for (int i = copy.length - 1; i >= 0; i--) {
            int index = (copy[i] / exp) % 10;
            counts[index]--;
            arr[counts[index]] = copy[i];
        }
    }

}
