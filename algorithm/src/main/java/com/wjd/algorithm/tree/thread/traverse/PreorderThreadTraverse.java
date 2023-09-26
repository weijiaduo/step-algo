package com.wjd.algorithm.tree.thread.traverse;

import com.wjd.algorithm.tree.thread.build.PreorderThreadBuilder;
import com.wjd.structure.tree.binary.TreeNode;
import com.wjd.structure.tree.thread.ThreadNode;

/**
 * 前序线索二叉树遍历
 *
 * @author weijiaduo
 * @since 2022/12/4
 */
public class PreorderThreadTraverse implements ThreadTraverse {

    /**
     * 根节点
     */
    private final ThreadNode root;
    /**
     * 遍历到的当前节点
     */
    private ThreadNode cur;

    public PreorderThreadTraverse(TreeNode root) {
        this.root = new PreorderThreadBuilder().build(root);
    }

    @Override
    public ThreadNode first() {
        cur = root;
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
            // 无后驱节点，子节点先左后右
            if (cur.left != null && !cur.lTag) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        return cur;
    }

}
