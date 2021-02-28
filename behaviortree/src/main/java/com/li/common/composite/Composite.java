package com.li.common.composite;

import com.li.common.behaviour.Behaviour;

/**
 * 复合行为
 * 行为树中具有多个子节点的分支被称为复合行为
 **/
public interface Composite extends Behaviour {


    /**
     * 添加子行为
     **/
    void addChild(Behaviour behaviour);

    /**
     * 移除子行为
     **/
    void removeChild(Behaviour behaviour);

    /**
     * 清除所有子行为
     **/
    void clearChildren();
}
