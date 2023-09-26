package com.wjd.structure.tree.avl;

/**
 * 平衡二叉树接口
 *
 * @author weijiaduo
 * @since 2023/2/21
 */
public interface AVLTree {

    /**
     * 获取指定值的节点
     *
     * @param val 指定值
     * @return val对应的节点/null
     */
    Integer get(int val);

    /**
     * 插入新节点
     *
     * @param val 新值
     */
    void insert(int val);

    /**
     * 删除指定值的节点
     *
     * @param val 指定值
     */
    void remove(int val);

}
