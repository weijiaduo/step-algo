package com.wjd.structure.tree.bplus;

/**
 * B+ 树接口定义
 *
 * @author weijiaduo
 * @since 2023/1/9
 */
public interface BPTree<K extends Comparable<K>, V>  {

    /**
     * 查找指定 key 的值
     *
     * @param key key
     * @return value
     */
    V get(K key);

    /**
     * 添加指定 key-value 的节点
     *
     * @param key   key
     * @param value value
     */
    void put(K key, V value);

    /**
     * 删除指定 key 的节点
     *
     * @param key key
     */
    void remove(K key);

}
