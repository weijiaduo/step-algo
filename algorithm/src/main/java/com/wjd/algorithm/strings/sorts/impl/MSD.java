package com.wjd.algorithm.strings.sorts.impl;

import com.wjd.algorithm.strings.sorts.Sort;

/**
 * 高位优先排序
 * <p>
 * 对字符串按照从索引 0~n 去对比排序
 *
 * @author weijiaduo
 * @since 2023/4/15
 */
public class MSD implements Sort {

    /**
     * 假设字符集为 ASCII 码
     */
    protected int R = 256;

    @Override
    public void sort(String[] strings) {
        countSort(strings, 0, strings.length, 0, new String[strings.length]);
    }

    /**
     * 基于指定索引进行计数排序
     *
     * @param strings 字符串数组
     * @param l       [l, r)
     * @param r       [l, r)
     * @param index   索引
     * @param aux     临时数组空间
     */
    private void countSort(String[] strings, int l, int r, int index, String[] aux) {
        // 1 个字符串不需要排序
        if (l + 1 >= r) {
            return;
        }

        // 统计字符出现的次数
        boolean isEnd = true;
        int[] count = new int[R + 1];
        for (int i = l; i < r; i++) {
            String s = strings[i];
            int idx = charAt(s, index);
            count[idx + 1]++;
            isEnd &= idx == -1;
        }
        // 判断索引是否已经超出所有字符串的长度
        // 如果已经超出，则说明 [l, r) 内的字符串是相等的
        if (isEnd) {
            return;
        }
        // 将次数汇总累积起来
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }
        // 按照频率排序（倒着插入保证稳定性）
        for (int i = r - 1; i >= l; i--) {
            String s = strings[i];
            int idx = charAt(s, index);
            aux[l + (--count[idx + 1])] = s;
        }
        // 最后将结果回写
        for (int i = l; i < r; i++) {
            strings[i] = aux[i];
        }

        // 对每个相同前缀的分组递归排序
        for (int i = 1; i < count.length; i++) {
            countSort(strings, l + count[i - 1], l + count[i], index + 1, aux);
        }
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
