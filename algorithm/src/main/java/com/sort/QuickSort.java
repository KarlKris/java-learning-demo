package com.sort;

import com.util.ArrayPrintln;

//快速排序
public class QuickSort {
	
	public static int[] sort(int[] array){
		if(array == null || array.length ==0){
			return null;
		}
		int left=0,right=array.length-1;
		quickSort(array, left, right);
		return array;
	}
	
	public static void quickSort(int[] array,int x,int y){
		//临界情况
		if(x>=y){
			return;
		}
		int base=array[x];
		int left=x;
		int right=y;
		while(left!=right){
			 //从右往左找比基数小的数
			 //取等的意思就是为了防止有跟基数相等的数并且左边还存在比基数小的数时错误交换
			 while(left<right && array[right]>base){
				 right--;
			 }
			 //从左往右找比基数大的数
			 //取等的意思就是为了防止array[left]==base(就是一开始的情况)时，左指针位置错误
			 while(left<right && array[left]<=base){
				 left++;
			 }
			 if(left<right){
				 swap(array, left, right);
			 }
		}
		//左右指针相遇时，直接于基数交换
		//这也是为什么要让右指针先动的原因（基数是最左边的数）
		//因为这样才能防止当基数在某段递归中的有效数组钟是最小值时
		//出现基数不与自身交换的错误交换，因为左指针先移动，会移动到错误的位置
		//（例如 3，4，5时，左指针会移动到5的位置，从而3跟5交换，这是错误的，右指针先移动则不会出现这个错误）
		swap(array, x, left);
		quickSort(array, x, left-1);
		quickSort(array, right+1, y);
		
	}
	
	public static void swap(int[] array,int x,int y){
		int temp=array[x];
		array[x]=array[y];
		array[y]=temp;
	}
	
	public static void main(String[] args) {
		int[] a= ArrayPrintln.randomArray();
		ArrayPrintln.Array_Println(a);
		ArrayPrintln.Array_Println(QuickSort.sort(a));
	}

}
