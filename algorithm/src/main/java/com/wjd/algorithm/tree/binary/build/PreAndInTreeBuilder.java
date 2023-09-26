package com.wjd.algorithm.tree.binary.build;

import com.wjd.structure.tree.binary.TreeNode;

/**
 * 根据前序 + 中序构造树
 *
 * @author weijiaduo
 * @since 2022/11/26
 */
public class PreAndInTreeBuilder implements BinaryTreeBuilder<int[][]> {

    @Override
    public TreeNode build(int[][] values) {
        if (values == null || values.length != 2
                || values[0] == null || values[1] == null
                || values[0].length != values[1].length) {
            return null;
        }

        int[] preorder = values[0];
        int[] inorder = values[1];
        return build(preorder, 0, inorder, 0, preorder.length);
    }

    private TreeNode build(int[] preorder, int s1, int[] inorder, int s2, int len) {
        if (len <= 0) {
            return null;
        }

        // 前序遍历的第一个就是根节点
        int rootVal = preorder[s1];
        // 在中序遍历中找到根节点的位置
        int offset = 0;
        while (offset < len && inorder[s2 + offset] != rootVal) {
            offset++;
        }
        // 在中序中找不到，则属于非法数据
        if (offset == len) {
            return null;
        }

        TreeNode root = new TreeNode(rootVal);
        root.left = build(preorder, s1 + 1, inorder, s2, offset);
        root.right = build(preorder, s1 + 1 + offset, inorder, s2 + 1 + offset, len - offset - 1);
        return root;
    }

}
