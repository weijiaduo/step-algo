package com.wjd.structure.tree.ufs;

/**
 * 并查集-路径压缩 + 按秩合并
 *
 * @author weijiaduo
 * @since 2022/9/27
 */
public class ArrayUnionFind {

    /**
     * 父节点存储
     */
    private int[] parent;
    /**
     * 节点深度
     */
    private int[] rank;

    public ArrayUnionFind(int n) {
        parent = new int[n];
        rank = new int[n];
        init(n);
    }

    /**
     * 初始化并查集
     *
     * @param n 元素个数
     */
    private void init(int n) {
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    /**
     * 查找元素
     *
     * @param x 指定元素
     * @return 元素根节点
     */
    public int find(int x) {
        if (parent[x] == x) {
            return x;
        } else {
            // 路径压缩，会导致 rank 有误差
            parent[x] = find(parent[x]);
            return parent[x];
        }
    }

    /**
     * 合并元素子集合
     *
     * @param x1 元素
     * @param x2 元素
     */
    public void union(int x1, int x2) {
        int root1 = find(x1);
        int root2 = find(x2);
        if (root1 == root2) {
            return;
        }

        // 按秩合并
        if (rank[root1] > rank[root2]) {
            parent[root2] = root1;
        } else if (rank[root1] < rank[root2]) {
            parent[root1] = root2;
        } else {
            // 深度一样的时候，合并起来会加深一层
            parent[root2] = root1;
            rank[root1]++;
        }
    }

}
