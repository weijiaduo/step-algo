package com.wjd.structure.heap.leftist;

/**
 * 左倾堆（Leftist Heap）
 * <p>
 * 基本性质：
 * <p>
 * 1. 节点的键值小于或等于它的左右子节点的键值
 * <p>
 * 2. 节点的左孩子的NPL >= 右孩子的NPL
 * <p>
 * 3. 节点的NPL = 它的右孩子的NPL + 1
 *
 * @author weijiaduo
 * @since 2023/9/26
 */
public interface LeftistHeap<T> {

    /**
     * 合并另外一个堆
     *
     * @param other 另外一个堆
     */
    void merge(LeftistHeap<T> other);

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
