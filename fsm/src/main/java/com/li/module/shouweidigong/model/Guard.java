package com.li.module.shouweidigong.model;

import com.li.map.GameMap;
import com.li.map.Point;
import com.li.module.shouweidigong.BlackBoard;
import com.li.module.shouweidigong.ShouWeiDiGongConstants;
import com.li.module.shouweidigong.mathine.ShouWeiStateMathine;
import com.li.state.State;
import com.li.state.StateConstant;

import java.util.ArrayList;
import java.util.List;

/**
 * @author li-yuanwen
 * 守卫
 */
public class Guard extends ShouWeiStateMathine {

    /** 当前坐标 **/
    private Point currentPoint;

    /** 起始坐标 **/
    private Point sourcePoint;

    public Guard(int id, StateConstant stateConstant, Point sourcePoint, BlackBoard blackBoard, GameMap map) {
        super(id, stateConstant, blackBoard, map);
        this.sourcePoint = sourcePoint;
        this.currentPoint = sourcePoint;
    }

    public boolean isOutOfRange() {
        return Math.abs(currentPoint.getX() - sourcePoint.getX()) > ShouWeiDiGongConstants.GUARD_RANGE
                || Math.abs(currentPoint.getY() - sourcePoint.getY()) > ShouWeiDiGongConstants.GUARD_RANGE;
    }

    @Override
    protected List<State> declareAllStates() {
        List<State> states = new ArrayList<>();
        return states;
    }


    @Override
    public Point getCurPoint() {
        return currentPoint;
    }
}
