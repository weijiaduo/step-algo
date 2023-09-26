package com.wjd.algorithm.tree.generic.transform;

import com.wjd.structure.tree.binary.TreeNode;
import com.wjd.structure.tree.generic.Node;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BinaryTreeTransformerTest {

    @Test
    void testTransTo() {
        Integer[] values = {1, null, 3, 2, 4, null, null, 5, 6};
        List<Integer> expect = Arrays.asList(1, 3, null, null, 2, 5, 4, null, 6);

        Node tree = Node.build(values);
        TreeNode btree = new BinaryTreeTransformer().transTo(tree);
        List<Integer> actual = TreeNode.traverse(btree);

        assertEquals(expect.toString(), actual.toString());
    }
}