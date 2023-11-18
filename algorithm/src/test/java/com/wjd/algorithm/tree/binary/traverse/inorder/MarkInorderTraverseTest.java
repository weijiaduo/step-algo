package com.wjd.algorithm.tree.binary.traverse.inorder;

import com.wjd.algorithm.tree.Traverse;
import com.wjd.structure.tree.binary.TreeNode;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MarkInorderTraverseTest {

    @Test
    void traverse() {
        Traverse<TreeNode> traverse = new MarkInorderTraverse();

        Integer[] values = {1, null, 2, 3};
        Integer[] expectArr = {1, 3, 2};
        String expect = Arrays.toString(expectArr);

        TreeNode root = TreeNode.build(values);
        List<TreeNode> list = traverse.traverse(root);
        String actual = String.valueOf(list);

        assertEquals(expect, actual);
    }

}