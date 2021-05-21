package com.li.map;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

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
                return new Point(x, y + speed);
            }
            case DOWN: {
                return new Point(x, y - speed);
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

    /**
     * 仅计算直线位置的方向
     * @param targetPoint 相邻位置
     * @return 相对于目标位置的方向
     */
    public Direction calDirection(Point targetPoint) {
        int subX = targetPoint.x - this.x;
        int subY = targetPoint.y - this.y;

        if (subX != 0 && subY != 0) {
            throw new IllegalArgumentException("calulate direction, target point must be next to this point");
        }

        if (subX == 0) {
            return subY < 0 ? Direction.DOWN : Direction.UP;
        }

        return subX < 0 ? Direction.LEFT : Direction.RIGHT;

    }

    public List<Point> calRoutes(Direction direction, int range) {
        List<Point> points = new ArrayList<>(range);
        switch (direction) {
        case UP: {
            for (int i = 1; i <= range; i++) {
                points.add(new Point(x, y + i));
            }
            return points;
        }
        case DOWN: {
            for (int i = 1; i <= range; i++) {
                points.add(new Point(x, y - i));
            }
            return points;
        }
        case LEFT: {
            for (int i = 1; i <= range; i++) {
                points.add(new Point(x - i, y ));
            }
            return points;
        }
        case RIGHT: {
            for (int i = 1; i <= range; i++) {
                points.add(new Point(x + i, y ));
            }
            return points;
        }
        default: {
            return null;
        }
        }
    }

    @Override
    public String toString() {
        return "[" + x + "," + y + "]";
    }
}
