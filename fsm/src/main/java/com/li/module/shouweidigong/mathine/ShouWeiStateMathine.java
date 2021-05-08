package com.li.module.shouweidigong.mathine;

import com.li.event.Event;
import com.li.machine.StateMachine;
import com.li.map.GameMap;
import com.li.map.Point;
import com.li.module.shouweidigong.BlackBoard;
import com.li.state.State;
import com.li.state.StateConstant;

/**
 * @author li-yuanwen
 * 守卫地宫游戏 AI抽象
 */
public abstract class ShouWeiStateMathine extends StateMachine {

    /** ai标识 **/
    private int id;

    /** 当前状态 **/
    private State state;

    public ShouWeiStateMathine(int id, StateConstant stateConstant, BlackBoard blackBoard, GameMap map) {
        super(blackBoard, map);
        this.id = id;
        this.state = getState(stateConstant);
    }

    public <T> void execute(Event<T> event) {
        this.state = execute(getCurState().getState(), event);
    }

    public int getId() {
        return id;
    }

    /**
     *
     * @return 当前状态
     */
    private State getCurState() {
        return state;
    };

    /**
     *
     * @return 当前位置
     */
    public abstract Point getCurPoint();
}
