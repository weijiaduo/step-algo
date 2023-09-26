package com.wjd.structure.heap.skew;

import java.util.Arrays;

/**
 * 斜堆（Skew Heap）实现类
 * <p>
 * 左倾堆（Leftist Heap）的变种，只是斜堆没有 NPL 这个属性
 *
 * @author weijiaduo
 * @since 2023/9/26
 */
public class SkewHeapImpl<T extends Comparable<T>> implements SkewHeap<T> {

    /**
     * 堆根节点
     */
    private SkewHeapNode<T> root;
    /**
     * 节点数量
     */
    private int size;

    @Override
    public void merge(SkewHeap<T> other) {
        if (!(other instanceof SkewHeapImpl<T>)) {
            return;
        }

        root = merge(root, ((SkewHeapImpl<T>) other).root);
        size += other.size();
    }

    @Override
    public void insert(T val) {
        root = merge(root, new SkewHeapNode<>(val));
        size++;
    }

    @Override
    public T removeFirst() {
        if (size <= 0) {
            throw new IllegalStateException("Heap is Empty!");
        }

        SkewHeapNode<T> node = root;
        root = merge(root.left, root.right);
        node.left = node.right = null;
        size--;
        return node.val;
    }

    @Override
    public T first() {
        if (size <= 0) {
            throw new IllegalStateException("Heap is Empty!");
        }

        return root.val;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size <= 0;
    }

    /**
     * 合并 2 个堆，并返回新堆的根节点
     *
     * @param h1 堆1 根节点
     * @param h2 堆2 根节点
     * @return 新堆的根节点
     */
    private SkewHeapNode<T> merge(SkewHeapNode<T> h1, SkewHeapNode<T> h2) {
        if (h1 == null) {
            return h2;
        }
        if (h2 == null) {
            return h1;
        }

        // 以小根节点作为新堆的根节点
        SkewHeapNode<T> min, max;
        if (h1.val.compareTo(h2.val) <= 0) {
            min = h1;
            max = h2;
        } else {
            min = h2;
            max = h1;
        }

        // 小根节点的右子节点和大根节点合并
        min.right = merge(min.right, max);

        // 交换左右子节点
        SkewHeapNode<T> tmp = min.left;
        min.left = min.right;
        min.right = tmp;

        return min;
    }

    @Override
    public String toString() {
        return Arrays.toString(new SkewHeapSerializer().serialize(root));
    }

}
