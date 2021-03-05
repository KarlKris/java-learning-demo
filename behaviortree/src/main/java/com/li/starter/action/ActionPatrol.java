package com.li.starter.action;

import com.li.common.action.BaseAction;
import com.li.common.status.Status;

/**
 * @Description 巡逻节点
 * @Author li-yuanwen
 * @Date 2021/3/4 20:50
 */
public class ActionPatrol extends BaseAction {

    @Override
    public void init() {

    }

    @Override
    public void close() {

    }

    @Override
    public Status update() {
        System.out.println("ActionPatrol");
        return Status.SUCCESS;
    }
}
