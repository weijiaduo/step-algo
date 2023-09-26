package com.wjd.algorithm.tree.bplus.traverse;

import com.wjd.structure.tree.bplus.BPTInternal;
import com.wjd.structure.tree.bplus.BPTLeaf;
import com.wjd.structure.tree.bplus.BPTNode;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LevelBPTreeTraverseTest {

    @Test
    void testTraverse() throws Exception {
        String expect = "[[14, 30], [9, 10, 12], [14, 23, 25], [30, 33, 37, 48, 50]]";

        Method addEntry = BPTNode.class.getDeclaredMethod("addEntry", BPTNode.Entry.class);
        addEntry.setAccessible(true);

        BPTInternal<Integer, Integer> root = new BPTInternal<>(6);
        addEntry.invoke(root, new BPTNode.Entry<>(14, 14));
        addEntry.invoke(root, new BPTNode.Entry<>(30, 30));

        BPTLeaf<Integer, Integer> child0 = new BPTLeaf<>(6);
        addEntry.invoke(child0, new BPTNode.Entry<>(9, 9));
        addEntry.invoke(child0, new BPTNode.Entry<>(10, 10));
        addEntry.invoke(child0, new BPTNode.Entry<>(12, 12));

        BPTLeaf<Integer, Integer> child1 = new BPTLeaf<>(6);
        addEntry.invoke(child1, new BPTNode.Entry<>(14, 14));
        addEntry.invoke(child1, new BPTNode.Entry<>(23, 23));
        addEntry.invoke(child1, new BPTNode.Entry<>(25, 25));

        BPTLeaf<Integer, Integer> child2 = new BPTLeaf<>(6);
        addEntry.invoke(child2, new BPTNode.Entry<>(30, 30));
        addEntry.invoke(child2, new BPTNode.Entry<>(33, 33));
        addEntry.invoke(child2, new BPTNode.Entry<>(37, 37));
        addEntry.invoke(child2, new BPTNode.Entry<>(48, 48));
        addEntry.invoke(child2, new BPTNode.Entry<>(50, 50));

        root.setChild(0, child0);
        root.setChild(1, child1);
        root.setChild(2, child2);

        List<List<Integer>> actual = new LevelBPTreeTraverse<Integer, Integer>().traverse(root);
        System.out.println(actual);

        assertEquals(expect, actual.toString());
    }
}