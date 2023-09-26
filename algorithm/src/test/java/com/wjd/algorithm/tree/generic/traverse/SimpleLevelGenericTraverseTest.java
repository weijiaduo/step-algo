package com.wjd.algorithm.tree.generic.traverse;

import com.wjd.structure.tree.generic.Node;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SimpleLevelGenericTraverseTest {

    @Test
    void testTraverse() {
        List<Integer> expect = Arrays.asList(1, 3, 2, 4, 5, 6);
        Node tree = new Node(1);
        Node node3 = new Node(3);
        Node node2 = new Node(2);
        tree.children = Arrays.asList(node3, node2, new Node(4));
        node2.children = Arrays.asList(new Node(5), new Node(6));
        List<Node> actual = new SimpleLevelGenericTraverse().traverse(tree);

        assertEquals(expect.toString(), actual.toString());
    }
}