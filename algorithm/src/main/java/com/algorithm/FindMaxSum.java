package com.algorithm;

/**
 * @author li-yuanwen
 * 给定一组数据（数均大于0）中，取不相邻的数最大和
 */
public class FindMaxSum {


    public int findMaxSum(int[] values) {
        if (values == null) {
            return 0;
        }
        int length = values.length;
        if (length == 1) {
            return values[0];
        }
        if (length == 2) {
            return Math.max(values[0], values[1]);
        }

        int[] cache = new int[length];
        int left = doFindMaxSum(values, 2, cache) + values[0];
        int right = doFindMaxSum(values, 3, cache) + values[1];
        return Math.max(left, right);
    }

    private int doFindMaxSum(int[] values, int index, int[] cache) {
        int length = values.length;
        if (index >= length) {
            return 0;
        }

        int c = cache[length-index];
        if (c > 0) {
            return c;
        }

        int left = doFindMaxSum(values, index + 2, cache) + values[index];
        int right = doFindMaxSum(values, index + 1, cache);
        int max = Math.max(left, right);
        cache[length - index] = max;

        return max;
    }

    public static void main(String[] args) {
        FindMaxSum sum = new FindMaxSum();

        int[] values = {98, 1, 2, 8, 9, 4};
        int maxSum = sum.findMaxSum(values);
        System.out.println(maxSum);
    }

}
