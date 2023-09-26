package com.wjd.structure.heap.binary;

/**
 * 堆接口
 *
 * @author weijiaduo
 * @since 2023/2/24
 */
public interface Heap<T> {

    /**
     * 插入新值
     *
     * @param val 新值
     */
    void insert(T val);

    /**
     * 移除第一个节点
     *
     * @return 第一个节点值
     */
    T removeFirst();

    /**
     * @return 第一个元素
     */
    T first();

    /**
     * @return 元素数量
     */
    int size();

}
