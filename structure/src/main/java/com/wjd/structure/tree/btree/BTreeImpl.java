package com.wjd.structure.tree.btree;

/**
 * B-树实现类
 *
 * @author weijiaduo
 * @since 2023/1/2
 */
public class BTreeImpl<K extends Comparable<K>, V> implements BTree<K, V> {

    /**
     * 根节点
     */
    private BTNode<K, V> root;
    /**
     * m 阶 B 树
     */
    private final int m;

    public BTreeImpl(int m) {
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
    private V get(BTNode<K, V> root, K key) {
        if (root == null) {
            return null;
        }

        // 查找当前 key 所在的位置
        int index = root.findIndex(key);
        if (key.equals(root.getKey(index))) {
            // 找到 key 对应的节点
            return root.getValue(index);
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
    private BTNode<K, V> put(BTNode<K, V> root, K key, V value) {
        // 1. 整棵树为空时，插入新节点
        if (root == null) {
            BTNode<K, V> node = new BTNode<>(m);
            return node.add(key, value);
        }

        // 验证是否已存在，存在则只需更新
        int index = root.findIndex(key);
        if (key.equals(root.getKey(index))) {
            // 更新节点
            root.setValue(index, value);
            return root;
        }

        // 2. 叶子节点，直接添加
        if (root.isLeaf()) {
            // 添加到叶子节点中，有可能发生分裂
            return root.add(key, value);
        }

        // 3. 内部节点，添加到子树的叶子节点中
        BTNode<K, V> child = root.getChild(index);
        child = put(child, key, value);

        // 添加元素后子树可能发生了分裂
        return root.overflow(index, child);
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
    private BTNode<K, V> remove(BTNode<K, V> root, K key) {
        if (root == null) {
            return null;
        }

        // 找到对应节点后移除元素
        int index = root.findIndex(key);
        if (key.equals(root.getKey(index))) {
            return root.delete(index);
        }

        // 在子树内递归移除元素
        remove(root.getChild(index), key);

        // 移除后可能需要父节点下溢
        return root.underflow(index);
    }

    @Override
    public String toString() {
        return new BTreeSerializer<K, V>().serialize(root).toString();
    }

}
