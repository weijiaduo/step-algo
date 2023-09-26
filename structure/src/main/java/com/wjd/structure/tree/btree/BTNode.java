package com.wjd.structure.tree.btree;

import java.util.ArrayList;
import java.util.List;

/**
 * B-树节点
 *
 * @author weijiaduo
 * @since 2023/1/2
 */
public class BTNode<K extends Comparable<K>, V> {

    /**
     * m 阶 B 树
     */
    private final int m;
    /**
     * 节点元素阈值
     */
    private final int threshold;
    /**
     * 节点元素数组
     * <p>
     * 元素的第 0 位始终是一个占位元素，专门用于存放最左子节点
     * <p>
     * 所以一个节点能拥有的元素个数最多是 m - 1
     */
    private final Object[] elements;
    /**
     * 实际元素数量
     * <p>
     * 取值范围是 [0, m - 1]
     */
    private int size;

    public BTNode(int m) {
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
    private int half(int m) {
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
    private boolean canBorrow() {
        return size > threshold;
    }

    /**
     * 是否是叶子节点
     * <p>
     * 没有任何一个非空子节点时才是叶子节点
     *
     * @return true叶子节点/false内部节点
     */
    public boolean isLeaf() {
        for (int i = 0; i <= size; i++) {
            if (getChild(i) != null) {
                return false;
            }
        }
        return true;
    }

    /**
     * 获取所有的元素
     *
     * @return 元素集合
     */
    public List<Entry<K, V>> entries() {
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
     * 获取所有的 value
     *
     * @return value 集合
     */
    public List<V> values() {
        List<V> values = new ArrayList<>(size);
        // 元素从索引 1 开始
        for (int i = 1; i <= size; i++) {
            values.add(getValue(i));
        }
        return values;
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
     * 获取所有的子节点
     * <p>
     * 注意：是所有可能的子节点，也包括了 null 子节点
     *
     * @return 子节点集合
     */
    public List<BTNode<K, V>> children() {
        List<BTNode<K, V>> children = new ArrayList<>(size + 1);
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
    public BTNode<K, V> firstChild() {
        return getChild(0);
    }

    /**
     * 返回最后一个子节点
     * <p>
     * 注意：不是最后一个非空子节点，而是最后 1 个元素的右子节点
     *
     * @return 最后一个子节点
     */
    public BTNode<K, V> lastChild() {
        return getChild(size);
    }

    /**
     * 获取指定位置的下一层子节点
     *
     * @param index 指定位置
     * @return 下一层子树根节点
     */
    public BTNode<K, V> getChild(int index) {
        return getEntry(index).pointer;
    }

    /**
     * 更新指定位置的子节点
     *
     * @param index 索引
     * @param node  子节点
     */
    public void setChild(int index, BTNode<K, V> node) {
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
    private int binaryle(List<K> keys, K key) {
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
     * 添加新元素
     *
     * @param key   key
     * @param value value
     */
    public BTNode<K, V> add(K key, V value) {
        BTNode<K, V> node = new BTNode<>(m);
        node.addEntry(new Entry<>(key, value));
        int index = findIndex(key);
        return overflow(index, node);
    }

    /**
     * 节点上溢处理
     * <p>
     * 子树添加新元素后，可能会分裂成新节点，替代原有的子节点
     *
     * @param index 索引
     * @param node  新节点
     * @return 添加后的当前节点
     */
    public BTNode<K, V> overflow(int index, BTNode<K, V> node) {
        if (node == null || node.isEmpty()) {
            return this;
        }

        // 还是旧节点，说明子节点没有上溢，无需处理
        BTNode<K, V> cur = getChild(index);
        if (cur == node) {
            return this;
        }

        // 1. 当前空间足够插入
        if (size + node.size() < m) {
            // 新子节点的左子节点替代旧子节点的位置
            setChild(index, node.firstChild());
            for (Entry<K, V> entry : node.entries()) {
                insertEntry(++index, entry);
            }
            return this;
        }

        // 2. 当前空间不够，需要分裂成 3 个节点
        return splitNodes(index, node);
    }

    /**
     * 子节点上溢后，父节点已满，则需要分裂节点，将 1 个节点拆分成 3 个节点
     *
     * @param index   上溢子节点的父元素索引
     * @param newNode 上溢的子节点
     * @return 分裂后新的父节点
     */
    private BTNode<K, V> splitNodes(int index, BTNode<K, V> newNode) {
        // 新子节点的左子节点替代旧子节点的位置
        setChild(index, newNode.firstChild());

        int newSize = size + newNode.size();
        List<Entry<K, V>> allEntries = new ArrayList<>(newSize);
        List<Entry<K, V>> curEntries = entries();
        for (int i = 0; i < index && i < size; i++) {
            allEntries.add(curEntries.get(i));
        }
        allEntries.addAll(newNode.entries());
        for (int i = index; i < size; i++) {
            allEntries.add(curEntries.get(i));
        }

        // 根节点
        int mid = newSize / 2;
        BTNode<K, V> root = new BTNode<>(m);
        for (int i = mid; i <= mid; i++) {
            root.addEntry(allEntries.get(i));
        }
        // 左子节点
        BTNode<K, V> left = new BTNode<>(m);
        for (int i = 0; i < mid; i++) {
            left.addEntry(allEntries.get(i));
        }
        // 右子节点
        BTNode<K, V> right = new BTNode<>(m);
        for (int i = mid + 1; i < newSize; i++) {
            right.addEntry(allEntries.get(i));
        }

        // 边界指针
        left.setChild(0, firstChild());
        right.setChild(0, root.lastChild());
        root.setChild(0, left);
        root.setChild(root.size, right);
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
    public BTNode<K, V> delete(int index) {
        if (isEmpty() || index == 0) {
            throw new IllegalStateException(String.format("index: %d, size: %d", index, size));
        }

        if (isLeaf()) {
            // 叶子节点
            // 直接删除，删除后可能会变成元素为空的空节点
            // 空节点将由父节点的 underflow 处理掉，或者由根节点处理掉
            removeEntry(index);
            return this;
        }

        // 内部节点
        // 使用前驱或后驱进行替换
        int rpIndex = getReplacer(this, index);
        BTNode<K, V> child = getChild(rpIndex);
        BTNode<K, V> newChild;
        if (rpIndex < index) {
            // 前驱元素替换
            Entry<K, V> max = max(child);
            newChild = removeMax(child);
            setChild(rpIndex, newChild);
            setEntry(index, max);
        } else {
            // 后驱元素替换
            Entry<K, V> min = min(child);
            newChild = removeMin(child);
            setChild(rpIndex, newChild);
            setEntry(index, min);
        }

        // 移除后可能需要父节点下溢
        return underflow(rpIndex);
    }

    /**
     * 节点下溢处理
     * <p>
     * 存在非法子节点时，可能需要对父节点进行下溢操作
     *
     * @param index 父节点索引
     * @return 根节点
     */
    public BTNode<K, V> underflow(int index) {
        // 验证子节点是否合法
        BTNode<K, V> cur = getChild(index);
        if (cur == null || cur.isLegal()) {
            return this;
        }

        // 1. 从兄弟节点借一个元素
        BTNode<K, V> left = null;
        if (index > 0) {
            left = getChild(index - 1);
            if (left != null && left.canBorrow()) {
                return borrowLeft(index);
            }
        }
        BTNode<K, V> right = null;
        if (index < size) {
            right = getChild(index + 1);
            if (right != null && right.canBorrow()) {
                return borrowRight(index);
            }
        }
        if (left == null && right == null) {
            return this;
        }

        // 2. 合并父元素 + 左右子节点
        // 始终把当前节点作为合并时的右子节点
        return mergeNodes(left != null ? index : index + 1);
    }

    /**
     * 借用左子节点的值
     * <p>
     * 实际就是右旋，将父节点转到右子节点，左子节点的元素转到父节点
     *
     * @param index 父节点索引
     */
    private BTNode<K, V> borrowLeft(int index) {
        BTNode<K, V> left = getChild(index - 1);
        BTNode<K, V> right = getChild(index);

        Entry<K, V> parentEntry = getEntry(index);
        Entry<K, V> leftEntry = left.getEntry(left.size);
        BTNode<K, V> leftRight = left.lastChild();

        // 父节点转到右子节点
        right.insertEntry(1, parentEntry);
        right.setChild(1, right.firstChild());
        // 左子节点转到父节点
        left.removeEntry(left.size);
        setEntry(index, leftEntry);
        setChild(index, right);
        right.setChild(0, leftRight);
        return this;
    }

    /**
     * 借用右子节点的值
     * <p>
     * 实际就是左旋，将父元素转到左子节点，右子节点的元素转到父节点
     *
     * @param index 父节点索引
     */
    private BTNode<K, V> borrowRight(int index) {
        BTNode<K, V> right = getChild(index + 1);
        BTNode<K, V> left = getChild(index);

        Entry<K, V> parentEntry = getEntry(index + 1);
        Entry<K, V> rightEntry = right.getEntry(1);
        BTNode<K, V> rightLeft = right.firstChild();

        // 父节点转到左子节点
        left.addEntry(parentEntry);
        // 右子节点转到父节点
        right.setChild(0, right.getChild(1));
        right.removeEntry(1);
        setEntry(index + 1, rightEntry);
        setChild(index + 1, right);
        left.setChild(left.size, rightLeft);
        return this;
    }

    /**
     * 父节点元素 + 右子节点，全都合并到左子节点
     * <p>
     * 因为父节点元素和右子节点是一一对应的，所以都是按照“父 + 右 --> 左”进行合并
     * <p>
     * 方便同时删除父节点元素和右子节点
     *
     * @param index 父元素索引
     */
    private BTNode<K, V> mergeNodes(int index) {
        BTNode<K, V> left = getChild(index - 1);
        BTNode<K, V> right = getChild(index);

        // 父节点元素合并到左子节点
        Entry<K, V> parentEntry = getEntry(index);
        left.addEntry(parentEntry);

        // 右子节点合并到左子节点
        left.setChild(left.size, right.firstChild());
        for (Entry<K, V> entry : right.entries()) {
            left.addEntry(entry);
        }

        // 移除父节点元素
        removeEntry(index);
        return this;
    }

    /**
     * 获取可替换子节点索引（前驱/后驱）
     *
     * @param root  当前根节点
     * @param index 当前索引
     * @return 可替换子节点索引
     */
    private int getReplacer(BTNode<K, V> root, int index) {
        BTNode<K, V> right = null;
        if (index <= root.size) {
            right = root.getChild(index);
        }
        if (right == null) {
            return index - 1;
        }
        BTNode<K, V> left = null;
        if (index > 0) {
            left = root.getChild(index - 1);
        }
        if (left == null) {
            return index;
        }

        if (left.canBorrow()) {
            return index - 1;
        } else {
            return index;
        }
    }

    /**
     * 获取最大值
     *
     * @param root 当前根节点
     * @return 最大值
     */
    private Entry<K, V> max(BTNode<K, V> root) {
        if (root == null) {
            return null;
        }

        if (root.isLeaf()) {
            return root.getEntry(root.size);
        }
        return max(root.lastChild());
    }

    /**
     * 移除最大值
     *
     * @param root 当前根节点
     * @return 移除后的根节点
     */
    private BTNode<K, V> removeMax(BTNode<K, V> root) {
        if (root == null) {
            return null;
        }

        if (root.isLeaf()) {
            root.removeEntry(root.size);
            return root;
        }

        int index = root.size;
        removeMax(root.getChild(index));
        return root.underflow(index);
    }

    /**
     * 获取最小值
     *
     * @param root 当前根节点
     * @return 最小值
     */
    private Entry<K, V> min(BTNode<K, V> root) {
        if (root == null) {
            return null;
        }

        if (root.isLeaf()) {
            return root.getEntry(1);
        }
        return min(root.firstChild());
    }

    /**
     * 移除最小值
     *
     * @param root 当前根节点
     * @return 移除值后的根节点
     */
    private BTNode<K, V> removeMin(BTNode<K, V> root) {
        if (root == null) {
            return null;
        }

        if (root.isLeaf()) {
            root.removeEntry(1);
            return root;
        }

        int index = 0;
        removeMin(root.getChild(index));
        return root.underflow(index);
    }

    /**
     * 获取指定位置的元素
     *
     * @param index 索引
     * @return 元素
     */
    @SuppressWarnings("unchecked")
    private Entry<K, V> getEntry(int index) {
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
    private void setEntry(int index, Entry<K, V> entry) {
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
    private void addEntry(Entry<K, V> entry) {
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
    private void insertEntry(int index, Entry<K, V> entry) {
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
    private void removeEntry(int index) {
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
    static class Entry<K extends Comparable<K>, V> {
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
        BTNode<K, V> pointer;

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
