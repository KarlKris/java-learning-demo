package com.li.module.shouweidigong.model;

import com.li.map.GameMap;
import com.li.map.Point;
import com.li.module.shouweidigong.BlackBoard;
import com.li.module.shouweidigong.mathine.ShouWeiStateMathine;
import com.li.state.State;
import com.li.state.StateConstant;

import java.util.ArrayList;
import java.util.List;

/**
 * @author li-yuanwen
 * 盗贼
 */
public class Thieves extends ShouWeiStateMathine {

    /** 盗贼位置 **/
    private Point curPoint;

    public Thieves(int id, StateConstant stateConstant, Point curPoint, BlackBoard blackBoard, GameMap map) {
        super(id, stateConstant, blackBoard, map);
        this.curPoint = curPoint;
    }

    @Override
    protected List<State> declareAllStates() {
        return new ArrayList<>();
    }

    @Override
    public Point getCurPoint() {
        return curPoint;
    }
}
