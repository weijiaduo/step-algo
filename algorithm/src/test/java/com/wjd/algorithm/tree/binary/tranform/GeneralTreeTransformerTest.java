package com.wjd.algorithm.tree.binary.tranform;

import com.wjd.structure.tree.binary.TreeNode;
import com.wjd.structure.tree.generic.Node;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GeneralTreeTransformerTest {

    @Test
    void testTransTo() {
        Integer[] values = {1, 3, null, null, 2, 5, 4, null, 6};
        List<Integer> expect = Arrays.asList(1, null, 3, 2, 4, null, null, 5, 6);

        TreeNode btree = TreeNode.build(values);
        List<Node> forest = new GeneralTreeTransformer().transTo(btree);
        List<Integer> actual = Node.traverse(forest.get(0));

        assertEquals(expect.toString(), actual.toString());
    }
}