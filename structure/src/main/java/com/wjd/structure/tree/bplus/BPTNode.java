package com.wjd.structure.tree.bplus;

import java.util.ArrayList;
import java.util.List;

/**
 * B+树基类节点
 *
 * @author weijiaduo
 * @since 2023/1/10
 */
public abstract class BPTNode<K extends Comparable<K>, V> {

    /**
     * m 阶 B 树
     */
    protected final int m;
    /**
     * 节点元素阈值
     */
    protected final int threshold;
    /**
     * 节点元素数组
     * <p>
     * 元素的第 0 位始终是一个占位元素，专门用于存放最左子节点
     * <p>
     * 所以一个节点能拥有的元素个数最多是 m - 1
     */
    protected final Object[] elements;
    /**
     * 实际元素数量
     * <p>
     * 取值范围是 [0, m - 1]
     */
    protected int size;

    public BPTNode(int m) {
        this.m = m;
        this.threshold = half(m);
        elements = new Object[m];
        // 最左元素占位，用于存放最左子节点指针
        elements[0] = new Entry<K, V>(null, null);
        size = 0;
    }

    /**
     * @return 元素数量
     */
    public int size() {
        return size;
    }

    /**
     * 半数阈值 ceil(m / 2) - 1
     *
     * @param m 当前值
     * @return 半数阈值
     */
    protected int half(int m) {
        return (m + 1) / 2 - 1;
    }

    /**
     * 是否已满
     *
     * @return true已满/false未满
     */
    public boolean isFull() {
        return size == m - 1;
    }

    /**
     * 是否为空
     *
     * @return true为空/false非空
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 节点是否合法
     * <p>
     * 节点的元素数量要求大于等于阈值
     *
     * @return true合法/false非法
     */
    public boolean isLegal() {
        return size >= threshold;
    }

    /**
     * 是否可借用元素给别的节点
     * <p>
     * 当节点的元素数量大于阈值时，才可以外借元素
     *
     * @return true可借用/false不可借用
     */
    protected boolean canBorrow() {
        return size > threshold;
    }

    /**
     * 是否是叶子节点
     * <p>
     * 没有任何一个非空子节点时才是叶子节点
     *
     * @return true叶子节点/false内部节点
     */
    public abstract boolean isLeaf();

    /**
     * 获取所有的元素
     *
     * @return 元素集合
     */
    protected List<Entry<K, V>> entries() {
        List<Entry<K, V>> entries = new ArrayList<>(size);
        // 元素从索引 1 开始
        for (int i = 1; i <= size; i++) {
            entries.add(getEntry(i));
        }
        return entries;
    }

    /**
     * 获取所有的 key
     *
     * @return key 集合
     */
    public List<K> keys() {
        List<K> keys = new ArrayList<>(size);
        // 元素从索引 1 开始
        for (int i = 1; i <= size; i++) {
            keys.add(getKey(i));
        }
        return keys;
    }

    /**
     * 获取指定位置的 key
     *
     * @param index 索引
     * @return key
     */
    public K getKey(int index) {
        return getEntry(index).key;
    }

    /**
     * 获取所有的子节点
     * <p>
     * 注意：是所有可能的子节点，也包括了 null 子节点
     *
     * @return 子节点集合
     */
    public List<BPTNode<K, V>> children() {
        List<BPTNode<K, V>> children = new ArrayList<>(size + 1);
        // 子节点从索引 0 开始
        for (int i = 0; i <= size; i++) {
            children.add(getChild(i));
        }
        return children;
    }

    /**
     * 返回第一个子节点
     * <p>
     * 注意：不是第一个非空子节点，而是第 1 个元素的左子节点
     *
     * @return 第一个子节点
     */
    public BPTNode<K, V> firstChild() {
        return getChild(0);
    }

    /**
     * 返回最后一个子节点
     * <p>
     * 注意：不是最后一个非空子节点，而是最后 1 个元素的右子节点
     *
     * @return 最后一个子节点
     */
    public BPTNode<K, V> lastChild() {
        return getChild(size);
    }

    /**
     * 获取指定位置的下一层子节点
     *
     * @param index 指定位置
     * @return 下一层子树根节点
     */
    public BPTNode<K, V> getChild(int index) {
        return getEntry(index).pointer;
    }

    /**
     * 更新指定位置的子节点
     *
     * @param index 索引
     * @param node  子节点
     */
    public void setChild(int index, BPTNode<K, V> node) {
        getEntry(index).pointer = node;
    }

    /**
     * 查找指定 key 的位置
     * <p>
     * 返回最后一个小于等于 key 的位置
     *
     * @param key key
     * @return key 的位置
     */
    public int findIndex(K key) {
        // 第 0 位是占位元素，还没算在内
        return 1 + binaryle(keys(), key);
    }

    /**
     * 二分查找，返回最后一个小于等于 key 的位置
     *
     * @param keys key 集合
     * @param key  指定 key
     * @return 最后一个小于等于 key 的位置/-1
     */
    protected int binaryle(List<K> keys, K key) {
        int size = keys.size();
        int l = 0, r = size - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            K k = keys.get(m);
            if (k.compareTo(key) <= 0) {
                if (m == size - 1 || keys.get(m + 1).compareTo(key) > 0) {
                    return m;
                }
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return -1;
    }

    /**
     * 获取指定位置的元素
     *
     * @param index 索引
     * @return 元素
     */
    @SuppressWarnings("unchecked")
    protected Entry<K, V> getEntry(int index) {
        return (Entry<K, V>) elements[index];
    }

    /**
     * 更新指定位置的元素
     * <p>
     * 要求原始元素必须存在，否则应该使用 {@code insertEntry()}
     *
     * @param index 索引
     * @param entry 元素
     */
    protected void setEntry(int index, Entry<K, V> entry) {
        Entry<K, V> oldEntry = getEntry(index);
        if (oldEntry == null || index == 0) {
            throw new IllegalStateException(String.format("index: %d, size: %d", index, size));
        }

        // 保留子节点，只替换元素的 key-value
        elements[index] = entry;
        entry.pointer = oldEntry.pointer;
    }

    /**
     * 追加新元素
     * <p>
     * 此方法不会产生节点分裂，需要分裂时应调用 {@code overflow()} 方法
     *
     * @param entry 元素
     */
    protected void addEntry(Entry<K, V> entry) {
        if (isFull()) {
            throw new IllegalStateException(String.format("size: %d", size));
        }

        elements[++size] = entry;
    }

    /**
     * 插入新元素
     * <p>
     * 此方法不会产生节点分裂，需要分裂时应调用 {@code overflow()} 方法
     *
     * @param index 插入索引
     * @param entry 元素
     */
    protected void insertEntry(int index, Entry<K, V> entry) {
        if (isFull() || index == 0) {
            throw new IllegalStateException(String.format("index: %d, size: %d", index, size));
        }

        if (size >= index) {
            System.arraycopy(elements, index, elements, index + 1, size - index + 1);
            elements[index] = entry;
            size++;
        } else {
            elements[index] = entry;
            size = index;
        }
    }

    /**
     * 移除指定位置的元素
     *
     * @param index 索引
     */
    protected void removeEntry(int index) {
        if (isEmpty() || index == 0) {
            throw new IllegalStateException(String.format("index: %d, size: %d", index, size));
        }

        if (size > index) {
            System.arraycopy(elements, index + 1, elements, index, size - index);
        }
        elements[size] = null;
        size--;
    }

    @Override
    public String toString() {
        String[] s = new String[size];
        for (int i = 1; i <= size; i++) {
            s[i - 1] = elements[i].toString();
        }
        return "[" + String.join(", ", s) + "]";
    }

    /**
     * 单个元素
     */
    public static class Entry<K extends Comparable<K>, V> {
        /**
         * key
         */
        K key;
        /**
         * value
         */
        V value;
        /**
         * 右子节点指针，即大于 key 的子树
         */
        BPTNode<K, V> pointer;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
            pointer = null;
        }

        @Override
        public String toString() {
            return "{" + key + " : " + value + "}";
        }
    }

}
