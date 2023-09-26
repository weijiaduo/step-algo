package com.wjd.structure.stack;

/**
 * 栈接口
 *
 * @author weijiaduo
 * @since 2022/8/24
 */
public interface Stack {

    /**
     * 入栈
     *
     * @param val 值
     */
    void push(int val);

    /**
     * 出栈
     *
     * @return 栈顶值
     */
    int pop();

    /**
     * 栈顶
     *
     * @return 栈顶值
     */
    int top();

    /**
     * 栈大小
     *
     * @return 大小
     */
    int size();

    /**
     * @return 是否为空
     */
    boolean isEmpty();

    /**
     * @return 是否已满
     */
    boolean isFull();

}
