package com.wjd.structure.heap.index;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 索引堆
 *
 * @author weijiaduo
 * @since 2023/2/25
 */
public class IndexHeapImpl<T extends Comparable<T>> implements IndexHeap<T> {

    /**
     * 元素数组
     */
    private final T[] elements;
    /**
     * 堆数组，堆元素 -> 元素数组索引
     * <p>
     * 另外，heap[0] 不用
     */
    private final int[] heap;
    /**
     * 元素数组索引 -> 堆数组索引
     */
    private final int[] idxMap;
    /**
     * 元素比较器
     */
    private final Comparator<T> cmp;
    /**
     * 元素数量
     */
    private int size;

    public IndexHeapImpl(int capacity) {
        this(capacity, Comparator.reverseOrder());
    }

    public IndexHeapImpl(int capacity, Comparator<T> cmp) {
        this.cmp = cmp;
        //noinspection unchecked
        elements = (T[]) new Comparable[capacity];
        heap = new int[capacity + 1];
        idxMap = new int[capacity];
        Arrays.fill(heap, -1);
        Arrays.fill(idxMap, -1);
    }

    @Override
    public void insert(int idx, T val) {
        if (idx < 0 || idx >= elements.length) {
            throw new IllegalStateException(String.format("size: %d, index: %d", elements.length, idx));
        }

        if (containsIndex(idx)) {
            // 更新元素
            elements[idx] = val;
            int hp = idxMap[idx];
            siftUp(hp);
            siftDown(hp);
        } else {
            // 插入元素
            elements[idx] = val;
            int hp = ++size;
            heap[hp] = idx;
            idxMap[idx] = hp;
            siftUp(hp);
        }
    }

    @Override
    public T removeFirst() {
        if (size <= 0) {
            throw new IllegalStateException("Heap isEmpty!");
        }

        int hp = 1;
        int idx = heap[hp];
        T val = elements[idx];
        swap(hp, size);
        heap[size] = -1;
        idxMap[idx] = -1;
        elements[idx] = null;
        size--;

        siftDown(hp);
        return val;
    }

    @Override
    public T remove(int idx) {
        if (!containsIndex(idx)) {
            throw new IllegalStateException(String.format("size: %d, index: %d", elements.length, idx));
        }

        int hp = idxMap[idx];
        T val = elements[idx];
        swap(hp, size);
        heap[size] = -1;
        idxMap[idx] = -1;
        elements[idx] = null;
        size--;

        // 删除最后一个元素无需处理
        if (hp <= size) {
            siftUp(hp);
            siftDown(hp);
        }
        return val;
    }

    @Override
    public T first() {
        if (size <= 0) {
            throw new IllegalStateException("Heap isEmpty!");
        }
        return elements[heap[1]];
    }

    @Override
    public int firstIndex() {
        if (size <= 0) {
            throw new IllegalStateException("Heap isEmpty!");
        }
        return heap[1];
    }

    @Override
    public boolean containsIndex(int idx) {
        if (idx < 0 || idx >= elements.length) {
            return false;
        }
        return idxMap[idx] != -1;
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * 从上往下调整
     *
     * @param hp 当前节点，堆数组索引
     */
    private void siftDown(int hp) {
        int i = hp;
        while (i < size) {
            int m = i;
            int l = left(i);
            if (l <= size && prior(l, m)) {
                m = l;
            }
            int r = right(i);
            if (r <= size && prior(r, m)) {
                m = r;
            }
            if (m == i) {
                break;
            }
            swap(i, m);
            i = m;
        }
    }

    /**
     * 从下往上调整
     *
     * @param hp 当前节点，堆数组索引
     */
    private void siftUp(int hp) {
        int i = hp;
        while (i > 1) {
            int p = parent(i);
            if (p > 0 && prior(i, p)) {
                swap(i, p);
                i = p;
            } else {
                break;
            }
        }
    }

    /**
     * 父节点索引
     *
     * @param hp 当前节点，堆数组索引
     * @return 父节点索引
     */
    private int parent(int hp) {
        return hp / 2;
    }

    /**
     * 左子节点索引
     *
     * @param hp 当前节点，堆数组索引
     * @return 左子节点索引
     */
    private int left(int hp) {
        return 2 * hp;
    }

    /**
     * 右子节点索引
     *
     * @param hp 当前节点，堆数组索引
     * @return 右子节点索引
     */
    private int right(int hp) {
        return 2 * hp + 1;
    }

    /**
     * elements[heap[hp]] 是否优先于 elements[heap[hq]]
     *
     * @param hp 堆数组索引 hp
     * @param hq 堆数组索引 hq
     * @return true/false
     */
    private boolean prior(int hp, int hq) {
        return cmp.compare(elements[heap[hp]], elements[heap[hq]]) < 0;
    }

    /**
     * 交换 2 个元素的位置
     *
     * @param hp 堆数组索引 hp
     * @param hq 堆数组索引 hq
     */
    private void swap(int hp, int hq) {
        if (hp == hq) {
            return;
        }
        int t = heap[hp];
        heap[hp] = heap[hq];
        heap[hq] = t;
        idxMap[heap[hp]] = hp;
        idxMap[heap[hq]] = hq;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 1; i <= size; i++) {
            if (i > 1) {
                sb.append(", ");
            }
            sb.append("(")
                    .append(heap[i])
                    .append(", ")
                    .append(elements[heap[i]])
                    .append(")");
        }
        sb.append("]");
        return sb.toString();
    }

}
