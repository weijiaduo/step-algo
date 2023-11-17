package com.wjd.structure.list;

/**
 * 单向链表
 *
 * @author weijiaduo
 * @since 2022/8/20
 */
public class SinglyList implements List {

    /**
     * 哨兵头节点
     */
    private final ListNode head;
    /**
     * 尾节点
     */
    private ListNode tail;
    /**
     * 链表大小
     */
    private int size;

    public SinglyList() {
        head = new ListNode(-1);
        tail = head;
        size = 0;
    }

    @Override
    public int get(int index) {
        checkIndex(index);

        ListNode p = head;
        for (int i = 0; i <= index && p != null; i++) {
            p = p.next;
        }
        return p != null ? p.val : -1;
    }

    @Override
    public void add(int val) {
        ListNode newListNode = new ListNode(val);
        link(tail, newListNode);
        tail = newListNode;
        size++;
    }

    @Override
    public void insert(int index, int val) {
        checkPosition(index);

        if (index == size) {
            add(val);
            return;
        }

        ListNode p = head;
        for (int i = 0; i < index && p.next != null; i++) {
            p = p.next;
        }
        link(p, new ListNode(val));
        size++;
    }

    @Override
    public int remove(int index) {
        checkIndex(index);

        ListNode p = head;
        for (int i = 0; i < index && p.next != null; i++) {
            p = p.next;
        }
        if (p.next == null) {
            return -1;
        }

        ListNode ListNode = p.next;
        unlink(p);
        if (tail == ListNode) {
            tail = p;
        }
        size--;
        return ListNode.val;
    }

    @Override
    public boolean contains(int val) {
        ListNode p = head.next;
        while (p != null) {
            if (p.val == val) {
                return true;
            }
            p = p.next;
        }
        return false;
    }

    /**
     * 链表大小
     *
     * @return 链表大小
     */
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
     * @param p           前一个节点
     * @param newListNode 新节点
     */
    private void link(ListNode p, ListNode newListNode) {
        newListNode.next = p.next;
        p.next = newListNode;
    }

    /**
     * 取消链接节点
     *
     * @param p 上一个节点
     */
    private void unlink(ListNode p) {
        p.next = p.next.next;
    }

}
