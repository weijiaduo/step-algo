package com.wjd.algorithm.tree.generic.traverse;

import com.wjd.structure.tree.generic.Node;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PreorderGenericTraverseTest {

    @Test
    void testRecursiveTraverse() {
        Integer[] values = {1, null, 3, 2, 4, null, 5, 6};
        Integer[] expectArr = {1, 3, 5, 6, 2, 4};
        String expect = Arrays.toString(expectArr);

        Node tree = Node.build(values);
        List<Node> list = new PreorderGenericTraverse().traverse(tree);
        String actual = String.valueOf(list);

        assertEquals(expect, actual);
    }

    @Test
    void testIterate() {
        Integer[] values = {1, null, 3, 2, 4, null, 5, 6};
        Integer[] expectArr = {1, 3, 5, 6, 2, 4};
        String expect = Arrays.toString(expectArr);

        PreorderGenericTraverse traverse = new PreorderGenericTraverse();
        traverse.setType(2);
        Node tree = Node.build(values);
        List<Node> list = traverse.traverse(tree);
        String actual = String.valueOf(list);

        assertEquals(expect, actual);
    }

    @Test
    void testMark() {
        Integer[] values = {1, null, 3, 2, 4, null, 5, 6};
        Integer[] expectArr = {1, 3, 5, 6, 2, 4};
        String expect = Arrays.toString(expectArr);

        PreorderGenericTraverse traverse = new PreorderGenericTraverse();
        traverse.setType(3);
        Node tree = Node.build(values);
        List<Node> list = traverse.traverse(tree);
        String actual = String.valueOf(list);

        assertEquals(expect, actual);
    }

}