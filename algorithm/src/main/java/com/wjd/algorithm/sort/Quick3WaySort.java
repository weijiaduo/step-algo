package com.wjd.algorithm.sort;

/**
 * 三向快速排序
 * <p>
 * 时间复杂度：最好 O(nlogn) 最差 O(n^2) 平均 O(nlogn)
 * <p>
 * 空间复杂度：O(logn)
 * <p>
 * 稳定性：不稳定
 *
 * @author weijiaduo
 * @since 2023/4/16
 */
public class Quick3WaySort implements Sort {

    @Override
    public void sort(int[] arr) {
        partSort(arr, 0, arr.length);
    }

    /**
     * 递归排序
     *
     * @param arr   数组
     * @param start [start, end)
     * @param end   [start, end)
     */
    private void partSort(int[] arr, int start, int end) {
        if (start >= end) {
            return;
        }
        int x = arr[start];
        int lp = start - 1, rp = end;
        for (int i = lp + 1; i < rp; ) {
            if (arr[i] < x) {
                swap(arr, i++, ++lp);
            } else if (arr[i] > x) {
                swap(arr, i, --rp);
            } else {
                i++;
            }
        }
        // 对小于的部分排序
        partSort(arr, start, lp + 1);
        // 对大于的部分排序
        partSort(arr, rp, end);
    }

}
