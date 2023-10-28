package com.wjd.structure.tree.binary;

/**
 * 二叉树接口
 *
 * @author weijiaduo
 * @since 2023/10/29
 */
public interface BinaryTree {

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
