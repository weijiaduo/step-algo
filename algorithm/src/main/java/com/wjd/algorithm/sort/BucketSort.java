package com.wjd.algorithm.sort;

import java.util.*;

/**
 * 桶排序
 * <p>
 * 时间复杂度：O(n)
 * <p>
 * 空间复杂度：O(w)
 * <p>
 * 稳定性：稳定
 *
 * @author weijiaduo
 * @since 2022/9/5
 */
public class BucketSort implements Sort {

    int width;

    public BucketSort() {
        this(10);
    }

    public BucketSort(int width) {
        this.width = width;
    }

    @Override
    public void sort(int[] arr) {
        // 找出数据的范围（最小最大值）
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int num : arr) {
            if (num > max) {
                max = num;
            }
            if (num < min) {
                min = num;
            }
        }

        // 根据数据范围平均划分桶
        int n = (max - min + width) / width;
        List<List<Integer>> buckets = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            buckets.add(new ArrayList<>());
        }

        // 把不同范围的数据划分到不同的桶里面
        for (int num : arr) {
            int index = (num - min) / width;
            List<Integer> list = buckets.get(index);
            list.add(num);
        }

        // 桶之间已经是有序的了，只需对桶内排序
        for (List<Integer> bucket : buckets) {
            Collections.sort(bucket);
        }

        // 按桶的顺序遍历所有数据，就是已经排好序的了
        int k = 0;
        for (List<Integer> bucket : buckets) {
            for (Integer num : bucket) {
                arr[k++] = num;
            }
        }
    }

}
