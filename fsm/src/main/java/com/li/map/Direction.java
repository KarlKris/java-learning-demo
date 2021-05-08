package com.li.map;

import cn.hutool.core.util.RandomUtil;

/**
 * 方向
 * @author li-yuanwen
 */
public enum Direction {

    /** 上 **/
    UP,
    /** 下 **/
    DOWN,
    /** 左 **/
    LEFT,
    /** 右 **/
    RIGHT,

    ;
    public static Direction randomDirection() {
        Direction[] values = values();
        int length = values.length;
        int randomInt = RandomUtil.randomInt(length);

        return values[randomInt];
    }


    public static Direction randomDirectionUnless(Direction direction) {
        Direction[] values = values();
        int length = values.length;
        int randomInt = RandomUtil.randomInt(length);

        Direction temp;
        return (temp = values[randomInt]) != direction ? temp : values[(randomInt + 1) % length];
    }
}
