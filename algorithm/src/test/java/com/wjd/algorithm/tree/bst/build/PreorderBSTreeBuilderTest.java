package com.wjd.algorithm.tree.bst.build;

import com.wjd.structure.tree.binary.TreeNode;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PreorderBSTreeBuilderTest {

    @Test
    void testBuild() {
        Integer[] values = {8, 5, 1, 7, 10, 12};
        List<Integer> expect = Arrays.asList(8, 5, 10, 1, 7, null, 12);

        TreeNode root = new PreorderBSTreeBuilder().build(values);
        List<Integer> actual = TreeNode.traverse(root);

        assertEquals(expect.toString(), actual.toString());
    }
}