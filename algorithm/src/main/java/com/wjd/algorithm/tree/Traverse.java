package com.wjd.algorithm.tree;

import java.util.List;

/**
 * 树节点遍历接口
 *
 * @author weijiaduo
 * @since 2022/12/10
 */
public interface Traverse<T> {

    /**
     * 遍历
     *
     * @param node 节点
     * @return 节点列表
     */
    List<T> traverse(T node);

}
