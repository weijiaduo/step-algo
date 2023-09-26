package com.wjd.algorithm.sort;

/**
 * 排序接口
 *
 * @author weijiaduo
 * @since 2022/7/21
 */
public interface Sort {

    /**
     * 排序
     *
     * @param arr 数组
     */
    void sort(int[] arr);

    /**
     * 交换数组元素
     *
     * @param arr 数组
     * @param i   索引
     * @param j   索引
     */
    default void swap(int[] arr, int i, int j) {
        if (i == j) {
            return;
        }
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
