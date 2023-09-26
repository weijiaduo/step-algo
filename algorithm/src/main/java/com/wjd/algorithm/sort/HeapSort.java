package com.wjd.algorithm.sort;

/**
 * 堆排序
 *
 * @author weijiaduo
 * @since 2023/2/26
 */
public class HeapSort implements Sort {

    @Override
    public void sort(int[] arr) {
        // 建堆
        int n = arr.length;
        for (int i = (n - 1) / 2; i >= 0; i--) {
            sink(arr, i, n);
        }

        // 排序
        for (int i = n; i > 0; i--) {
            swap(arr, 0, i - 1);
            sink(arr, 0, i - 1);
        }
    }

    /**
     * 下沉
     *
     * @param arr 数组
     * @param i   当前索引 i
     * @param n   数组长度
     */
    private void sink(int[] arr, int i, int n) {
        while (i < n - 1) {
            int m = i;
            // 左子节点
            int l = 2 * i + 1;
            if (l < n && arr[l] > arr[m]) {
                m = l;
            }
            // 右子节点
            int r = 2 * i + 2;
            if (r < n && arr[r] > arr[m]) {
                m = r;
            }
            if (m == i) {
                break;
            }
            swap(arr, i, m);
            i = m;
        }
    }

}
