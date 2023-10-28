package com.wjd.structure.tree.binary;

import java.util.Arrays;
import java.util.List;

/**
 * 二叉树节点
 */
public class TreeNode {

    /**
     * 节点值
     */
    public int val;
    /**
     * 左子节点
     */
    public TreeNode left;
    /**
     * 右子节点
     */
    public TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }

    /**
     * 根据层序遍历序列（空节点为null）生成树
     *
     * @param values 输入值
     * @return 二叉树
     */
    public static TreeNode build(Integer[] values) {
        return new BinaryTreeSerializer().deserialize(values);
    }

    /**
     * 集合形式的输入
     *
     * @param list 集合
     * @return 二叉树
     */
    public static TreeNode build(List<Integer> list) {
        return build(list.toArray(new Integer[0]));
    }

    /**
     * 字符串形式的输入
     *
     * @param strings 字符串输入
     * @return 二叉树
     */
    public static TreeNode build(String[] strings) {
        if (strings == null) {
            return null;
        }
        int n = strings.length;
        Integer[] values = new Integer[n];
        for (int i = 0; i < n; i++) {
            String s = strings[i];
            if ("#".equals(s) || "null".equals(s)) {
                values[i] = null;
            } else {
                values[i] = Integer.parseInt(s);
            }
        }
        return build(values);
    }

    /**
     * 构建形态的层序遍历（没有节点的位置是 null）
     *
     * @param tree 根节点
     * @return 遍历列表
     */
    public static List<Integer> traverse(TreeNode tree) {
        Integer[] values = new BinaryTreeSerializer().serialize(tree);
        return values != null ? Arrays.asList(values) : null;
    }

    /**
     * 二叉树的序列化字符串输出
     *
     * @param tree 根节点
     * @return 序列化字符串
     */
    public static String toString(TreeNode tree) {
        return toString(tree, null);
    }

    /**
     * 二叉树的序列化字符串输出
     *
     * @param tree    根节点
     * @param nullStr null 的字符串表示
     * @return 序列化字符串
     */
    public static String toString(TreeNode tree, String nullStr) {
        Integer[] values = new BinaryTreeSerializer().serialize(tree);
        if (values == null) {
            return "";
        }

        int n = values.length;
        String[] strings = new String[n];
        for (int i = 0; i < n; i++) {
            if (values[i] == null) {
                strings[i] = nullStr;
            } else {
                strings[i] = String.valueOf(values[i]);
            }
        }
        return Arrays.toString(strings);
    }

    @Override
    public String toString() {
        return String.valueOf(val);
    }

}
