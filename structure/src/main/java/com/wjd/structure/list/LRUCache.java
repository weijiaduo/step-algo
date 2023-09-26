package com.wjd.structure.list;

import java.util.HashMap;
import java.util.Map;

/**
 * 146. LRU 缓存
 * <p>
 * 请你设计并实现一个满足 LRU (最近最少使用) 缓存 约束的数据结构。
 * <p>
 * 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
 *
 * @author weijiaduo
 * @since 2022/6/29
 */
public class LRUCache {

    static class Node {
        int key;
        int val;
        Node prev;
        Node next;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    Map<Integer, Node> nodeMap = new HashMap<>();
    int capacity;
    Node head, tail;

    /**
     * 思路：哈希表存储节点，双向链表保存节点热度，最新使用的节点放链表头，最不常使用的在链尾
     * <p>
     * 执行耗时:41 ms,击败了84.68% 的Java用户
     * 内存消耗:108.6 MB,击败了56.40% 的Java用户
     */
    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        Node node = nodeMap.get(key);
        if (node == null) {
            // -1表示未找到
            return -1;
        }

        // 移动到链表头
        removeNode(node);
        addNode(node);
        return node.val;
    }

    public void put(int key, int value) {
        Node node = nodeMap.get(key);
        if (node != null) {
            // 更新节点
            node.val = value;
            // 从原位置移除
            removeNode(node);
        } else {
            // 新增节点
            node = new Node(key, value);
            nodeMap.put(key, node);
        }

        // 移动到链表头
        addNode(node);

        // 移除最近不使用的节点
        if (nodeMap.size() > capacity) {
            nodeMap.remove(tail.key);
            removeNode(tail);
        }
    }

    /**
     * 从链表中移除节点
     *
     * @param node 节点
     */
    private void removeNode(Node node) {
        if (node == head) {
            // 移除链表头
            head = node.next;
            if (head != null) {
                head.prev = null;
            }
        } else if (node == tail) {
            // 移除链表尾
            tail = node.prev;
            if (tail != null) {
                tail.next = null;
            }
        } else {
            // 移除中间节点
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
        // 断开节点的所有连接
        node.prev = node.next = null;
    }

    /**
     * 添加节点到链表头
     *
     * @param node 节点
     */
    private void addNode(Node node) {
        // 添加到链头
        node.next = head;
        if (head != null) {
            head.prev = node;
        }
        head = node;
        if (tail == null) {
            tail = head;
        }
    }

}
