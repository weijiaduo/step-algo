package com.wjd.structure.hashtable;

/**
 * 散列表-拉链法
 * <p>
 * 散列函数：折叠法 + 取余
 * <p>
 * 散列冲突：双向拉链法
 *
 * @author weijiaduo
 * @since 2022/9/24
 */
public class LinkedHashTable implements HashTable {

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

        Node prev;
        Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public LinkedHashTable() {
        this(16);
    }

    public LinkedHashTable(int capacity) {
        this(capacity, 0.75);
    }

    public LinkedHashTable(int capacity, double factory) {
        if (capacity <= 0) {
            capacity = 16;
        }
        table = new Node[capacity];
        size = 0;
        this.factory = factory;
    }

    @Override
    public void put(int key, int value) {
        int index = hash(key);
        Node head = table[index];
        if (head == null) {
            // 数组链表头节点
            table[index] = new Node(key, value);
        } else {
            // 查找 key 值是否存在
            Node node = head, prev = null;
            while (node != null && node.key != key) {
                prev = node;
                node = node.next;
            }

            // 更新节点
            if (node != null) {
                node.value = value;
                return;
            }

            // 追加节点
            node = new Node(key, value);
            prev.next = node;
            node.prev = prev;
        }
        size++;

        // 检查是否需要扩容
        checkResize();
    }

    @Override
    public int remove(int key) {
        int index = hash(key);
        Node head = table[index];
        if (head == null) {
            return -1;
        }

        // 查找 key 的节点
        Node node = head;
        while (node != null && node.key != key) {
            node = node.next;
        }
        if (node == null) {
            return -1;
        }

        // 移除链表节点
        if (node.prev == null) {
            // 数组链表头节点
            table[index] = node.next;
            node.next = null;
        } else if (node.next == null) {
            // 链表尾节点
            node.prev.next = null;
            node.prev = null;
        } else {
            // 链表中间节点
            node.next.prev = node.prev;
            node.prev.next = node.next;
            node.prev = node.next = null;
        }

        size--;
        return node.value;
    }

    @Override
    public int get(int key) {
        Node node = find(key);
        if (node == null) {
            return -1;
        }
        return node.value;
    }

    @Override
    public boolean contains(int key) {
        return find(key) != null;
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
    protected Node find(int key) {
        int index = hash(key);
        Node node = table[index];
        if (node == null) {
            return null;
        }
        while (node != null && node.key != key) {
            node = node.next;
        }
        return node;
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
        Node[] oldTable = table;
        int newLength = oldTable.length << 1;
        table = new Node[newLength];

        // 重新映射数组元素
        for (Node head : oldTable) {
            for (Node node = head; node != null; ) {
                Node next = node.next;
                // 从旧链表断开
                node.prev = node.next = null;
                // 连接到新链表
                int index = hash(node.key);
                if (table[index] == null) {
                    // 链表头节点
                    table[index] = node;
                } else {
                    // 添加到链表尾部
                    Node tail = table[index];
                    while (tail.next != null) {
                        tail = tail.next;
                    }
                    tail.next = node;
                    node.prev = tail;
                }
                node = next;
            }
        }
    }

}
