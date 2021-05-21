package com.algorithm.queue;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.RandomUtil;
import com.algorithm.sort.AbstractSort;
import com.algorithm.sort.QuickSort;

import java.lang.reflect.Array;

/**
 * @author li-yuanwen
 * 优先队列抽象基类---基于二叉堆（父节点一定大于子节点的完全二叉树）的数据结构
 * 节点k的父节点一定是k/2
 * 节点k的子节点一定是2k和2k+1
 */
public abstract class AbstractQueue<I extends Comparable<I>> implements Queue<I> {

    /** 元素对象 **/
    protected I[] items;
    /** 数量 **/
    protected int size;

    public AbstractQueue(I[] items) {
        this.items = (I[]) Array.newInstance(items[0].getClass(), items.length + 1);
        for (I i : items) {
            insert(i);
        }
    }

    public AbstractQueue(int size) {
        this.items = (I[]) new Object[size];
    }

    @Override
    public void insert(I item) {
        // 数组元素从下标1开始存储
        items[++size] = item;
        swim(size);
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    /** 由下至上上浮保证二叉堆性质 **/
    protected void swim(int index) {
        // 数组元素从下标1开始存储
        while (index > 1 && less(index / 2, index)) {
            exch(index / 2, index);
            index /= 2;
        }
    }

    /** 由上至下下沉保证二叉堆性质 **/
    protected void sink(int index) {
        if (index <= 0) {
            return;
        }
        while (index < size) {
            int j = 2 * index;
            // 找到子节点最小的结点下沉
            if (j < size && less(j, j + 1)) {
                j++;
            }

            if (!less(index, j)) {
                break;
            }

            exch(index, j);
            index = j;
        }
    }

    protected boolean less(int i, int j) {
        return items[i].compareTo(items[j]) < 0;
    }

    protected void exch(int i, int j) {
        I temp = items[i];
        items[i] = items[j];
        items[j] = temp;
    }

    public static void main(String[] args) {
        for (int z = 0; z < 1000; z++) {
            int length = 5000;
            Integer[] items = new Integer[length];
            for (int i = 0; i < length; i++) {
                items[i] = RandomUtil.randomInt(1000);
            }
            AbstractSort<Integer> s1 = new QuickSort<>();
            Queue<Integer> minQueue = new MinQueue<>(items);

            s1.sort(items);
            for (int i = 0; i < length; i++) {
                Integer j = minQueue.del();
                if (!j.equals(items[i])) {
                    break;
                }
            }
        }
    }
}
