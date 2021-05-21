package com.algorithm.sort;

/**
 * @author li-yuanwen
 * 堆排序
 * a.将无需序列构建成一个堆，根据升序降序需求选择大顶堆或小顶堆;
 * b.将堆顶元素与末尾元素交换，将最大元素"沉"到数组末端;
 * c.重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素，反复执行调整+交换步骤，直到整个序列有序。
 * 时间复杂度：0(nlgn)
 */
public class HeapSort<I extends Comparable<I>> extends AbstractSort<I> {

    @Override
    protected void doSort(I[] items) {
        int length = items.length;
        // 构造大顶堆（升序）
        for (int i = (length / 2) - 1; i >= 0; i--) {
            sink(items, i, length);
        }
        // 将堆顶元素与末尾元素进行交换，使末尾元素最大
        while (length > 0) {
            exch(items, 0, --length);
            sink(items, 0, length);
        }

    }

    protected void sink(I[] items, int index, int length) {
        while (2 * index + 1 < length) {
            int k = 2 * index + 1;
            // 大顶堆,将大数上浮
            if (k + 1 < length && less(items[k], items[k + 1])) {
                k++;
            }

            if (!less(items[index],items[k])) {
                break;
            }

            exch(items, index, k);
            index = k;
        }
    }

    @Override
    protected String getSortType() {
        return "HeapSort";
    }
}
