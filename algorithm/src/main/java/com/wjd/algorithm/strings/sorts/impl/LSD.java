package com.wjd.algorithm.strings.sorts.impl;

import com.wjd.algorithm.strings.sorts.Sort;

/**
 * 低位优先的排序算法
 * <p>
 * 假设所有字符串长度是一样的
 * <p>
 * 对字符串按照从索引 n~0 去对比排序
 *
 * @author weijiaduo
 * @since 2023/4/15
 */
public class LSD implements Sort {

    /**
     * 假设字符集为 ASCII 码
     */
    protected int R = 256;

    @Override
    public void sort(String[] strings) {
        // 前提是每个字符串长度一样
        int d = strings[0].length();
        String[] aux = new String[strings.length];
        for (int i = d - 1; i >= 0; i--) {
            countSort(strings, i, aux);
        }
    }

    /**
     * 基于指定索引进行计数排序
     *
     * @param strings 字符串数组
     * @param index   指定索引
     * @param aux     临时数组空间
     */
    private void countSort(String[] strings, int index, String[] aux) {
        // 统计字符出现的次数
        int[] count = new int[R + 1];
        int n = strings.length;
        for (String s : strings) {
            int idx = charAt(s, index);
            count[idx + 1]++;
        }
        // 将次数汇总累积起来
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }
        // 按照频率排序（倒着插入保证稳定性）
        for (int i = n - 1; i >= 0; i--) {
            String s = strings[i];
            int idx = charAt(s, index);
            aux[--count[idx + 1]] = s;
        }
        // 最后将结果回写
        for (int i = 0; i < n; i++) {
            strings[i] = aux[i];
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
