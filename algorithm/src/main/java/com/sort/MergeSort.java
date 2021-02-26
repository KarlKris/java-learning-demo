package com.sort;

import com.util.ArrayPrintln;

//分治法--归并排序
/*
 * 总结：
 * 分解待排序的n个元素的序列成各具n/2个元素的两个子序列
 * 归并排序递归地排序两个子序列
 * 合并两个已排序的子序列以产生已排序的答案
 */
public class MergeSort {

    //归并
    public static int[] merge_Sort(int[] num, int min, int max) {
        int mid = (min + max) / 2;
        if (max > min) {
            //归并排序左半部分
            merge_Sort(num, min, mid);
            //归并排序右半部分
            merge_Sort(num, mid + 1, max);
            //将左右两部分归并排序
            merge(num, min, mid, max);
        }
        return num;
    }

    public static void merge(int[] num, int min, int mid, int max) {
        int[] temp = new int[max + 1];//临时存放点
        int size = min;
        int p = min;
        int q = mid + 1;
        int r = max;

        //左右两段有序序列，开始逐个比较大小。直到一方序列为空
        while (p <= mid && q <= max) {
            if (num[p] < num[q]) {
                temp[size++] = num[p++];
            } else {
                temp[size++] = num[q++];
            }
        }

        //若上面右边序列先为空，则将左边序列依次添加进临时存放点
        while (p <= mid) {
            temp[size++] = num[p++];
        }

        //若上面左边序列先为空，则将右边序列依次添加进临时存放点
        while (q <= max) {
            temp[size++] = num[q++];
        }

        //将临时存放点的数据赋值给num原数组
        //num=temp;
        //不可以这样赋值，因为这样相当于给num重新分配一个内存地址
        //这样外部的num数组依旧是原先的地址
        //下面这种写法是不改变地址，但改变地址指向的内容
        for (; min <= max; min++) {
            num[min] = temp[min];
        }
    }


    public static void main(String[] args) {
        int a[] = ArrayPrintln.randomArray();
        ArrayPrintln.Array_Println(a);

        ArrayPrintln.Array_Println(merge_Sort(a, 0, a.length - 1));

    }

}
