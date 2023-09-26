package com.wjd.structure.stack;

/**
 * 顺序栈
 *
 * @author weijiaduo
 * @since 2022/8/24
 */
public class ArrayStack implements Stack {

    private static final int DEFAULT_CAPACITY = 16;

    private final int[] elements;
    private final int capacity;
    private int size;

    public ArrayStack() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayStack(int capacity) {
        if (capacity <= 0) {
            capacity = DEFAULT_CAPACITY;
        }
        this.capacity = capacity;
        elements = new int[capacity];
        size = 0;
    }

    @Override
    public void push(int val) {
        if (isFull()) {
            throw new StackOverflowError();
        }
        elements[size++] = val;
    }

    @Override
    public int pop() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }
        return elements[--size];
    }

    @Override
    public int top() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }
        return elements[size - 1];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == capacity;
    }

}
