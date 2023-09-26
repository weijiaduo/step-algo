package com.wjd.algorithm.binary;

/**
 * 二分查找
 *
 * @author weijiaduo
 * @since 2022/8/27
 */
public interface Search {

    /**
     * 给定值，查找指定的索引
     *
     * @param arr    数组
     * @param target 给定值
     * @return 指定索引
     */
    int search(int[] arr, int target);

}
