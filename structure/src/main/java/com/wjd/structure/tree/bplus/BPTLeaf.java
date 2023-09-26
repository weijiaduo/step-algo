package com.wjd.structure.tree.bplus;

import java.util.List;

/**
 * B+树叶子节点
 *
 * @author weijiaduo
 * @since 2023/1/10
 */
public class BPTLeaf<K extends Comparable<K>, V> extends BPTNode<K, V> {

    /**
     * 双向链表，上一个节点
     */
    private BPTLeaf<K, V> prev;
    /**
     * 双向链表，下一个节点
     */
    private BPTLeaf<K, V> next;

    public BPTLeaf(int m) {
        super(m);
    }

    @Override
    public boolean isLeaf() {
        return true;
    }

    /**
     * 添加新元素
     *
     * @param key   key
     * @param value value
     */
    public BPTNode<K, V> add(K key, V value) {
        int index = findIndex(key);

        // 1. 空间足够，可以直接插入新元素
        if (!this.isFull()) {
            insertEntry(index + 1, new Entry<>(key, value));
            return this;
        }

        // 2. 空间不足，需要分裂节点
        return splitNodes(index, new Entry<>(key, value));
    }

    /**
     * 叶子节点已满，无法插入新元素时，需分裂成 3 个节点
     *
     * @param index    元素插入位置
     * @param newEntry 新元素
     * @return 分裂后的父节点
     */
    private BPTNode<K, V> splitNodes(int index, Entry<K, V> newEntry) {
        List<Entry<K, V>> curEntries = entries();
        curEntries.add(index, newEntry);
        int newSize = curEntries.size();
        int mid = newSize / 2;

        // 根节点（内部节点只需要索引就够了，不需要数据）
        BPTNode<K, V> root = new BPTInternal<>(m);
        Entry<K, V> entry = curEntries.get(mid);
        root.addEntry(new Entry<>(entry.key, null));
        // 左子节点
        BPTLeaf<K, V> left = new BPTLeaf<>(m);
        for (int i = 0; i < mid; i++) {
            left.addEntry(curEntries.get(i));
        }
        // 右子节点（和内部节点不同，右子节点包括了根节点元素）
        BPTLeaf<K, V> right = new BPTLeaf<>(m);
        for (int i = mid; i < newSize; i++) {
            right.addEntry(curEntries.get(i));
        }
        // 设置父子节点关系
        root.setChild(0, left);
        root.setChild(root.size, right);

        // 更新叶子节点双向链表指针
        left.prev = this.prev;
        if (this.prev != null) {
            this.prev.next = left;
        }
        left.next = right;
        right.prev = left;
        if (this.next != null) {
            this.next.prev = right;
        }
        right.next = this.next;
        this.prev = this.next = null;
        return root;
    }

    /**
     * 删除指定位置的元素
     * <p>
     * 删除元素后，节点可能会产生下溢，从而返回了一个空元素节点
     *
     * @param index 索引
     * @return 删除后的根节点
     */
    public BPTNode<K, V> delete(int index) {
        // 叶子节点直接删除元素，删除后可能会变成元素为空的空节点
        // 空节点会由内部节点处理掉，或者由根节点处理掉
        removeEntry(index);
        return this;
    }

    /**
     * 获取指定位置的 value
     *
     * @param index 索引
     * @return value
     */
    public V getValue(int index) {
        return getEntry(index).value;
    }

    /**
     * 设置指定位置的 value
     *
     * @param index 索引
     * @param value 值
     */
    public void setValue(int index, V value) {
        getEntry(index).value = value;
    }

    /**
     * @return 上一个叶子节点
     */
    public BPTLeaf<K, V> getPrev() {
        return prev;
    }

    /**
     * @return 下一个叶子节点
     */
    public BPTLeaf<K, V> getNext() {
        return next;
    }

    /**
     * 更新上一个叶子节点
     *
     * @param prev 上一个叶子节点
     */
    protected void setPrev(BPTLeaf<K, V> prev) {
        this.prev = prev;
    }

    /**
     * 更新下一个叶子节点
     *
     * @param next 下一个叶子节点
     */
    protected void setNext(BPTLeaf<K, V> next) {
        this.next = next;
    }

}
