package com.li.common.behaviour;

import com.li.common.blackboard.BlackBoard;
import com.li.common.status.Status;

/**
 * @Auther: li-yuanwen
 * @Date: 2021/2/28 21:47
 * @Description: 基础节点对象,规定API调用契约
 **/
public abstract class BaseBehaviour implements Behaviour {

    /** 状态 **/
    private Status status;
    /** 黑板 **/
    private BlackBoard board;

    @Override
    public final Status tick() {
        // 行为第一次调用
        if (status != Status.RUNNING) {
            onInitialize();
        }


        // 更新状态
        status = update();
        // 行为结束
        if (status != Status.RUNNING) {
            onTerminate(status);
        }

        return status;
    }
}
