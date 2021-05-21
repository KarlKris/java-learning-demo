package com.algorithm.sort;

import cn.hutool.core.util.RandomUtil;

/**
 * @author li-yuanwen
 * 抽象排序基类
 */
public abstract class AbstractSort<I extends Comparable<I>> {

    /**
     * 排序(升序)
     * @param items 待排序元素数组
     */
    public long sort(I[] items) {
        long start = System.nanoTime();
        doSort(items);
        long end = System.nanoTime();
        assert isSorted(items);
        System.out.println(getSortType() + "排序[" + items.length + "]个元素耗时：" + (end - start));
        return end - start;
    }

    /**
     * 排序(升序)
     * @param items 待排序元素数组
     */
    protected abstract void doSort(I[] items);

    /**
     * 比较元素
     * @param a 对象a
     * @param b 对象b
     * @return a.compareTo(b)
     */
    protected boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    /**
     * 将元素交换位置
     * @param items 数组
     * @param i     交换下标
     * @param j     交换下标
     */
    protected void exch(I[] items, int i, int j) {
        I temp = items[i];
        items[i] = items[j];
        items[j] = temp;
    }

    protected void show(I[] items) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (int i = 0; i < items.length; i++) {
            stringBuilder.append(items[i]);
            if (i != items.length - 1) {
                stringBuilder.append(",");
            }
        }
        stringBuilder.append("]");
        System.out.println(stringBuilder.toString());
    }

    protected boolean isSorted(I[] items) {
        for (int i = 1; i < items.length; i++) {
            if (less(items[i], items[i - 1])) {
                return false;
            }
        }
        return true;
    }

    /**
     * @return 排序类型
     */
    protected abstract String getSortType();

    public static void testSorted() {
        int length = 5000;
        Integer[] sorteds = new Integer[length];
        for (int i = 0; i < length; i++) {
            sorteds[i] = i + 1;
        }
        long selTime = 0;
        long insTime = 0;
        long sheTime = 0;
        long merTime = 0;
        long quiTime = 0;
        long heaTime = 0;
        AbstractSort<Integer> s2 = new InsertSort<>();
        AbstractSort<Integer> s1 = new SelectionSort<>();
        AbstractSort<Integer> s3 = new ShellSort<>();
        AbstractSort<Integer> s4 = new MergeSort<>();
        AbstractSort<Integer> s5 = new QuickSort<>();
        AbstractSort<Integer> s6 = new HeapSort<>();
        for (int j = 0; j < 5000; j++) {
            insTime += s2.sort(sorteds);
            selTime += s1.sort(sorteds);
            sheTime += s3.sort(sorteds);
            merTime += s4.sort(sorteds);
            quiTime += s5.sort(sorteds);
            heaTime += s6.sort(sorteds);
        }
        System.out.println("插入排序5k数据量平均耗时:" + (insTime / 5000));
        System.out.println("选择排序5k数据量平均耗时:" + (selTime / 5000));
        System.out.println("希尔排序5k数据量平均耗时:" + (sheTime / 5000));
        System.out.println("归并排序5k数据量平均耗时:" + (merTime / 5000));
        System.out.println("快速排序5k数据量平均耗时:" + (quiTime / 5000));
        System.out.println("堆排序5k数据量平均耗时:" + (heaTime / 5000));
    }

    public static void testRandom() {
        int length = 5000;
        Integer[] items = new Integer[length];
        long selTime = 0;
        long insTime = 0;
        long sheTime = 0;
        long merTime = 0;
        long quiTime = 0;
        long heaTime = 0;
        AbstractSort<Integer> s2 = new InsertSort<>();
        AbstractSort<Integer> s1 = new SelectionSort<>();
        AbstractSort<Integer> s3 = new ShellSort<>();
        AbstractSort<Integer> s4 = new MergeSort<>();
        AbstractSort<Integer> s5 = new QuickSort<>();
        AbstractSort<Integer> s6 = new HeapSort<>();
        for (int j = 0; j < 5000; j++) {
            for (int i = 0; i < length; i++) {
                items[i] = RandomUtil.randomInt(2000);
            }

            insTime += s2.sort(items);

            for (int i = 0; i < length; i++) {
                items[i] = RandomUtil.randomInt(2000);
            }

            selTime += s1.sort(items);

            for (int i = 0; i < length; i++) {
                items[i] = RandomUtil.randomInt(2000);
            }

            sheTime += s3.sort(items);

            for (int i = 0; i < length; i++) {
                items[i] = RandomUtil.randomInt(2000);
            }

            merTime += s4.sort(items);

            for (int i = 0; i < length; i++) {
                items[i] = RandomUtil.randomInt(2000);
            }

            quiTime += s5.sort(items);

            for (int i = 0; i < length; i++) {
                items[i] = RandomUtil.randomInt(2000);
            }

            heaTime += s6.sort(items);
        }

        System.out.println("插入排序5k数据量平均耗时:" + (insTime / 5000));
        System.out.println("选择排序5k数据量平均耗时:" + (selTime / 5000));
        System.out.println("希尔排序5k数据量平均耗时:" + (sheTime / 5000));
        System.out.println("归并排序5k数据量平均耗时:" + (merTime / 5000));
        System.out.println("快速排序5k数据量平均耗时:" + (quiTime / 5000));
        System.out.println("堆排序5k数据量平均耗时:" + (heaTime / 5000));
    }

    public static void main(String[] args) {
//        testSorted();
        testRandom();
    }

}
