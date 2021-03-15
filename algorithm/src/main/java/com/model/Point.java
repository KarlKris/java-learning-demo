package com.model;

import lombok.Getter;

/**
 * @Description 位置节点
 * @Author li-yuanwen
 * @Date 2021/3/15 11:17
 */
@Getter
public class Point {

    // x坐标
    private int x;
    // y坐标
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point)) return false;

        Point point = (Point) o;

        if (x != point.x) return false;
        return y == point.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
