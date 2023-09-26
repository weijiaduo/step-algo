package com.wjd.algorithm.tree.binary.traverse;

import com.wjd.structure.tree.binary.TreeNode;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PreorderTraverseTest {

    @Test
    void traverse() {
        PreorderTraverse traverse = new PreorderTraverse();

        Integer[] values = {1, null, 2, 3};
        Integer[] expectArr = {1, 2, 3};
        String expect = Arrays.toString(expectArr);

        TreeNode root = TreeNode.build(values);
        List<TreeNode> list = traverse.traverse(root);
        String actual = String.valueOf(list);

        assertEquals(expect, actual);
    }

    @Test
    void setType() {
        PreorderTraverse traverse = new PreorderTraverse();

        Integer[] values = {1, null, 2, 3};
        Integer[] expectArr = {1, 2, 3};
        String expect = Arrays.toString(expectArr);
        TreeNode root = TreeNode.build(values);

        // 递归实现
        traverse.setType(1);
        List<TreeNode> list = traverse.traverse(root);
        String actual = String.valueOf(list);
        assertEquals(expect, actual);

        // 迭代实现
        traverse.setType(2);
        list = traverse.traverse(root);
        actual = String.valueOf(list);
        assertEquals(expect, actual);

        // 标记实现
        traverse.setType(3);
        list = traverse.traverse(root);
        actual = String.valueOf(list);
        assertEquals(expect, actual);
    }
}