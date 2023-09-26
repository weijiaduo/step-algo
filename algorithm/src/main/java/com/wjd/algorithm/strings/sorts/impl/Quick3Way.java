package com.wjd.algorithm.strings.sorts.impl;

import com.wjd.algorithm.strings.sorts.Sort;

/**
 * 三向快速排序
 * <p>
 * 将字符串分成 3 类：
 * <p>
 * 1. 小于
 * 2. 等于
 * 3. 大于
 * <p>
 * 然后再递归排序：
 * <p>
 * 1. 小于/大于：继续比较当前索引
 * <p>
 * 2. 等于：比较下一个索引
 *
 * @author weijiaduo
 * @since 2023/4/15
 */
public class Quick3Way implements Sort {

    @Override
    public void sort(String[] strings) {
        quickSort(strings, 0, strings.length, 0);
    }

    /**
     * 基于指定索引进行计数排序
     *
     * @param strings 字符串数组
     * @param l       [l, r)
     * @param r       [l, r)
     * @param index   索引
     */
    private void quickSort(String[] strings, int l, int r, int index) {
        // 1 个字符串不需要排序
        if (l + 1 >= r) {
            return;
        }

        // 对字符串进行 3 向分类
        boolean isEnd = true;
        int lp = l - 1, rp = r;
        int x = charAt(strings[l], index);
        for (int i = l; i < rp; ) {
            int idx = charAt(strings[i], index);
            if (idx < x) {
                swap(strings, i++, ++lp);
            } else if (idx > x) {
                swap(strings, i, --rp);
            } else {
                i++;
            }
            isEnd &= idx == -1;
        }
        // 判断索引是否已经超出所有字符串的长度
        // 如果已经超出，则说明 [l, r) 内的字符串是相等的
        if (isEnd) {
            return;
        }

        // 小于的部分，继续比较当前索引
        quickSort(strings, l, lp + 1, index);
        // 等于的部分，比较下一个索引位置
        quickSort(strings, lp + 1, rp, index + 1);
        // 大于的部分，继续比较当前索引
        quickSort(strings, rp, r, index);
    }

    /**
     * 交换字符串
     *
     * @param strings 字符串数组
     * @param i       i
     * @param j       j
     */
    private void swap(String[] strings, int i, int j) {
        if (i == j) {
            return;
        }
        String temp = strings[i];
        strings[i] = strings[j];
        strings[j] = temp;
    }

    /**
     * 获取指定索引的字符，不存在时返回 -1
     *
     * @param s     字符串
     * @param index 指定索引
     * @return 字符/-1
     */
    private int charAt(String s, int index) {
        return index < s.length() ? s.charAt(index) : -1;
    }

}
