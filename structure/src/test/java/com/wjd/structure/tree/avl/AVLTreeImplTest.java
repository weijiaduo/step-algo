package com.wjd.structure.tree.avl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AVLTreeImplTest {

    @Test
    void testInsertLeftLeft() {
        AVLTree avlTree = new AVLTreeImpl();
        assertEquals("[]", avlTree.toString());
        avlTree.insert(30);
        assertEquals("[30]", avlTree.toString());
        avlTree.insert(28);
        assertEquals("[30, 28]", avlTree.toString());
        avlTree.insert(26);
        assertEquals("[28, 26, 30]", avlTree.toString());
        avlTree.insert(24);
        assertEquals("[28, 26, 30, 24]", avlTree.toString());
        avlTree.insert(22);
        assertEquals("[28, 24, 30, 22, 26]", avlTree.toString());
        avlTree.insert(20);
        assertEquals("[24, 22, 28, 20, null, 26, 30]", avlTree.toString());
        avlTree.insert(18);
        assertEquals("[24, 20, 28, 18, 22, 26, 30]", avlTree.toString());
    }

    @Test
    void testInsertLeftRight() {
        AVLTree avlTree = new AVLTreeImpl();
        assertEquals("[]", avlTree.toString());
        avlTree.insert(30);
        assertEquals("[30]", avlTree.toString());
        avlTree.insert(26);
        assertEquals("[30, 26]", avlTree.toString());
        avlTree.insert(28);
        assertEquals("[28, 26, 30]", avlTree.toString());
        avlTree.insert(22);
        assertEquals("[28, 26, 30, 22]", avlTree.toString());
        avlTree.insert(24);
        assertEquals("[28, 24, 30, 22, 26]", avlTree.toString());
        avlTree.insert(18);
        assertEquals("[24, 22, 28, 18, null, 26, 30]", avlTree.toString());
        avlTree.insert(20);
        assertEquals("[24, 20, 28, 18, 22, 26, 30]", avlTree.toString());
    }

    @Test
    void testInsertRightRight() {
        AVLTree avlTree = new AVLTreeImpl();
        assertEquals("[]", avlTree.toString());
        avlTree.insert(30);
        assertEquals("[30]", avlTree.toString());
        avlTree.insert(32);
        assertEquals("[30, null, 32]", avlTree.toString());
        avlTree.insert(34);
        assertEquals("[32, 30, 34]", avlTree.toString());
        avlTree.insert(36);
        assertEquals("[32, 30, 34, null, null, null, 36]", avlTree.toString());
        avlTree.insert(38);
        assertEquals("[32, 30, 36, null, null, 34, 38]", avlTree.toString());
        avlTree.insert(40);
        assertEquals("[36, 32, 38, 30, 34, null, 40]", avlTree.toString());
        avlTree.insert(42);
        assertEquals("[36, 32, 40, 30, 34, 38, 42]", avlTree.toString());
    }

    @Test
    void testInsertRightLeft() {
        AVLTree avlTree = new AVLTreeImpl();
        assertEquals("[]", avlTree.toString());
        avlTree.insert(30);
        assertEquals("[30]", avlTree.toString());
        avlTree.insert(34);
        assertEquals("[30, null, 34]", avlTree.toString());
        avlTree.insert(32);
        assertEquals("[32, 30, 34]", avlTree.toString());
        avlTree.insert(38);
        assertEquals("[32, 30, 34, null, null, null, 38]", avlTree.toString());
        avlTree.insert(36);
        assertEquals("[32, 30, 36, null, null, 34, 38]", avlTree.toString());
        avlTree.insert(42);
        assertEquals("[36, 32, 38, 30, 34, null, 42]", avlTree.toString());
        avlTree.insert(40);
        assertEquals("[36, 32, 40, 30, 34, 38, 42]", avlTree.toString());
    }

    @Test
    void testGet() {
        AVLTree avlTree = new AVLTreeImpl();
        avlTree.insert(30);
        avlTree.insert(28);
        avlTree.insert(26);
        avlTree.insert(24);
        avlTree.insert(22);
        avlTree.insert(20);
        avlTree.insert(18);

        assertEquals(30, avlTree.get(30));
        assertEquals(28, avlTree.get(28));
        assertEquals(26, avlTree.get(26));
        assertEquals(24, avlTree.get(24));
        assertEquals(22, avlTree.get(22));
        assertEquals(20, avlTree.get(20));
        assertEquals(18, avlTree.get(18));

        assertNull(avlTree.get(17));
        assertNull(avlTree.get(19));
        assertNull(avlTree.get(21));
        assertNull(avlTree.get(23));
        assertNull(avlTree.get(25));
        assertNull(avlTree.get(27));
        assertNull(avlTree.get(29));
        assertNull(avlTree.get(31));
    }

    @Test
    void testLeafRemoveLeftLeft() {
        AVLTree avlTree = new AVLTreeImpl();
        avlTree.insert(30);
        avlTree.insert(28);
        avlTree.insert(26);
        avlTree.insert(24);
        avlTree.insert(22);
        avlTree.insert(20);
        avlTree.insert(18);
        avlTree.insert(16);
        assertEquals("[24, 20, 28, 18, 22, 26, 30, 16]", avlTree.toString());

        avlTree.remove(22);
        assertEquals("[24, 18, 28, 16, 20, 26, 30]", avlTree.toString());
        avlTree.remove(30);
        assertEquals("[24, 18, 28, 16, 20, 26]", avlTree.toString());
        avlTree.remove(26);
        assertEquals("[24, 18, 28, 16, 20]", avlTree.toString());
        avlTree.remove(28);
        assertEquals("[18, 16, 24, null, null, 20]", avlTree.toString());
    }

    @Test
    void testLeafRemoveLeftRight() {
        AVLTree avlTree = new AVLTreeImpl();
        avlTree.insert(30);
        avlTree.insert(28);
        avlTree.insert(26);
        avlTree.insert(24);
        avlTree.insert(22);
        avlTree.insert(20);
        avlTree.insert(18);
        avlTree.insert(23);
        assertEquals("[24, 20, 28, 18, 22, 26, 30, null, null, null, 23]", avlTree.toString());

        avlTree.remove(30);
        assertEquals("[24, 20, 28, 18, 22, 26, null, null, null, null, 23]", avlTree.toString());
        avlTree.remove(26);
        assertEquals("[22, 20, 24, 18, null, 23, 28]", avlTree.toString());
    }

    @Test
    void testLeafRemoveRightRight() {
        AVLTree avlTree = new AVLTreeImpl();
        avlTree.insert(30);
        avlTree.insert(28);
        avlTree.insert(26);
        avlTree.insert(24);
        avlTree.insert(22);
        avlTree.insert(20);
        avlTree.insert(18);
        avlTree.insert(32);
        assertEquals("[24, 20, 28, 18, 22, 26, 30, null, null, null, null, null, null, null, 32]", avlTree.toString());

        avlTree.remove(26);
        assertEquals("[24, 20, 30, 18, 22, 28, 32]", avlTree.toString());
        avlTree.remove(18);
        assertEquals("[24, 20, 30, null, 22, 28, 32]", avlTree.toString());
        avlTree.remove(22);
        assertEquals("[24, 20, 30, null, null, 28, 32]", avlTree.toString());
        avlTree.remove(20);
        assertEquals("[30, 24, 32, null, 28]", avlTree.toString());
    }

    @Test
    void testLeafRemoveRightLeft() {
        AVLTree avlTree = new AVLTreeImpl();
        avlTree.insert(30);
        avlTree.insert(28);
        avlTree.insert(26);
        avlTree.insert(24);
        avlTree.insert(22);
        avlTree.insert(20);
        avlTree.insert(18);
        avlTree.insert(27);
        assertEquals("[24, 20, 28, 18, 22, 26, 30, null, null, null, null, null, 27]", avlTree.toString());

        avlTree.remove(18);
        assertEquals("[24, 20, 28, null, 22, 26, 30, null, null, null, 27]", avlTree.toString());
        avlTree.remove(22);
        assertEquals("[26, 24, 28, 20, null, 27, 30]", avlTree.toString());
    }

    @Test
    void testInternalRemoveLeftLeft() {
        AVLTree avlTree = new AVLTreeImpl();
        avlTree.insert(30);
        avlTree.insert(28);
        avlTree.insert(26);
        avlTree.insert(24);
        avlTree.insert(22);
        avlTree.insert(20);
        avlTree.insert(18);
        avlTree.insert(16);
        assertEquals("[24, 20, 28, 18, 22, 26, 30, 16]", avlTree.toString());

        avlTree.remove(24);
        assertEquals("[26, 20, 28, 18, 22, null, 30, 16]", avlTree.toString());
        avlTree.remove(26);
        assertEquals("[20, 18, 28, 16, null, 22, 30]", avlTree.toString());
        avlTree.remove(20);
        assertEquals("[22, 18, 28, 16, null, null, 30]", avlTree.toString());
        avlTree.remove(22);
        assertEquals("[28, 18, 30, 16]", avlTree.toString());
        avlTree.remove(28);
        assertEquals("[18, 16, 30]", avlTree.toString());
    }

    @Test
    void testInternalRemoveLeftRight() {
        AVLTree avlTree = new AVLTreeImpl();
        avlTree.insert(30);
        avlTree.insert(28);
        avlTree.insert(26);
        avlTree.insert(24);
        avlTree.insert(22);
        avlTree.insert(20);
        avlTree.insert(18);
        avlTree.insert(23);
        assertEquals("[24, 20, 28, 18, 22, 26, 30, null, null, null, 23]", avlTree.toString());

        avlTree.remove(24);
        assertEquals("[26, 20, 28, 18, 22, null, 30, null, null, null, 23]", avlTree.toString());
        avlTree.remove(26);
        assertEquals("[22, 20, 28, 18, null, 23, 30]", avlTree.toString());
    }

    @Test
    void testInternalRemoveRightRight() {
        AVLTree avlTree = new AVLTreeImpl();
        avlTree.insert(30);
        avlTree.insert(28);
        avlTree.insert(26);
        avlTree.insert(24);
        avlTree.insert(22);
        avlTree.insert(20);
        avlTree.insert(18);
        avlTree.insert(32);
        assertEquals("[24, 20, 28, 18, 22, 26, 30, null, null, null, null, null, null, null, 32]", avlTree.toString());

        avlTree.remove(24);
        assertEquals("[26, 20, 30, 18, 22, 28, 32]", avlTree.toString());
    }

    @Test
    void testInternalRemoveRightLeft() {
        AVLTree avlTree = new AVLTreeImpl();
        avlTree.insert(30);
        avlTree.insert(28);
        avlTree.insert(26);
        avlTree.insert(24);
        avlTree.insert(22);
        avlTree.insert(20);
        avlTree.insert(18);
        avlTree.insert(27);
        assertEquals("[24, 20, 28, 18, 22, 26, 30, null, null, null, null, null, 27]", avlTree.toString());

        avlTree.remove(20);
        assertEquals("[24, 22, 28, 18, null, 26, 30, null, null, null, 27]", avlTree.toString());
        avlTree.remove(22);
        assertEquals("[26, 24, 28, 18, null, 27, 30]", avlTree.toString());
    }

}