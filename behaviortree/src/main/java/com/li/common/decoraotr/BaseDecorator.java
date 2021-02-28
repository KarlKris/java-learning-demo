package com.li.common.decoraotr;

import com.li.common.behaviour.BaseBehaviour;
import com.li.common.behaviour.Behaviour;

/**
 * @Auther: li-yuanwen
 * @Date: 2021/2/28 22:28
 * @Description: 基础装饰器 控制节点
 **/
public abstract class BaseDecorator extends BaseBehaviour implements Decorator {

    private Behaviour behaviour;

    public void setBehaviour(Behaviour behaviour) {
        this.behaviour = behaviour;
    }

}
