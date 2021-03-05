package com.li.common.behaviour;

import com.li.common.status.Status;

/**
 * 抽象行为的概念,行为树的基石。该接口可以被激活，运行和注销.
 **/
public interface Behaviour {

    /**
     * API调用契约
     */
    Status tick();

    /**
     * 在行为的更新方法即将被首次调用前调用
     **/
    void onInitialize();

    /**
     * 在每次行为树更新时被调用且仅被调用一次
     **/
    Status update();

    /**
     * 当刚刚更新的行为不再处于运行状态时，立即调用一次
     **/
    void onTerminate(Status status);

    /**
     * 添加子节点
     **/
    void addChild(Behaviour behaviour);

    /** 获取节点状态 **/
    Status getStatus();
}
