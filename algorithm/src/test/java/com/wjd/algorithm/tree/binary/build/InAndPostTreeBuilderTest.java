package com.wjd.algorithm.tree.binary.build;

import com.wjd.structure.tree.binary.TreeNode;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InAndPostTreeBuilderTest {

    @Test
    void build() {
        int[] inorder = {9, 3, 15, 20, 7};
        int[] postorder = {9, 15, 7, 20, 3};
        List<Integer> expect = Arrays.asList(3, 9, 20, null, null, 15, 7);
        TreeNode root = new InAndPostTreeBuilder().build(new int[][]{inorder, postorder});
        List<Integer> actual = TreeNode.traverse(root);
        assertEquals(expect, actual);
    }

}