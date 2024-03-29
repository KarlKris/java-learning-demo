package com.li.module.shouweidigong.model;

import com.li.event.EventConstant;
import com.li.map.GameMap;
import com.li.map.Point;
import com.li.module.shouweidigong.BlackBoard;
import com.li.module.shouweidigong.ShouWeiDiGongConstants;
import com.li.module.shouweidigong.mathine.ShouWeiStateMathine;
import com.li.module.shouweidigong.transition.CharseTransition;
import com.li.module.shouweidigong.transition.PatrolTransition;
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

    public void updatePoint(Point point) {
        this.currentPoint = point;
    }

    @Override
    protected List<State> declareAllStates() {
        List<State> states = new ArrayList<>();

        State patrolState = new State(StateConstant.PATROL);
        State chaseState = new State(StateConstant.CHASE);
        PatrolTransition patrolTransition = new PatrolTransition(EventConstant.PATROL, patrolState, chaseState);
        patrolState.addTransition(patrolTransition);

        CharseTransition charseTransition = new CharseTransition(EventConstant.FIND, chaseState, patrolState);
        chaseState.addTransition(charseTransition);

        states.add(patrolState);
        states.add(chaseState);

        return states;
    }


    @Override
    public Point getCurPoint() {
        return currentPoint;
    }
}
