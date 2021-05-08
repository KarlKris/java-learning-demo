package com.li.map;

import cn.hutool.core.util.RandomUtil;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @author li-yuanwen
 * 游戏地图
 */
public class GameMap {


    /** 地图长度 **/
    private int x;

    /** 地图宽度 **/
    private int y;

    /** 障碍物 **/
    private Map<Integer, Set<Integer>> obstacles;

    public GameMap(int x, int y, List<Point> obstacles) {
        this(x, y);

        checkAndUpdateObstacles(obstacles);
    }

    public GameMap(int x, int y) {
        this.x = x;
        this.y = y;
    }

    private void checkAndUpdateObstacles(List<Point> obstacles) {
        if (obstacles == null) {
            this.obstacles = new HashMap<>(0);
            return;
        }
        Map<Integer, Set<Integer>> temp = new HashMap<>(obstacles.size());
        for (Point point : obstacles) {
            int pointX = point.getX();
            if (pointX < 0) {
                throw new IndexOutOfBoundsException("obstacle point x less than 0 ");
            }
            if (pointX >= x) {
                throw new IndexOutOfBoundsException("obstacle point x more than " + x);
            }

            int pointY = point.getY();
            if (pointY < 0) {
                throw new IndexOutOfBoundsException("obstacle point y less than 0 ");
            }
            if (pointY >= y) {
                throw new IndexOutOfBoundsException("obstacle point y more than " + y);
            }

            Set<Integer> ys = temp.computeIfAbsent(pointX, k -> new HashSet<>(2));
            ys.add(pointY);
        }
        this.obstacles = temp;
    }

    public List<Point> pathFinding(int startX, int startY, int destX, int destY) {
        /** 检查参数合法性 **/
        if (startX < 0 || destX < 0 || startY < 0 || destY < 0) {
            throw new IndexOutOfBoundsException("startX ,startY, destX or destY less than 0 ");
        }
        if (startX >= x || destX >= x) {
            throw new IndexOutOfBoundsException("startX or destX out of " + x);
        }
        if (startY >= y || destY >= y) {
            throw new IndexOutOfBoundsException("startY or destY out of " + y);
        }

        PriorityQueue<LinkedPoint> queue = new PriorityQueue<LinkedPoint>(calPointSize()
                , Comparator.comparingDouble(o -> o.priority));
        // 设置起点
        LinkedPoint startPoint = new LinkedPoint(startX, startY);
        startPoint.setPriority(0);
        queue.add(startPoint);

        return doPathFinding(queue, new ArrayList<>(), startX, startY, destX, destY);
    }

    public void randomObstacles(int num) {
        if (num + obstacles.size() > x * y - 2) {
            throw new UnsupportedOperationException("obstacles num more than total point");
        }

        for (int i = 0; i < num; i++) {
            Set<Integer> ys = obstacles.computeIfAbsent(RandomUtil.randomInt(x), k -> new HashSet<>(2));
            ys.add(RandomUtil.randomInt(y));
        }

    }


    private List<Point> doPathFinding(PriorityQueue<LinkedPoint> queue, List<Point> closeList
            , int startX, int startY, int destX, int destY) {
        if (queue.isEmpty()) {
            return null;
        }
        // 优先级函数f(n) = g(n) + h(n) 选取综合优先级最高（值最小）的节点。
        LinkedPoint point = queue.poll();
        int x = point.getX();
        int y = point.getY();
        // 找到终点
        if (x == destX && y == destY) {
            List<Point> path = new ArrayList<>();
            while (point != null) {
                path.add(new Point(point.getX(), point.getY()));
                point = point.parent;
            }
            return path;
        }

        // 加入到已检查队列
        closeList.add(point);
        // 遍历附近邻点,上下左右
        // 上
        if (isValid(x, y + 1) && !isObstacle(x, y + 1)) {
            if (closeList.stream().noneMatch(p -> p.getX() == x && p.getY() == y + 1)) {
                LinkedPoint up = new LinkedPoint(x, y + 1, point);
                double priority = calGn(x, y + 1, startX, startY) + calHn(x, y + 1, destX, destY);
                up.setPriority(priority);
                queue.add(up);
            }
        }
        // 下
        if (isValid(x, y - 1) && !isObstacle(x, y - 1)) {
            if (closeList.stream().noneMatch(p -> p.getX() == x && p.getY() == y - 1)) {
                LinkedPoint down = new LinkedPoint(x, y - 1, point);
                double priority = calGn(x, y - 1, startX, startY) + calHn(x, y - 1, destX, destY);
                down.setPriority(priority);
                queue.add(down);
            }
        }
        // 左
        if (isValid(x - 1, y) && !isObstacle(x - 1, y)) {
            if (closeList.stream().noneMatch(p -> p.getX() == x - 1 && p.getY() == y)) {
                LinkedPoint left = new LinkedPoint(x - 1, y, point);
                double priority = calGn(x - 1, y, startX, startY) + calHn(x - 1, y, destX, destY);
                left.setPriority(priority);
                queue.add(left);
            }
        }
        // 右
        if (isValid(x + 1, y) && !isObstacle(x + 1, y)) {
            if (closeList.stream().noneMatch(p -> p.getX() == x + 1 && p.getY() == y)) {
                LinkedPoint right = new LinkedPoint(x + 1, y, point);
                double priority = calGn(x + 1, y, startX, startY) + calHn(x + 1, y, destX, destY);
                right.setPriority(priority);
                queue.add(right);
            }
        }
        return doPathFinding(queue, closeList, startX, startY, destX, destY);
    }


    private boolean isValid(int x, int y) {
        return (x >= 0 && x < this.x) && (y >= 0 && y < this.y);
    }

    private boolean isObstacle(int x, int y) {
        Set<Integer> temp;
        return (temp = this.obstacles.get(x)) != null && temp.contains(y);
    }

    /**
     * 节点n距离起点的代价。
     **/
    private double calGn(int x, int y, int startX, int startY) {
        int disX = Math.abs(x - startX);
        int disY = Math.abs(y - startY);
        return disX + disY;
    }

    /**
     * 节点n距离终点的预计代价，这也就是A*算法的启发函数。
     * <p>
     * 对于网格形式的图，有以下这些启发函数可以使用：
     * <p>
     * 如果图形中只允许朝上下左右四个方向移动，则可以使用曼哈顿距离（Manhattan distance）。
     * 如果图形中允许朝八个方向移动，则可以使用对角距离。
     * 如果图形中允许朝任何方向移动，则可以使用欧几里得距离（Euclidean distance）。
     **/
    private double calHn(int x, int y, int destX, int destY) {
        int disX = Math.abs(x - destX);
        int disY = Math.abs(y - destY);
        return disX + disY + ((Math.sqrt(2) - 1) * Math.min(disX, disY));
    }


    private int calPointSize() {
        return x * y;
    }

    @Setter
    class LinkedPoint extends Point {

        /**
         * 父节点
         **/
        private LinkedPoint parent;
        /**
         * 优先级
         **/
        private double priority;

        LinkedPoint(int x, int y) {
            super(x, y);
        }

        LinkedPoint(int x, int y, LinkedPoint parent) {
            super(x, y);
            this.parent = parent;
        }

    }

    private static List<Point> buildObstacles(int y) {
        List<Point> points = new ArrayList<>(9);
        boolean has = false;
        for (int i = 0; i < 10; i++) {
            if (!has && RandomUtil.randomBoolean()) {
                has = true;
                continue;
            }
            points.add(new Point(i, y));
        }

        return points;
    }
}
