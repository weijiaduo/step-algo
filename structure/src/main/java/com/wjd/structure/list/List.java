package com.wjd.structure.list;

/**
 * 链表
 *
 * @author weijiaduo
 * @since 2022/8/21
 */
public interface List {

    /**
     * 获取指定索引的节点值
     *
     * @param index 索引
     * @return 值
     */
    int get(int index);

    /**
     * 在链尾添加新节点
     *
     * @param val 新节点值
     */
    void add(int val);

    /**
     * 在指定位置添加新节点
     *
     * @param index 索引
     * @param val   节点值
     */
    void insert(int index, int val);

    /**
     * 移除指定位置的节点
     *
     * @param index 索引
     * @return 移除节点的值
     */
    int remove(int index);

    /**
     * 是否包含指定值的节点
     *
     * @param val 指定值
     * @return true/false
     */
    boolean contains(int val);

    /**
     * 链表大小
     *
     * @return 链表大小
     */
    int size();

}
