package com.wjd.algorithm.tree.binary.traverse.preorder;

import com.wjd.algorithm.tree.Traverse;
import com.wjd.structure.tree.binary.TreeNode;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RecursivePreorderTraverseTest {

    @Test
    void traverse() {
        Traverse<TreeNode> traverse = new RecursivePreorderTraverse();

        Integer[] values = {1, null, 2, 3};
        Integer[] expectArr = {1, 2, 3};
        String expect = Arrays.toString(expectArr);

        TreeNode root = TreeNode.build(values);
        List<TreeNode> list = traverse.traverse(root);
        String actual = String.valueOf(list);

        assertEquals(expect, actual);
    }

}