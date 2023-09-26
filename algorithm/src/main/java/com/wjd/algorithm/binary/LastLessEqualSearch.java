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
        return -1;
    }

}
