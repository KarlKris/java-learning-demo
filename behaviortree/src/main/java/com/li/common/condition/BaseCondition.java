package com.li.common.condition;

import com.li.common.behaviour.BaseBehaviour;
import com.li.common.behaviour.Behaviour;
import com.li.common.status.Status;

/**
 * @Description 条件节点基础类
 * @Author li-yuanwen
 * @Date 2021/3/1 16:58
 */
public abstract class BaseCondition extends BaseBehaviour implements Condition {


    @Override
    public void onInitialize() {

    }


    @Override
    public void onTerminate(Status status) {

    }

    @Override
    public void addChild(Behaviour behaviour) {
        throw new RuntimeException("invalid op");
    }
}
