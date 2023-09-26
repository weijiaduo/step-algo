package com.wjd.structure.queue;

/**
 * 循环队列
 *
 * @author weijiaduo
 * @since 2022/8/30
 */
public class CircularQueue implements Queue {

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

    public CircularQueue(int capacity) {
        this.capacity = capacity + 1;
        elements = new int[this.capacity];
        front = rear = 0;
    }

    @Override
    public boolean enqueue(int val) {
        if (isFull()) {
            return false;
        }

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
        return (rear + capacity - front) % capacity;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean isFull() {
        return size() == capacity - 1;
    }

    private int next(int index) {
        return (index + 1) % capacity;
    }

}
