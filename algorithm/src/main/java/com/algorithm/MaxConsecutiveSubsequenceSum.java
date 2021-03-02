package com.algorithm;

import common.ArrayUtils;

/**
 * @Description 最大连续子序列之和问题
 * @Author li-yuanwen
 * @Date 2021/3/2 10:55
 */
public class MaxConsecutiveSubsequenceSum {


    /**
     * 暴力求解
     **/
    public int[] directlyCalculating(int[] seq) {
        int maxSum = 0;
        int[] res = new int[1];

        int length = seq.length;
        for (int i = 0; i < length - 1; i++) {
            if (i == 0) {
                maxSum = seq[0];
                System.arraycopy(seq, 0, res, 0, 1);
            }
            for (int j = 1; j < length; j++) {
                int sum = 0;
                for (int z = i; z <= j; z++) {
                    sum += seq[z];
                }

                if (sum > maxSum) {
                    res = new int[j - i + 1];
                    System.arraycopy(seq, i, res, 0, j - i + 1);

                    maxSum = sum;
                }
            }
        }

        return res;
    }


    public static void main(String[] args) {
//        int[] array = ArrayUtils.randomArray(10);
        int[] array = {56, 15, 84, 63, 87, 58, 3, -8, -3, 90};
        ArrayUtils.Array_Println(array);

        MaxConsecutiveSubsequenceSum sum = new MaxConsecutiveSubsequenceSum();

        System.out.println("---------------------------------------");
        ArrayUtils.Array_Println(sum.directlyCalculating(array));

    }


}
