package com.wjd.structure.heap.skew;

/**
 * 斜堆节点
 *
 * @author weijiaduo
 * @since 2023/9/26
 */
public class SkewHeapNode<T> {

    /**
     * 节点值
     */
    public T val;
    /**
     * 左子节点
     */
    public SkewHeapNode<T> left;
    /**
     * 右子节点
     */
    public SkewHeapNode<T> right;

    public SkewHeapNode(T val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return val.toString();
    }

}
