package com.wjd.structure.tree.huffman;

/**
 * 哈夫曼树接口
 *
 * @author weijiaduo
 * @since 2023/2/23
 */
public interface HuffmanTree {

    /**
     * 获取指定 key 的编码
     *
     * @param key 指定 key
     * @return 编码字符串
     */
    String getCode(String key);

}
