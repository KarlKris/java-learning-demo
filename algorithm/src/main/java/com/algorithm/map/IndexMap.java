package com.algorithm.map;

/**
 * @author li-yuanwen
 * 自定义键值对数据结构api
 */
public interface IndexMap<K, V> {

    /**
     * 增加键值对
     * @param k 键值
     * @param v 值
     */
    void put(K k, V v);

    /**
     * 获取键值对应的值 or null
     * @param k 键值
     * @return 键值对应的值 or null
     */
    V get(K k);

    /**
     * 删除键值对应的值
     * @param k 键值
     * @return 键值对应的值 or null
     */
    V delete(K k);

    /**
     * 是否包含键值
     * @param k 键值
     * @return 存在键值 true
     */
    boolean containKey(K k);

    /**
     * map中是否存在键值对
     * @return 存在键值对 true
     */
    boolean isEmpty();

    /**
     * 获取键值对数量
     * @return 键值对数量
     */
    int size();

    /**
     * 获取所有键值的迭代对象
     * @return 所有键值
     */
    Iterable<K> keys();

}
