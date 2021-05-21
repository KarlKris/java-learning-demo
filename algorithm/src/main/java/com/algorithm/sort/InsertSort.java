package com.algorithm.sort;

/**
 * @author li-yuanwen
 * 插入排序
 */
public class InsertSort<I extends Comparable<I>> extends AbstractSort<I> {

    @Override
    protected void doSort(I[] items) {
        for (int i = 1; i < items.length; i++) {
            for (int j = i; j > 0 && less(items[j], items[j - 1]); j--) {
                exch(items, j - 1, j);
            }
        }
    }

    @Override
    protected String getSortType() {
        return "InsertSort ";
    }
}
