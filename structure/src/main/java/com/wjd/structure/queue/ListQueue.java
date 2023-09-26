package com.wjd.structure.queue;

/**
 * 链式队列
 *
 * @author weijiaduo
 * @since 2022/8/30
 */
public class ListQueue implements Queue {

    static class Node {
        int val;
        Node next;

        public Node(int val) {
            this.val = val;
        }
    }

    /**
     * 队列头节点
     */
    private Node head;
    /**
     * 队列尾节点
     */
    private Node tail;
    /**
     * 队列大小
     */
    private int size;

    public ListQueue() {
        head = tail = null;
        size = 0;
    }

    @Override
    public boolean enqueue(int val) {
        if (isFull()) {
            return false;
        }

        Node node = new Node(val);
        if (head == null) {
            head = node;
        } else {
            tail.next = node;
        }
        tail = node;
        size++;
        return true;
    }

    @Override
    public int dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }

        Node node = head;
        if (node == tail) {
            tail = null;
        }
        head = head.next;
        size--;
        return node.val;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size <= 0;
    }

    @Override
    public boolean isFull() {
        return false;
    }
}
