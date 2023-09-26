package com.wjd.algorithm.sort;

/**
 * 插入排序
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
public class InsertSort implements Sort {

    @Override
    public void sort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            // 未排序区间数据元素
            int x = arr[i];
            int j = i;
            // 插入到已排序区间合适的位置
            for (; j > 0 && x < arr[j - 1]; j--) {
                arr[j] = arr[j - 1];
            }
            arr[j] = x;
        }
    }

}
