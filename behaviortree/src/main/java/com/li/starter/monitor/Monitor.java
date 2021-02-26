package com.li.starter.monitor;

import com.li.starter.action.Action;
import com.li.starter.behaviour.Behaviour;
import com.li.starter.parallel.Parallel;

/**
 * 监视器，持续检查假设的有效性（即监视模式条件），可以说是使用并行器行为时最有用的模式。
 *
 **/
public interface Monitor extends Parallel {

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
