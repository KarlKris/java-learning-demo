package com.li.starter.condition;

import com.li.common.behaviour.Behaviour;
import com.li.common.blackboard.BlackBoard;
import com.li.common.condition.Condition;
import com.li.common.status.Status;

/**
 * @Description 描述
 * @Author li-yuanwen
 * @Date 2021/3/1 16:57
 */
public class ConditionIsSeeEnemy implements Condition {


    @Override
    public boolean valid(BlackBoard board) {
        return false;
    }

    @Override
    public Status tick() {
        return null;
    }

    @Override
    public void onInitialize() {

    }

    @Override
    public Status update() {
        return null;
    }

    @Override
    public void onTerminate(Status status) {

    }

    @Override
    public void addChild(Behaviour behaviour) {

    }
}
