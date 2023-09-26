package com.wjd.algorithm.strings.search;

/**
 * 字符串搜索接口
 *
 * @author weijiaduo
 * @since 2023/3/28
 */
public interface Search {

    /**
     * 搜索指定模式串是否在文本串中
     *
     * @param pat 模式串
     * @param txt 文本串
     * @return 模式串在文本串中的起始位置
     */
    int search(String pat, String txt);

}
