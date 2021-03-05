package com.li.common.action;

import com.li.common.behaviour.BaseBehaviour;
import com.li.common.behaviour.Behaviour;

/**
 * @Description 基础行为节点类
 * @Author li-yuanwen
 * @Date 2021/3/4 20:29
 */
public abstract class BaseAction extends BaseBehaviour implements Action {

    @Override
    public final void addChild(Behaviour behaviour) {
        throw new UnsupportedOperationException("Action cant add Child");
    }
}
