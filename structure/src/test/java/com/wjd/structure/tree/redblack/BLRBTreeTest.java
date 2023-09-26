package com.wjd.structure.tree.redblack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BLRBTreeTest {

    @Test
    void testInsertLeftLeft() {
        RBTree rbTree = new BLRBTree();
        assertEquals("[]", rbTree.toString());
        rbTree.insert(10);
        assertEquals("[b10]", rbTree.toString());
        rbTree.insert(9);
        assertEquals("[b10, r9]", rbTree.toString());
        rbTree.insert(8);
        assertEquals("[b9, b8, b10]", rbTree.toString());
        rbTree.insert(7);
        assertEquals("[b9, b8, b10, r7]", rbTree.toString());
        rbTree.insert(6);
        assertEquals("[b9, r7, b10, b6, b8]", rbTree.toString());
        rbTree.insert(5);
        assertEquals("[b9, r7, b10, b6, b8, null, null, r5]", rbTree.toString());
        rbTree.insert(4);
        assertEquals("[b7, b5, b9, b4, b6, b8, b10]", rbTree.toString());
    }

    @Test
    void testInsertLeftRight() {
        RBTree rbTree = new BLRBTree();
        assertEquals("[]", rbTree.toString());
        rbTree.insert(10);
        assertEquals("[b10]", rbTree.toString());
        rbTree.insert(5);
        assertEquals("[b10, r5]", rbTree.toString());
        rbTree.insert(9);
        assertEquals("[b9, b5, b10]", rbTree.toString());
        rbTree.insert(3);
        assertEquals("[b9, b5, b10, r3]", rbTree.toString());
        rbTree.insert(4);
        assertEquals("[b9, r4, b10, b3, b5]", rbTree.toString());
        rbTree.insert(1);
        assertEquals("[b9, r4, b10, b3, b5, null, null, r1]", rbTree.toString());
        rbTree.insert(2);
        assertEquals("[b4, b2, b9, b1, b3, b5, b10]", rbTree.toString());
    }

    @Test
    void testInsertRightRight() {
        RBTree rbTree = new BLRBTree();
        assertEquals("[]", rbTree.toString());
        rbTree.insert(4);
        assertEquals("[b4]", rbTree.toString());
        rbTree.insert(5);
        assertEquals("[b4, null, r5]", rbTree.toString());
        rbTree.insert(6);
        assertEquals("[b5, b4, b6]", rbTree.toString());
        rbTree.insert(7);
        assertEquals("[b5, b4, b6, null, null, null, r7]", rbTree.toString());
        rbTree.insert(8);
        assertEquals("[b5, b4, r7, null, null, b6, b8]", rbTree.toString());
        rbTree.insert(9);
        assertEquals("[b5, b4, r7, null, null, b6, b8, null, null, null, r9]", rbTree.toString());
        rbTree.insert(10);
        assertEquals("[b7, b5, b9, b4, b6, b8, b10]", rbTree.toString());
    }

    @Test
    void testInsertRightLeft() {
        RBTree rbTree = new BLRBTree();
        assertEquals("[]", rbTree.toString());
        rbTree.insert(5);
        assertEquals("[b5]", rbTree.toString());
        rbTree.insert(10);
        assertEquals("[b5, null, r10]", rbTree.toString());
        rbTree.insert(6);
        assertEquals("[b6, b5, b10]", rbTree.toString());
        rbTree.insert(15);
        assertEquals("[b6, b5, b10, null, null, null, r15]", rbTree.toString());
        rbTree.insert(11);
        assertEquals("[b6, b5, r11, null, null, b10, b15]", rbTree.toString());
        rbTree.insert(18);
        assertEquals("[b6, b5, r11, null, null, b10, b15, null, null, null, r18]", rbTree.toString());
        rbTree.insert(16);
        assertEquals("[b11, b6, b16, b5, b10, b15, b18]", rbTree.toString());
    }

    @Test
    void testGet() {
        RBTree rbTree = new BLRBTree();
        rbTree.insert(5);
        rbTree.insert(10);
        rbTree.insert(6);
        rbTree.insert(15);
        rbTree.insert(11);
        rbTree.insert(18);
        rbTree.insert(16);
        rbTree.insert(3);
        rbTree.insert(7);
        rbTree.insert(9);

        assertEquals(3, rbTree.get(3));
        assertEquals(5, rbTree.get(5));
        assertEquals(6, rbTree.get(6));
        assertEquals(7, rbTree.get(7));
        assertEquals(9, rbTree.get(9));
        assertEquals(10, rbTree.get(10));
        assertEquals(11, rbTree.get(11));
        assertEquals(15, rbTree.get(15));
        assertEquals(16, rbTree.get(16));
        assertEquals(18, rbTree.get(18));

        assertNull(rbTree.get(2));
        assertNull(rbTree.get(4));
        assertNull(rbTree.get(8));
        assertNull(rbTree.get(12));
        assertNull(rbTree.get(13));
        assertNull(rbTree.get(14));
        assertNull(rbTree.get(17));
    }

    @Test
    void testDeleteLeftBorrowRightRight() {
        RBTree rbTree = new BLRBTree();
        rbTree.insert(4);
        rbTree.insert(5);
        rbTree.insert(6);
        rbTree.insert(7);
        rbTree.insert(8);
        rbTree.insert(9);
        rbTree.insert(10);
        rbTree.insert(12);
        rbTree.insert(16);
        assertEquals("[b7, b5, b9, b4, b6, b8, r12, null, null, null, null, null, null, b10, b16]", rbTree.toString());

        rbTree.remove(4);
        assertEquals("[b9, b7, b12, b5, b8, b10, b16, null, r6]", rbTree.toString());
    }

    @Test
    void testDeleteLeftBorrowRightLeft() {
        RBTree rbTree = new BLRBTree();
        rbTree.insert(4);
        rbTree.insert(5);
        rbTree.insert(6);
        rbTree.insert(7);
        rbTree.insert(8);
        rbTree.insert(12);
        rbTree.insert(16);
        rbTree.insert(9);
        rbTree.insert(10);
        assertEquals("[b7, b5, b12, b4, b6, r9, b16, null, null, null, null, b8, b10]", rbTree.toString());

        rbTree.remove(4);
        assertEquals("[b9, b7, b12, b5, b8, b10, b16, null, r6]", rbTree.toString());
    }

    @Test
    void testDeleteLeftMergeRedParentBlackRight() {
        RBTree rbTree = new BLRBTree();
        rbTree.insert(18);
        rbTree.insert(16);
        rbTree.insert(14);
        rbTree.insert(12);
        rbTree.insert(10);
        rbTree.insert(9);
        rbTree.insert(8);
        rbTree.insert(7);
        rbTree.insert(6);
        rbTree.insert(5);
        rbTree.insert(4);
        assertEquals("[b12, r7, b16, b5, b9, b14, b18, b4, b6, b8, b10]", rbTree.toString());

        rbTree.remove(4);
        assertEquals("[b12, b7, b16, b5, r9, b14, b18, null, r6, b8, b10]", rbTree.toString());
    }

    @Test
    void testDeleteLeftMergeBlackParentBlackRight() {
        RBTree rbTree = new BLRBTree();
        rbTree.insert(10);
        rbTree.insert(9);
        rbTree.insert(8);
        rbTree.insert(7);
        rbTree.insert(6);
        rbTree.insert(5);
        rbTree.insert(4);
        assertEquals("[b7, b5, b9, b4, b6, b8, b10]", rbTree.toString());

        rbTree.remove(4);
        assertEquals("[b7, b5, r9, null, r6, b8, b10]", rbTree.toString());
    }

    @Test
    void testDeleteLeftRotateRedRight() {
        RBTree rbTree = new BLRBTree();
        rbTree.insert(4);
        rbTree.insert(5);
        rbTree.insert(6);
        rbTree.insert(7);
        rbTree.insert(8);
        rbTree.insert(9);
        rbTree.insert(10);
        rbTree.insert(12);
        rbTree.insert(14);
        rbTree.insert(16);
        rbTree.insert(18);
        assertEquals("[b7, b5, r12, b4, b6, b9, b16, null, null, null, null, b8, b10, b14, b18]", rbTree.toString());

        rbTree.remove(4);
        assertEquals("[b12, b7, b16, b5, r9, b14, b18, null, r6, b8, b10]", rbTree.toString());
    }

    @Test
    void testDeleteRightBorrowLeftLeft() {
        RBTree rbTree = new BLRBTree();
        rbTree.insert(16);
        rbTree.insert(12);
        rbTree.insert(10);
        rbTree.insert(9);
        rbTree.insert(8);
        rbTree.insert(7);
        rbTree.insert(6);
        rbTree.insert(5);
        rbTree.insert(4);
        assertEquals("[b9, b7, b12, r5, b8, b10, b16, b4, b6]", rbTree.toString());

        rbTree.remove(16);
        assertEquals("[b7, b5, b9, b4, b6, b8, b12, null, null, null, null, null, null, r10]", rbTree.toString());
    }

    @Test
    void testDeleteRightBorrowLeftRight() {
        RBTree rbTree = new BLRBTree();
        rbTree.insert(16);
        rbTree.insert(12);
        rbTree.insert(10);
        rbTree.insert(9);
        rbTree.insert(8);
        rbTree.insert(5);
        rbTree.insert(4);
        rbTree.insert(7);
        rbTree.insert(6);
        assertEquals("[b9, b5, b12, b4, r7, b10, b16, null, null, b6, b8]", rbTree.toString());

        rbTree.remove(16);
        assertEquals("[b7, b5, b9, b4, b6, b8, b12, null, null, null, null, null, null, r10]", rbTree.toString());
    }

    @Test
    void testDeleteRightMergeRedParentBlackLeft() {
        RBTree rbTree = new BLRBTree();
        rbTree.insert(4);
        rbTree.insert(5);
        rbTree.insert(6);
        rbTree.insert(7);
        rbTree.insert(8);
        rbTree.insert(9);
        rbTree.insert(10);
        rbTree.insert(12);
        rbTree.insert(14);
        rbTree.insert(16);
        rbTree.insert(18);
        assertEquals("[b7, b5, r12, b4, b6, b9, b16, null, null, null, null, b8, b10, b14, b18]", rbTree.toString());

        rbTree.remove(18);
        assertEquals("[b7, b5, b12, b4, b6, r9, b16, null, null, null, null, b8, b10, r14]", rbTree.toString());
    }

    @Test
    void testDeleteRightMergeBlackParentBlackLeft() {
        RBTree rbTree = new BLRBTree();
        rbTree.insert(4);
        rbTree.insert(5);
        rbTree.insert(6);
        rbTree.insert(7);
        rbTree.insert(8);
        rbTree.insert(9);
        rbTree.insert(10);
        assertEquals("[b7, b5, b9, b4, b6, b8, b10]", rbTree.toString());

        rbTree.remove(10);
        assertEquals("[b7, r5, b9, b4, b6, r8]", rbTree.toString());
    }

    @Test
    void testDeleteRightRotateRedLeft() {
        RBTree rbTree = new BLRBTree();
        rbTree.insert(18);
        rbTree.insert(16);
        rbTree.insert(14);
        rbTree.insert(12);
        rbTree.insert(10);
        rbTree.insert(9);
        rbTree.insert(8);
        rbTree.insert(7);
        rbTree.insert(6);
        rbTree.insert(5);
        rbTree.insert(4);
        assertEquals("[b12, r7, b16, b5, b9, b14, b18, b4, b6, b8, b10]", rbTree.toString());

        rbTree.remove(18);
        assertEquals("[b7, b5, b12, b4, b6, r9, b16, null, null, null, null, b8, b10, r14]", rbTree.toString());
    }

    @Test
    void testDeleteMinReplacer() {
        RBTree rbTree = new BLRBTree();
        rbTree.insert(18);
        rbTree.insert(16);
        rbTree.insert(14);
        rbTree.insert(12);
        rbTree.insert(10);
        rbTree.insert(9);
        rbTree.insert(8);
        rbTree.insert(7);
        rbTree.insert(6);
        rbTree.insert(5);
        rbTree.insert(4);
        assertEquals("[b12, r7, b16, b5, b9, b14, b18, b4, b6, b8, b10]", rbTree.toString());

        rbTree.remove(7);
        assertEquals("[b12, b8, b16, r5, b9, b14, b18, b4, b6, null, r10]", rbTree.toString());
    }

    @Test
    void testDeleteLeftReplacer() {
        RBTree rbTree = new BLRBTree();
        rbTree.insert(18);
        rbTree.insert(16);
        rbTree.insert(14);
        rbTree.insert(12);
        rbTree.insert(10);
        rbTree.insert(9);
        rbTree.insert(8);
        rbTree.insert(7);
        rbTree.insert(6);
        rbTree.insert(5);
        rbTree.insert(4);
        rbTree.insert(17);
        assertEquals("[b12, r7, b16, b5, b9, b14, b18, b4, b6, b8, b10, null, null, r17]", rbTree.toString());

        rbTree.remove(18);
        assertEquals("[b12, r7, b16, b5, b9, b14, b17, b4, b6, b8, b10]", rbTree.toString());
    }

    @Test
    void testDeleteRightReplacer() {
        RBTree rbTree = new BLRBTree();
        rbTree.insert(18);
        rbTree.insert(16);
        rbTree.insert(14);
        rbTree.insert(12);
        rbTree.insert(10);
        rbTree.insert(9);
        rbTree.insert(8);
        rbTree.insert(7);
        rbTree.insert(6);
        rbTree.insert(5);
        rbTree.insert(4);
        rbTree.insert(19);
        assertEquals("[b12, r7, b16, b5, b9, b14, b18, b4, b6, b8, b10, null, null, null, r19]", rbTree.toString());

        rbTree.remove(18);
        assertEquals("[b12, r7, b16, b5, b9, b14, b19, b4, b6, b8, b10]", rbTree.toString());
    }

}