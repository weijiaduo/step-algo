package com.wjd.algorithm.tree;

/**
 * 树构建类
 *
 * @author weijiaduo
 * @since 2022/8/28
 */
public interface TreeBuilder<T, V> {

    T build(V values);

}
