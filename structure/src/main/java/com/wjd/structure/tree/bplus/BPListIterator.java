package com.wjd.structure.tree.bplus;

import java.util.Iterator;
import java.util.Map;

/**
 * B+ 树链表迭代器
 *
 * @author weijiaduo
 * @since 2023/11/5
 */
public interface BPListIterator<K, V> extends Iterator<Map.Entry<K, V>> {

    /**
     * 是否有下一个元素
     *
     * @return true/false
     */
    boolean hasNext();

    /**
     * 下一个元素
     *
     * @return 下一个元素
     */
    Map.Entry<K, V> next();

    /**
     * 是否有上一个元素
     *
     * @return true/false
     */
    boolean hasPrev();

    /**
     * 上一个元素
     *
     * @return 上一个元素
     */
    Map.Entry<K, V> prev();

}
