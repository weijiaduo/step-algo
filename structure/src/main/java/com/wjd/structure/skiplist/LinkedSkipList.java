package com.wjd.structure.skiplist;

import java.util.*;

/**
 * 跳表（链表）
 *
 * @author weijiaduo
 * @since 2022/7/28
 */
public class LinkedSkipList<T extends Comparable<T>> implements SkipList<T> {

    class Node {
        T value;
        Node right;
        Node down;

        public Node(T value, Node right, Node down) {
            this.value = value;
            this.right = right;
            this.down = down;
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
     * 最大层级
     */
    int maxLevels;
    /**
     * 随机数
     */
    Random random = new Random();

    public LinkedSkipList() {
        this(DEFAULT_MAX_LEVEL);
    }

    public LinkedSkipList(int maxLevels) {
        this.maxLevels = maxLevels;
        this.head = new Node(null, null, null);
    }

    @Override
    public boolean search(T value) {
        Node p = head, q = null;
        // 从上往下一直找，直到最底层的原始链表为止
        while (p != null) {
            // 在当前层遍历，直到找到 value 所在区间
            while (p.right != null && value.compareTo(p.right.value) > 0) {
                p = p.right;
            }
            // 记录上一层的转折点
            q = p;
            // 搜索下一层
            p = p.down;
        }
        return q != null && q.right != null && q.right.value.equals(value);
    }

    @Override
    public void add(T value) {
        List<Node> processors = findProcessors(value);
        int levels = processors.size();

        Node newNode = null;
        boolean insertUp = true;
        for (int i = 0; i < levels && insertUp; i++) {
            Node processor = processors.get(i);
            // 新节点指向下一层的同值节点
            newNode = new Node(value, processor.right, newNode);
            // 在当前层插入新节点
            processor.right = newNode;

            // 是否要继续往上一层插入节点
            insertUp = random.nextDouble() < FACTOR;
        }

        // 更新层级
        if (insertUp) {
            levelUp();
        }
    }

    @Override
    public boolean erase(T value) {
        List<Node> processors = findProcessors(value);

        // 验证删除节点是否存在，没有找到则直接返回
        Node target = processors.get(0).right;
        if (target == null || !value.equals(target.value)) {
            return false;
        }

        // 从下往上，逐层删除指定节点
        for (Node processor : processors) {
            // 指定 value 值的节点在每一层都删完了，就跳出循环
            Node delNode = processor.right;
            if (delNode == null || !delNode.value.equals(value)) {
                break;
            }
            processor.right = delNode.right;
        }

        // 更新层级
        levelDown();

        return true;
    }

    /**
     * 提升层级
     */
    private void levelUp() {
        // 最上层只有一个头节点
        head = new Node(null, null, head);
    }

    /**
     * 降低层级
     */
    private void levelDown() {
        while (head.down != null) {
            // 连续 2 层为空时，才降低层级，因为最上层只有 1 个头节点
            if (head.right != null || head.down.right != null) {
                break;
            }
            Node p = head;
            head = head.down;
            p.down = null;
        }
    }

    /**
     * 查找指定值的前置路径
     *
     * @param value 指定值
     * @return 前置路径
     */
    private List<Node> findProcessors(T value) {
        Deque<Node> stack = new ArrayDeque<>();
        Node p = head;
        // 从上往下一直找，直到最底层的原始链表为止
        while (p != null) {
            // 在当前层遍历，直到找到 value 所在区间
            while (p.right != null && value.compareTo(p.right.value) > 0) {
                p = p.right;
            }
            // 记录上一层的转折点
            stack.push(p);
            // 搜索下一层
            p = p.down;
        }

        // 路径是倒序摆放的，最接近值 value 的在索引 0 的位置
        List<Node> path = new ArrayList<>(stack.size());
        while (!stack.isEmpty()) {
            path.add(stack.pop());
        }
        return path;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node p = head;
        while (p != null) {
            List<Object> values = new ArrayList<>();
            Node q = p.right;
            while (q != null) {
                values.add(q.value);
                q = q.right;
            }
            sb.append(values).append('\n');
            p = p.down;
        }
        return sb.toString();
    }
}
