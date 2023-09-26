package com.wjd.algorithm.tree.thread.traverse;

import com.wjd.algorithm.tree.binary.traverse.PreorderTraverse;
import com.wjd.structure.tree.binary.TreeNode;
import com.wjd.structure.tree.thread.ThreadNode;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PreorderThreadTraverseTest {

    @Test
    public void testThreadTree() {
        Integer[] values = {1, null, 2, 3};
        TreeNode root = TreeNode.build(values);
        List<TreeNode> list = new PreorderTraverse().traverse(root);
        String expect = String.valueOf(list);

        List<ThreadNode> list2 = new ArrayList<>();
        ThreadTraverse traverse = new PreorderThreadTraverse(root);
        for (ThreadNode p = traverse.first(); p != null; p = traverse.next()) {
            list2.add(p);
        }
        String actual = String.valueOf(list2);
        System.out.println(actual);

        assertEquals(expect, actual);
    }

}