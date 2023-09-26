package com.wjd.structure.tree.binaryindex;

/**
 * 树状数组（二叉索引树）
 *
 * @author weijiaduo
 * @since 2022/10/1
 */
public class BinaryIndexTree {

    /**
     * 树状数组
     */
    int[] tree;
    /**
     * 数组大小
     */
    int n;

    public BinaryIndexTree(int n) {
        this.n = n;
        tree = new int[this.n];
    }

    /**
     * 单点修改
     *
     * @param index 指定下标
     * @param val   增量修改值
     */
    public void update(int index, int val) {
        while (index < n) {
            tree[index] += val;
            index = nextP(index);
        }
    }

    /**
     * 区间查询
     *
     * @param l [l, r]
     * @param r [l, r]
     * @return [l, r] 的区间和
     */
    public int query(int l, int r) {
        return query(r) - query(l - 1);
    }

    /**
     * 区间查询
     *
     * @param index (0, index]
     * @return (0, 1] 的区间和
     */
    private int query(int index) {
        int sum = 0;
        while (index > 0) {
            sum += tree[index];
            index = prevP(index);
        }
        return sum;
    }

    /**
     * 计算后一个高层节点的下标
     *
     * @param index 当前下标
     * @return 后一个高层节点的下标
     */
    int nextP(int index) {
        // 层级间隔 2^k
        int g = lowBit(index);
        // 父节点下标
        return index + g;
    }

    /**
     * 计算前一个高层节点的下标
     *
     * @param index 当前下标
     * @return 前一个高层节点的下标
     */
    int prevP(int index) {
        // 层级间隔 2^k
        int g = lowBit(index);
        // 前一个高层节点下标
        return index - g;
    }

    /**
     * index 只剩余最低位 1 的值
     *
     * @param index 值
     * @return 只剩余最低位 1 的值
     */
    private int lowBit(int index) {
        return index & (-index);
    }

}
