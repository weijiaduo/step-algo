package com.wjd.algorithm.tree.generic.traverse;

import com.wjd.structure.tree.generic.Node;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PostorderGenericTraverseTest {

    @Test
    void testRecursiveTraverse() {
        Integer[] values = {1, null, 3, 2, 4, null, 5, 6};
        Integer[] expectArr = {5, 6, 3, 2, 4, 1};
        String expect = Arrays.toString(expectArr);

        Node tree = Node.build(values);
        List<Node> list = new PostorderGenericTraverse().traverse(tree);
        String actual = String.valueOf(list);

        assertEquals(expect, actual);
    }

    @Test
    void testIterateTraverse() {
        Integer[] values = {1, null, 3, 2, 4, null, 5, 6};
        Integer[] expectArr = {5, 6, 3, 2, 4, 1};
        String expect = Arrays.toString(expectArr);

        PostorderGenericTraverse traverse = new PostorderGenericTraverse();
        traverse.setType(2);
        Node tree = Node.build(values);
        List<Node> list = traverse.traverse(tree);
        String actual = String.valueOf(list);

        assertEquals(expect, actual);
    }

    @Test
    void testMarkTraverse() {
        Integer[] values = {1, null, 3, 2, 4, null, 5, 6};
        Integer[] expectArr = {5, 6, 3, 2, 4, 1};
        String expect = Arrays.toString(expectArr);

        PostorderGenericTraverse traverse = new PostorderGenericTraverse();
        traverse.setType(3);
        Node tree = Node.build(values);
        List<Node> list = traverse.traverse(tree);
        String actual = String.valueOf(list);

        assertEquals(expect, actual);
    }

}