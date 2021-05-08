package com.li.map;

import lombok.Getter;

/**
 * @author li-yuanwen
 * 地图位置坐标
 */
@Getter
public class Point {

    /** x **/
    private int x;
    /** y **/
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point calNewPoint(Direction direction, int speed) {
        switch (direction) {
            case UP: {
                return new Point(x, y - speed);
            }
            case DOWN: {
                return new Point(x, y + speed);
            }
            case LEFT: {
                return new Point(x - speed, y);
            }
            case RIGHT: {
                return new Point(x + speed, y);
            }
            default: {
                 return null;
            }
        }
    }

}
