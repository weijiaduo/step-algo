package com.wjd.structure.tree.generic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 树节点
 *
 * @author weijiaduo
 * @since 2022/12/10
 */
public class Node {

    /**
     * 节点值
     */
    public int val;

    /**
     * 子节点
     */
    public List<Node> children;

    /**
     * @param val 节点值
     */
    public Node(int val) {
        this.val = val;
        children = new ArrayList<>();
    }

    /**
     * 构建树
     *
     * @param values 层次遍历的输出结果
     * @return 树根节点
     */
    public static Node build(Integer[] values) {
        return new GenericTreeSerializer().deserialize(values);
    }

    /**
     * 构建树
     *
     * @param values 层次遍历的输出结果
     * @return 树根节点
     */
    public static Node build(List<Integer> values) {
        return build(values.toArray(new Integer[0]));
    }

    /**
     * 按层次遍历树的数据
     *
     * @param tree 树根节点
     * @return 层次数据
     */
    public static List<Integer> traverse(Node tree) {
        Integer[] values = new GenericTreeSerializer().serialize(tree);
        return values != null ? Arrays.asList(values) : null;
    }

    @Override
    public String toString() {
        return String.valueOf(val);
    }

}
