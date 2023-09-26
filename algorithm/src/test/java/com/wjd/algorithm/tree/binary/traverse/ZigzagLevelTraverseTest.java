package com.wjd.algorithm.tree.binary.traverse;

import com.wjd.structure.tree.binary.TreeNode;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ZigzagLevelTraverseTest {

    @Test
    void traverse() {
        ZigzagLevelTraverse traverse = new ZigzagLevelTraverse();

        Integer[] values = {3, 9, 20, null, null, 15, 7, 1, 5, null, 10};
        Integer[] expectArr = {3, 20, 9, 15, 7, 10, 5, 1};
        String expect = Arrays.toString(expectArr);

        TreeNode root = TreeNode.build(values);
        List<TreeNode> list = traverse.traverse(root);
        String actual = String.valueOf(list);

        assertEquals(expect, actual);
    }
}