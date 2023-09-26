package com.wjd.structure.tree.bplus;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BPTreeImplTest {

    /**
     * 1. 添加到根节点
     */
    @Test
    void testPutRootNormal() {
        int m = 6;
        BPTree<Integer, Integer> bptree = new BPTreeImpl<>(m);

        assertEquals("[[]]", bptree.toString());
        bptree.put(10, 10);
        assertEquals("[[10]]", bptree.toString());
        bptree.put(12, 12);
        assertEquals("[[10, 12]]", bptree.toString());
        bptree.put(33, 33);
        assertEquals("[[10, 12, 33]]", bptree.toString());
        bptree.put(23, 23);
        assertEquals("[[10, 12, 23, 33]]", bptree.toString());
        bptree.put(48, 48);
        assertEquals("[[10, 12, 23, 33, 48]]", bptree.toString());
    }

    /**
     * 2. 根节点上溢，分裂
     */
    @Test
    void testPutRootOverflow() {
        int m = 6;
        BPTree<Integer, Integer> bptree = new BPTreeImpl<>(m);
        bptree.put(10, 10);
        bptree.put(48, 48);
        bptree.put(12, 12);
        bptree.put(33, 33);
        bptree.put(23, 23);

        bptree.put(50, 50);
        assertEquals("[[33], [10, 12, 23], [33, 48, 50]]", bptree.toString());
    }

    /**
     * 1. 添加到叶子节点，叶子节点还有空间
     */
    @Test
    void testPutLeafNormal() {
        int m = 6;
        BPTree<Integer, Integer> bptree = new BPTreeImpl<>(m);
        bptree.put(10, 10);
        bptree.put(48, 48);
        bptree.put(12, 12);
        bptree.put(30, 30);
        bptree.put(23, 23);
        bptree.put(50, 50);

        bptree.put(9, 9);
        assertEquals("[[30], [9, 10, 12, 23], [30, 48, 50]]", bptree.toString());
        bptree.put(37, 37);
        assertEquals("[[30], [9, 10, 12, 23], [30, 37, 48, 50]]", bptree.toString());
        bptree.put(25, 25);
        assertEquals("[[30], [9, 10, 12, 23, 25], [30, 37, 48, 50]]", bptree.toString());
        bptree.put(33, 33);
        assertEquals("[[30], [9, 10, 12, 23, 25], [30, 33, 37, 48, 50]]", bptree.toString());
    }

    /**
     * 2. 添加到叶子节点，叶子节点溢出
     */
    @Test
    void testPutLeafOverflow() {
        int m = 6;
        BPTree<Integer, Integer> bptree = new BPTreeImpl<>(m);
        bptree.put(10, 10);
        bptree.put(48, 48);
        bptree.put(12, 12);
        bptree.put(30, 30);
        bptree.put(23, 23);
        bptree.put(50, 50);
        bptree.put(9, 9);
        bptree.put(37, 37);
        bptree.put(25, 25);
        bptree.put(33, 33);

        bptree.put(14, 14);
        assertEquals("[[14, 30], [9, 10, 12], [14, 23, 25], [30, 33, 37, 48, 50]]", bptree.toString());
        bptree.put(55, 55);
        assertEquals("[[14, 30, 48], [9, 10, 12], [14, 23, 25], [30, 33, 37], [48, 50, 55]]", bptree.toString());
        bptree.put(61, 61);
        bptree.put(52, 52);
        assertEquals("[[14, 30, 48], [9, 10, 12], [14, 23, 25], [30, 33, 37], [48, 50, 52, 55, 61]]", bptree.toString());
        bptree.put(59, 59);
        assertEquals("[[14, 30, 48, 55], [9, 10, 12], [14, 23, 25], [30, 33, 37], [48, 50, 52], [55, 59, 61]]", bptree.toString());
        bptree.put(64, 64);
        bptree.put(72, 72);
        assertEquals("[[14, 30, 48, 55], [9, 10, 12], [14, 23, 25], [30, 33, 37], [48, 50, 52], [55, 59, 61, 64, 72]]", bptree.toString());
        bptree.put(71, 71);
        assertEquals("[[14, 30, 48, 55, 64], [9, 10, 12], [14, 23, 25], [30, 33, 37], [48, 50, 52], [55, 59, 61], [64, 71, 72]]", bptree.toString());
    }

    /**
     * 3. 添加后，产生多层上溢（从叶子节点开始上溢）
     */
    @Test
    void testPutInternalOverflow() {
        int m = 6;
        BPTree<Integer, Integer> bptree = new BPTreeImpl<>(m);
        bptree.put(10, 10);
        bptree.put(48, 48);
        bptree.put(12, 12);
        bptree.put(30, 30);
        bptree.put(23, 23);
        bptree.put(50, 50);
        bptree.put(9, 9);
        bptree.put(37, 37);
        bptree.put(25, 25);
        bptree.put(33, 33);
        bptree.put(14, 14);
        bptree.put(55, 55);
        bptree.put(61, 61);
        bptree.put(52, 52);
        bptree.put(59, 59);
        bptree.put(64, 64);
        bptree.put(72, 72);
        bptree.put(71, 71);

        bptree.put(2, 2);
        bptree.put(11, 11);
        assertEquals("[[14, 30, 48, 55, 64], [2, 9, 10, 11, 12], [14, 23, 25], [30, 33, 37], [48, 50, 52], [55, 59, 61], [64, 71, 72]]", bptree.toString());
        bptree.put(3, 3);
        assertEquals("[[48], [10, 14, 30], [55, 64], [2, 3, 9], [10, 11, 12], [14, 23, 25], [30, 33, 37], [48, 50, 52], [55, 59, 61], [64, 71, 72]]", bptree.toString());
    }

    /**
     * 1. 移除叶子节点元素，叶子节点还满足要求
     */
    @Test
    void testRemoveLeafNormal() {
        int m = 6;
        BPTree<Integer, Integer> bptree = new BPTreeImpl<>(m);
        bptree.put(10, 10);
        bptree.put(48, 48);
        bptree.put(12, 12);
        bptree.put(30, 30);
        bptree.put(23, 23);
        bptree.put(50, 50);
        bptree.put(9, 9);
        bptree.put(37, 37);
        bptree.put(25, 25);
        bptree.put(33, 33);
        bptree.put(14, 14);
        assertEquals("[[14, 30], [9, 10, 12], [14, 23, 25], [30, 33, 37, 48, 50]]", bptree.toString());

        bptree.remove(33);
        assertEquals("[[14, 30], [9, 10, 12], [14, 23, 25], [30, 37, 48, 50]]", bptree.toString());
        bptree.remove(30);
        assertEquals("[[14, 30], [9, 10, 12], [14, 23, 25], [37, 48, 50]]", bptree.toString());
        bptree.remove(48);
        assertEquals("[[14, 30], [9, 10, 12], [14, 23, 25], [37, 50]]", bptree.toString());
    }

    /**
     * 2. 移除叶子节点元素，叶子节点从左兄弟节点借元素
     */
    @Test
    void testRemoveLeafBorrowLeft() {
        int m = 6;
        BPTree<Integer, Integer> bptree = new BPTreeImpl<>(m);
        bptree.put(10, 10);
        bptree.put(48, 48);
        bptree.put(12, 12);
        bptree.put(30, 30);
        bptree.put(23, 23);
        bptree.put(50, 50);
        bptree.put(9, 9);
        bptree.put(37, 37);
        bptree.put(25, 25);
        bptree.put(33, 33);
        bptree.put(55, 55);
        assertEquals("[[30, 48], [9, 10, 12, 23, 25], [30, 33, 37], [48, 50, 55]]", bptree.toString());

        bptree.remove(50);
        assertEquals("[[30, 48], [9, 10, 12, 23, 25], [30, 33, 37], [48, 55]]", bptree.toString());
        bptree.remove(55);
        assertEquals("[[30, 37], [9, 10, 12, 23, 25], [30, 33], [37, 48]]", bptree.toString());
        bptree.remove(30);
        assertEquals("[[25, 37], [9, 10, 12, 23], [25, 33], [37, 48]]", bptree.toString());
        bptree.remove(33);
        assertEquals("[[23, 37], [9, 10, 12], [23, 25], [37, 48]]", bptree.toString());
    }

    /**
     * 3. 移除叶子节点元素，叶子节点从右兄弟节点借元素
     */
    @Test
    void testRemoveLeafBorrowRight() {
        int m = 6;
        BPTree<Integer, Integer> bptree = new BPTreeImpl<>(m);
        bptree.put(10, 10);
        bptree.put(48, 48);
        bptree.put(12, 12);
        bptree.put(30, 30);
        bptree.put(23, 23);
        bptree.put(50, 50);
        bptree.put(9, 9);
        bptree.put(37, 37);
        bptree.put(25, 25);
        bptree.put(33, 33);
        bptree.put(14, 14);
        assertEquals("[[14, 30], [9, 10, 12], [14, 23, 25], [30, 33, 37, 48, 50]]", bptree.toString());

        bptree.remove(10);
        assertEquals("[[14, 30], [9, 12], [14, 23, 25], [30, 33, 37, 48, 50]]", bptree.toString());
        bptree.remove(12);
        assertEquals("[[23, 30], [9, 14], [23, 25], [30, 33, 37, 48, 50]]", bptree.toString());
        bptree.remove(25);
        assertEquals("[[23, 33], [9, 14], [23, 30], [33, 37, 48, 50]]", bptree.toString());
        bptree.remove(23);
        assertEquals("[[23, 37], [9, 14], [30, 33], [37, 48, 50]]", bptree.toString());
    }

    /**
     * 4. 移除叶子节点元素，叶子节点和兄弟节点合并，并产生下溢
     */
    @Test
    void testRemoveLeafUnderflow() {
        int m = 6;
        BPTree<Integer, Integer> bptree = new BPTreeImpl<>(m);
        bptree.put(10, 10);
        bptree.put(48, 48);
        bptree.put(12, 12);
        bptree.put(33, 33);
        bptree.put(23, 23);
        bptree.put(50, 50);
        assertEquals("[[33], [10, 12, 23], [33, 48, 50]]", bptree.toString());

        bptree.remove(48);
        assertEquals("[[33], [10, 12, 23], [33, 50]]", bptree.toString());
        bptree.remove(23);
        assertEquals("[[33], [10, 12], [33, 50]]", bptree.toString());
        bptree.remove(50);
        assertEquals("[[10, 12, 33]]", bptree.toString());
    }

    /**
     * 1. 移除内部节点元素，内部节点还满足要求
     */
    @Test
    void testRemoveInternalNormal() {
        int m = 6;
        BPTree<Integer, Integer> bptree = new BPTreeImpl<>(m);
        bptree.put(10, 10);
        bptree.put(48, 48);
        bptree.put(12, 12);
        bptree.put(30, 30);
        bptree.put(23, 23);
        bptree.put(50, 50);
        bptree.put(9, 9);
        bptree.put(37, 37);
        bptree.put(25, 25);
        bptree.put(33, 33);
        bptree.put(14, 14);
        bptree.put(55, 55);
        bptree.put(61, 61);
        bptree.put(52, 52);
        bptree.put(59, 59);
        bptree.put(64, 64);
        bptree.put(72, 72);
        bptree.put(71, 71);
        bptree.put(2, 2);
        bptree.put(11, 11);
        bptree.put(3, 3);
        assertEquals("[[48], [10, 14, 30], [55, 64], [2, 3, 9], [10, 11, 12], [14, 23, 25], [30, 33, 37], [48, 50, 52], [55, 59, 61], [64, 71, 72]]", bptree.toString());

        bptree.remove(2);
        assertEquals("[[48], [10, 14, 30], [55, 64], [3, 9], [10, 11, 12], [14, 23, 25], [30, 33, 37], [48, 50, 52], [55, 59, 61], [64, 71, 72]]", bptree.toString());
        bptree.remove(3);
        assertEquals("[[48], [11, 14, 30], [55, 64], [9, 10], [11, 12], [14, 23, 25], [30, 33, 37], [48, 50, 52], [55, 59, 61], [64, 71, 72]]", bptree.toString());
        bptree.remove(10);
        assertEquals("[[48], [14, 30], [55, 64], [9, 11, 12], [14, 23, 25], [30, 33, 37], [48, 50, 52], [55, 59, 61], [64, 71, 72]]", bptree.toString());
    }

    /**
     * 2. 移除内部节点元素，内部节点从左兄弟节点借元素
     */
    @Test
    void testRemoveInternalBorrowLeft() {
        int m = 6;
        BPTree<Integer, Integer> bptree = new BPTreeImpl<>(m);
        bptree.put(10, 10);
        bptree.put(48, 48);
        bptree.put(12, 12);
        bptree.put(30, 30);
        bptree.put(23, 23);
        bptree.put(50, 50);
        bptree.put(9, 9);
        bptree.put(37, 37);
        bptree.put(25, 25);
        bptree.put(33, 33);
        bptree.put(14, 14);
        bptree.put(55, 55);
        bptree.put(61, 61);
        bptree.put(52, 52);
        bptree.put(59, 59);
        bptree.put(64, 64);
        bptree.put(72, 72);
        bptree.put(71, 71);
        bptree.put(2, 2);
        bptree.put(11, 11);
        bptree.put(3, 3);
        assertEquals("[[48], [10, 14, 30], [55, 64], [2, 3, 9], [10, 11, 12], [14, 23, 25], [30, 33, 37], [48, 50, 52], [55, 59, 61], [64, 71, 72]]", bptree.toString());

        bptree.remove(48);
        bptree.remove(55);
        bptree.remove(72);
        assertEquals("[[48], [10, 14, 30], [55, 64], [2, 3, 9], [10, 11, 12], [14, 23, 25], [30, 33, 37], [50, 52], [59, 61], [64, 71]]", bptree.toString());
        bptree.remove(59);
        assertEquals("[[30], [10, 14], [48, 64], [2, 3, 9], [10, 11, 12], [14, 23, 25], [30, 33, 37], [50, 52, 61], [64, 71]]", bptree.toString());
    }

    /**
     * 3. 移除内部节点元素，叶子节点从右子节点借元素
     */
    @Test
    void testRemoveInternalBorrowRight() {
        int m = 6;
        BPTree<Integer, Integer> bptree = new BPTreeImpl<>(m);
        bptree.put(10, 10);
        bptree.put(48, 48);
        bptree.put(12, 12);
        bptree.put(30, 30);
        bptree.put(23, 23);
        bptree.put(50, 50);
        bptree.put(9, 9);
        bptree.put(37, 37);
        bptree.put(25, 25);
        bptree.put(33, 33);
        bptree.put(14, 14);
        bptree.put(55, 55);
        bptree.put(61, 61);
        bptree.put(52, 52);
        bptree.put(59, 59);
        bptree.put(64, 64);
        bptree.put(72, 72);
        bptree.put(71, 71);
        bptree.put(2, 2);
        bptree.put(11, 11);
        bptree.put(3, 3);
        bptree.put(75, 75);
        bptree.put(86, 86);
        bptree.put(82, 82);
        assertEquals("[[48], [10, 14, 30], [55, 64, 75], [2, 3, 9], [10, 11, 12], [14, 23, 25], [30, 33, 37], [48, 50, 52], [55, 59, 61], [64, 71, 72], [75, 82, 86]]", bptree.toString());

        bptree.remove(2);
        bptree.remove(3);
        bptree.remove(10);
        assertEquals("[[48], [14, 30], [55, 64, 75], [9, 11, 12], [14, 23, 25], [30, 33, 37], [48, 50, 52], [55, 59, 61], [64, 71, 72], [75, 82, 86]]", bptree.toString());

        bptree.remove(23);
        bptree.remove(25);
        assertEquals("[[48], [12, 30], [55, 64, 75], [9, 11], [12, 14], [30, 33, 37], [48, 50, 52], [55, 59, 61], [64, 71, 72], [75, 82, 86]]", bptree.toString());
        bptree.remove(11);
        assertEquals("[[55], [30, 48], [64, 75], [9, 12, 14], [30, 33, 37], [48, 50, 52], [55, 59, 61], [64, 71, 72], [75, 82, 86]]", bptree.toString());
    }

    /**
     * 4. 移除内部节点元素，父节点+左右子节点合并，多层都产生下溢（从叶子节点往上）
     */
    @Test
    void testRemoveInternalUnderflow() {
        int m = 6;
        BPTree<Integer, Integer> bptree = new BPTreeImpl<>(m);
        bptree.put(10, 10);
        bptree.put(48, 48);
        bptree.put(12, 12);
        bptree.put(30, 30);
        bptree.put(23, 23);
        bptree.put(50, 50);
        bptree.put(9, 9);
        bptree.put(37, 37);
        bptree.put(25, 25);
        bptree.put(33, 33);
        bptree.put(14, 14);
        bptree.put(55, 55);
        bptree.put(61, 61);
        bptree.put(52, 52);
        bptree.put(59, 59);
        bptree.put(64, 64);
        bptree.put(72, 72);
        bptree.put(71, 71);
        bptree.put(2, 2);
        bptree.put(11, 11);
        bptree.put(3, 3);
        assertEquals("[[48], [10, 14, 30], [55, 64], [2, 3, 9], [10, 11, 12], [14, 23, 25], [30, 33, 37], [48, 50, 52], [55, 59, 61], [64, 71, 72]]", bptree.toString());

        bptree.remove(71);
        bptree.remove(72);
        assertEquals("[[48], [10, 14, 30], [55, 61], [2, 3, 9], [10, 11, 12], [14, 23, 25], [30, 33, 37], [48, 50, 52], [55, 59], [61, 64]]", bptree.toString());
        bptree.remove(61);
        assertEquals("[[30], [10, 14], [48, 55], [2, 3, 9], [10, 11, 12], [14, 23, 25], [30, 33, 37], [48, 50, 52], [55, 59, 64]]", bptree.toString());
        bptree.remove(55);
        bptree.remove(59);
        assertEquals("[[30], [10, 14], [48, 52], [2, 3, 9], [10, 11, 12], [14, 23, 25], [30, 33, 37], [48, 50], [52, 64]]", bptree.toString());
        bptree.remove(52);
        assertEquals("[[10, 14, 30, 48], [2, 3, 9], [10, 11, 12], [14, 23, 25], [30, 33, 37], [48, 50, 64]]", bptree.toString());
    }

    @Test
    void testGet() {
        int m = 6;
        BPTree<Integer, Integer> bptree = new BPTreeImpl<>(m);
        bptree.put(10, 10);
        bptree.put(48, 48);
        bptree.put(12, 12);
        bptree.put(33, 33);
        bptree.put(23, 23);
        bptree.put(50, 50);
        assertEquals("[[33], [10, 12, 23], [33, 48, 50]]", bptree.toString());

        assertEquals(10, bptree.get(10));
        assertEquals(33, bptree.get(33));
        assertEquals(48, bptree.get(48));
        assertEquals(23, bptree.get(23));
        assertEquals(50, bptree.get(50));

        assertNull(bptree.get(9));
        assertNull(bptree.get(11));
        assertNull(bptree.get(15));
        assertNull(bptree.get(24));
        assertNull(bptree.get(32));
        assertNull(bptree.get(37));
        assertNull(bptree.get(49));
        assertNull(bptree.get(62));
    }

    /**
     * 验证叶子节点的双向链表
     */
    @Test
    void testLeafKeys() throws Exception {
        int m = 6;
        BPTree<Integer, Integer> bptree = new BPTreeImpl<>(m);
        bptree.put(10, 10);
        bptree.put(48, 48);
        bptree.put(12, 12);
        bptree.put(30, 30);
        bptree.put(23, 23);
        bptree.put(50, 50);
        bptree.put(9, 9);
        bptree.put(37, 37);
        bptree.put(25, 25);
        bptree.put(33, 33);
        bptree.put(14, 14);
        bptree.put(55, 55);
        bptree.put(61, 61);
        bptree.put(52, 52);
        bptree.put(59, 59);
        bptree.put(64, 64);
        bptree.put(72, 72);
        bptree.put(71, 71);
        bptree.put(2, 2);
        bptree.put(11, 11);
        bptree.put(3, 3);
        assertEquals("[[48], [10, 14, 30], [55, 64], [2, 3, 9], [10, 11, 12], [14, 23, 25], [30, 33, 37], [48, 50, 52], [55, 59, 61], [64, 71, 72]]", bptree.toString());

        BPTNode<Integer, Integer> min = getRoot(bptree);
        while (min != null && !min.isLeaf()) {
            min = min.getChild(0);
        }
        assertTrue(min instanceof BPTLeaf);

        List<Integer> expect = Arrays.asList(2, 3, 9, 10, 11, 12, 14, 23, 25, 30, 33, 37, 48, 50, 52, 55, 59, 61, 64, 71, 72);
        List<Integer> actual = new ArrayList<>();
        BPTLeaf<Integer, Integer> p = (BPTLeaf<Integer, Integer>) min;
        while (p != null) {
            actual.addAll(p.keys());
            p = p.getNext();
        }
        assertEquals(expect.toString(), actual.toString());
    }

    /**
     * 验证叶子节点的双向链表
     */
    @Test
    void testLeafReverseKeys() throws Exception {
        int m = 6;
        BPTree<Integer, Integer> bptree = new BPTreeImpl<>(m);
        bptree.put(10, 10);
        bptree.put(48, 48);
        bptree.put(12, 12);
        bptree.put(30, 30);
        bptree.put(23, 23);
        bptree.put(50, 50);
        bptree.put(9, 9);
        bptree.put(37, 37);
        bptree.put(25, 25);
        bptree.put(33, 33);
        bptree.put(14, 14);
        bptree.put(55, 55);
        bptree.put(61, 61);
        bptree.put(52, 52);
        bptree.put(59, 59);
        bptree.put(64, 64);
        bptree.put(72, 72);
        bptree.put(71, 71);
        bptree.put(2, 2);
        bptree.put(11, 11);
        bptree.put(3, 3);
        assertEquals("[[48], [10, 14, 30], [55, 64], [2, 3, 9], [10, 11, 12], [14, 23, 25], [30, 33, 37], [48, 50, 52], [55, 59, 61], [64, 71, 72]]", bptree.toString());

        BPTNode<Integer, Integer> max = getRoot(bptree);
        while (max != null && !max.isLeaf()) {
            max = max.getChild(max.size);
        }
        assertTrue(max instanceof BPTLeaf);

        List<Integer> expect = Arrays.asList(2, 3, 9, 10, 11, 12, 14, 23, 25, 30, 33, 37, 48, 50, 52, 55, 59, 61, 64, 71, 72);
        Collections.reverse(expect);
        List<Integer> actual = new ArrayList<>();
        BPTLeaf<Integer, Integer> p = (BPTLeaf<Integer, Integer>) max;
        while (p != null) {
            List<Integer> keys = p.keys();
            for (int i = keys.size() - 1; i >= 0; i--) {
                actual.add(keys.get(i));
            }
            p = p.getPrev();
        }
        assertEquals(expect.toString(), actual.toString());
    }

    /**
     * 获取根节点
     *
     * @param bptree B+树
     * @return 根节点
     * @throws Exception 异常
     */
    @SuppressWarnings("unchecked")
    private BPTNode<Integer, Integer> getRoot(BPTree<Integer, Integer> bptree) throws Exception {
        Field rootField = BPTreeImpl.class.getDeclaredField("root");
        rootField.setAccessible(true);
        BPTNode<Integer, Integer> root = (BPTNode<Integer, Integer>) rootField.get(bptree);
        rootField.setAccessible(false);
        return root;
    }
}