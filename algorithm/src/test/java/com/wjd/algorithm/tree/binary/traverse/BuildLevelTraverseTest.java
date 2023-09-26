package com.wjd.algorithm.tree.binary.traverse;

import com.wjd.structure.tree.binary.TreeNode;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BuildLevelTraverseTest {

    @Test
    void traverse() {
        BuildLevelTraverse traverse = new BuildLevelTraverse();

        Integer[] values = {1, 3, 2, 5, 3, null, 9};
        String expect = Arrays.toString(values);

        TreeNode root = TreeNode.build(values);
        List<TreeNode> list = traverse.traverse(root);
        String actual = String.valueOf(list);

        assertEquals(expect, actual);
    }
}