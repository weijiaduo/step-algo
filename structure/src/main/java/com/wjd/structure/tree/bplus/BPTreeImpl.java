package com.wjd.structure.tree.bplus;

import java.util.Map;
import java.util.NoSuchElementException;

/**
 * B+ 树实现类
 *
 * @author weijiaduo
 * @since 2023/1/9
 */
public class BPTreeImpl<K extends Comparable<K>, V> implements BPTree<K, V> {

    /**
     * 根节点
     */
    private BPTNode<K, V> root;
    /**
     * m 阶 B+ 树
     */
    private final int m;

    /**
     * B+树构造器
     *
     * @param m 指定 m 阶 B+ 树
     */
    public BPTreeImpl(int m) {
        this.m = m;
    }

    @Override
    public V get(K key) {
        return get(root, key);
    }

    /**
     * 从当前节点开始找指定 key 的值
     *
     * @param root 当前根节点
     * @param key  key
     * @return value
     */
    private V get(BPTNode<K, V> root, K key) {
        if (root == null) {
            return null;
        }

        // 查找当前 key 所在的位置
        int index = root.findIndex(key);
        if (root.isLeaf()) {
            if (key.equals(root.getKey(index))) {
                BPTLeaf<K, V> leaf = (BPTLeaf<K, V>) root;
                return leaf.getValue(index);
            } else {
                return null;
            }
        } else {
            // 往子树继续找 key 对应的节点
            return get(root.getChild(index), key);
        }
    }

    @Override
    public void put(K key, V value) {
        root = put(root, key, value);
    }

    /**
     * 在当前树内添加指定的 key-value
     *
     * @param root  当前根节点
     * @param key   key
     * @param value value
     * @return 新的根节点
     */
    private BPTNode<K, V> put(BPTNode<K, V> root, K key, V value) {
        // 1. 整棵树为空时，插入新节点
        if (root == null) {
            BPTLeaf<K, V> leaf = new BPTLeaf<>(m);
            return leaf.add(key, value);
        }

        int index = root.findIndex(key);
        if (root.isLeaf()) {
            // 2. 叶子节点
            BPTLeaf<K, V> leaf = (BPTLeaf<K, V>) root;

            // 验证是否已存在，存在则只需更新值
            if (key.equals(leaf.getKey(index))) {
                leaf.setValue(index, value);
                return leaf;
            }

            // 添加到叶子节点中，有可能发生了分裂
            return leaf.add(key, value);
        } else {
            // 3. 内部节点，添加到子树的叶子节点中
            BPTInternal<K, V> node = (BPTInternal<K, V>) root;
            BPTNode<K, V> child = node.getChild(index);
            child = put(child, key, value);

            // 添加到子树中，有可能发生了分裂
            return node.overflow(index, child);
        }
    }

    @Override
    public void remove(K key) {
        root = remove(root, key);
        if (root != null && root.isEmpty()) {
            // 树的高度下降
            root = root.firstChild();
        }
    }

    /**
     * 在当前树内删除指定 key 的节点
     *
     * @param root 当前根节点
     * @param key  key
     * @return 新的根节点
     */
    private BPTNode<K, V> remove(BPTNode<K, V> root, K key) {
        if (root == null) {
            return null;
        }

        int index = root.findIndex(key);
        if (root.isLeaf()) {
            BPTLeaf<K, V> leaf = (BPTLeaf<K, V>) root;
            if (key.equals(root.getKey(index))) {
                // 找到对应节点后移除元素
                return leaf.delete(index);
            } else {
                // 没找到对应节点，直接返回
                return leaf;
            }
        } else {
            BPTInternal<K, V> node = (BPTInternal<K, V>) root;
            // 在子树内递归移除元素
            remove(node.getChild(index), key);
            // 移除后可能需要父节点下溢
            return node.underflow(index);
        }
    }

    @Override
    public BPListIterator<K, V> iterator() {
        return new BPLtr();
    }

    final class BPLtr implements BPListIterator<K, V> {

        /**
         * 头节点
         */
        private final BPTLeaf<K, V> head;
        /**
         * 上一个访问的节点，往前移动时 prev = null
         */
        private BPTLeaf<K, V> prev;
        /**
         * 下一个访问的节点
         */
        private BPTLeaf<K, V> next;
        /**
         * 下一个元素索引，从 1 开始
         */
        private int nextIndex;

        BPLtr() {
            BPTNode<K, V> min = root;
            while (min != null && !min.isLeaf()) {
                min = min.getChild(0);
            }
            if (min instanceof BPTLeaf<K, V>) {
                head = (BPTLeaf<K, V>) min;
            } else {
                throw new IllegalStateException("Not found head node!");
            }

            // 表头前面：next == null && prev == null
            // 表尾后面：next == null && prev != null
            next = prev = null;
            nextIndex = 0;
        }

        @Override
        public boolean hasNext() {
            return next != null || prev == null;
        }

        @Override
        public Map.Entry<K, V> next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            // 初始化为头节点
            if (next == null) {
                next = head;
                nextIndex = 1;
            }
            Node<K, V> node = new Node<>(next.getKey(nextIndex), next.getValue(nextIndex));
            // 当前节点遍历完了，到下一个节点
            if (++nextIndex > next.size()) {
                prev = next;
                next = next.next;
                nextIndex = 1;
            }
            return node;
        }

        @Override
        public boolean hasPrev() {
            BPTLeaf<K, V> p;
            if (next != null) {
                p = nextIndex != 1 ? next : next.prev;
            } else {
                p = prev;
            }
            return p != null;
        }

        @Override
        public Map.Entry<K, V> prev() {
            if (!hasPrev()) {
                throw new NoSuchElementException();
            }

            BPTLeaf<K, V> cur;
            int index;
            if (next != null) {
                if (nextIndex != 1) {
                    cur = next;
                    index = nextIndex - 1;
                } else {
                    cur = next.prev;
                    index = cur.size;
                }
            } else {
                cur = prev;
                index = cur.size;
            }
            // 更新节点
            prev = null;
            next = cur;
            nextIndex = index;
            return new Node<>(cur.getKey(index), cur.getValue(index));
        }

    }

    final static class Node<K, V> implements Map.Entry<K, V> {

        private final K key;
        private V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            return this.value = value;
        }

    }

    @Override
    public String toString() {
        return new BPTreeSerializer<K, V>().serialize(root).toString();
    }

}
