package com.algorithm.queue;

/**
 * @author li-yuanwen
 * 优先队列接口API接口
 */
public interface Queue<I extends Comparable<I>> {

    /**
     * 插入队列
     * @param item 元素
     */
    void insert(I item);

    /**
     * 返回元素
     * @return 元素
     */
    I get();

    /**
     * 删除并返回元素
     * @return 元素
     */
    I del();

    /**
     * 返回是否是空队列
     * @return true 队列为空
     */
    boolean isEmpty();

    /**
     * 返回队列元素数量
     * @return 队列元素数量
     */
    int size();

}
