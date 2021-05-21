package com.algorithm.sort;

import cn.hutool.core.util.ArrayUtil;

/**
 * @author li-yuanwen
 * 快速排序：尽可能的构造有序子数组，再利用
 * 时间复杂度:0(nlgn)
 */
public class QuickSort<I extends Comparable<I>> extends AbstractSort<I> {

    @Override
    protected void doSort(I[] items) {
        // 防止数组已基本有序而导致算法速度下降
        ArrayUtil.shuffle(items);
        quickSort(items, 0, items.length - 1);
    }

    private void quickSort(I[] items, int fromIndex, int toIndex) {
        if (fromIndex > toIndex) {
            return;
        }

        // 左右指针
        int left = fromIndex;
        int right = toIndex;
        // 单次中尽可能的排序
        while (left != right) {
            //从右往左找比基数小的数
            while (right > left && less(items[fromIndex], items[right])) {
                right--;
            }

            //从左往右找比基数大的数
            while (left < right && !less(items[fromIndex], items[left])) {
                left++;
            }

            if (left < right) {
                exch(items, left, right);
            }
        }

        // 交换基数位置
        exch(items, left, fromIndex);
        // 左边排序
        quickSort(items, fromIndex, left - 1);
        // 右边排序
        quickSort(items, left + 1, toIndex);
    }

    @Override
    protected String getSortType() {
        return "QuickSort ";
    }
}
