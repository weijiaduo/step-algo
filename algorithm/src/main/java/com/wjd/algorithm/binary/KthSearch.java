package com.wjd.algorithm.binary;

/**
 * 数组中的第K个最大元素
 *
 * @author weijiaduo
 * @since 2023/7/2
 */
public class KthSearch implements Search {

    @Override
    public int search(int[] arr, int k) {
        return iterate(arr, k);
    }

    /**
     * 思路：快速排序的二分法，每次可将数据分成2份，再判断k再那边，然后再继续二分
     * <p>
     * 复杂度：时间 O(n) 空间 O(logn)
     */
    private int recursive(int[] nums, int k) {
        return recursive(nums, 0, nums.length, k - 1);
    }

    /**
     * 递归二分
     */
    private int recursive(int[] nums, int start, int end, int k) {
        if (k < start || k >= end) {
            return -1;
        }

        int p = partition(nums, start, end);
        if (p == k) {
            return nums[p];
        } else if (p > k) {
            return recursive(nums, start, p, k);
        } else {
            return recursive(nums, p + 1, end, k);
        }
    }

    /**
     * 思路：递归改成迭代
     * <p>
     * 复杂度：时间 O(n) 空间 O(logn)
     */
    private int iterate(int[] nums, int k) {
        k -= 1;
        int start = 0, end = nums.length;
        while (start < end) {
            int p = partition(nums, start, end);
            if (p == k) {
                return nums[p];
            } else if (p > k) {
                end = p;
            } else {
                start = p + 1;
            }
        }
        return -1;
    }

    /**
     * 二分数组，这种方式的代码简洁一点
     *
     * @param nums  数组
     * @param start [start, end)
     * @param end   [start, end)
     * @return 分隔点索引
     */
    private int partition(int[] nums, int start, int end) {
        int p = pivot(nums, start, end - 1);
        swap(nums, start, p);
        int ref = nums[start], lp = start;
        for (int i = lp + 1; i < end; i++) {
            if (nums[i] >= ref) {
                swap(nums, ++lp, i);
            }
        }
        swap(nums, start, lp);
        return lp;
    }

    /**
     * 选择分区点（选择三个点的中值位置）
     *
     * @param arr 数组
     * @param i   [i, j]
     * @param j   [i, j]
     * @return 分区点索引
     */
    private int pivot(int[] arr, int i, int j) {
        int mid = i + (j - i) / 2;
        if (arr[i] < arr[j]) {
            return arr[mid] > arr[i] ? mid : j;
        } else {
            return arr[mid] < arr[i] ? mid : i;
        }
    }

    private void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

}
