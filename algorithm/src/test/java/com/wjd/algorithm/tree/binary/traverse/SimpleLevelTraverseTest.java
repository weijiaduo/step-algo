package com.wjd.algorithm.tree.binary.traverse;

import com.wjd.structure.tree.binary.TreeNode;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SimpleLevelTraverseTest {

    @Test
    void traverse() {
        SimpleLevelTraverse traverse = new SimpleLevelTraverse();

        Integer[] values = {3,9,20,null,null,15,7};
        Integer[] expectArr = {3, 9, 20, 15, 7};
        String expect = Arrays.toString(expectArr);

        TreeNode root = TreeNode.build(values);
        List<TreeNode> list = traverse.traverse(root);
        String actual = String.valueOf(list);

        assertEquals(expect, actual);
    }
}