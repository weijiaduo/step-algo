package com.wjd.algorithm.binary;

/**
 * 寻找等于给定值的位置
 *
 * @author weijiaduo
 * @since 2023/10/6
 */
public class EqualSearch implements Search {

    @Override
    public int search(int[] arr, int target) {
        int n = arr.length;
        int low = 0, high = n - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

}
