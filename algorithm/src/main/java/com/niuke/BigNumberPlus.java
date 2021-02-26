package com.niuke;

/**
 * @Description 大数加法
 * <p>
 * 以字符串的形式读入两个数字，编写一个函数计算它们的和，以字符串形式返回。
 * （字符串长度不大于100000，保证字符串仅由'0'~'9'这10种字符组成）
 * @Author li-yuanwen
 * @Date 2021/2/24 19:33
 */
public class BigNumberPlus {

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * 计算两个数之和
     * <p>
     * 该思路是：
     * <p>
     * 1.反转两个字符串，便于从低位到高位相加和最高位的进位导致和的位数增加；
     * 2.对齐两个字符串，即短字符串的高位用‘0’补齐，便于后面的相加；
     * 3.从头遍历，把两个正整数的每一位都相加，并加上进位；
     * 4.最高位有进位则补上进位；
     * 5.逆序输出；
     *
     * @param s string字符串 表示第一个整数
     * @param t string字符串 表示第二个整数
     * @return string字符串
     */
    public String solve(String s, String t) {
        // write code here
        //反转字符串
        StringBuffer n1 = new StringBuffer(s).reverse();
        StringBuffer n2 = new StringBuffer(t).reverse();
        int l1 = n1.length();
        int l2 = n2.length();
        int maxL = l1 > l2 ? l1 : l2;

        //补齐0
        if (l1 < l2) {
            for (int i = l1; i < l2; i++) {
                n1.append("0");
            }
        } else {
            for (int i = l2; i < l1; i++) {
                n2.append("0");
            }
        }

        StringBuffer res = new StringBuffer();//存放的结果
        int c = 0;//进位

        for (int i = 0; i < maxL; i++) {
            int nSum = Integer.parseInt(n1.charAt(i) + "") + Integer.parseInt(n2.charAt(i) + "") + c;
            int ap = nSum % 10;
            res.append(ap);
            c = nSum / 10;
        }
        if (c > 0) {
            res.append(c);
        }

        return res.reverse().toString();
    }

    public static void main(String[] args) {
        BigNumberPlus plus = new BigNumberPlus();
        System.out.println(plus.solve("79", "89"));
    }
}
