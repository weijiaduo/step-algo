package com.wjd.structure.queue;

/**
 * 顺序队列
 *
 * @author weijiaduo
 * @since 2022/8/30
 */
public class ArrayQueue implements Queue {

    /**
     * 队列元素
     */
    private final int[] elements;
    /**
     * 最大容量
     */
    private final int capacity;
    /**
     * 队列头
     */
    private int front;
    /**
     * 队列尾
     */
    private int rear;

    public ArrayQueue(int capacity) {
        this.capacity = capacity;
        elements = new int[this.capacity];
        front = rear = 0;
    }

    @Override
    public boolean enqueue(int val) {
        if (isFull()) {
            return false;
        }
        ensureCapacity();

        elements[rear] = val;
        rear = next(rear);
        return true;
    }

    @Override
    public int dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }
        int val = elements[front];
        front = next(front);
        return val;
    }

    @Override
    public int size() {
        return rear - front;
    }

    @Override
    public boolean isEmpty() {
        return size() <= 0;
    }

    @Override
    public boolean isFull() {
        return size() == capacity;
    }

    /**
     * 数组迁移
     */
    private void ensureCapacity() {
        if (rear == capacity) {
            int size = size();
            System.arraycopy(elements, front, elements, 0, size);
            front = 0;
            rear = size;
        }
    }

    private int next(int index) {
        return index + 1;
    }

}
