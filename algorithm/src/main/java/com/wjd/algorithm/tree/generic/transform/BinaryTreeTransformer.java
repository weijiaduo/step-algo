package com.wjd.algorithm.tree.generic.transform;

import com.wjd.structure.tree.binary.TreeNode;
import com.wjd.structure.tree.generic.Node;

import java.util.List;

/**
 * 通用树转换成二叉树
 *
 * @author weijiaduo
 * @since 2022/12/11
 */
public class BinaryTreeTransformer {

    /**
     * 转换成二叉树
     *
     * @param root 通用树根节点
     * @return 二叉树根节点
     */
    public TreeNode transTo(Node root) {
        if (root == null) {
            return null;
        }

        TreeNode btNode = new TreeNode(root.val);
        List<Node> children = root.children;
        if (children != null) {
            TreeNode last = null;
            for (int i = children.size() - 1; i >= 0; i--) {
                TreeNode node = transTo(children.get(i));
                // 右节点指向相邻兄弟节点
                node.right = last;
                last = node;
            }
            // 左节点指向第一个儿子节点
            btNode.left = last;
        }

        return btNode;
    }

}
