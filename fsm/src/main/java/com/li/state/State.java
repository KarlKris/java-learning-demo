package com.li.state;

import com.li.transition.Transition;
import lombok.Getter;

import java.util.List;

/**
 * @author li-yuanwen
 *
 * 状态
 */
@Getter
public class State {

    /** 状态 **/
    private StateConstant state;

    /** 当前状态可以执行的动作 **/
    private List<Transition> transitions;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof State))
            return false;

        State state1 = (State) o;

        return state == state1.state;
    }

    @Override
    public int hashCode() {
        return state != null ? state.hashCode() : 0;
    }
}
