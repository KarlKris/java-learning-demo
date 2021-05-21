package com.algorithm.sort;

/**
 * @author li-yuanwen
 * 选择排序
 * 时间复杂度：0(n^2)
 */
public class SelectionSort<I extends Comparable<I>> extends AbstractSort<I> {

    @Override
    protected void doSort(I[] items) {
        int length = items.length;
        for (int i = 0; i < length; i++) {
            int targetIndex = i;
            for (int j = i + 1; j < length; j++) {
                if (!less(items[targetIndex], items[j])) {
                    targetIndex = j;
                }
            }
            exch(items, i, targetIndex);
        }
    }

    @Override
    protected String getSortType() {
        return "SelectionSort ";
    }
}
