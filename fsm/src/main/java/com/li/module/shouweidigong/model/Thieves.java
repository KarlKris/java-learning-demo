package com.li.module.shouweidigong.model;

import com.li.event.EventConstant;
import com.li.map.GameMap;
import com.li.map.Point;
import com.li.module.shouweidigong.BlackBoard;
import com.li.module.shouweidigong.mathine.ShouWeiStateMathine;
import com.li.module.shouweidigong.transition.EscapeTransition;
import com.li.module.shouweidigong.transition.SearchTransition;
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

    public void updatePoint(Point point) {
        this.curPoint = point;
    }

    @Override
    protected List<State> declareAllStates() {
        List<State> states = new ArrayList<>();

        State searchState = new State(StateConstant.SEARCH);
        State escapeState = new State(StateConstant.ESCAPE);
        SearchTransition searchTransition = new SearchTransition(EventConstant.SEARCH, searchState, escapeState);
        searchState.addTransition(searchTransition);

        EscapeTransition escapeTransition = new EscapeTransition(EventConstant.DISCORVERED, escapeState, searchState);
        escapeState.addTransition(escapeTransition);

        states.add(searchState);
        states.add(escapeState);

        return states;
    }

    @Override
    public Point getCurPoint() {
        return curPoint;
    }
}
