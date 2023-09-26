package com.wjd.structure.skiplist;

/**
 * 跳表接口
 *
 * @author weijiaduo
 * @since 2022/7/28
 */
public interface SkipList<T extends Comparable<T>> {

    /**
     * 搜索指定值是否存在
     *
     * @param value 指定值
     * @return true存在/false不存在
     */
    boolean search(T value);

    /**
     * 添加新值
     *
     * @param value 新值
     */
    void add(T value);

    /**
     * 删除指定值
     *
     * @param value 指定值
     * @return true删除成功/false删除失败
     */
    boolean erase(T value);

}
