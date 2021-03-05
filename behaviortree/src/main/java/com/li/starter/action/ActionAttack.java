package com.li.starter.action;

import com.li.common.action.BaseAction;
import com.li.common.status.Status;

/**
 * @Description 攻击节点
 * @Author li-yuanwen
 * @Date 2021/3/4 20:42
 */
public class ActionAttack extends BaseAction {

    @Override
    public void init() {

    }

    @Override
    public void close() {

    }

    @Override
    public Status update() {
        System.out.println("ActionAttack");
        return Status.SUCCESS;
    }

}
