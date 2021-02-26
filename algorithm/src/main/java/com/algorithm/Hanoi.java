package com.algorithm;

//汉诺塔算法 递归排序
/*
 * 总结：
 * 递归处理数学归纳法的的问题
 * 将n个不可处理的问题慢慢递归为
 * 已知条件可处理的问题
 * 这里的已知条件是
 * 若只有一个盘子，则直接放到目标柱(就是if(n==1)所处理的样子)
 * 若有两个盘子，则先将最上边的盘子放到辅助柱
 * 然后将第二个盘子放到目标柱
 * 最后将第一个盘子放到目标柱
 * 以此类推
 * 若有n个盘子，则先将n-1盘子放到辅助柱
 * 再将第n个盘子放到目标柱
 * 最后再将n-1个盘子放到目标柱
 */
public class Hanoi {

    //n个盘子  原柱子a 目标柱子c  辅助柱子b
    public void move(int n, char a, char b, char c) {

        if (n == 1) {
            System.out.println("盘" + n + " 由 " + a + " 移至 " + c);
        } else {
            //将n-1个盘从A柱移到B柱
            move(n - 1, a, c, b);
            System.out.println("盘" + n + " 由 " + a + " 移至 " + c);//将第n个盘子放到目标柱
            //将n-1个盘从B柱移到C柱
            move(n - 1, b, a, c);
        }
    }

    public static void main(String[] args) {
        Hanoi h = new Hanoi();
        h.move(4, 'A', 'B', 'C');
    }

}
