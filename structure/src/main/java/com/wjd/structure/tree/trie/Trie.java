package com.wjd.structure.tree.trie;

/**
 * 字典树接口
 *
 * @author weijiaduo
 * @since 2023/3/30
 */
public interface Trie {

    /**
     * 插入单词
     *
     * @param word 单词
     */
    void insert(String word);

    /**
     * 是否包含指定单词
     *
     * @param word 单词
     * @return 单词是否存在
     */
    boolean search(String word);

    /**
     * 是否包含指定前缀
     *
     * @param prefix 前缀
     * @return 是否有前缀
     */
    boolean startsWith(String prefix);

    /**
     * 查找指定单词的最短路径
     *
     * @param word 单词
     * @return 最短路径/单词本身
     */
    String minPrefix(String word);

    /**
     * 是否匹配字符串表达式
     *
     * @param word 字符串表达式
     * @return true/false
     */
    boolean match(String word);

}
