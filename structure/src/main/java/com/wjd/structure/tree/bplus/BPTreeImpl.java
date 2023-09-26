package com.wjd.structure.tree.bplus;

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
    public String toString() {
        return new BPTreeSerializer<K, V>().serialize(root).toString();
    }

}
