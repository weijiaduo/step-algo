package com.wjd.structure.tree.bst;

import com.wjd.structure.tree.binary.TreeNode;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class BSTreeImplTest {

    @Test
    void testInit() {
        int caseSize = 1000;
        Random random = new Random();
        for (int i = 0; i < caseSize; i++) {
            Set<Integer> existSet = new HashSet<>();
            int size = 50 + random.nextInt(150);
            int[] values = new int[size];
            for (int j = 0; j < size; j++) {
                values[j] = random.nextInt(1000);
                existSet.add(values[j]);
            }
            BSTreeImpl bsTree = new BSTreeImpl(values);

            // 中序遍历整棵树，看是否是有序的
            List<TreeNode> inorder = inorder(bsTree.getRoot());
            int prev = inorder.get(0).val;
            for (int k = 1; k < inorder.size(); k++) {
                int cur = inorder.get(k).val;
                assertTrue(cur > prev);
                assertTrue(existSet.contains(cur));
                prev = cur;
            }

            // 验证元素值是否完整
            Set<Integer> valueSet = new HashSet<>();
            for (TreeNode node : inorder) {
                valueSet.add(node.val);
            }
            assertEquals(valueSet.size(), existSet.size());
            assertTrue(valueSet.containsAll(existSet));
        }
    }

    @Test
    void testQuery() {
        int caseSize = 1000;
        Random random = new Random();
        for (int i = 0; i < caseSize; i++) {
            Set<Integer> existSet = new HashSet<>();
            int size = 50 + random.nextInt(150);
            int[] values = new int[size];
            for (int j = 0; j < size; j++) {
                values[j] = random.nextInt(1000);
                existSet.add(values[j]);
            }
            BSTreeImpl bsTree = new BSTreeImpl(values);

            // 随机验证节点是否存在
            int testSize = 50 + random.nextInt(50);
            for (int j = 0; j < testSize; j++) {
                int val = random.nextInt(1000);
                TreeNode node = bsTree.query(val);
                assertEquals(existSet.contains(val), node != null);
                if (node != null) {
                    assertEquals(val, node.val);
                }
            }
        }
    }

    @Test
    void testDelete() {
        int caseSize = 1000;
        Random random = new Random();
        for (int i = 0; i < caseSize; i++) {
            int size = 50 + random.nextInt(150);
            int[] values = new int[size];
            for (int j = 0; j < size; j++) {
                values[j] = random.nextInt(1000);
            }
            BSTreeImpl bsTree = new BSTreeImpl(values);

            // 随机验证节点是否删除成功
            int testSize = 50 + random.nextInt(50);
            for (int j = 0; j < testSize; j++) {
                int val = random.nextInt(1000);
                bsTree.remove(val);
                assertNull(bsTree.query(val));
            }
        }
    }

    @Test
    void testInsert() {
        int caseSize = 1000;
        Random random = new Random();
        for (int i = 0; i < caseSize; i++) {
            int size = 50 + random.nextInt(150);
            int[] values = new int[size];
            for (int j = 0; j < size; j++) {
                values[j] = random.nextInt(1000);
            }
            BSTreeImpl bsTree = new BSTreeImpl(values);

            // 随机验证节点是否插入成功
            int testSize = 50 + random.nextInt(50);
            for (int j = 0; j < testSize; j++) {
                int val = random.nextInt(1000);
                bsTree.insert(val);
                assertNotNull(bsTree.query(val));
            }

            // 中序遍历整棵树，看是否是有序的
            List<TreeNode> inorder = inorder(bsTree.getRoot());
            int prev = inorder.get(0).val;
            for (int k = 1; k < inorder.size(); k++) {
                int cur = inorder.get(k).val;
                assertTrue(cur > prev);
                prev = cur;
            }
        }
    }

    /**
     * 中序遍历
     *
     * @param root 根节点
     * @return 中序遍历序列
     */
    private List<TreeNode> inorder(TreeNode root) {
        List<TreeNode> values = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                // 保存现场 nextPC
                stack.push(cur);
                // 左节点
                cur = cur.left;
            } else {
                // 根节点
                cur = stack.pop();
                values.add(cur);
                // 右节点
                cur = cur.right;
            }
        }
        return values;
    }

}