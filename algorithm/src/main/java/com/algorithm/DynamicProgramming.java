package com.algorithm;

//动态规划算法--钢条切割问题---还未解决
//n必须小于或等于price.length
public class DynamicProgramming {

    public static int[] price = {0, 1, 5, 8, 9, 10, 17, 17, 20, 24, 30};//长度为i的钢条价值为price[i];

    public static int[] Max_value = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};

    public static int Cut(int[] p, int n) {
        if (Max_value[n] != -1) {
            return Max_value[n];
        }
        int value = Integer.MIN_VALUE;
        if (n == 0) {
            value = p[0];
        }
        for (int i = 1; i <= n; i++) {
            if (i > price.length - 1) {
                for (int j = 1; j <= n - 10; j++) {
                    value = Math.max(value, p[j] + Cut(p, n - j));
                }
            } else {
                value = Math.max(value, p[i] + Cut(p, n - i));
            }
        }
        Max_value[n] = value;
        return value;
    }

    public static void main(String[] args) {
        int n = 5;
        System.out.println("长度为" + n + "的钢条价值最高为：");
        System.out.println(Cut(DynamicProgramming.price, n));
    }

}
