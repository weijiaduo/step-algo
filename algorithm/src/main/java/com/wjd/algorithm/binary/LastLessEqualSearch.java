package com.wjd.algorithm.binary;

/**
 * 寻找最后一个小于等于给定值的位置
 *
 * @author weijiaduo
 * @since 2022/8/27
 */
public class LastLessEqualSearch implements Search {

    @Override
    public int search(int[] arr, int target) {
        return search1(arr, target);
        // return search2(arr, target);
    }

    /**
     * 简单直接的算法
     */
    private int search1(int[] arr, int target) {
        int n = arr.length;
        int low = 0, high = n - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] <= target) {
                if (mid == n - 1 || arr[mid + 1] > target) {
                    return mid;
                }
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        // target < arr[0]
        return -1;
    }

    /**
     * 根据循环不变量来实现
     */
    private int search2(int[] arr, int target) {
        // 循环不变量
        // arr[low - 1] <= target
        // arr[high + 1] > target
        int n = arr.length;
        int low = 0, high = n - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] <= target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        // 循环终止条件：high + 1 == low
        // 此时依旧满足循环不变量
        return high; // low - 1
    }

}
