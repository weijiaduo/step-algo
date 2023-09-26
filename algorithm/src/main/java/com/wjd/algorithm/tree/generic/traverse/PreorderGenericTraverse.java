package com.wjd.algorithm.tree.generic.traverse;

import com.wjd.algorithm.tree.ListVisitor;
import com.wjd.structure.tree.generic.Node;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

/**
 * 通用树-前序遍历
 *
 * @author weijiaduo
 * @since 2022/12/11
 */
public class PreorderGenericTraverse implements GenericTraverse {

    /**
     * 列表访问者
     */
    private ListVisitor<Node> visitor;

    /**
     * 遍历实现类型：
     * 1：递归
     * 2：迭代
     * 3：标记
     */
    private int type = 1;

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public List<Node> traverse(Node root) {
        visitor = new ListVisitor<>();
        if (type == 3) {
            mark(root);
        } else if (type == 2) {
            iterate(root);
        } else {
            recursive(root);
        }
        List<Node> list = visitor.getList();
        visitor = null;
        return list;
    }

    /**
     * 递归遍历
     *
     * @param root 根节点
     */
    private void recursive(Node root) {
        if (root == null) {
            return;
        }

        visitor.visit(root);
        if (root.children != null) {
            for (Node child : root.children) {
                recursive(child);
            }
        }
    }

    /**
     * 迭代实现
     *
     * @param root 树根节点
     */
    private void iterate(Node root) {
        if (root == null) {
            return;
        }

        Deque<Node> stack = new ArrayDeque<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            visitor.visit(node);
            List<Node> children = node.children;
            if (children != null && !children.isEmpty()) {
                for (int i = children.size() - 1; i >= 0; i--) {
                    stack.push(children.get(i));
                }
            }
        }
    }

    /**
     * 标记实现
     *
     * @param root 树根节点
     */
    private void mark(Node root) {
        Deque<Node> stack = new ArrayDeque<>();
        Deque<Boolean> marks = new ArrayDeque<>();
        stack.push(root);
        marks.push(false);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            Boolean mark = marks.pop();
            if (mark) {
                visitor.visit(node);
            } else {
                // 倒序添加
                List<Node> children = node.children;
                if (children != null) {
                    for (int i = children.size() - 1; i >= 0; i--) {
                        stack.push(children.get(i));
                        marks.push(false);
                    }
                }
                stack.push(node);
                marks.push(true);
            }
        }
    }

}
