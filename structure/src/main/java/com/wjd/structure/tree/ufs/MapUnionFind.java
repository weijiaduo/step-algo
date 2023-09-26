package com.wjd.structure.tree.ufs;

import java.util.HashMap;
import java.util.Map;

/**
 * 并查集-路径压缩 + 按秩合并
 *
 * @author weijiaduo
 * @since 2022/9/27
 */
public class MapUnionFind {

    /**
     * 父节点存储
     */
    private Map<Integer, Integer> parent;
    /**
     * 节点深度
     */
    private Map<Integer, Integer> rank;

    public MapUnionFind(int[] nums) {
        parent = new HashMap<>();
        rank = new HashMap<>();
        init(nums);
    }

    /**
     * 初始化并查集
     *
     * @param nums 元素数组
     */
    private void init(int[] nums) {
        for (int num : nums) {
            parent.put(num, num);
            rank.put(num, 1);
        }
    }

    /**
     * 查找元素
     *
     * @param x 指定元素
     * @return 元素根节点
     */
    public Integer find(int x) {
        Integer p = parent.get(x);
        if (p == null) {
            return null;
        }
        if (p == x) {
            return x;
        } else {
            // 路径压缩，会导致 rank 有误差
            parent.put(x, find(p));
            return parent.get(x);
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
        int rank1 = rank.get(root1);
        int rank2 = rank.get(root2);
        if (rank1 > rank2) {
            parent.put(root2, root1);
        } else if (rank1 < rank2) {
            parent.put(root1, root2);
        } else {
            // 深度一样的时候，合并起来会加深一层
            parent.put(root2, root1);
            rank.put(root1, rank1 + 1);
        }
    }

}
