package com.wjd.algorithm.tree.binary.traverse.postorder;

import com.wjd.algorithm.tree.Traverse;
import com.wjd.structure.tree.binary.TreeNode;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IteratePostorderTraverseTest {

    @Test
    void traverse() {
        Traverse<TreeNode> traverse = new IteratePostorderTraverse();

        Integer[] values = {1, null, 2, 3};
        Integer[] expectArr = {3, 2, 1};
        String expect = Arrays.toString(expectArr);

        TreeNode root = TreeNode.build(values);
        List<TreeNode> list = traverse.traverse(root);
        String actual = String.valueOf(list);

        assertEquals(expect, actual);
    }

}