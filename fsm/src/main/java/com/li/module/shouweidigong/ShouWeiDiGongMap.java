package com.li.module.shouweidigong;

import com.li.map.GameMap;
import com.li.map.Point;
import lombok.Getter;

import java.util.List;

/**
 * @author li-yuanwen
 */
@Getter
public class ShouWeiDiGongMap extends GameMap {

    /** 宝物坐标 **/
    private Point treaturePoint;

    public ShouWeiDiGongMap(int x, int y, List<Point> obstacles) {
        super(x, y, obstacles);
        calTreaturePoint(x, y);
    }

    public ShouWeiDiGongMap(int x, int y) {
        super(x, y);
        calTreaturePoint(x, y);
    }

    private void calTreaturePoint(int x, int y) {
        this.treaturePoint = new Point(x / 2, y / 2);
    }

    public List<Point> pathFind(Point curPoint) {
        return pathFinding(curPoint.getX(), curPoint.getY(), treaturePoint.getX(), treaturePoint.getY());
    }
}
