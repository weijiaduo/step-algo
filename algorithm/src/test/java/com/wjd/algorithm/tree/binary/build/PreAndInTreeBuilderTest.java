package com.wjd.algorithm.tree.binary.build;

import com.wjd.structure.tree.binary.TreeNode;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PreAndInTreeBuilderTest {

    @Test
    void build() {
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        List<Integer> expect = Arrays.asList(3, 9, 20, null, null, 15, 7);
        TreeNode root = new PreAndInTreeBuilder().build(new int[][]{preorder, inorder});
        List<Integer> actual = TreeNode.traverse(root);
        assertEquals(expect, actual);
    }

}