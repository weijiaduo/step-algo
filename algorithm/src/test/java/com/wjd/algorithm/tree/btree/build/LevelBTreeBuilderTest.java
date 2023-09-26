package com.wjd.algorithm.tree.btree.build;

import com.wjd.algorithm.tree.btree.traverse.LevelBTreeTraverse;
import com.wjd.structure.tree.btree.BTNode;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LevelBTreeBuilderTest {

    @Test
    void testBuild() {
        List<List<Integer>> expect = Arrays.asList(
                Arrays.asList(21, 22),
                Arrays.asList(11, 12),
                List.of(),
                Arrays.asList(31, 32, 33),
                Arrays.asList(1, 2)
        );

        BTNode<Integer, Integer> root = new LevelBTreeBuilder().build(expect);
        List<List<Integer>> actual = new LevelBTreeTraverse<Integer, Integer>().traverse(root);
        System.out.println(actual);

        assertEquals(expect.toString(), actual.toString());
    }

}