package com.algorithm.map;

/**
 * @author li-yuanwen
 * 有序键值对 数据结构
 */
public interface SortMap<K extends Comparable<K>, V> extends IndexMap<K, V> {

    /**
     * 返回最小键值
     * @return 最小键值
     */
    K min();

    /**
     * 返回最大键值
     * @return 最大键值
     */
    K max();

    /**
     * 删除最小键值
     * @return 最小键值
     */
    K deleteMin();

    /**
     * 删除最大键值
     * @return 最大键值
     */
    K deleteMax();

    /**
     * 获取小于等于k的最大键值
     * @param k 键值
     * @return 小于等于k的最大键值
     */
    K floor(K k);

    /**
     *获取大于等于k的最大键值
     * @param k 键值
     * @return 大于等于k的最大键值
     */
    K ceiling(K k);

    /**
     * 获取小于k的键值数量
     * @param k 键值
     * @return 小于k的键值数量
     */
    int rank(K k);

    /**
     * 获取排名为index的键值
     * @param index 排名
     * @return 排名为index的键值
     */
    K select(int index);

}
