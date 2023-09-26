package com.wjd.structure.heap.index;

/**
 * 索引堆接口
 *
 * @author weijiaduo
 * @since 2023/2/25
 */
public interface IndexHeap<T> {

    /**
     * 插入新值
     *
     * @param index 指定索引
     * @param val   新值
     */
    void insert(int index, T val);

    /**
     * 移除第一个节点
     *
     * @return 第一个节点值
     */
    T removeFirst();

    /**
     * 删除指定索引的元素
     *
     * @param index 指定索引
     * @return 被删除元素
     */
    T remove(int index);

    /**
     * @return 第一个元素
     */
    T first();

    /**
     * 根节点元素的索引
     *
     * @return 索引
     */
    int firstIndex();

    /**
     * 指定索引的元素是否存在
     *
     * @param index 指定索引
     * @return true/false
     */
    boolean containsIndex(int index);

    /**
     * @return 元素数量
     */
    int size();

}
