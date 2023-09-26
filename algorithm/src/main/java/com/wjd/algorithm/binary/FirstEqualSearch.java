package com.wjd.algorithm.binary;

/**
 * 寻找第一个等于给定值的索引
 *
 * @author weijiaduo
 * @since 2022/8/27
 */
public class FirstEqualSearch implements Search {

    @Override
    public int search(int[] arr, int target) {
        int n = arr.length;
        int low = 0, high = n - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == target) {
                if (mid == 0 || arr[mid - 1] != target) {
                    return mid;
                }
                high = mid - 1;
            } else if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

}
