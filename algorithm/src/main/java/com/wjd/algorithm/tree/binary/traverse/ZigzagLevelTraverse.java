package com.wjd.algorithm.tree.binary.traverse;

import com.wjd.algorithm.tree.ListVisitor;
import com.wjd.algorithm.tree.Traverse;
import com.wjd.structure.tree.binary.TreeNode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Z 锯齿形层序遍历
 * <p>
 * 即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行。
 *
 * @author weijiaduo
 * @since 2023/2/26
 */
public class ZigzagLevelTraverse implements Traverse<TreeNode> {

    /**
     * 列表访问者
     */
    private ListVisitor<TreeNode> visitor;

    @Override
    public List<TreeNode> traverse(TreeNode root) {
        visitor = new ListVisitor<>();
        zigzag(root);
        List<TreeNode> list = visitor.getList();
        visitor = null;
        return list;
    }

    /**
     * 思路：
     * <p>
     * 1. 父子节点分别放2个栈中，交替遍历父子栈
     * <p>
     * 2. 简化，把2个栈换成一个双向队列
     */
    private void zigzag(TreeNode root) {
        Deque<TreeNode> deque = new LinkedList<>();
        deque.offer(root);
        boolean leftStart = true;
        while (!deque.isEmpty()) {
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = leftStart ? deque.pollFirst() : deque.pollLast();
                if (node == null) {
                    continue;
                }

                visitor.visit(node);
                if (leftStart) {
                    if (node.left != null) {
                        deque.offerLast(node.left);
                    }
                    if (node.right != null) {
                        deque.offerLast(node.right);
                    }
                } else {
                    if (node.right != null) {
                        deque.offerFirst(node.right);
                    }
                    if (node.left != null) {
                        deque.offerFirst(node.left);
                    }
                }
            }
            leftStart = !leftStart;
        }
    }

}
