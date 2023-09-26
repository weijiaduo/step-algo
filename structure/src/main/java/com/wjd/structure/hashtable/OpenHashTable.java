package com.wjd.structure.hashtable;

/**
 * 散列表-开放寻址法
 * <p>
 * 散列函数：折叠法 + 取余
 * <p>
 * 散列冲突：线性探测法
 *
 * @author weijiaduo
 * @since 2022/9/24
 */
public class OpenHashTable implements HashTable {

    /**
     * 散列表数组
     */
    Node[] table;
    /**
     * 元素数量
     */
    int size;
    /**
     * 负载因子
     */
    double factory;

    static class Node {
        final int key;
        int value;
        boolean deleted;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public OpenHashTable() {
        this(16);
    }

    public OpenHashTable(int capacity) {
        this(capacity, 0.75);
    }

    public OpenHashTable(int capacity, double factory) {
        if (capacity <= 0) {
            capacity = 16;
        }
        table = new Node[capacity];
        size = 0;
        this.factory = factory;
    }

    @Override
    public void put(int key, int value) {
        boolean succeed = false;
        int length = table.length;
        int index = hash(key);
        for (int i = 0; i < length; i++) {
            Node node = table[index];
            // 插入节点
            if (node == null || node.deleted) {
                node = new Node(key, value);
                table[index] = node;
                size++;
                succeed = true;
                break;
            }
            // 更新节点
            if (node.key == key) {
                node.value = value;
                succeed = true;
                break;
            }
            index = conflict(index);
        }

        // 验证添加结果
        if (!succeed) {
            // 没有添加成功，扩容后再尝试
            resize();
            put(key, value);
        } else {
            // 添加成功后，检查是否需要扩容
            checkResize();
        }
    }

    @Override
    public int remove(int key) {
        int index = findIndex(key);
        if (index < 0) {
            return -1;
        }

        // 只标记删除，不清除节点
        Node node = table[index];
        node.deleted = true;
        size--;
        return node.value;
    }

    @Override
    public int get(int key) {
        int index = findIndex(key);
        if (index < 0) {
            return -1;
        }
        return table[index].value;
    }

    @Override
    public boolean contains(int key) {
        return findIndex(key) != -1;
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * 查找指定key的索引位置
     *
     * @param key 关键字
     * @return 对应的索引/-1表示不存在
     */
    protected int findIndex(int key) {
        int length = table.length;
        int index = hash(key);
        for (int i = 0; i < length; i++) {
            Node node = table[index];
            if (node == null) {
                return -1;
            }
            // key 值存在，且没有删除标记
            if (node.key == key && !node.deleted) {
                return index;
            }
            index = conflict(index);
        }
        return -1;
    }

    /**
     * 散列函数
     * <p>
     * 关键字低 16 位和高 16 位进行异或运算，然后取余
     *
     * @param key 关键字
     * @return 散列地址
     */
    protected int hash(int key) {
        return ((key ^ (key >>> 16)) & 0xFFFF) % table.length;
    }

    /**
     * 散列冲突的下一个序列值
     * <p>
     * 线性探测法
     *
     * @param index 索引
     * @return 冲突的下一个索引
     */
    protected int conflict(int index) {
        return (index + 1) % table.length;
    }

    /**
     * 检查并进行数组扩容
     */
    protected void checkResize() {
        // 大于负载因子时，进行扩容
        if (1.0 * size / table.length > factory) {
            resize();
        }
    }

    /**
     * 动态扩容
     */
    protected synchronized void resize() {
        // 数组进行 2 倍扩容
        Node[] oldTable =  table;
        int newLength = oldTable.length << 1;
        table = new Node[newLength];

        // 重新映射数组元素
        for (Node node : oldTable) {
            if (node.deleted) {
                continue;
            }
            int index = hash(node.key);
            while (table[index] != null) {
                index = conflict(index);
            }
            table[index] = node;
        }
    }

}
