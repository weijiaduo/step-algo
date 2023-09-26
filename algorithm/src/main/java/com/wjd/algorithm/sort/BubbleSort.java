package com.wjd.algorithm.sort;

/**
 * 冒泡排序
 * <p>
 * 时间复杂度：最好 O(n) 最差 O(n^2) 平均 O(n^2)
 * <p>
 * 空间复杂度：O(1)
 * <p>
 * 稳定性：稳定
 *
 * @author weijiaduo
 * @since 2022/7/21
 */
public class BubbleSort implements Sort {

    /**
     * 排序
     *
     * @param arr 数组
     */
    @Override
    public void sort(int[] arr) {
        // slowSort(arr);
        fastSort(arr);
    }

    /**
     * 慢一点的冒泡排序
     *
     * @param arr 数组
     */
    private void slowSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            for (int j = n - 1; j > i; j--) {
                if (arr[j] < arr[j - 1]) {
                    swap(arr, j, j - 1);
                }
            }
        }
    }

    /**
     * 快一点的冒泡排序
     *
     * @param arr 数组
     */
    private void fastSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            boolean flag = false;
            for (int j = n - 1; j > i; j--) {
                if (arr[j] < arr[j - 1]) {
                    swap(arr, j, j - 1);
                    flag = true;
                }
            }
            // 一轮冒泡里没有交换，说明已经排好序了
            if (!flag) {
                break;
            }
        }
    }

}
