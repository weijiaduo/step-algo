package com.wjd.structure.tree.bplus;

import java.util.ArrayList;
import java.util.List;

/**
 * B+树内部节点
 *
 * @author weijiaduo
 * @since 2023/1/10
 */
public class BPTInternal<K extends Comparable<K>, V> extends BPTNode<K, V> {

    public BPTInternal(int m) {
        super(m);
    }

    @Override
    public boolean isLeaf() {
        return false;
    }

    /**
     * 节点上溢处理
     * <p>
     * 子树添加新元素后，可能会分裂成新节点，替代原有的子节点
     *
     * @param index 索引
     * @param node  新节点
     * @return 添加后的当前节点
     */
    public BPTNode<K, V> overflow(int index, BPTNode<K, V> node) {
        if (node == null || node.isEmpty()) {
            return this;
        }

        // 还是旧节点，说明子节点没有上溢，无需处理
        BPTNode<K, V> cur = getChild(index);
        if (cur == node) {
            return this;
        }

        // 1. 当前空间足够，可以直接插入新节点
        if (size + node.size() < m) {
            // 新子节点的左子节点替代旧子节点的位置
            setChild(index, node.firstChild());
            for (Entry<K, V> entry : node.entries()) {
                insertEntry(++index, entry);
            }
            return this;
        }

        // 2. 当前空间不够，需要分裂成 3 个节点
        return splitNodes(index, node);
    }

    /**
     * 子节点上溢后，父节点已满，则需要分裂节点，将 1 个节点拆分成 3 个节点
     *
     * @param index   上溢子节点的父元素索引
     * @param newNode 上溢的子节点
     * @return 分裂后新的父节点
     */
    private BPTNode<K, V> splitNodes(int index, BPTNode<K, V> newNode) {
        // 新子节点的左子节点替代旧子节点的位置
        setChild(index, newNode.firstChild());

        int newSize = size + newNode.size();
        List<Entry<K, V>> allEntries = new ArrayList<>(newSize);
        List<Entry<K, V>> curEntries = entries();
        for (int i = 0; i < index && i < size; i++) {
            allEntries.add(curEntries.get(i));
        }
        allEntries.addAll(newNode.entries());
        for (int i = index; i < size; i++) {
            allEntries.add(curEntries.get(i));
        }

        // 根节点
        int mid = newSize / 2;
        BPTInternal<K, V> root = new BPTInternal<>(m);
        for (int i = mid; i <= mid; i++) {
            root.addEntry(allEntries.get(i));
        }
        // 左子节点
        BPTInternal<K, V> left = new BPTInternal<>(m);
        for (int i = 0; i < mid; i++) {
            left.addEntry(allEntries.get(i));
        }
        // 右子节点（和叶子节点不同，右子节点不包括根节点元素）
        BPTInternal<K, V> right = new BPTInternal<>(m);
        for (int i = mid + 1; i < newSize; i++) {
            right.addEntry(allEntries.get(i));
        }

        // 更新边界指针
        left.setChild(0, firstChild());
        right.setChild(0, root.lastChild());
        root.setChild(0, left);
        root.setChild(root.size, right);
        return root;
    }

    /**
     * 节点下溢处理
     * <p>
     * 存在非法子节点时，可能需要对父节点进行下溢操作
     *
     * @param index 父节点索引
     * @return 根节点
     */
    public BPTNode<K, V> underflow(int index) {
        // 验证子节点是否合法，合法就不用下溢
        BPTNode<K, V> cur = getChild(index);
        if (cur == null || cur.isLegal()) {
            return this;
        }

        // 1. 从兄弟节点借一个元素
        BPTNode<K, V> left = null;
        if (index > 0) {
            left = getChild(index - 1);
            if (left != null && left.canBorrow()) {
                return borrowLeft(index);
            }
        }
        BPTNode<K, V> right = null;
        if (index < size) {
            right = getChild(index + 1);
            if (right != null && right.canBorrow()) {
                return borrowRight(index);
            }
        }
        if (left == null && right == null) {
            return this;
        }

        // 2. 合并父元素 + 左右子节点
        // 始终把当前节点作为合并时的右子节点
        return mergeNodes(left != null ? index : index + 1);
    }

    /**
     * 借用左子节点的值
     * <p>
     * 实际就是右旋，将父节点转到右子节点，左子节点的元素转到父节点
     *
     * @param index 父节点索引
     */
    private BPTNode<K, V> borrowLeft(int index) {
        BPTNode<K, V> left = getChild(index - 1);
        BPTNode<K, V> right = getChild(index);
        if (left.isLeaf()) {
            // 叶子节点，父元素不是数据，不能旋转到叶子节点里面
            // 左叶子节点转移到右叶子节点（叶子节点的子节点都为空，不需要更新）
            Entry<K, V> leftEntry = left.getEntry(left.size);
            left.removeEntry(left.size);
            right.insertEntry(1, leftEntry);
            // 更新父节点索引
            setEntry(index, new Entry<>(leftEntry.key, null));
            setChild(index, right);
        } else {
            // 内部节点，都是索引元素，直接旋转
            Entry<K, V> parentEntry = getEntry(index);
            Entry<K, V> leftEntry = left.getEntry(left.size);
            BPTNode<K, V> leftRight = left.lastChild();

            // 父节点转到右子节点
            right.insertEntry(1, parentEntry);
            right.setChild(1, right.firstChild());
            // 左子节点转到父节点
            left.removeEntry(left.size);
            setEntry(index, leftEntry);
            setChild(index, right);
            right.setChild(0, leftRight);
        }
        return this;
    }

    /**
     * 借用右子节点的值
     * <p>
     * 实际就是左旋，将父元素转到左子节点，右子节点的元素转到父节点
     *
     * @param index 父节点索引
     */
    private BPTNode<K, V> borrowRight(int index) {
        BPTNode<K, V> right = getChild(index + 1);
        BPTNode<K, V> left = getChild(index);
        if (right.isLeaf()) {
            // 叶子节点，父元素不是数据，不能旋转到叶子节点里面
            // 右叶子节点转移到左叶子子节点（叶子节点的子节点都为空，不需要更新）
            Entry<K, V> rightEntry = right.getEntry(1);
            right.removeEntry(1);
            left.addEntry(rightEntry);
            // 更新父节点索引
            Entry<K, V> newRightEntry = right.getEntry(1);
            setEntry(index + 1, new Entry<>(newRightEntry.key, null));
            setChild(index + 1, right);
        } else {
            // 内部节点，都是索引元素，直接旋转
            Entry<K, V> parentEntry = getEntry(index + 1);
            Entry<K, V> rightEntry = right.getEntry(1);
            BPTNode<K, V> rightLeft = right.firstChild();

            // 父节点转到左子节点
            left.addEntry(parentEntry);
            // 右子节点转到父节点
            right.setChild(0, right.getChild(1));
            right.removeEntry(1);
            setEntry(index + 1, rightEntry);
            setChild(index + 1, right);
            left.setChild(left.size, rightLeft);
        }
        return this;
    }

    /**
     * 父节点元素 + 右子节点，全都合并到左子节点
     * <p>
     * 因为父节点元素和右子节点是一一对应的，所以都是按照“父 + 右 --> 左”进行合并
     * <p>
     * 方便同时删除父节点元素和右子节点
     *
     * @param index 父节点索引
     */
    private BPTNode<K, V> mergeNodes(int index) {
        BPTNode<K, V> left = getChild(index - 1);
        BPTNode<K, V> right = getChild(index);

        // 父节点元素合并到左子节点
        if (!left.isLeaf()) {
            // 子节点是叶子节点时，合并不要加上父元素
            Entry<K, V> parentEntry = getEntry(index);
            left.addEntry(parentEntry);
        }

        // 右子节点合并到左子节点
        left.setChild(left.size, right.firstChild());
        for (Entry<K, V> entry : right.entries()) {
            left.addEntry(entry);
        }

        // 移除父节点元素
        removeEntry(index);

        // 更新叶子节点双向链表的指针
        if (left.isLeaf()) {
            BPTLeaf<K, V> leftLeaf = (BPTLeaf<K, V>) left;
            BPTLeaf<K, V> rightLeaf = (BPTLeaf<K, V>) right;
            BPTLeaf<K, V> rightNext = rightLeaf.getNext();
            if (rightNext != null) {
                rightNext.setPrev(leftLeaf);
            }
            leftLeaf.setNext(rightNext);
        }
        return this;
    }

}
