package com.wjd.structure.hashtable;

/**
 * @author weijiaduo
 * @since 2022/9/24
 */
public interface HashTable {

    /**
     * 添加键值对
     *
     * @param key   关键字
     * @param value 值
     */
    void put(int key, int value);

    /**
     * 移除指定值
     *
     * @param key 关键字
     * @return 对应值/-1表示不存在
     */
    int remove(int key);

    /**
     * 获取指定值
     *
     * @param key 关键字
     * @return 对应值/-1表示不存在
     */
    int get(int key);

    /**
     * 是否包含指定值
     *
     * @param key 关键字
     * @return true/false
     */
    boolean contains(int key);

    /**
     * @return 当前元素数量
     */
    int size();

}
