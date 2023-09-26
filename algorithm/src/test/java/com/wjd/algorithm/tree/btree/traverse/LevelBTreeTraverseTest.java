package com.wjd.algorithm.tree.btree.traverse;

import com.wjd.structure.tree.btree.BTNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class LevelBTreeTraverseTest {

    @Test
    void testTraverse() {
        List<List<Integer>> expect = Arrays.asList(
                Arrays.asList(21, 22),
                Arrays.asList(11, 12),
                List.of(),
                Arrays.asList(31, 32, 33),
                Arrays.asList(1, 2)
        );

        int m = 4;
        BTNode<Integer, Integer> root = new BTNode<>(m);
        root.add(21, 21);
        root.add(22, 22);

        BTNode<Integer, Integer> child0 = new BTNode<>(m);
        child0.add(11, 11);
        child0.add(12, 12);
        BTNode<Integer, Integer> child0child0 = new BTNode<>(m);
        child0child0.add(1, 1);
        child0child0.add(2, 2);
        child0.setChild(0, child0child0);

        BTNode<Integer, Integer> child1 = new BTNode<>(m);
        child1.add(31, 31);
        child1.add(32, 32);
        child1.add(33, 33);

        root.setChild(0, child0);
        root.setChild(2, child1);

        List<List<Integer>> actual = new LevelBTreeTraverse<Integer, Integer>().traverse(root);
        System.out.println(actual);
        Assertions.assertEquals(expect.toString(), actual.toString());
    }
}