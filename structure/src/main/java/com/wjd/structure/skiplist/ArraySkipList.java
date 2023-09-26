package com.wjd.structure.skiplist;

import java.util.*;

/**
 * 跳表（数组实现）
 *
 * @author weijiaduo
 * @since 2022/7/27
 */
public class ArraySkipList<T extends Comparable<T>> implements SkipList<T> {

    class Node {
        T value;
        List<Node> forwards;

        Node(T value, int levels) {
            this.value = value;
            forwards = new ArrayList<>(levels);
            for (int i = 0; i < levels; i++) {
                forwards.add(null);
            }
        }
    }

    /**
     * 默认最大层级
     */
    static final int DEFAULT_MAX_LEVEL = 32;
    /**
     * 随机因子
     */
    static final double FACTOR = 0.25;

    /**
     * 哨兵头节点
     */
    Node head;
    /**
     * 当前层级
     */
    int levels = 0;
    /**
     * 最大层级
     */
    int maxLevels;
    /**
     * 随机数
     */
    Random random = new Random();

    public ArraySkipList() {
        this(DEFAULT_MAX_LEVEL);
    }

    public ArraySkipList(int maxLevels) {
        this.maxLevels = maxLevels;
        head = new Node(null, maxLevels);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean search(T value) {
        Node p = head, q = null;
        // 从上往下一直找，直到最底层的原始链表为止
        for (int i = levels - 1; i >= 0; i--) {
            // 在当前层遍历，直到找到 value 所在区间
            Node right = p.forwards.get(i);
            while (right != null && value.compareTo(right.value) > 0) {
                p = right;
                right = p.forwards.get(i);
            }
            // 记录上一层的转折点
            q = p;
        }
        if (q != null) {
            Node target = q.forwards.get(0);
            return target != null && value.equals(target.value);
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(T value) {
        List<Node> processors = findProcessors(value);
        int lv = randomLevel();
        levels = Math.max(levels, lv);
        Node newNode = new Node(value, levels);
        for (int i = 0; i < lv; i++) {
            Node processor = processors.get(i);
            newNode.forwards.set(i, processor.forwards.get(i));
            processor.forwards.set(i, newNode);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean erase(T value) {
        List<Node> processors = findProcessors(value);
        Node target = processors.get(0).forwards.get(0);
        if (target == null || !value.equals(target.value)) {
            return false;
        }

        // 从下往上，逐层删除指定节点
        for (int i = 0; i < levels; i++) {
            Node processor = processors.get(i);
            Node delNode = processor.forwards.get(i);
            if (delNode == null || delNode != target) {
                break;
            }
            processor.forwards.set(i, delNode.forwards.get(i));
        }

        // 降低层级，最顶层只有头节点时
        for (; levels > 1; levels--) {
            if (head.forwards.get(levels - 1) != null) {
                break;
            }
        }

        return true;
    }

    /**
     * 查找指定值的前置路径
     *
     * @param value 指定值
     * @return 前置路径
     */
    private List<Node> findProcessors(T value) {
        List<Node> path = new ArrayList<>(maxLevels);
        for (int i = 0; i < maxLevels; i++) {
            path.add(head);
        }
        Node p = head;
        for (int i = levels - 1; i >= 0; i--) {
            // 在当前层遍历，直到找到 value 所在区间
            Node right = p.forwards.get(i);
            while (right != null && value.compareTo(right.value) > 0) {
                p = right;
                right = p.forwards.get(i);
            }
            // 记录上一层的转折点
            path.set(i, p);
        }
        return path;
    }

    /**
     * 随机层级
     * <p>
     * 这个随机有点影响时间
     *
     * @return 随即层级
     */
    private int randomLevel() {
        int lv = 1;
        while (lv < maxLevels && random.nextDouble() < FACTOR) {
            lv++;
        }
        return lv;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = levels - 1; i >= 0; i--) {
            sb.append("[");
            Node p = head.forwards.get(i);
            while (p != null) {
                sb.append(p.value).append(",");
                p = p.forwards.get(i);
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append("]\n");
        }
        return sb.toString();
    }
}
