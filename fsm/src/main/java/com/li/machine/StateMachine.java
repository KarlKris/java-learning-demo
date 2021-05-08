package com.li.machine;

import com.li.event.Event;
import com.li.map.GameMap;
import com.li.module.shouweidigong.BlackBoard;
import com.li.state.State;
import com.li.state.StateConstant;
import com.li.transition.Transition;

import java.util.List;

/**
 * @author li-yuanwen
 * 状态机
 */
public abstract class StateMachine {

    /** 状态集合 **/
    private List<State> states;
    /** 地图信息 **/
    private GameMap map;
    /** 黑板信息 **/
    private BlackBoard blackBoard;

    public StateMachine(BlackBoard blackBoard, GameMap map) {
        this.map = map;
        this.blackBoard = blackBoard;
    }

    public <T> State execute(StateConstant constant, Event<T> event) {
        State state = getState(constant);
        if (state == null) {
            throw new UnsupportedOperationException("states not contains constant:" + constant.name());
        }
        for (Transition transition : state.getTransitions()) {
            if (!transition.isExecutable(state)) {
                continue;
            }
            return transition.execute(event, map, blackBoard);
        }

        throw new UnsupportedOperationException("state transitions not contains transition:" + constant.name() + "-" + event.getEventCode());
    }


    protected State getState(StateConstant constant) {
        if (states == null || states.isEmpty()) {
            states = declareAllStates();
        }

        for (State state : states) {
            if (!state.getState().equals(constant)) {
                continue;
            }
            return state;
        }
        return null;
    }

    /**
     *
     * @return 定义所有状态
     */
    protected abstract List<State> declareAllStates();


}
