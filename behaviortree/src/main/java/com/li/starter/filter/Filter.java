package com.li.starter.filter;

import com.li.starter.action.Action;
import com.li.starter.behaviour.Behaviour;
import com.li.starter.sequence.Sequence;

/**
 * 过滤器，一种会在特殊条件下拒绝执行子行为的行为树分支。
 * 许多行为都具有在执行时必须时刻满足的假设条件，当这些条件被破坏时，整颗资行为属都应及时推出
 **/
public interface Filter extends Sequence {

    /**
     * 前置条件
     * @param condition 前置条件
     **/
    void addCondtion(Behaviour condition);

    /**
     * 执行
     * @param action 执行动作
     **/
    void addAction(Action action);
}
