package com.wjd.structure.heap.leftist;

/**
 * 左倾堆节点
 *
 * @author weijiaduo
 * @since 2023/9/26
 */
public class LeftistHeapNode<T extends Comparable<T>> {

    /**
     * 节点值
     */
    public T val;

    /**
     * 零距离（Null Path Length）
     */
    public int npl;

    /**
     * 左子节点
     */
    public LeftistHeapNode<T> left;

    /**
     * 右子节点
     */
    public LeftistHeapNode<T> right;

    public LeftistHeapNode(T val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return val.toString() + "(" + npl + ")";
    }

}
