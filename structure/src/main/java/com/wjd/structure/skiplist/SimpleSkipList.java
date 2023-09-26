package com.wjd.structure.skiplist;

import java.util.Arrays;
import java.util.Random;

/**
 * 整数跳表（数组实现）
 *
 * @author weijiaduo
 * @since 2022/7/27
 */
public class SimpleSkipList {

    static class Node {
        int value;
        Node[] forwards;

        Node(int value, int levels) {
            this.value = value;
            forwards = new Node[levels];
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

    public SimpleSkipList() {
        this(DEFAULT_MAX_LEVEL);
    }

    public SimpleSkipList(int maxLevels) {
        this.maxLevels = maxLevels;
        head = new Node(-1, maxLevels);
    }

    /**
     * 搜索指定值是否存在
     *
     * @param value 指定值
     * @return true存在/false不存在
     */
    public boolean search(int value) {
        Node p = head, q = null;
        // 从上往下一直找，直到最底层的原始链表为止
        for (int i = levels - 1; i >= 0; i--) {
            // 在当前层遍历，直到找到 value 所在区间
            Node right = p.forwards[i];
            while (right != null && value > right.value) {
                p = right;
                right = p.forwards[i];
            }
            // 记录上一层的转折点
            q = p;
        }
        if (q != null) {
            Node target = q.forwards[0];
            return target != null && value == target.value;
        }
        return false;
    }

    /**
     * 添加新值
     *
     * @param value 新值
     */
    public void add(int value) {
        Node[] processors = findProcessors(value);
        int lv = randomLevel();
        levels = Math.max(levels, lv);
        Node newNode = new Node(value, levels);
        for (int i = 0; i < lv; i++) {
            Node processor = processors[i];
            newNode.forwards[i] = processor.forwards[i];
            processor.forwards[i] = newNode;
        }
    }

    /**
     * 删除指定值
     *
     * @param value 指定值
     * @return true删除成功/false删除失败
     */
    public boolean erase(int value) {
        Node[] processors = findProcessors(value);
        Node target = processors[0].forwards[0];
        if (target == null || value != target.value) {
            return false;
        }

        // 从下往上，逐层删除指定节点
        for (int i = 0; i < levels; i++) {
            Node processor = processors[i];
            Node delNode = processor.forwards[i];
            if (delNode == null || delNode != target) {
                break;
            }
            processor.forwards[i] = delNode.forwards[i];
        }

        // 降低层级，最顶层只有头节点时
        for (; levels > 1; levels--) {
            if (head.forwards[levels - 1] != null) {
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
    private Node[] findProcessors(int value) {
        Node[] path = new Node[maxLevels];
        Arrays.fill(path, head);
        Node p = head;
        for (int i = levels - 1; i >= 0; i--) {
            // 在当前层遍历，直到找到 value 所在区间
            Node right = p.forwards[i];
            while (right != null && value > right.value) {
                p = right;
                right = p.forwards[i];
            }
            // 记录上一层的转折点
            path[i] = p;
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
            Node p = head.forwards[i];
            while (p != null) {
                sb.append(p.value).append(",");
                p = p.forwards[i];
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append("]\n");
        }
        return sb.toString();
    }
}
