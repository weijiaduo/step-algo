package com.wjd.structure.queue;

/**
 * @author weijiaduo
 * @since 2022/8/30
 */
public interface Deque {

    /**
     * 插入列头
     *
     * @param value 新值
     * @return true/false
     */
    boolean insertFront(int value);

    /**
     * 插入列尾
     *
     * @param value 新值
     * @return true/false
     */
    boolean insertLast(int value);

    /**
     * 删除列头
     *
     * @return true/false
     */
    boolean deleteFront();

    /**
     * 删除列尾
     *
     * @return true/false
     */
    boolean deleteLast();

    /**
     * 获取列头
     *
     * @return 列头值
     */
    int getFront();

    /**
     * 获取列尾
     *
     * @return 列尾值
     */
    int getRear();

    /**
     * @return 是否为空
     */
    boolean isEmpty();

    /**
     * @return 是否已满
     */
    boolean isFull();

}
