package com.sort;

import com.util.ArrayPrintln;

//插入排序，类似于抽牌
//时间复杂度O(n*n)
public class InsertionSort {
	
	//排序后的数放到新的数组并返回
	public static int[] NewArray_Insertion_Sort(int num[]){
		int size=num.length;
		int a[]=new int[size];
		for(int i=0;i<size;i++){
            int b=num[i];
            //开始抽牌
			if(i==0){
				//第一次抽牌
				a[i]=b;
			}else{
				for(int j=i-1;j>=0;j--){
					//a[j+1]与a[j]比较
					int c=a[j];
					if(b<c){
						//a[j+1]<a[j],交换位置
						a[j]=b;
						a[j+1]=c;
					}else{
						//a[j+1]>a[j],抽的牌位置不变
						a[j+1]=b;
						break;
					}
				}
			}
		}
		
		return a;
	}
	
	//在原数组中进行排序并返回
	public static int[] LocalArray_Insertion_Sort(int[] num){
		int length=num.length;
		for(int i=1;i<length;i++){
			int temp=num[i];
			while(temp<num[i-1]){
				num[i]=num[i-1];
				num[i-1]=temp;	
				//如果数组的第一个数比第二个数大就要考虑数组越界问题
				if((i-1)<=0){
					break;
				}
				i--;
			}
		}
		return num;
	}
	
	
	public static void main(String[] args) {
		int a[]= ArrayPrintln.randomArray();
		ArrayPrintln.Array_Println(a);
		
		long startTime1=System.nanoTime();
		ArrayPrintln.Array_Println(NewArray_Insertion_Sort(a));
		long endTime1=System.nanoTime();
		//输出程序运行时间
		System.out.println("NewArray_Insertion_Sort(a)程序运行时间：" + (endTime1 - startTime1) + "ns"); 
		
		long startTime2=System.nanoTime();
		ArrayPrintln.Array_Println(LocalArray_Insertion_Sort(a));
		long endTime2=System.nanoTime();
		//输出程序运行时间
		System.out.println("LocalArray_Insertion_Sort(a)程序运行时间：" + (endTime2 - startTime2) + "ns"); 
	}

}
