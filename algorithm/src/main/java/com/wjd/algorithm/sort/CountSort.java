package com.wjd.algorithm.sort;

import java.util.Arrays;

/**
 * 计数排序
 * <p>
 * 时间复杂度：O(n)
 * <p>
 * 空间复杂度：O(n)
 * <p>
 * 稳定性：稳定
 *
 * @author weijiaduo
 * @since 2022/9/5
 */
public class CountSort implements Sort {

    @Override
    public void sort(int[] arr) {
        // 找出数据的范围（最小最大值）
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int num : arr) {
            if (num > max) {
                max = num;
            }
            if (num < min) {
                min = num;
            }
        }

        // 初始化计数范围
        int n = max - min + 1;
        int[] counts = new int[n];

        // 统计数字的数量
        for (int num : arr) {
            int index = num - min;
            counts[index]++;
        }

        // 累计数量和
        for (int i = 1; i < counts.length; i++) {
            counts[i] += counts[i - 1];
        }

        // 倒序遍历获取排序结果
        int[] copy = Arrays.copyOf(arr, arr.length);
        for (int i = copy.length - 1; i >= 0; i--) {
            int index = copy[i] - min;
            counts[index]--;
            arr[counts[index]] = copy[i];
        }
    }

}
