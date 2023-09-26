package com.wjd.structure.queue;

/**
 * 队列
 *
 * @author weijiaduo
 * @since 2022/8/30
 */
public interface Queue {

    /**
     * 入队
     *
     * @param val 值
     * @return true/false
     */
    boolean enqueue(int val);

    /**
     * 出队
     *
     * @return 队头
     */
    int dequeue();

    /**
     * 大小
     *
     * @return 大小
     */
    int size();

    /**
     * 是否为空
     *
     * @return true/false
     */
    boolean isEmpty();

    /**
     * 是否已满
     *
     * @return true/false
     */
    boolean isFull();

}
