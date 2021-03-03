package com.niuke;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 输入一个字符串, 按字典序打印出该字符串中字符的所有排列
 * 例如输入字符串abc,则按字典序打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。
 * @Author li-yuanwen
 * @Date 2021/3/2 19:31
 */
public class Permutation {

    public List<String> permutation(String str) {
        List<String> res = new ArrayList<>();

        char[] charArray = str.toCharArray();

        doPermutation(charArray, 0, res);

        return res;
    }


    private void doPermutation(char[] chars, int i, List<String> list) {
        if (i == chars.length - 1) {
            list.add(String.valueOf(chars));
        } else {
            for (int j = i; j < chars.length; j++) {
                if (i == j || chars[i] != chars[j]) {
                    swap(chars, i, j);
                    doPermutation(chars, i + 1, list);
                    swap(chars, i, j);
                }

            }
        }
    }

    private void swap(char[] array, int from, int to) {
        char t = array[from];
        array[from] = array[to];
        array[to] = t;
    }

    public static void main(String[] args) {
        String str = "abcdefg";

        Permutation permutation = new Permutation();
        List<String> list = permutation.permutation(str);
        System.out.println(list.size());
    }

}
