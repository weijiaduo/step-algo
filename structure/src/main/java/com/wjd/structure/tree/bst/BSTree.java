package com.wjd.structure.tree.bst;

import com.wjd.structure.tree.binary.TreeNode;

/**
 * 二叉搜索树
 *
 * @author weijiaduo
 * @since 2022/12/14
 */
public interface BSTree {
    /**
     * 查找指定节点
     *
     * @param val 指定值
     * @return val对应的节点/null
     */
    TreeNode query(int val);

    /**
     * 删除指定值的节点
     *
     * @param val 指定值
     */
    void remove(int val);

    /**
     * 插入节点
     *
     * @param val 新节点值
     */
    void insert(int val);

}
