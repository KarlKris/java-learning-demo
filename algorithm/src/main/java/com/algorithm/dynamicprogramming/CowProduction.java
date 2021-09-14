package com.algorithm.dynamicprogramming;

/**
 * @author li-yuanwen
 * 母牛生产问题：
 * 假设农场中成熟的母牛每年都会生 1 头小母牛，并且永远不会死。
 * 第一年有 1 只小母牛，从第二年开始，母牛开始生小母牛。
 * 每只小母牛 3 年之后成熟又可以生小母牛。
 * 给定整数 N，求 N 年后牛的数量。
 * 设f(i)为第i年成熟的母牛数量 g(i)为第i年生的小母牛数量, t(i)为第i年的母牛数量,则存在等式
 * f(i) = f(i-1) + g(i-3)
 * g(i) = f(i)
 * f(i) = f(i-1) + f(i-3)
 * 则总量为 t(i) = f(i) + g(i) + g(i-1) + g(i-2) = 2f(i) + f(i-2) + f(i-1)
 */
public class CowProduction {


    public static int cowNum(int year) {
        return 2 * f(year) + f(year - 1) + f(year - 2) ;
    }


    private static int f(int year) {
        if (year <= 3) {
            return 0;
        }
        // 3-6年只有一头成熟母牛
        if (year <= 6) {
            return 1;
        }
        return f(year - 1) + f(year - 3);
    }


    public static void main(String[] args) {
        System.out.println(cowNum(10));
    }

}
