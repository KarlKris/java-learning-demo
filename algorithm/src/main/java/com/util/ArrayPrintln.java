package com.util;

/*
 * 打印数组元素
 */
public class ArrayPrintln {

	public static int SIZE=20;
	
	public static void Array_Println(int num[]){
		int size=num.length;
		for(int i=0;i<size;i++){
			System.out.print(num[i]+" ");
		}
		System.out.println();
	}
	
	//随机生成size为100的 范围1-100的数组
	public static int[] randomArray(){
		int a[]=new int[SIZE];
		int i=0;
		while(i<SIZE){
			a[i]=(int) (Math.random()*100+1);
			i++;
		}
		return a;
	}

}
