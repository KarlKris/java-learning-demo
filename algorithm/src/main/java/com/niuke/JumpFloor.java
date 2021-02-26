package com.niuke;

/**
 * @Description 一只青蛙一次可以跳上1级台阶，也可以跳上2级
 *              求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）
 * @Author li-yuanwen
 * @Date 2021/2/21 19:30
 */
public class JumpFloor {

    public int jumpFloor(int target) {
        if (target < 0) {
            return 0;
        }
        if (target <= 2) {
            return target;
        }
        return jumpFloor(target - 1) + jumpFloor(target - 2);
    }

    public static void main(String[] args) {
        JumpFloor jumpFloor = new JumpFloor();
        System.out.println(jumpFloor.jumpFloor(4));
    }

}