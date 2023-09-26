package com.wjd.structure.tree.huffman;

/**
 * 哈夫曼树节点
 *
 * @author weijiaduo
 * @since 2023/2/23
 */
public class HTNode {

    /**
     * 关键字
     */
    public String key;
    /**
     * 关键字权重
     */
    public int weight;
    /**
     * 左子节点
     */
    public HTNode left;
    /**
     * 右子节点
     */
    public HTNode right;

    public HTNode(String key, int weight) {
        this.key = key;
        this.weight = weight;
    }

}
