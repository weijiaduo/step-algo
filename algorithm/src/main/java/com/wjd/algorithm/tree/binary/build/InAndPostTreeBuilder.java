package com.wjd.algorithm.tree.binary.build;

import com.wjd.structure.tree.binary.TreeNode;

/**
 * 根据中序 + 后序构建树
 *
 * @author weijiaduo
 * @since 2022/11/26
 */
public class InAndPostTreeBuilder implements BinaryTreeBuilder<int[][]> {

    @Override
    public TreeNode build(int[][] values) {
        if (values == null || values.length != 2
                || values[0] == null || values[1] == null
                || values[0].length != values[1].length) {
            return null;
        }

        int[] inorder = values[0];
        int[] postorder = values[1];
        return build(inorder, 0, postorder, 0, inorder.length);
    }

    private TreeNode build(int[] inorder, int s1, int[] postorder, int s2, int len) {
        if (len <= 0) {
            return null;
        }

        // 后序遍历的最后一个就是根节点
        int rootVal = postorder[s2 + len - 1];
        // 在中序遍历中找到根节点的位置
        int offset = 0;
        while (offset < len && inorder[s1 + offset] != rootVal) {
            offset++;
        }
        // 在中序中找不到，则属于非法数据
        if (offset == len) {
            return null;
        }

        TreeNode root = new TreeNode(rootVal);
        root.left = build(inorder, s1, postorder, s2, offset);
        root.right = build(inorder, s1 + 1 + offset, postorder, s2 + offset, len - offset - 1);
        return root;
    }

}
