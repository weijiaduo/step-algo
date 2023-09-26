package com.wjd.structure.heap.skew;

/**
 * 斜堆（Skew Heap）
 * <p>
 * 左倾堆（Leftist Heap）的变种，只是斜堆没有 NPL 这个属性
 *
 * @author weijiaduo
 * @since 2023/9/26
 */
public interface SkewHeap<T> {

    /**
     * 合并另外一个堆
     *
     * @param other 另外一个堆
     */
    void merge(SkewHeap<T> other);

    /**
     * 插入新值
     *
     * @param val 新值
     */
    void insert(T val);

    /**
     * 移除根节点
     *
     * @return 根节点值
     */
    T removeFirst();

    /**
     * 根节点值
     *
     * @return 根节点值
     */
    T first();

    /**
     * 堆大小
     *
     * @return 堆大小
     */
    int size();

    /**
     * 是否为空
     *
     * @return true/false
     */
    boolean isEmpty();

}
