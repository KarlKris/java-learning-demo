package com.algorithm.sort;

/**
 * @author li-yuanwen
 * 希尔排序(插入排序的改进版，将元素移动由1改为了h):使数组中任意间隔为h的元素都是有序的。构造基本有序序列，提高插入排序的性能
 * 时间复杂度：0(n^1.5)
 */
public class ShellSort<I extends Comparable<I>> extends AbstractSort<I> {

    @Override
    protected void doSort(I[] items) {
        int length = items.length;
        int h = 1;

        int div = length / 3;
        // 增量序列 1,4,13,40,121...
        while (h < div) {
            h = 3 * h + 1;
        }

        while (h >= 1) {
            for (int i = h; i < length; i++) {
                for (int j = i; j >= h && less(items[j], items[j-h]); j-=h) {
                    exch(items, j, j-h);
                }
            }
            h /= 3;
        }

    }

    @Override
    protected String getSortType() {
        double a = 1;
        char s = '\u0639';
        return "ShellSort ";
    }
}
