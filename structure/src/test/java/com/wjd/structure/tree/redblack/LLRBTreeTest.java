package com.wjd.structure.tree.redblack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LLRBTreeTest {

    @Test
    void testTwoLInsert() {
        RBTree rbTree = new LLRBTree();
        assertEquals("[]", rbTree.toString());
        rbTree.insert(10);
        assertEquals("[b10]", rbTree.toString());
        rbTree.insert(5);
        assertEquals("[b10, r5]", rbTree.toString());
    }

    @Test
    void testTwoRInsert() {
        RBTree rbTree = new LLRBTree();
        assertEquals("[]", rbTree.toString());
        rbTree.insert(5);
        assertEquals("[b5]", rbTree.toString());
        rbTree.insert(10);
        assertEquals("[b10, r5]", rbTree.toString());
    }

    @Test
    void testThreeLInsert() {
        RBTree rbTree = new LLRBTree();
        rbTree.insert(10);
        rbTree.insert(5);
        assertEquals("[b10, r5]", rbTree.toString());

        rbTree.insert(3);
        assertEquals("[b5, b3, b10]", rbTree.toString());
        rbTree.insert(2);
        assertEquals("[b5, b3, b10, r2]", rbTree.toString());
        rbTree.insert(1);
        assertEquals("[b5, r2, b10, b1, b3]", rbTree.toString());
        rbTree.insert(8);
        assertEquals("[b5, r2, b10, b1, b3, r8]", rbTree.toString());
        rbTree.insert(7);
        assertEquals("[b5, b2, b8, b1, b3, b7, b10]", rbTree.toString());
    }

    @Test
    void testThreeMInsert() {
        RBTree rbTree = new LLRBTree();
        rbTree.insert(10);
        rbTree.insert(5);
        assertEquals("[b10, r5]", rbTree.toString());

        rbTree.insert(7);
        assertEquals("[b7, b5, b10]", rbTree.toString());
        rbTree.insert(3);
        assertEquals("[b7, b5, b10, r3]", rbTree.toString());
        rbTree.insert(4);
        assertEquals("[b7, r4, b10, b3, b5]", rbTree.toString());
        rbTree.insert(8);
        assertEquals("[b7, r4, b10, b3, b5, r8]", rbTree.toString());
        rbTree.insert(9);
        assertEquals("[b7, b4, b9, b3, b5, b8, b10]", rbTree.toString());
    }

    @Test
    void testThreeRInsert() {
        RBTree rbTree = new LLRBTree();
        rbTree.insert(5);
        rbTree.insert(10);
        assertEquals("[b10, r5]", rbTree.toString());
        rbTree.insert(16);
        assertEquals("[b10, b5, b16]", rbTree.toString());
        rbTree.insert(3);
        assertEquals("[b10, b5, b16, r3]", rbTree.toString());
        rbTree.insert(7);
        assertEquals("[b10, r5, b16, b3, b7]", rbTree.toString());
        rbTree.insert(20);
        assertEquals("[b10, r5, b20, b3, b7, r16]", rbTree.toString());
        rbTree.insert(24);
        assertEquals("[b10, b5, b20, b3, b7, b16, b24]", rbTree.toString());
        rbTree.insert(30);
        assertEquals("[b10, b5, b20, b3, b7, b16, b30, null, null, null, null, null, null, r24]", rbTree.toString());
        rbTree.insert(32);
        assertEquals("[b10, b5, b30, b3, b7, r20, b32, null, null, null, null, b16, b24]", rbTree.toString());
    }

    @Test
    void testGet() {
        RBTree rbTree = new LLRBTree();
        rbTree.insert(5);
        rbTree.insert(10);
        rbTree.insert(16);
        rbTree.insert(3);
        rbTree.insert(7);
        rbTree.insert(20);
        rbTree.insert(24);
        rbTree.insert(30);
        rbTree.insert(32);

        assertEquals(5, rbTree.get(5));
        assertEquals(16, rbTree.get(16));
        assertEquals(3, rbTree.get(3));
        assertEquals(20, rbTree.get(20));
        assertEquals(24, rbTree.get(24));
        assertEquals(32, rbTree.get(32));
        assertEquals(30, rbTree.get(30));
        assertEquals(10, rbTree.get(10));

        assertNull(rbTree.get(2));
        assertNull(rbTree.get(6));
        assertNull(rbTree.get(8));
        assertNull(rbTree.get(11));
        assertNull(rbTree.get(17));
        assertNull(rbTree.get(21));
        assertNull(rbTree.get(25));
        assertNull(rbTree.get(31));
        assertNull(rbTree.get(33));
    }

    @Test
    void testTwoLeafDelete() {
        RBTree rbTree = new LLRBTree();
        rbTree.insert(5);
        rbTree.insert(10);
        rbTree.insert(16);
        rbTree.insert(3);
        rbTree.insert(7);
        rbTree.insert(20);
        rbTree.insert(24);
        assertEquals("[b10, b5, b20, b3, b7, b16, b24]", rbTree.toString());

        rbTree.remove(3);
        assertEquals("[b20, r10, b24, b7, b16, null, null, r5]", rbTree.toString());
    }

    @Test
    void testTwoInternalDelete() {
        RBTree rbTree = new LLRBTree();
        rbTree.insert(5);
        rbTree.insert(10);
        rbTree.insert(16);
        rbTree.insert(3);
        rbTree.insert(7);
        rbTree.insert(20);
        rbTree.insert(24);
        assertEquals("[b10, b5, b20, b3, b7, b16, b24]", rbTree.toString());

        rbTree.remove(5);
        assertEquals("[b20, r10, b24, b7, b16, null, null, r3]", rbTree.toString());
    }

    @Test
    void testThreeLLeafDelete() {
        RBTree rbTree = new LLRBTree();
        rbTree.insert(10);
        rbTree.insert(5);
        rbTree.insert(7);
        rbTree.insert(3);
        rbTree.insert(8);
        assertEquals("[b7, b5, b10, r3, null, r8]", rbTree.toString());

        rbTree.remove(3);
        assertEquals("[b7, b5, b10, null, null, r8]", rbTree.toString());
        rbTree.remove(8);
        assertEquals("[b7, b5, b10]", rbTree.toString());
    }

    @Test
    void testThreeLDoubleDelete() {
        RBTree rbTree = new LLRBTree();
        rbTree.insert(10);
        rbTree.insert(5);
        rbTree.insert(7);
        rbTree.insert(3);
        rbTree.insert(4);
        rbTree.insert(8);
        assertEquals("[b7, r4, b10, b3, b5, r8]", rbTree.toString());

        rbTree.remove(4);
        assertEquals("[b7, b5, b10, r3, null, r8]", rbTree.toString());
    }

    @Test
    void testThreeRSingleDelete() {
        RBTree rbTree = new LLRBTree();
        rbTree.insert(10);
        rbTree.insert(5);
        rbTree.insert(7);
        rbTree.insert(3);
        rbTree.insert(8);
        assertEquals("[b7, b5, b10, r3, null, r8]", rbTree.toString());

        rbTree.remove(5);
        assertEquals("[b7, b3, b10, null, null, r8]", rbTree.toString());
        rbTree.remove(10);
        assertEquals("[b7, b3, b8]", rbTree.toString());
    }

    @Test
    void testDeleteMin() {
        LLRBTree rbTree = new LLRBTree();
        rbTree.insert(5);
        rbTree.insert(10);
        rbTree.insert(16);
        rbTree.insert(3);
        rbTree.insert(7);
        rbTree.insert(20);
        rbTree.insert(24);
        assertEquals("[b10, b5, b20, b3, b7, b16, b24]", rbTree.toString());

        rbTree.deleteMin();
        assertEquals("[b20, r10, b24, b7, b16, null, null, r5]", rbTree.toString());
    }

    @Test
    void testDeleteMax() {
        LLRBTree rbTree = new LLRBTree();
        rbTree.insert(5);
        rbTree.insert(10);
        rbTree.insert(16);
        rbTree.insert(3);
        rbTree.insert(7);
        rbTree.insert(20);
        rbTree.insert(24);
        assertEquals("[b10, b5, b20, b3, b7, b16, b24]", rbTree.toString());

        rbTree.deleteMax();
        assertEquals("[b10, r5, b20, b3, b7, r16]", rbTree.toString());
    }

}