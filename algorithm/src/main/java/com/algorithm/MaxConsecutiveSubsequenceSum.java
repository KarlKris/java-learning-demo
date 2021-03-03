package com.algorithm;

import cn.hutool.core.util.ArrayUtil;
import common.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

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
        int maxSum = Integer.MIN_VALUE;
        int[] res = null;

        int length = seq.length;


        for (int i = 0; i < length - 1; i++) {
            int sum = 0;
            for (int j = i; j < length; j++) {
                sum += seq[j];

                if (sum > maxSum) {
                    res = new int[j - i + 1];
                    System.arraycopy(seq, i, res, 0, j - i + 1);

                    maxSum = sum;
                }
            }
        }

        return res;
    }

    /** 线性方法找出最大子序列 **/
    public int[] linear(int[] array) {
        if (ArrayUtil.isEmpty(array)) {
            throw new RuntimeException("array is null or empty");
        }

        int length = array.length;
        if (length == 1) {
            return array;
        }

        int maxSum = array[0];
        int sum = maxSum;
        int leftIndex = 0;
        int rightIndex = 0;
        int curLeftIndex = 0;

        for (int i = 1; i < length; i++) {
            // 若当前之和大于零，则直接计算，否则直接清空
            // 否则重新查找剩余的序列中最大子序列
            if (sum >= 0) {
                sum += array[i];
            }else {
                sum = array[i];
                curLeftIndex = i;
            }

            // 判断找出的最大子序列和已知的最大子序列
            if (sum > maxSum) {
                maxSum = sum;
                leftIndex = curLeftIndex;
                rightIndex = i;
            }
        }

        int[] res = new int[rightIndex - leftIndex + 1];
        System.arraycopy(array, leftIndex, res, 0, rightIndex - leftIndex + 1);

        return res;
    }


    /** 分治递归求解最大子序列 **/
    public int[] recursion(int[] array) {
        MaxConsecutiveSubsequenceCtx ctx = doRecursion(array, 0, array.length - 1);
        int[] res = new int[ctx.right - ctx.left + 1];
        System.arraycopy(array, ctx.left, res, 0, ctx.right - ctx.left + 1);

        return res;
    }

    private MaxConsecutiveSubsequenceCtx doRecursion(int[] array, int left, int right) {
        if (left == right) {
            return new MaxConsecutiveSubsequenceCtx(left, right, array[left]);
        }

        int mid = (right + left) / 2;

        List<MaxConsecutiveSubsequenceCtx> list = new ArrayList<>(3);

        list.add(doRecursion(array, left, mid));
        list.add(doRecursion(array, mid + 1, right));
        list.add(findCrossSubsequence(array, left, mid, right));

        // 排序比较找出的左边，右边和中间序列
        list.sort((o1, o2) -> o2.sum - o1.sum);

        return list.get(0);
    }

    /** 找出位于中间的最大子序列 **/
    private MaxConsecutiveSubsequenceCtx findCrossSubsequence(int[] array, int left, int mid, int right) {
        int leftSum = 0;
        int maxLeftSum = Integer.MIN_VALUE;
        int leftIndex = 0;
        for (int i = mid; i >= left; i--) {
            leftSum += array[i];

            if (leftSum > maxLeftSum) {
                leftIndex = i;
                maxLeftSum = leftSum;
            }
        }

        int rightSum = 0;
        int maxRightSum = Integer.MIN_VALUE;
        int rightIndex = 0;
        for (int i = mid + 1; i <= right; i++) {
            rightSum += array[i];

            if (rightSum > maxRightSum) {
                rightIndex = i;
                maxRightSum = rightSum;
            }
        }

        return new MaxConsecutiveSubsequenceCtx(leftIndex, rightIndex, maxLeftSum + maxRightSum);

    }


    public static void main(String[] args) {
        int[] array = {-12, -11, -10, -13, -15, -9, -5, -23, -14};
//        int[] array = {13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7};
        ArrayUtils.Array_Println(array);

        MaxConsecutiveSubsequenceSum sum = new MaxConsecutiveSubsequenceSum();

        System.out.println("-----------------directlyCalculating----------------------");
        ArrayUtils.Array_Println(sum.directlyCalculating(array));
        System.out.println("-----------------recursion----------------------");
        ArrayUtils.Array_Println(sum.recursion(array));
        System.out.println("-----------------linear----------------------");
        ArrayUtils.Array_Println(sum.linear(array));


    }


    /**
     * 子序列信息
     **/
    class MaxConsecutiveSubsequenceCtx {
        private int left;
        private int right;
        private int sum;

        MaxConsecutiveSubsequenceCtx(int left, int right, int sum) {
            this.left = left;
            this.right = right;
            this.sum = sum;
        }
    }
}
