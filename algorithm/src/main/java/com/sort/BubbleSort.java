package com.sort;

import com.util.ArrayPrintln;

//冒泡排序
public class BubbleSort {

    public static int[] bubbleSort(int[] array) {
        if (array == null || array.length == 0) {
            return null;
        }
        int length = array.length;
        boolean flag = false;
        for (int i = 0; i < length; i++) {
            int size = length - i - 1;
            //每一轮都会把未排序的数字中最值往后走
            for (int j = 0; j < size; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    flag = true;
                }
            }
            if (!flag) {
                return array;
            }
            flag = false;
        }
        return array;
    }

    public static void main(String[] args) {
        int a[] = ArrayPrintln.randomArray();
        ArrayPrintln.Array_Println(a);
        ArrayPrintln.Array_Println(BubbleSort.bubbleSort(a));
    }

}
