package com.wjd.algorithm.tree.binary.tranform;

import com.wjd.structure.tree.binary.TreeNode;
import com.wjd.structure.tree.generic.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树转森林
 *
 * @author weijiaduo
 * @since 2022/12/12
 */
public class GeneralTreeTransformer {

    public List<Node> transTo(TreeNode root) {
        if (root == null) {
            return null;
        }

        List<Node> nodes = new ArrayList<>();
        Node node = new Node(root.val);
        nodes.add(node);

        // 右节点是兄弟节点
        List<Node> siblings = transTo(root.right);
        if (siblings != null) {
            nodes.addAll(siblings);
        }

        // 左节点是儿子节点
        node.children = transTo(root.left);
        return nodes;
    }

}
