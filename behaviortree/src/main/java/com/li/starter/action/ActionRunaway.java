package com.li.starter.action;

import com.li.common.action.BaseAction;
import com.li.common.status.Status;

/**
 * @Description 跑路节点
 * @Author li-yuanwen
 * @Date 2021/3/4 20:51
 */
public class ActionRunaway extends BaseAction {

    @Override
    public void init() {

    }

    @Override
    public void close() {

    }

    @Override
    public Status update() {
        System.out.println("ActionRunaway");
        return Status.SUCCESS;
    }
}
