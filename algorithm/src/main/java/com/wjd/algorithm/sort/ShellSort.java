package com.wjd.algorithm.sort;

/**
 * 希尔排序
 * <p>
 * 时间复杂度：暂时无法分析，但是达不到 O(n^2)
 * <p>
 * 空间复杂度：O(1)
 * <p>
 * 稳定性：不稳定
 *
 * @author weijiaduo
 * @since 2022/9/24
 */
public class ShellSort implements Sort {

    @Override
    public void sort(int[] arr) {
        int n = arr.length;

        // 递增序列：1, 4, 13, 40, 121, ...
        int h = 1;
        while (h < n / 3) {
            h = 3 * h + 1;
        }

        // 按照序列倒序递减至 1
        while (h >= 1) {
            // 插入排序
            for (int i = h; i < n; i++) {
                int x = arr[i];
                int j = i;
                for (; j >= h && x < arr[j - h]; j -= h) {
                    arr[j] = arr[j - h];
                }
                arr[j] = x;
            }
            h /= 3;
        }
    }

}
