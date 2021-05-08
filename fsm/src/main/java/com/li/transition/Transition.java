package com.li.transition;

import com.li.event.Event;
import com.li.map.GameMap;
import com.li.module.shouweidigong.BlackBoard;
import com.li.state.State;
import lombok.Getter;

/**
 * @author li-yuanwen
 *
 * 状态转换的处理
 */
@Getter
public abstract class Transition<B> {

    /** 触发事件类型 **/
    private String eventCode;

    /** 触发前状态 **/
    private State currentState;

    /** 触发后状态 **/
    private State nextState;

    public State execute(Event<B> event, GameMap map, BlackBoard blackBoard) {
        if (doExecute(event.getOwnerId(), event.getTime(), event.getBody(), map, blackBoard)) {
            return nextState;
        }
        return currentState;
    }

    public boolean isExecutable(State state) {
        return state.equals(currentState);
    }

    /**
     *
     * @param body 事件内容
     * @return 是否成功执行并改变状态
     */
    protected abstract boolean doExecute(int id, int time, B body, GameMap map, BlackBoard blackBoard);

}
