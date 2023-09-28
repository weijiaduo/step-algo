package com.wjd.algorithm.sort;

/**
 * 二分插入排序
 * <p>
 * 时间复杂度：最好 O(nlogn) 最差 O(n^2) 平均 O(n^2)
 * <p>
 * 空间复杂度：O(1)
 * <p>
 * 稳定性：稳定
 *
 * @author weijiaduo
 * @since 2023/9/28
 */
public class BinaryInsertSort implements Sort {

    @Override
    public void sort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int x = arr[i];
            // 二分查找合适的插入位置
            int loc = firstGreat(arr, 0, i - 1, x);
            // 插入到已排序区间合适的位置
            for (int j = i; j > loc; j--) {
                arr[j] = arr[j - 1];
            }
            arr[loc] = x;
        }
    }

    /**
     * 寻找第一个大于指定值的位置
     *
     * @param arr 数组
     * @param lo  起始位置 [lo, hi]
     * @param hi  结束位置 [lo, hi]
     * @param val 指定值
     * @return 符合条件的索引
     */
    private int firstGreat(int[] arr, int lo, int hi, int val) {
        int left = lo, right = hi;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] > val) {
                if (mid == 0 || arr[mid] <= val) {
                    return mid;
                }
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

}
