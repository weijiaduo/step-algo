package com.wjd.algorithm.tree.thread.traverse;

import com.wjd.algorithm.tree.thread.build.PostorderThreadBuilder;
import com.wjd.structure.tree.binary.TreeNode;
import com.wjd.structure.tree.thread.ThreadNode;

/**
 * 后序线索二叉树树
 *
 * @author weijiaduo
 * @since 2022/12/4
 */
public class PostorderThreadTraverse implements ThreadTraverse {

    /**
     * 根节点
     */
    private final ThreadNode root;
    /**
     * 遍历到的当前节点
     */
    private ThreadNode cur;

    public PostorderThreadTraverse(TreeNode root) {
        this.root = new PostorderThreadBuilder().build(root);
    }

    @Override
    public ThreadNode first() {
        if (root.left != null) {
            cur = first0(root.left);
        } else {
            cur = first0(root.right);
        }
        return cur;
    }

    @Override
    public ThreadNode next() {
        if (cur == null) {
            return null;
        }

        if (cur.rTag) {
            // 有后驱节点
            cur = cur.right;
        } else {
            // 无后驱节点，先右子树，再父节点
            ThreadNode parent = cur.parent;
            if (parent == null) {
                cur = null;
            } else if (parent.right != null && !parent.rTag && parent.right != cur) {
                cur = first0(parent.right);
            } else {
                cur = parent;
            }
        }
        return cur;
    }

    /**
     * @param root 根节点
     * @return 第一个节点
     */
    public ThreadNode first0(ThreadNode root) {
        if (root == null) {
            return null;
        }
        while (!root.lTag) {
            root = root.left;
        }
        return root;
    }

}
