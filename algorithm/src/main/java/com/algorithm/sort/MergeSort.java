package com.algorithm.sort;

/**
 * @author li-yuanwen
 * 归并排序
 * 时间复杂度：0(nlgn)
 */
public class MergeSort<I extends Comparable<I>> extends AbstractSort<I> {

    @Override
    protected void doSort(I[] items) {
        div(items, 0, items.length - 1);
    }

    /**
     * 划分
     * @param items 待排序数组
     * @param min   待排序起始下标
     * @param max   待排序结束下标
     */
    private void div(I[] items, int min, int max) {
        if (max > min) {
            int mid = (max + min) >> 1;

            div(items, min, mid);
            div(items, mid + 1, max);
            merge(items, min, mid, max);
        }
    }

    /**
     * 使用插入排序来合并小数组
     * @param items
     * @param min
     * @param max
     */
    private void divWithInsertSort(I[] items, int min, int max) {
        int k = (int) Math.log(max);
        if (max - min > k) {
            int mid = (max + min) >> 1;

            div(items, min, mid);
            div(items, mid + 1, max);
            merge(items, min, mid, max);
        } else {
            insertSort(items, min, max);
        }
    }

    /**
     * 合并
     * @param items 待排序数组
     * @param min   左边已排序数组下标起始
     * @param mid   左边已排序数组下标末尾
     * @param max   右边已排序数组下标末尾
     */
    private void merge(I[] items, int min, int mid, int max) {
        Object[] temp = new Object[max - min + 1];
        int i = min;
        int j = mid + 1;

        int index = 0;
        while (i <= mid && j <= max) {
            if (less(items[i], items[j])) {
                temp[index++] = items[i++];
            } else {
                temp[index++] = items[j++];
            }
        }

        while (i <= mid) {
            temp[index++] = items[i++];
        }

        while (j <= max) {
            temp[index++] = items[j++];
        }

        for (int z = 0; z < temp.length; z++) {
            items[z + min] = (I) temp[z];
        }
    }

    /**
     * 插入排序合并
     * @param items
     * @param min
     * @param max
     */
    private void insertSort(I[] items, int min, int max) {
        int length = max - min + 1;
        for (int i = 1; i < length; i++) {
            for (int j = i; j > 0 && less(items[j + min], items[j + min - 1]); j--) {
                exch(items, j + min, j + min - 1);
            }
        }
    }

    @Override
    protected String getSortType() {
        return "MergeSort ";
    }
}
