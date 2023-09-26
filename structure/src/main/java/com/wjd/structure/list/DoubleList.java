package com.wjd.structure.list;

/**
 * 双向链表
 *
 * @author weijiaduo
 * @since 2022/8/20
 */
public class DoubleList implements List {

    static class Node {
        int val;
        Node prev;
        Node next;

        public Node(int val) {
            this.val = val;
            this.prev = null;
            this.next = null;
        }
    }

    /**
     * 哨兵头节点
     */
    private Node head;
    /**
     * 尾节点
     */
    private Node tail;
    /**
     * 链表大小
     */
    private int size;

    public DoubleList() {
        head = new Node(-1);
        tail = head;
        size = 0;
    }

    @Override
    public int get(int index) {
        checkIndex(index);

        Node p = head;
        for (int i = 0; i <= index && p != null; i++) {
            p = p.next;
        }
        return p != null ? p.val : -1;
    }

    @Override
    public void add(int val) {
        Node newNode = new Node(val);
        link(tail, newNode);
        tail = newNode;
        size++;
    }

    @Override
    public void insert(int index, int val) {
        checkPosition(index);

        if (index == size) {
            add(val);
            return;
        }

        Node p = head;
        for (int i = 0; i < index && p.next != null; i++) {
            p = p.next;
        }
        link(p, new Node(val));
        size++;
    }

    @Override
    public int remove(int index) {
        checkIndex(index);

        Node p = head;
        for (int i = 0; i < index && p.next != null; i++) {
            p = p.next;
        }
        if (p == null || p.next == null) {
            return -1;
        }

        Node node = p.next;
        unlink(p);
        if (tail == node) {
            tail = p;
        }
        size--;
        return node.val;
    }

    @Override
    public boolean contains(int val) {
        Node p = head.next;
        while (p != null) {
            if (p.val == val) {
                return true;
            }
            p = p.next;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * 检查索引范围
     *
     * @param index 索引
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index: " + index);
        }
    }

    /**
     * 检查插入位置范围
     *
     * @param index 索引
     */
    private void checkPosition(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("position: " + index);
        }
    }

    /**
     * 链接新节点
     *
     * @param p       前一个节点
     * @param newNode 新节点
     */
    private void link(Node p, Node newNode) {
        newNode.prev = p;
        newNode.next = p.next;
        if (p.next != null) {
            p.next.prev = newNode;
        }
        p.next = newNode;
    }

    /**
     * 取消链接节点
     *
     * @param p 上一个节点
     */
    private void unlink(Node p) {
        p.next = p.next.next;
        if (p.next != null) {
            p.next.prev = p;
        }
    }

}
