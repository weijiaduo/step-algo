package com.wjd.structure.heap.leftist;

import java.util.Arrays;

/**
 * 左倾堆（Leftist Heap）实现类
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
public class LeftistHeapImpl<T extends Comparable<T>> implements LeftistHeap<T> {

    /**
     * 根节点
     */
    private LeftistHeapNode<T> root;

    /**
     * 节点数量
     */
    private int size;

    @Override
    public void merge(LeftistHeap<T> other) {
        if (!(other instanceof LeftistHeapImpl<T>)) {
            return;
        }

        root = merge(root, ((LeftistHeapImpl<T>) other).root);
        size += other.size();
    }

    @Override
    public void insert(T val) {
        root = merge(root, new LeftistHeapNode<>(val));
        size++;
    }

    @Override
    public T removeFirst() {
        if (size <= 0) {
            throw new IllegalStateException("Heap is Empty!");
        }

        LeftistHeapNode<T> node = root;
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
    private LeftistHeapNode<T> merge(LeftistHeapNode<T> h1, LeftistHeapNode<T> h2) {
        if (h1 == null) {
            return h2;
        }
        if (h2 == null) {
            return h1;
        }

        // 以小根节点作为新堆的根节点
        LeftistHeapNode<T> min, max;
        if (h1.val.compareTo(h2.val) <= 0) {
            min = h1;
            max = h2;
        } else {
            min = h2;
            max = h1;
        }

        // 小根节点的右子节点和大根节点合并
        min.right = merge(min.right, max);

        // 右子节点距离 > 左子节点距离，交换左右子节点
        if (npl(min.right) > npl(min.left)) {
            LeftistHeapNode<T> tmp = min.left;
            min.left = min.right;
            min.right = tmp;
        }

        // 根节点距离 = 右子节点距离 + 1
        min.npl = npl(min.right) + 1;

        return min;
    }

    /**
     * 节点的 NPL 距离
     *
     * @param node 节点
     * @return NPL 距离
     */
    private int npl(LeftistHeapNode<T> node) {
        // null 节点的距离为 -1
        return node != null ? node.npl : -1;
    }

    @Override
    public String toString() {
        return Arrays.toString(new LeftistHeapSerializer().serialize(root));
    }

}
