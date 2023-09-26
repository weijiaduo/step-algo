package com.wjd.structure.queue;

/**
 * 循环双端队列
 *
 * @author weijiaduo
 * @since 2022/8/19
 */
public class CircularDeque implements Deque {

    private final int[] elements;
    private final int capacity;
    private int front;
    private int rear;

    public CircularDeque(int capacity) {
        this.capacity = capacity + 1;
        elements = new int[this.capacity];
        front = rear = 0;
    }

    @Override
    public boolean insertFront(int value) {
        if (isFull()) {
            return false;
        }
        elements[front] = value;
        front = next(front);
        return true;
    }

    @Override
    public boolean insertLast(int value) {
        if (isFull()) {
            return false;
        }
        rear = prev(rear);
        elements[rear] = value;
        return true;
    }

    @Override
    public boolean deleteFront() {
        if (isEmpty()) {
            return false;
        }
        front = prev(front);
        return true;
    }

    @Override
    public boolean deleteLast() {
        if (isEmpty()) {
            return false;
        }
        rear = next(rear);
        return true;
    }

    @Override
    public int getFront() {
        if (isEmpty()) {
            return -1;
        }
        int index = prev(front);
        return elements[index];
    }

    @Override
    public int getRear() {
        if (isEmpty()) {
            return -1;
        }
        return elements[rear];
    }

    @Override
    public boolean isEmpty() {
        return front == rear;
    }

    @Override
    public boolean isFull() {
        return (front + 1) % capacity == rear;
    }

    private int next(int index) {
        return (index + 1) % capacity;
    }

    private int prev(int index) {
        return (index - 1 + capacity) % capacity;
    }

}
