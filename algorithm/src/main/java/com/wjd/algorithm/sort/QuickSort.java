package com.wjd.algorithm.sort;

/**
 * 快速排序
 * <p>
 * 时间复杂度：最好 O(nlogn) 最差 O(n^2) 平均 O(nlogn)
 * <p>
 * 空间复杂度：O(logn)
 * <p>
 * 稳定性：不稳定
 *
 * @author weijiaduo
 * @since 2022/7/16
 */
public class QuickSort implements Sort {

    /**
     * 分区方式
     */
    private final int type;

    public QuickSort() {
        this(1);
    }

    public QuickSort(int type) {
        this.type = type;
    }

    /**
     * 排序
     *
     * @param arr 数组
     */
    @Override
    public void sort(int[] arr) {
        partSort(arr, 0, arr.length);
    }

    /**
     * 递归排序
     *
     * @param arr   数组
     * @param start [start, end)
     * @param end   [start, end)
     */
    private void partSort(int[] arr, int start, int end) {
        if (start < end) {
            // 分区
            int m;
            if (type == 3) {
                m = partition3(arr, start, end);
            } else if (type == 2) {
                m = partition2(arr, start, end);
            } else {
                m = partition(arr, start, end);
            }

            // 左边排序
            partSort(arr, start, m);
            // 右边排序
            partSort(arr, m + 1, end);
        }
    }

    /**
     * 二分数组（遍历）
     *
     * @param arr   数组
     * @param start [start, end)
     * @param end   [start, end)
     * @return 分隔点索引
     */
    private int partition(int[] arr, int start, int end) {
        // 选取分区点
        int p = pivot(arr, start, end - 1);
        // 将分区点放到最前面
        swap(arr, start, p);
        int ref = arr[start];
        int lp = start;
        // 将数据与分区点对比，分成小于和大于2部分
        for (int i = lp + 1; i < end; i++) {
            if (arr[i] <= ref) {
                swap(arr, ++lp, i);
            }
        }
        // 将分区点放到它最终的位置
        swap(arr, start, lp);
        return lp;
    }

    /**
     * 二分数组（双指针）
     *
     * @param arr   数组
     * @param start [start, end)
     * @param end   [start, end)
     * @return 分隔点索引
     */
    private int partition2(int[] arr, int start, int end) {
        // 选取分区点
        int p = pivot(arr, start, end - 1);
        // 将分区点放到最前面
        swap(arr, start, p);
        int ref = arr[start];
        int lp = start, rp = end - 1;
        // 将数据与分区点对比，分成小于和大于2部分
        while (lp < rp) {
            while (lp < rp && arr[rp] >= ref) rp--;
            while (lp < rp && arr[lp] <= ref) lp++;
            swap(arr, lp, rp);
        }
        // 将分区点放到它最终的位置
        swap(arr, start, rp);
        return rp;
    }

    /**
     * 二分数组（针对有大量重复值的情况）
     *
     * @param arr   数组
     * @param start [start, end)
     * @param end   [start, end)
     * @return 分隔点索引
     */
    private int partition3(int[] arr, int start, int end) {
        // 选取分区点
        int p = pivot(arr, start, end - 1);
        // 将分区点放到最前面
        swap(arr, start, p);
        int ref = arr[start];
        int lp = start, rp = end;
        // 将数据与分区点对比，分成小于和大于2部分
        while (lp < rp) {
            // 对于大量重复值的情况，这种写法能够尽量均分数组
            do rp--; while (lp < rp && arr[rp] > ref);
            do lp++; while (lp < rp && arr[lp] < ref);
            if (lp < rp) {
                swap(arr, lp, rp);
            }
        }
        // 将分区点放到它最终的位置
        swap(arr, start, rp);
        return rp;
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

}
