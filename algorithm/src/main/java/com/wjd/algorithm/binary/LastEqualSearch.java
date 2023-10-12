package com.wjd.algorithm.binary;

/**
 * 寻找最后一个等于给定值的索引
 *
 * @author weijiaduo
 * @since 2022/8/27
 */
public class LastEqualSearch implements Search {

    /**
     * 查询方式
     */
    private final int type;

    public LastEqualSearch() {
        this(1);
    }

    public LastEqualSearch(int type) {
        this.type = type;
    }

    @Override
    public int search(int[] arr, int target) {
        if (type == 2) {
            return search2(arr, target);
        } else {
            return search1(arr, target);
        }
    }

    /**
     * 简单直接的写法
     */
    private int search1(int[] arr, int target) {
        int n = arr.length;
        int low = 0, high = n - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == target) {
                if (mid == n - 1 || arr[mid + 1] != target) {
                    return mid;
                }
                low = mid + 1;
            } else if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 代码好看的写法
     */
    private int search2(int[] arr, int target) {
        int ret = -1;
        int n = arr.length;
        int low = 0, high = n - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == target) {
                ret = mid;
                low = mid + 1;
            } else if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ret;
    }

}
