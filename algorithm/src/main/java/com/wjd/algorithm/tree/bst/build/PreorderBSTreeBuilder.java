package com.wjd.algorithm.tree.bst.build;

import com.wjd.structure.tree.binary.TreeNode;

/**
 * 根据前序遍历构建二叉搜索树
 *
 * @author weijiaduo
 * @since 2022/12/17
 */
public class PreorderBSTreeBuilder implements BSTreeBuilder<Integer[]> {

    @Override
    public TreeNode build(Integer[] values) {
        return dfs(values, 0, values.length - 1);
    }

    /**
     * 深度遍历
     *
     * @param values 前序遍历列表
     * @param start  区间[start, end]
     * @param end    区间[start, end]
     * @return 区间根节点
     */
    private TreeNode dfs(Integer[] values, int start, int end) {
        if (end < start) {
            return null;
        }

        TreeNode root = new TreeNode(values[start]);
        int middle = end + 1;
        for (int i = start + 1; i <= end; i++) {
            if (values[i] > root.val) {
                middle = i;
                break;
            }
        }

        root.left = dfs(values, start + 1, middle - 1);
        root.right = dfs(values, middle, end);
        return root;
    }

}
