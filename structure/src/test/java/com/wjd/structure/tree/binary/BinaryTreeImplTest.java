package com.wjd.structure.tree.binary;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class BinaryTreeImplTest {

    @Test
    void query() {
        Integer[] values = new Integer[]{3, 5, 1, 6, 2, 9, 8, null, null, 7, 4};
        BinaryTree tree = new BinaryTreeImpl(values);
        assertEquals(5, tree.query(5).val);
        assertEquals(3, tree.query(3).val);
        assertEquals(9, tree.query(9).val);

        assertNull(tree.query(10));
        assertNull(tree.query(-1));
    }

    @Test
    void remove() {
        Integer[] values = new Integer[]{3, 5, 1, 6, 2, 9, 8, null, null, 7, 4};
        BinaryTree tree = new BinaryTreeImpl(values);

        assertEquals(5, tree.query(5).val);
        tree.remove(5);
        assertNull(tree.query(5));

        assertEquals(3, tree.query(3).val);
        tree.remove(3);
        assertNull(tree.query(3));

        assertEquals(9, tree.query(9).val);
        tree.remove(9);
        assertNull(tree.query(9));
    }

    @Test
    void insert() {
        BinaryTree tree = new BinaryTreeImpl();
        tree.insert(1);
        assertEquals("[1]", tree.toString());
        tree.insert(2);
        assertEquals("[1, null, 2]", tree.toString());
        tree.insert(3);
        assertEquals("[1, 3, 2]", tree.toString());
        tree.insert(4);
        assertEquals("[1, 3, 2, null, null, 4]", tree.toString());
        tree.insert(5);
        assertEquals("[1, 3, 2, 5, null, 4]", tree.toString());
    }

}