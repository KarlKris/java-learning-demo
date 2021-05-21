package com.algorithm.queue;

/**
 * @author li-yuanwen
 */
public class MinQueue<I extends Comparable<I>> extends AbstractQueue<I> {

    public MinQueue(I[] items) {
        super(items);
    }

    public MinQueue(int size) {
        super(size);
    }

    @Override
    public I get() {
        return items[1];
    }

    @Override
    public I del() {
        I min = items[1];
        exch(1, size--);
        // help gc
        items[size + 1] = null;
        sink(1);
        return min;
    }

    @Override
    protected void swim(int index) {
        // 数组元素从下标1开始存储
        while (index > 1 && greater(index / 2, index)) {
            exch(index / 2, index);
            index /= 2;
        }
    }

    @Override
    protected void sink(int index) {
        if (index <= 0) {
            return;
        }
        while (2 * index <= size) {
            int j = 2 * index;
            // 找到子节点最小的结点下沉
            if (j < size && greater(j, j + 1)) {
                j++;
            }

            if (!greater(index, j)) {
                break;
            }

            exch(index, j);
            index = j;
        }
    }

    // is pq[1..n] a min heap?
    private boolean isMinHeap() {
        for (int i = 1; i <= size; i++) {
            if (items[i] == null) {
                return false;
            }
        }
        for (int i = size + 1; i < items.length; i++) {
            if (items[i] != null) {
                return false;
            }
        }
        if (items[0] != null) {
            return false;
        }
        return isMinHeapOrdered(1);
    }

    // is subtree of pq[1..n] rooted at k a min heap?
    private boolean isMinHeapOrdered(int k) {
        if (k > size) {
            return true;
        }

        int left = 2 * k;
        int right = 2 * k + 1;
        if (left <= size && greater(k, left)) {
            return false;
        }

        if (right <= size && greater(k, right)) {
            return false;
        }
        return isMinHeapOrdered(left) && isMinHeapOrdered(right);
    }

    protected boolean greater(int i, int j) {
        return items[i].compareTo(items[j]) > 0;
    }
}
