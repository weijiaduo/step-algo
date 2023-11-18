package com.wjd.structure.tree.thread;

import com.wjd.structure.tree.binary.TreeNode;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InorderThreadTreeTest {

    @Test
    void iterator() {
        Integer[] values = {1, null, 2, 3};
        TreeNode root = TreeNode.build(values);
        ThreadTree threadTree = new InorderThreadTree(root);
        List<Integer> expect = Arrays.asList(1, 3, 2);

        List<Integer> actual = threadTree.iterator();
        assertEquals(expect, actual);
    }

}